package veribis.veribiscrmdyn.Fragment.List;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.ListView;

import com.cantekinandroidlib.customJson.jsonHelper;
import com.cantekinandroidlib.logger.CustomLogger;
import com.cantekinandroidlib.webApi.IThreadDelegete;
import com.cantekinandroidlib.webApi.ThreadWebApiPost;

import java.util.ArrayList;
import java.util.Map;

import Data.MyPreference;
import Model.DataModelList;
import Model.Form.baseProperties;
import Model.ListRequestModel;
import Model.filter;
import veribis.veribiscrmdyn.Fragment._baseFragment;
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
        request.fields = new ArrayList<>();
        for (Map<String, Object> widget : prop.getWidgets()) {
            request.fields.add(String.valueOf(widget.get("field")));
        }
        request.entity = prop.getEntity();
        request.pageSize = prop.getListPageSize();
        if (prop.getParentField() != null && prop.getParentFieldId() != null) {
            request.filter = new filter();
            request.filter.field = prop.getParentField();
            request.filter.op = "eq";
            request.filter.val1 = String.valueOf(prop.getParentFieldId());
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
        request.page = page;
        CustomLogger.alert(TAG, jsonHelper.objectToJson(request));
        new ThreadWebApiPost<>(REQUEST_READ, this, request, webApiAddress).execute();
        ((MainActivity) getActivity()).showProgress("Liste Çekliyor");

    }

    @Override
    public void postResult(String data,int requestCode) {
        // TODO: 25.1.2017 Data model boş yada hatalı gelebilir kontrolünü yap
        DataModelList model = jsonHelper.stringToObject(data, DataModelList.class);
        if (model != null) {
            dataList.addAll(model.Data); //listin datsource sine  at
            listAdapter.notifyDataSetChanged(); //listeyi güncelle
        }
        ((MainActivity) getActivity()).dismissProgress();
    }
}
