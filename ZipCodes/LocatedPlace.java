/* Name:    Zaynab Ghazi
 * File:    LocatedPlace.java
 * Desc:
 *
 * This program creates an object LocatedPlace 
 * that has 5 fields:
 * zipcode, town, state, latitude and longitude .
 */
public class LocatedPlace extends Place{
    //create 3 String instance variables to respectively store the zipcode of the place, its corresponding town and its state
    private String zip, town, state;
    //create 2 double instance variables to respectively store the place's latitude and longitude
    private double latitude,longitude;
    //create constructor
    public LocatedPlace(String zip, String town, String state,double latitude, double longitude){
	super(zip, town, state);
	this.latitude = latitude;
	this.longitude = longitude;
    }
    //override toString() from superclass place to include location
    public String toString(){
	return ""+getTown()+", "+getState()+" "+latitude+" "+longitude;
    }
    //create getter to access latitude
    public double getLatitude(){
	return this.latitude;
    }
    //create getter to access longitude
    public double getLongitude(){
	return this.longitude;
    }
    //create setter to modify latitude
    public void setLatitude(double x){
	this.latitude=x;
    }
    //create setter to modify longitude
    public void setLongitude(double x){
	this.longitude=x;
    }
}
