package veribis.veribiscrmdyn.Widgets;

import android.content.Context;
import android.view.View;
<<<<<<< .mine
import android.widget.ImageButton;
=======
import android.widget.Button;
>>>>>>> .theirs
import android.widget.LinearLayout;
import android.widget.TextView;

<<<<<<< .mine
import java.util.ArrayList;
import java.util.Map;


=======
import java.util.ArrayList;
import java.util.Map;

import cantekinLogger.CustomLogger;
>>>>>>> .theirs
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
<<<<<<< .mine
  protected ArrayList<String> smallButtons;
  private static float weight=0.8f;
=======
  protected ArrayList<String> smallButtons;

>>>>>>> .theirs

  public AbstractWidget(Context context) {
    super(context);
    labelView = new TextView(context);
  }

<<<<<<< .mine
  /**
   * özel prop alt class larda
   * buraya eklenecek
   * @param properties
   */
  public void setProp(Map<String, Object> properties) {
    setLabel(String.valueOf(properties.get("Label")));
    setField(String.valueOf(properties.get("Field")));
    smallButtons = (ArrayList<String>) properties.get("Buttons");
  }

=======
  /**
   * özel prop alt class larda
   * buraya eklenecek
   *
   * @param properties
   */
  public void setProp(Map<String, Object> properties) {
    setLabel(String.valueOf(properties.get("Label")));
    setField(String.valueOf(properties.get("Field")));
    smallButtons = (ArrayList<String>) properties.get("Buttons");
  }
>>>>>>> .theirs

  public LinearLayout getLayout() {
    LinearLayout rowLayout = new LinearLayout(getContext());
    // Set the layout full width, full height
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    params.setMargins(2, 2, 2, 2);
    rowLayout.setLayoutParams(params);
    rowLayout.setOrientation(LinearLayout.VERTICAL); //dikey
    rowLayout.setBackgroundColor(getResources().getColor(R.color.rowTransparan));
    //etiketi satıra ekle

    labelView.setLayoutParams(params);
<<<<<<< .mine
    widget.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 120,weight));
=======
    widget.setLayoutParams(params);
>>>>>>> .theirs
    //view ve butonlar için yatay layout
    LinearLayout content = new LinearLayout(getContext());
    content.setLayoutParams(params);
    content.setOrientation(LinearLayout.HORIZONTAL);  //yatay
    content.addView(widget);
<<<<<<< .mine
    float buttonsWeight = 0;
    if (smallButtons != null)
      for (String btn : smallButtons) {
        ImageButton button = WidgetButtonBuilder.getWidgetButtons(getContext(),btn, widget);
        content.addView(button);
        buttonsWeight += ((LinearLayout.LayoutParams) button.getLayoutParams()).weight;
      }
    content.setWeightSum(weight + buttonsWeight);


=======
    //TODO:buton varsa buraya gelecek  layoutları ayarla
    if (smallButtons != null)
      for (String btn : smallButtons) {
        // TODO: 26.1.2017 bu butonlar gene bir factori den gelmeli 
        Button button = new Button(getContext());
        button.setLayoutParams(params);
        button.setText(btn);
        content.addView(button);
        CustomLogger.info(TAG,btn);
      }
>>>>>>> .theirs
    rowLayout.addView(labelView);
    rowLayout.addView(content);
    return rowLayout;
  }

  public String getValue() {
    return "";
  }
<<<<<<< .mine

  public void setValue(String data) { }


=======

  public void setValue(String data) {
  }

>>>>>>> .theirs
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
