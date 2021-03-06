package com.cantekin.menu.com.cantekin.menu.items;

import android.app.Activity;
import android.content.Context;
import android.view.MenuItem;

import veribis.veribiscrmdyn.Fragment.MyFragment;
import veribis.veribiscrmdyn.R;

/**
 * Created by Cantekin on 16.1.2017.
 */
public class CancelButtonCommand implements IMenuButtonCommand {
  Context context;
  MyFragment fragment;

  public CancelButtonCommand(Context context) {
    this.context = context;
  }

  public CancelButtonCommand(MyFragment fragment) {
    this.context = fragment.getContext();
    this.fragment = fragment;
  }

  @Override
  public void execute() {
    ((Activity) context).onBackPressed();
  }

  @Override
  public String name() {
    return "İptal";
  }

  @Override
  public int icon() {
    return R.drawable.cast_album_art_placeholder;
  }

  @Override
  public int ShowAsAction() {
    return MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW;
  }
}
