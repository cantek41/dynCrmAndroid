package veribis.veribiscrmdyn.Fragment.List;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

import Model.DataModel;
import Model.ListRequestModel;
import Model.Response;
import cantekinLogger.CustomLogger;
import cantekinWebApi.IThreadDelegete;
import cantekinWebApi.ThreadWebApiPost;
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
  private ArrayList<Response> dataList;
  public ListFragment() {
    // Required empty public constructor
  }
  @Override
  public void initFragment() {
    dataList = new ArrayList<Response>();
    ((MainActivity) getActivity()).changeTitle("List");
    ((MainActivity) getActivity()).fab.setVisibility(View.VISIBLE);
    data_list = (ListView) getActivity().findViewById(R.id.dataListListView);
    listLoad();
  }
  @Override
  public ListFragment setProp() {
    LayoutId=R.layout.fragment_list;
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
    DataModel model = (DataModel) new Gson().fromJson(data, DataModel.class);
    dataList.addAll(model.Data);
    istAdapter.notifyDataSetChanged();
    ((MainActivity) getActivity()).dismissProgress();

  }
}
