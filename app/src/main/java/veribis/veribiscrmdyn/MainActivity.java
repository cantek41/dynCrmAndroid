package veribis.veribiscrmdyn;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cantekinandroidlib.FileHelper.FileConverter;
import com.cantekinandroidlib.logger.CustomLogger;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.File;

import Data.User;
import me.sudar.zxingorient.ZxingOrient;
import me.sudar.zxingorient.ZxingOrientResult;
import veribis.veribiscrmdyn.Dialog.ListDialog;
import veribis.veribiscrmdyn.Fragment.Form.FormFragment;
import veribis.veribiscrmdyn.Fragment.HomeFragment;
import veribis.veribiscrmdyn.Widgets.SelectableWidget.SelectableContainer;


public class MainActivity extends BaseActivity {
    public static final int REQUEST_CAMERA = 101;
    public static final int SELECT_FILE = 102;
    private static final String TAG = "MainActivity";
    public View barcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = User.getUser(this);
        if (user != null)
            initActivity();
    }


    @Override
    protected void initActivity() {
        super.initActivity();
        //// TODO: 10.2.2017 dashboard gelecek
        fmTr = getSupportFragmentManager().beginTransaction();
        if (fmTr.isEmpty())
            fmTr.add(R.id.content, new HomeFragment().setProp(null));
        else
            fmTr.add(R.id.content, new HomeFragment().setProp(null));
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
                    if (fragment instanceof FormFragment) {

                        Bitmap photo = (Bitmap) intent.getExtras().get("data");
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


    public void onListDialog(SelectableContainer container) {
        ListDialog bul = new ListDialog(this);
        bul.setTitle(container.getDialogTitle());
        bul.setData(container);
        bul.show();
    }
}
