package veribis.veribiscrmdyn.Menu.Data;

import java.util.List;

public class MenuGroupModel {
    private String label;
    private Row data;
    private String name;
    private String icon;
    private String widgetType;

    public Row getData() {
        return data;
    }

    public void setData(Row data) {
        this.data = data;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getWidgetType() {
        return widgetType;
    }

    public void setWidgetType(String widgetType) {
        this.widgetType = widgetType;
    }

}

