package ca.uwaterloo.syde.shipmate.entity;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * LogDatabase manages historic log data.
 * @author Brian
 *
 */
public class LogDatabase extends SQLiteOpenHelper {
	
	private static final String TABLE_LOG =  "Log";
	private static final String DATABASE_NAME = "LogDatabase";
	private static final String KEY_ID = "Key";
	private static final String KEY_ORIGIN = "Origin";
	private static final String KEY_DESTINATION = "Destination";
	private static final String KEY_SHIPPING_TYPE="ShippingType";
	private static final String KEY_DATE="Date";
	private static final String KEY_DELIVERY_STANDARD = "DeliveryStandard";
	private static final int DATABASE_VERSION = 7;

    public LogDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_LOG + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_ORIGIN + " TEXT,"
                + KEY_DESTINATION + " TEXT," + KEY_SHIPPING_TYPE + " TEXT," + KEY_DELIVERY_STANDARD + " TEXT,"+ KEY_DATE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOG);
 
        // Create tables again
        onCreate(db);
	}
	
    // Adding new contact
    public void addEntry(LogEntry logEntry) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        // Define stored values
        ContentValues values = new ContentValues();
        values.put(KEY_ORIGIN, logEntry.getOrigin());
        values.put(KEY_DESTINATION, logEntry.getDestination());
        values.put(KEY_SHIPPING_TYPE, logEntry.getShippingType());
        values.put(KEY_DELIVERY_STANDARD, logEntry.getDeliveryStandard());
        values.put(KEY_DATE, logEntry.getDate());
 
        // Inserting Row
        db.insert(TABLE_LOG, null, values);
        db.close(); // Closing database connection
    }
     
    // Getting All Contacts
    public List<LogEntry> getAllEntries() {
        List<LogEntry> entryList = new ArrayList<LogEntry>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_LOG;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	System.out.println(cursor.getString(0));
            	System.out.println(cursor.getString(1));
            	System.out.println(cursor.getString(2));
            	System.out.println(cursor.getString(3));
            	System.out.println(cursor.getString(4));
                LogEntry logEntry = new LogEntry(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(5), cursor.getString(4));
                // Adding entry to list
                entryList.add(logEntry);
            } while (cursor.moveToNext());
        }
        // return contact list
        return entryList;
    } 
 
    // Getting contacts Count
    public int getEntriesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_LOG;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }

}
