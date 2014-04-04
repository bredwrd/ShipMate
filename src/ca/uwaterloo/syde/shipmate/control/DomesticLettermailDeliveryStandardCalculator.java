package ca.uwaterloo.syde.shipmate.control;

import java.util.Date;

import ca.uwaterloo.syde.shipmate.entity.DedicatedProcessingFacility;
import ca.uwaterloo.syde.shipmate.entity.NodeDatabase;
import ca.uwaterloo.syde.shipmate.entity.PostalCode;

public class DomesticLettermailDeliveryStandardCalculator extends DeliveryStandardCalculator {
	private PostalCode origin;
	private PostalCode destination;
	private Date selectedInputDate = new Date(System.currentTimeMillis());
	
	private final int additionalRemoteTime = 4;
	
	public DomesticLettermailDeliveryStandardCalculator(PostalCode origin, PostalCode destination) {
		this.origin = origin;
		this.destination = destination;
		deliveryStandard = determineDeliveryStandard(origin, destination);
	}
	
	private String determineDeliveryStandard(PostalCode origin, PostalCode destination) {
		String result = "";
		int totalTime = 0;
		boolean rangedArrivalDate = false;
		
		// Origin input; common for all destinations and shipping type
		Date sentDate = selectedInputDate;
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
		//Date calculatedArrivalBeginDate = addBusinessDays(baseTime);
		//Date calculatedArrivalEndDate;
		if (rangedArrivalDate)
		{
			result += " to " + Integer.toString(baseTime + additionalRemoteTime);
			//calculatedArrivalEndDate = addBusinessDays(totalTime);
		} else
		{
			//calculatedArrivalEndDate = calculatedArrivalBeginDate;
		}

		// display resulting output
		result += " business days";
		
		return result;
	}
	
}
