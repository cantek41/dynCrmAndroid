package com.cantekinandroidlib.webApi;

import com.cantekinandroidlib.customJson.jsonHelper;
import com.cantekinandroidlib.logger.CustomLogger;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * http post yapmak için kullanılır
 * Created by Cantekin on 28.7.2016.
 */

public class RestApi<T> {
    private String TAG = "RestApi";
    private final String webApiAddress;

    public void setTAG(String TAG) {
        this.TAG = TAG;
    }

    public RestApi(String webApiAddress) {
        this.webApiAddress = webApiAddress;
    }

    public String Post(T requests) {
        String requestJson = jsonHelper.objectToJson(requests);
        String response = "";
        try {
            URL url=new URL(webApiAddress);
            HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection=OauthHeaders.getHeaders(urlConnection);
            OutputStream os = urlConnection.getOutputStream();
            os.write(requestJson.getBytes("UTF-8"));
            os.close();
            urlConnection.connect();
            if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + urlConnection.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (urlConnection.getInputStream())));

            String row;
            String output="";
            while ((row = br.readLine()) != null) {
                output+=row;
            }
            urlConnection.disconnect();
            response=output;
        } catch (Exception e) {
            CustomLogger.error(TAG,e.getMessage());
        }
        CustomLogger.info(TAG,response);
        return response;
    }

    public String PostToken(MultiValueMap<String, String> map) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        ResponseEntity result =
                restTemplate.exchange(webApiAddress, HttpMethod.POST, entity, String.class);
        System.out.println(result.getBody().toString());
        return result.getBody().toString();
    }

    public String PostFile(byte[] byteArray, final String filename) {
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        final MultiValueMap<String, Object> data = new LinkedMultiValueMap<>();
        data.add("files", new FileMessageResource(byteArray, filename));
        final HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(data);
        final ResponseEntity<Map<String, String>> response = restTemplate.exchange(webApiAddress, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<Map<String, String>>() {
        });
        return String.valueOf(response.getStatusCode());
    }

    /**
     * Socket Timeout ayarı
     */
    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        int timeout = 5000;
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory =
                new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(timeout);
        return clientHttpRequestFactory;
    }

}

