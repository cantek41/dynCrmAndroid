package veribis.veribiscrmdyn;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cantekinandroidlib.customJson.jsonHelper;
import com.cantekinandroidlib.webApi.IThreadDelegete;
import com.cantekinandroidlib.webApi.OauthHeaders;
import com.cantekinandroidlib.webApi.ThreadWebApiPost;
import com.cantekinandroidlib.webApi.ThreadWebApiPostURLEncoded;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import Data.MyPreference;
import Data.User;
import Model.LoginRequest;
import Model.LoginResponse;

public class LoginActivity extends BaseActivity implements IThreadDelegete {
    private static final int REQUEST_LOGIN = 10002;
    private static final int REQUEST_USERDATA = 10003;
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
                        LoginRequest requestq = new LoginRequest();
                        new ThreadWebApiPost<>(REQUEST_USERDATA, this, requestq, webApiUserDataAddress).execute();
                        showProgress("Kullanıcı Bilgileri Çekiliyor");
                    } else {
                        showMessage(loginRespons.getError_description());
                    }
                    break;
                case REQUEST_USERDATA:
                    User user = jsonHelper.stringToObject(data, User.class);
                    user.setAccess_token(loginRespons.getAccess_token());
                    user.setToken_type(loginRespons.getToken_type());
                    user.setExpires_in(loginRespons.getExpires_in());
                    if (user.getUserId() != null) {
                        MyPreference.getPreference(this).setUserData(jsonHelper.objectToJson(user));
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    }
                    dismissProgress();
                    break;
            }

        }
    }


}

