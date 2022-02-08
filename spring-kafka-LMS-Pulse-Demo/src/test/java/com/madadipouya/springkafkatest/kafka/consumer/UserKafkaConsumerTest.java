package com.madadipouya.springkafkatest.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.madadipouya.springkafkatest.dto.PulseRequestModel;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@EmbeddedKafka
@SpringBootTest(properties = "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserKafkaConsumerTest {

    private final String TOPIC_NAME = "com.madadipouya.kafka.user";

    private Producer<String, String> producer;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    private ObjectMapper objectMapper;

    @SpyBean
    private UserKafkaConsumer userKafkaConsumer;

    @Captor
    ArgumentCaptor<PulseRequestModel> userArgumentCaptor;

    @Captor
    ArgumentCaptor<String> topicArgumentCaptor;

    @Captor
    ArgumentCaptor<Integer> partitionArgumentCaptor;

    @Captor
    ArgumentCaptor<Long> offsetArgumentCaptor;

    @BeforeAll
    void setUp() {
        Map<String, Object> configs = new HashMap<>(KafkaTestUtils.producerProps(embeddedKafkaBroker));
        producer = new DefaultKafkaProducerFactory<>(configs, new StringSerializer(), new StringSerializer()).createProducer();
    }

    @Test
    void testLogKafkaMessages() throws JsonProcessingException {
        // Write a message (John Wick user) to Kafka using a test producer
        String uuid = "11111";
        String message = "";//objectMapper.writeValueAsString(new PulseRequestModel(uuid, "John", "Wick"));
        producer.send(new ProducerRecord<>(TOPIC_NAME, 0, uuid, message));
        producer.flush();

        // Read the message and assert its properties
        verify(userKafkaConsumer, timeout(5000).times(1))
                .logKafkaMessages(userArgumentCaptor.capture(), topicArgumentCaptor.capture(),
                        partitionArgumentCaptor.capture(), offsetArgumentCaptor.capture());

        PulseRequestModel user = userArgumentCaptor.getValue();
        assertNotNull(user);
//        assertEquals("11111", user.getUuid());
//        assertEquals("John", user.getFirstName());
//        assertEquals("Wick", user.getLastName());
        assertEquals(TOPIC_NAME, topicArgumentCaptor.getValue());
        assertEquals(0, partitionArgumentCaptor.getValue());
        assertEquals(0, offsetArgumentCaptor.getValue());
    }

    @AfterAll
    void shutdown() {
        producer.close();
    }
}