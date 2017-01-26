package veribis.veribiscrmdyn.Widgets.WidgetButtons;

import android.content.Context;
import android.widget.ImageButton;

public abstract class IWidgetButton extends ImageButton implements IWidgetButtonCommand  {
  public IWidgetButton(Context context) {
    super(context);
  }
}
