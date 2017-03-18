package veribis.veribiscrmdyn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Model.Form.baseProperties;
import veribis.veribiscrmdyn.Fragment.EnumFragmentType;

/**
 * Created by Cantekin on 27.1.2017.
 */
public class getFromProp {
  public static baseProperties getListNoFilter() {
//    FormProperties formProperties = new FormProperties();
//    formProperties.setFormName("Form1");
//
//    formProperties.setParentField("Priority");
//    formProperties.setEntity("Activity");
//    formProperties.setFragmentType(EnumFragmentType.LIST);
//    List<String> listFields = new ArrayList<String>();
//    listFields.add("Id");
//    listFields.add("Description");
//    listFields.add("Subject");
//    formProperties.setFields(listFields);
    baseProperties listProperties = new baseProperties();
    listProperties.setFormName("uniq form name");
    listProperties.setFormTitle("Form Title");
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
    widget.put("widgetType", "TEXT");
    widgets.add(widget);

    widget = new HashMap<>();
    widget.put("label", "Not");
    widget.put("field", "Id");
    widget.put("widgetType", "TEXT");
    widgets.add(widget);

    widget = new HashMap<>();
    widget.put("label", "Not");
    widget.put("field", "Note");
    widget.put("widgetType", "TEXT");
    widgets.add(widget);

 //   listProperties.setWidgets(widgets);
    return listProperties;
  }
  public static baseProperties getList() {
//    FormProperties formProperties = new FormProperties();
//    formProperties.setFormName("Form1");
//
//    formProperties.setParentField("Priority");
//    formProperties.setEntity("Activity");
//    formProperties.setFragmentType(EnumFragmentType.LIST);
//    List<String> listFields = new ArrayList<String>();
//    listFields.add("Id");
//    listFields.add("Description");
//    listFields.add("Subject");
//    formProperties.setFields(listFields);
    baseProperties listProperties = new baseProperties();
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
    widget.put("widgetType", "TEXT");
    widgets.add(widget);

  //  listProperties.setWidgets(widgets);
    return listProperties;
  }

