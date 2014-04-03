package ca.uwaterloo.syde.shipmate.entity;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.NodeList;

public class PostalCode {
	private String key;
	private ForwardStationArea fsa;
	private LocalDeliveryUnit ldu;
	private DedicatedProcessingFacility dpf;
	private boolean valid;
	private boolean remote;
	private boolean major;
	
	public PostalCode(String keyCandidate){
		key = sanitizeKey(keyCandidate);
		valid = determineIfValid(key);
		
		String fsaKey = determineFsaKey(key);
		fsa = new ForwardStationArea(fsaKey);
		String lduKey = determineLdu(key);
		ldu = new LocalDeliveryUnit(lduKey);
		
		// set dpf name and if major or non-major
		major = fsa.getMajor();
		String dpfKey = fsa.getDpfKey();
		dpf = new DedicatedProcessingFacility(dpfKey);
		remote = determineIfRemote(fsa, ldu);
	}
	
	/*
	 *  sets remote by using LDU and DPF
	 */
	private boolean determineIfRemote(ForwardStationArea fsa, LocalDeliveryUnit ldu)
	{
		boolean rtnBoolean = false;
		
		// for now, all remote codes are of format [A-Z]0[A-Z] [0-9][A-Z][O-9]
		// (zero is second char) so only check under that condition
		char secondChar = fsa.getKey().charAt(1);
		if (secondChar == '0')
		{
			NodeList remoteLookupNodes = NodeDatabase.getRemoteLookup();
		    for (int idx = 0; idx < remoteLookupNodes.getLength(); idx += 2) {
		    	String tmpFsaData = remoteLookupNodes.item(idx).getTextContent();
		    	// First, match FSA, and if it matches, then try to match LDU
		    	if (tmpFsaData.contains(fsa.getKey()))
		    	{
		    		String tmpLduData = remoteLookupNodes.item(idx + 1).getTextContent();
		    		if (tmpLduData.contains(ldu.getKey()) || (tmpLduData.contains("-") && ldu.determineIfRangedListContainsKey(tmpLduData)))
    				{
		    			// is remote
		    			rtnBoolean = true;
		    			break;
    				}
		    	}
		    }
		}
	    
		return rtnBoolean;
	}
	
	/*
	 *  determines if postal code is valid and exists
	 */
	private boolean determineIfValid(String key)
	{
		boolean rtnBoolean = false;
		
		Pattern tmpPattern = Pattern.compile("[ABCEGHJKLMNPRSTVXY]\\d[A-Z]\\d[A-Z]\\d");
		Matcher tmpMatcher = tmpPattern.matcher(key);
		if (tmpMatcher.find())
		{
			rtnBoolean = true;
			
		}
		return rtnBoolean;
	}
	
	/*
	 * selects and returns first 3 alphanumeric chars (AKA FSA)
	 *  returns "" if a valid FSA isn't found
	 */
	private String determineFsaKey(String postalCodeName)
	{
		String tmpFsaKey = "";
		// match the first three alphanumeric chars
		Pattern tmpPattern = Pattern.compile("[ABCEGHJKLMNPRSTVXY]\\d[A-Z]");
		Matcher tmpMatcher = tmpPattern.matcher(postalCodeName);
		if (tmpMatcher.find())
		{
			tmpFsaKey = tmpMatcher.group();
		}
		return tmpFsaKey;
	}
	
	/*
	 *  selects and returns last 3 alphanumeric chars (AKA LDU)
	 *  returns "" if a valid LDU isn't found
	 */
	private String determineLdu(String key)
	{
		String rtnString = "";
		// match the first three alphanumeric chars
		Pattern tmpPattern = Pattern.compile("[0-9][A-Z][0-9]$");
		Matcher tmpMatcher = tmpPattern.matcher(key);
		if (tmpMatcher.find())
		{
			rtnString = tmpMatcher.group();
		}
		return rtnString;
	}
	
	public boolean isValid(){
		return false;
	}
	
	public boolean isMajor(){
		return major;
	}
	
	public boolean isRemote(){
	
		return remote;
	}
	
	public DedicatedProcessingFacility getDpf(){
		
		return dpf;
	}
	
	private String sanitizeKey(String keyCandidate){
		String sanitizedKey = "";
		// strip non-alphanumeric chars
		keyCandidate = keyCandidate.replaceAll("[^\\p{L}\\p{N}]", "");
		// match six alphanumeric chars
		Pattern tmpPattern = Pattern.compile("^[\\w][\\w][\\w][\\w][\\w][\\w]$");
		Matcher tmpMatcher = tmpPattern.matcher(keyCandidate);
		if (tmpMatcher.find())
		{
			sanitizedKey = tmpMatcher.group().toUpperCase(Locale.ENGLISH);
		}
		return sanitizedKey;
		
	}
}
