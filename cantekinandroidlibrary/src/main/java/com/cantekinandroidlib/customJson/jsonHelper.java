package com.cantekinandroidlib.customJson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * json to object
 * object to Json
 * Created by Cantekin on 27.1.2017.
 */

public class jsonHelper {

    public static String objectToJson(Object object) throws NullPointerException {
        if (object == null)
            throw new NullPointerException("object is not null");
        return new Gson().toJson(object);
    }

    //    public static <T> T stringToObject(String json, Class<T> responseType) throws NullPointerException {
//        if (json == null)
//            throw new NullPointerException("json is not null");
//
//        return new Gson().fromJson(json, responseType);
//     //   return new Gson().fromJson(json, new TypeToken<T>(){}.getType());
//    }
    public static <T> T stringToObject(String json, Class<T> responseType) throws NullPointerException {
        if (json == null)
            throw new NullPointerException("json is not null");
        return new Gson().fromJson(json, responseType);
    }
}
