package ca.uwaterloo.syde.shipmate.control;

import ca.uwaterloo.syde.shipmate.entity.PostalCode;

public class DomesticLettermailDeliveryStandardCalculator implements DeliveryStandardCalculator {
	private PostalCode origin;
	private PostalCode destination;
	private int deliveryStandard;
	
	public DomesticLettermailDeliveryStandardCalculator(PostalCode origin, PostalCode destination) {
		this.origin = origin;
		this.destination = destination;
	}
	
	public int getDeliveryStandard() {
		// TODO Auto-generated method stub
		// Do stuff with origin, destination PostalCodes and return delivery standard, in business days
		return 0;
	}
	
}
