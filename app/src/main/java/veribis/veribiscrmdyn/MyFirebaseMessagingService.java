package veribis.veribiscrmdyn;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v4.app.NotificationCompat;


import com.cantekinandroidlib.customJson.jsonHelper;
import com.cantekinandroidlib.logger.CustomLogger;
import com.cantekinandroidlib.webApi.IThreadDelegete;
import com.cantekinandroidlib.webApi.OauthHeaders;
import com.cantekinandroidlib.webApi.ThreadWebApiPost;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import Data.MyPreference;
import Data.User;
import Data.UserDataToPreference;
import Model.LoginResponse;

/**
 * Created by Cantekin on 11.3.2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService implements IThreadDelegete {

    private static final String TAG = "MyFirebaseMsgService";
    private static final int REQUEST_GETMENU = 10009;
    private static final int REQUEST_GETFORM = 10010;


    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        CustomLogger.info(TAG, "From: " + remoteMessage.getFrom());
        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            CustomLogger.info(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        if (remoteMessage.getData().size() > 0) {
            CustomLogger.info(TAG, "Message data payload: " + remoteMessage.getData());
            String actionType = remoteMessage.getData().get("actionType");
            String formName = remoteMessage.getData().get("formName");
            String formType = remoteMessage.getData().get("formType");
            switch (actionType) {
                case "update":
                    getDataServer(formName, formType);
                    break;
                case "delete":
                    MyPreference.getPreference(getApplicationContext()).deleteValue(formName);
                    break;
            }

        }
    }
    // [END receive_message]

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icon)
                .setContentTitle("FCM Message")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    private void getDataServer(String formName, String formType) {
        String webApiAddress = null;
        Map<String, String> req = new HashMap();
        switch (formType) {
            case "menu":
                webApiAddress = MyPreference.getPreference(this).getMenuApiAddres();
                req.put("menuName", formName);
                new ThreadWebApiPost<>(REQUEST_GETMENU, this, req, webApiAddress).execute();

                break;
            case "form":
                webApiAddress = MyPreference.getPreference(this).getFormApiAddres();
                req.put("formName", formName);
                new ThreadWebApiPost<>(REQUEST_GETFORM, this, req, webApiAddress).execute();

                break;
        }


    }

    @Override
    public void postResult(String data, int requestCode) {
        if (data != null) {
            switch (requestCode) {
                case REQUEST_GETFORM:
                    try {
                        JSONObject form = new JSONObject(data);
                        MyPreference.getPreference(getApplicationContext()).setData(form.getString("formName"), data);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case REQUEST_GETMENU:

                    try {
                        JSONObject menu = new JSONObject(data).getJSONObject("Widget");
                        MyPreference.getPreference(getApplicationContext()).setData("menu", menu.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }

        }
    }
}