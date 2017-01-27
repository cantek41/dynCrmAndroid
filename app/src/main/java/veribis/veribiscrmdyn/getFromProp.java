package veribis.veribiscrmdyn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Model.Form.FormProperties;
import veribis.veribiscrmdyn.Widgets.EnumWidgetTypes;

/**
 * Created by Cantekin on 27.1.2017.
 */
public class getFromProp {
  public static FormProperties get() {
    FormProperties formProperties = new FormProperties();
    formProperties.setFormName("Form1");
    formProperties.setId(103);
    formProperties.setEntity("Activity");
    formProperties.widgets = new ArrayList<Map<String, Object>>();
    formProperties.Buttons = new ArrayList<String>();
    formProperties.Buttons.add("SAVE");
    formProperties.Buttons.add("CANCEL");
    formProperties.Buttons.add("ATTACH");
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
    return formProperties;
  }
}
