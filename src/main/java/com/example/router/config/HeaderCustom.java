package com.example.router.config;

import com.example.router.domain.Origin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HeaderCustom {

    @Value("${X_IBM_CLIENT_ID_1}")
    private String headerIbmClientId1;

    @Value("${X_IBM_CLIENT_SECRET_1}")
    private String headerIbmClientSecret1;

    @Value("${AUTHORIZATION_IBM_1}")
    private String headerAuthorizationIBMClient1;

    @Value("${X_IBM_CLIENT_ID_2}")
    private String headerIbmClientId2;

    @Value("${X_IBM_CLIENT_SECRET_2}")
    private String headerIbmClientSecret2;

    @Value("${AUTHORIZATION_IBM_2}")
    private String headerAuthorizationIBMClient2;

    @Value("${EMAIL_CURRENT}")
    private String fromEmailHeader;

    public String getAuthorization(Origin origin) {
        if (origin == null)
            return headerAuthorizationIBMClient1;
        else if (Origin.CLIENT_1 == origin)
            return headerAuthorizationIBMClient1;
        else if (Origin.CLIENT_2 == origin)
            return headerAuthorizationIBMClient2;
        else
            return headerAuthorizationIBMClient1;
    }

    public String getClientId(Origin origin) {
        if (origin == null)
            return headerIbmClientId1;
        else if (Origin.CLIENT_1 == origin)
            return headerIbmClientId1;
        else if (Origin.CLIENT_2 == origin)
            return headerIbmClientId2;
        else
            return headerIbmClientId1;
    }

    public String getSecretId(Origin origin) {
        if (origin == null)
            return headerIbmClientSecret1;
        else if (Origin.CLIENT_1 == origin)
            return headerIbmClientSecret1;
        else if (Origin.CLIENT_2 == origin)
            return headerIbmClientSecret2;
        else
            return headerIbmClientSecret1;
    }

    public String getEmail() {
        return fromEmailHeader;
    }

}
