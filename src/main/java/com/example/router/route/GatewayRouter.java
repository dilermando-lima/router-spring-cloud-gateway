package com.example.router.route;


import com.example.router.config.HeaderCustom;
import com.example.router.config.PortConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GatewayRouter {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder, @Autowired HeaderCustom headerCustom , @Autowired PortConfig portConfig ) {

        return builder.routes()
                .route(r -> Routers.ROUTE_1.getRoute(r, headerCustom, portConfig)  )
                .route(r -> Routers.ROUTE_2.getRoute(r, headerCustom, portConfig)  )
                .route(r -> Routers.ROUTE_3.getRoute(r, headerCustom, portConfig)  )
                .route(r -> Routers.ROUTE_4.getRoute(r, headerCustom, portConfig)  )
                .route(r -> Routers.ROUTE_5.getRoute(r, headerCustom, portConfig)  )
                .route(r -> Routers.ROUTE_6.getRoute(r, headerCustom, portConfig)  )
                .route(r -> Routers.ROUTE_7.getRoute(r, headerCustom, portConfig)  )
                .build();
              
    }
    

}
