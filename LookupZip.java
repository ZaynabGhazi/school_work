/* Name:    Zaynab Ghazi
 * File:    LookupZip.java
 * Desc:
 *
 * 
 *
 * This program reads two files containing zipcode population and location data
 * and allows to retrieve that data from just the zipcode
 */

import java.util.*;
import java.io.*;

public class LookupZip{
    //create constants for indices
    public static final int ZIP=0;
    public static final int TOWN=1;
    public static final int STATE=2;
    public static final int POPULATION=3;
    public static final int LATITUDE=5;
    public static final int LONGITUDE=6;
    /** Parses one line of input by creating a Place (or PopulatedPlace) that
     *  denotes the information in the given line
     *  @param lineNumber The line number of this line
     *  @param line One line from the zipcodes file
     *  @return A Place that contains the relevant information
     *  (zip code, town, state) or (zip code, town, state, latitude, longitude, population) from that line
     */
    public static Place parseLine(int lineNumber, String line){
       	String[] Field = line.split(",");
	if (Field.length >=4 ){
	    PopulatedPlace populatedPlace = new PopulatedPlace(Field[ZIP],Field[TOWN],Field[STATE],0.0,0.0,Field[POPULATION]);
	    return populatedPlace;
	}
	Place place = new Place(Field[ZIP],Field[TOWN],Field[STATE]);
	return place;
    }
    /** Reads two zipcodes file, parsing every line
     *  @param filename1 The name of the zipcodes file1 containing population data
     *  @param filename2 The name of the zipcodes file2 containing location data
     *  @return The array of Places representing all the
     *  data in the file.
     */
    public static ArrayList<Place> readZipCodes(String filename1, String filename2) throws FileNotFoundException{
	
	Scanner input1 = new Scanner(new File (filename1));
	//skip the first line that doesn't contain relevant data
	String skippedline = input1.nextLine();  
	ArrayList<Place> lst = new ArrayList<Place>();
	int j=0;
	while(input1.hasNextLine()){
	    lst.add(j , parseLine(j+2,input1.nextLine()));
	    j++;
	}
	input1.close();
	
	Scanner input2 = new Scanner(new File (filename2));
	String FirstLine2 = input2.nextLine();
	while(input2.hasNextLine()){
	    String line = input2.nextLine();
	    String[] Fields = line.split(",",-1);
	    String code = Fields[ZIP].replace("\"",""); //retrieve only number without("")
		 //set-up a loop to check if any zipcode from the arraylist matches the one from the file line
		for(int i = 0; i <= lst.size()-1; i++){ 
		    String zipcode = lst.get(i).getZip();
		    if(code.equals(zipcode) && !Fields[LATITUDE].isEmpty() && !Fields[LONGITUDE].isEmpty()){
			//retrieve zipcode data for potential creation of new object or modification of object instance variable
			String state = lst.get(i).getState();
			String town = lst.get(i).getTown();
			String population = lst.get(i).getPopulation();
			double latitude = Double.parseDouble(Fields[LATITUDE]);
			double longitude = Double.parseDouble(Fields[LONGITUDE]);
			if(!population.isEmpty()){
			    //update latitude and longitude by file data 
			    ((PopulatedPlace)lst.get(i)).setLatitude(latitude);
			    ((PopulatedPlace)lst.get(i)).setLongitude(longitude);
			}
			else{
			    //create a locatedPlace with new location data from file and replace existing Place object
			    LocatedPlace locatedPlace = new LocatedPlace(zipcode,town,state,latitude,longitude);
			    lst.set(i,locatedPlace );
			}
			break;
		    }
		}
	}//while-end
	input2.close();
	return lst;
    }
    /** Find a Place with a given zip code
     *  @param zip The zip code (as a String) to look up
     *  @param ArrayList that contains the data (Places)
     *  @return A place that matches the given zip code,
     *  or null if no such place exists.
     */
    public static Place lookupZip(ArrayList<Place> lst, String zip){
	for(int i = 0; i < lst.size(); i++){
	    String code = lst.get(i).getZip();
	    if (code.equals(zip)) return  lst.get(i);
	}
	return null;
    }
}

