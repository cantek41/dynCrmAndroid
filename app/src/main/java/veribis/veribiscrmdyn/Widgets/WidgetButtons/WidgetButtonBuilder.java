package veribis.veribiscrmdyn.Widgets.WidgetButtons;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Cantekin on 16.1.2017.
 */
public class WidgetButtonBuilder {
  static float weight = 0.2f;

  private WidgetButtonBuilder() {
  }

  public static ImageButton getWidgetButtons(Context context, String buttonName, View parentValue) {
    EnumWidgetButtonItem enumitem = EnumWidgetButtonItem.valueOf(buttonName);
    IWidgetButton command = null;
    switch (enumitem) {
      case CALL:
        command = new WidgetCallButtonCommand(context);
        break;
      case MAP:
        command = new WidgetNavigationButtonCommand(context);
        break;
      case BARCODE:
        command = new WidgetBarcodeButtonCommand(context);
        break;
      case LOCATION:
        command = new WidgetLocationButtonCommand(context);
        break;
      // TODO: 27.1.2017 diğer butonlarda eklenecek
      default:
        break;
    }
    command.setLayoutParams(new LinearLayout.LayoutParams(100, ViewGroup.LayoutParams.WRAP_CONTENT, weight));
    ((LinearLayout.LayoutParams)command.getLayoutParams()).setMargins(0,2,8,2);
    command.setParent(parentValue);
    command.setImageResource(command.icon());
    command.setScaleType(ImageView.ScaleType.FIT_CENTER);
    command.setAdjustViewBounds(true);
    command.setBackgroundColor(Color.TRANSPARENT);
    final IWidgetButton finalCommand = command;
    command.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        finalCommand.execute();
      }
    });
    return command;
  }
}
