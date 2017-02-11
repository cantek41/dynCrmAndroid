package veribis.veribiscrmdyn.Menu;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import java.util.ArrayList;
import java.util.List;

import Data.MyPreference;
import veribis.veribiscrmdyn.Menu.Data.EnumMenuItem;
import veribis.veribiscrmdyn.BaseActivity;
import veribis.veribiscrmdyn.Menu.Data.MenuGroupModel;
import veribis.veribiscrmdyn.Menu.Data.MenuItemModel;
import veribis.veribiscrmdyn.Menu.Data.MenuModel;

/**
 * Created by Cantekin on 30.1.2017.
 */
public class MenuBuilder {
    public static void build(final Context context, Menu menu) {
      //  MyPreference.getPreference(context).deletePreferences();
        MenuModel menuModel = MyPreference.getPreference(context).getMenu();
        if (menuModel != null)
            for (MenuGroupModel group : menuModel.getGroup()) {
                SubMenu subMenu = menu.addSubMenu(group.getName());
                for (final MenuItemModel item : group.getData()) {
                    int id = context.getResources().getIdentifier(item.getIcon(), "drawable", context.getPackageName());
                    subMenu.add(item.getName()).setIcon(id).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            ((BaseActivity) context).menuItemClick(item);
                            return true;
                        }
                    });
                }
            }
    }
}
