package io.devfactory.infra.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.devfactory.web.repository.CatalogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Slf4j
@Service
public class CatalogConsumerService {

  private final CatalogRepository catalogRepository;
  private final ObjectMapper objectMapper;

  public CatalogConsumerService(CatalogRepository catalogRepository, ObjectMapper objectMapper) {
    this.catalogRepository = catalogRepository;
    this.objectMapper = objectMapper;
  }

  @Transactional
  @KafkaListener(topics = "catalog-consumer-topic")
  public void updateQuantity(String kafkaMessage) {
    log.debug("[dev] kafkaMessage: {}", kafkaMessage);

    try {
      final var readValue = objectMapper.readValue(kafkaMessage, new TypeReference<Map<String, Object>>() {
      });

      final var productId = readValue.getOrDefault("productUniqueId", "").toString();
      catalogRepository.findByProductUniqueId(productId)
          .ifPresentOrElse(
              catalog -> {
                final var quantity = Integer.parseInt(readValue.getOrDefault("quantity", "0").toString());
                catalog.updateStock(quantity);
              },
              () -> log.error("KafkaConsumerService.updateQuantity: {} not found", productId)
          );

    } catch (JsonProcessingException e) {
      log.error("KafkaConsumerService.updateQuantity: {}", e.getMessage(), e);
    }
  }

}
