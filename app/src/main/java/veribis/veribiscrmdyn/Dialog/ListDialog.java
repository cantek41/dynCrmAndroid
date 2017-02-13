package veribis.veribiscrmdyn.Dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import veribis.veribiscrmdyn.List.IMyList;
import veribis.veribiscrmdyn.List.ListController;
import veribis.veribiscrmdyn.List._baseListAdapter;
import veribis.veribiscrmdyn.R;
import veribis.veribiscrmdyn.Widgets.SelectableWidget.SelectableContainer;


/**
 * Created by Cantekin on 12.2.2017.
 */

public class ListDialog extends AlertDialog.Builder {
    private static final String TAG = "ListDialog";
    SelectableContainer container;
    private AlertDialog dialog;
    private ArrayList<Map<String, Object>> dataList = new ArrayList<>();
    Context context;

    public ListDialog(Context context) {
        super(context);
        this.context = context;
    }

    public void setData(SelectableContainer container) {
        this.container = container;
    }

    @Override
    public AlertDialog show() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_list, null);
        ListView dataListView = (ListView) view.findViewById(R.id.dataListListView);
        ListController listController = new ListController(context);
        DialogAdapter listAdapter = new DialogAdapter(context, R.layout.row_data_list, dataList, listController);
        dataListView.setAdapter(listAdapter);
        EditText searchEdit = (EditText) view.findViewById(R.id.searchText);
        listController.setData(container, listAdapter, dataList).searchable(searchEdit).run();
        this.setView(view);
        dialog = super.show();
        return dialog;
    }


    class DialogAdapter extends _baseListAdapter {

        public DialogAdapter(Context context, int resource, List<Map<String, Object>> data, IMyList view) {
            super(context, resource, data);
            this.view = view;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout listLayout = new LinearLayout(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            params.setMargins(10, 10, 2, 2);
            listLayout.setLayoutParams(params);
            TextView listText = new TextView(getContext());
            listText.setLayoutParams(params);
            listText.setTextSize(20);
            listLayout.addView(listText);
            final Map<String, Object> o = getItem(position);
            listText.setText(String.valueOf(o.get(container.getTextKey())));
            listLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    container.getWidget().setData(String.valueOf(o.get(container.getTextKey())), String.valueOf(o.get(container.getValueKey())));
                    dialog.dismiss();
                }
            });
            return listLayout;
        }


    }

}

