package veribis.veribiscrmdyn.Lists;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

import DynamicView.Model.DataModel;
import DynamicView.Model.ListRequestModel;
import DynamicView.Model.Response;
import DynamicView.View.ListAdapter;
import cantekinWebApi.IThreadDelegete;
import cantekinWebApi.ThreadWebApiPost;
import veribis.veribiscrmdyn.R;

public class MyListActivity extends AppCompatActivity implements IThreadDelegete, IMyList {
  String webApiAddress = "http://demo.veribiscrm.com/api/mobile/getlist";
  private ArrayList<Response> dataList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Init();
  }

  protected void Init() {
    setContentView(R.layout.activity_list);
    dataList = new ArrayList<Response>();
    listLoad();
  }

  ListAdapter istAdapter;
  ListView data_list;

  private void listLoad() {
    data_list = (ListView) findViewById(R.id.dataListListView);
    getData(1);
    fisrtLoad();
  }

  private void fisrtLoad() {
    FragmentTransaction frgTra;
    frgTra = getSupportFragmentManager().beginTransaction();
    istAdapter = new ListAdapter
      (this, this, frgTra, R.layout.row_data_list, dataList);
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
    DataModel model = (DataModel) new Gson().fromJson(data, DataModel.class);
    dataList.addAll(model.Data);
    //  fisrtLoad();
    istAdapter.notifyDataSetChanged();
  }
}
