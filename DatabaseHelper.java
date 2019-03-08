package com.madt.sree.assignment_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String database_name = "login.db";

    private String table_name = "log";

    private String column_id = "id";

    private String column_name = "username";

    private String column_password = "password";

    private String create_table_statment = "create table "+table_name+"("+column_id+" integer primary key autoincrement ," + column_name +" text not null ,"+ column_password+" text not null );";

    private String drop_table_if_exists_statement = "DROP TABLE " + table_name +";";
    private String pull_database_query = "SELECT * FROM " + table_name +";";

    public DatabaseHelper(Context context)
    {
        super(context,database_name,null,2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(create_table_statment);
        System.out.println(" >>>>>>>>>>> Table Created <<<<<<<<<<");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL(drop_table_if_exists_statement);
        System.out.println(" >>>>>>>>>>> Table Dropped <<<<<<<<<<");
    }


    public void pull_database()
    {
        SQLiteDatabase data = this.getReadableDatabase();
        Cursor res = data.rawQuery(pull_database_query,null);

        while (res.moveToNext())
        {
           // System.out.println(res[0]);
        }



    }

    public boolean successful_login_check(String name, String password)
    {
        SQLiteDatabase sb = this.getReadableDatabase();
        String query = "select "+column_id+" from "+table_name+" where "+column_name+" = " + "'"+ name+"'"+" and " +column_password+" = " + "'"+ password +"'";
        Cursor res = null;

        Log.e(">>>>>>>> debug ", create_table_statment );
        Log.e(">>>>>> Sreejith ", query);
        try
        {
           res = sb.rawQuery(query,null);
        }
        catch (Exception e)
        {
            System.out.println("SQL Database Error : " + e);
        }


        if(res.getCount() == 0)
        {
            return  false;
        }

        else return true;
    }





    public boolean insertData(String username, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(column_name,username);
        cv.put(column_password,password);

        long result = db.insert(table_name,null,cv);

        if (result == -1)
        {
            return false;
        }
        else
            return  true;
    }


    public boolean search_existing_user_name(String name)
    {
        SQLiteDatabase sb = this.getReadableDatabase();
        String query = "select "+column_id+" from "+table_name+" where "+column_name+" = " + "'"+ name+"'";
        Cursor res = null;


        try
        {
            res = sb.rawQuery(query,null);
        }
        catch (Exception e)
        {
            System.out.println("SQL Database Error : " + e);
        }

        System.out.println("ddddddddddd >>>>" + res.getCount());
        if(res.getCount() == 0)
        {
            return  false;
        }

        else return true;
    }


}
