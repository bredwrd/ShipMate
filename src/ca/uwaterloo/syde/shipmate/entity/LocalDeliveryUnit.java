package ca.uwaterloo.syde.shipmate.entity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.NodeList;

/**
 * LocalDeliveryUnit stores and determines information related to Local Delivery Units
 * @author Brian Stock
 *
 */
public class LocalDeliveryUnit {
	private String key;
	
	public String getKey() {
		return key;
	}
	
	public boolean getRemote(){
		return true;
	}
	
	public LocalDeliveryUnit(String key){
		this.key = key;
	}
	
	/* checks a list containing a mix of individual and ranged LDUs
	 * and determines if the fsaName is contained within that list
	 */
	public boolean determineIfRangedListContainsKey(String listToCheck)
	{
		boolean rtnBoolean = false;
		
		// pattern matching any ranged LDU list (e.g. A1A-2B2)
		Pattern fsaRangePattern = Pattern.compile("\\d[A-Z]\\d-\\d[A-Z]\\d");
		Matcher fsaRangeMatcher = fsaRangePattern.matcher(listToCheck);
		while (fsaRangeMatcher.find() && !rtnBoolean)
		{
			// ranged LDU list found and match is not found
			
			String tmpFsaRange = fsaRangeMatcher.group();
			
			// initialize pattern and variable for start of range (e.g. A1A)
			Pattern rangeStartPattern = Pattern.compile("^\\d[A-Z]\\d");
			Matcher rangeStartMatcher = rangeStartPattern.matcher(tmpFsaRange);
			rangeStartMatcher.find();
			String tmpRangeStart = rangeStartMatcher.group();
			
			// initialize pattern and variable for end of range (e.g. B2B)
			Pattern rangeEndPattern = Pattern.compile("\\d[A-Z]\\d$");
			Matcher rangeEndMatcher = rangeEndPattern.matcher(tmpFsaRange);
			rangeEndMatcher.find();
			String tmpRangeEnd = rangeEndMatcher.group();
			
			// check if the LDU in question is within the start - end range
			if (key.compareTo(tmpRangeStart) > 0 && key.compareTo(tmpRangeEnd) < 0)
			{
				// match found
				rtnBoolean = true;
			}
		}
		
		return rtnBoolean;
	}
}
