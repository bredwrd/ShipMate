package ca.uwaterloo.syde.shipmate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import ca.uwaterloo.syde.shipmate.R;
import ca.uwaterloo.syde.shipmate.control.DataUpdaterService;

/**
 * UpdateManagerActivity provides a user experience for polling and processing of data sources.
 * Threading goes in another activity.
 * @author Brian Stock
 *
 */
public class UpdateManagerActivity extends Activity {
	DataUpdaterService dataUpdaterService = new DataUpdaterService();

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    // Define layout.
	    setContentView(R.layout.update_manager_layout);
	    
	    displayMessage("Loading...");
	    
	    // Run updater service in background thread to keep UX responsive.
	    final Context context = this;
		Thread thread = new Thread(){
	        public void run() {
	        	dataUpdaterService.updateNodeSources();
	        	// Initiate HomeActivity.
	        	runOnUiThread(new Runnable() {
					public void run() {
					    Intent intent = new Intent(context, HomeActivity.class);
					    startActivity(intent);
					}
	            });
	        }
        };
        thread.start();
	}
	
	/**
	 * displayMessage sets a layout component to display a programmable message.
	 * @param message - text to display to the user.
	 */
	private void displayMessage(String message) {
		TextView updateStatusText = (TextView) findViewById(R.id.updateStatusText);
		updateStatusText.setText(message);
	}
}
