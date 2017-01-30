package veribis.veribiscrmdyn.Menu;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import java.util.ArrayList;
import java.util.List;

import Model.Menu.*;
import Model.Menu.EnumMenuItem;
import veribis.veribiscrmdyn.BaseActivity;

/**
 * Created by Cantekin on 30.1.2017.
 */
public class MenuBuilder {
  public static void build(final Context context, Menu menu) {
    for (MenuGroupModel group : getMenuModel().getGroup()) {
      SubMenu subMenu = menu.addSubMenu(group.getName());
      for (final MenuItemModel item: group.getData()){
        int id = context.getResources().getIdentifier(item.getIcon(), "drawable", context.getPackageName());
        subMenu.add(item.getName()).setIcon(id).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
          @Override
          public boolean onMenuItemClick(MenuItem menuItem) {
            ((BaseActivity)context).menuItemClick(item);
            return true;
          }
        });
      }
    }
  }

  //// TODO: 30.1.2017 silinecek
  public static MenuModel getMenuModel() {
    MenuModel menuModel = new MenuModel();

    List<MenuGroupModel> groups = new ArrayList<MenuGroupModel>();
    List<MenuItemModel> items = new ArrayList<MenuItemModel>();
    MenuItemModel item = new MenuItemModel();
    item.setName("Mesai");
    item.setLink("MesaiFormu");
    item.setIcon("ic_menu_share");
    item.setType(EnumMenuItem.FORM);
    items.add(item);

    item = new MenuItemModel();
    item.setName("Mesai");
    item.setLink("MesaiFormu");
    item.setIcon("ic_menu_share");
    item.setType(EnumMenuItem.FORM);
    items.add(item);

    MenuGroupModel grup = new MenuGroupModel();
    grup.setName("Aktivite");
    grup.setData(items);

    groups.add(grup);

    grup = new MenuGroupModel();
    grup.setName("Firma");
    grup.setData(items);
    groups.add(grup);
    menuModel.setGroup(groups);
    return menuModel;
  }
}
