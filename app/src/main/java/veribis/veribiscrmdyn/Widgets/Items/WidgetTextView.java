package veribis.veribiscrmdyn.Widgets.Items;

import android.content.Context;
import android.widget.TextView;

import veribis.veribiscrmdyn.Widgets.AbstractWidget;

/**
 * Created by Cantekin on 23.1.2017.
 */
public class WidgetTextView extends AbstractWidget {
  public WidgetTextView(Context context) {
    super(context);
    widget = new TextView(context);
  }
  @Override
  public void init() {
    ((TextView) widget).setTextSize(15);
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
