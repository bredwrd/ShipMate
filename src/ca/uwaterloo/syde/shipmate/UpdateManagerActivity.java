package ca.uwaterloo.syde.shipmate;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import ca.uwaterloo.syde.shipmate.R;
import ca.uwaterloo.syde.shipmate.control.DataUpdaterService;


public class UpdateManagerActivity extends Activity {

	DataUpdaterService dataUpdaterService = new DataUpdaterService();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.update_manager_layout);
	    displayMessage("Loading...");
	    final Context context = this;
		Thread thread = new Thread(){
	        public void run() {
	        	dataUpdaterService.updateNodeSources();
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
	
	
	private void displayMessage(String message) {
		TextView updateStatusText = (TextView) findViewById(R.id.updateStatusText);
		updateStatusText.setText(message);
	}
}
