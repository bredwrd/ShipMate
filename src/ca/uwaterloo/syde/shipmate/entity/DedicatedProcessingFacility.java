package ca.uwaterloo.syde.shipmate.entity;

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

	public int getBaseTime(String key){
		int index = NodeDatabase.getIndexOf(key);
		return dpfBaseDistanceList[index];
	}	
	
}
