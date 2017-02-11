package veribis.veribiscrmdyn;


import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestRunner;
import android.test.mock.MockContext;

import com.cantekinandroidlib.customJson.jsonHelper;
import com.cantekinandroidlib.webApi.ThreadWebApiPost;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Data.MyPreference;
import Model.Form.baseProperties;
import Model.UpdateRequestModel;
import veribis.veribiscrmdyn.Fragment.EnumFragmentType;
import veribis.veribiscrmdyn.Menu.Data.EnumMenuItem;
import veribis.veribiscrmdyn.Menu.Data.MenuGroupModel;
import veribis.veribiscrmdyn.Menu.Data.MenuItemModel;
import veribis.veribiscrmdyn.Menu.Data.MenuModel;

import static org.junit.Assert.*;

/**
 * Created by Cantekin on 10.2.2017.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyPreferenceTest {
    private MainActivity mainActivity;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void setActivity() {
        mainActivity = mActivityRule.getActivity();
    }

    @Test(expected = NullPointerException.class)
    public void preferanceNullDataTest() {
        MyPreference.getPreference(mainActivity.getApplicationContext()).setData(null, null);
    }

    @Test
    public void preferanceNewMenuDataTest() {
        MenuModel menuModel = new MenuModel();
        List<MenuGroupModel> groups = new ArrayList<>();
        List<MenuItemModel> items = new ArrayList<>();
        MenuItemModel item;
        MenuGroupModel grup = new MenuGroupModel();
        grup.setName("Firma");

        item = new MenuItemModel();
        item.setName("Firma");
        item.setLink("FirmaListe");
        item.setIcon("ic_menu_share");
        item.setType(EnumMenuItem.LIST);
        items.add(item);

        grup.setData(items);
        groups.add(grup);

        grup = new MenuGroupModel();
        items = new ArrayList<>();
        grup.setName("Kişiler");
        groups.add(grup);

        item = new MenuItemModel();
        item.setName("KisiListe");
        item.setLink("KisiListe");
        item.setIcon("ic_menu_share");
        item.setType(EnumMenuItem.LIST);
        items.add(item);

        grup.setData(items);


        grup = new MenuGroupModel();
        items = new ArrayList<>();
        grup.setName("Aktiviteler");
        groups.add(grup);

        item = new MenuItemModel();
        item.setName("AktiviteListe");
        item.setLink("AktiviteListe");
        item.setIcon("ic_menu_share");
        item.setType(EnumMenuItem.LIST);
        items.add(item);


        item = new MenuItemModel();
        item.setName("İş Başı");
        item.setLink("IsBasi");
        item.setIcon("ic_menu_share");
        item.setType(EnumMenuItem.LIST);
        items.add(item);

        item = new MenuItemModel();
        item.setName("Gun Sonu");
        item.setLink("GunSonu");
        item.setIcon("ic_menu_share");
        item.setType(EnumMenuItem.LIST);
        items.add(item);

        item = new MenuItemModel();
        item.setName("Id Okut");
        item.setLink("IdOkut");
        item.setIcon("ic_menu_share");
        item.setType(EnumMenuItem.LIST);
        items.add(item);
        grup.setData(items);

        grup = new MenuGroupModel();
        items = new ArrayList<>();
        grup.setName("Siparişler");



        item = new MenuItemModel();
        item.setName("Listele");
        item.setLink("SiparisListele");
        item.setIcon("ic_menu_share");
        item.setType(EnumMenuItem.LIST);

        items.add(item);
        grup.setData(items);
        groups.add(grup);
        menuModel.setGroup(groups);
        Context context = mainActivity.getApplicationContext();
        MyPreference.getPreference(context).setData("menu", jsonHelper.objectToJson(menuModel));
        MenuModel menuModel2 = MyPreference.getPreference(context).getMenu();
        Assert.assertNotNull(menuModel2);
    }

    @Test
    public void demoListmDataTest() {
        baseProperties formProperties = getDemoView.getMesaiList();
        Context context = mainActivity.getApplicationContext();
        MyPreference.getPreference(context).setData(formProperties.getFormName(), jsonHelper.objectToJson(formProperties));
        baseProperties form = MyPreference.getPreference(context).getData(formProperties.getFormName(), baseProperties.class);
        Assert.assertNotNull(form);
    }

    @Test
    public void demoFormDataTest() {
        baseProperties formProperties = getDemoView.getMesaiForm();
        Context context = mainActivity.getApplicationContext();
        MyPreference.getPreference(context).setData(formProperties.getFormName(), jsonHelper.objectToJson(formProperties));
        baseProperties form = MyPreference.getPreference(context).getData(formProperties.getFormName(), baseProperties.class);
        Assert.assertNotNull(form);
    }

}

class getDemoView {
    public static baseProperties getMesaiList() {
        baseProperties listProperties = new baseProperties();
        listProperties.setFormName("FirmaListe");
        listProperties.setFormTitle("Firmalar");
        listProperties.setEntity("Company");
        listProperties.setFormType(EnumFragmentType.LIST);

        //listProperties.setParentField("Priority");//ilişikili alan

        listProperties.setActionButtonFromType(EnumFragmentType.FORM);
        listProperties.setActionButtonIsVisible(true);
        listProperties.setActionButtonLink("FirmaEkle");

        listProperties.setEditLink("FirmaEkle");
        listProperties.setEditFormType(EnumFragmentType.FORM);

        listProperties.setListPageSize(10);


        ArrayList<Map<String, Object>> widgets = new ArrayList<>();

        Map<String, Object> widget = new HashMap<>();
        widget.put("label", "Firma Adı");
        widget.put("field", "Name");
        widget.put("widgetType", "TEXT");
        widgets.add(widget);

        widget = new HashMap<>();
        widget.put("label", "Id");
        widget.put("field", "Id");
        widget.put("widgetType", "TEXT");
        widgets.add(widget);

        widget = new HashMap<>();
        widget.put("label", "Tel");
        widget.put("field", "CompanyPhone");
        widget.put("widgetType", "TEXT");
        widgets.add(widget);

//        widget = new HashMap<>();
//        widget.put("label", "Subject");
//        widget.put("field", "Subject");
//        widget.put("widgetType", "TEXT");
//        widgets.add(widget);

        listProperties.setWidgets(widgets);
        return listProperties;
    }

    public static baseProperties getMesaiForm() {
        baseProperties formProperties = new baseProperties();
        formProperties.setFormName("FirmaEkle");
        formProperties.setFormTitle("Firma Ekle");
        formProperties.setFormType(EnumFragmentType.FORM);
        formProperties.setEntity("Company");

        formProperties.setActionButtonIsVisible(false);
        formProperties.setActionButtonLink("Mesai");
        formProperties.setActionButtonFromType(EnumFragmentType.FORM);
   //     formProperties.setParentField("Priority");//üstform ilişki

        ArrayList<Map<String, Object>> widgets = new ArrayList<Map<String, Object>>();
        ArrayList<String> Buttons = new ArrayList<String>();
        Buttons.add("SAVE");
        Buttons.add("CANCEL");
        Buttons.add("ATTACH");
        formProperties.setButtons(Buttons);
        Map<String, Object> widget = new HashMap<String, Object>();
        widget.put("label", "Firma Adı");
        widget.put("field", "Name");
        widget.put("widgetType", "EDITVIEW");
        widget.put("buttons", null);
        widgets.add(widget);

        widget = new HashMap<>();
        widget.put("label", "Lokasyon");
        widget.put("field", "Location");
        widget.put("widgetType", "EDITVIEW");
        widget.put("buttons", null);
        ArrayList<String> btn = new ArrayList<>();
        btn.add("LOCATION");
        widget.put("buttons", btn);

        widgets.add(widget);
        widget = new HashMap<String, Object>();
        widget.put("label", "Mail");
        widget.put("field", "Mail");
        widget.put("widgetType", "EDITVIEW");
        widgets.add(widget);

//        widget = new HashMap<String, Object>();
//        widget.put("subForm", "MesaiList");
//        widget.put("label", "AltForm");
//        widget.put("widgetType", "SUBFORM");
//        widget.put("subFormType", "LIST");
//
//        widgets.add(widget);
//
//        widget = new HashMap<String, Object>();
//        widget.put("subForm", "Mesai");
//        widget.put("label", "AltForm");
//        widget.put("widgetType", "SUBFORM");
//        widget.put("subFormType", "FORM");
//        widgets.add(widget);

        formProperties.setWidgets(widgets);

        return formProperties;
    }

}