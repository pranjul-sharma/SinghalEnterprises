package in.test.singhalenterprises.singhalenterprises;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

;

public class HomeActivity extends AppCompatActivity {
    public static HomeActivity currObj;
    NavigationView navigationView;
    DrawerLayout drawer;
    Toolbar toolbar;
    View headerView;
    FragmentManager fm;
    FragmentTransaction ft;

    private NavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            new NavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.drawer_home:
                            return initFrag(new HomeFragment(), "Home");
                        case R.id.drawer_products:
                            Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                            intent.putExtra("EXTRA_TAG", "drawer");
                            startActivity(intent);
                            return true;
                        case R.id.drawer_ecatelogue:
                            return initFrag(new ECatalogueFragment(), "E-Catalogue");
                        case R.id.drawer_blog:
                            return initFrag(new BlogFragment(), "Blog");
                        case R.id.drawer_know_us:
                            return initFrag(new KnowUsFragment(), "Know Us");
                        case R.id.drawer_share_app:
                            drawer.closeDrawer(GravityCompat.START);
                            Toast.makeText(getApplicationContext(), "share intent will be added here whenever download link is ready.", Toast.LENGTH_LONG).show();
                            break;
                        case R.id.drawer_feedback:
                            Intent intent1 = new Intent(getApplicationContext(), FeedbackActivity.class);
                            intent1.putExtra("TAG", "feedback");
                            startActivity(intent1);

                    }
                    return false;
                }
            };

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return initFrag(new HomeFragment(), "Home");
                case R.id.navigation_ecatalogue:
                    return initFrag(new ECatalogueFragment(), "E-Catalogue");
                case R.id.navigation_contact_us:
                    return initFrag(new KnowUsFragment(), "Know Us");
            }
            return false;
        }

    };

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/Montserrat-Medium.ttf")
                .setFontAttrId(R.attr.fontPath).build());
        setContentView(R.layout.activity_home);

        currObj = this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar = getSupportActionBar();
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        fm = getSupportFragmentManager();

        initFrag(new HomeFragment(), "Home");

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(onNavigationItemSelectedListener);
        headerView = navigationView.getHeaderView(0);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.START);
            }
        });
    }

    private boolean initFrag(final Fragment fragment, final String str) {

        ft = fm.beginTransaction();
        ft.replace(R.id.content, fragment, "visible_fragment");
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(str);
        getSupportActionBar().setTitle(str);
        ft.commit();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else {
            fm.popBackStack();
            if (fm.getBackStackEntryCount() == 1) {
                super.onBackPressed();
            }
        }
    }
}
