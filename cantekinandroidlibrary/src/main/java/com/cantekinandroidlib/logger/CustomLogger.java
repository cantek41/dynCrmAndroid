package com.cantekinandroidlib.logger;


import android.util.Log;

/**
 * TODO: server mekanizmasını ekle
 * hataları servara göndermek gerekirse
 * diye böyle bir sınıf oluşturdum
 * Created by Cantekin on 8.1.2017.
 */
public final class CustomLogger {
    public static void setLogType(EnumLogType logType) {
        CustomLogger.logType = logType;
    }

    private static EnumLogType logType;


    private static EnumLogType getLogType() {
        if (logType == null)
            logType = EnumLogType.LOGCAT;
        return logType;
    }

    /**
     * Loglama yapar logType a göre
     * Web,LogCat veya Systeme
     *
     * @param TAG
     * @param message
     */
    public static void error(String TAG, String message) {
        switch (getLogType()) {
            case LOGCAT:
                Log.e(TAG, message);
                break;
            case WEBAPI:
                //TODO: webapi logger yapılacak
                break;
            case SYSTEM:
                System.out.format("TAG=%s %n Message=%s %n", TAG, message);
                break;
        }
    }

    public static void alert(String TAG, String message) {
        if (message != null)
            switch (getLogType()) {
                case LOGCAT:
                    Log.w(TAG, message);
                    break;
                case WEBAPI:
                    //TODO: webapi logger yapılacak
                    break;
                case SYSTEM:
                    System.out.format("TAG=%s %n Message=%s %n", TAG, message);
                    break;
            }
    }

    public static void info(String TAG, String message) {
        switch (getLogType()) {
            case LOGCAT:
                Log.i(TAG, message);
                break;
            case WEBAPI:
                //TODO: webapi logger yapılacak
                break;
            case SYSTEM:
                System.out.format("TAG=%s %n Message=%s %n", TAG, message);
                break;
        }
    }

}
