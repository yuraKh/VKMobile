package test.com.vk.vkmobile.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKList;

import test.com.vk.vkmobile.R;
import test.com.vk.vkmobile.adapters.FeedAdapter;
import test.com.vk.vkmobile.api.ApiFactory;
import test.com.vk.vkmobile.api.ApiResponse;
import test.com.vk.vkmobile.halper.CircleTransform;
import test.com.vk.vkmobile.loader.NewsFeedLoader;
import test.com.vk.vkmobile.models.feed.NewsFeed;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, LoaderManager.LoaderCallbacks<ApiResponse> {


    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm);
        VKApi.users().get(VKParameters.from(VKApiConst.FIELDS, "photo_200, online"))
                .executeWithListener(new VKRequest.VKRequestListener() {
                    @Override
                    public void onComplete(VKResponse response) {
                        VKApiUserFull user = ((VKList<VKApiUserFull>) response.parsedModel).get(0);

                        Log.d("User name", user.first_name + " " + user.last_name + " " + user.photo_50);
                        final ImageView profileImg = (ImageView) findViewById(R.id.imageView);
                        TextView nameText = (TextView) findViewById(R.id.nameTextView);
                        nameText.setText(user.first_name + " " + user.last_name);
                        TextView emailText = (TextView) findViewById(R.id.emailTextView);
                        if(user.online)
                        emailText.setText("Online");
                        else
                            emailText.setText("Offline");
                        Picasso.with(MainActivity.this).load(user.photo_200).transform(new CircleTransform())
                                .fit().centerCrop().into(profileImg, new  Callback.EmptyCallback() {

                            //When the image is loaded, palette will take the color and apply it to the other imageView

                            @Override public void onSuccess()
                            {
                                final Bitmap bitmap = ((BitmapDrawable) profileImg.getDrawable()).getBitmap();// Ew!
                                Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
                                    public void onGenerated(Palette palette)
                                    {

                                        if (palette != null)
                                        {

                                            Palette.Swatch vibrantSwatch = palette.getDominantSwatch();

                                            if (vibrantSwatch != null)
                                            {
                                                final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.navHeaderLinear);
                                                linearLayout.setBackgroundColor(vibrantSwatch.getRgb());

                                            }
                                        }
                                    }
                                });
                            }
                        });
                    }
                });


        ApiFactory.init();
        getSupportLoaderManager().initLoader(0, Bundle.EMPTY, this);

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

        if (id == R.id.nav_massages) {
            // Handle the camera action
        } else if (id == R.id.nav_friends) {

        } else if (id == R.id.nav_communities) {

        } else if (id == R.id.nav_photos) {

        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_search) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public Loader<ApiResponse> onCreateLoader(int id, Bundle args) {
        return new NewsFeedLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<ApiResponse> loader, ApiResponse data) {
        NewsFeed newsFeed = data.getTypedAnswer();
        FeedAdapter feedAdapter = new FeedAdapter(this, newsFeed.getResponse());
        recyclerView.setAdapter(feedAdapter);
    }

    @Override
    public void onLoaderReset(Loader<ApiResponse> loader) {

    }
}
