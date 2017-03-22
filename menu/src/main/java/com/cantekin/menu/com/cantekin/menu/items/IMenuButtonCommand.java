package com.cantekin.menu.com.cantekin.menu.items;

/**
 * her menu elemanı için gerekli arayüz
 * Created by Cantekin on 16.1.2017.
 */
public interface IMenuButtonCommand {
  void execute();
  String name();
  int icon();
  int ShowAsAction();
}
