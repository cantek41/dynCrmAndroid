package veribis.veribiscrmdyn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import Model.Form.FormProperties;
import me.sudar.zxingorient.ZxingOrient;
import me.sudar.zxingorient.ZxingOrientResult;
import veribis.veribiscrmdyn.Fragment.Form.FormFragment;


public class MainActivity extends BaseActivity {
  public View barcode;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initActivity();
  }

  public void goDetail() {
    fmTr = getSupportFragmentManager().beginTransaction();
    fmTr.add(R.id.content, new FormFragment().setProp(new FormProperties()));
    fmTr.addToBackStack(null);
    fmTr.commit();
  }

  @Override
  protected void initActivity() {
    super.initActivity();
    fmTr = getSupportFragmentManager().beginTransaction();
    fmTr.add(R.id.content, new FormFragment().setProp(new FormProperties()));
    fmTr.commit();
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    ZxingOrientResult scanResult =
      ZxingOrient.parseActivityResult(requestCode, resultCode, intent);
    if (scanResult != null) {
      if (barcode instanceof TextView)
        ((TextView) barcode).setText(scanResult.getContents());
      else if (barcode instanceof EditText)
        ((EditText) barcode).setText(scanResult.getContents());

    }
  }
}
