package com.sachinapp.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.CpuUsageInfo;
import android.printservice.CustomPrinterIconCallback;

import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {

    DbHandler(Context context)
    {
        super(context, Variables.DB_NAME, null, Variables.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + Variables.TABLE_NAME + "(" + Variables.CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Variables.CUSTOMER_NAME + " TEXT, " + Variables.CUSTOMER_AGE + " INT, " + Variables.ACTIVE_CUSTOMER + " BOOL " + ")";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(CustomerModel customerModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues data = new ContentValues();

        data.put(Variables.CUSTOMER_NAME, customerModel.getName());
        data.put(Variables.CUSTOMER_AGE, customerModel.getAge());
        data.put(Variables.ACTIVE_CUSTOMER, customerModel.getActive());

        long insert = db.insert(Variables.TABLE_NAME, null, data);

        if(insert == -1)
        {
            return false;
        }else
        {
            return true;
        }
    }

    public boolean deleteOne(CustomerModel customerModel)
    {
        String query = "DELETE FROM " + Variables.TABLE_NAME + " WHERE " + Variables.CUSTOMER_ID + " = " + customerModel.getId();

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        if(cursor.moveToFirst())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public List<CustomerModel> fetchAllData()
    {
        List<CustomerModel> returnList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + Variables.TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst())
        {
            do
            {
                CustomerModel customerModel = new CustomerModel();
                customerModel.setId(cursor.getInt(0));
                customerModel.setName(cursor.getString(1));
                customerModel.setAge(cursor.getInt(2));
                customerModel.setActive(cursor.getInt(3) == 1 ? true : false);
                returnList.add(customerModel);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }
}
