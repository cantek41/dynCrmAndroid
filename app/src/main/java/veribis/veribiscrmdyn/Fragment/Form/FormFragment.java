package veribis.veribiscrmdyn.Fragment.Form;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Model.UpdateRequestModel;
import cantekinLogger.CustomLogger;
import cantekinWebApi.IThreadDelegete;
import cantekinWebApi.ThreadWebApiPost;
import veribis.veribiscrmdyn.BaseActivity;
import veribis.veribiscrmdyn.Fragment.MyFragment;
import veribis.veribiscrmdyn.MainActivity;
import veribis.veribiscrmdyn.Menu.MenuButtonBuilder;
import veribis.veribiscrmdyn.R;
import veribis.veribiscrmdyn.Widgets.AbstractWidget;
import veribis.veribiscrmdyn.Widgets.WidgetHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class FormFragment extends MyFragment implements IThreadDelegete {
  private static final String TAG = "FormFragment";
  private String webApiAddress = "http://demo.veribiscrm.com/api/mobile/UpdateData";
  List<AbstractWidget> fields;//kaydet te gidecek datalar burada

  public FormFragment() {
    // Required empty public constructor
  }

  @Override
  public FormFragment setProp() {
    LayoutId = R.layout.fragment_form;
    return this;
  }

  @Override
  protected void initFragment() {
    super.initFragment();
    ((BaseActivity) getActivity()).changeTitle("Güne Başla");
    ((BaseActivity) getActivity()).fab.setVisibility(View.VISIBLE);
   /* createWidget("Tarih");
    createWidget("Güne Başla");
    createWidget("Fotoğraf Çek");*/
    intiWidgets();
  }

  private void intiWidgets() {
    LinearLayout root = (LinearLayout) getActivity().findViewById(R.id.fargmentForm);
    fields = new WidgetHelper(root, "dfsgdsf").build();
  }

  @Override
  public void save() {

    UpdateRequestModel request = new UpdateRequestModel();
    request.entity = "Activity";
    request.data.put("Id",103);
    for (AbstractWidget w : fields) {
      request.data.put(w.getField(),w.getValue());
    }
    new ThreadWebApiPost<UpdateRequestModel>(this, request, webApiAddress).execute();
    ((MainActivity) getActivity()).showProgress("Kaydediliyor");
  }

  @Override
  public void onPrepareOptionsMenu(Menu menu) {
    //TODO: menuler somun datasından gelmeli
    menu.clear();
    menu = MenuButtonBuilder.getMenuButtons(this, menu, "SAVE");
    menu = MenuButtonBuilder.getMenuButtons(getActivity(), menu, "CANCEL");
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

  @Override
  public void postResult(String data) {
    CustomLogger.info(TAG, "List");
//    DataModel model = (DataModel) new Gson().fromJson(data, DataModel.class);
    ((MainActivity) getActivity()).dismissProgress();
  }
}
