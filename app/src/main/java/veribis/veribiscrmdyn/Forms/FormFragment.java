package veribis.veribiscrmdyn.Forms;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import DynamicView.Model.Response;
import veribis.veribiscrmdyn.BaseMyActivity;
import veribis.veribiscrmdyn.IMyFragment;
import veribis.veribiscrmdyn.Menu.MenuButtonBuilder;
import veribis.veribiscrmdyn.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FormFragment extends Fragment implements IMyFragment {
  private String webApiAddress = "http://demo.veribiscrm.com/api/mobile/getlist";
  private ArrayList<Response> dataList;
  private View view;

  public FormFragment() {
    // Required empty public constructor
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    this.view = view;
    getActivity().invalidateOptionsMenu();
    initFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    setHasOptionsMenu(true);//fragmentlerde options menuyu kullanabilmek için gerekli
    return inflater.inflate(R.layout.fragment_form, container, false);
  }

  @Override
  public void onPrepareOptionsMenu(Menu menu) {
    menu.clear();
    menu = MenuButtonBuilder.getMenuButtons(getActivity(), menu, "SAVE");
    menu = MenuButtonBuilder.getMenuButtons(getActivity(), menu, "CANCEL");
  }


  @Override
  public void initFragment() {
    ((BaseMyActivity) getActivity()).changeTitle("Güne Başla");
    ((BaseMyActivity) getActivity()).fab.setVisibility(View.VISIBLE);
    createWidget("Tarih");
    createWidget("Güne Başla");
    createWidget("Fotoğraf Çek");

  }

  private void createWidget(String label) {
    LinearLayout linearLayout = new LinearLayout(getActivity());
    // Set the layout full width, full height
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    params.setMargins(2, 2, 0, 0);
    linearLayout.setLayoutParams(params);

    linearLayout.setOrientation(LinearLayout.VERTICAL); //dikey
    linearLayout.setBackgroundColor(getResources().getColor(R.color.rowTransparan));



    TextView w0 = new TextView(getActivity());
    w0.setLayoutParams(params);
    w0.setText(label);


    LinearLayout content = new LinearLayout(getActivity());
    content.setLayoutParams(params);
    content.setOrientation(LinearLayout.HORIZONTAL);  //yatay
    EditText w1 = new EditText(getActivity());
    w1.setLayoutParams(params);
    content.addView(w1);


    linearLayout.addView(w0);
    linearLayout.addView(content);
    LinearLayout root = (LinearLayout) getActivity().findViewById(R.id.fargmentForm);
    root.addView(linearLayout);
    // viewGroup.addView(linearLayout);
  }

  public void crateButton() {

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

}
