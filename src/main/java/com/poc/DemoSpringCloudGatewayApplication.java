package com.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.webflux.ProxyExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@SpringBootApplication
public class DemoSpringCloudGatewayApplication {

  private static final String MOCK_URL_SERVICE = "https://run.mocky.io";

  public static void main(String[] args) {
    SpringApplication.run(DemoSpringCloudGatewayApplication.class, args);
  }

  @Bean
  public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    return builder
        .routes()
        .route(
            "mock-1",
            r ->
                r.path("/mock")
                    .and()
                    .header("type", "1")
                    .filters(
                        f -> f.rewritePath("/mock", "/v3/12f9efb0-8942-4b3d-bf6f-727cdf89d92a"))
                    .uri(MOCK_URL_SERVICE))
        .route(
            "mock-2",
            r ->
                r.path("/mock")
                    .and()
                    .header("type", "2")
                    .filters(
                        f -> f.rewritePath("/mock", "/v3/c058b0e9-6860-456c-a4f5-344d4ba021df"))
                    .uri(MOCK_URL_SERVICE))
        .route(
            "mock-3",
            r ->
                r.path("/mock")
                    .and()
                    .header("type", "3")
                    .filters(
                        f -> f.rewritePath("/mock", "/v3/0bf24927-0283-43f9-84e3-32f927ef04e4"))
                    .uri(MOCK_URL_SERVICE))
        .build();
  }

  @GetMapping("/mock-get")
  public Mono<ResponseEntity<byte[]>> proxy(ProxyExchange<byte[]> proxy) throws Exception {
    return proxy
        .sensitive("Host")
        .uri("https://run.mocky.io/v3/e2aca955-604c-4ea3-bdad-034ab98a1f5c")
        .get();
  }
}
