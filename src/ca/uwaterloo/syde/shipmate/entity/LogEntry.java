package ca.uwaterloo.syde.shipmate.entity;

/**
 * LogEntry represents a recorded delivery standard log entry
 * @author Brian Stock
 *
 */
public class LogEntry {
	private String origin;
	private String destination;
	private String shippingType;
	private String date;
	private String deliveryStandard;
	
	public LogEntry(String origin, String destination, String shippingType, String date, String deliveryStandard) {
		this.origin = origin;
		this.destination = destination;
		this.shippingType = shippingType;
		this.date = date;
		this.deliveryStandard = deliveryStandard;
	}
	
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getShippingType() {
		return shippingType;
	}
	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDeliveryStandard() {
		return deliveryStandard;
	}
	public void setDeliveryStandard(String deliveryStandard) {
		this.deliveryStandard = deliveryStandard;
	}

	
	
}
