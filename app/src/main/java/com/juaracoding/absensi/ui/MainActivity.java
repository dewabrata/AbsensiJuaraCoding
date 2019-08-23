package com.juaracoding.absensi.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.juaracoding.absensi.R;
import com.juaracoding.absensi.adapter.AdapterUserList;
import com.juaracoding.absensi.model.DummyDataFactory;

public class MainActivity extends AppCompatActivity {
    private ActionBar actionBar;
    private Toolbar toolbar;
    private RecyclerView listUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        listUser = (RecyclerView)findViewById(R.id.listUser);
        initToolbar();
        initNavigationMenu();

        initData();
    }

    private void initData() {

        AdapterUserList toadapter = new AdapterUserList (this,new DummyDataFactory().createUser());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        listUser.setLayoutManager(linearLayoutManager);

        listUser.setAdapter(toadapter);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.pink_600));
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Drawer No Icon");
        //Tools.setSystemBarColor(this, R.color.pink_700);
    }

    private void initNavigationMenu() {
        final NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerOpened(View drawerView) {
               // updateCounter(nav_view);
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem item) {
                Toast.makeText(getApplicationContext(), item.getTitle() + " Selected", Toast.LENGTH_SHORT).show();
                actionBar.setTitle(item.getTitle());
                drawer.closeDrawers();
                return true;
            }
        });

        // open drawer at start
        //drawer.openDrawer(GravityCompat.START);
       // updateCounter(nav_view);
    }
}
