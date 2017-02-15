package veribis.veribiscrmdyn.List;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;


import com.cantekinandroidlib.customJson.jsonHelper;
import com.cantekinandroidlib.logger.CustomLogger;
import com.cantekinandroidlib.webApi.IThreadDelegete;
import com.cantekinandroidlib.webApi.ThreadWebApiPost;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Data.MyPreference;
import Model.DataModelList;
import Model.Filter;
import Model.ListRequestModel;
import Model.Sort;
import veribis.veribiscrmdyn.MainActivity;
import veribis.veribiscrmdyn.R;
import veribis.veribiscrmdyn.Widgets.SelectableWidget.SelectableContainer;

/**
 * Created by Cantekin on 13.2.2017.
 */

public class ListController implements IMyList, IThreadDelegete {
    private static final String TAG = "ListController";
    private static final int REQUEST_READ = 10002;
    private String webApiAddress;
    SelectableContainer container;

    private ListRequestModel request;
    private ArrayList<Map<String, Object>> dataList;
    Context context;
    _baseListAdapter listAdapter;
    DataModelList resultData;
    EditText searchText;
    ImageButton searchButton;

    public ListController(Context context) {
        this.context = context;
        webApiAddress = MyPreference.getPreference(context).getListWebApiAddress();
    }

    public ListController setData(SelectableContainer container, _baseListAdapter listAdapter, ArrayList<Map<String, Object>> dataList) {
        this.container = container;
        this.listAdapter = listAdapter;
        this.dataList = dataList;
        return this;
    }

    public ListController searchable(EditText searchText, ImageButton searchButton) {
        this.searchText = searchText;
        this.searchButton = searchButton;
        return this;
    }

    public void run() {
        if (searchButton != null)
            searchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeRequest(String.valueOf(searchText.getText()));
                }
            });
        if (request == null)
            setRequest();
        getData(1);

    }
    public ListController setRequest(ListRequestModel request) {
        this.request = request;
        return this;
    }

    public void setRequest() {
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
    }



    private void changeRequest(String text) {
        Filter filter = new Filter();
        filter.setField(container.getTextKey());
        filter.setOp("contains");
        filter.setVal1(text);
        request.setFilter(filter);
        getData(1);
        dataList.clear();
    }

    @Override
    public void postResult(String data, int requestCode) {
        // TODO: 25.1.2017 Data model boş yada hatalı gelebilir kontrolünü yap
        CustomLogger.info(TAG, data);
        resultData = jsonHelper.stringToObject(data, DataModelList.class);
        if (resultData.Status.ErrCode == 0 && resultData.Data != null) {
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
    public int getPageSize() {
        return request.getPageSize();
    }

    @Override
    public int getTotal() {
        if (resultData == null)
            return -1;
        else
            return resultData.Total;
    }


}
