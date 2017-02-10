package Data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.cantekinandroidlib.webApi.IThreadDelegete;
import com.cantekinandroidlib.webApi.ThreadWebApiPost;

import java.util.ArrayList;
import java.util.List;

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

public class MyPreference implements IThreadDelegete {
    private static String TAG = "Preference";
    private String webApiAddress = "http://demo.veribiscrm.com/api/mobile/UpdateData";
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
        new ThreadWebApiPost<UpdateRequestModel>(this, null, webApiAddress).execute();
        showProgress("Kaydediliyor");

        MenuModel menuModel = new MenuModel();
        List<MenuGroupModel> groups = new ArrayList<MenuGroupModel>();
        List<MenuItemModel> items = new ArrayList<MenuItemModel>();
        MenuItemModel item = new MenuItemModel();
        item.setName("Mesai");
        item.setLink("MesaiFormu");
        item.setIcon("ic_menu_share");
        item.setType(EnumMenuItem.FORM);
        items.add(item);
        item = new MenuItemModel();
        item.setName("Mesai");
        item.setLink("MesaiFormu");
        item.setIcon("ic_menu_share");
        item.setType(EnumMenuItem.FORM);
        items.add(item);

        MenuGroupModel grup = new MenuGroupModel();
        grup.setName("Aktivite");
        grup.setData(items);

        groups.add(grup);

        grup = new MenuGroupModel();
        grup.setName("Firma");
        grup.setData(items);
        groups.add(grup);
        menuModel.setGroup(groups);
        return menuModel;
    }

    private void showProgress(String message) {
        if (context instanceof MainActivity)
            ((MainActivity) context).showProgress(message);
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
     * delegeti için interfaceden gelen metot
     *
     * @param data
     */
    @Override
    public void postResult(String data) {
        if (context instanceof MainActivity)
            ((MainActivity) context).dismissProgress();
    }

    /**
     * get api List address
     * default value "http://demo.veribiscrm.com/api/mobile/getlist"     *
     *
     * @return "http://demo.veribiscrm.com/api/mobile/getlist"
     */
    public String getListWebApiAddress() {
        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(context);
        return data.getString("getWebApiAddress", "http://demo.veribiscrm.com/api/mobile/getlist");
    }

    /**
     * logOut işleminde kullanıcıya ait tüm verilerin
     * silinmesini sağlar
     */
    public void deletePreferences() {
        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(context);
        data.edit().clear().commit();
    }
}
