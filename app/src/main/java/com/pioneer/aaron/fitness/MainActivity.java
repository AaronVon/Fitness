package com.pioneer.aaron.fitness;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pioneer.aaron.fitness.Activities.MyAccountActivity;
import com.pioneer.aaron.fitness.Fragments.ActivityFragment;
import com.pioneer.aaron.fitness.Fragments.CircleFragment;
import com.pioneer.aaron.fitness.Fragments.HistoryFragment;
import com.pioneer.aaron.fitness.Fragments.MainFragment;
import com.pioneer.aaron.fitness.Utils.Constant;
import com.pioneer.aaron.fitness.Utils.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private FragmentManager fragmentManager;

    private Handler postDelayHandler;

    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView(savedInstanceState);
    }

    private void initView(Bundle savedInstanceState) {
        //init widgets
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);

        fragmentManager = getSupportFragmentManager();


        //set NavigationView item selected listener
        mNavigationView.setNavigationItemSelectedListener(new myNavigationItemClickListener());

        initHeaderView();

        if (null == savedInstanceState) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_content, MainFragment.newInstance())
                    .commit();
        }
    }

    private class myNavigationItemClickListener implements NavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            menuItem.setChecked(true);
            final int id = menuItem.getItemId();
            postDelayHandler = new Handler();
            postDelayHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    switch (id) {
                        case R.id.menu_drawer_homepage:
                            fragmentManager.beginTransaction()
                                    .replace(R.id.main_content, MainFragment.newInstance())
                                    .commit();
                            break;
                        case R.id.menu_drawer_history:
                            fragmentManager.beginTransaction()
                                    .replace(R.id.main_content, HistoryFragment.newInstance())
                                    .commit();
                            break;
                    }
                }
            }, Constant.DRAWER_CLOSE_DELAY_MS);


            mDrawerLayout.closeDrawers();
            return false;
        }
    }

    /**
     * customize headerView in codes
     */
    private void initHeaderView() {
        View headerView = LayoutInflater.from(this).inflate(R.layout.drawer_header, null);
        ((ImageView) headerView.findViewById(R.id.user_thumbnail)).setImageBitmap(
                RoundedImageView.getCroppedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.me), 500));
        ((TextView) headerView.findViewById(R.id.user_id)).setText("Aaron");
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyAccountActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.keep);
            }
        });
        mNavigationView.addHeaderView(headerView);
    }

    /**
     * double click back button to exit app
     * */
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
        } else {
            if (System.currentTimeMillis() - exitTime < 2000) {
                finish();
            } else {
                exitTime = System.currentTimeMillis();
                Snackbar.make(findViewById(R.id.main_content), "Press again to exit.", Snackbar.LENGTH_SHORT).show();
            }
        }
    }
}
