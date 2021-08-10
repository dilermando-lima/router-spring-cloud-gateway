package com.example.router.route;

import com.example.router.config.HeaderCustom;
import com.example.router.config.PortConfig;
import com.example.router.constant.Const;
import com.example.router.domain.Origin;
import com.example.router.domain.Port;
import com.example.router.domain.TypeRouter;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

public enum Routers {


    ROUTE_1(TypeRouter.GATEWAY_TO_MICROSERVICE, "/api/profiles", "/ms-profiles/api/v1/profiles", Port.APP_1, Origin.CLIENT_1 ),
    ROUTE_2(TypeRouter.GATEWAY_TO_MICROSERVICE, "/api/users", "/ms-users/api/v1/users", Port.APP_2, Origin.CLIENT_1 ),
    ROUTE_3(TypeRouter.GATEWAY_TO_MICROSERVICE, "/api/service1", "/ms-service/leads", Port.APP_3, Origin.CLIENT_2 ),
    ROUTE_4(TypeRouter.GATEWAY_TO_MICROSERVICE, "/api/service1", "/ms-profile/sales", Port.APP_3, Origin.CLIENT_2 ),
    ROUTE_5(TypeRouter.ROUTE_TO_MICROSERVICE, "/api/report", "/ms-report/api/v1/report", Port.APP_4, Origin.CLIENT_1 ),
    ROUTE_6(TypeRouter.ROUTE_TO_MICROSERVICE, "/api/dashboard", "/ms-report/api/v1/dashboard", Port.APP_4, Origin.CLIENT_1 ),
    ROUTE_7(TypeRouter.GATEWAY_TO_MICROSERVICE, "/api/email", "/ms-email/api/v1/email", Port.APP_5, Origin.CLIENT_2 ),
    ;

    @SuppressWarnings("unused")
    private final TypeRouter type; // only for docs
    private final String from;
    private final String to;
    private final Port port;
    private final Origin origin;

	private Routers(TypeRouter type, String from, String to, Port port, Origin origin) {
		this.type = type;
		this.from = from;
		this.to = to;
        this.port = port;
        this.origin = origin;
	}

    public Buildable<Route> getRoute(PredicateSpec predicateSpec, HeaderCustom headerCustom , PortConfig portConfig  ){
        return predicateSpec.path( this.from + "**")
                .filters(f -> f.rewritePath( this.to + "(?<RID>.*)", this.to  + "${RID}")
                                .setRequestHeader(HttpHeaders.FROM, headerCustom.getEmail() )
                                .setRequestHeader(HttpHeaders.AUTHORIZATION, headerCustom.getAuthorization(this.origin))
                                .setRequestHeader(Const.API_CONNECT_HEADER_ID, headerCustom.getClientId(this.origin))
                                .setRequestHeader(Const.API_CONNECT_HEADER_SECRET, headerCustom.getSecretId(this.origin))
                                .dedupeResponseHeader(Const.ACCESS_CONTROL_ALLOW_ORIGIN, Const.RETAIN_UNIQUE))
                .uri(Const.DEFAULT_URI + ":" + portConfig.get(this.port));
    }

    public Buildable<Route> getRouteOnlyPost(PredicateSpec predicateSpec, HeaderCustom headerCustom , PortConfig portConfig  ){
        return predicateSpec.order(-100).method(HttpMethod.POST).and().path( this.from  )
                .filters(f -> f.rewritePath( this.to + "(?<RID>.*)", this.to  + "${RID}")
                                .setRequestHeader(HttpHeaders.FROM, headerCustom.getEmail() )
                                .setRequestHeader(HttpHeaders.AUTHORIZATION, headerCustom.getAuthorization(this.origin))
                                .setRequestHeader(Const.API_CONNECT_HEADER_ID, headerCustom.getClientId(this.origin))
                                .setRequestHeader(Const.API_CONNECT_HEADER_SECRET, headerCustom.getSecretId(this.origin))
                                .dedupeResponseHeader(Const.ACCESS_CONTROL_ALLOW_ORIGIN, Const.RETAIN_UNIQUE))
                .uri(Const.DEFAULT_URI + ":" + portConfig.get(this.port));
    }








    
}

