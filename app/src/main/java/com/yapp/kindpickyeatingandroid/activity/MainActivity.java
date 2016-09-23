package com.yapp.kindpickyeatingandroid.activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.yapp.kindpickyeatingandroid.R;
import com.yapp.kindpickyeatingandroid.fragment.OneFragment;
import com.yapp.kindpickyeatingandroid.fragment.TwoFragment;
import com.yapp.kindpickyeatingandroid.util.GpsInfo;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    final String TAG = "MainActivity";
    private GpsInfo gps;
    int mCurrentFragmentIndex;
    public final static int FRAGMENT_ONE = 0;
    public final static int FRAGMENT_TWO = 1;
//    private String myLat;
//    private String myLon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        gps = new GpsInfo(MainActivity.this);
//        // GPS 사용유무 가져오기
//        if (gps.isGetLocation()) {
//
//            double latitude = gps.getLatitude();
//            double longitude = gps.getLongitude();
//
//            myLat = String.valueOf(latitude);
//            myLon = String.valueOf(longitude);
//
//            Toast.makeText(
//                    getApplicationContext(),
//                    "당신의 위치 - \n위도: " + latitude + "\n경도: " + longitude,
//                    Toast.LENGTH_LONG).show();
//        } else {
//            // GPS 를 사용할수 없으므로
//            gps.showSettingsAlert();
//        }
//
//        SampleAPIAS s=new SampleAPIAS();
//        try{
//            //여기서 excute로 데이터를 넘긴후!
//            MapRestaurantDto smp = s.execute(myLat,myLon).get();
//
//        }
//        catch(Exception e){
//
//        }

        mCurrentFragmentIndex = FRAGMENT_ONE;

        fragmentReplace(mCurrentFragmentIndex);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.setStatusBarBackgroundColor(Color.WHITE);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    public void fragmentReplace(int reqNewFragmentIndex) {

        Fragment newFragment = null;

        Log.d(TAG, "fragmentReplace " + reqNewFragmentIndex);

        newFragment = getFragment(reqNewFragmentIndex);

        // replace fragment
//        final FragmentTransaction transaction = getSupportFragmentManager()
//                .beginTransaction();
//
//        transaction.replace(R.id.ll_fragment, newFragment);
//
//        // Commit the transaction
//        transaction.commit();

        getSupportFragmentManager().beginTransaction().replace(R.id.ll_fragment, newFragment).commit();

    }

    private Fragment getFragment(int idx) {
        Fragment newFragment = null;

        switch (idx) {
            case FRAGMENT_ONE:
                newFragment = new OneFragment();
                break;
            case FRAGMENT_TWO:
                newFragment = new TwoFragment();
                break;

            default:
                Log.d(TAG, "Unhandle case");
                break;
        }

        return newFragment;
    }

    public void mClick(View v) {

        switch (v.getId()) {

            case R.id.btn_map:
                mCurrentFragmentIndex = FRAGMENT_ONE;
                fragmentReplace(mCurrentFragmentIndex);
                v.setBackgroundResource(R.drawable.map_button_clk);
                break;
            case R.id.btn_recommend:
                mCurrentFragmentIndex = FRAGMENT_TWO;
                fragmentReplace(mCurrentFragmentIndex);
                v.setBackgroundResource(R.drawable.recommend_button_clk);

                break;
        }

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

        Intent i;
        if (id == R.id.nav_map) {
        } else if (id == R.id.nav_favorite) {
//            i = new Intent(MainActivity.this,FavoriteActivity.class);
            i = new Intent(MainActivity.this,RestaurantDetailActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_write) {
            i = new Intent(MainActivity.this,MenuActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_my_profile) {
            i = new Intent(MainActivity.this,MyProfileActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_logout) {
            i = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(i);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
