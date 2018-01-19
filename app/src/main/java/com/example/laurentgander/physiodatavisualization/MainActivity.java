package com.example.laurentgander.physiodatavisualization;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.laurentgander.physiodatavisualization.fragments.AnalyseFragment;
import com.example.laurentgander.physiodatavisualization.fragments.HomeFragment;
import com.example.laurentgander.physiodatavisualization.fragments.MeasuresFragment;
import com.example.laurentgander.physiodatavisualization.fragments.VisualisationFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MeasureServiceConnection {

    private static final String TAG = MainActivity.class.getSimpleName();
    private MeasureService service;
    private ServiceConnection serviceConnection;
    private BroadcastReceiver broadcastReceiver;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        HomeFragment mainFragment = new HomeFragment();
        setSupportActionBar(toolbar);
        getSupportFragmentManager().beginTransaction().add(R.id.main_fragment, mainFragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = new Intent(this, MeasureService.class);

        serviceConnection = new ServiceConnection(){
            @Override
            public void onServiceConnected(ComponentName name, IBinder binder) {
                service = ((MeasureService.LocalBinder) binder).getService();
                onMeasureServiceConnected();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                service = null;
                onMeasureServiceDisconnected();
            }
        };
        bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()){
                    case MeasureService.ACTION_CREATE_MEASURE:
                        Log.i(TAG,"Successfully created a new measure.");
                }
            }
        };

        IntentFilter intentFilter = new IntentFilter();

        intentFilter.addAction(MeasureService.ACTION_CREATE_MEASURE);

        registerReceiver(broadcastReceiver, intentFilter);

        navigationView.setCheckedItem(R.id.nav_home);

            onNavigationItemSelected(navigationView.getMenu().

            findItem(R.id.nav_home));
    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(broadcastReceiver);

        unbindService(serviceConnection);
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
    @Override
    public void onMeasureServiceConnected() {
    }

    @Override
    public void onMeasureServiceDisconnected() {
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_home){
            HomeFragment homeFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment ,homeFragment).commit();
            homeFragment.onMeasureServiceConnected();
        }
        if (id == R.id.nav_visualisation) {
            VisualisationFragment visualisationFragment = new VisualisationFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment ,visualisationFragment).commit();
            visualisationFragment.onMeasureServiceConnected();

        } else if (id == R.id.nav_analyse) {
            AnalyseFragment analyseFragment = new AnalyseFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, analyseFragment).commit();
            analyseFragment.onMeasureServiceConnected();

        } else if (id == R.id.nav_mesures) {
            MeasuresFragment mesuresFragment = new MeasuresFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment ,mesuresFragment).commit();
            mesuresFragment.onMeasureServiceConnected();
        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    protected void notifyFragmentConnected(int id) {
    }

    /**
     * Utility function to call the "onGarbageServiceDisconnected" method of fragments.
     *
     * Note: currently not used by the application, just here for completion. :)
     *
     * @param id The ID of the fragment to notify
     */
    protected void notifyFragmentDisconnected(int id) {
    }
    public MeasureService getService() {
        return service;
    }
}
