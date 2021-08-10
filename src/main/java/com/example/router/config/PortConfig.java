package com.example.router.config;

import com.example.router.domain.Port;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PortConfig {
    
    @Value("${PORT_APP_1}")
    private Integer portApp1;

    @Value("${PORT_APP_2}")
    private Integer portApp2;

    @Value("${PORT_APP_3}")
    private Integer portApp3;

    @Value("${PORT_APP_4}")
    private Integer portApp4;

    @Value("${PORT_APP_5}")
    private Integer portApp5;
    
    public  Integer get(Port port){

        if( Port.APP_1  ==  port ) return portApp1;
        if( Port.APP_2  ==  port ) return portApp2;
        if( Port.APP_3  ==  port ) return portApp3;
        if( Port.APP_4  ==  port ) return portApp4;
        if( Port.APP_5  ==  port ) return portApp5;
        
        return portApp1;

    }

}
