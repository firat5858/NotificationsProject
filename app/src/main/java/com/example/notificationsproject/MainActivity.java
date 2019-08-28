package com.example.notificationsproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mShowNotificationButton;
    NotificationManager mNotificationManager;
    NotificationCompat.Builder mBuilder;
    PendingIntent mResultPendingIntent;
    TaskStackBuilder mTaskStackBuilder;
    Intent mResultIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mShowNotificationButton = findViewById(R.id.btnShowNotification);

        mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle("Notification Title");
        mBuilder.setContentText("Notification Detail...");

        mResultIntent = new Intent(this, MainActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mTaskStackBuilder = TaskStackBuilder.create(this);
            mTaskStackBuilder.addParentStack(MainActivity.this);
            mTaskStackBuilder.addNextIntent(mResultIntent);
            mResultPendingIntent = mTaskStackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        }

        mBuilder.setContentIntent(mResultPendingIntent);
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mShowNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNotificationManager.notify(1,mBuilder.build());
            }
        });


    }
    





}
