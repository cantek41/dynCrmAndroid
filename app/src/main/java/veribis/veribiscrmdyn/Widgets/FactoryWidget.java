package veribis.veribiscrmdyn.Widgets;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import veribis.veribiscrmdyn.Widgets.Items.WidgetEditView;
import veribis.veribiscrmdyn.Widgets.Items.WidgetTextView;

/**
 * Created by Cantekin on 23.1.2017.
 */
public final class FactoryWidget {

  public static List<AbstractWidget> createViewGroup(Context context, List<WidgetProperties> widgets) {
    List<AbstractWidget> result = new ArrayList<AbstractWidget>();
    for (WidgetProperties widget : widgets) {
      result.add(createWidget(context, widget));
    }
    return result;
  }

  public static AbstractWidget createWidget(Context contex, WidgetProperties properties) {
    AbstractWidget widget = null;
    switch (properties.getWidgetType()) {
      case TEXT:
        widget = new WidgetTextView(contex);
        break;
      case EDIT:
        widget = new WidgetEditView(contex);
        break;
      default:
        break;
    }
    widget = setProp(contex, widget, properties);
    return widget;
  }

  private static AbstractWidget setProp(Context contex, AbstractWidget widget, WidgetProperties properties) {
    //TODO:widget setprop
    widget.setLabel(properties.getLabel());
    widget.setField(properties.getField());

    //set custom properties
    widget.setProp(properties);
    return widget;
  }


}
