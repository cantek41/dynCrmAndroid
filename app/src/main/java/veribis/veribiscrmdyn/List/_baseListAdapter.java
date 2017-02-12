package veribis.veribiscrmdyn.List;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cantekinandroidlib.logger.CustomLogger;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Data.MyPreference;
import Model.Form.baseProperties;
import veribis.veribiscrmdyn.Fragment.FragmentFactory;
import veribis.veribiscrmdyn.MainActivity;
import veribis.veribiscrmdyn.R;

import static com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.*;

/**
 * Created by Cantekin on 8.1.2017.
 */
public abstract class _baseListAdapter extends ArrayAdapter<Map<String, Object>> {
    private final String TAG = "_baseListAdapter";
    protected IMyList view;
    protected int pageSize;


    public _baseListAdapter(Context context, int resource, List<Map<String, Object>> objects) {
        super(context, resource, objects);
    }

    protected void prepairText(TextView view, Object o) {
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
            if (super.getCount() < view.getTotal())
                view.getData((super.getCount() / pageSize) + 1);
        }
        return super.getItem(position);
    }

    public boolean closeEngoughToPullData(int position) {
        return super.getCount()- 1 == position ;
    }
}
