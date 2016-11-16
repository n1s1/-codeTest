package com.humanproject.fitness.database;

import java.io.IOException;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.humanproject.fitness.bean.User;

public class FitDatabaseHelper extends SQLiteOpenHelper {
	private static FitDatabaseHelper sInstance;

	private static final String DATABASE_NAME = "fitness.db";
	static final int DATABASE_VERSION = 1;
	private static final String TAG = FitDatabaseHelper.class.getName();

	public static FitDatabaseHelper getInstance(Context context) {
		if (sInstance == null) {
			try {
				DbUtils.createDatabaseIfNotExists(context);
			} catch (IOException e) {
				Log.e(TAG, e.toString());
			}
			sInstance = new FitDatabaseHelper(context.getApplicationContext());
		}
		return sInstance;
	}

	private FitDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		//mdb = getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//FitnessTable.onCreate(db);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//FitnessTable.onUpgrade(db, oldVersion, newVersion);
	}
	
	@Override
	public synchronized void close(){
//	    if(mdb != null){
//	    	mdb.close();
//	    }
//	    super.close();
	}
}
