package veribis.veribiscrmdyn.Widgets.Items;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import veribis.veribiscrmdyn.MainActivity;
import veribis.veribiscrmdyn.Widgets.AbstractWidget;
import veribis.veribiscrmdyn.Widgets.SelectableWidget.ISelectableWidget;
import veribis.veribiscrmdyn.Widgets.SelectableWidget.SelectableContainer;

/**
 * Created by Cantekin on 23.1.2017.
 */
public class WidgetDropDown extends AbstractWidget implements ISelectableWidget {
    private String text;
    private String value;
    private String SQLId;
    ISelectableWidget sWidget = this;

    public WidgetDropDown(Context context) {
        super(context);
        widget = new TextView(context);
        init((TextView) widget);
    }

    public void init(TextView v) {
        v.setTextSize(15);
        v.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectableContainer container=new SelectableContainer();
                container.setWidget(sWidget);
                container.setDialogTitle(getLabel());
                container.setSqlId(31);
                container.setTextKey("AdSoyad");
                container.setValueKey("Id");
                ((MainActivity) getContext()).onListDialog(container);
                //value="1";
                //setValue(text);
            }
        });
    }

    @Override
    public String getValue() {
        return value;
    }


    @Override
    public void setValue(String data) {
        // TODO: 12.2.2017 gelen data listeden çekilmeli
        ((TextView) widget).setText(data);
    }

    @Override
    public void setData(String text, String value) {
        this.value = value;
        setValue(text);
    }
}
