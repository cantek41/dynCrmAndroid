package veribis.veribiscrmdyn;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cantekinandroidlib.logger.CustomLogger;
import com.cantekinandroidlib.webApi.ThreadWebApiPostURLEncoded;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import Data.MyPreference;

public class SettingActivity extends BaseActivity {
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
    }

    private void urlOnClick() {
        if (mainUrl.getText().length() > 0) {
            MyPreference.getPreference(this).setMainURL(mainUrl.getText().toString());
        } else
            Toast.makeText(this, "Url Boş geçilemez", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

