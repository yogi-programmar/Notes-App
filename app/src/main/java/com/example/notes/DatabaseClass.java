package com.example.notes;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import android.widget.Toast;


public class DatabaseClass extends SQLiteOpenHelper {
    Context context;
    public  static  final  int DTABASE_VERSION=2;
    public  static  final  String DATABSE_NAME="ind";
    public  static  final  String  TABLE_NAME ="notes";



    //COULUMNS
    public static  final  String KEY_ID="id";
    public static  final  String  KEY_TITLE="title";
    public static  final String KEY_NOTE="description";

    public  static  final String CREATE_TABLE=" CREATE TABLE "+TABLE_NAME+"("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            KEY_TITLE+"TEXT NOT NULL,"+KEY_NOTE+"TEXT);";


    public DatabaseClass( Context context) {
        super( context, DATABSE_NAME, null, DTABASE_VERSION);
        this.context=context;


    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public  void addNotes(String TITLE,String Note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE,TITLE);
        values.put(KEY_NOTE,Note);
        long result= db.insert(TABLE_NAME,null,values);
        if(result==-1){
//            Toast.makeText(context,"not",Toast.LENGTH_SHORT).show();


        }else {
            Toast.makeText(context, "yes", Toast.LENGTH_SHORT).show();
        }

    }


    Cursor readAllData(){
        String query="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase database= this.getReadableDatabase();
        Cursor cursor= null;
        if(database!=null){
            cursor= database.rawQuery(query,null);

        }


        return  cursor;

    }

    void deleteAllNotes() {
        SQLiteDatabase database= this.getWritableDatabase();
        String query="DELETE FROM "+TABLE_NAME;
        database.execSQL(query);

    }
}

