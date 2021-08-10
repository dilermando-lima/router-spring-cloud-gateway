package com.example.router.config;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.CACHED_REQUEST_BODY_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;

import java.net.URI;
import java.util.Collections;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);

    private boolean isRequestToLogger(ServerWebExchange exchange){
        return !exchange.getRequest().getPath().toString().contains("swagger-");
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();

        if( isRequestToLogger(exchange)  ){

                // LOGGER.debug("\tpath: " + request.getPath())
                // LOGGER.debug("\tmethod: " + request.getMethodValue())
                // LOGGER.debug("\tURI: " + request.getURI())
                // LOGGER.debug("\tHeaders: " + request.getHeaders())
                Object requestBody = exchange.getAttribute(CACHED_REQUEST_BODY_ATTR);
                
                
                Set<URI> uris = exchange.getAttributeOrDefault(GATEWAY_ORIGINAL_REQUEST_URL_ATTR, Collections.emptySet());
                String originalUri = (uris.isEmpty()) ? "Unknown" : uris.iterator().next().toString();
                URI routeUri = exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR);

                LOGGER.debug("request {} {} is routed to {} " , request.getMethodValue() , originalUri, routeUri);
                if( requestBody != null ) LOGGER.debug("\tbody: {} ", requestBody);

        }
     
        return chain.filter(exchange);
    }
    
}
