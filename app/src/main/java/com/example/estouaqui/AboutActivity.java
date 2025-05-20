package com.example.estouaqui;
//
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//
//public class AboutActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_about);
//
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Sobre");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        TextView aboutText = findViewById(R.id.about_text);
//        aboutText.setText("O aplicativo Mensagem de Vida foi criado com carinho para oferecer " +
//                "suporte e esperança para pessoas que estão passando por momentos difíceis.\n\n" +
//                "Desenvolvido por [Seu Nome], este projeto nasceu da crença de que pequenas " +
//                "mensagens positivas podem fazer uma grande diferença na vida de alguém.\n\n" +
//                "Se você está enfrentando pensamentos suicidas ou de automutilação, " +
//                "por favor, busque ajuda profissional:\n" +
//                "Centro de Valorização da Vida (CVV): 188\n" +
//                "Site: www.cvv.org.br");
//
//        Button supportButton = findViewById(R.id.support_button);
//        supportButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Altere para o seu link de apoio/doação
//                String url = "https://seusite.com/apoie";
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse(url));
//                startActivity(intent);
//            }
//        });
//    }
//
//    @Override
//    public boolean onSupportNavigateUp() {
//        onBackPressed();
//        return true;
//    }
//}

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sobre");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView aboutText = findViewById(R.id.about_text);
        aboutText.setText("O aplicativo Mensagem de Vida foi criado com carinho para oferecer " +
                "suporte e esperança para pessoas que estão passando por momentos difíceis.\n\n" +
                "Desenvolvido por [Seu Nome], este projeto nasceu da crença de que pequenas " +
                "mensagens positivas podem fazer uma grande diferença na vida de alguém.\n\n" +
                "Se você está enfrentando pensamentos suicidas ou de automutilação, " +
                "por favor, busque ajuda profissional:\n" +
                "Centro de Valorização da Vida (CVV): 188\n" +
                "Site: www.cvv.org.br");

        Button supportButton = findViewById(R.id.support_button);
        supportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Altere para o seu link de apoio/doação
                String url = "https://seusite.com/apoie";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}