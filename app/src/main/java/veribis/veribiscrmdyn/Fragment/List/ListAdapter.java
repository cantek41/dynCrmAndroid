package veribis.veribiscrmdyn.Fragment.List;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import Model.Response;
import veribis.veribiscrmdyn.Fragment.Form.FormFragment;
import veribis.veribiscrmdyn.R;

/**
 * Created by Cantekin on 8.1.2017.
 */
public class ListAdapter extends ArrayAdapter<Response> {
  private final String TAG = "AccountAdapter";
  private Context context;
  private IMyList view;
  private FragmentTransaction frgmTra;

  public ListAdapter(Context context, IMyList view, FragmentTransaction frgmTra, int resource, List<Response> objects) {
    super(context, resource, objects);
    this.context = context;
    this.view = view;
    this.frgmTra=frgmTra;

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
      LinearLayout row = (LinearLayout) v.findViewById(R.id.listRow);
      TextView large = (TextView) v.findViewById(R.id.rowLargeText);
      TextView small1 = (TextView) v.findViewById(R.id.rowSmalTextLeft);
      TextView small2 = (TextView) v.findViewById(R.id.rowSmallTextRightFirst);
      TextView small3 = (TextView) v.findViewById(R.id.rowSmallTextSecond);

      large.setText(o.getSubject() + " " + String.valueOf(position));
      small1.setText(String.valueOf(o.getId()));
      small2.setText(o.getOpenOrClose());

      //satıra tıklama
      row.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          //TODO: değişmeli
          frgmTra.replace(R.id.content,new FormFragment().setProp());
          frgmTra.addToBackStack(null);
          frgmTra.commit();
        }
      });
    }
    return v;
  }

  /**
   * Liste Sonuna gelmişse yen data çekmek
   * için web servara gider
   * @param position
   * @return
     */
  @Override
  public Response getItem(int position) {
    if (closeEngoughToPullData(position)) {
      ((IMyList) (view)).getData((super.getCount() / 10) + 1);
    }
    return super.getItem(position);
  }

  public boolean closeEngoughToPullData(int position) {
    return super.getCount() - position < 3;
  }
}
