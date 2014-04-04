package ca.uwaterloo.syde.shipmate.entity;

/**
 * DedicatedProcessFacility stores and retrieves data associated with a Dedicated Processing Facility
 * @author Brian Stock
 *
 */
public class DedicatedProcessingFacility {
	private String key;
	private int[] dpfBaseDistanceList;
	
	public DedicatedProcessingFacility(String key, int... dpfBaseDistanceList) {
		this.key = key;
		this.dpfBaseDistanceList = dpfBaseDistanceList;
	}
	
	public String getKey() {
		return key;
	}

	/**
	 * getBaseTime retrieves the base time between this DPF and another.
	 * @param key - unique identifier of (destination) DPF to determine dpf to
	 * @return base delivery standard in business days
	 */
	public int getBaseTime(String key){
		int index = NodeDatabase.getIndexOf(key);
		return dpfBaseDistanceList[index];
	}	
	
}
