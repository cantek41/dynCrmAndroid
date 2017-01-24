package veribis.veribiscrmdyn.Widgets;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import veribis.veribiscrmdyn.R;

/**
 * Created by Cantekin on 16.1.2017.
 */
public class AbstractWidget extends View {
  protected TextView labelView;
  protected String Field;
  protected View widget;


  public AbstractWidget(Context context) {
    super(context);
    labelView=new TextView(context);
  }

  public void setProp(WidgetProperties properties){}

  public LinearLayout getLayout()
  {
    LinearLayout rowLayout = new LinearLayout(getContext());
    // Set the layout full width, full height
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    params.setMargins(2, 2, 0, 0);
    rowLayout.setLayoutParams(params);
    rowLayout.setOrientation(LinearLayout.VERTICAL); //dikey
    rowLayout.setBackgroundColor(getResources().getColor(R.color.rowTransparan));
    //etiketi satıra ekle
    labelView.setLayoutParams(params);
    widget.setLayoutParams(params);

    //view ve butonlar için yatay layout
    LinearLayout content = new LinearLayout(getContext());
    content.setLayoutParams(params);
    content.setOrientation(LinearLayout.HORIZONTAL);  //yatay
    content.addView(widget);
    //TODO:buton varsa buraya gelecek

    rowLayout.addView(labelView);
    rowLayout.addView(content);
    return rowLayout;
  }

  public String getValue() {
    return "";
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
