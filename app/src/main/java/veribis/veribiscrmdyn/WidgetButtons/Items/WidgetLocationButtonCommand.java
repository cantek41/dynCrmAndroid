package veribis.veribiscrmdyn.WidgetButtons.Items;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cantekinandroidlib.location.GpsIntentService;

import veribis.veribiscrmdyn.R;
import veribis.veribiscrmdyn.WidgetButtons.IWidgetButton;
import veribis.veribiscrmdyn.WidgetButtons.IWidgetButtonCommand;


/**
 * Created by Cantekin on 16.1.2017.
 */
public class WidgetLocationButtonCommand extends IWidgetButton implements IWidgetButtonCommand {
  View parent;
  GpsIntentService gps;

  public WidgetLocationButtonCommand(Context context) {
    super(context);
    gps = new GpsIntentService(context);
  }


  @Override
  public String name() {
    return "Lokasyon";
  }

  @Override
  public int icon() {
    return R.mipmap.widget_button_location;
  }

  @Override
  public void execute() {
    if (gps.getmLastLocation() == null)
      return;
    StringBuilder location = new StringBuilder();
    location.append(gps.getmLastLocation().getLatitude());
    location.append(",");
    location.append(gps.getmLastLocation().getLongitude());
    if (parent instanceof TextView) {
      ((TextView) parent).setText(location.toString());
    } else if (parent instanceof EditText) {
      ((EditText) parent).setText(location.toString());
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
