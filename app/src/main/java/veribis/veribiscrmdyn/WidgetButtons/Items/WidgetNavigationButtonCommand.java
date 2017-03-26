package veribis.veribiscrmdyn.WidgetButtons.Items;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import veribis.veribiscrmdyn.BaseActivity;
import veribis.veribiscrmdyn.R;
import veribis.veribiscrmdyn.WidgetButtons.IWidgetButton;
import veribis.veribiscrmdyn.WidgetButtons.IWidgetButtonCommand;


/**
 * Created by Cantekin on 27.1.2017.
 */
public class WidgetNavigationButtonCommand extends IWidgetButton implements IWidgetButtonCommand {
  View parent;

  public WidgetNavigationButtonCommand(Context context) {
    super(context);
  }


  @Override
  public String name() {
    return "Harita";
  }

  @Override
  public int icon() {
    return R.mipmap.widget_button_navigation;
  }

  @Override
  public void execute() {
    try {
      String[] location = getParentValue().split(",");
      float latitude = Float.valueOf(location[0]);// 55.758192f;
      float longitude = Float.valueOf(location[1]);// 37.642817f;
      String uriBegin = "geo:" + latitude + "," + longitude;
      String query = latitude + "," + longitude ;
      String encodedQuery = Uri.encode(query);
      String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
      Uri uri = Uri.parse(uriString);
      Intent intent = new Intent(Intent.ACTION_VIEW, uri);
      intent.putExtra("lat_to", latitude);
      intent.putExtra("lon_to", longitude);
      getContext().startActivity(intent);
    } catch (Exception e) {
      ((BaseActivity)getContext()).showMessage("Lokasyon doğru formatta değil");
      // TODO: 27.1.2017 stringler dinamik olmalı
    }

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
