package com.maslke.spring.es;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.InternalSum;
import org.elasticsearch.search.aggregations.metrics.SumAggregationBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetAddress;
import java.util.Map;

public class EsDao {

    private TransportClient transportClient;

    @PostConstruct
    private void init() throws Exception {
        Settings settings = Settings.builder()
                .put("client.transport.sniff", true)
                .put("cluster.name", "es-cluster").build();
        transportClient = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
        System.out.println(transportClient.toString());
    }

    @PreDestroy
    private void destroy() {
        if (transportClient != null) {
            transportClient.close();
        }
    }

    public void searchAggr() throws Exception {
        SearchRequestBuilder searchRequestBuilder = transportClient.prepareSearch("bank");
        TermsAggregationBuilder builder = AggregationBuilders.terms("state_name").field("state.keyword").size(10000);
        SumAggregationBuilder sumAggregationBuilder = AggregationBuilders.sum("balance_sum").field("balance");
        searchRequestBuilder.addAggregation(builder.subAggregation(sumAggregationBuilder));
        // 查询
        SearchResponse response = searchRequestBuilder
                .execute().actionGet();
        Map<String, Aggregation> aggMap = response.getAggregations().getAsMap();
        StringTerms teams = (StringTerms) aggMap.get("state_name");
        for (Terms.Bucket teamBucket : teams.getBuckets()) {
            String team = (String) teamBucket.getKey();
            Map<String, Aggregation> subAggMap = teamBucket.getAggregations().getAsMap();
            InternalSum totalSalary = (InternalSum) subAggMap.get("balance_sum");
            double totalSalaryValue = totalSalary.getValue();
            System.out.println(team + " " + totalSalaryValue);
        }
    }

    public void searchByState(String state) throws Exception {

        QueryBuilder stateBuilder = QueryBuilders.matchQuery("state", state);
        QueryBuilder firstNameBuilder = QueryBuilders.matchQuery("firstname", "Vanessa");
        QueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().must(stateBuilder).must(firstNameBuilder);
        SearchRequestBuilder requestBuilder = transportClient.prepareSearch("bank")
                .setScroll(new TimeValue(60 * 1000))
                .setSize(1000)
                .setQuery(stateBuilder);
        SearchResponse rep = requestBuilder.get();
        do {
            for (SearchHit hit : rep.getHits().getHits()) {
                System.out.println(hit.getSourceAsString());
            }
            System.out.println("continue");
            rep = transportClient.prepareSearchScroll(rep.getScrollId()).setScroll(new TimeValue(60 * 1000)).get();
        } while (rep.getHits().getHits().length != 0);
    }
}
