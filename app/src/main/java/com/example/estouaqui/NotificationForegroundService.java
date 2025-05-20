package com.example.estouaqui;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

public class NotificationForegroundService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(1, createNotification());
        return START_STICKY;
    }

    private Notification createNotification() {
        NotificationChannel channel = new NotificationChannel(
                "service_channel",
                "Background Service",
                NotificationManager.IMPORTANCE_LOW
        );
        getSystemService(NotificationManager.class).createNotificationChannel(channel);

        return new NotificationCompat.Builder(this, "service_channel")
                .setContentTitle("Mensagem de Vida")
                .setContentText("Executando em segundo plano")
                .setSmallIcon(R.drawable.ic_heart)
                .build();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}