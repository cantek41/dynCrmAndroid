package veribis.veribiscrmdyn.Widgets;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import veribis.veribiscrmdyn.R;
import veribis.veribiscrmdyn.Widgets.WidgetButtons.WidgetButtonBuilder;

/**
 * Created by Cantekin on 16.1.2017.
 */
public class AbstractWidget extends View {
    private static final String TAG = "AbstractWidget";
    protected TextView labelView;
    protected String Field;
    protected View widget;
    protected ArrayList<String> smallButtons;
    private static  float weight = 0.8f;

    public void setWeight(float weight)
    {
        AbstractWidget.weight =weight;
    }
    public AbstractWidget(Context context) {
        super(context);
        labelView = new TextView(context);
    }

    /**
     * özel prop alt class larda
     * buraya eklenecek
     *
     * @param properties
     */
    public void setProp(Map<String, Object> properties) {
        setLabel(String.valueOf(properties.get("label")));
        setField(String.valueOf(properties.get("field")));
        if (properties.get("buttons") instanceof ArrayList)
            smallButtons = (ArrayList<String>) properties.get("buttons");
    }

    public LinearLayout getLayout() {
        LinearLayout rowLayout = new LinearLayout(getContext());
        // Set the dialog_list full width, full height
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(2, 2, 2, 2);
        rowLayout.setLayoutParams(params);
        rowLayout.setOrientation(LinearLayout.VERTICAL); //dikey
        rowLayout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.rowTransparan));
        //etiketi satıra ekle
        labelView.setLayoutParams(params);
        widget.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, weight));
        //view ve butonlar için yatay dialog_list
        LinearLayout content = new LinearLayout(getContext());
        content.setLayoutParams(params);
        content.setOrientation(LinearLayout.HORIZONTAL);  //yatay
        content.addView(widget);
        float buttonsWeight = 0;
        if (smallButtons != null)
            for (String btn : smallButtons) {
                ImageButton button = WidgetButtonBuilder.getWidgetButtons(getContext(), btn, widget);
                content.addView(button);
                buttonsWeight += ((LinearLayout.LayoutParams) button.getLayoutParams()).weight;
            }
        content.setWeightSum(weight + buttonsWeight);
        rowLayout.addView(labelView);
        rowLayout.addView(content);
        return rowLayout;
    }

    public String getValue() {
        return "";
    }

    public void setValue(String data) {
    }

    public String getField() {
        return Field;
    }

    public void setField(String field) {
        Field = field;
    }

    public String getLabel() {
        return labelView.getText().toString();
    }

    public void setLabel(String text) {
        labelView.setText(text);
    }

}
