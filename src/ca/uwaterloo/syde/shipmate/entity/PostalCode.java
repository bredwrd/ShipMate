package ca.uwaterloo.syde.shipmate.entity;

public class PostalCode {
	private String key;
	private String fsa;
	private LocalDeliveryUnit ldu;
	private DedicatedProcessingFacility dpf;
	private boolean remote;
	private boolean major;
	
	public PostalCode(String keyCandidate){
		
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
	
	private String sanitizeKey(){
		
		return "";
		
	}
}
