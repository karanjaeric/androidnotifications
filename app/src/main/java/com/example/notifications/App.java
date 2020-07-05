package com.example.notifications;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {
    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2";

    @Override
    //Callback immediately after starting the app
    //perfect place to define channels
    public void onCreate() {
        super.onCreate();

        ceateNotificationChannels();
    }

    private void ceateNotificationChannels() {
        //check if we are on android oreo or higher(api level 26 and above)
        //channel are only available to api level 26 and higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID, "Channel1", NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is Channel 1");
            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID, "Channel2", NotificationManager.IMPORTANCE_LOW
            );
            channel2.setDescription("This is Channel 2");
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }
    }
}
