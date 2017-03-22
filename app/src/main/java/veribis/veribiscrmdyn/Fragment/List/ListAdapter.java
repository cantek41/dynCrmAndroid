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
import veribis.veribiscrmdyn.Fragment.FragmentFactory;
import veribis.veribiscrmdyn.List.IMyList;
import veribis.veribiscrmdyn.List._baseListAdapter;
import veribis.veribiscrmdyn.MainActivity;
import veribis.veribiscrmdyn.R;

/**
 * Created by Cantekin on 8.1.2017.
 */
public class ListAdapter extends _baseListAdapter {
    private final String TAG = "ListAdapter";
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
//                        CustomLogger.info(TAG, "ID===>" + value.toString());
                        ((MainActivity) getContext())
                                .showFragment(FragmentFactory.getFragment(newProp.getFormType())
                                        .setProp(newProp));
                    } else {
                        CustomLogger.alert(TAG, "Form Adı:" +formProperties.getEditLink());
                        ((MainActivity) getContext()).showMessage("Form bulunamadı");
                    }

                }
            });
        }
        return v;
    }
}
