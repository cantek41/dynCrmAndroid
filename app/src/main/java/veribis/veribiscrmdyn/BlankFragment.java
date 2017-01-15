package veribis.veribiscrmdyn;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import DynamicView.Model.DataModel;
import DynamicView.Model.ListRequestModel;
import DynamicView.Model.Response;
import DynamicView.View.ListAdapter;
import cantekinLogger.CustomLogger;
import cantekinWebApi.IThreadDelegete;
import cantekinWebApi.ThreadWebApiPost;

/**
 * Created by Cantekin on 28.7.2016.
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment implements IThreadDelegete , IList {


  private static final String TAG = "BlankFragment";

  public BlankFragment() {
    // Required empty public constructor
  }
  String webApiAddress = "http://demo.veribiscrm.com/api/mobile/getlist";
  private ArrayList<Response> dataList;


  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @Override
  public void onStart() {
    super.onStart();
    Init();
  }

  public void changeTitle() {

    ActionBar actionBar = ((AppCompatActivity) getContext()).getSupportActionBar();
    if (actionBar != null) {


      actionBar.setDisplayShowTitleEnabled(false);
      actionBar.setDisplayShowCustomEnabled(true);
      View customView =getActivity().getLayoutInflater().inflate(R.layout.actionbar_title, null);
      // Get the textview of the title
      TextView customTitle = (TextView) customView.findViewById(R.id.actionbarTitle);

      customTitle.setText("sdfasfsdfsdf");
      // Change the font family (optional)
      customTitle.setTypeface(Typeface.MONOSPACE);
      // Set the on click listener for the title
      customTitle.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Toast.makeText(getContext(), "dfsf", Toast.LENGTH_SHORT).show();

        }
      });
      actionBar.setCustomView(customView);
    }

  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_blankgg, container, false);
  }
  protected void Init() {
    dataList = new ArrayList<Response>();
    listLoad();
  }

  ListAdapter istAdapter;
  ListView data_list;

  private void listLoad() {
    data_list = (ListView) getActivity().findViewById(R.id.dataListListView);
    getData(1);
    fisrtLoad();
  }

  private void fisrtLoad() {
    istAdapter = new ListAdapter
      (getContext(),this, R.layout.row_data_list, dataList);
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
    CustomLogger.info(TAG,"List");
    DataModel model = (DataModel) new Gson().fromJson(data, DataModel.class);
    dataList.addAll(model.Data);
    istAdapter.notifyDataSetChanged();
  }



}
