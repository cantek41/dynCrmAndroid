package veribis.veribiscrmdyn.Dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cantekinandroidlib.customJson.jsonHelper;
import com.cantekinandroidlib.logger.CustomLogger;
import com.cantekinandroidlib.webApi.IThreadDelegete;
import com.cantekinandroidlib.webApi.ThreadWebApiPost;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Data.MyPreference;
import Model.DataModelList;
import Model.ListRequestModel;
import Model.Sort;
import veribis.veribiscrmdyn.List.IMyList;
import veribis.veribiscrmdyn.List._baseListAdapter;
import veribis.veribiscrmdyn.MainActivity;
import veribis.veribiscrmdyn.R;
import veribis.veribiscrmdyn.Widgets.SelectableWidget.SelectableContainer;


/**
 * Created by Cantekin on 12.2.2017.
 */

public class ListDialog extends AlertDialog.Builder implements IThreadDelegete, IMyList {
    private static final String TAG = "ListDialog";
    private static final int REQUEST_READ = 10002;
    private String webApiAddress = MyPreference.getPreference(getContext()).getListWebApiAddress();

    SelectableContainer container;
    private AlertDialog dialog;
    private ListRequestModel request;
    private ArrayList<Map<String, Object>> dataList = new ArrayList<>();
    private DialogAdapter listAdapter;
    Context context;
    DataModelList resultData;

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
        setRequest();
        getData(1);
        listAdapter = new DialogAdapter(getContext(), R.layout.row_data_list, dataList, this);
        dataListView.setAdapter(listAdapter);
        this.setView(view);
        dialog = super.show();
        return dialog;
    }

    private void setRequest() {
        request = new ListRequestModel();
        request.setSqlId(container.getSqlId());
        Sort sort = new Sort();   //= container.getSqlId();
        sort.setDir("asc");
        sort.setField(container.getTextKey());
        request.setSort(sort);
        request.setPageSize(10);
        request.setPage(1);
        List<String> fields = new ArrayList<>();
        fields.add(container.getTextKey());
        fields.add(container.getValueKey());
        request.setFields(fields);

//        if (prop.getParentField() != null && prop.getParentFieldId() != null) {
//            request.Filter = new Filter();
//            request.Filter.field = prop.getParentField();
//            request.Filter.op = "eq";
//            request.Filter.val1 = String.valueOf(prop.getParentFieldId());
//        }
    }

    @Override
    public void postResult(String data, int requestCode) {
        // TODO: 25.1.2017 Data model boş yada hatalı gelebilir kontrolünü yap
        CustomLogger.info(TAG, data);
        resultData = jsonHelper.stringToObject(data, DataModelList.class);
        if (resultData.Status.ErrCode == 0) {
            dataList.addAll(resultData.Data); //listin datsource sine  at
            listAdapter.notifyDataSetChanged(); //listeyi güncelle
        }
        ((MainActivity) context).dismissProgress();
    }

    @Override
    public void getData(int page) {
        if (!((MainActivity) context).isConnection())
            return;
        request.setPage(page);
        CustomLogger.alert(TAG, jsonHelper.objectToJson(request));
        new ThreadWebApiPost<>(REQUEST_READ, this, request, webApiAddress).execute();
        ((MainActivity) context).showProgress("Liste Çekliyor");
    }

    @Override
    public int getTotal() {
        if (resultData == null)
            return -1;
        else
            return resultData.Total;
    }


    class DialogAdapter extends _baseListAdapter {

        public DialogAdapter(Context context, int resource, List<Map<String, Object>> data, IMyList view) {
            super(context, resource, data);
            pageSize = request.getPageSize();
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

