package veribis.veribiscrmdyn;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import cantekinWebApi.IThreadDelegete;


public class BaseActivity extends AppCompatActivity
  implements NavigationView.OnNavigationItemSelectedListener, IThreadDelegete {
  FragmentTransaction fmTr;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          .setAction("Action", null).show();
      }
    });

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
      this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
    Init();
  }

  public void goDetail()
  {
    fmTr = getSupportFragmentManager().beginTransaction();
    fmTr.add(R.id.content, new BlankggFragment());
    fmTr.commit();
  }
  protected void Init() {
    //fgşdf

    fmTr = getSupportFragmentManager().beginTransaction();
    fmTr.add(R.id.content, new BlankFragment());
    fmTr.commit();

    /*ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      CustomLogger.info("actionBar", "actionBar");

      actionBar.setDisplayShowTitleEnabled(false);
      actionBar.setDisplayShowCustomEnabled(true);
      View customView = getLayoutInflater().inflate(R.layout.actionbar_title, null);
      // Get the textview of the title
      TextView customTitle = (TextView) customView.findViewById(R.id.actionbarTitle);

      customTitle.setText("sdfasfsdfsdf");
      // Change the font family (optional)
      customTitle.setTypeface(Typeface.MONOSPACE);
      // Set the on click listener for the title
      customTitle.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Toast.makeText(getApplicationContext(), "dfsf", Toast.LENGTH_SHORT).show();

        }
      });
      actionBar.setCustomView(customView);
    }
      */

  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_camera) {
      Intent myIntent = new Intent(this, ListActivity.class);
      startActivity(myIntent);
    } else if (id == R.id.nav_share) {


    } else if (id == R.id.nav_send) {

    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  @Override
  public void postResult(String data) {

  }
}
