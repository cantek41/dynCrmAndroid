package veribis.veribiscrmdyn.Fragment.List;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Model.Form.FormProperties;
import veribis.veribiscrmdyn.Fragment.Form.FormFragment;
import veribis.veribiscrmdyn.R;

/**
 * Created by Cantekin on 8.1.2017.
 */
public class ListAdapter extends ArrayAdapter<Map<String, Object>> {
  private final String TAG = "AccountAdapter";
  private Context context;
  private IMyList view;
  private FragmentTransaction frgmTra;

  public ListAdapter(Context context, IMyList view, FragmentTransaction frgmTra, int resource, List<Map<String, Object>> objects) {
    super(context, resource, objects);
    this.context = context;
    this.view = view;
    this.frgmTra = frgmTra;

  }

  @Override
  public View getView(final int position, final View convertView, ViewGroup parent) {
    View v = convertView;
    if (v == null) {
      LayoutInflater vi;
      vi = LayoutInflater.from(getContext());
      v = vi.inflate(R.layout.row_data_list, null);
    }
    final Map<String, Object> o = getItem(position);
    if (o != null) {
      LinearLayout row = (LinearLayout) v.findViewById(R.id.listRow);
      TextView large = (TextView) v.findViewById(R.id.rowLargeText);
      TextView small1 = (TextView) v.findViewById(R.id.rowSmalTextLeft);
      TextView small2 = (TextView) v.findViewById(R.id.rowSmallTextRightFirst);
      TextView small3 = (TextView) v.findViewById(R.id.rowSmallTextSecond);

      //TODO: list gösterim değişmeli

      List<Object> list = new ArrayList<Object>(o.values());
      prepairText(large, list.get(0));
      prepairText(small1, list.get(1));
      prepairText(small2, list.get(2));
      final Object value = o.get("Id");
      //satıra tıklama
      row.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          //TODO: değişmeli dinamik olarak fragmente bilgiler göndermeli
          frgmTra.replace(R.id.content, new FormFragment().setProp(new FormProperties("Activity",((Double) value).intValue())));
          frgmTra.addToBackStack(null);
          frgmTra.commit();
        }
      });
    }
    return v;
  }

  private void prepairText(TextView view, Object o) {
    if (o instanceof Double) view.setText(String.valueOf(((Double) o).intValue()));
    else view.setText(String.valueOf(o));

  }


  /**
   * Liste Sonuna gelmişse yen data çekmek
   * için web servara gider
   *
   * @param position
   * @return
   */
  @Override
  public Map<String, Object> getItem(int position) {
    if (closeEngoughToPullData(position)) {
      ((IMyList) (view)).getData((super.getCount() / 10) + 1);
    }
    return super.getItem(position);
  }

  public boolean closeEngoughToPullData(int position) {
    return super.getCount() - position < 3;
  }
}
