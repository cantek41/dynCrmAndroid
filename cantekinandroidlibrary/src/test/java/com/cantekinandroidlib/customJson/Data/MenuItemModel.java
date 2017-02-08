package com.cantekinandroidlib.customJson.Data;

public class MenuItemModel {
  private String name;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}

