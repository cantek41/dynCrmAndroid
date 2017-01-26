package veribis.veribiscrmdyn.Widgets;

import android.content.Context;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Cantekin on 23.1.2017.
 */
public class WidgetHelper {
  Context context;
  LinearLayout root;
  ArrayList<Map<String, Object>> widgetsProperties;

  public WidgetHelper(LinearLayout root, ArrayList<Map<String, Object>> widgets) {
    widgetsProperties = widgets;
    context = root.getContext();
    this.root = root;
  }

  public List<AbstractWidget> build() {
    List<AbstractWidget> widgets = FactoryWidget.createViewGroup(context, widgetsProperties);
    for (AbstractWidget w : widgets) {
      root.addView(w.getLayout());
    }
    return widgets;
  }
}
