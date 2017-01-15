package Firebase;

import android.net.Uri;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class MyFirebaseMessagingService extends FirebaseMessagingService  {

    private static final String TAG = "MyFirebaseMsgService";
    //Status 101 Check 102 Answer 103 Request

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getData());
        String message = remoteMessage.getData().get("notification_type");
        String data = remoteMessage.getData().get("data");
        sendNotification(data, message);

    }

    private void sendNotification(String data, String notification_message) {
        Log.i(TAG, "notification_message " + notification_message);

       /*ekrana notification gönder*/
     /*  String message = null;
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        switch (notification_message) {
            case "new_offer_confirmation":
                message = getString(R.string.notification_answer);
                // defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                setNotification(data, defaultSoundUri, message, Activity_Offer_Answer.class);
                break;
            case "new_payment_confirmation":
                message = getString(R.string.notification_yuk_detay);
                // defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                setNotification(data, defaultSoundUri, message, Activity_OfferDetail.class);
                break;
            case "new_shipment":
                getShipments();
                break;
            case "new_shipment_confirmation":
                message = getString(R.string.notification_yuk_teklif);
                //  defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                setNotification(data, defaultSoundUri, message, Activity_Offer.class);
                break;
            case "shipment_removed":
                getShipments();
                break;
            case "user_rating":
                  SettingsApp.setUserRating(getApplicationContext(),data);
                break;
            default:
                Log.i(TAG, notification_message);
                break;
        } */


    }


    private void setNotification(String shipment_id, Uri defaultSoundUri, String message, Class responseType) {
  /*      Intent intent = new Intent(this, responseType);// OfferActivity.class);
        intent.putExtra("shipment", shipment_id);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent,
                PendingIntent.FLAG_ONE_SHOT);
        Log.i(TAG, "intent" + intent.getStringExtra("shipment"));
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.icon)
                .setContentTitle("Tirport")
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 , notificationBuilder.build());      */
    }


}
