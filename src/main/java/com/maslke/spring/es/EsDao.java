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
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetAddress;
import java.util.Random;
import java.util.concurrent.*;

@Component
public class EsDao {

    private TransportClient transportClient;

    ExecutorService executorService = new ThreadPoolExecutor(3, 3, 0,
            TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(3));

    @PostConstruct
    private void init() throws Exception {
        Settings settings = Settings.builder()
                .put("client.transport.sniff", true)
                .put("cluster.name", "es-cluter").build();
        transportClient = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
    }

    @PreDestroy
    private void destory() {
        if (transportClient != null) {
            transportClient.close();
        }
    }

    public void searchByState(String state) {
        QueryBuilder stateBuilder = QueryBuilders.termQuery("state", state);
        SearchRequestBuilder requestBuilder = transportClient.prepareSearch()
                .setIndices("bank")
                .setScroll(new TimeValue(30 * 1000))
                .setSize(1000)
                .setQuery(stateBuilder);
        SearchResponse rep = requestBuilder.get();
        do {
            for (SearchHit hit : rep.getHits().getHits()) {
                System.out.println(hit.getSourceAsString());
                executorService.submit(() -> {

                });
            }
            rep = transportClient.prepareSearchScroll(rep.getScrollId()).setScroll(new TimeValue(30 * 1000)).get();
        } while (rep.getHits().getHits().length != 0);
    }
}
