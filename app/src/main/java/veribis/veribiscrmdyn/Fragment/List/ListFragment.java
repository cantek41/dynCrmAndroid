package veribis.veribiscrmdyn.Fragment.List;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Model.DataModelList;
import Model.Form.FormProperties;
import Model.ListRequestModel;
import cantekinLogger.CustomLogger;
import cantekinWebApi.IThreadDelegete;
import cantekinWebApi.ThreadWebApiPost;
import cntJson.jsonHelper;
import veribis.veribiscrmdyn.Fragment.MyFragment;
import veribis.veribiscrmdyn.MainActivity;
import veribis.veribiscrmdyn.R;

/**
 * Created by Cantekin on 28.7.2016.
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends MyFragment implements IThreadDelegete, IMyList {
  private static final String TAG = "ListFragment";
  private String webApiAddress = "http://demo.veribiscrm.com/api/mobile/getlist";
  private ListAdapter istAdapter;
  private ListView data_list;
  private ArrayList<Map<String, Object>> dataList;
  private List<String> listFields;

  public ListFragment() {
    // Required empty public constructor
  }

  @Override
  public void initFragment() {
    dataList = new ArrayList<Map<String, Object>>();
    ((MainActivity) getActivity()).changeTitle("List");
    ((MainActivity) getActivity()).fab.setVisibility(View.VISIBLE);
    data_list = (ListView) getActivity().findViewById(R.id.dataListListView);
    listLoad();
  }

  @Override
  public ListFragment setProp(FormProperties prop) {
    LayoutId = R.layout.fragment_list;
    // TODO: 24.1.2017 fieldlar dinamik gelmeli
    listFields = new ArrayList<String>();
    listFields.add("Id");
    listFields.add("Description");
    listFields.add("Subject");
    return this;
  }

  private void listLoad() {
    getData(1);
    fisrtLoad();
  }

  private void fisrtLoad() {
    FragmentTransaction frgTra = getFragmentManager().beginTransaction();
    istAdapter = new ListAdapter
      (getContext(), this, frgTra, R.layout.row_data_list, dataList);
    data_list.setAdapter(istAdapter);
  }

  public void getData(int page) {
    if (!checkConnection())
      return;
    ListRequestModel request = new ListRequestModel();
    request.entity = "Activity";
    request.pageSize = 10;
    request.page = page;
    request.fields = new String[]{"Id", "Subject", "OpenOrClose"};
    new ThreadWebApiPost<ListRequestModel>(this, request, webApiAddress).execute();
    ((MainActivity) getActivity()).showProgress("Liste Çekliyor");

  }

  @Override
  public void postResult(String data) {
    CustomLogger.info(TAG, "List");
    // TODO: 25.1.2017 data model boş yada hatalı gelebilir kontrolünü yap
    DataModelList model = jsonHelper.stringToObject(data,DataModelList.class);
    dataList.addAll(model.Data);
    istAdapter.notifyDataSetChanged();
    ((MainActivity) getActivity()).dismissProgress();

  }
}
