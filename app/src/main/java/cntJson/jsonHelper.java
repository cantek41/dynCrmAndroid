package cntJson;

import com.google.gson.Gson;

/**
 * json to object
 * object to Json
 * Created by Cantekin on 27.1.2017.
 */
public class jsonHelper {

  public static String objectToJson(Object object)
  {
    // TODO: 27.1.2017 içi yazılacak ve test
    return null;
  }
  public static <T> T stringToObject(String json,Class<T> responseType)
  { // TODO: 27.1.2017 test metod yazılacak
    return  (T) new Gson().fromJson(json,responseType);
  }
}
