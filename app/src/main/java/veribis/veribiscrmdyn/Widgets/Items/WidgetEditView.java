package veribis.veribiscrmdyn.Widgets.Items;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import veribis.veribiscrmdyn.Widgets.ActiveWidget;
import veribis.veribiscrmdyn.Widgets.WidgetProperties;

/**
 * Created by Cantekin on 23.1.2017.
 */
public class WidgetEditView extends ActiveWidget {

  public WidgetEditView(Context context) {
    super(context);
    widget = new EditText(context);
    init((EditText) widget);
  }

  public void init(EditText v) {
    v.setOnFocusChangeListener(new OnFocusChangeListener() {
      @Override
      public void onFocusChange(View view, boolean b) {
        onClick(view);
      }
    });
  }

  @Override
  public void setProp(WidgetProperties properties) {
    super.setProp(properties);
    // TODO: 24.1.2017 özel propertiler burada eklenecek
  }

  @Override
  public String getValue() {
    return ((EditText) widget).getText().toString();
  }

}
