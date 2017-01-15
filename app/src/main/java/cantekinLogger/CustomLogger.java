package cantekinLogger;


import android.util.Log;

/**
 * TODO: server mekanizmasını ekle
 * hataları servara göndermek gerekirse
 * diye böyle bir sınıf oluşturdum
 * Created by Cantekin on 8.1.2017.
 */
public final class CustomLogger {
  public static EnumLogType logType = EnumLogType.LOGCAT;

  public static void error(String TAG, String message) {
    switch (logType) {
      case LOGCAT:
        Log.e(TAG, message);
        break;
      case WEBAPI:
        //TODO: webapi logger yapılacak
        break;
      case SYSTEM:
        System.out.format("TAG=%s %n Message=%s %n",TAG,message);
        break;
    }
  }

  public static void alert(String TAG, String message) {
    Log.w(TAG, message);
  }

  public static void info(String TAG, String message) {
    Log.i(TAG, message);
  }

}
