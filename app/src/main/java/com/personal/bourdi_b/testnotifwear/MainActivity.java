package com.personal.bourdi_b.testandroidwearnotificationpages;


import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                bt = (Button) stub.findViewById(R.id.button);
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent actionIntent = new Intent(MainActivity.this, NotifActivity.class);
                        PendingIntent actionPendingIntent =
                                PendingIntent.getActivity(MainActivity.this, 0, actionIntent,
                                                          PendingIntent.FLAG_UPDATE_CURRENT);

                        Notification notificationQuestion =
                                new NotificationCompat.Builder(MainActivity.this)
                                        .setSmallIcon(R.drawable.ic_full_sad)
                                        .extend(new NotificationCompat.WearableExtender().setDisplayIntent(actionPendingIntent).setCustomSizePreset(
                                                NotificationCompat.WearableExtender.SIZE_LARGE))
                                        .build();

                        Notification notification =
                                new NotificationCompat.Builder(MainActivity.this)
                                        .setSmallIcon(R.drawable.ic_full_sad)
                                        .setContentTitle("Title")
                                        .setContentText("Swipe to the right!")
                                        .extend(new NotificationCompat.WearableExtender().addPage(notificationQuestion))
                                        .build();


                        NotificationManagerCompat notificationManager =
                                NotificationManagerCompat.from(MainActivity.this);

                        notificationManager.notify(001, notification);

                    }
                });


            }
        });


    }
}
