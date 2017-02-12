package com.cantekinandroidlib.webApi;

import android.preference.PreferenceActivity;

import com.cantekinandroidlib.customJson.jsonHelper;
import com.cantekinandroidlib.logger.CustomLogger;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.JsonObject;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;


/**
 * http post yapmak için kullanılır
 * Created by Cantekin on 28.7.2016.
 */

public class RestApi<T> {
    private String TAG = "RestApi";
    private static String HttpServerErrorExceptionTAG = "{\"Data\":null,\"Status\":{\"ErrCode\":1,\"Message\":\"Id is Null\"}}";
    private static String RestClientExceptionTAG = "{\"Data\":null,\"Status\":{\"ErrCode\":1,\"Message\":\"Id is Null\"}}";
    private static String ExceptionTAG = "{\"Data\":null,\"Status\":{\"ErrCode\":1,\"Message\":\"Id is Null\"}}";
    private final String webApiAddress;

    public void setTAG(String TAG) {
        this.TAG = TAG;
    }

    /**
     * Default "{\"Data\":null,\"Status\":{\"ErrCode\":1,\"Message\":\"Id is Null\"}}";
     *
     * @param httpServerErrorExceptionTAG
     */
    public void setHttpServerErrorExceptionTAG(String httpServerErrorExceptionTAG) {
        HttpServerErrorExceptionTAG = httpServerErrorExceptionTAG;
    }

    /**
     * Default "{\"Data\":null,\"Status\":{\"ErrCode\":1,\"Message\":\"Id is Null\"}}";
     *
     * @param restClientExceptionTAG
     */
    public void setRestClientExceptionTAG(String restClientExceptionTAG) {
        RestClientExceptionTAG = restClientExceptionTAG;
    }

    /**
     * Default "{\"Data\":null,\"Status\":{\"ErrCode\":1,\"Message\":\"Id is Null\"}}";
     *
     * @param exceptionTAG
     */
    public void setExceptionTAG(String exceptionTAG) {
        ExceptionTAG = exceptionTAG;
    }

    public RestApi(String webApiAddress) {
        this.webApiAddress = webApiAddress;
    }

    /**
     * api ye post yapmak için kullanılır,
     * request modeli gönderilir
     *
     * @param requests data
     * @return Json String
     */
    public String Post(T requests) {
        RestTemplate restTemplate = new RestTemplate();
        //MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        //jsonHttpMessageConverter.getObjectMapper().configure(SerializationFeature., false);
       // restTemplate.getMessageConverters().add(jsonHttpMessageConverter);

       // HttpHeaders headers=new HttpHeaders();
     //   headers.put("content-type", "application/json");
      //  headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
      //  headers.setContentType(MediaType.APPLICATION_JSON);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        String response = "";
        try {
            CustomLogger.info(TAG, "Request= " + jsonHelper.objectToJson(requests));
            response = restTemplate.postForObject(webApiAddress, requests, String.class,headers);
            CustomLogger.info(TAG, "Response= " + response);
        } catch (HttpServerErrorException ex) {
            response = HttpServerErrorExceptionTAG;
            CustomLogger.error(TAG, "wep api kaynaklı hata, Id gitmermiş olabilir " + ex.getMessage().toString());
            ex.printStackTrace();
        } catch (RestClientException ex) {
            CustomLogger.error(TAG, " SocketException " + ex.getMessage().toString());
            response = RestClientExceptionTAG;
        } catch (Exception ex) {
            CustomLogger.error(TAG, "Genel Hata " + ex.getMessage().toString());
            response = RestClientExceptionTAG;
        } finally {
            restTemplate = null;
        }
        return response;
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

