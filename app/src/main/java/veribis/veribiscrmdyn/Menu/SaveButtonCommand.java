package veribis.veribiscrmdyn.Menu;

import android.content.Context;
import android.view.MenuItem;
import android.widget.Toast;

import veribis.veribiscrmdyn.R;


/**
 * Created by Cantekin on 16.1.2017.
 */
public class SaveButtonCommand implements IMenuButtonCommand {
  Context context;

  public SaveButtonCommand(Context context) {
    this.context = context;
  }

  @Override
  public void execute() {
    Toast.makeText(context, "Kaydet", Toast.LENGTH_SHORT).show();
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
