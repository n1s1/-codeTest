package com.humanproject.fitness.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.humanproject.fitness.bean.User;
import com.humanproject.fitness.contentprovider.FitnessContentProvider;
import com.humanproject.fitness.database.FitnessTable;

public class UserManager {
	private Context context;
	
	public UserManager(Context context) {
		this.context = context;
	}
	
    public Uri insertUser (String email, String name, String password){
    	Uri mNewUri;

    	ContentValues mNewValues = new ContentValues();

    	mNewValues.put(FitnessTable.COLUMN_EMAIL, email);
    	mNewValues.put(FitnessTable.COLUMN_NAME, name);
    	mNewValues.put(FitnessTable.COLUMN_PASSWD, password);

    	mNewUri = context.getContentResolver().insert(
    	    FitnessContentProvider.CONTENT_URI,
    	    mNewValues
    	    );

        return mNewUri;
    }

    public int updateUserPassword (String email, String password){
    	ContentValues mUpdateValues = new ContentValues();
    	String mSelectionClause = FitnessTable.COLUMN_EMAIL + " = ?";
    	String[] mSelectionArgs = {email};
    			
    	mUpdateValues.put(FitnessTable.COLUMN_PASSWD, password);
    	int mRowsUpdated = context.getContentResolver().update(
    			FitnessContentProvider.CONTENT_URI,
    		    mUpdateValues,                       
    		    mSelectionClause,                    
    		    mSelectionArgs                      
    		);
        return mRowsUpdated;
    }
    
    public int updateUserFitnessStats (User user){
    	ContentValues mUpdateValues = new ContentValues();
    	String mSelectionClause = FitnessTable.COLUMN_EMAIL + " = ?";
    	String[] mSelectionArgs = {user.getEmail()};
    	
    	long totalFeets = user.getTotalFeets();
    	mUpdateValues.put(FitnessTable.COLUMN_TOTALFEETS, totalFeets);
    	mUpdateValues.put(FitnessTable.COLUMN_DAILYFEETS, user.getDailyFeets());
    	mUpdateValues.put(FitnessTable.COLUMN_MILESTONE, totalFeets/1000);

    	int mRowsUpdated = context.getContentResolver().update(
    			FitnessContentProvider.CONTENT_URI,
    		    mUpdateValues,
    		    mSelectionClause,
    		    mSelectionArgs
    		);
        return mRowsUpdated;
    }

    public User getUserDetail (String username){
        Uri uri = FitnessContentProvider.CONTENT_URI;
        String[] projection = {FitnessTable.COLUMN_ID, FitnessTable.COLUMN_PASSWD,
        		FitnessTable.COLUMN_NAME, FitnessTable.COLUMN_LOCATION, FitnessTable.COLUMN_TOTALFEETS, 
        		FitnessTable.COLUMN_DAILYFEETS, FitnessTable.COLUMN_LASTWALKEDAT, FitnessTable.COLUMN_MILESTONE, 
        		FitnessTable.COLUMN_RANK};
    	String[] selectionArgs = {username};

        User myUser = new User(username);
    	Cursor cursor = context.getContentResolver().query(uri, projection, FitnessTable.COLUMN_EMAIL + " = ?", selectionArgs, null);
        if (cursor.moveToFirst()){
            do {
                myUser.setUserId(cursor.getLong(0));
                myUser.setPassword(cursor.getString(1));
                myUser.setName(cursor.getString(2));
                myUser.setLocation(cursor.getString(3));                
                myUser.setTotalFeets(cursor.getLong(4));
                myUser.setDailyFeets(cursor.getInt(5));
                myUser.setLastWalkedAt(cursor.getLong(6));
                myUser.setMilestone(cursor.getInt(7));
                myUser.setRank(cursor.getInt(8));
                
            } while (cursor.moveToNext());
        }
        cursor.close();
        return myUser;
    }
}
