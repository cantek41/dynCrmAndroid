package veribis.veribiscrmdyn.Fragment.Form;


import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.Form.DataModelForm;
import Model.Form.FormProperties;
import Model.UpdateRequestModel;
import cantekinLogger.CustomLogger;
import cantekinWebApi.IThreadDelegete;
import cantekinWebApi.ThreadWebApiPost;
import cntJson.jsonHelper;
import veribis.veribiscrmdyn.BaseActivity;
import veribis.veribiscrmdyn.Fragment.MyFragment;
import veribis.veribiscrmdyn.MainActivity;
import veribis.veribiscrmdyn.Menu.MenuButtonBuilder;
import veribis.veribiscrmdyn.R;
import veribis.veribiscrmdyn.Widgets.AbstractWidget;
import veribis.veribiscrmdyn.Widgets.EnumWidgetTypes;
import veribis.veribiscrmdyn.Widgets.WidgetHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class FormFragment extends MyFragment implements IThreadDelegete {
  private static final String TAG = "FormFragment";
  private String webApiAddress = "http://demo.veribiscrm.com/api/mobile/UpdateData"; //preferanceden gelecek
  private String webApiAddressGet = "http://demo.veribiscrm.com/api/mobile/GetData";
  List<AbstractWidget> widgetFields;//kaydet te gidecek datalar burada
  private FormProperties formProperties;
  private DataModelForm formModel = new DataModelForm();

  public FormFragment() {
    // Required empty public constructor
  }

  @Override
  public FormFragment setProp(FormProperties prop) {
    if (prop != null) this.formProperties = prop;
    LayoutId = R.layout.fragment_form;
    // TODO: 25.1.2017 form datası prferenstan gelecek
    formProperties = new FormProperties();
    formProperties.setFormName("Form1");
    formProperties.setId(103);
    formProperties.setEntity("Activity");
    formProperties.widgets = new ArrayList<Map<String, Object>>();
    formProperties.Buttons = new ArrayList<String>();
    formProperties.Buttons.add("SAVE");
    formProperties.Buttons.add("CANCEL");
    Map<String, Object> widget = new HashMap<String, Object>();
    widget.put("Label", "Description");
    widget.put("Field", "Description");
    widget.put("WidgetType", EnumWidgetTypes.EDIT);
    widget.put("Buttons", null);
    formProperties.widgets.add(widget);
    widget = new HashMap<String, Object>();
    widget.put("Label", "Subject");
    widget.put("Field", "Subject");
    widget.put("WidgetType", EnumWidgetTypes.EDIT);
    widget.put("Buttons", null);
    ArrayList<String> btn = new ArrayList<String>();
    btn.add("CALL");
    btn.add("LOCATION");
    widget.put("Buttons", btn);

    formProperties.widgets.add(widget);
    widget = new HashMap<String, Object>();
    widget.put("Label", "Note");
    widget.put("Field", "Note");
    widget.put("WidgetType", EnumWidgetTypes.EDIT);
    formProperties.widgets.add(widget);

    formModel.Data.put("Id", formProperties.getId());
    for (Map<String, Object> w : formProperties.widgets) {
      formModel.Data.put(String.valueOf(w.get("Field")), "");
    }
    return this;
  }

  @Override
  protected void initFragment() {
    super.initFragment();
    ((BaseActivity) getActivity()).changeTitle(formProperties.getFormName());
    ((BaseActivity) getActivity()).fab.setVisibility(View.VISIBLE);
    intiWidgets();
  }

  private void intiWidgets() {
    LinearLayout root = (LinearLayout) getActivity().findViewById(R.id.fargmentForm);
    widgetFields = new WidgetHelper(root, formProperties.widgets).build();
    getData();
  }

  private void getData() {
    if (!checkConnection())
      return;
    UpdateRequestModel request = new UpdateRequestModel();
    request.entity = formProperties.getEntity();
    request.data = formModel.Data;
    new ThreadWebApiPost<UpdateRequestModel>(this, request, webApiAddressGet).execute();
    ((MainActivity) getActivity()).showProgress("Data Getiriliyor");

  }


  @Override
  public void save() {
    if (!checkConnection())
      return;
    UpdateRequestModel request = new UpdateRequestModel();
    request.entity = formProperties.getEntity();
    request.data.put("Id", formProperties.getId());
    for (AbstractWidget w : widgetFields) {
      request.data.put(w.getField(), w.getValue());
    }
    //TODO: stringler dinamik olmalı
    new ThreadWebApiPost<UpdateRequestModel>(this, request, webApiAddress).execute();
    ((MainActivity) getActivity()).showProgress("Kaydediliyor");
  }

  @Override
  public void onPrepareOptionsMenu(Menu menu) {
    menu.clear();
    for (String button : formProperties.Buttons)
      menu = MenuButtonBuilder.getMenuButtons(this, menu, button);
  }

  @Override
  public void postResult(String data) {
    // TODO: 25.1.2017 data model boş yada hatalı gelebilir kontrolünü yap
    try {
      // TODO: 26.1.2017 jsonları dataya çeviren bir class yaz hep ordan çek yarın gson kullanmzasan çok yerde değişiklik yapmak gerekebilir
      formModel = jsonHelper.stringToObject(data, DataModelForm.class);
      if (formModel.Status.ErrCode == 0)
        setDataToWidget();
      else
        ((MainActivity) getActivity()).showMessage(formModel.Status.Message);
    } catch (JsonSyntaxException ex) {
      CustomLogger.error(TAG, ex.getMessage());
      ((MainActivity) getActivity()).showMessage("Yöneticinize Başvurun Hata Kodu:Tasarım uyuşmazlığı");
    } catch (NullPointerException ex) {
      CustomLogger.error(TAG, ex.getMessage());
      ((MainActivity) getActivity()).showMessage("Yöneticinize Başvurun Hata Kodu:Null Data");
    } finally {
      ((MainActivity) getActivity()).dismissProgress();
    }
  }

  private void setDataToWidget() {
    for (AbstractWidget w : widgetFields) {
      w.setValue(formModel.Data.get(w.getField().toString()).toString());
    }
  }
}
