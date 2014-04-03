package ca.uwaterloo.syde.shipmate.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DedicatedProcessingFacility {
	private static Map dpfKeyIndexMap = new HashMap<String, Integer>();
	private Map dpfBaseTimeLookup = new HashMap<Integer, Integer>();
	private String key;
	
	public DedicatedProcessingFacility(String key, int... dpfBaseDistanceList) {
		this.key = key;
		this.dpfBaseTimeLookup = dpfBaseTimeLookup;
	}
	
	public String getKey() {
		return key;
	}

	public int getBaseTime(String key){
		return (Integer) dpfBaseTimeLookup.get(key);
	}
	
	private static void setupNameIndexMap() {
		dpfKeyIndexMap.put("CalgaryAB", 0);
		dpfKeyIndexMap.put("CharlottetownPE", 1);
		dpfKeyIndexMap.put("EdmontonAB", 2);
		dpfKeyIndexMap.put("FrederictonAB", 3);
		dpfKeyIndexMap.put("CalgaryAB", 4);
		dpfKeyIndexMap.put("CalgaryAB", 0);
		dpfKeyIndexMap.put("CalgaryAB", 0);
		dpfKeyIndexMap.put("CalgaryAB", 0);
		dpfKeyIndexMap.put("CalgaryAB", 0);
		dpfKeyIndexMap.put("CalgaryAB", 0);
		dpfKeyIndexMap.put("CalgaryAB", 0);
		dpfKeyIndexMap.put("CalgaryAB", 0);
		dpfKeyIndexMap.put("CalgaryAB", 0);
		dpfKeyIndexMap.put("CalgaryAB", 0);
		dpfKeyIndexMap.put("CalgaryAB", 0);
	}
}
