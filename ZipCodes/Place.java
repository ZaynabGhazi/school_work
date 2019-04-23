/* Name:    Zaynab Ghazi
 * File:    Place.java
 * Desc:
 *
 * This program creates an object Place 
 * that has 3 fields:
 * zipcode, town and state
 */
import java.io.*;
import java.util.*;

public class Place{
    //create 3 String instance variables to respectively store the Place's zipcode and its corresponding town and state information
    private String zip, town, state;
    /** Creates a Place with the given zip, town name, and
     *  state
     *  @param zip The 5-digit zip code
     *  @param town The town name
     *  @param state The state abbreviation
     */
    public Place(String zip, String town, String state){
	this.zip = zip;
	this.town = town;
	this.state = state;
    }
    //create instance method to access private variable:zip
    public String getZip(){
	return this.zip;
    }
    //create instance method to access private variable:town
    public String getTown(){
	return this.town;
    }
    //create instance method to access private variable: state
    public String getState(){
	return this.state;
    }
    //specify the print format of object Place
    public String toString(){
	return ""+town+", "+state+" ";
    }
    //create getter to differentiate between Place and PopulatedPlace in client program
    public String getPopulation(){
	return "";
    }
}
