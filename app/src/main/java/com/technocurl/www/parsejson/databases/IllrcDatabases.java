package com.technocurl.www.parsejson.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dinesh on 9/17/16.
 */
public class IllrcDatabases extends SQLiteOpenHelper {

    Cursor cursor;

    private static final String LOG = "dinesh";
    private static final String DATABASE_NAME = "illrc";
    private static final int DATABASE_VERSION = 1;

    //databases table name
    private  static  final String TABLE_INFO="info";
    private static final String TABLE_NAJIR="najir";
    private static final String TABLE_KANUN_CHETRAGAT="kanun_chetragat";
    private static final String TABLE_KANUN_BISAYEGAT="kanun_bisayegat";
    private static final String TABLE_GAZET_MANTRALAGAT="gazet_mantralagat";
    private static final String TABLE_GAZET_BISAYGAT="gazet_bisayegat";

    //table element info
    private static final String ID="_id";
    private static  final String PHONE="phone";
    private static final String STATUS="flag";
    private static final String USER="user";

    //table element najir
    private static final  String PUBLICATION = "publication";
    //table element kanun_chetragat
    private static  final String CHETRAGAT_BARGIKARAN="chetragat_kanun";
    //table element kanun_bisayaget
    private static final String KANUNI_BARGIKARAN="bisayagat_kanun";
    //table element gazet mantralagat
    private static final String GAZET_MANTRALAGAT="mantralagat_gazet";
    //table element gazet bisayagat
    private static final String GAZET_BISAYAGAT="bisayagat_gazet";

    //create table quert
    private static final String CREATE_TABLE_INFO = " create table  " + TABLE_INFO + "(" + ID + " integer primary key, " + PHONE + " text , "  + STATUS + " text , " +  USER + " text " + ")";
    private static final String CREATE_TABLE_NAJIR = " create table  " + TABLE_NAJIR + "(" + ID + " integer primary key, " +  PUBLICATION + " text " + ")";
    private static final String CREATE_TABLE_KANUN_CHETRAGAT_ = " create table  " + TABLE_KANUN_CHETRAGAT + "(" + ID + " integer primary key, " +  CHETRAGAT_BARGIKARAN + " text " + ")";
    private static final String CREATE__KANUN_BISAYEGAT = " create table  " + TABLE_KANUN_BISAYEGAT + "(" + ID + " integer primary key, " +  KANUNI_BARGIKARAN + " text " + ")";
    private static final String CREATE__GAZET_MANTRALAGAT = " create table  " + TABLE_GAZET_MANTRALAGAT + "(" + ID + " integer primary key, " +  GAZET_MANTRALAGAT + " text " + ")";
    private static final String CREATE__GAZET_BISAYGAT = " create table  " + TABLE_GAZET_BISAYGAT + "(" + ID + " integer primary key, " +  GAZET_BISAYAGAT + " text " + ")";


    public IllrcDatabases(Context context) {
        super(context, /*DATABASE_NAME*/"/mnt/sdcard/illrc.db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_INFO);
        db.execSQL(CREATE_TABLE_NAJIR);
        db.execSQL(CREATE_TABLE_KANUN_CHETRAGAT_);
        db.execSQL(CREATE__KANUN_BISAYEGAT);
        db.execSQL(CREATE__GAZET_MANTRALAGAT);
        db.execSQL(CREATE__GAZET_BISAYGAT);

        Log.d("info", CREATE_TABLE_INFO);
        Log.d("najir", CREATE_TABLE_NAJIR);
        Log.d("kanun chetragat", CREATE_TABLE_KANUN_CHETRAGAT_);
        Log.d("kanun bisayagat", CREATE__KANUN_BISAYEGAT);
        Log.d("gazet mantralagat",CREATE__GAZET_MANTRALAGAT);
        Log.d("gazet bisayagat",CREATE__GAZET_BISAYGAT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INFO);
        db.execSQL("DROP IF EXISTS " + TABLE_GAZET_BISAYGAT);
        db.execSQL("DROP IF EXISTS " + TABLE_GAZET_MANTRALAGAT);
        db.execSQL("DROP IF EXISTS " + TABLE_KANUN_BISAYEGAT);
        db.execSQL("DROP IF EXISTS " + TABLE_KANUN_CHETRAGAT);
        db.execSQL("DROP IF EXISTS " + TABLE_NAJIR);

    }
    public void deletNajirItem(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("delet from najir where _id = " + id );

    }

    public long insertNajir(String no) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PUBLICATION, no);
        return sqLiteDatabase.insert(TABLE_NAJIR, null, contentValues);
    }


    public long insertKanun(String no) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CHETRAGAT_BARGIKARAN, no);
        return sqLiteDatabase.insert(TABLE_KANUN_CHETRAGAT, null, contentValues);
    }
    public long insertgazet(String no) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GAZET_MANTRALAGAT, no);
        return sqLiteDatabase.insert(TABLE_GAZET_MANTRALAGAT, null, contentValues);
    }


    public long insertInfo(String no,  String status,String code) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PHONE, no);
        contentValues.put(STATUS, status);
        contentValues.put(USER, code);
        return sqLiteDatabase.insert(TABLE_INFO, null, contentValues);
    }
    public String getStatus(){
        String img= null;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String selectquery= "select flag from info " ;
        Cursor cursor = sqLiteDatabase.rawQuery(selectquery,null);
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToPosition(0);
            do {
                img=cursor.getString(cursor.getColumnIndex("flag"));
            }while (cursor.moveToNext());

        }
        return img;
    }
    public String getPublicationbyId(){
        String img= null;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String selectquery= "select flag from info " ;
        Cursor cursor = sqLiteDatabase.rawQuery(selectquery,null);
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToPosition(0);
            do {
                img=cursor.getString(cursor.getColumnIndex("flag"));
            }while (cursor.moveToNext());

        }
        return img;
    }
    public String getMantralayaById(){
        String img= null;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String selectquery= "select flag from gazet_mantralagat " ;
        Cursor cursor = sqLiteDatabase.rawQuery(selectquery,null);
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToPosition(0);
            do {
                img=cursor.getString(cursor.getColumnIndex("flag"));
            }while (cursor.moveToNext());

        }
        return img;
    }
    public String getChetragatBargikaran(String id){
        String img= null;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String selectquery= "select chetragat_kanun from kanun_chetragat where _id = " + id;
        Cursor cursor = sqLiteDatabase.rawQuery(selectquery,null);
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToPosition(0);
            do {
                img=cursor.getString(cursor.getColumnIndex("chetragat_kanun"));
            }while (cursor.moveToNext());

        }
        return img;
    }

    public ArrayList<String> getnajirPublication(){
        ArrayList<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT publication FROM " + TABLE_NAJIR;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(cursor.getColumnIndexOrThrow("publication")));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }
    public ArrayList<String> getkanunchetragat(){
        ArrayList<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT chetragat_kanun FROM " + TABLE_KANUN_CHETRAGAT;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(cursor.getColumnIndexOrThrow("chetragat_kanun")));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }

    public String getKeyCardType(String _id) {
        String bankbalresult = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String selectquery = "select card_nature from  profile where _id = " + _id;
        Cursor cursor = db.rawQuery(selectquery, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            do {
                bankbalresult = cursor.getString(cursor.getColumnIndex("card_nature"));
            } while (cursor.moveToNext());
        }
        return bankbalresult;
    }


    public ArrayList<String> getgazetbisayagat(){
        ArrayList<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT mantralagat_gazet FROM " + TABLE_GAZET_MANTRALAGAT;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(cursor.getColumnIndexOrThrow("mantralagat_gazet")));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }

}
