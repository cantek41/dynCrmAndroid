package veribis.veribiscrmdyn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.cantekinandroidlib.customJson.jsonHelper;
import com.cantekinandroidlib.webApi.IThreadDelegete;
import com.cantekinandroidlib.webApi.OauthHeaders;
import com.cantekinandroidlib.webApi.ThreadWebApiPostURLEncoded;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import Data.MyPreference;
import Data.User;
import Model.LoginResponse;
import veribis.veribiscrmdyn.Demo.getDemoView;

/**
 * ilk açılışta beklerken
 * kullanıcıyı bilgilendirici ekran
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          createDemo();

      //  getDemoView.getMenu(this);
        setOauth();
        // TODO: 5.2.2017 menu ve dashboard ayarı yapılmalı arkada

    }

    private void setOauth() {
        User user = User.getUser(this);
        if (user != null)
            new UserLoginController(this, user.getUser_name(), user.getPassword()).login();
        else
            startActivity(new Intent(this, LoginActivity.class));
    }

    public void startApp() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    private void createDemo() {
        getDemoView.getMenu(this);
        getDemoView.getFirmalarList(this);
        getDemoView.getFirmaForm(this);
        getDemoView.getKisilerList(this);
        getDemoView.getKisiForm(this);
        getDemoView.getAktiviteList(this);
        getDemoView.getAktivite(this);
        getDemoView.getIsBasi(this);
        getDemoView.getIsSonu(this);
        getDemoView.getIdOkut(this);
        getDemoView.getSiperisList(this);
        getDemoView.getSiperis(this);
    }

    class UserLoginController implements IThreadDelegete {
        private static final int REQUEST_LOGIN = 10002;
        private String userName;
        private String password;
        private Context context;

        public UserLoginController(Context context, String userName, String password) {
            this.context = context;
            this.userName = userName;
            this.password = password;
        }

        public void login() {
            String webApiLoginAddress = MyPreference.getPreference(context).getLoginAddress();
            MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
            requestMap.add("grant_type", "password");
            requestMap.add("username", userName);
            requestMap.add("password", password);
            new ThreadWebApiPostURLEncoded<>(REQUEST_LOGIN, this, requestMap, webApiLoginAddress).execute();
        }

        @Override
        public void postResult(String data, int requestCode) {
            if (data != null) {
                switch (requestCode) {
                    case REQUEST_LOGIN:
                        LoginResponse loginRespons = jsonHelper.stringToObject(data, LoginResponse.class);
                        if (loginRespons.getError() == null) {
                            OauthHeaders.setToken(loginRespons.getAccess_token(), loginRespons.getToken_type());
                            startApp();
                        } else {
                            Toast.makeText(getApplicationContext(), loginRespons.getError_description(), Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        }
                        break;

                }

            }
        }

    }
}


