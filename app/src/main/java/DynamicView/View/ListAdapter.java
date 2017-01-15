package DynamicView.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import DynamicView.Model.Response;
import veribis.veribiscrmdyn.IList;
import veribis.veribiscrmdyn.R;

/**
 * Created by Cantekin on 8.1.2017.
 */
public class ListAdapter extends ArrayAdapter<Response> {
  private final String TAG = "AccountAdapter";
  private Context context;
  private IList view;

  public ListAdapter(Context context,IList view, int resource, List<Response> objects) {
    super(context, resource, objects);
    this.context = context;
    this.view=view;

  }

  @Override
  public View getView(final int position, final View convertView, ViewGroup parent) {
    View v = convertView;
    if (v == null) {
      LayoutInflater vi;
      vi = LayoutInflater.from(getContext());
      v = vi.inflate(R.layout.row_data_list, null);
    }
    final Response o = getItem(position);
    if (o != null) {
      TextView large = (TextView) v.findViewById(R.id.rowLargeText);
      TextView small1 = (TextView) v.findViewById(R.id.rowSmalTextLeft);
      TextView small2 = (TextView) v.findViewById(R.id.rowSmallTextRightFirst);
      TextView small3 = (TextView) v.findViewById(R.id.rowSmallTextSecond);

      large.setText(o.getSubject() + " " + String.valueOf(position));
      small1.setText(String.valueOf(o.getId()));
      small2.setText(o.getOpenOrClose());


    }
    return v;
  }

  @Override
  public Response getItem(int position) {
    if (closeEngoughToPullData(position)) {
      ((IList)(view)).getData((super.getCount() / 10) + 1);
    }
    return super.getItem(position);
  }


  // will return true, if loaded item 3 items away from end
  public boolean closeEngoughToPullData(int position) {
    return super.getCount() - position < 3;
  }
}
