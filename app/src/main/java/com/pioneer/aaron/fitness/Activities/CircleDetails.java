package com.pioneer.aaron.fitness.Activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pioneer.aaron.fitness.Fragments.TrackDialogFragment;
import com.pioneer.aaron.fitness.R;
import com.pioneer.aaron.fitness.Utils.Constant;

/**
 * Created by Aaron on 9/7/15.
 */
public class CircleDetails extends AppCompatActivity {
    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private Bundle mBundle;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_item_details);

        initView();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        mBundle = getIntent().getExtras();
        mCollapsingToolbarLayout.setTitle(mBundle.getString(Constant.P_ID));
        mCollapsingToolbarLayout.setExpandedTitleColor(android.R.color.transparent);
        supportPostponeEnterTransition();
        setCollapsingToolBarStyle();
        setPeopleDetails();
    }

    private void setPeopleDetails() {
        ((ImageView) findViewById(R.id.p_thumbnail)).setImageResource(mBundle.getInt(Constant.P_THUMBNAIL));
        ((TextView) findViewById(R.id.people_distance)).append(mBundle.getString(Constant.P_DISTANCE));
        ((TextView) findViewById(R.id.people_fans)).append(mBundle.getString(Constant.P_FANS));
        ((TextView) findViewById(R.id.people_achievement)).append(mBundle.getString(Constant.P_ACHIEVE));
        ((TextView) findViewById(R.id.people_slogan)).append(mBundle.getString(Constant.P_SLOGAN));
        ((ImageView) findViewById(R.id.track_1)).setImageResource(R.drawable.track_1);
        ((ImageView) findViewById(R.id.track_2)).setImageResource(R.drawable.track_2);
        ((ImageView) findViewById(R.id.track_3)).setImageResource(R.drawable.track_3);
        findViewById(R.id.track_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrackDialogFragment.newInstance(R.drawable.track_1)
                        .show(getSupportFragmentManager(), Constant.TAG_TRA_DIALOG);
            }
        });
        findViewById(R.id.track_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrackDialogFragment.newInstance(R.drawable.track_2)
                        .show(getSupportFragmentManager(), Constant.TAG_TRA_DIALOG);
            }
        });
        findViewById(R.id.track_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrackDialogFragment.newInstance(R.drawable.track_3)
                        .show(getSupportFragmentManager(), Constant.TAG_TRA_DIALOG);
            }
        });
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(findViewById(R.id.app_bar_layout), "Are you sure to follow him?", Snackbar.LENGTH_LONG)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                        .show();
            }
        });
    }

    private void setCollapsingToolBarStyle() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), mBundle.getInt(Constant.P_THUMBNAIL));
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
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
