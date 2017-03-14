package veribis.veribiscrmdyn;

import android.app.Service;
import android.util.Log;

import com.cantekinandroidlib.logger.CustomLogger;
import com.cantekinandroidlib.webApi.IThreadDelegete;
import com.cantekinandroidlib.webApi.ThreadWebApiPost;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.util.HashMap;
import java.util.Map;

import Data.MyPreference;
import Data.User;

/**
 * Created by Cantekin on 11.3.2017.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService implements IThreadDelegete {

    private static final String TAG = "MyFirebaseIIDService";
    private static final int REQUEST_SET_DEVICE = 10014;

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }
    // [END refresh_token]

    /**
     * Persist token to third-party servers.
     * <p>
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        if (User.getUser(getApplicationContext()) == null)
            return;
        CustomLogger.alert(TAG, "sendRegistrationToServer");
        String webApiAddress = null;
        Map<String, String> req = new HashMap();
        webApiAddress = MyPreference.getPreference(this).getSetDeviceApiAddres();
        req.put("userName", User.getUser(getApplicationContext()).getName());
        req.put("deviceID", token);
        new ThreadWebApiPost<>(REQUEST_SET_DEVICE, this, req, webApiAddress).execute();
    }

    @Override
    public void postResult(String data, int requestCode) {

    }
}