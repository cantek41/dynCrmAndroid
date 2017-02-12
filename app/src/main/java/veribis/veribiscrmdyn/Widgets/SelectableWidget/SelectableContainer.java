package veribis.veribiscrmdyn.Widgets.SelectableWidget;

/**
 * dailog listesi modeli
 * Created by Cantekin on 12.2.2017.
 */

public class SelectableContainer {
    private String dialogTitle;
    private ISelectableWidget widget;
    private int sqlId;
    private String textKey;
    private String valueKey;

    public String getDialogTitle() {
        return dialogTitle;
    }

    public void setDialogTitle(String dialogTitle) {
        this.dialogTitle = dialogTitle;
    }

    public ISelectableWidget getWidget() {
        return widget;
    }

    public void setWidget(ISelectableWidget widget) {
        this.widget = widget;
    }

    public int getSqlId() {
        return sqlId;
    }

    public void setSqlId(int sqlId) {
        this.sqlId = sqlId;
    }

    public String getTextKey() {
        return textKey;
    }

    public void setTextKey(String textKey) {
        this.textKey = textKey;
    }

    public String getValueKey() {
        return valueKey;
    }

    public void setValueKey(String valueKey) {
        this.valueKey = valueKey;
    }
}
