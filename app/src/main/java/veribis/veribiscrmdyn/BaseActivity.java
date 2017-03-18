package veribis.veribiscrmdyn;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapCircleThumbnail;
import com.cantekinandroidlib.logger.CustomLogger;
import com.squareup.picasso.Picasso;

import Data.MyPreference;
import Data.User;
import Model.Form.baseProperties;
import veribis.veribiscrmdyn.Fragment.FragmentFactory;
import veribis.veribiscrmdyn.Fragment._baseFragment;
import veribis.veribiscrmdyn.Menu.Data.MenuItemModel;
import veribis.veribiscrmdyn.Menu.MenuBuilder;

/**
 * Created by Cantekin on 21.1.2017.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public FragmentTransaction fmTr;
    public FloatingActionButton fab;
    protected ProgressDialog mProgressDialog;
    protected NavigationView navigationView;
    public User user;


    protected void initActivity() {
        initFabButton();
        initNavigation();

    }

    protected void initFabButton() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((_baseFragment) getSupportFragmentManager().findFragmentById(R.id.content)).fabOnClick();
            }
        });
    }

    protected void initNavigation() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        initMenu();
    }


    private void initMenu() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        initUserInfo();
        Menu m = navigationView.getMenu();
        MenuBuilder.build(this, m);
        //statik menu elamalları ekle
        m.add("Ayarlar").setIcon(R.drawable.anahtar).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(getApplicationContext(), SettingActivity.class));
                ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer(GravityCompat.START);
                return true;
            }
        });
        m.add("Yardım").setIcon(R.drawable.anahtar).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.veribis.com.tr"));
                startActivity(intent);
                ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer(GravityCompat.START);
                return true;
            }
        });
        m.add("Çıkış").setIcon(R.drawable.anahtar).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                logOut();
                ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void logOut() {
        new AlertDialog.Builder(BaseActivity.this)
                .setMessage("Kişisel bilgileriniz silinecek, Emin misiniz?")
                .setCancelable(true)
                .setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyPreference.getPreference(getApplicationContext()).clearPreferences();
                        dialog.dismiss();
                        finish();
                        User.clearUser();
                        Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
                        startActivity(intent);
                    }
                }).show();
    }

    /**
     * user reme tıklayınca
     *
     * @param v
     */
    public void ShowDetail(View v) {
    }

    private void initUserInfo() {
        View header = navigationView.getHeaderView(0);
        TextView txtUserName = (TextView) header.findViewById(R.id.txtUserName);
        TextView txtUserMail = (TextView) header.findViewById(R.id.txtUserMail);
        BootstrapCircleThumbnail imgUser = (BootstrapCircleThumbnail) header.findViewById(R.id.imgUserImage);
        txtUserName.setText(user.getName() + " " + user.getSurName());
        txtUserMail.setText(user.getEmail());
        String imageURL = user.getPicturePath().replace("..", MyPreference.getPreference(this).getMainURL());
        CustomLogger.info("imageURL", imageURL);
        Picasso.with(this).load(imageURL).into(imgUser);
    }

    /**
     * ActionBar Text
     *
     * @param title
     */
    public void changeTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);
            View customView = getLayoutInflater().inflate(R.layout.actionbar_title, null);
            TextView customTitle = (TextView) customView.findViewById(R.id.actionbarTitle);
            customTitle.setText(title);
            customTitle.setTextColor(ContextCompat.getColor(this, R.color.TextHeaderLight));
            // Change the font family (optional)
            customTitle.setTypeface(Typeface.DEFAULT_BOLD);
            customTitle.setTextSize(20);
            // Set the on click listener for the title
            customTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "dfsf", Toast.LENGTH_SHORT).show();
                }
            });
            actionBar.setCustomView(customView);
        }
    }

    /**
     * menu elamallarına tıklanınca çalışacak
     *
     * @param menuItem
     */
    public void menuItemClick(MenuItemModel menuItem) {
        //// TODO: 10.2.2017 menugetLink kullanılmalı
        baseProperties prop = MyPreference.getPreference(getApplicationContext()).getData(menuItem.getLink(), baseProperties.class);

        if (prop != null)
            showFragment(FragmentFactory.getFragment(prop.getFormType()).setProp(prop));
        else {
            showMessage("form kayıtlı değil.");
        }
        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer(GravityCompat.START);
    }

    /**
     * fragmentleri aktifleştirilmesi için kullanılır
     *
     * @param fragment
     */
    public void showFragment(_baseFragment fragment) {
        fmTr = getSupportFragmentManager().beginTransaction();
        fmTr.replace(R.id.content, fragment);
        fmTr.addToBackStack(null);
        fmTr.commit();
    }

    /**
     * All Activity Control backbutton
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        boolean drawerOpen = drawer.isDrawerOpen(GravityCompat.START);
        if (drawerOpen) drawer.closeDrawer(GravityCompat.START);
        else {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStack();
            } else {
                super.onBackPressed();
            }
        }
    }

    /**
     * aktivitenin tamamında toas mesajlarının kontolu buradan yapılır
     *
     * @param msg
     */
    public void showMessage(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * All Activity
     * Message Progressbar Open
     *
     * @param msg
     */
    public void showProgress(String msg) {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            dismissProgress();
        mProgressDialog = ProgressDialog.show(this, getResources().getString(
                R.string.app_name), msg);
    }

    /**
     * All Activity
     * ProgressBar Close
     */
    public void dismissProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    /**
     * Network connection check     *
     *
     * @return
     */
    public boolean isConnection() {
        ConnectivityManager connMgr = (ConnectivityManager) getApplicationContext()
                .getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            showMessage("Bağlantınızı Kontrol edin");
        // TODO: 25.1.2017 textler dinamik gelmeli
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        } else {
            showMessage("İzinleri vermezseniz uygulama doğru çalışmayacaktır.");
            // TODO: 30.1.2017 dinamik string
        }
    }
}
