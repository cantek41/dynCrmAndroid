package veribis.veribiscrmdyn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * ilk açılışta beklerken
 * kullanıcıyı bilgilendirici ekran
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        // TODO: 5.2.2017 menu ve dashboard ayarı yapılmalı arkada
    }

    /**
     * https://www.bignerdranch.com/blog/splash-screens-the-right-way/
     * in --> res/drawable
     <?xml version="1.0" encoding="utf-8"?>
     <layer-list xmlns:android="http://schemas.android.com/apk/res/android">
     <item
     android:drawable="@color/gray"/>
     <item>
     <bitmap
     android:gravity="center"
     android:src="@mipmap/ic_launcher"/>
     </item>
     </layer-list>
     ****************************
     styles.xml
     <resources>

     <!-- Base application theme. -->
     <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
     <!-- Customize your theme here. -->
     </style>

     <style name="SplashTheme" parent="Theme.AppCompat.NoActionBar">
     <item name="android:windowBackground">@drawable/background_splash</item>
     </style>

     </resources>
     */
}
