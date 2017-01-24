package veribis.veribiscrmdyn.Widgets;

import android.content.Context;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cantekin on 23.1.2017.
 */
public class WidgetHelper {
  Context context;
  LinearLayout root;
  List<WidgetProperties> widgetsData;

  public WidgetHelper(LinearLayout root,String swed)
  {
    widgetsData=new ArrayList<WidgetProperties>();
    widgetsData.add(new WidgetProperties(EnumWidgetTypes.TEXT,"etiket","Description"));
    widgetsData.add(new WidgetProperties(EnumWidgetTypes.EDIT,"edit Etiketi","Subject"));
    context=root.getContext();
    this.root=root;
  }

  public List<AbstractWidget> build()
  {
    List<AbstractWidget> widgets=FactoryWidget.createViewGroup(context,widgetsData);
    for (AbstractWidget w:widgets)
    {
      root.addView(w.getLayout());
    }
    return widgets;
  }
}
