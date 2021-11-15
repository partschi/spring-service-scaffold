package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/v1")
class ApiController {

  @Value("${spring.application.name}")
  private String name;

  @GetMapping(path = "/info")
  Mono<String> getAppInfo() {
    return Mono.just(name);
  }

}
