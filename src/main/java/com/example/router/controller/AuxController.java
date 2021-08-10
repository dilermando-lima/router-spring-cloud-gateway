package com.example.router.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuxController {

    @PostMapping({ 
            "/auth/auth-example-1/oauth2/token",
            "/auth/auth-example-2/oauth2/token",
            "/auth/auth-example-3/oauth2/token" })
    @ResponseStatus(HttpStatus.OK)
    public void authTokenLogin() {
        // will fake request to
        // https://.../auth/oauth-20-provider/oauth2/token
        // for get token auth
    }

}
