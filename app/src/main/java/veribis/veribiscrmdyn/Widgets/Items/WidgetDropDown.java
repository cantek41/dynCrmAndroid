package veribis.veribiscrmdyn.Widgets.Items;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import veribis.veribiscrmdyn.List.ValueController;
import veribis.veribiscrmdyn.MainActivity;
import veribis.veribiscrmdyn.Widgets.AbstractWidget;
import veribis.veribiscrmdyn.Widgets.SelectableWidget.ISelectableWidget;
import veribis.veribiscrmdyn.Widgets.SelectableWidget.SelectableContainer;

/**
 * Created by Cantekin on 23.1.2017.
 */
public class WidgetDropDown extends AbstractWidget implements ISelectableWidget {
    private String textKey, valueKey;
    private String value;
    private int sqlId;
    ISelectableWidget sWidget = this;

    public WidgetDropDown(Context context) {
        super(context);
        widget = new TextView(context);
    }

    @Override
    public void init() {
        TextView v = (TextView) widget;
        v.setTextSize(15);
        v.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectableContainer container = new SelectableContainer();
                container.setWidget(sWidget);
                container.setDialogTitle(getLabel());
                container.setSqlId(sqlId);
                container.setTextKey(textKey);
                container.setValueKey(valueKey);
                ((MainActivity) getContext()).onListDialog(container);
            }
        });
    }

    @Override
    public void setProp(Map<String, Object> properties) {
        super.setProp(properties);
        valueKey = String.valueOf(properties.get("valueKey"));
        textKey = String.valueOf(properties.get("textKey"));
        if (properties.get("sqlId")!=null) {
            sqlId=((Double)properties.get("sqlId")).intValue();
        }
        else
            sqlId = 0;

      /* bu şeilde olmalı
        sqlId=31;
        valueKey="Id";
        textKey="AdSoyad";*/
    }

    @Override
    public String getValue() {
        if(value==null)
            return null;
        return String.valueOf((Double.valueOf(value).intValue()));
    }


    @Override
    public void setValue(String data) {
        SelectableContainer container = new SelectableContainer();
        container.setTextKey(textKey);
        container.setValueKey(valueKey);
        container.setWidget(this);
        container.setSqlId(sqlId);
        container.setFilterText(data);
        ValueController value= new ValueController(getContext());
        value.setData(container).run();
    }


    @Override
    public void setData(String text, String value) {
        this.value = value;
        ((TextView) widget).setText(text);
    }
}
