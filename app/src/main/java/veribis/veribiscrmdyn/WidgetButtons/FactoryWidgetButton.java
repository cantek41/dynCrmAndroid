package veribis.veribiscrmdyn.WidgetButtons;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import veribis.veribiscrmdyn.WidgetButtons.Items.WidgetBarcodeButtonCommand;
import veribis.veribiscrmdyn.WidgetButtons.Items.WidgetCallButtonCommand;
import veribis.veribiscrmdyn.WidgetButtons.Items.WidgetLocationButtonCommand;
import veribis.veribiscrmdyn.WidgetButtons.Items.WidgetMailButtonCommand;
import veribis.veribiscrmdyn.WidgetButtons.Items.WidgetNavigationButtonCommand;

/**
 * Created by Cantekin on 16.1.2017.
 */
public class FactoryWidgetButton {
    static float weight = 0.2f;

    private FactoryWidgetButton() {
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
            case SEND_MAIL:
                command = new WidgetMailButtonCommand(context);
                break;
            // TODO: 27.1.2017 diğer butonlarda eklenecek
            default:
                break;
        }
        command.setLayoutParams(new LinearLayout.LayoutParams(110, 110, weight));
        ((LinearLayout.LayoutParams) command.getLayoutParams()).setMargins(0, 0, 5, 0);
        command.setParent(parentValue);
        command.setPadding(0, 0, 0, 0);
        command.setImageResource(command.icon());
        command.setScaleType(ImageView.ScaleType.FIT_CENTER);
        // command.setAdjustViewBounds(true);
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
