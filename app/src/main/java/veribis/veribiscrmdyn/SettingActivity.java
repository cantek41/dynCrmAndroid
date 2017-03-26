package veribis.veribiscrmdyn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
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

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

import Data.MyPreference;
import Data.User;
import Data.UserDataToPreference;
import Model.LoginResponse;

public class SettingActivity extends BaseActivity implements IThreadDelegete {
    private static final int REQUEST_USER_TEMPLATE = 10001;
    EditText mainUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initActivity();
    }

    @Override
    protected void initActivity() {
        Button urlBtn = (Button) findViewById(R.id.btnMainUrl);
        mainUrl = (EditText) findViewById(R.id.editMainUrl);
        mainUrl.setText(MyPreference.getPreference(this).getMainURL());
        urlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urlOnClick();
            }
        });

        Button btnRefreshData = (Button) findViewById(R.id.btnRefreshData);
        btnRefreshData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshData();
            }
        });
    }

    private void urlOnClick() {
        if (mainUrl.getText().length() > 0) {
            MyPreference.getPreference(this).setMainURL(mainUrl.getText().toString());
        } else
            Toast.makeText(this, "Url Boş geçilemez", Toast.LENGTH_SHORT).show();
    }

    private void refreshData() {
        String webApiUserDataAddress = MyPreference.getPreference(this).getUserFormDataWebApiAddress();
        Map<String, String> req = new HashMap();
        user=MyPreference.getPreference(this).getUserData();
        req.put("userName", user.getName());
        new ThreadWebApiPost<>(REQUEST_USER_TEMPLATE, this, req, webApiUserDataAddress).execute();
        showProgress("Menu ve Form Bilgileri Çekiliyor");
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void postResult(String data, int requestCode) {
        if (data != null) {
            switch (requestCode) {
                case REQUEST_USER_TEMPLATE:
                    new UserDataToPreference(this).Run(data);
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                    break;
            }
        }
        dismissProgress();
    }
}

