package com.humanproject.fitness.contentprovider;

import java.util.Arrays;
import java.util.HashSet;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.humanproject.fitness.database.FitDatabaseHelper;
import com.humanproject.fitness.database.FitnessTable;

public class FitnessContentProvider extends ContentProvider {
	private FitDatabaseHelper database;
	// used for the UriMacher
	private static final int FITNESS = 10;
	private static final int ID = 20;

	private static final String AUTHORITY = "com.humanproject.fitness.fitnessprovider";

	private static final String BASE_PATH = "fitness";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
			+ "/" + BASE_PATH);

	public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
			+ "/fitness";
	public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
			+ "/id";

	private static final UriMatcher sURIMatcher = new UriMatcher(
			UriMatcher.NO_MATCH);
	private static final String TAG = FitnessContentProvider.class.getName();

	static {
		sURIMatcher.addURI(AUTHORITY, BASE_PATH, FITNESS);
		sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", ID);
	}

	@Override
	public boolean onCreate() {
		database = FitDatabaseHelper.getInstance(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

		// check if the caller has requested a column which does not exists
		checkColumnsFields(projection);

		// Set the table
		queryBuilder.setTables(FitnessTable.TABLE_FITNESS);

		int uriType = sURIMatcher.match(uri);
		switch (uriType) {
		case FITNESS:
			break;
		case ID:
			// adding the ID to the original query
			queryBuilder.appendWhere(FitnessTable.COLUMN_ID + "="
					+ uri.getLastPathSegment());
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		SQLiteDatabase db = database.getReadableDatabase();
		Cursor cursor = queryBuilder.query(db, projection, selection,
				selectionArgs, null, null, sortOrder);

		// make sure that potential listeners are getting notified
		cursor.setNotificationUri(getContext().getContentResolver(), uri);

		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int uriType = sURIMatcher.match(uri);
		SQLiteDatabase sqlDB = database.getWritableDatabase();
		long id = 0;
		switch (uriType) {
		case FITNESS:
			id = sqlDB.insert(FitnessTable.TABLE_FITNESS, null,
					values);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return Uri.parse(BASE_PATH + "/" + id);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int uriType = sURIMatcher.match(uri);
		SQLiteDatabase sqlDB = database.getWritableDatabase();
		int rowsDeleted = 0;
		switch (uriType) {
		case FITNESS:
			rowsDeleted = sqlDB.delete(FitnessTable.TABLE_FITNESS,
					selection, selectionArgs);
			break;
		case ID:
			String id = uri.getLastPathSegment();
			if (TextUtils.isEmpty(selection)) {
				rowsDeleted = sqlDB.delete(
						FitnessTable.TABLE_FITNESS,
						FitnessTable.COLUMN_ID + "=" + id, null);
			} else {
				rowsDeleted = sqlDB.delete(
						FitnessTable.TABLE_FITNESS,
						FitnessTable.COLUMN_ID + "=" + id + " and "
								+ selection, selectionArgs);
			}
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return rowsDeleted;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int uriType = sURIMatcher.match(uri);
		SQLiteDatabase sqlDB = database.getWritableDatabase();
		int rowsUpdated = 0;
		switch (uriType) {
		case FITNESS:
			rowsUpdated = sqlDB.update(FitnessTable.TABLE_FITNESS,
					values, selection, selectionArgs);
			break;
		case ID:
			String id = uri.getLastPathSegment();
			if (TextUtils.isEmpty(selection)) {
				rowsUpdated = sqlDB.update(
						FitnessTable.TABLE_FITNESS, values,
						FitnessTable.COLUMN_ID + "=" + id, null);
			} else {
				rowsUpdated = sqlDB.update(
						FitnessTable.TABLE_FITNESS, values,
						FitnessTable.COLUMN_ID + "=" + id + " and "
								+ selection, selectionArgs);
			}
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return rowsUpdated;
	}

	private void checkColumnsFields(String[] projection) {
		String[] available = {
				FitnessTable.COLUMN_NAME,
				FitnessTable.COLUMN_EMAIL,
				FitnessTable.COLUMN_PASSWD,				
				FitnessTable.COLUMN_TOTALFEETS, 
				FitnessTable.COLUMN_DAILYFEETS,
				FitnessTable.COLUMN_LASTWALKEDAT,				
				FitnessTable.COLUMN_MILESTONE, 
				FitnessTable.COLUMN_RANK, 
				FitnessTable.COLUMN_ID };
		if (projection != null) {
			HashSet<String> requestedColumns = new HashSet<String>(
					Arrays.asList(projection));
			Log.i(TAG, requestedColumns.toString());
			HashSet<String> availableColumns = new HashSet<String>(
					Arrays.asList(available));

			if (!availableColumns.containsAll(requestedColumns)) {
				throw new IllegalArgumentException(
						"Unknown columns in projection");
			}
		}
	}

}
