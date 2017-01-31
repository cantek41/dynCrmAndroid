package veribis.veribiscrmdyn.Fragment.Form;


import android.support.v4.app.Fragment;
import android.view.Menu;
import android.widget.LinearLayout;

import com.google.gson.JsonSyntaxException;

import java.util.List;
import java.util.Map;

import Model.Form.DataModelForm;
import Model.Form.FormProperties;
import Model.UpdateRequestModel;
import cantekinLogger.CustomLogger;
import cantekinWebApi.IThreadDelegete;
import cantekinWebApi.ThreadWebApiPost;
import cntJson.jsonHelper;
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
  //TODO: preferanceden gelecek
  private String webApiAddressGet = "http://demo.veribiscrm.com/api/mobile/GetData";
  List<AbstractWidget> widgetFields;//kaydet te gidecek datalar burada
  private DataModelForm formModel;

  public FormFragment() {
    // Required empty public constructor
  }

  @Override
  public FormFragment setProp(FormProperties prop) {
    if (prop != null) this.formProperties = prop;
    LayoutId = R.layout.fragment_form;
    formModel = new DataModelForm();
    formModel.Data.put("Id", formProperties.getRecordId());
    String field;
    if (formProperties.getWidgets() != null)
      for (Map<String, Object> w : formProperties.getWidgets()) {
        field = String.valueOf(w.get("Field"));
        if (!field.equals("null")) {
          formModel.Data.put(field, "");
          CustomLogger.alert(TAG, field);
        }
      }

    if (formProperties.getParentField() != null) {
      formModel.Data.put(formProperties.getParentField(), formProperties.getParentId());
      CustomLogger.alert(TAG, formProperties.getParentField() + "--" + formProperties.getParentId());
    }
    return this;
  }

  @Override
  protected void initFragment() {
    super.initFragment();
    intiWidgets();
  }

  private void intiWidgets() {
    LinearLayout root = (LinearLayout) getActivity().findViewById(R.id.fargmentForm);
    widgetFields = new WidgetHelper(root, formProperties.getWidgets()).build();
    setDataToWidget();
    if (formProperties.getRecordId() > 0)
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
    request.data.put("Id", formProperties.getRecordId());
    CustomLogger.info(TAG, "ID" + formProperties.getRecordId());

    for (AbstractWidget w : widgetFields) {
      if (!w.getField().equals("null")) {
        CustomLogger.info(TAG, w.getField() + w.getValue());
        request.data.put(w.getField(), w.getValue());
      }
    }
    //TODO: stringler dinamik olmalı
    new ThreadWebApiPost<UpdateRequestModel>(this, request, webApiAddress).execute();
    ((MainActivity) getActivity()).showProgress("Kaydediliyor");
  }

  @Override
  public void onPrepareOptionsMenu(Menu menu) {
    menu.clear();
    for (String button : formProperties.getButtons())
      menu = MenuButtonBuilder.getMenuButtons(this, menu, button);
  }

  @Override
  public void postResult(String data) {
    // TODO: 25.1.2017 data model boş yada hatalı gelebilir kontrolünü yap
    try {
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
      if (!w.getField().equals("null") && formModel.Data.get(w.getField().toString()) != null) {
        w.setValue(formModel.Data.get(w.getField().toString()).toString());
        CustomLogger.alert(TAG, formModel.Data.get(w.getField().toString()).toString());
      }
    }
  }

  public void uploadFile() {
    ((MainActivity) getActivity()).showMessage("dosyaGonder");
    // TODO: 27.1.2017 erkan abi dosya alan apiyi yazacak, ona göre burayı yaz
  }
}
