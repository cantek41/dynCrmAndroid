package Data;

import android.content.Context;

import com.cantekinandroidlib.logger.CustomLogger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Cantekin on 10.3.2017.
 * webden gelen form ve list bilgilerini
 * preferanceye aktarır
 */

public class UserDataToPreference {
    Context context;

    public UserDataToPreference(Context _context) {
        context = _context;
    }

    public void Run(String data) {
        try {
            JSONObject obj = new JSONObject(data);
            MyPreference.getPreference(context).setData("menu", obj.getString("menu"));
            if (obj.getString("lists") != null) {
                JSONArray lists = new JSONArray(obj.getString("lists"));
                for (int i = 0; i < lists.length(); i++) {
                    JSONObject lst = lists.getJSONObject(i);
                    MyPreference.getPreference(context).setData(lst.getString("formName"), lst.toString());
                }
            }
            if (obj.getString("forms") != null) {
                JSONArray forms = new JSONArray(obj.getString("forms"));
                for (int i = 0; i < forms.length(); i++) {
                    JSONObject frm = forms.getJSONObject(i);
                    MyPreference.getPreference(context).setData(frm.getString("formName"), frm.toString());
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
