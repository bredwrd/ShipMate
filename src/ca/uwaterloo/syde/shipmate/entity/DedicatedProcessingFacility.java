package ca.uwaterloo.syde.shipmate.entity;

public class DedicatedProcessingFacility {
	private static int numOfDpfs;
	private int[] dpfBaseTimeLookup;
	private static int currentDpfNum;
	public static String[] dpfKeysList;
	private static int currentDpfIndex;
	private static int indexOfKeysList;
	private String key;
	
	public DedicatedProcessingFacility(String key, int dpfBaseTimeList) {
		
	}
	
	public static int getNumofDpf() {
		return 0;
	}
	
	public String getKey() {
		return "";
	}
	
	public void setKey(String key) {
		
	}
	
	public static String[] getDpfNameList() {
		return new String[0];
	}
	
	public static void setDpfNameList(String[] nameList) {
		
	}
	
	public int getBaseTime(String key){
		
		return 0;
	}
	
	public int getIndexOf(String key){
		
		return 0;
	}
	
	public int getNextIndex(){
		
		return 0;
	}
	
}
