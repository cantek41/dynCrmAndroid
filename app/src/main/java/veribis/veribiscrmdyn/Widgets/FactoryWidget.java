package veribis.veribiscrmdyn.Widgets;

import android.content.Context;

import com.cantekinandroidlib.logger.CustomLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import veribis.veribiscrmdyn.Widgets.Items.WidgetEditView;
import veribis.veribiscrmdyn.Widgets.Items.WidgetSubForm;
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
        CustomLogger.alert("factory", String.valueOf(properties.get("widgetType")));
        switch (EnumWidgetTypes.valueOf((String) properties.get("widgetType"))) {
            case TEXT:
                widget = new WidgetTextView(contex);
                break;
            case EDITVIEW:
                widget = new WidgetEditView(contex);
                break;
            case SUBFORM:
                widget = new WidgetSubForm(contex);
                break;
            default:
                widget = new WidgetTextView(contex);
                break;
        }
        widget.setProp(properties);
        return widget;
    }
}
