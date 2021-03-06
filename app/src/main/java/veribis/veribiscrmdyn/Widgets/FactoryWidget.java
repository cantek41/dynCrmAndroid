package veribis.veribiscrmdyn.Widgets;

import android.content.Context;

import com.cantekinandroidlib.logger.CustomLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import veribis.veribiscrmdyn.Widgets.Items.WidgetDate;
import veribis.veribiscrmdyn.Widgets.Items.WidgetDropDown;
import veribis.veribiscrmdyn.Widgets.Items.WidgetEditView;
import veribis.veribiscrmdyn.Widgets.Items.WidgetListView;
import veribis.veribiscrmdyn.Widgets.Items.WidgetSubForm;
import veribis.veribiscrmdyn.Widgets.Items.WidgetTextView;
import veribis.veribiscrmdyn.Widgets.Items.WidgetTime;

/**
 * Created by Cantekin on 23.1.2017.
 */
public final class FactoryWidget {

    public static List<AbstractWidget> createViewGroup(Context context, ArrayList<Map<String, Object>> widgets) {
        List<AbstractWidget> result = new ArrayList<>();
        for (Map<String, Object> w : widgets) {
            AbstractWidget abstractWidget = createWidget(context, w);
            if (abstractWidget != null)
                result.add(abstractWidget);
        }
        return result;
    }

    public static AbstractWidget createWidget(Context contex, Map<String, Object> properties) {
        AbstractWidget widget = null;
        CustomLogger.alert("factory", String.valueOf(properties.get("widgetType")));
        if (properties.get("widgetType") == null)
            return null;
        switch (EnumWidgetTypes.valueOf((String) properties.get("widgetType"))) {
            case TEXTVIEW:
                widget = new WidgetTextView(contex);
                break;
            case EDITVIEW:
                widget = new WidgetEditView(contex);
                break;
            case SUBFORM:
                widget = new WidgetSubForm(contex);
                break;
            case DROPDOWN:
                widget = new WidgetDropDown(contex);
                break;
            case DATEPICKER:
                widget = new WidgetDate(contex);
                break;
            case TIMEPICKER:
                widget = new WidgetTime(contex);
                break;
            case LISTVIEW:
                widget = new WidgetListView(contex);
                break;
            default:
                widget = new WidgetTextView(contex);
                break;
        }
        widget.setProp(properties);
        widget.init();
        return widget;
    }
}
