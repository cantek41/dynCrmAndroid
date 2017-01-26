package veribis.veribiscrmdyn.Widgets.WidgetButtons;

import android.view.View;

/**
 * her menu elemanı için gerekli arayüz
 * Created by Cantekin on 16.1.2017.
 */
public interface IWidgetButtonCommand {
  String name();

  int icon();

  void execute();

  void setParent(View parent);
}
