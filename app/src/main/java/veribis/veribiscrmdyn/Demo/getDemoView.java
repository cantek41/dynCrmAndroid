package veribis.veribiscrmdyn.Demo;

import android.content.Context;

import com.cantekinandroidlib.customJson.jsonHelper;
import com.cantekinandroidlib.logger.CustomLogger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Data.MyPreference;
import Model.Form.baseProperties;
import veribis.veribiscrmdyn.Fragment.EnumFragmentType;
import veribis.veribiscrmdyn.Menu.Data.EnumMenuItem;
import veribis.veribiscrmdyn.Menu.Data.MenuGroupModel;
import veribis.veribiscrmdyn.Menu.Data.MenuItemModel;
import veribis.veribiscrmdyn.Menu.Data.MenuModel;

/**
 * Created by Cantekin on 15.2.2017.
 */
public class getDemoView {
    public static void getMenu(Context context) {
        MenuModel menuModel = new MenuModel();
        List<MenuGroupModel> groups = new ArrayList<>();
        List<MenuItemModel> items = new ArrayList<>();
        MenuItemModel item;
        MenuGroupModel grup = new MenuGroupModel();
        grup.setLabel("Firma");

        item = new MenuItemModel();
        item.setLabel("Firmalar");
        item.setLink("FirmaListe");
        item.setIcon("ic_menu_share");

        items.add(item);

        grup.setData(items);
        groups.add(grup);

        grup = new MenuGroupModel();
        items = new ArrayList<>();
        grup.setLabel("Kişi");
        groups.add(grup);

        item = new MenuItemModel();
        item.setLabel("Kişiler");
        item.setLink("KisiListe");
        item.setIcon("ic_menu_share");

        items.add(item);

        grup.setData(items);


        grup = new MenuGroupModel();
        items = new ArrayList<>();
        grup.setLabel("Aktivite");
        groups.add(grup);

        item = new MenuItemModel();
        item.setLabel("Aktiviteler");
        item.setLink("AktiviteList");
        item.setIcon("ic_menu_share");

        items.add(item);


        item = new MenuItemModel();
        item.setLabel("İş Başı");
        item.setLink("IsBasi");
        item.setIcon("ic_menu_share");

        items.add(item);

        item = new MenuItemModel();
        item.setLabel("Gun Sonu");
        item.setLink("IsSonu");
        item.setIcon("ic_menu_share");

        items.add(item);

        item = new MenuItemModel();
        item.setLabel("Id Okut");
        item.setLink("IdOkut");
        item.setIcon("ic_menu_share");

        items.add(item);
        grup.setData(items);

        grup = new MenuGroupModel();
        items = new ArrayList<>();
        grup.setLabel("Siparişler");


        item = new MenuItemModel();
        item.setLabel("Listele");
        item.setLink("SiparisList");
        item.setIcon("ic_menu_share");


        items.add(item);
        grup.setData(items);
        groups.add(grup);
        menuModel.setGroup(groups);
     CustomLogger.alert("ornbek", jsonHelper.objectToJson(menuModel));
        MyPreference.getPreference(context).setData("menu", jsonHelper.objectToJson(menuModel));
    }

    public static void getFirmalarList(Context context) {
        baseProperties listProperties = new baseProperties();
        listProperties.setFormName("FirmaListe");
        listProperties.setFormTitle("Firmalar");
        listProperties.setEntity("Company");
        listProperties.setFormType(EnumFragmentType.LIST);

        listProperties.setSearchField("Name");
        listProperties.setSortField("Name");

        //listProperties.setParentField("Priority");//ilişikili alan

        listProperties.setActionButtonFromType(EnumFragmentType.FORM);
        listProperties.setActionButtonIsVisible(true);
        listProperties.setActionButtonLink("FirmaEkle");

        listProperties.setEditLink("FirmaEkle");
        listProperties.setEditFormType(EnumFragmentType.FORM);

        listProperties.setListPageSize(10);

        ArrayList<Map<String, Object>> widgets = new ArrayList<>();
        widgets.add(newWidget("Firma Adı", "Name", "TEXT", null, null, null));
        widgets.add(newWidget("Id", "Id", "TEXT", null, null, null));
        widgets.add(newWidget("Mail", "Mail", "TEXT", null, null, null));
        widgets.add(newWidget("Web", "Web", "TEXT", null, null, null));

        listProperties.setWidgets(widgets);
        CustomLogger.alert("ornbek", jsonHelper.objectToJson(listProperties));

        MyPreference.getPreference(context).setData(listProperties.getFormName(), jsonHelper.objectToJson(listProperties));

    }

