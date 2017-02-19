package veribis.veribiscrmdyn.Menu.Data;

import java.util.List;

public class MenuGroupModel{
  public List<MenuItemModel> getData() {
    return data;
  }

  public void setData(List<MenuItemModel> data) {
    this.data = data;
  }
  private List<MenuItemModel> data;

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  private String label;
}
