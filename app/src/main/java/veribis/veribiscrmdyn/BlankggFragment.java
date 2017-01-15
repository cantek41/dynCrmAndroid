package veribis.veribiscrmdyn;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import DynamicView.Model.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankggFragment extends Fragment {
  String webApiAddress = "http://demo.veribiscrm.com/api/mobile/getlist";
  private ArrayList<Response> dataList;

  public BlankggFragment() {
    // Required empty public constructor
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    createLabel(view);
    crateButton(view);
    createLabel(view);
    crateButton(view);
    changeTitle();

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

  public void crateButton(View view) {

    LinearLayout linearLayout = new LinearLayout(getActivity());
    // Set the layout full width, full height
    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    linearLayout.setLayoutParams(params);
    linearLayout.setOrientation(LinearLayout.VERTICAL); //or VERTICAL


    Button button2 = new Button(getActivity());
    button2.setLayoutParams(params);
    button2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Islem();
      }
    });
    //... and other views

    ViewGroup viewGroup = (ViewGroup) view;
    linearLayout.addView(button2);
    viewGroup.addView(linearLayout);
  }

  private void createLabel(View view) {
    LinearLayout linearLayout = new LinearLayout(getActivity());
    // Set the layout full width, full height
    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    linearLayout.setLayoutParams(params);
    linearLayout.setOrientation(LinearLayout.VERTICAL); //or VERTICAL

    TextView label = new TextView(getActivity());
    label.setText("selam");
    label.setTextColor(Color.WHITE);
    //For buttons visibility, you must set the layout params in order to give some width and height:

    params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    label.setLayoutParams(params);

    ViewGroup viewGroup = (ViewGroup) view;
    linearLayout.addView(label);
    viewGroup.addView(linearLayout);

  }

  private void Islem() {
    Toast.makeText(getActivity(), "salam", Toast.LENGTH_SHORT).show();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_blankgg, container, false);
  }


}
