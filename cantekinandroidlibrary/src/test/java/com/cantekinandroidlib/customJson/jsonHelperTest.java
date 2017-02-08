package com.cantekinandroidlib.customJson;

import com.cantekinandroidlib.customJson.Data.EnumFragmentType;
import com.cantekinandroidlib.customJson.Data.EnumMenuItem;
import com.cantekinandroidlib.customJson.Data.FormProperties;
import com.cantekinandroidlib.customJson.Data.ListProperties;
import com.cantekinandroidlib.customJson.Data.MenuGroupModel;
import com.cantekinandroidlib.customJson.Data.MenuItemModel;
import com.cantekinandroidlib.customJson.Data.MenuModel;
import com.cantekinandroidlib.logger.CustomLogger;
import com.cantekinandroidlib.logger.EnumLogType;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Cantekin on 6.2.2017.
 */
public class jsonHelperTest {
    @Test
    public void menuJsonTest() throws Exception {
        MenuModel menuModel = new MenuModel();

        List<MenuGroupModel> groups = new ArrayList<MenuGroupModel>();
        List<MenuItemModel> items = new ArrayList<MenuItemModel>();
        MenuItemModel item = new MenuItemModel();
        item.setName("Mesai");
        item.setLink("MesaiFormu");
        item.setIcon("ic_menu_share");
        item.setType(EnumMenuItem.FORM);
        items.add(item);

        item = new MenuItemModel();
        item.setName("Mesai");
        item.setLink("MesaiFormu");
        item.setIcon("ic_menu_share");
        item.setType(EnumMenuItem.FORM);
        items.add(item);

        MenuGroupModel grup = new MenuGroupModel();
        grup.setName("Aktivite");
        grup.setData(items);

        groups.add(grup);

        grup = new MenuGroupModel();
        grup.setName("Firma");
        grup.setData(items);
        groups.add(grup);
        menuModel.setGroup(groups);
        CustomLogger.setLogType(EnumLogType.SYSTEM);
        CustomLogger.alert("Menu Json= ", jsonHelper.objectToJson(menuModel));
    }

    @Test
    public void listJSonTest() throws Exception {
        ListProperties listProperties = new ListProperties();
        listProperties.setFormName("uniq form name");
        listProperties.setFormTitle("Form Title");

        listProperties.setParentField("Priority");
        listProperties.setEntity("Activity");
        listProperties.setFormType(EnumFragmentType.LIST);

        listProperties.setActionButtonFromType(EnumFragmentType.FORM);
        listProperties.setActionButtonIsVisible(true);
        listProperties.setActionButtonLink("uniq form name");

        listProperties.setEditLink("uniq form name");
        listProperties.setEditFormType(EnumFragmentType.FORM);

        listProperties.setListPageSize(10);

        ArrayList<String> Buttons = new ArrayList<String>();
        Buttons.add("SAVE");
        Buttons.add("CANCEL");
        Buttons.add("ATTACH");
        listProperties.setButtons(Buttons);


        ArrayList<Map<String, Object>> widgets = new ArrayList<>();

        Map<String, Object> widget = new HashMap<>();
        widget.put("label", "Description");
        widget.put("field", "Description");
        widgets.add(widget);

        listProperties.setWidgets(widgets);

        CustomLogger.setLogType(EnumLogType.SYSTEM);
        CustomLogger.alert("List Json= ", jsonHelper.objectToJson(listProperties));
    }

    @Test
    public void formJsonTest() throws Exception {
        FormProperties formProperties = new FormProperties();

        formProperties.setFormName("uniq form name");
        formProperties.setFormTitle("uTitle");
        formProperties.setFormType(EnumFragmentType.FORM);

        formProperties.setEntity("Activity");

        formProperties.setParentField("CompanyId");

        formProperties.setActionButtonIsVisible(false);
        formProperties.setActionButtonLink("uniq Form name");
        formProperties.setActionButtonFromType(EnumFragmentType.FORM);


        ArrayList<Map<String, Object>> widgets = new ArrayList<Map<String, Object>>();
        ArrayList<String> Buttons = new ArrayList<String>();
        Buttons.add("SAVE");
        Buttons.add("CANCEL");
        Buttons.add("ATTACH");
        formProperties.setButtons(Buttons);
        Map<String, Object> widget = new HashMap<String, Object>();
        widget.put("label", "Description");
        widget.put("field", "Description");
        widget.put("widgetType", "EDIT");
        widget.put("buttons", null);
        widgets.add(widget);
        widget = new HashMap<String, Object>();
        widget.put("label", "Subject");
        widget.put("field", "Subject");
        widget.put("widgetType", "EDIT");
        widget.put("buttons", null);
        ArrayList<String> btn = new ArrayList<String>();
        btn.add("CALL");
        btn.add("LOCATION");
        widget.put("buttons", btn);

        widgets.add(widget);
        widget = new HashMap<String, Object>();
        widget.put("label", "Note");
        widget.put("field", "Note");
        widget.put("widgetType", "EDIT");
        widgets.add(widget);

        widget = new HashMap<String, Object>();
        widget.put("subForm", "Form1");
        widget.put("label", "AltForm");
        widget.put("widgetType", "SUBFORM");
        widget.put("subFormType", "LIST");

        widgets.add(widget);

        widget = new HashMap<String, Object>();
        widget.put("subForm", "Form2");
        widget.put("label", "AltForm");
        widget.put("widgetType", "SUBFORM");
        widget.put("subFormType", "FORM");

        widgets.add(widget);

        formProperties.setWidgets(widgets);

        CustomLogger.setLogType(EnumLogType.SYSTEM);
        CustomLogger.alert("Form Json= ", jsonHelper.objectToJson(formProperties));
    }


}