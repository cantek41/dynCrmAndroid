package veribis.veribiscrmdyn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.Form.FormProperties;
import veribis.veribiscrmdyn.Fragment.EnumFragmentType;
import veribis.veribiscrmdyn.Widgets.EnumWidgetTypes;

/**
 * Created by Cantekin on 27.1.2017.
 */
public class getFromProp {
  public static FormProperties getList() {
    FormProperties formProperties = new FormProperties();
    formProperties.setFormName("Form1");

    formProperties.setParentField("Priority");
    formProperties.setEntity("Activity");
    formProperties.setFragmentType(EnumFragmentType.LIST);
    List<String> listFields = new ArrayList<String>();
    listFields.add("Id");
    listFields.add("Description");
    listFields.add("Subject");
    formProperties.setFields(listFields);
    return formProperties;
  }

  public static FormProperties get() {
    FormProperties formProperties = new FormProperties();
    formProperties.setFormName("Form1");
    formProperties.setRecordId(103);
    formProperties.setEntity("Activity");
    formProperties.setFragmentType(EnumFragmentType.FORM);
    ArrayList<Map<String, Object>> widgets = new ArrayList<Map<String, Object>>();
    ArrayList<String> Buttons = new ArrayList<String>();
    Buttons.add("SAVE");
    Buttons.add("CANCEL");
    Buttons.add("ATTACH");
    formProperties.setButtons(Buttons);
    Map<String, Object> widget = new HashMap<String, Object>();
    widget.put("Label", "Description");
    widget.put("Field", "Description");
    widget.put("WidgetType", EnumWidgetTypes.EDIT);
    widget.put("Buttons", null);
    widgets.add(widget);
    widget = new HashMap<String, Object>();
    widget.put("Label", "Subject");
    widget.put("Field", "Subject");
    widget.put("WidgetType", EnumWidgetTypes.EDIT);
    widget.put("Buttons", null);
    ArrayList<String> btn = new ArrayList<String>();
    btn.add("CALL");
    btn.add("LOCATION");
    widget.put("Buttons", btn);

    widgets.add(widget);
    widget = new HashMap<String, Object>();
    widget.put("Label", "Note");
    widget.put("Field", "Note");
    widget.put("WidgetType", EnumWidgetTypes.EDIT);
    widgets.add(widget);

    widget = new HashMap<String, Object>();
    widget.put("SubForm", "Form1");
    widget.put("Label", "AltForm");
    widget.put("WidgetType", EnumWidgetTypes.SUBFORM);
    widgets.add(widget);

    widget = new HashMap<String, Object>();
    widget.put("SubForm", "Form2");
    widget.put("Label", "AltForm");
    widget.put("WidgetType", EnumWidgetTypes.SUBFORM);
    widgets.add(widget);

    formProperties.setWidgets(widgets);
    return formProperties;
  }

  public static FormProperties getSubForm() {
    FormProperties formProperties = new FormProperties();
    formProperties.setFormName("Form1");
    formProperties.setParentField("Priority");
    formProperties.setRecordId(0);
    formProperties.setParentId(103);
    formProperties.setEntity("Activity");
    ArrayList<Map<String, Object>> widgets = new ArrayList<Map<String, Object>>();
    ArrayList<String> Buttons = new ArrayList<String>();
    Buttons.add("SAVE");
    Buttons.add("CANCEL");
    Buttons.add("ATTACH");
    formProperties.setButtons(Buttons);
    Map<String, Object> widget = new HashMap<String, Object>();
    widget.put("Label", "Description");
    widget.put("Field", "Description");
    widget.put("WidgetType", EnumWidgetTypes.EDIT);
    widget.put("Buttons", null);
    widgets.add(widget);
    widget = new HashMap<String, Object>();
    widget.put("Label", "Subject");
    widget.put("Field", "Subject");
    widget.put("WidgetType", EnumWidgetTypes.EDIT);
    widget.put("Buttons", null);
    ArrayList<String> btn = new ArrayList<String>();
    btn.add("CALL");
    btn.add("LOCATION");
    widget.put("Buttons", btn);

    widgets.add(widget);
    widget = new HashMap<String, Object>();
    widget.put("Label", "Note");
    widget.put("Field", "Note");
    widget.put("WidgetType", EnumWidgetTypes.EDIT);
    widgets.add(widget);

    widget = new HashMap<String, Object>();
    widget.put("Field", "Priority");
    widget.put("Label", "Priority");
    widget.put("WidgetType", EnumWidgetTypes.TEXT);
    widgets.add(widget);
    formProperties.setWidgets(widgets);
    return formProperties;
  }
}
