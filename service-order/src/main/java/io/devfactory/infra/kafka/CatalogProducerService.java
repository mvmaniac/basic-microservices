package io.devfactory.infra.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.devfactory.web.dto.response.OrderResponseView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CatalogProducerService {

  private final KafkaTemplate<String, String> kafkaTemplate;
  private final ObjectMapper objectMapper;

  public CatalogProducerService(KafkaTemplate<String, String> kafkaTemplate,
      ObjectMapper objectMapper) {
    this.kafkaTemplate = kafkaTemplate;
    this.objectMapper = objectMapper;
  }

  public void send(String topic, OrderResponseView orderResponseView) {
    try {
      final var jsonString = objectMapper.writeValueAsString(orderResponseView);
      kafkaTemplate.send(topic, jsonString);

      log.debug("[dev] Kafka Producer sent data from the order microservice: {}", jsonString);

    } catch (JsonProcessingException e) {
      log.error("KafkaProducerService.send.JsonProcessingException: {}", e.getMessage(), e);
    }
  }

}