    public static void getFirmaForm(Context context) {
        baseProperties formProperties = new baseProperties();
        formProperties.setFormName("FirmaEkle");
        formProperties.setFormTitle("Firma Ekle");
        formProperties.setFormType(EnumFragmentType.FORM);
        formProperties.setEntity("Company");

        formProperties.setActionButtonIsVisible(false);

        //     formProperties.setParentField("Priority");//üstform ilişki

        ArrayList<Map<String, Object>> widgets = new ArrayList<Map<String, Object>>();
        ArrayList<String> Buttons = new ArrayList<>();
        Buttons.add("SAVE");
        Buttons.add("CANCEL");
        Buttons.add("ATTACH");
        formProperties.setButtons(Buttons);

        widgets.add(newWidget("Firma Adı", "Name", "DATEPICKER", null, null, null));
        widgets.add(newWidget("Tarih", "CreatedDate", "DATEPICKER", null, null, null));
        widgets.add(newWidget("Lokasyon", "Location", "TEXTVIEW", "LOCATION", null, null));
        widgets.add(newWidget("Mail", "Mail", "TEXTVIEW", "SEND_MAIL", null, null));
        widgets.add(newWidget("Kişiler", null, "SUBFORM", null, "KisiListe", "LIST"));
        widgets.add(newWidget("Aktiviteler", null, "SUBFORM", null, "AktiviteList", "LIST"));
        widgets.add(newWidget("Siparişler", null, "SUBFORM", null, "SiparisList", "LIST"));

        formProperties.setWidgets(widgets);
        CustomLogger.alert("ornbek", jsonHelper.objectToJson(formProperties));

        MyPreference.getPreference(context).setData(formProperties.getFormName(), jsonHelper.objectToJson(formProperties));
    }

    public static void getKisilerList(Context context) {
        baseProperties listProperties = new baseProperties();
        listProperties.setFormName("KisiListe");
        listProperties.setFormTitle("Kişiler");
        listProperties.setEntity("Contact");
        listProperties.setFormType(EnumFragmentType.LIST);

        listProperties.setSearchField("Name");
        listProperties.setSortField("Name");

        listProperties.setParentField("CompanyId");//ilişikili alan

        listProperties.setActionButtonFromType(EnumFragmentType.FORM);
        listProperties.setActionButtonIsVisible(true);
        listProperties.setActionButtonLink("KisiEkle");

        listProperties.setEditLink("KisiEkle");
        listProperties.setEditFormType(EnumFragmentType.FORM);

        listProperties.setListPageSize(10);


        ArrayList<Map<String, Object>> widgets = new ArrayList<>();

        widgets.add(newWidget("Adı", "Name", "EDITVIEW", null, null, null));
        widgets.add(newWidget("Soyad", "SurName", "EDITVIEW", null, null, null));
        widgets.add(newWidget("Id", "Id", "EDITVIEW", null, null, null));
        widgets.add(newWidget("Mail", "Mail", "EDITVIEW", null, null, null));


        listProperties.setWidgets(widgets);
        MyPreference.getPreference(context).setData(listProperties.getFormName(), jsonHelper.objectToJson(listProperties));

    }

    public static void getKisiForm(Context context) {
        baseProperties formProperties = new baseProperties();
        formProperties.setFormName("KisiEkle");
        formProperties.setFormTitle("Kişi Ekle");
        formProperties.setFormType(EnumFragmentType.FORM);
        formProperties.setEntity("Contact");

        formProperties.setActionButtonIsVisible(false);

        formProperties.setParentField("CompanyId");//üstform ilişki

        ArrayList<Map<String, Object>> widgets = new ArrayList<>();
        ArrayList<String> Buttons = new ArrayList<>();
        Buttons.add("SAVE");
        Buttons.add("CANCEL");
        formProperties.setButtons(Buttons);


        widgets.add(newWidget("Ad", "Name", "EDITVIEW", null, null, null));
        widgets.add(newWidget("Soyad", "SurName", "EDITVIEW", null, null, null));
        widgets.add(newWidget("Email", "Mail", "EDITVIEW", "SEND_MAIL", null, null));

        formProperties.setWidgets(widgets);

        MyPreference.getPreference(context)
                .setData(formProperties.getFormName(),
                        jsonHelper.objectToJson(formProperties));
    }

