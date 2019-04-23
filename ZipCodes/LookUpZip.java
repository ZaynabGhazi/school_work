/* Name:    Zaynab Ghazi
 * File:    LookUpZip.java
 * Desc:
 *
 * 
 *
 * This program reads two files containing zipcode population and location data
 * and allows to retrieve that data from just the zipcode
 */

import java.util.*;
import java.io.*;

public class LookUpZip{
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
       	String[] field = line.split(",");
	if (field.length >=4 ){
	    PopulatedPlace populatedPlace = new PopulatedPlace(field[ZIP],field[TOWN],field[STATE],0.0,0.0,field[POPULATION]);
	    return populatedPlace;
	}
	Place place = new Place(field[ZIP],field[TOWN],field[STATE]);
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
	String skippedline1 = input1.nextLine();  
	ArrayList<Place> lst = new ArrayList<Place>();
	int j=0;
	while(input1.hasNextLine()){
	    lst.add(j , parseLine(j+2,input1.nextLine()));
	    j++;
	}
	input1.close();
	
	Scanner input2 = new Scanner(new File (filename2));
	String skippedline2 = input2.nextLine();
	while(input2.hasNextLine()){
	    String line = input2.nextLine();
	    String[] fields= line.split(",",-1);
	    String code = fields[ZIP].replace("\"",""); //retrieve only number without("")
	    // ASSIGNMENT 5 MODIFICATIONS: Using Binary Search to weave the two files:
	    Place target = lookupZip(lst,code); //lookupZip() returns corresponding existing Place
	    if (target != null  && !fields[LATITUDE].isEmpty() && !fields[LONGITUDE].isEmpty()){
		//retrieve zipcode data for potential creation of new object or modification of object instance variable
		String zipcode = target.getZip();
		String state = target.getState();
		String town = target.getTown();
		String population = target.getPopulation();
		double latitude = Double.parseDouble(fields[LATITUDE]);
		double longitude = Double.parseDouble(fields[LONGITUDE]);
		if(!population.isEmpty()){
		    //update latitude and longitude by file data 
		    ((PopulatedPlace)target).setLatitude(latitude);
		    ((PopulatedPlace)target).setLongitude(longitude);
		}
		else{
		    //create a locatedPlace with new location data from file and replace existing Place object
		    LocatedPlace locatedPlace = new LocatedPlace(zipcode,town,state,latitude,longitude);
		    lst.set(lst.indexOf(target),locatedPlace );
		}
	    }
	}//while-end
	input2.close();
	return lst;
    }
    /** Find a Place with a given zip code using binary search
     *  @param zip The zip code (as a String) to look up
     *  @param ArrayList that contains the data (Places)
     *  @return A place that matches the given zip code,
     *  or null if no such place exists.
     */
    public static Place lookupZip(ArrayList<Place> lst, String zip){
	return binarySearch(lst, zip, 0, lst.size()-1);    
    }
    /** Finds the user's target Place using Binary search
     *  binarySearch is a helper-method to lookupZip()
     *  @param ArrayList that contains the data (Places)
     *  @param String of the zipcode
     *  @param int the lower index of the search
     *  @param int the upper index of the search
     *  @return A place that matches the given zip code,
     *  or null if no such place exists.
     */
    public static Place binarySearch(ArrayList<Place> lst, String target, int low, int high){
	if (low > high ) return null;
	else{
	    int mid = (int) ((low+high)/2);
	    int res = target.compareTo(lst.get(mid).getZip());
	    if ( res== 0) return lst.get(mid);
	    else if ( res < 0) return binarySearch(lst,target,low,mid-1);
	    else return binarySearch(lst,target,mid+1,high);
	}
    }
}

