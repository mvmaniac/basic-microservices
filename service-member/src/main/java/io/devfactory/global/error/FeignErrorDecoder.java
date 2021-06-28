package io.devfactory.global.error;

import feign.Response;
import feign.codec.ErrorDecoder;
import io.devfactory.error.ServiceRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

  @Override
  public Exception decode(String methodKey, Response response) {
    switch (response.status()) {
      case 400:
        break;
      case 404:
        if (methodKey.contains("retrieveOrders")) {
          return new ResponseStatusException(HttpStatus.valueOf(response.status()), "orders is empty");
        }
        break;
      default:
        return new ServiceRuntimeException(response.reason());
    }
    return null;
  }

}
