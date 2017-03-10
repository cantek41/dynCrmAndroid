package Data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.cantekinandroidlib.customJson.jsonHelper;
import com.cantekinandroidlib.logger.CustomLogger;
import com.cantekinandroidlib.webApi.IThreadDelegete;
import com.cantekinandroidlib.webApi.ThreadWebApiPost;

import java.util.ArrayList;
import java.util.List;

import Model.Form.baseProperties;
import Model.UpdateRequestModel;
import veribis.veribiscrmdyn.MainActivity;
import veribis.veribiscrmdyn.Menu.Data.EnumMenuItem;
import veribis.veribiscrmdyn.Menu.Data.MenuGroupModel;
import veribis.veribiscrmdyn.Menu.Data.MenuItemModel;
import veribis.veribiscrmdyn.Menu.Data.MenuModel;

/**
 * preferences e erişmek
 * genel ayarları
 * kaydetmek ve okumak için
 * Created by Cantekin on 11.01.2016.
 * singleton pattern
 */

public class MyPreference  {
    private static String TAG = "Preference";
    private Context context;
    public static MyPreference preference;

    private MyPreference(Context context) {
        this.context = context;
    }

    public static MyPreference getPreference(Context context) {
        if (preference == null)
            preference = new MyPreference(context);
        return preference;
    }

    public MenuModel getMenu() {
        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(context);
        String menuString = data.getString("menu", null);
        if (menuString == null)
            return null;
        return jsonHelper.stringToObject(menuString, MenuModel.class);
    }

    public void setData(@NonNull String name, @NonNull String value) throws NullPointerException {
        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = data.edit();
        if (value != null && name != null) editor.putString(name, value);
        else
            throw new NullPointerException("paramtreler null olamaz");
        editor.commit();
    }

    public <T> T getData(String demo, Class<T> clazzType) {
        // TODO: 10.2.2017 olmayan formu apiden getirmeye çalış
        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(context);
        String dataString = data.getString(demo, null);
        if (dataString == null)
            return null;
        return jsonHelper.stringToObject(dataString, clazzType);
    }

    public void setUserData(String user) {
        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = data.edit();
        CustomLogger.error(TAG, user);
        if (user != null) editor.putString("User", user);
        editor.commit();
    }

    public User getUserData() {
        // TODO: 10.2.2017 olmayan formu apiden getirmeye çalış
        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(context);
        String dataString = data.getString("User", null);
        if (dataString == null) return null;
        return jsonHelper.stringToObject(dataString, User.class);
    }

    /**
     * set api UpdateData and Insert adress
     *
     * @param address
     */
    public void setSetWepApiAddress(String address) {
        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = data.edit();
        if (address != null) editor.putString("setWebApiAddress", address);
        editor.commit();
    }

    public String getWepApiRootAddress() {
        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(context);
        return data.getString("WepApiRootAddres", "http://demo.veribiscrm.com/");
    }

    /**
     * get login web address
     *
     * @return
     */
    public String getLoginWepApiAddress() {
        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(context);
        return data.getString("LoginWepApiAddress", "http://demo.veribiscrm.com/token");
    }

    /**
     * get api UpdateData and Insert address
     * defult value "http://demo.veribiscrm.com/api/mobile/UpdateData"
     *
     * @return "http://demo.veribiscrm.com/api/mobile/UpdateData"
     */
    public String getSetWepApiAddress() {
        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(context);
        return data.getString("setWebApiAddress", "http://demo.veribiscrm.com/api/mobile/UpdateData");
    }

    /**
     * get api UpdateData and Insert address
     * defult value "http://demo.veribiscrm.com/api/mobile/UpdateData"
     *
     * @return "http://demo.veribiscrm.com/api/mobile/UpdateData"
     */
    public String getWepApiSaveFileAddress() {
        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(context);
        return data.getString("setWebApiAddress", "http://demo.veribiscrm.com/api/mobile/SaveFile");
    }

    /**
     * get api GetData address
     * default value "http://demo.veribiscrm.com/api/mobile/GetData"
     *
     * @return "http://demo.veribiscrm.com/api/mobile/GetData"
     */
    public String getGetWepApiAddress() {
        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(context);
        return data.getString("getWebApiAddress", "http://demo.veribiscrm.com/api/mobile/GetData");
    }


    /**
     * get api List address
     * default value "http://demo.veribiscrm.com/api/mobile/getlist"     *
     *
     * @return "http://demo.veribiscrm.com/api/mobile/getlist"
     */
    public String getListWebApiAddress() {
        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(context);
        return data.getString("getListWebApiAddress", "http://demo.veribiscrm.com/api/mobile/getlist");
    }

    public String getSqlWebApiAddress() {
        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(context);
        return data.getString("getSqlWebApiAddress", "http://demo.veribiscrm.com/api/mobile/GetReportList");
    }

    /**
     * logOut işleminde kullanıcıya ait tüm verilerin
     * silinmesini sağlar
     */
    public void deletePreferences() {
        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(context);
        data.edit().clear().commit();
    }


    public String getUserDataWebApiAddress() {
        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(context);
        return data.getString("getUserDataWebApiAddress", "http://demo.veribiscrm.com/api/admin/AccountApi/GetEmployeData");
    }

}
