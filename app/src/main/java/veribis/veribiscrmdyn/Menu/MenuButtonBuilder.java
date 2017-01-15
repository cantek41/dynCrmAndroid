package veribis.veribiscrmdyn.Menu;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.Menu;
import android.view.MenuItem;

import static veribis.veribiscrmdyn.Menu.EnumMenuItem.valueOf;

/**
 * Created by Cantekin on 16.1.2017.
 */
public class MenuButtonBuilder {


  private MenuButtonBuilder() {

  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public static final Menu getMenuButtons(Context context, Menu menu, String buttonName) {
    EnumMenuItem enumitem= valueOf(buttonName);
    IMenuButtonCommand command = null;
    switch (enumitem) {
      case SAVE:
        command=new SaveButtonCommand(context);
        break;
      case CANCEL:
        command=new CancelButtonCommand(context);
        break;
      default:
        break;
    }
    MenuItem item=menu.add(command.name());
    item.setIcon(context.getDrawable(command.icon()));
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
