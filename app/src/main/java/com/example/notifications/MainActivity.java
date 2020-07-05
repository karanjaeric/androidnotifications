package com.example.notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;
    private EditText editTextTitle;
    private EditText editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = NotificationManagerCompat.from(this);
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextMessage = findViewById(R.id.edit_text_message);
    }


    public void sendOnChannel1(View view) {
        String message = editTextMessage.getText().toString();
        String title = editTextTitle.getText().toString();
        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, activityIntent, 0);

        Intent broadCastIntent = new Intent(this, BroadcastReceiver.class);
        broadCastIntent.putExtra("toastMessage", message);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this, 0, broadCastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this, App.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.BLUE)
                .setAutoCancel(true)
                .setContentIntent(contentIntent)
                .setOnlyAlertOnce(false)
                .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
                .build();
        notificationManager.notify(1, notification);

    }

    public void sendOnChannel2(View view) {
        Notification notification = new NotificationCompat.Builder(this, App.CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_two)
                .setContentTitle(editTextTitle.getText().toString())
                .setContentText(editTextMessage.getText().toString())
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();
        notificationManager.notify(1, notification);
    }
}
