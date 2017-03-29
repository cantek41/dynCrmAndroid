package veribis.veribiscrmdyn.Widgets.Items;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import com.cantekinandroidlib.logger.CustomLogger;

import java.util.Map;

import veribis.veribiscrmdyn.Widgets.AbstractWidget;

/**
 * Created by Cantekin on 23.1.2017.
 */
public class WidgetEditView extends AbstractWidget {

  private static final String TAG = "WidgetEditView";

  public WidgetEditView(Context context) {
    super(context);
    widget = new EditText(context);
  }

  @Override
  public void init() {
    EditText v=(EditText)widget;
    v.setOnFocusChangeListener(new OnFocusChangeListener() {
      @Override
      public void onFocusChange(View view, boolean b) {
      //  onClick(view);
      }
    });
  }

  @Override
  public void setProp(Map<String, Object> properties) {
    super.setProp(properties);
    setValue(String.valueOf(properties.get("defaultValue")));

    // TODO: 24.1.2017 özel propertiler burada eklenecek
  }

  @Override
  public void layoutClick() {
    CustomLogger.alert(TAG,"layoutClick");

  }

  @Override
  public String getValue() {
    return ((EditText) widget).getText().toString();
  }

  @Override
  public void setValue(String data) {
    CustomLogger.alert(TAG,"defaultValue=="+String.valueOf(data));
    ((EditText) widget).setText(data);
  }

}
