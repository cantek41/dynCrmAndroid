package veribis.veribiscrmdyn.Widgets;

/**
 * Created by Cantekin on 23.1.2017.
 */
public class WidgetProperties {
  private String label;
  private String field;
  private EnumWidgetTypes widgetType;

  public WidgetProperties(EnumWidgetTypes widgetType, String label,String field) {
    this.widgetType=widgetType;
    this.label=label;
    this.field=field;
  }

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public EnumWidgetTypes getWidgetType() {
    return widgetType;
  }

  public void setWidgetType(EnumWidgetTypes widgetType) {
    this.widgetType = widgetType;
  }
}
