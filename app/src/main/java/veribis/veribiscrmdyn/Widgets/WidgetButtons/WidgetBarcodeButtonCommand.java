package veribis.veribiscrmdyn.Widgets.WidgetButtons;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import me.sudar.zxingorient.ZxingOrient;
import veribis.veribiscrmdyn.MainActivity;
import veribis.veribiscrmdyn.R;


/**
 * Created by Cantekin on 16.1.2017.
 */
public class WidgetBarcodeButtonCommand extends IWidgetButton implements IWidgetButtonCommand {
  View parent;

  public WidgetBarcodeButtonCommand(Context context) {
    super(context);
  }


  @Override
  public String name() {
    return "Barkod";
  }

  @Override
  public int icon() {
    return R.drawable.barkod;
  }

  @Override
  public void execute() {
    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions((Activity) getContext(), new String[]{Manifest.permission.CAMERA}, 0);
      return;
    }
    ((MainActivity)getContext()).barcode=parent;
    ZxingOrient integrator = new ZxingOrient((MainActivity)getContext());
    integrator.setIcon(R.drawable.icon)   // Sets the custom icon
      .setToolbarColor("#AA3F51B5")       // Sets Tool bar Color
      .setInfoBoxColor("#AA3F51B5")       // Sets Info box color
      .setInfo("Barkod okutunuz")   //// TODO: 27.1.2017 danimik string
      .initiateScan();
  }

  @Override
  public void setParent(View parent) {
    this.parent = parent;
  }

  private String getParentValue() {
    String result = null;
    if (parent instanceof TextView) {
      result = ((TextView) parent).getText().toString();
    } else if (parent instanceof EditText) {
      result = ((EditText) parent).getText().toString();
    }
    return result;
  }

}