    public static void getAktiviteList(Context context) {
        baseProperties listProperties = new baseProperties();
        listProperties.setFormName("AktiviteList");
        listProperties.setFormTitle("Aktiviteler");
        listProperties.setEntity("Activity");
        listProperties.setFormType(EnumFragmentType.LIST);

        listProperties.setSearchField("Subject");
        listProperties.setSortField("Subject");

        listProperties.setParentField("CompanyId");//ilişikili alan

        listProperties.setActionButtonFromType(EnumFragmentType.FORM);
        listProperties.setActionButtonIsVisible(true);
        listProperties.setActionButtonLink("AktiviteEkle");

        listProperties.setEditLink("AktiviteEkle");
        listProperties.setEditFormType(EnumFragmentType.FORM);

        listProperties.setListPageSize(10);


        ArrayList<Map<String, Object>> widgets = new ArrayList<>();

        widgets.add(newWidget("Konu", "Subject", "TEXT", null, null, null));
        widgets.add(newWidget("Id", "Id", "TEXT", null, null, null));
        widgets.add(newWidget("Tarih", "StartDate", "TEXT", null, null, null));
        widgets.add(newWidget("Not", "Note", "TEXT", null, null, null));

        listProperties.setWidgets(widgets);
        MyPreference.getPreference(context).setData(listProperties.getFormName(), jsonHelper.objectToJson(listProperties));

    }

    public static void getAktivite(Context context) {
        baseProperties formProperties = new baseProperties();
        formProperties.setFormName("AktiviteEkle");
        formProperties.setFormTitle("Aktivite Ekle");
        formProperties.setFormType(EnumFragmentType.FORM);
        formProperties.setEntity("Activity");

        formProperties.setActionButtonIsVisible(false);

        formProperties.setParentField("CompanyId");//üstform ilişki

        ArrayList<Map<String, Object>> widgets = new ArrayList<>();
        ArrayList<String> Buttons = new ArrayList<>();
        Buttons.add("SAVE");
        Buttons.add("CANCEL");
        formProperties.setButtons(Buttons);

        widgets.add(newWidget("Konu", "Subject", "EDITVIEW", null, null, null));
        widgets.add(newWidget("Not", "Note", "EDITVIEW", null, null, null));
        widgets.add(newWidget("Tarih", "StartDate", "EDITVIEW", null, null, null));
        widgets.add(newWidget("Yer", "Note", "TEXTVIEW", "BARCODE", null, null));

        formProperties.setWidgets(widgets);

        MyPreference.getPreference(context)
                .setData(formProperties.getFormName(),
                        jsonHelper.objectToJson(formProperties));
    }

    public static void getIsBasi(Context context) {
        baseProperties formProperties = new baseProperties();
        formProperties.setFormName("IsBasi");
        formProperties.setFormTitle("İş Başı");
        formProperties.setFormType(EnumFragmentType.FORM);
        formProperties.setEntity("Activity");

        formProperties.setActionButtonIsVisible(false);

        formProperties.setParentField("CompanyId");//üstform ilişki

        ArrayList<Map<String, Object>> widgets = new ArrayList<>();
        ArrayList<String> Buttons = new ArrayList<>();
        Buttons.add("SAVE");
        Buttons.add("CANCEL");
        formProperties.setButtons(Buttons);

        widgets.add(newWidget("Tarih", "StartDate", "EDITVIEW", null, null, null));
        widgets.add(newWidget("Yer", "Note", "TEXTVIEW", "BARCODE", null, null));

        formProperties.setWidgets(widgets);

        MyPreference.getPreference(context)
                .setData(formProperties.getFormName(),
                        jsonHelper.objectToJson(formProperties));
    }

    public static void getIsSonu(Context context) {
        baseProperties formProperties = new baseProperties();
        formProperties.setFormName("IsSonu");
        formProperties.setFormTitle("İş Sonu");
        formProperties.setFormType(EnumFragmentType.FORM);
        formProperties.setEntity("Activity");

        formProperties.setActionButtonIsVisible(false);

        formProperties.setParentField("CompanyId");//üstform ilişki

        ArrayList<Map<String, Object>> widgets = new ArrayList<>();
        ArrayList<String> Buttons = new ArrayList<>();
        Buttons.add("SAVE");
        Buttons.add("CANCEL");
        formProperties.setButtons(Buttons);

        widgets.add(newWidget("Tarih", "StartDate", "EDITVIEW", null, null, null));
        widgets.add(newWidget("Yer", "Note", "TEXTVIEW", "BARCODE", null, null));

        formProperties.setWidgets(widgets);

        MyPreference.getPreference(context)
                .setData(formProperties.getFormName(),
                        jsonHelper.objectToJson(formProperties));
    }