  public static baseProperties get() {
//    FormProperties formProperties = new FormProperties();
//    formProperties.setFormName("Form1");
//    formProperties.setRecordId(103);
//    formProperties.setEntity("Activity");
//    formProperties.setFragmentType(EnumFragmentType.FORM);
//    ArrayList<Map<String, Object>> widgets = new ArrayList<Map<String, Object>>();
//    ArrayList<String> Buttons = new ArrayList<String>();
//    Buttons.add("SAVE");
//    Buttons.add("CANCEL");
//    Buttons.add("ATTACH");
//    formProperties.setButtons(Buttons);
//    Map<String, Object> widget = new HashMap<String, Object>();
//    widget.put("Label", "Description");
//    widget.put("Field", "Description");
//    widget.put("WidgetType", EnumWidgetTypes.EDIT);
//    widget.put("Buttons", null);
//    widgets.add(widget);
//    widget = new HashMap<String, Object>();
//    widget.put("Label", "Subject");
//    widget.put("Field", "Subject");
//    widget.put("WidgetType", EnumWidgetTypes.EDIT);
//    widget.put("Buttons", null);
//    ArrayList<String> btn = new ArrayList<String>();
//    btn.add("CALL");
//    btn.add("LOCATION");
//    widget.put("Buttons", btn);
//
//    widgets.add(widget);
//    widget = new HashMap<String, Object>();
//    widget.put("Label", "Note");
//    widget.put("Field", "Note");
//    widget.put("WidgetType", EnumWidgetTypes.EDIT);
//    widgets.add(widget);
//
//    widget = new HashMap<String, Object>();
//    widget.put("SubForm", "Form1");
//    widget.put("Label", "AltForm");
//    widget.put("WidgetType", EnumWidgetTypes.SUBFORM);
//    widgets.add(widget);
//
//    widget = new HashMap<String, Object>();
//    widget.put("SubForm", "Form2");
//    widget.put("Label", "AltForm");
//    widget.put("WidgetType", EnumWidgetTypes.SUBFORM);
//    widgets.add(widget);
//
//    formProperties.setWidgets(widgets);
//    return formProperties;
//  }
//
//  public static FormProperties getSubForm() {
//    FormProperties formProperties = new FormProperties();
//    formProperties.setFormName("Form1");
//    formProperties.setParentField("Priority");
//    formProperties.setRecordId(0);
//    formProperties.setParentId(103);
//    formProperties.setEntity("Activity");
//    ArrayList<Map<String, Object>> widgets = new ArrayList<Map<String, Object>>();
//    ArrayList<String> Buttons = new ArrayList<String>();
//    Buttons.add("SAVE");
//    Buttons.add("CANCEL");
//    Buttons.add("ATTACH");
//    formProperties.setButtons(Buttons);
//    Map<String, Object> widget = new HashMap<String, Object>();
//    widget.put("Label", "Description");
//    widget.put("Field", "Description");
//    widget.put("WidgetType", EnumWidgetTypes.EDIT);
//    widget.put("Buttons", null);
//    widgets.add(widget);
//    widget = new HashMap<String, Object>();
//    widget.put("Label", "Subject");
//    widget.put("Field", "Subject");
//    widget.put("WidgetType", EnumWidgetTypes.EDIT);
//    widget.put("Buttons", null);
//    ArrayList<String> btn = new ArrayList<String>();
//    btn.add("CALL");
//    btn.add("LOCATION");
//    widget.put("Buttons", btn);
//
//    widgets.add(widget);
//    widget = new HashMap<String, Object>();
//    widget.put("Label", "Note");
//    widget.put("Field", "Note");
//    widget.put("WidgetType", EnumWidgetTypes.EDIT);
//    widgets.add(widget);
//
//    widget = new HashMap<String, Object>();
//    widget.put("Field", "Priority");
//    widget.put("Label", "Priority");
//    widget.put("WidgetType", EnumWidgetTypes.TEXT);
//    widgets.add(widget);
//    formProperties.setWidgets(widgets);
    baseProperties formProperties = new baseProperties();

    formProperties.setFormName("uniq form name");
    formProperties.setFormTitle("uTitle");
    formProperties.setFormType(EnumFragmentType.FORM);

    formProperties.setEntity("Activity");
    formProperties.setRecordId("103");

    formProperties.setParentField("Priority");

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
    widget.put("widgetType", "EDITVIEW");
    widget.put("buttons", null);
    widgets.add(widget);
    widget = new HashMap<String, Object>();
    widget.put("label", "Subject");
    widget.put("field", "Subject");
    widget.put("widgetType", "EDITVIEW");
    widget.put("buttons", null);
    ArrayList<String> btn = new ArrayList<String>();
    btn.add("CALL");
    btn.add("LOCATION");
    widget.put("buttons", btn);

    widgets.add(widget);
    widget = new HashMap<String, Object>();
    widget.put("label", "Note");
    widget.put("field", "Note");
    widget.put("widgetType", "EDITVIEW");
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

   // formProperties.setWidgets(widgets);

    return formProperties;
  }
  public static baseProperties getSubForm()
  {
    baseProperties formProperties = new baseProperties();
    formProperties.setFormName("uniq form name");
    formProperties.setFormTitle("uTitle");
    formProperties.setFormType(EnumFragmentType.FORM);

    formProperties.setEntity("Activity");
    formProperties.setRecordId("0");

    formProperties.setParentField("Priority");

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
    widget.put("widgetType", "EDITVIEW");
    widget.put("buttons", null);
    widgets.add(widget);
    widget = new HashMap<String, Object>();
    widget.put("label", "Subject");
    widget.put("field", "Subject");
    widget.put("widgetType", "EDITVIEW");
    widget.put("buttons", null);
    ArrayList<String> btn = new ArrayList<String>();
    btn.add("CALL");
    btn.add("LOCATION");
    widget.put("buttons", btn);

    widgets.add(widget);
    widget = new HashMap<String, Object>();
    widget.put("label", "Note");
    widget.put("field", "Note");
    widget.put("widgetType", "EDITVIEW");
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

    //formProperties.setWidgets(widgets);

    return formProperties;
  }
}
