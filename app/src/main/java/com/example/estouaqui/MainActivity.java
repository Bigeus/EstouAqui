//package com.example.estouaqui;
//
//import android.app.AlarmManager;
//import android.app.PendingIntent;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.core.content.ContextCompat;
//
//import java.util.Calendar;
//
//public class MainActivity extends AppCompatActivity {
//
//    private DatabaseHelper dbHelper;
//     //   private static final long INTERVAL_HOUR = 60 * 60 * 1000; // 1 hora em milissegundos
//     private static final long INTERVAL_HOUR = 60 * 1000;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // Configurar a Toolbar
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Mensagem de Vida");
//
//        // Inicializar o banco de dados
//        dbHelper = new DatabaseHelper(this);
//        dbHelper.initializeMessages();
//
//        // Configurar o texto da tela principal
//        TextView mainText = findViewById(R.id.main_text);
//        mainText.setText("Bem-vindo ao Mensagem de Vida!\n\n" +
//                "Este aplicativo foi criado para lembrar que você é importante e valioso. " +
//                "A cada hora, você receberá uma mensagem positiva para te inspirar e fortalecer.\n\n" +
//                "Lembre-se: Você não está sozinho nesta jornada. " +
//                "Cada novo dia é uma oportunidade para recomeçar.");
//
//        // Configurar o alarme para enviar notificações a cada hora
//        setupHourlyNotifications();
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_about) {
//            Intent intent = new Intent(this, AboutActivity.class);
//            startActivity(intent);
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void setupHourlyNotifications() {
//        Intent intent = new Intent(this, NotificationReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(
//                this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
//
//        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//
//        // Configurar para começar na próxima hora cheia
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//        calendar.set(Calendar.MINUTE, 0);
//
//
//        // Repetir a cada hora
//        alarmManager.setRepeating(
//                AlarmManager.RTC_WAKEUP,
//                calendar.getTimeInMillis(),
//                INTERVAL_HOUR,
//                pendingIntent
//        );
//    }
//}
package com.example.estouaqui;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int NOTIFICATION_PERMISSION_CODE = 1001;
    private static final int ALARM_REQUEST_CODE = 1002;
    private static final long INTERVAL_MINUTE = 60 * 1000; // 1 minuto em milissegundos

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Iniciar o serviço em primeiro plano
        startForegroundService(new Intent(this, NotificationForegroundService.class));

        // Configurações iniciais
        setupToolbar();
        setupDatabase();
        setupUI();
        checkAndRequestPermissions();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Mensagem de Vida");
        }
    }

    private void setupDatabase() {
        dbHelper = new DatabaseHelper(this);
        dbHelper.initializeMessages();
    }

    private void setupUI() {
        TextView mainText = findViewById(R.id.main_text);
        mainText.setText("Bem-vindo ao Mensagem de Vida!\n\n" +
                "Este aplicativo foi criado para lembrar que você é importante e valioso. " +
                "A cada minuto, você receberá uma mensagem positiva para te inspirar e fortalecer.\n\n" +
                "Lembre-se: Você não está sozinho nesta jornada.");
    }

    private void checkAndRequestPermissions() {
        // Verificar permissão de notificação (Android 13+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestNotificationPermission();
            } else {
                setupAlarm();
            }
        } else {
            setupAlarm();
        }
    }

    private void requestNotificationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.POST_NOTIFICATIONS)) {
            new AlertDialog.Builder(this)
                    .setTitle("Permissão Necessária")
                    .setMessage("Permita notificações para receber mensagens motivacionais")
                    .setPositiveButton("OK", (dialog, which) ->
                            ActivityCompat.requestPermissions(this,
                                    new String[]{Manifest.permission.POST_NOTIFICATIONS},
                                    NOTIFICATION_PERMISSION_CODE))
                    .setNegativeButton("Cancelar", null)
                    .show();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.POST_NOTIFICATIONS},
                    NOTIFICATION_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == NOTIFICATION_PERMISSION_CODE &&
                grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            setupAlarm();
        }
    }

    private void setupAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this, ALARM_REQUEST_CODE, intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        // Configurar para disparar no próximo minuto exato
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.MINUTE, 1);

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S &&
                    !alarmManager.canScheduleExactAlarms()) {
                // Solicitar permissão para alarmes exatos
                startActivity(new Intent(android.provider.Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM));
                return;
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendar.getTimeInMillis(),
                        pendingIntent);
            } else {
                alarmManager.setRepeating(
                        AlarmManager.RTC_WAKEUP,
                        calendar.getTimeInMillis(),
                        INTERVAL_MINUTE,
                        pendingIntent);
            }
            Log.d(TAG, "Alarme configurado com sucesso");
        } catch (SecurityException e) {
            Log.e(TAG, "Erro ao configurar alarme", e);
            Toast.makeText(this, "Erro ao configurar notificações", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Não pare o serviço aqui para manter em segundo plano
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}