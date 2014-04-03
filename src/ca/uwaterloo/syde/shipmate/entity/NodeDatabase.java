package ca.uwaterloo.syde.shipmate.entity;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.NodeList;

public class NodeDatabase {
	private static NodeList fsaDpfLookup;
	private Map remoteNodes = new HashMap();
	private Map dpfNodes = new HashMap<String, DedicatedProcessingFacility>();

	public void save(HashMap remoteNodes,
			HashMap<String, DedicatedProcessingFacility> dpfNodes) {

	}

	public NodeDatabase() {

	}
	
	public static void setFsaDpfLookup(NodeList fsaDpfLookup) {
		NodeDatabase.fsaDpfLookup = fsaDpfLookup;
	}
	public static NodeList getFsaDpfLookup() {
		return fsaDpfLookup;
	}

	/*
	 * initializes dpfGrid with values from Delivery Standards site
	 */
	private void setupDpfGrid() {
		// HTML document only contains an image, no parsable text, so hardcoded
		// TODO- parse data from pdf available from Delivery Standards site
		DedicatedProcessingFacility dpfCalgary = new DedicatedProcessingFacility(
				"CalgaryAB", 2, 4, 3, 4, 4, 4, 5, 6, 4, 4, 4, 4, 4, 4, 4, 4, 4,
				4, 4, 4, 4, 4, 4, 4, 4);
		DedicatedProcessingFacility dpfCharlottetown = new DedicatedProcessingFacility(
				"CharlottetownPE", 4, 2, 4, 4, 3, 4, 4, 6, 4, 4, 3, 4, 4, 4, 4,
				3, 4, 3, 4, 4, 4, 4, 4, 4, 4);
		DedicatedProcessingFacility dpfEdmonton = new DedicatedProcessingFacility(
				"EdmontonAB", 3, 4, 2, 4, 4, 4, 5, 6, 4, 4, 4, 4, 4, 4, 4, 4,
				4, 4, 4, 4, 4, 4, 4, 4, 4);
		DedicatedProcessingFacility dpfFredericton = new DedicatedProcessingFacility(
				"FrederictonNB", 4, 4, 4, 2, 3, 4, 5, 6, 4, 4, 3, 4, 4, 4, 4,
				3, 4, 4, 4, 4, 4, 4, 4, 4, 4);
		DedicatedProcessingFacility dpfHalifax = new DedicatedProcessingFacility(
				"HalifaxNS", 4, 3, 4, 3, 2, 4, 4, 6, 4, 4, 3, 4, 4, 4, 4, 3, 4,
				3, 4, 4, 4, 4, 4, 4, 4);
		DedicatedProcessingFacility dpfHamilton = new DedicatedProcessingFacility(
				"HamiltonON", 4, 4, 4, 4, 4, 2, 5, 6, 3, 3, 4, 4, 3, 4, 4, 4,
				4, 4, 3, 4, 4, 4, 3, 4, 4);
		DedicatedProcessingFacility dpfHappyValley = new DedicatedProcessingFacility(
				"HappyValleyNL", 5, 4, 5, 5, 4, 5, 2, 6, 5, 5, 4, 5, 5, 5, 5,
				5, 5, 5, 5, 5, 5, 5, 5, 5, 5);
		DedicatedProcessingFacility dpfIqualuit = new DedicatedProcessingFacility(
				"IqaluitNU", 6, 6, 6, 6, 6, 6, 6, 2, 6, 6, 6, 4, 5, 5, 6, 6, 6,
				6, 5, 6, 6, 6, 6, 6, 6);
		DedicatedProcessingFacility dpfKitchener = new DedicatedProcessingFacility(
				"KitchenerON", 4, 4, 4, 4, 4, 3, 5, 6, 2, 3, 4, 4, 3, 4, 4, 4,
				4, 4, 3, 4, 4, 4, 3, 4, 4);
		DedicatedProcessingFacility dpfLondon = new DedicatedProcessingFacility(
				"LondonON", 4, 4, 4, 4, 4, 3, 5, 6, 3, 2, 4, 4, 3, 4, 4, 4, 4,
				4, 3, 4, 4, 4, 3, 4, 4);
		DedicatedProcessingFacility dpfMoncton = new DedicatedProcessingFacility(
				"MonctonNB", 4, 3, 4, 3, 3, 4, 4, 6, 4, 4, 2, 4, 4, 4, 4, 3, 4,
				3, 4, 4, 4, 4, 4, 4, 4);
		DedicatedProcessingFacility dpfMontreal = new DedicatedProcessingFacility(
				"MontralQC", 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 2, 3, 3, 4, 4, 4,
				4, 3, 4, 4, 4, 4, 4, 4);
		DedicatedProcessingFacility dpfOttawa = new DedicatedProcessingFacility(
				"OttawaONGatineauQC", 4, 4, 4, 4, 4, 3, 5, 5, 3, 3, 4, 3, 2, 3,
				4, 4, 4, 4, 3, 4, 4, 4, 3, 4, 4);
		DedicatedProcessingFacility dpfQuebec = new DedicatedProcessingFacility(
				"QubecQC", 4, 4, 4, 4, 4, 4, 5, 5, 4, 4, 4, 3, 3, 2, 4, 4, 4,
				4, 4, 4, 4, 4, 4, 4, 4);
		DedicatedProcessingFacility dpfRegina = new DedicatedProcessingFacility(
				"ReginaSK", 4, 4, 4, 4, 4, 4, 5, 6, 4, 4, 4, 4, 4, 4, 2, 4, 3,
				4, 4, 4, 4, 4, 4, 3, 4);
		DedicatedProcessingFacility dpfStJohn = new DedicatedProcessingFacility(
				"StJohnNB", 4, 3, 4, 3, 3, 4, 4, 6, 4, 4, 3, 4, 4, 4, 4, 2, 4,
				3, 4, 4, 4, 4, 4, 4, 4);
		DedicatedProcessingFacility dpfSaskatoon = new DedicatedProcessingFacility(
				"SaskatoonSK", 4, 4, 4, 4, 4, 4, 5, 6, 4, 4, 4, 4, 4, 4, 3, 4,
				2, 4, 4, 4, 4, 4, 4, 3, 4);
		DedicatedProcessingFacility dpfStJohns = new DedicatedProcessingFacility(
				"StJohnsNL", 4, 3, 4, 4, 3, 4, 3, 6, 4, 4, 3, 4, 4, 4, 4, 3, 4,
				2, 4, 4, 4, 4, 4, 4, 4);
		DedicatedProcessingFacility dpfToronto = new DedicatedProcessingFacility(
				"TorontoON", 4, 4, 4, 4, 4, 3, 5, 5, 3, 3, 4, 3, 3, 4, 4, 4, 4,
				4, 2, 4, 4, 4, 3, 4, 4);
		DedicatedProcessingFacility dpfVancouver = new DedicatedProcessingFacility(
				"VancouverBC", 4, 4, 4, 4, 4, 4, 5, 6, 4, 4, 4, 4, 4, 4, 4, 4,
				4, 4, 4, 2, 3, 4, 4, 4, 4);
		DedicatedProcessingFacility dpfVictoria = new DedicatedProcessingFacility(
				"VictoriaBC", 4, 4, 4, 4, 4, 4, 5, 6, 4, 4, 4, 4, 4, 4, 4, 4,
				4, 4, 4, 3, 2, 4, 4, 4, 4);
		DedicatedProcessingFacility dpfWhitehorse = new DedicatedProcessingFacility(
				"WhitehorseYT", 4, 4, 4, 4, 4, 4, 5, 6, 4, 4, 4, 4, 4, 4, 4, 4,
				4, 4, 4, 4, 4, 2, 4, 4, 4);
		DedicatedProcessingFacility dpfWindsor = new DedicatedProcessingFacility(
				"WindsorON", 4, 4, 4, 4, 4, 3, 5, 6, 3, 3, 4, 4, 3, 4, 4, 4, 4,
				4, 3, 4, 4, 4, 2, 4, 4);
		DedicatedProcessingFacility dpfWinnipeg = new DedicatedProcessingFacility(
				"WinnipegMB", 4, 4, 4, 4, 4, 4, 5, 6, 4, 4, 4, 4, 4, 4, 3, 4,
				3, 4, 4, 4, 4, 4, 4, 2, 4);
		DedicatedProcessingFacility dpfYellowknife = new DedicatedProcessingFacility(
				"YellowknifeNT", 4, 4, 4, 4, 4, 4, 5, 6, 4, 4, 4, 4, 4, 4, 4,
				4, 4, 4, 4, 4, 4, 4, 4, 4, 2);
	}

}
