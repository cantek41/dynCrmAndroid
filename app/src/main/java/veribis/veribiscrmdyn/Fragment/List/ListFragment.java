package veribis.veribiscrmdyn.Fragment.List;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.ListView;

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
import Model.Form.baseProperties;
import Model.ListRequestModel;
import Model.Sort;
import veribis.veribiscrmdyn.Fragment._baseFragment;
import veribis.veribiscrmdyn.List.IMyList;
import veribis.veribiscrmdyn.MainActivity;
import veribis.veribiscrmdyn.R;

/**
 * Created by Cantekin on 28.7.2016.
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends _baseFragment implements IThreadDelegete, IMyList {
    private static final String TAG = "ListFragment";
    private String webApiAddress = MyPreference.getPreference(getContext()).getListWebApiAddress();
    private ListAdapter listAdapter;
    private ListView dataListView;
    private ArrayList<Map<String, Object>> dataList;   //listin dataSourcesi
    private ListRequestModel request;
    private static final int REQUEST_READ = 10002;
    DataModelList resultData;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void initFragment() {
        super.initFragment();
        dataListView = (ListView) getActivity().findViewById(R.id.dataListListView);
        listLoad();
    }

    @Override
    public ListFragment setProp(baseProperties prop) {
        this.formProperties = prop;
        LayoutId = R.layout.fragment_list;
        request = new ListRequestModel();

        List<String> fields = new ArrayList<>();
        for (Map<String, Object> widget : prop.getWidgets()) {
            fields.add(String.valueOf(widget.get("field")));
        }
        Sort sort = new Sort();   //= container.getSqlId();
        sort.setDir("asc");
        sort.setField("Name");
        request.setSort(sort);
        request.setFields(fields);
        request.setEntity(prop.getEntity());
        request.setPageSize(prop.getListPageSize());
        if (prop.getParentField() != null && prop.getParentFieldId() != null) {
            Filter filter = new Filter();
            filter.setField(prop.getParentField());
            filter.setOp("eq");
            filter.setVal1(String.valueOf(prop.getParentFieldId()));
            request.setFilter(filter);
            //// TODO: 12.2.2017 arama gizlenecek burada
        }
        return this;
    }

    private void listLoad() {
        dataList = new ArrayList<>();//datasource oluşturuldu
        getData(1);
        fisrtLoad();
    }

    private void fisrtLoad() {
        FragmentTransaction frgTra = getFragmentManager().beginTransaction();
        listAdapter = new ListAdapter
                (getContext(), this, frgTra, R.layout.row_data_list, dataList, formProperties);
        dataListView.setAdapter(listAdapter);
    }

    public void getData(int page) {
        if (!isConnection())
            return;
        request.setPage(page);
        CustomLogger.alert(TAG, jsonHelper.objectToJson(request));
        new ThreadWebApiPost<>(REQUEST_READ, this, request, webApiAddress).execute();
        ((MainActivity) getActivity()).showProgress("Liste Çekliyor");
    }

    @Override
    public int getTotal() {
        if (resultData == null)
            return request.getPageSize();
        else
            return resultData.Total;
    }

    @Override
    public void postResult(String data, int requestCode) {
        // TODO: 25.1.2017 Data model boş yada hatalı gelebilir kontrolünü yap
        resultData = jsonHelper.stringToObject(data, DataModelList.class);
        if (resultData != null) {
            dataList.addAll(resultData.Data); //listin datsource sine  at
            listAdapter.notifyDataSetChanged(); //listeyi güncelle
        }
        ((MainActivity) getActivity()).dismissProgress();
    }
}
