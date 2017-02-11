package veribis.veribiscrmdyn;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cantekinandroidlib.FileHelper.FileConverter;
import com.cantekinandroidlib.logger.CustomLogger;

import java.io.File;

import Model.Form.baseProperties;
import me.sudar.zxingorient.ZxingOrient;
import me.sudar.zxingorient.ZxingOrientResult;
import veribis.veribiscrmdyn.Fragment.Form.FormFragment;
import veribis.veribiscrmdyn.Fragment.FragmentFactory;
import veribis.veribiscrmdyn.Fragment._baseFragment;
import veribis.veribiscrmdyn.Widgets.EnumEvetType;


public class MainActivity extends BaseActivity {
    public static final int REQUEST_CAMERA = 101;
    public static final int SELECT_FILE = 102;
    private static final String TAG = "MainActivity";
    public View barcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActivity();
    }


    @Override
    protected void initActivity() {
        super.initActivity();
        //// TODO: 10.2.2017 dashboard gelecek 
        fmTr = getSupportFragmentManager().beginTransaction();
        fmTr.add(R.id.content, new FormFragment().setProp(getFromProp.get()));
        fmTr.commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == RESULT_OK)
            switch (requestCode) {
                case ZxingOrient.REQUEST_CODE:
                    ZxingOrientResult scanResult =
                            ZxingOrient.parseActivityResult(requestCode, resultCode, intent);
                    if (scanResult == null)
                        return;
                    if (barcode instanceof TextView)
                        ((TextView) barcode).setText(scanResult.getContents());
                    else if (barcode instanceof EditText)
                        ((EditText) barcode).setText(scanResult.getContents());
                    break;
                case REQUEST_CAMERA:
                    Object fragment = getSupportFragmentManager().findFragmentById(R.id.content);
                    CustomLogger.alert(TAG, String.valueOf(intent.getExtras().get("Data")));
                    if (fragment instanceof FormFragment) {
                        Bitmap photo = (Bitmap) intent.getExtras().get("Data");
                        Uri tempUri = FileConverter.getImageUri(getApplicationContext(), photo);
                        String imagePath = FileConverter.getPath(this, tempUri);
                        ((FormFragment) fragment).uploadFile(new File(imagePath));
                    }
                    break;
                case SELECT_FILE:
                    Object frag = getSupportFragmentManager().findFragmentById(R.id.content);
                    CustomLogger.alert(TAG, String.valueOf(intent.getData()));
                    if (frag instanceof FormFragment) {
                        Uri selectedImage = intent.getData();
                        String selectePath = FileConverter.getPath(this, selectedImage);
                        CustomLogger.alert(TAG, selectePath);
                        ((FormFragment) frag).uploadFile(new File(selectePath));
                    }
                    break;
                default:
                    break;
            }
    }


}
