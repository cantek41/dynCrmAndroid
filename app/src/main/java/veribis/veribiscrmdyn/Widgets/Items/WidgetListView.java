package veribis.veribiscrmdyn.Widgets.Items;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cantekinandroidlib.logger.CustomLogger;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Data.MyPreference;
import Model.Filter;
import Model.Form.baseProperties;
import Model.ListRequestModel;
import Model.Sort;
import veribis.veribiscrmdyn.Fragment.Form.FormFragment;
import veribis.veribiscrmdyn.Fragment.List.ListAdapter;
import veribis.veribiscrmdyn.List.ListController;
import veribis.veribiscrmdyn.List.ValueController;
import veribis.veribiscrmdyn.MainActivity;
import veribis.veribiscrmdyn.R;
import veribis.veribiscrmdyn.WidgetButtons.FactoryWidgetButton;
import veribis.veribiscrmdyn.Widgets.AbstractWidget;
import veribis.veribiscrmdyn.Widgets.SelectableWidget.ISelectableWidget;
import veribis.veribiscrmdyn.Widgets.SelectableWidget.SelectableContainer;

/**
 * Created by Cantekin on 23.1.2017.
 */
public class WidgetListView extends AbstractWidget {
    private static final String TAG = "WidgetListView";
    private ListRequestModel request;
    private baseProperties prop;

    public WidgetListView(Context context) {
        super(context);
        widget = new ListView(context);
    }

    @Override
    public void init() {
        CustomLogger.alert(TAG,"init");
        if (prop != null) {
            prop.setParentFieldId(((FormFragment) ((MainActivity) getContext()).getSupportFragmentManager().findFragmentById(R.id.content)).formProperties.getRecordId());
            setFilter();
            listLoad();
        }
    }

    @Override
    public LinearLayout getLayout() {
        SpannableString spanString = new SpannableString(labelView.getText().toString());
        spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString.length(), 0);
        labelView.setText(spanString);
        // labelView.setTextSize(labelView.getTextSize() + 2);
        LinearLayout rowLayout = super.getLayout();

        widget.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 700));
        rowLayout.setBackgroundColor(Color.TRANSPARENT);
        rowLayout.setPadding(0, 20, 0, 20);
        return rowLayout;
    }

    @Override
    public void layoutClick() {
    }

    @Override
    public void setValue(String data) {
        listLoad();
    }

    @Override
    public void setProp(Map<String, Object> properties) {
        super.setProp(properties);
        if (properties.get("subForm") == null)
            return;
        String listName = (String.valueOf(properties.get("subForm")));
        prop = MyPreference.getPreference(getContext()).getData(listName, baseProperties.class);

        request = new ListRequestModel();
        List<String> fields = new ArrayList<>();
        for (Map<String, Object> widget : prop.getWidget().getWidgets()) {
            fields.add(String.valueOf(widget.get("field")));
        }
        Sort sort = new Sort();   //= container.getSqlId();
        sort.setDir("asc");
        if (prop.getSortField()!=null && !prop.getSortField().equals("")) {
            sort.setField(prop.getSortField());
        } else
            sort.setField("Id");
        request.setSort(sort);
        request.setFields(fields);
        request.setEntity(prop.getEntity());
        request.setPageSize(prop.getListPageSize());
        request.setSqlId(prop.getSqlId());

    }

    private void setFilter() {
        CustomLogger.alert(TAG,"setFilter");
        if (prop.getParentField() != null && prop.getParentFieldId() != null) {
            Filter filter = new Filter();
            filter.setField(prop.getParentField());
            filter.setOp("eq");
            filter.setVal1(String.valueOf(prop.getParentFieldId()));
            request.setFilter(filter);
        }
    }

    private void listLoad() {
        CustomLogger.alert(TAG,"listLoad");
        ArrayList<Map<String, Object>> dataList = new ArrayList<>();
        ListController listController = new ListController(getContext(), MyPreference.getPreference(getContext()).getSqlAddress());
        FragmentTransaction frgTra = (((MainActivity) getContext()).getSupportFragmentManager().findFragmentById(R.id.content)).getFragmentManager().beginTransaction();
        ListAdapter listAdapter = new ListAdapter
                (getContext(), listController, frgTra, R.layout.row_data_list, dataList, prop);
        SelectableContainer container = new SelectableContainer();
        container.setDialogTitle(prop.getFormTitle());
        container.setTextKey(prop.getSearchField());
        listController
                .setData(container, listAdapter, dataList)
                .setRequest(request)
                .run();
        ((ListView) widget).setAdapter(listAdapter);
    }

}
