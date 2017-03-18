package Model.Form;

import java.util.ArrayList;
import java.util.Map;

public class Widget {
    private ArrayList<Map<String, Object>> widgets;

    public ArrayList<Map<String, Object>> getWidgets() {
        return widgets;
    }

    public void setWidgets(ArrayList<Map<String, Object>> widgets) {
        this.widgets = widgets;
    }
}
