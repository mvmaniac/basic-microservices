package io.devfactory.infra.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.devfactory.infra.kafka.dto.OrderProducerMessage;
import io.devfactory.infra.kafka.dto.OrderProductPayload;
import io.devfactory.web.dto.response.OrderResponseView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderProducerService {

  private final KafkaTemplate<String, String> kafkaTemplate;
  private final ObjectMapper objectMapper;

  public OrderProducerService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
    this.kafkaTemplate = kafkaTemplate;
    this.objectMapper = objectMapper;
  }

  public void send(String topic, String memberUniqueId, OrderResponseView orderResponseView) {
    try {
      final var payload = OrderProductPayload.of(memberUniqueId, orderResponseView);
      final var producerMessage = OrderProducerMessage.of(topic, payload);

      final var jsonString = objectMapper.writeValueAsString(producerMessage);
      kafkaTemplate.send(topic, jsonString);

      log.debug("[dev] Kafka Producer sent data from the order microservice: {}", jsonString);

    } catch (JsonProcessingException e) {
      log.error("KafkaProducerService.send: {}", e.getMessage(), e);
    }
  }

}
