package com.pioneer.aaron.fitness.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.pioneer.aaron.fitness.R;
import com.pioneer.aaron.fitness.Utils.Constant;

/**
 * Created by Aaron on 9/7/15.
 */
public class PlaygroundDetails extends AppCompatActivity {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.playground_item_details);

        initView();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        //layout views
        final Bundle bundle = getIntent().getExtras();
        ((ImageView) findViewById(R.id.playground_thumnail)).setImageResource(bundle.getInt(Constant.PLAYGROUND_THUMBNAIL));
        ((TextView) findViewById(R.id.playground_id)).setText(bundle.getString(Constant.PLAYGROUND_ID));
        ((TextView) findViewById(R.id.playground_location)).setText(bundle.getString(Constant.PLAYGROUND_LOCATION));
        ((TextView) findViewById(R.id.playground_type)).setText(bundle.getString(Constant.PLAYGROUND_TYPE));
        ((TextView) findViewById(R.id.playground_ma)).setText(bundle.getString(Constant.PLAYGROUND_MA));
        ((TextView) findViewById(R.id.playground_circle)).setText(bundle.getString(Constant.PLAYGROUND_TRACK));
        ((TextView) findViewById(R.id.business_hour)).setText(bundle.getString(Constant.PLAYGROUND_BH));
        ((TextView) findViewById(R.id.price)).setText(bundle.getString(Constant.PLAYGROUND_PRICE));
        ((TextView) findViewById(R.id.brief)).setText(bundle.getString(Constant.PLAYGROUND_BRIEF));

        final String phone_num;
        phone_num = bundle.getString(Constant.PLAYGROUND_NUM);
        ((ImageButton)findViewById(R.id.phone)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phone_num.equals("0")) {
                    Snackbar.make(findViewById(R.id.parent_layout), "no number found", Snackbar.LENGTH_SHORT)
                            .setAction("OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            })
                            .show();
                } else {
                    Snackbar.make(findViewById(R.id.parent_layout), "Dial " + phone_num, Snackbar.LENGTH_LONG)
                            .setAction("Dial", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone_num));
                                    startActivity(dialIntent);
                                }
                            })
                            .show();
                }

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
