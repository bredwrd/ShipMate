package ca.uwaterloo.syde.shipmate.control;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import ca.uwaterloo.syde.shipmate.entity.LogDatabase;
import ca.uwaterloo.syde.shipmate.entity.LogEntry;
import ca.uwaterloo.syde.shipmate.entity.PostalCode;

/**
 * DomesticLettermailDeliveryStandardCalculator implements delivery standard calculation for domestic lettermail.
 * @author Brian Stock
 *
 */
public class DomesticLettermailDeliveryStandardCalculator extends DeliveryStandardCalculator {
	private PostalCode origin;
	private PostalCode destination;
	private static final String SHIPPING_TYPE_KEY = "Domestic Lettermail";
	private Date date = new Date(System.currentTimeMillis());
	LogDatabase logDb;

	private final int additionalRemoteTime = 4;
	
	public DomesticLettermailDeliveryStandardCalculator(PostalCode origin, PostalCode destination, Context context) {
		this.origin = origin;
		this.destination = destination;
		logDb = new LogDatabase(context);
		deliveryStandard = determineDeliveryStandard(origin, destination);
	}
	
	/**
	 * determineDeliveryStandard determines delivery standard for an origin, destination pair.
	 * @param origin
	 * @param destination
	 * @return String delivery standard
	 */
	private String determineDeliveryStandard(PostalCode origin, PostalCode destination) {
		String result = "";
		int totalTime = 0;
		boolean rangedArrivalDate = false;
		
		// Origin input; common for all destinations and shipping type
		int baseTime = origin.getDpf().getBaseTime(destination.getDpf().getKey());
		totalTime += baseTime;
		result = Integer.toString(baseTime);
		// for now, do not differentiate between which PC (source or dest.)
		// is remote, but it would be possible.
		if (origin.isRemote() || destination.isRemote())
		{
			// is remote transaction
			totalTime += additionalRemoteTime;
			rangedArrivalDate = true;
		}
		
		// handle ranged arrival time due to remote PC
		if (rangedArrivalDate)
		{
			result += " to " + Integer.toString(baseTime + additionalRemoteTime);
		} else
		{
			
		}

		// display resulting output
		result += " business days";
		String date = new SimpleDateFormat("yyyy-MM-dd").format(this.date);
		LogEntry logEntry = new LogEntry(origin.getKey(), destination.getKey(), SHIPPING_TYPE_KEY, date, result);
		logDb.addEntry(logEntry);
		logDb.close();
		return result;
	}
	
}
