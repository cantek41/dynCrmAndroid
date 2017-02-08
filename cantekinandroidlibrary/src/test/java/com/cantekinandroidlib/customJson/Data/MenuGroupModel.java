package com.cantekinandroidlib.customJson.Data;

import java.util.List;

public class MenuGroupModel{
  public List<MenuItemModel> getData() {
    return data;
  }

  public void setData(List<MenuItemModel> data) {
    this.data = data;
  }
  private List<MenuItemModel> data;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  private String name;
}
