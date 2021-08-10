package com.example.router.constant;

public class Const {

    private Const() {
        throw new IllegalStateException("Const class");
    }

    public static final String DEFAULT_URI = "http://localhost";
    public static final String API_CONNECT_HEADER_ID = "X-IBM-Client-Id";
    public static final String API_CONNECT_HEADER_SECRET = "X-IBM-Client-Secret";
    public static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    public static final String RETAIN_UNIQUE = "RETAIN_UNIQUE";

}
