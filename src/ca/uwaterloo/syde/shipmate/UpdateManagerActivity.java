package ca.uwaterloo.syde.shipmate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
	    dataUpdaterService.updateNodeSources();
	    Intent intent = new Intent(this, HomeActivity.class);
	    this.startActivity(intent);
	}
	
	private void displayMessage(String message) {
		TextView updateStatusText = (TextView) findViewById(R.id.updateStatusText);
		updateStatusText.setText(message);
	}
}
