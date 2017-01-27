package veribis.veribiscrmdyn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import me.sudar.zxingorient.ZxingOrient;
import me.sudar.zxingorient.ZxingOrientResult;
import veribis.veribiscrmdyn.Fragment.Form.FormFragment;


public class MainActivity extends BaseActivity {
  public static final int REQUEST_CAMERA = 101;
  public static final int SELECT_FILE = 102;
  private static final String TAG ="MainActivity" ;
  public View barcode;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initActivity();
  }

  public void goDetail() {
    fmTr = getSupportFragmentManager().beginTransaction();
    fmTr.add(R.id.content, new FormFragment().setProp(getFromProp.get()));
    fmTr.addToBackStack(null);
    fmTr.commit();
  }

  @Override
  protected void initActivity() {
    super.initActivity();
    fmTr = getSupportFragmentManager().beginTransaction();
    fmTr.add(R.id.content, new FormFragment().setProp(getFromProp.get()));
    fmTr.commit();
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent intent) {
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
          ((FormFragment)fragment).uploadFile();
        }
        break;
      case SELECT_FILE:
        Object frag = getSupportFragmentManager().findFragmentById(R.id.content);
        if (frag instanceof FormFragment) {
          ((FormFragment)frag).uploadFile();
        }
        break;
      default:
        break;
    }
  }
}
