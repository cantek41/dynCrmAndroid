package veribis.veribiscrmdyn.Menu.Data;

public class MenuItemModel {
  private String label;
  private String icon;
  private String link;

  public EnumMenuItem getType() {
    return type;
  }

  public void setType(EnumMenuItem type) {
    this.type = type;
  }

  private EnumMenuItem type;

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

}

