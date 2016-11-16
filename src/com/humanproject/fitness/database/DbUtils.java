package com.humanproject.fitness.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DbUtils {
    private static final String DB_PATH = "/data/data/com.humanproject.fitness/databases/";
    private static final String DB_NAME = "fitness.db";
    private static SQLiteDatabase db;
	private static final String TAG = DbUtils.class.getName();

    public static void createDatabaseIfNotExists(Context context) throws IOException {
        boolean createDb = false;

        File dbDir = new File(DB_PATH);
        if (!dbDir.exists()) {
            dbDir.mkdirs();
            createDb = true;
        }
        
        File dbFile = new File(DB_PATH + DB_NAME);

        if (!dbFile.exists()) {
            createDb = true;
        }
        else {
        	Log.i(TAG, dbFile.getAbsolutePath()+" Exists");

            db = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READONLY);

            if (db.getVersion() != FitDatabaseHelper.DATABASE_VERSION) {
                dbFile.delete();
                createDb = true;
            }
        }

        if (createDb) {
            InputStream myInput = context.getAssets().open(DB_NAME);
            
            OutputStream myOutput = new FileOutputStream(dbFile);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

            // Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
            SQLiteDatabase dbwrite = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);
            dbwrite.setVersion(FitDatabaseHelper.DATABASE_VERSION);
            dbwrite.close();
            if (db != null)
                db = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READONLY);

        }
    }

    public static SQLiteDatabase getStaticDb() {
    	if(db!=null)
    		return db;
        return SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READONLY);
    }
}
