package veribis.veribiscrmdyn.Widgets;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Cantekin on 23.1.2017.
 */
public class ActiveWidget extends AbstractWidget {
  public ActiveWidget(Context context) {
    super(context);
  }

  public void onClick(View v)
  {
    Toast.makeText(getContext(),getLabel(),Toast.LENGTH_SHORT).show();
  }

}
