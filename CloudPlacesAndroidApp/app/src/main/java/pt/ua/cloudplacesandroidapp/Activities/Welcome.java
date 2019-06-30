package pt.ua.cloudplacesandroidapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import pt.ua.cloudplacesandroidapp.API.CommunicationWithAPI;
import pt.ua.cloudplacesandroidapp.API.House;
import pt.ua.cloudplacesandroidapp.ApiClient;
import pt.ua.cloudplacesandroidapp.AppConstants;
import pt.ua.cloudplacesandroidapp.Fragments.AllAccommodations;
import pt.ua.cloudplacesandroidapp.Fragments.Settings;
import pt.ua.cloudplacesandroidapp.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Welcome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Timer timer;
    public static int notification_counter = 0;
    CommunicationWithAPI apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment f = new AllAccommodations();
        ft.replace(R.id.content_frame, f);
        ft.commit();

        View headerView = navigationView.getHeaderView(0);
        TextView t1 = (TextView) headerView.findViewById(R.id.username_menu);
        TextView t2 = (TextView) headerView.findViewById(R.id.email_menu);
        ImageView i1 = (ImageView) headerView.findViewById(R.id.photo_menu);
        t1.setText(AppConstants.CURRENT_PERSON.getName());
        t2.setText(AppConstants.CURRENT_PERSON.getEmail());
        String base64Image = AppConstants.CURRENT_PERSON.getPhoto().split(",")[1];
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        Glide.with(this).asBitmap().load(decodedString).into(i1);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerMethod();
            }

        }, 0, 10000);
    }

    private void TimerMethod()
    {
        //This method is called directly by the timer
        //and runs in the same thread as the timer.

        //We call the method that will work with the UI
        //through the runOnUiThread method.
        this.runOnUiThread(Timer_Tick);
    }

    private Runnable Timer_Tick = new Runnable() {
        @Override
        public void run() {
            System.out.println("ENTREI");
            apiInterface = ApiClient.getClient().create(CommunicationWithAPI.class);
            Call<List<House>> call = apiInterface.getProperties();
            if (AppConstants.CURRENT_HOUSES == null){
                call.enqueue(new Callback<List<House>>() {
                    @Override
                    public void onResponse(Call<List<House>> call, Response<List<House>> response) {
                        AppConstants.CURRENT_HOUSES = response.body();
                    }

                    @Override
                    public void onFailure(Call<List<House>> call, Throwable t) {
                        AppConstants.CURRENT_HOUSES = new ArrayList<>();
                    }
                });
            }else{
                call.enqueue(new Callback<List<House>>() {
                    @Override
                    public void onResponse(Call<List<House>> call, Response<List<House>> response) {
                        List<House> temp = response.body();
                        for (House h1 : temp) {
                            boolean contains = false;
                            for (House h2 : AppConstants.CURRENT_HOUSES){
                                if (h1.getHouseId() == h2.getHouseId()) contains = true;
                            }
                            if (!contains) notification(h1.getName(), h1.getDescription());
                        }
                        AppConstants.CURRENT_HOUSES = temp;
                    }

                    @Override
                    public void onFailure(Call<List<House>> call, Throwable t) {
                        AppConstants.CURRENT_HOUSES = new ArrayList<>();
                    }
                });
            }
        }
    };

    private void notification(String house, String description){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, "notify_001");

        mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
        mBuilder.setContentTitle("NEW HOUSE: " + house);
        mBuilder.setContentText(description);
        mBuilder.setPriority(Notification.PRIORITY_MAX);

        NotificationManager mNotificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "YOUR_CHANNEL_ID";
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            mNotificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }

        mNotificationManager.notify(notification_counter, mBuilder.build());
        notification_counter++;
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        int n = item.getItemId();
        return true;
    }

    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;
        Bundle b = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_log_out:
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
                                startActivity(new Intent(Welcome.this, LoginActivity.class));
                                finish();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(Welcome.this);
                builder.setMessage(R.string.question_logout)
                        .setNegativeButton(getString(R.string.str_no), dialogClickListener)
                        .setPositiveButton(getString(R.string.str_yes), dialogClickListener).show();
                break;
            case R.id.nav_settings:
                fragment = new Settings();
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        //calling the method displayselectedscreen and passing the id of selected menu
        displaySelectedScreen(item.getItemId());
        //make this method blank
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
