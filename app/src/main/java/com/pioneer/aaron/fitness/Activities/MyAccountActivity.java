package com.pioneer.aaron.fitness.Activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.pioneer.aaron.fitness.R;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by Aaron on 9/1/15.
 */
public class MyAccountActivity extends AppCompatActivity{

    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myaccount_layout);

        init();
    }

    private void init() {
        mToolbar = (Toolbar) findViewById(R.id.account_tool_bar);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.account_collapsing_toolbar);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        mCollapsingToolbarLayout.setTitle(getString(R.string.account));
        mCollapsingToolbarLayout.setExpandedTitleColor(android.R.color.transparent);

        supportPostponeEnterTransition();

        /*//I'm not sure what is LinearLayoutManager for
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);*/

        //automatic get the color set using Palette and set it to CollapsingToolBar
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.me);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int defaultColor = getResources().getColor(R.color.colorPrimary);
                int defaultTitleColor = getResources().getColor(R.color.white);
                int bgColor = palette.getMutedColor(defaultColor);
                int titleColor = palette.getLightVibrantColor(defaultTitleColor);
                mCollapsingToolbarLayout.setContentScrimColor(bgColor);
                mCollapsingToolbarLayout.setCollapsedTitleTextColor(titleColor);
                mCollapsingToolbarLayout.setExpandedTitleColor(titleColor);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}
