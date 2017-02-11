package veribis.veribiscrmdyn.Fragment.List;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cantekinandroidlib.logger.CustomLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Data.MyPreference;
import Model.Form.baseProperties;
import veribis.veribiscrmdyn.Fragment.Form.FormFragment;
import veribis.veribiscrmdyn.Fragment.FragmentFactory;
import veribis.veribiscrmdyn.Fragment._baseFragment;
import veribis.veribiscrmdyn.MainActivity;
import veribis.veribiscrmdyn.R;
import veribis.veribiscrmdyn.getFromProp;

/**
 * Created by Cantekin on 8.1.2017.
 */
public class ListAdapter extends ArrayAdapter<Map<String, Object>> {
    private final String TAG = "AccountAdapter";
    private IMyList view;
    private FragmentTransaction frgmTra;
    private baseProperties formProperties;

    public ListAdapter(Context context, IMyList view, FragmentTransaction frgmTra, int resource, List<Map<String, Object>> objects, baseProperties formProperties) {
        super(context, resource, objects);
        this.view = view;
        this.frgmTra = frgmTra;
        this.formProperties = formProperties;

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
            TextView[] listText = new TextView[]{large, small1, small2, small3};
            //TODO: list gösterim değişmeli
            List<Object> list = new ArrayList<>(o.values());
            for (int i = 0; i < list.size(); i++) {
                prepairText(listText[i], list.get(i));
            }
            final Object value = o.get("Id");
            //satıra tıklama
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    baseProperties newProp = MyPreference.getPreference(getContext()).getData(formProperties.getEditLink(), baseProperties.class);
                    if (newProp != null) {
                        newProp.setParentFieldId(formProperties.getParentFieldId());
                        newProp.setRecordId(value);
                        ((MainActivity) getContext())
                                .showFragment(FragmentFactory.getFragment(newProp.getFormType())
                                        .setProp(newProp));
                    } else
                        ((MainActivity) getContext()).showMessage("Form bulunamadı");

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
     * Liste Sonuna gelmişse yen Data çekmek
     * için web servara gider
     *
     * @param position
     * @return
     */
    @Override
    public Map<String, Object> getItem(int position) {
        if (closeEngoughToPullData(position)) {
            if ((super.getCount() / 10) + 1 > 1)
                view.getData((super.getCount() / 10) + 1);
        }
        return super.getItem(position);
    }

    public boolean closeEngoughToPullData(int position) {
        return super.getCount() - position < 3;
    }
}
