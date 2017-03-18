package com.cantekinandroidlib.webApi;

import com.cantekinandroidlib.logger.CustomLogger;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.net.HttpURLConnection;

/**
 * Created by Cantekin on 25.2.2017.
 */

public final class OauthHeaders {
    private static String token;
    private static String token_type;

    public static void setToken(String _token, String _token_type) {
        token = _token;
        token_type = _token_type;
    }

    public static HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        if (token != null)
            headers.set("Authorization", token_type + " " + token);
        return headers;
    }

    public static HttpURLConnection getHeaders(HttpURLConnection con) {
        con.setRequestProperty("Accept", "*/*");
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        if (token != null)
            con.setRequestProperty("Authorization", token_type + " " + token);
        CustomLogger.info("Authorization", token_type + " " + token);
        return con;
    }
}
