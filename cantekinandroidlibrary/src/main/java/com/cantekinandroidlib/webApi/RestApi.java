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
    private static String HttpServerErrorExceptionTAG = "{\"Data\":null,\"Status\":{\"ErrCode\":1,\"Message\":\"Id is Null\"}}";
    private static String RestClientExceptionTAG = "{\"Data\":null,\"Status\":{\"ErrCode\":1,\"Message\":\"Id is Null\"}}";
    private static String ExceptionTAG = "{\"Data\":null,\"Status\":{\"ErrCode\":1,\"Message\":\"Id is Null\"}}";
    private final String webApiAddress;
    private HttpHeaders headers;

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

    public RestApi(String webApiAddress, HttpHeaders headers) {
        this.webApiAddress = webApiAddress;
        this.headers = headers;
    }

    /**
     * api ye post yapmak için kullanılır,
     * request modeli gönderilir
     *
     * @param requests data
     * @return Json String
     */
    public String Post66(T requests) {
        RestTemplate restTemplate = new RestTemplate();
//        MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
//        jsonHttpMessageConverter.getObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//        restTemplate.getMessageConverters().add(jsonHttpMessageConverter);
//        HttpHeaders headers = OauthHeaders.getHeaders();
//        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
//            List<String> values = Collections.unmodifiableList(entry.getValue());
//            CustomLogger.alert(entry.getKey(), values.get(0));
//        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "bearer MP985WW1CoHPW_v2bHxYnNHDy3wQsm_wpKEWQNpMArdzC41cmFlhmr7bbJ2CMgAyPvDyhCH5PJhJLLkF39SA2pNc-D0WQfs_Jg7z6skT9Tytf4d4y46jUnjIwqodhwlccXbb1ToRAMqheeZ7Kr1ZSxrVzeOyjpWuPty4Eyqe5z--7Uakj1db3m5L1t6X3Ze88ZE1SBBNE2UUJR-3aHm_3oca9rgOBf0WiJ4ophymRnnl6aV6nXcTXPRSCZ9rMDkP");
        String response = "";
        try {
//            CustomLogger.info(TAG, "Request= " + jsonHelper.objectToJson(requests));
            response = restTemplate.postForObject(webApiAddress, requests, String.class, headers);
            CustomLogger.info(TAG, "Response= " + response);
        } catch (HttpServerErrorException ex) {
            response = HttpServerErrorExceptionTAG;
            CustomLogger.error(TAG, "wep api kaynaklı hata, Id gitmermiş olabilir " + ex.getMessage().toString());
            ex.printStackTrace();
        } catch (RestClientException ex) {
            CustomLogger.error(TAG, " SocketException " + ex.getMessage().toString());
            response = RestClientExceptionTAG;
            ex.printStackTrace();
        } catch (Exception ex) {
            CustomLogger.error(TAG, "Genel Hata " + ex.getMessage().toString());
            response = RestClientExceptionTAG;
            ex.printStackTrace();
        } finally {
            restTemplate = null;
        }
        return response;
    }

    public String Post(T requests) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<T> entity = new HttpEntity<>(requests, OauthHeaders.getHeaders());
        ResponseEntity result = null;
        String response = "";
        try {
            result = restTemplate.exchange(webApiAddress, HttpMethod.POST, entity, String.class);
            System.out.println(result.getBody().toString());
            response = result.getBody().toString();
        } catch (HttpServerErrorException ex) {
            response = HttpServerErrorExceptionTAG;
            CustomLogger.error(TAG, "wep api kaynaklı hata, Id gitmermiş olabilir " + ex.getMessage().toString());
            ex.printStackTrace();
        } catch (RestClientException ex) {
            CustomLogger.error(TAG, " SocketException " + ex.getMessage().toString());
            response = RestClientExceptionTAG;
            ex.printStackTrace();
        } catch (Exception ex) {
            CustomLogger.error(TAG, "Genel Hata " + ex.getMessage().toString());
            response = RestClientExceptionTAG;
            ex.printStackTrace();
        } finally {
            restTemplate = null;
        }
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

