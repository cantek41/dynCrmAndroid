package veribis.veribiscrmdyn.Widgets.WidgetButtons;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import veribis.veribiscrmdyn.R;


/**
 * Created by Cantekin on 16.1.2017.
 */
public class WidgetSaveButtonCommand extends IWidgetButton implements IWidgetButtonCommand {
  View parent;

  public WidgetSaveButtonCommand(Context context) {
    super(context);
  }


  @Override
  public String name() {
    return "Kaydet";
  }

  @Override
  public int icon() {
    return R.drawable.call;
  }

  @Override
  public void execute() {
    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + getParentValue()));
    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions((Activity) getContext(), new String[]{Manifest.permission.CALL_PHONE}, 0);
      return;
    }
    getContext().startActivity(intent);
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
