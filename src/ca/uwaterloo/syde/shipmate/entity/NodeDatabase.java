package ca.uwaterloo.syde.shipmate.entity;

import org.w3c.dom.NodeList;

public class NodeDatabase {
	private static int currentDpfIndex = 0;
	private static final int numOfDpf = 25;
	private static String[] dpfKeys = new String[numOfDpf];
	private static DedicatedProcessingFacility[] dpfGrid = 
			new DedicatedProcessingFacility[numOfDpf]; 
	
	private static NodeList fsaDpfLookup;
	private static NodeList remoteLookup;
	
	public static void setFsaDpfLookup(NodeList temp_fsaDpfLookup) {
		NodeDatabase.fsaDpfLookup = temp_fsaDpfLookup;
	}
	public static NodeList getFsaDpfLookup() {
		return fsaDpfLookup;
	}
	
	public static void setRemoteLookup(NodeList temp_remoteLookup) {
		NodeDatabase.remoteLookup = temp_remoteLookup;
	}
	public static NodeList getRemoteLookup() {
		return remoteLookup;
	}
	
	public static DedicatedProcessingFacility getDpf(String key) {
		return dpfGrid[getIndexOf(key)];
	}


	/*
	 *  initializes dpfGrid with values from Delivery Standards site
	 */
	public static void setupDpfGrid()
	{
		// HTML document only contains an image, no parsable text, so hardcoded
		// TODO- parse data from pdf available from Delivery Standards site
		DedicatedProcessingFacility dpfCalgary = newDedicatedProcessingFacility("CalgaryAB", 			2, 4, 3, 4, 4, 4, 5, 6, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4);
		dpfGrid[getNextIndex()] = dpfCalgary;
		DedicatedProcessingFacility dpfCharlottetown = newDedicatedProcessingFacility("CharlottetownPE", 4, 2, 4, 4, 3, 4, 4, 6, 4, 4, 3, 4, 4, 4, 4, 3, 4, 3, 4, 4, 4, 4, 4, 4, 4);
		dpfGrid[getNextIndex()] = dpfCharlottetown;
		DedicatedProcessingFacility dpfEdmonton = newDedicatedProcessingFacility("EdmontonAB", 			3, 4, 2, 4, 4, 4, 5, 6, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4);
		dpfGrid[getNextIndex()] = dpfEdmonton;
		DedicatedProcessingFacility dpfFredericton = newDedicatedProcessingFacility("FrederictonNB", 	4, 4, 4, 2, 3, 4, 5, 6, 4, 4, 3, 4, 4, 4, 4, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4);
		dpfGrid[getNextIndex()] = dpfFredericton;
		DedicatedProcessingFacility dpfHalifax = newDedicatedProcessingFacility("HalifaxNS", 			4, 3, 4, 3, 2, 4, 4, 6, 4, 4, 3, 4, 4, 4, 4, 3, 4, 3, 4, 4, 4, 4, 4, 4, 4);
		dpfGrid[getNextIndex()] = dpfHalifax;
		DedicatedProcessingFacility dpfHamilton = newDedicatedProcessingFacility("HamiltonON", 			4, 4, 4, 4, 4, 2, 5, 6, 3, 3, 4, 4, 3, 4, 4, 4, 4, 4, 3, 4, 4, 4, 3, 4, 4);
		dpfGrid[getNextIndex()] = dpfHamilton;
		DedicatedProcessingFacility dpfHappyValley = newDedicatedProcessingFacility("HappyValleyNL", 	5, 4, 5, 5, 4, 5, 2, 6, 5, 5, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5);
		dpfGrid[getNextIndex()] = dpfHappyValley;
		DedicatedProcessingFacility dpfIqualuit = newDedicatedProcessingFacility("IqaluitNU", 			6, 6, 6, 6, 6, 6, 6, 2, 6, 6, 6, 4, 5, 5, 6, 6, 6, 6, 5, 6, 6, 6, 6, 6, 6);
		dpfGrid[getNextIndex()] = dpfIqualuit;
		DedicatedProcessingFacility dpfKitchener = newDedicatedProcessingFacility("KitchenerON", 		4, 4, 4, 4, 4, 3, 5, 6, 2, 3, 4, 4, 3, 4, 4, 4, 4, 4, 3, 4, 4, 4, 3, 4, 4);
		dpfGrid[getNextIndex()] = dpfKitchener;
		DedicatedProcessingFacility dpfLondon = newDedicatedProcessingFacility("LondonON", 				4, 4, 4, 4, 4, 3, 5, 6, 3, 2, 4, 4, 3, 4, 4, 4, 4, 4, 3, 4, 4, 4, 3, 4, 4);
		dpfGrid[getNextIndex()] = dpfLondon;
		DedicatedProcessingFacility dpfMoncton = newDedicatedProcessingFacility("MonctonNB", 			4, 3, 4, 3, 3, 4, 4, 6, 4, 4, 2, 4, 4, 4, 4, 3, 4, 3, 4, 4, 4, 4, 4, 4, 4);
		dpfGrid[getNextIndex()] = dpfMoncton;
		DedicatedProcessingFacility dpfMontreal = newDedicatedProcessingFacility("MontralQC", 			4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 2, 3, 3, 4, 4, 4, 4, 3, 4, 4, 4, 4, 4, 4);
		dpfGrid[getNextIndex()] = dpfMontreal;
		DedicatedProcessingFacility dpfOttawa = newDedicatedProcessingFacility("OttawaONGatineauQC", 	4, 4, 4, 4, 4, 3, 5, 5, 3, 3, 4, 3, 2, 3, 4, 4, 4, 4, 3, 4, 4, 4, 3, 4, 4);
		dpfGrid[getNextIndex()] = dpfOttawa;
		DedicatedProcessingFacility dpfQuebec = newDedicatedProcessingFacility("QubecQC", 				4, 4, 4, 4, 4, 4, 5, 5, 4, 4, 4, 3, 3, 2, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4);
		dpfGrid[getNextIndex()] = dpfQuebec;
		DedicatedProcessingFacility dpfRegina = newDedicatedProcessingFacility("ReginaSK", 				4, 4, 4, 4, 4, 4, 5, 6, 4, 4, 4, 4, 4, 4, 2, 4, 3, 4, 4, 4, 4, 4, 4, 3, 4);
		dpfGrid[getNextIndex()] = dpfRegina;
		DedicatedProcessingFacility dpfStJohn = newDedicatedProcessingFacility("StJohnNB", 				4, 3, 4, 3, 3, 4, 4, 6, 4, 4, 3, 4, 4, 4, 4, 2, 4, 3, 4, 4, 4, 4, 4, 4, 4);
		dpfGrid[getNextIndex()] = dpfStJohn;
		DedicatedProcessingFacility dpfSaskatoon = newDedicatedProcessingFacility("SaskatoonSK", 		4, 4, 4, 4, 4, 4, 5, 6, 4, 4, 4, 4, 4, 4, 3, 4, 2, 4, 4, 4, 4, 4, 4, 3, 4);
		dpfGrid[getNextIndex()] = dpfSaskatoon;
		DedicatedProcessingFacility dpfStJohns = newDedicatedProcessingFacility("StJohnsNL", 			4, 3, 4, 4, 3, 4, 3, 6, 4, 4, 3, 4, 4, 4, 4, 3, 4, 2, 4, 4, 4, 4, 4, 4, 4);
		dpfGrid[getNextIndex()] = dpfStJohns;
		DedicatedProcessingFacility dpfToronto = newDedicatedProcessingFacility("TorontoON",				4, 4, 4, 4, 4, 3, 5, 5, 3, 3, 4, 3, 3, 4, 4, 4, 4, 4, 2, 4, 4, 4, 3, 4, 4);
		dpfGrid[getNextIndex()] = dpfToronto;
		DedicatedProcessingFacility dpfVancouver = newDedicatedProcessingFacility("VancouverBC", 		4, 4, 4, 4, 4, 4, 5, 6, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 3, 4, 4, 4, 4);
		dpfGrid[getNextIndex()] = dpfVancouver;
		DedicatedProcessingFacility dpfVictoria = newDedicatedProcessingFacility("VictoriaBC",			4, 4, 4, 4, 4, 4, 5, 6, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 2, 4, 4, 4, 4);
		dpfGrid[getNextIndex()] = dpfVictoria;
		DedicatedProcessingFacility dpfWhitehorse = newDedicatedProcessingFacility("WhitehorseYT",		4, 4, 4, 4, 4, 4, 5, 6, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 4, 4, 4);
		dpfGrid[getNextIndex()] = dpfWhitehorse;
		DedicatedProcessingFacility dpfWindsor = newDedicatedProcessingFacility("WindsorON",				4, 4, 4, 4, 4, 3, 5, 6, 3, 3, 4, 4, 3, 4, 4, 4, 4, 4, 3, 4, 4, 4, 2, 4, 4);
		dpfGrid[getNextIndex()] = dpfWindsor;
		DedicatedProcessingFacility dpfWinnipeg = newDedicatedProcessingFacility("WinnipegMB", 			4, 4, 4, 4, 4, 4, 5, 6, 4, 4, 4, 4, 4, 4, 3, 4, 3, 4, 4, 4, 4, 4, 4, 2, 4);
		dpfGrid[getNextIndex()] = dpfWinnipeg;
		DedicatedProcessingFacility dpfYellowknife = newDedicatedProcessingFacility("YellowknifeNT",		4, 4, 4, 4, 4, 4, 5, 6, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2);
		dpfGrid[getNextIndex()] = dpfYellowknife;
	}
	
	private static DedicatedProcessingFacility newDedicatedProcessingFacility(String key, int... distanceList) {
		dpfKeys[currentDpfIndex] = key;
		return new DedicatedProcessingFacility(key, distanceList);
	}
	
	/*
	 * Returns and increases currentNumOfDpf
	 */
	public static int getNextIndex()
	{
		if (currentDpfIndex < numOfDpf)
		{
			currentDpfIndex ++;
			return currentDpfIndex - 1;
		} else
		{
			return -1;
		}
	}
	/*
	 * Returns the index of the provided dpf name
	 * 	-1 if not found in list
	 */
	public static int getIndexOf(String dpfName)
	{
		int indexOfDpf = -1;
		int numOfKeys = dpfKeys.length;
		for (int i = 0; i < numOfKeys; i++)
		{
			if (dpfKeys[i].equals(dpfName))
			{
				indexOfDpf = i;
			}
		}
		return indexOfDpf;
	}

}

