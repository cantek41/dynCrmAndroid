package veribis.veribiscrmdyn;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

import Model.Form.FormProperties;
import Model.Menu.MenuItemModel;
import veribis.veribiscrmdyn.Fragment.Form.FormFragment;
import veribis.veribiscrmdyn.Fragment.List.ListFragment;
import veribis.veribiscrmdyn.Menu.MenuBuilder;

/**
 * Created by Cantekin on 21.1.2017.
 */
public abstract class BaseActivity extends AppCompatActivity {
  public FragmentTransaction fmTr;
  public FloatingActionButton fab;
  private final String TAG = "BaseActivity";
  protected ProgressDialog mProgressDialog;
  protected NavigationView navigationView;

  protected void initActivity() {
    initNavigation();
  }

  protected void initNavigation() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        //TODO:dinamik hale gelmeli
        fmTr = getSupportFragmentManager().beginTransaction();
        fmTr.replace(R.id.content, new FormFragment().setProp(new FormProperties()));
        fmTr.addToBackStack(null);
        fmTr.commit();
      }
    });
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
      this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();
    initMenu();
  }

  protected void initMenu() {
    navigationView = (NavigationView) findViewById(R.id.nav_view);
    Menu m = navigationView.getMenu();
    MenuBuilder.build(this, m);
    //statik menu elamalları ekle
    m.add("Ayarlar").setIcon(R.drawable.anahtar).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem menuItem) {
        //todo: ayarlar sayfası yapılacak
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
      customTitle.setTextColor(getResources().getColor(R.color.TextHeaderLight));
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
   * @param menuItem
   */
  public void menuItemClick(MenuItemModel menuItem) {
    fmTr = getSupportFragmentManager().beginTransaction();
    fmTr.replace(R.id.content, getFragment());
    fmTr.addToBackStack(null);
    fmTr.commit();
    ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer(GravityCompat.START);
  }

  //TODO: burası Jsondan gelecek
  private Fragment getFragment() {
    return new ListFragment().setProp(getFromProp.getList());
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


  @Override
  public void onRequestPermissionsResult(int requestCode,
                                         String permissions[], int[] grantResults) {
    if (grantResults.length > 0
      && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
    } else {
      showMessage("İzinleri vermezseniz uygulama doğru çalışmayacaktır.");
      // TODO: 30.1.2017 dinamik string
    }
  }


}
