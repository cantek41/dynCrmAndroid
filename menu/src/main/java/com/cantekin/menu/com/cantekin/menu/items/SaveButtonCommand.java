package com.cantekin.menu.com.cantekin.menu.items;

import android.content.Context;
import android.view.MenuItem;

import com.cantekin.menu.com.cantekin.menu.items.IMenuButtonCommand;

import veribis.veribiscrmdyn.Fragment.MyFragment;
import veribis.veribiscrmdyn.R;


/**
 * Created by Cantekin on 16.1.2017.
 */
public class SaveButtonCommand implements IMenuButtonCommand {
  Context context;
  MyFragment fragment;

  public SaveButtonCommand(Context context) {
    this.context = context;
  }

  public SaveButtonCommand(MyFragment fragment) {
    this.context = fragment.getContext();
    this.fragment = fragment;
  }

  @Override
  public void execute() {
       if(fragment!=null)
         fragment.save();
    //Toast.makeText(context, "Kaydet", Toast.LENGTH_SHORT).show();
  }

  @Override
  public String name() {
    return "Kaydet";
  }

  @Override
  public int icon() {
    return R.drawable.save;
  }

  @Override
  public int ShowAsAction() {
    return MenuItem.SHOW_AS_ACTION_ALWAYS;
  }
}
