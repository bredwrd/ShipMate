package ca.uwaterloo.syde.shipmate;

import java.util.List;

import ca.uwaterloo.syde.shipmate.entity.LogDatabase;
import ca.uwaterloo.syde.shipmate.entity.LogEntry;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class LogViewActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_layout);
         
        // Read database.
        LogDatabase dbh = new LogDatabase(this);
        SQLiteDatabase db = null;
        try {
        	db = dbh.getReadableDatabase();
	        List<LogEntry> entries = dbh.getAllEntries();       
	        TableLayout table = (TableLayout)LogViewActivity.this.findViewById(R.id.attrib_table);
	        
	        for (LogEntry en: entries) {
	            //Fill out the fields.
	        	String tmpString = en.getOrigin();
	        	TableRow row= new TableRow(this);
	        	TextView tv = (TextView)findViewById(R.id.attrib_to);
	            tv.setText(tmpString);
	            ((TextView)findViewById(R.id.attrib_from)).setText(en.getDestination());
	            ((TextView)findViewById(R.id.attrib_delivery_standard)).setText(en.getDeliveryStandard());
	            ((TextView)findViewById(R.id.attrib_type)).setText(en.getShippingType());
	            ((TextView)findViewById(R.id.attrib_date)).setText(en.getDate());
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
