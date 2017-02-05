package com.cantekin.webApi;

import com.cantekin.logger.CustomLogger;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


/**
 * http post yapmak için kullanılır
 * Created by Cantekin on 28.7.2016.
 */

public class RestApi<T> {
    private  String TAG = "RestApi";
    private static String HttpServerErrorExceptionTAG = "{\"Data\":null,\"Status\":{\"ErrCode\":1,\"Message\":\"Id is Null\"}}";
    private static String RestClientExceptionTAG = "{\"Data\":null,\"Status\":{\"ErrCode\":1,\"Message\":\"Id is Null\"}}";
    private static String ExceptionTAG = "{\"Data\":null,\"Status\":{\"ErrCode\":1,\"Message\":\"Id is Null\"}}";
    String webApiAddress;

    public void setTAG(String TAG) {
        this.TAG = TAG;
    }

    /**
     * Default "{\"Data\":null,\"Status\":{\"ErrCode\":1,\"Message\":\"Id is Null\"}}";
     * @param httpServerErrorExceptionTAG
     */
    public void setHttpServerErrorExceptionTAG(String httpServerErrorExceptionTAG) {
        HttpServerErrorExceptionTAG = httpServerErrorExceptionTAG;
    }

    /**
     * Default "{\"Data\":null,\"Status\":{\"ErrCode\":1,\"Message\":\"Id is Null\"}}";
     * @param restClientExceptionTAG
     */
    public void setRestClientExceptionTAG(String restClientExceptionTAG) {
        RestClientExceptionTAG = restClientExceptionTAG;
    }

    /**
     * Default "{\"Data\":null,\"Status\":{\"ErrCode\":1,\"Message\":\"Id is Null\"}}";
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
     * @param requests data
     * @return Json String
     */
    public String Post(T requests) {
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonHttpMessageConverter.getObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        restTemplate.getMessageConverters().add(jsonHttpMessageConverter);
        String response = "";
        try {
            response = restTemplate.postForObject(webApiAddress, requests, String.class);
            CustomLogger.info(TAG, "Response= " + response);
        } catch (HttpServerErrorException ex) {
            response=HttpServerErrorExceptionTAG;
            CustomLogger.error(TAG, "wep api kaynaklı hata, Id gitmermiş olabilir " + ex.getMessage().toString());
        } catch (RestClientException ex) {
            CustomLogger.error(TAG, " SocketException " + ex.getMessage().toString());
            response=RestClientExceptionTAG;
        } catch (Exception ex) {
            CustomLogger.error(TAG, "Genel Hata " + ex.getMessage().toString());
            response=RestClientExceptionTAG;
        } finally {
            restTemplate = null;
        }
        return response;
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

