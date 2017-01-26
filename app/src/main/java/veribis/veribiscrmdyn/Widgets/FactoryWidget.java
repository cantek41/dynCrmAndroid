package veribis.veribiscrmdyn.Widgets;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import veribis.veribiscrmdyn.Widgets.Items.WidgetEditView;
import veribis.veribiscrmdyn.Widgets.Items.WidgetTextView;

/**
 * Created by Cantekin on 23.1.2017.
 */
public final class FactoryWidget {

  public static List<AbstractWidget> createViewGroup(Context context, ArrayList<Map<String, Object>> widgets) {
    List<AbstractWidget> result = new ArrayList<AbstractWidget>();
    for (Map<String, Object> w : widgets) {
      result.add(createWidget(context, w));
    }
    return result;
  }

  public static AbstractWidget createWidget(Context contex, Map<String, Object> properties) {
    AbstractWidget widget = null;
    switch ((EnumWidgetTypes) properties.get("WidgetType")) {
      case TEXT:
        widget = new WidgetTextView(contex);
        break;
      case EDIT:
        widget = new WidgetEditView(contex);
        break;
      default:
        break;
    }
    widget.setProp(properties);
    return widget;
  }
}
