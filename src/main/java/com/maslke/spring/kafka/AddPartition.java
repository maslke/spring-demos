package com.maslke.spring.kafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreatePartitionsResult;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewPartitions;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.common.KafkaFuture;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AddPartition {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(AdminClientConfig.CLIENT_ID_CONFIG, "consumer-client-1");
        try (AdminClient client = AdminClient.create(properties)) {
            try {
                createNewTopic(client, "test-topic2", 5, (short) 1);

                increasePartitions(client, "test-topic2", 10);
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.print("over");


    }

    private static void increasePartitions(AdminClient client, String topicName, int partitions) throws Exception {
        DescribeTopicsResult topicsResult = client.describeTopics(Arrays.asList(topicName));
        KafkaFuture<TopicDescription> future = topicsResult.values().get(topicName);
        TopicDescription description = future.get();
        if (description.partitions().size() >= partitions) {
            return;
        }
        Map<String, NewPartitions> newPartitionsMap = new HashMap<>();
        newPartitionsMap.put(topicName, NewPartitions.increaseTo(partitions));
        CreatePartitionsResult createPartitionsResult = client.createPartitions(newPartitionsMap);
        KafkaFuture<Void> future1 = createPartitionsResult.values().get(topicName);
        future1.get();
        System.out.println("create new partitions successfully");
    }

    private static void createNewTopic(AdminClient client, String topicName, int partition, short replicationFactor) throws Exception {
        if (topicIsExist(client, topicName)) {
            System.out.println("topic already exists.");
            return;
        }
        NewTopic topic = new NewTopic(topicName, partition, replicationFactor);
        CreateTopicsResult result = client.createTopics(Arrays.asList(topic));
        Map<String, KafkaFuture<Void>> values = result.values();
        KafkaFuture<Void> future = values.get(topicName);
        future.get();
        System.out.println("create successfully");
    }

    private static boolean topicIsExist(AdminClient client, String topicName) throws Exception {
        ListTopicsResult result = client.listTopics();
        KafkaFuture<Set<String>> future = result.names();
        Set<String> set = future.get(10000, TimeUnit.SECONDS);
        return set.contains(topicName);
    }
}
