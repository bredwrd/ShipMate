package ca.uwaterloo.syde.shipmate;

import java.util.List;

import ca.uwaterloo.syde.shipmate.entity.LogDatabase;
import ca.uwaterloo.syde.shipmate.entity.LogEntry;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
/**
 * LogViewActivity prints out the search history in a table view
 * @author Frank
 *
 */
public class LogViewActivity extends Activity {
	
	LogDatabase dbh;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_layout);
         
        // Read database.
        dbh = new LogDatabase(this);
        SQLiteDatabase db = null;
        try {
        	db = dbh.getReadableDatabase();
	        List<LogEntry> entries = dbh.getAllEntries();       
	        TableLayout table = (TableLayout)LogViewActivity.this.findViewById(R.id.attrib_table);
	        
	        for (LogEntry en: entries) {
	            //Fill out the fields based on bd results
	        	String to = en.getOrigin();
	        	String from = en.getDestination();
	        	String delivery_standard = en.getDeliveryStandard();
	        	String type = en.getShippingType();
	        	String date = en.getDate();
	        	
	        	//create new row
	        	TableRow row= new TableRow(this);
	        	//add layout to row
	        	row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
	        	
	        	//create new text fields
	        	TextView to_view = new TextView(this);
	        	TextView from_view = new TextView(this);
	        	TextView delivery_standard_view = new TextView(this);
	        	TextView type_view = new TextView(this);
	        	TextView date_view = new TextView(this);
	        	
	        	//add text to the fields
	        	to_view.setText(to);
	        	from_view.setText(from);
	        	delivery_standard_view.setText(delivery_standard);
	        	type_view.setText(type);
	        	date_view.setText(date);
	        	//add size to text	        	
	        	to_view.setTextSize(10);
	        	from_view.setTextSize(10);
	        	delivery_standard_view.setTextSize(10);
	        	type_view.setTextSize(10);
	        	date_view.setTextSize(10);
	        	
	        	//add the row to view
	        	row.addView(to_view);
	        	row.addView(from_view);
	        	row.addView(delivery_standard_view);
	        	row.addView(type_view);
	        	row.addView(date_view);
	        	//add the table to view
	            table.addView(row);
	        }
        } catch(SQLiteException e) {
        	e.printStackTrace();
        } finally {
            if(dbh != null)
                dbh.close();
        }
    }
}
