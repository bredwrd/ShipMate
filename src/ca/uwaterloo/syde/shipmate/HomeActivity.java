package ca.uwaterloo.syde.shipmate;

import ca.uwaterloo.syde.shipmate.control.DataUpdaterService;
import ca.uwaterloo.syde.shipmate.control.DeliveryStandardCalculator;
import ca.uwaterloo.syde.shipmate.control.DomesticLettermailDeliveryStandardCalculator;
import ca.uwaterloo.syde.shipmate.control.GeocoderController;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends Activity {
	//TODO TextView updateStatusText;
	DataUpdaterService dataUpdaterService = new DataUpdaterService();
	DeliveryStandardCalculator deliveryStandardCalculator = new DomesticLettermailDeliveryStandardCalculator();
	//TODO new....
	GeocoderController geocodercontroller;
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.home_layout);
	}
}
