package veribis.veribiscrmdyn;

import android.graphics.Typeface;
import android.os.Bundle;
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

import veribis.veribiscrmdyn.Forms.FormFragment;
import veribis.veribiscrmdyn.Lists.MyListFragment;


public class BaseMyActivity extends AppCompatActivity
  implements NavigationView.OnNavigationItemSelectedListener, IMyActivity {
  FragmentTransaction fmTr;
  public FloatingActionButton fab;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initNavigation();
    initActivity();
  }

  /**
   * yan açılır menu için hazırlıklar
   */
  private void initNavigation() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
       /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          .setAction("Action", null).show();*/
        fmTr = getSupportFragmentManager().beginTransaction();
        fmTr.replace(R.id.content, new FormFragment());
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

  public void goDetail() {
    fmTr = getSupportFragmentManager().beginTransaction();
    fmTr.add(R.id.content, new FormFragment());
    fmTr.addToBackStack(null);
    fmTr.commit();
  }

  public void initActivity() {
    fmTr = getSupportFragmentManager().beginTransaction();
    fmTr.add(R.id.content, new HomeFragment());
    fmTr.commit();
  }

  private Fragment getFragment() {
    return new MyListFragment().setProp();
  }

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
}