    public static void getIdOkut(Context context) {
        baseProperties formProperties = new baseProperties();
        formProperties.setFormName("IdOkut");
        formProperties.setFormTitle("Id Okut");
        formProperties.setFormType(EnumFragmentType.FORM);
        formProperties.setEntity("Activity");

        formProperties.setActionButtonIsVisible(false);

        formProperties.setParentField("CompanyId");//üstform ilişki

        ArrayList<Map<String, Object>> widgets = new ArrayList<>();
        ArrayList<String> Buttons = new ArrayList<>();
        Buttons.add("SAVE");
        Buttons.add("CANCEL");
        formProperties.setButtons(Buttons);

        widgets.add(newWidget("Tarih", "StartDate", "EDITVIEW", null, null, null));
        widgets.add(newWidget("Yer", "Note", "TEXTVIEW", "BARCODE", null, null));

        formProperties.setWidgets(widgets);

        MyPreference.getPreference(context)
                .setData(formProperties.getFormName(),
                        jsonHelper.objectToJson(formProperties));
    }

    public static void getSiperisList(Context context) {
        baseProperties listProperties = new baseProperties();
        listProperties.setFormName("SiparisList");
        listProperties.setFormTitle("Siperisler");
        listProperties.setEntity("Document");
        listProperties.setFormType(EnumFragmentType.LIST);

        listProperties.setSearchField("Subject");
        listProperties.setSortField("Subject");

        listProperties.setParentField("CompanyId");//ilişikili alan

        listProperties.setActionButtonFromType(EnumFragmentType.FORM);
        listProperties.setActionButtonIsVisible(true);
        listProperties.setActionButtonLink("SiparisEkle");

        listProperties.setEditLink("SiparisEkle");
        listProperties.setEditFormType(EnumFragmentType.FORM);

        listProperties.setListPageSize(10);


        ArrayList<Map<String, Object>> widgets = new ArrayList<>();

        widgets.add(newWidget("Konu", "Subject", "TEXT", null, null, null));
        widgets.add(newWidget("Id", "Id", "TEXT", null, null, null));
        widgets.add(newWidget("Tarih", "DocumentDate", "TEXT", null, null, null));

        listProperties.setWidgets(widgets);
        MyPreference.getPreference(context).setData(listProperties.getFormName(), jsonHelper.objectToJson(listProperties));

    }

    public static void getSiperis(Context context) {
        baseProperties formProperties = new baseProperties();
        formProperties.setFormName("SiparisEkle");
        formProperties.setFormTitle("Sipariş Ekle");
        formProperties.setFormType(EnumFragmentType.FORM);
        formProperties.setEntity("Document");

        formProperties.setActionButtonIsVisible(false);

        formProperties.setParentField("CompanyId");//üstform ilişki

        ArrayList<Map<String, Object>> widgets = new ArrayList<>();
        ArrayList<String> Buttons = new ArrayList<>();
        Buttons.add("SAVE");
        Buttons.add("CANCEL");
        formProperties.setButtons(Buttons);

        widgets.add(newWidget("Konu", "Subject", "EDITVIEW", null, null, null));
        widgets.add(newWidget("Tarih", "DocumentDate", "EDITVIEW", null, null, null));

        formProperties.setWidgets(widgets);

        MyPreference.getPreference(context)
                .setData(formProperties.getFormName(),
                        jsonHelper.objectToJson(formProperties));
    }

    private static Map<String, Object> newWidget(String label, String field, String wType, String wbtn, String subForm, String SubFormType) {
        Map<String, Object> widget = new HashMap<>();
        widget.put("label", label);
        if (field != null)
            widget.put("field", field);
        widget.put("widgetType", wType);
        widget.put("subForm", subForm);
        widget.put("subFormType", SubFormType);
        if (wbtn != null) {
            ArrayList<String> btn = new ArrayList<>();
            btn.add(wbtn);
            widget.put("buttons", btn);
        }
        return widget;
    }
}
