package veribis.veribiscrmdyn.Fragment.Form;

import android.view.Menu;
import android.widget.LinearLayout;

import com.cantekinandroidlib.customJson.jsonHelper;
import com.cantekinandroidlib.logger.CustomLogger;
import com.cantekinandroidlib.webApi.IThreadDelegete;
import com.cantekinandroidlib.webApi.ThreadWebApiPost;
import com.cantekinandroidlib.webApi.ThreadWebApiPostFile;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import Data.MyPreference;
import Model.Form.DataModelForm;
import Model.Form.baseProperties;
import Model.UpdateRequestModel;

import veribis.veribiscrmdyn.Fragment.FragmentFactory;
import veribis.veribiscrmdyn.Fragment._baseFragment;
import veribis.veribiscrmdyn.MainActivity;
import veribis.veribiscrmdyn.Menu.MenuButtonBuilder;
import veribis.veribiscrmdyn.R;
import veribis.veribiscrmdyn.Widgets.AbstractWidget;
import veribis.veribiscrmdyn.Widgets.EnumEvetType;
import veribis.veribiscrmdyn.Widgets.WidgetHelper;


public class FormFragment extends _baseFragment implements IThreadDelegete {
    private static final String TAG = "FormFragment";
    private String wepApiSetAddress = MyPreference.getPreference(getContext()).getSetAddress();
    private String wepApiSaveFileAddress = MyPreference.getPreference(getContext()).getSaveFileAddress();
    private String webApiGetDAtaAddress = MyPreference.getPreference(getContext()).getGetAddress();
    List<AbstractWidget> widgetFields;//kaydet te gidecek datalar burada
    private DataModelForm formModel;
    private static final int REQUEST_FILE = 10001;
    private static final int REQUEST_READ = 10002;
    private static final int REQUEST_UPDATE = 10003;

    public FormFragment() {
        // Required empty public constructor
    }

    @Override
    public FormFragment setProp(baseProperties prop) {
        if (prop != null) this.formProperties = prop;
        LayoutId = R.layout.fragment_form;
        formModel = new DataModelForm();
        String field;
//        formModel.Data.put("Id", formProperties.getRecordId());
//        CustomLogger.alert(TAG, "ID===>" + formProperties.getRecordId());
        if (formProperties.getWidget().getWidgets() != null)
            for (Map<String, Object> w : formProperties.getWidget().getWidgets()) {
                field = String.valueOf(w.get("field"));
                if (!field.equals("null")) {
                    formModel.Data.put(field, "");
                    CustomLogger.alert(TAG, field);
                }
            }
        formModel.Data.put("Id", formProperties.getRecordId());
        CustomLogger.alert(TAG, "ID===>" + formProperties.getRecordId());
        if (formProperties.getParentField() != null) {
            formModel.Data.put(formProperties.getParentField(), formProperties.getParentFieldId());
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
        widgetFields = new WidgetHelper(root, formProperties.getWidget().getWidgets()).build();
        if (formProperties.getRecordId() != null) {
            setDataToWidget();
            getData();
        }
    }

    private void getData() {
        if (!isConnection())
            return;
        CustomLogger.alert(TAG, "ID" + formProperties.getRecordId());
        UpdateRequestModel request = new UpdateRequestModel();
        request.entity = formProperties.getEntity();
        request.Data = formModel.Data;

        new ThreadWebApiPost<UpdateRequestModel>(REQUEST_READ, this, request, webApiGetDAtaAddress).execute();
        ((MainActivity) getActivity()).showProgress("Data Getiriliyor");

    }


    @Override
    public void save() {
        if (!isConnection())
            return;
        UpdateRequestModel request = new UpdateRequestModel();
        request.entity = formProperties.getEntity();
        request.Data.put("Id", formProperties.getRecordId());
        CustomLogger.info(TAG, "ID" + formProperties.getRecordId());
        if (formProperties.getParentField() != null && formProperties.getParentFieldId() != null)
            request.Data.put(formProperties.getParentField(), formProperties.getParentFieldId());


        for (AbstractWidget w : widgetFields) {
            if (!w.getField().equals("null")) {
                CustomLogger.info(TAG, w.getField() + w.getValue());
                request.Data.put(w.getField(), w.getValue());
            }
        }
        //TODO: stringler dinamik olmalı
        new ThreadWebApiPost<UpdateRequestModel>(REQUEST_UPDATE, this, request, wepApiSetAddress).execute();
        ((MainActivity) getActivity()).showProgress("Kaydediliyor");
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        for (String button : formProperties.getButtons())
            menu = MenuButtonBuilder.getMenuButtons(this, menu, button);
    }


    private void setDataToWidget() {
        for (AbstractWidget w : widgetFields) {
            if (!w.getField().equals("null") && formModel.Data.get(w.getField().toString()) != null) {
                w.setValue(formModel.Data.get(w.getField().toString()).toString());
                CustomLogger.alert(TAG, formModel.Data.get(w.getField().toString()).toString());
            }
        }
    }

    public void uploadFile(File file) {
        ((MainActivity) getActivity()).showMessage("dosyaGonder");

        byte bytes[] = new byte[(int) file.length()];
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(file));
            DataInputStream dis = new DataInputStream(bis);
            dis.readFully(bytes);
        } catch (FileNotFoundException e) {
            CustomLogger.error(TAG, "dosya Yok" + e.getMessage());

        } catch (IOException e) {
            e.printStackTrace();
            CustomLogger.error(TAG, "hata" + e.getMessage());
        }

        String addres = wepApiSaveFileAddress + "?TableName=" + formProperties.getEntity() + "&Id=" + formProperties.getRecordId();
        CustomLogger.alert(TAG, String.valueOf(bytes));
        CustomLogger.alert(TAG, file.getName());

        new ThreadWebApiPostFile(REQUEST_FILE, this, bytes, file.getName(), addres).execute();
        ((MainActivity) getActivity()).showProgress("Kaydediliyor");


        // TODO: 27.1.2017 erkan abi dosya alan apiyi yazacak, ona göre burayı yaz
    }

    public void onClickWidget(EnumEvetType subform, String formName, Object o) {
        CustomLogger.alert(String.valueOf(subform), formName);
        switch (subform) {
            case SUBFORM:
                // TODO: 30.1.2017 forma adına göre bulup getirecek
                if (formProperties.getRecordId() == null) {
                    ((MainActivity) getActivity()).showMessage("Önce Kaydetmelisiniz");
                } else {
                    baseProperties newProp = MyPreference.getPreference(getContext()).getData(formName, baseProperties.class);
                    if (newProp == null) {
                        ((MainActivity) getActivity()).showMessage("form bulunamadı");
                    } else {
                        newProp.setParentFieldId(formProperties.getRecordId());
                        ((MainActivity) getActivity()).showFragment(FragmentFactory.getFragment(newProp.getFormType()).setProp(newProp));
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void postResult(String data, int requestCode) {
        // TODO: 25.1.2017 Data model boş yada hatalı gelebilir kontrolünü yap
        switch (requestCode) {
            case REQUEST_READ:
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
                }
                break;
            case REQUEST_FILE:
                CustomLogger.error(TAG, data);
                if (!data.equals("200"))
                    ((MainActivity) getActivity()).showMessage("Yöneticinize Başvurun Hata Kodu:Dosya Yükleme Başarısız");

                break;
            case REQUEST_UPDATE:
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
                }
                break;
        }
        ((MainActivity) getActivity()).dismissProgress();
    }
}
