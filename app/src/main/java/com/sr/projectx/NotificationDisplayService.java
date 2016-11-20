package com.sr.projectx;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class NotificationDisplayService extends Service {
final int NOTIFICATION_ID=30;
    private boolean isNotificationVisible() {
        Intent notificationIntent = new Intent(getApplicationContext(), NotificationDisplayService.class);
        PendingIntent test = PendingIntent.getActivity(getApplicationContext(), NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_NO_CREATE);
        return test != null;
    }
    Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
     public static Bundle mMyAppsBundle = new Bundle();

    public NotificationDisplayService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        displaynoti("Ayen","بسم الله الرحمن الرحيم , تجربة الاشعار");

        return super.onStartCommand(intent, flags, startId);
    }

    public void displaynoti(String title,String text){

Intent notiI=new Intent(this,MainActivity.class);
        PendingIntent notiPI=PendingIntent.getActivity(this,0,notiI,0);

        Intent notiI2=new Intent(this,MyService.class);
        PendingIntent notiPI2=PendingIntent.getActivity(this,0,notiI2,0);
        Resources res = getApplicationContext().getResources();

        Bitmap large_icon = BitmapFactory.decodeResource(res,R.mipmap.ainico);


        NotificationCompat.Builder noti=new    NotificationCompat.Builder(this)
.setContentTitle(title)
.setContentText(text)
 .setSmallIcon(R.mipmap.ainn)
   .setLargeIcon(large_icon)
.setColor(getResources().getColor(R.color.colorAccent))
.setContentIntent(notiPI)
                .setSound(uri, AudioManager.STREAM_RING)
                .setVibrate(new long[] { 0, 1000 })
                .setOngoing(false)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .setNumber(1)
                .setLights(Color.YELLOW, 1000, 1000)
                .setPriority(NotificationCompat.PRIORITY_MAX)

.setStyle(new NotificationCompat.BigTextStyle().bigText(text))
                .addAction(R.mipmap.ic_launcher,"هذا زر ",notiPI2);
        NotificationManager notiM =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
         noti.setAutoCancel(true);

        notiM.notify(NOTIFICATION_ID,noti.build());









    }
}
