package ca.uwaterloo.syde.shipmate;

import java.util.List;

import ca.uwaterloo.syde.shipmate.entity.LogDatabase;
import ca.uwaterloo.syde.shipmate.entity.LogEntry;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class LogViewActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_layout);
         
        LogDatabase db = new LogDatabase(this);
        
        // Reading all contacts
        List<LogEntry> entries = db.getAllEntries();       
        TableLayout table = (TableLayout)LogViewActivity.this.findViewById(R.id.attrib_table);
        
        for (LogEntry en: entries) {
            // Inflate your row "template" and fill out the fields.
            TableRow row = (TableRow)LayoutInflater.from(LogViewActivity.this).inflate(R.id.attrib_row, null);
            ((TextView)row.findViewById(R.id.attrib_to)).setText(en.getOrigin());
            ((TextView)row.findViewById(R.id.attrib_from)).setText(en.getDestination());
            ((TextView)row.findViewById(R.id.attrib_delivery_standard)).setText(en.getDeliveryStandard());
            ((TextView)row.findViewById(R.id.attrib_type)).setText(en.getShippingType());
            ((TextView)row.findViewById(R.id.attrib_date)).setText(en.getDate());
            table.addView(row);
        }
    }
}
