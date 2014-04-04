package ca.uwaterloo.syde.shipmate;

import ca.uwaterloo.syde.shipmate.control.DeliveryStandardCalculator;
import ca.uwaterloo.syde.shipmate.control.DomesticLettermailDeliveryStandardCalculator;
import ca.uwaterloo.syde.shipmate.control.GPSTracker;
import ca.uwaterloo.syde.shipmate.entity.PostalCode;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * HomeActivity
 * 
 *
 */
public class HomeActivity extends Activity {
	private DeliveryStandardCalculator deliveryStandardCalculator;
	private GPSTracker gpsTracker;
	private ProgressBar progress;
	private EditText editText;
	private Context context;
	
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.home_layout);
		context = this;
		
		// Initiate location service.
		gpsTracker = new GPSTracker(this);
		
		// On run_button click, submit origin and destination for processing and display result.
		Button runButton = (Button) findViewById(R.id.run_button);
		runButton.setOnClickListener(new Button.OnClickListener() {  
	        public void onClick(View v)
            {
	        	// Fetch postal code keys from user input.
	        	EditText toPostalCodeField = (EditText) findViewById(R.id.to_postal_code);
	        	EditText fromPostalCodeField = (EditText) findViewById(R.id.from_postal_code);
	        	
	        	// Submit origin and destination for processing.
	        	PostalCode toPostalCode = new PostalCode(toPostalCodeField.getText().toString());
	        	PostalCode fromPostalCode = new PostalCode(fromPostalCodeField.getText().toString());
	        	deliveryStandardCalculator = new DomesticLettermailDeliveryStandardCalculator(toPostalCode, fromPostalCode, context);
	        	TextView resultTextView = (TextView)findViewById(R.id.runResults);
	        	
	        	// Get and display result.
	        	resultTextView.setText("Delivery Standard: " + deliveryStandardCalculator.getDeliveryStandard());
            }
         });
		
		// On view_history_button click, displays history of past run events.
		Button historyButton = (Button) findViewById(R.id.view_history_button);
		historyButton.setOnClickListener(new Button.OnClickListener() {  
	        public void onClick(View v)
            {
	        	Intent intent = new Intent(context, LogViewActivity.class);
			    startActivity(intent);
            }
         });
	}
	
	/**
	 * AddressButton_OnClick listens on a button click 
	 * and starts a thread for the long process of 
	 * returning the current location's Postal Code
	 * @param v - the button that gets clicked
	 * @author Frank Alfieri
	 */
	public void AddressButton_OnClick(final View v) {
		final Context context = this;
		Thread thread = new Thread(){
			String address_text;
	        public void run() {
	        	//Run required location service methods to get the location's Postal Code
				gpsTracker.getLocation();
				gpsTracker.getLongitude();
				gpsTracker.getLatitude();
				//Use collected data and get the Postal code from Geocoder services.
				//Returns the Postal Code
			    address_text = gpsTracker.getGeocoderAddress(context).get(0).getPostalCode();
			    /* Passes the necessary code to the application with
		         * runOnUiThread(). The parameter is an anonymous Runnable object that
		         * contains the Java statements put in it by its run() method.
		        */
			    runOnUiThread(new Runnable() {
					public void run() {
						//Add the Postal Code text to the field on the UI
						editText.setText(address_text);
						//Stop spinning wheel (Task is done)
				        progress.setVisibility(View.INVISIBLE);
					}
	            });
	        }
        };
        //Check which button is being pressed
        if (v.getId() == R.id.to_button){
			editText = (EditText)findViewById(R.id.to_postal_code);
        	progress = (ProgressBar) findViewById(R.id.to_gpsStatusProgress);
        	
        } else if (v.getId() == R.id.from_button){
			editText = (EditText)findViewById(R.id.from_postal_code);
        	progress = (ProgressBar) findViewById(R.id.from_gpsStatusProgress);
        }
		// Start spinning wheel (Long task begins)
        progress.setVisibility(View.VISIBLE);
        thread.start();
	}
}
