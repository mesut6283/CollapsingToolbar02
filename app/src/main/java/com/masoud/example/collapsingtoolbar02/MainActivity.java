package com.masoud.example.collapsingtoolbar02;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    TextView tv, tv2;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //---------------change font
        tv = (TextView) findViewById(R.id.textview1);
        tv2 = (TextView) findViewById(R.id.textview2);
        Typeface tp = Typeface.createFromAsset(getAssets(), "fonts/journal.ttf");
        tv.setTypeface(tp);

        Typeface tp2 = Typeface.createFromAsset(getAssets(), "fonts/journal.ttf");
        tv2.setTypeface(tp2);
        //---------------change font

        //--------------------Rounded image
        imageView = (ImageView) findViewById(R.id.imageView);
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.love2);
        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(getResources(), mBitmap);
        drawable.setCircular(true);
        // drawable.setCornerRadius(Math.max(mBitmap.getWidth(), mBitmap.getHeight()) / 2.0f);
        imageView.setImageDrawable(drawable);
        //------------------------------------------

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        collapsingToolbarLayout.setTitle("Rahaaa");

        // collapsingToolbarLayout.setExpandedTitleColor(getResources.getColor(R.color.RED));
        // collapsingToolbarLayout.setCollapsedTitleTextColor(R.color.RED);

        // collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        // collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedappbar);

        dynamicToolbarColor();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private void dynamicToolbarColor() {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.love);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {

            @Override
            public void onGenerated(Palette palette) {
                int primColor = palette.getMutedColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
                int primDarkColor = palette.getDarkMutedColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
                collapsingToolbarLayout.setContentScrimColor(primColor);
                collapsingToolbarLayout.setStatusBarScrimColor(primDarkColor);
            }
        });
    }
}
