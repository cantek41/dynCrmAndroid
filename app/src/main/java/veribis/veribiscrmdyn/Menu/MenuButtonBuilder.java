package veribis.veribiscrmdyn.Menu;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.Menu;
import android.view.MenuItem;

import veribis.veribiscrmdyn.Fragment._baseFragment;

import static veribis.veribiscrmdyn.Menu.EnumMenuItem.valueOf;

/**
 * Created by Cantekin on 16.1.2017.
 */
public class MenuButtonBuilder {


  private MenuButtonBuilder() {

  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public static Menu getMenuButtons(_baseFragment fragment, Menu menu, String buttonName) {
    EnumMenuItem enumitem= valueOf(buttonName);
    IMenuButtonCommand command = null;
    switch (enumitem) {
      case SAVE:
        command=new SaveButtonCommand(fragment);
        break;
      case CANCEL:
        command=new CancelButtonCommand(fragment);
        break;
      case ATTACH:
        command=new AttachButtonCommand(fragment);
        break;
      default:
        break;
    }
    MenuItem item=menu.add(command.name());
    item.setIcon(fragment.getContext().getDrawable(command.icon()));
    item.setShowAsAction(command.ShowAsAction());
    final IMenuButtonCommand finalCommand = command;
    item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem menuItem) {
        finalCommand.execute();
        return false;
      }
    });
    return menu;
  }
}
