package com.rtd.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

	// @RestController
	// public class Royal{

	// 	@GetMapping("/")
	// 	public String hello(){
	// 		return "hello";
	// 	}
	// }

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	 @Bean
    public RouteLocator routerBuilder(RouteLocatorBuilder routeLocatorBuilder){ 
        return routeLocatorBuilder.routes() 
                        .route("USERSERVICE",r->r.path("/users/**") 
                                .uri("lb://USERSERVICE")) 
                        .route("HOTELSERVICE",r->r.path("/hotels/**") 
                                .uri("lb://HOTELSERVICE"))
						.route("RATINGSERVICE",r->r.path("/ratings/**")
						.uri("lb://RATINGSERVICE")).build(); 
    } 
}
