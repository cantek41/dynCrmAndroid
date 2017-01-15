package cantekinWebApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import cantekinLogger.CustomLogger;


/**
 * http post yapmak için kullanılır
 * Created by Cantekin on 28.7.2016.
 */

public class RestApi<T> {
  private final String TAG = "RestApi";
  String webApiAddress;


  public RestApi(String webApiAddress) {
    this.webApiAddress = webApiAddress;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public String Post(T requests) {
    RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
    MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
    jsonHttpMessageConverter.getObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    restTemplate.getMessageConverters().add(jsonHttpMessageConverter);
    String response = "";
    try {
      response = restTemplate.postForObject(webApiAddress, requests, String.class);
      CustomLogger.info(TAG + "Response=", response);

    } catch (RestClientException ex) {
      CustomLogger.error(TAG + " SocketException", ex.getMessage().toString());

    } catch (Exception ex) {
      CustomLogger.error(TAG + "Genel Hata", ex.getMessage().toString());

    } finally {
      restTemplate = null;
      CustomLogger.info(TAG + " restTemplate", "null");
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

