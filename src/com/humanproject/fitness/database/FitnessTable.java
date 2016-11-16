package com.humanproject.fitness.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class FitnessTable {
	  // Database table
	  public static final String TABLE_FITNESS = "fitness";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_NAME = "name";
	  public static final String COLUMN_EMAIL = "email";
	  public static final String COLUMN_PASSWD = "passwd";
	  public static final String COLUMN_LOCATION = "location";	  
	  public static final String COLUMN_TOTALFEETS = "total feets";
	  public static final String COLUMN_DAILYFEETS = "daily feets";
	  public static final String COLUMN_LASTWALKEDAT = "last walked at";
	  public static final String COLUMN_MILESTONE = "milestone";
	  public static final String COLUMN_RANK = "rank";
	  
	  // Database creation SQL statement
	  private static final String TABLE_CREATE = "create table " 
	      + TABLE_FITNESS
	      + "(" 
	      + COLUMN_ID + " integer primary key autoincrement, " 
	      + COLUMN_NAME + " text not null, " 
	      + COLUMN_EMAIL + " text not null," 
	      + COLUMN_PASSWD + " text not null,"
	      + COLUMN_LOCATION + " text," 	      	      
	      + COLUMN_TOTALFEETS + " integer,"
	      + COLUMN_DAILYFEETS + " integer,"
	      + COLUMN_LASTWALKEDAT + " integer,"
	      + COLUMN_MILESTONE+ " integer,"
	      + COLUMN_RANK+ " integer"
	      + ");";

	  public static void onCreate(SQLiteDatabase database) {
	    database.execSQL(TABLE_CREATE);
	  }

	  public static void onUpgrade(SQLiteDatabase database, int oldVersion,
	      int newVersion) {
	    Log.w(FitnessTable.class.getName(), "Upgrading database from version "
	        + oldVersion + " to " + newVersion
	        + ", which will destroy all old data");
	    database.execSQL("DROP TABLE IF EXISTS " + TABLE_FITNESS);
	    onCreate(database);
	  }
}
