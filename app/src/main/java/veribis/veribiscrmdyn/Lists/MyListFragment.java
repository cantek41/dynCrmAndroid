package veribis.veribiscrmdyn.Lists;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

import DynamicView.Model.DataModel;
import DynamicView.Model.ListRequestModel;
import DynamicView.Model.Response;
import DynamicView.View.ListAdapter;
import cantekinLogger.CustomLogger;
import cantekinWebApi.IThreadDelegete;
import cantekinWebApi.ThreadWebApiPost;
import veribis.veribiscrmdyn.BaseMyActivity;
import veribis.veribiscrmdyn.IMyFragment;
import veribis.veribiscrmdyn.R;

/**
 * Created by Cantekin on 28.7.2016.
 * A simple {@link Fragment} subclass.
 */
public class MyListFragment extends Fragment implements IThreadDelegete, IMyList, IMyFragment {
  private static final String TAG = "MyListFragment";
  private String webApiAddress = "http://demo.veribiscrm.com/api/mobile/getlist";
  private ListAdapter istAdapter;
  private ListView data_list;
  private ArrayList<Response> dataList;
  public MyListFragment() {
    // Required empty public constructor
  }
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_list, container, false);
  }
  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initFragment();
  }
  @Override
  public void initFragment() {
    dataList = new ArrayList<Response>();
    ((BaseMyActivity) getActivity()).changeTitle("List");
    ((BaseMyActivity) getActivity()).fab.setVisibility(View.VISIBLE);
    data_list = (ListView) getActivity().findViewById(R.id.dataListListView);
    listLoad();
  }
  public MyListFragment setProp() {
    //TODO liste ayarları gidecek
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
  }
  @Override
  public void postResult(String data) {
    CustomLogger.info(TAG, "List");
    DataModel model = (DataModel) new Gson().fromJson(data, DataModel.class);
    dataList.addAll(model.Data);
    istAdapter.notifyDataSetChanged();
  }
}
