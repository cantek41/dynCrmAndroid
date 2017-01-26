package veribis.veribiscrmdyn;

import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import Model.Form.FormProperties;
import veribis.veribiscrmdyn.Fragment.Form.FormFragment;
import veribis.veribiscrmdyn.Fragment.List.ListFragment;

/**
 * Created by Cantekin on 21.1.2017.
 */
public abstract class BaseActivity extends AppCompatActivity
  implements NavigationView.OnNavigationItemSelectedListener {
  public FragmentTransaction fmTr;
  public FloatingActionButton fab;
  private final String TAG = "BaseActivity";
  protected ProgressDialog mProgressDialog;

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

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
  }

  /**
   * ActionBar Text
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

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_camera) {
      fmTr = getSupportFragmentManager().beginTransaction();
      fmTr.replace(R.id.content, getFragment());
      fmTr.addToBackStack(null);
      fmTr.commit();
    } else if (id == R.id.nav_share) {
      fmTr = getSupportFragmentManager().beginTransaction();
      fmTr.replace(R.id.content, getFragment());
      fmTr.addToBackStack(null);
      fmTr.commit();
    } else if (id == R.id.nav_send) {
    }
    ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer(GravityCompat.START);
    return true;
  }

  //TODO: burası Jsondan gelecek
  private Fragment getFragment() {
    return new ListFragment().setProp(new FormProperties());
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
    }
  }
}
