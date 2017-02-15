package veribis.veribiscrmdyn.Widgets.Items;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cantekinandroidlib.logger.CustomLogger;

import java.util.Map;

import veribis.veribiscrmdyn.Fragment.Form.FormFragment;
import veribis.veribiscrmdyn.MainActivity;
import veribis.veribiscrmdyn.R;
import veribis.veribiscrmdyn.Widgets.AbstractWidget;
import veribis.veribiscrmdyn.Widgets.EnumEvetType;

/**
 * Created by Cantekin on 23.1.2017.
 */
public class WidgetSubForm extends AbstractWidget {
  private String formName;

  public WidgetSubForm(Context context) {
    super(context);
    widget = new TextView(context);
  }

  /**
   * nesneye tıklayınca gidilecek formun idsini gönderecek
   * MainActivity) getContext()).onClickWidget( olayı ile aktif
   * kaydın idsini alarak alt forma giecek
   *
   */
  @Override
  public void init() {
    TextView v=(TextView)widget;
    v.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        if (formName != null)
          ((FormFragment)((MainActivity) getContext()).getSupportFragmentManager().findFragmentById(R.id.content))
                  .onClickWidget(EnumEvetType.SUBFORM, formName, null);
      }
    });
  }

  @Override
  public void setProp(Map<String, Object> properties) {
    super.setProp(properties);
    if (properties.get("subForm") != null) {
      formName = String.valueOf(properties.get("subForm"));
    }
  }

  @Override
  public String getValue() {
    return ((TextView) widget).getText().toString();
  }

  @Override
  public void setValue(String data) {
    ((TextView) widget).setText(data);
  }
}
