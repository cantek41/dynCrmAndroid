package veribis.veribiscrmdyn.Widgets.Items;

import android.content.Context;
import android.widget.TextView;

import com.cantekinandroidlib.logger.CustomLogger;

import java.util.Map;

import veribis.veribiscrmdyn.Widgets.AbstractWidget;

/**
 * Created by Cantekin on 23.1.2017.
 */
public class WidgetTextView extends AbstractWidget {
  private String TAG="WidgetTextView";

  public WidgetTextView(Context context) {
    super(context);
    widget = new TextView(context);
  }
  @Override
  public void init() {
    ((TextView) widget).setTextSize(17);
  }
  @Override
  public void setProp(Map<String, Object> properties) {
    super.setProp(properties);
    setValue(String.valueOf(properties.get("defaultValue")));
    CustomLogger.alert(TAG,"defaultValue"+String.valueOf(properties.get("defaultValue")));
  }

  @Override
  public void layoutClick() {
    CustomLogger.alert(TAG,"layoutClick");

  }

  @Override
  public String getValue() {
    return ((TextView) widget).getText().toString();
  }
  @Override
  public void setValue(String data) {
    ((TextView) widget).setText(data);
  }
}
