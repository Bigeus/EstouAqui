package com.example.estouaqui;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//public class DatabaseHelper extends SQLiteOpenHelper {
//
//    private static final String DATABASE_NAME = "mensagens.db";
//    private static final int DATABASE_VERSION = 1;
//
//    private static final String TABLE_MESSAGES = "mensagens";
//    private static final String COLUMN_ID = "_id";
//    private static final String COLUMN_MESSAGE = "mensagem";
//
//    public DatabaseHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String createTableQuery = "CREATE TABLE " + TABLE_MESSAGES + " (" +
//                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                COLUMN_MESSAGE + " TEXT NOT NULL)";
//        db.execSQL(createTableQuery);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGES);
//        onCreate(db);
//    }
//
//    public void initializeMessages() {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        // Verificar se já existem mensagens no banco
//        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_MESSAGES, null);
//        cursor.moveToFirst();
//        int count = cursor.getInt(0);
//        cursor.close();
//
//        if (count == 0) {
//            // Adicionar mensagens iniciais
//            String[] messages = {
//                    "Você é mais forte do que imagina. Cada pequeno passo conta na sua jornada.",
//                    "Lembre-se que é normal ter dias difíceis. O importante é não desistir.",
//                    "Sua presença no mundo faz diferença. Você importa muito mais do que imagina.",
//                    "Respire fundo. Este momento vai passar e dias melhores virão.",
//                    "Você merece amor, especialmente o amor próprio. Cuide-se com gentileza hoje."
//            };
//
//            for (String message : messages) {
//                ContentValues values = new ContentValues();
//                values.put(COLUMN_MESSAGE, message);
//                db.insert(TABLE_MESSAGES, null, values);
//            }
//        }
//    }
//
//    public String getRandomMessage() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String message = "Você é incrível!"; // Mensagem padrão
//
//        Cursor cursor = db.rawQuery("SELECT " + COLUMN_MESSAGE + " FROM " + TABLE_MESSAGES, null);
//
//        if (cursor.moveToFirst()) {
//            List<String> messages = new ArrayList<>();
//            do {
//                messages.add(cursor.getString(0));
//            } while (cursor.moveToNext());
//
//            Random random = new Random();
//            message = messages.get(random.nextInt(messages.size()));
//        }
//
//        cursor.close();
//        return message;
//    }
//}

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mensagens.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_MESSAGES = "mensagens";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_MESSAGE = "mensagem";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_MESSAGES + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_MESSAGE + " TEXT NOT NULL)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGES);
        onCreate(db);
    }

    public void initializeMessages() {
        SQLiteDatabase db = this.getWritableDatabase();

        // Verificar se já existem mensagens no banco
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_MESSAGES, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();

        if (count == 0) {
            // Adicionar mensagens iniciais
            String[] messages = {
                    "Você é mais forte do que imagina. Cada pequeno passo conta na sua jornada.",
                    "Lembre-se que é normal ter dias difíceis. O importante é não desistir.",
                    "Sua presença no mundo faz diferença. Você importa muito mais do que imagina.",
                    "Respire fundo. Este momento vai passar e dias melhores virão.",
                    "Você merece amor, especialmente o amor próprio. Cuide-se com gentileza hoje."
            };

            for (String message : messages) {
                ContentValues values = new ContentValues();
                values.put(COLUMN_MESSAGE, message);
                db.insert(TABLE_MESSAGES, null, values);
            }
        }
    }

    public String getRandomMessage() {
        SQLiteDatabase db = this.getReadableDatabase();
        String message = "Você é incrível!"; // Mensagem padrão

        Cursor cursor = db.rawQuery("SELECT " + COLUMN_MESSAGE + " FROM " + TABLE_MESSAGES, null);

        if (cursor.moveToFirst()) {
            List<String> messages = new ArrayList<>();
            do {
                messages.add(cursor.getString(0));
            } while (cursor.moveToNext());

            Random random = new Random();
            message = messages.get(random.nextInt(messages.size()));
        }

        cursor.close();
        return message;
    }
}