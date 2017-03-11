package veribis.veribiscrmdyn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cantekinandroidlib.customJson.jsonHelper;
import com.cantekinandroidlib.logger.CustomLogger;
import com.cantekinandroidlib.webApi.IThreadDelegete;
import com.cantekinandroidlib.webApi.OauthHeaders;
import com.cantekinandroidlib.webApi.ThreadWebApiPost;
import com.cantekinandroidlib.webApi.ThreadWebApiPostURLEncoded;
import com.google.firebase.iid.FirebaseInstanceId;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

import Data.MyPreference;
import Data.User;
import Data.UserDataToPreference;
import Model.LoginResponse;

public class LoginActivity extends BaseActivity implements IThreadDelegete {
    private static final int REQUEST_LOGIN = 10002;
    private static final int REQUEST_USERDATA = 10003;
    private static final int REQUEST_USER_TEMPLATE = 10004;
    private EditText userName;
    private EditText password;
    LoginResponse loginRespons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initActivity();
    }

    protected void initActivity() {
        Button loginBtn = (Button) findViewById(R.id.btnLogin);
        userName = (EditText) findViewById(R.id.edtUserName);
        password = (EditText) findViewById(R.id.pswPassword);
        // TODO: 10.3.2017 sil
        userName.setText("erkan");
        password.setText("deneme");
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginOnClick();
            }
        });
    }

    private void loginOnClick() {
        if (userName.getText().length() > 0 && password.getText().length() > 0) {
            String webApiLoginAddress = MyPreference.getPreference(this).getLoginWepApiAddress();
            MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
            requestMap.add("grant_type", "password");
            requestMap.add("username", userName.getText().toString());
            requestMap.add("password", password.getText().toString());
            new ThreadWebApiPostURLEncoded<>(REQUEST_LOGIN, this, requestMap, webApiLoginAddress).execute();
            showProgress("İşleniyor");
        } else
            Toast.makeText(this, "UserName or password  are not null!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void postResult(String data, int requestCode) {
        if (data != null) {
            switch (requestCode) {
                case REQUEST_LOGIN:
                    loginRespons = jsonHelper.stringToObject(data, LoginResponse.class);
                    if (loginRespons.getError() == null) {
                        OauthHeaders.setToken(loginRespons.getAccess_token(), loginRespons.getToken_type());
                        String webApiUserDataAddress = MyPreference.getPreference(this).getUserDataWebApiAddress();
                        new ThreadWebApiPost<>(REQUEST_USERDATA, this, "", webApiUserDataAddress).execute();
                        showProgress("Kullanıcı Bilgileri Çekiliyor");

                    } else {
                        showMessage(loginRespons.getError_description());
                    }
                    break;
                case REQUEST_USERDATA:
                    User user = jsonHelper.stringToObject(data, User.class);
                    user.setUser_name(userName.getText().toString());
                    user.setPassword(password.getText().toString());
                    if (user.getUserId() != null) {
                        MyPreference.getPreference(this).setUserData(jsonHelper.objectToJson(user));
                        String webApiUserDataAddress = MyPreference.getPreference(this).getUserFormDataWebApiAddress();
                        Map<String, String> req = new HashMap();
                        req.put("userName", user.getName());
                        new ThreadWebApiPost<>(REQUEST_USER_TEMPLATE, this, req, webApiUserDataAddress).execute();
                        showProgress("Menu ve Form Bilgileri Çekiliyor");
                        getDeviceToken();
                    } else
                        dismissProgress();
                    break;
                case REQUEST_USER_TEMPLATE:
                    if (data != null) {
                        new UserDataToPreference(this).Run(data);
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    }
                    dismissProgress();
                    break;
            }

        }
    }


    private void getDeviceToken() {
        CustomLogger.alert("TOKEN", "InstanceID token: " + FirebaseInstanceId.getInstance().getToken());
    }
}

