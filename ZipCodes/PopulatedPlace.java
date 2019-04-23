/* Name:    Zaynab Ghazi
 * File:    PopulatedPlace.java
 * Desc:
 *
 * This program creates an object PopulatedPlace 
 * that has 6 fields:
 * zipcode, town, state, latitude, longitude and population.
 */
public class PopulatedPlace extends LocatedPlace{
    //create 4 String instance variables to store LocatedPlace's zipcode, town, state and population number
    private String zip, town, state, population;
    //create 2 double instance variables to respectively store the zipcode's corresponding longitude and latitude
    private double longitude, latitude;
    //create constructor
    public PopulatedPlace(String zip, String town, String state, double latitude, double longitude, String population){
	super(zip, town, state, latitude, longitude);
	this.population = population ;
    }
    //override toString() method from LocatedPlace to include Population
    public String toString(){
	return ""+getTown()+", "+getState()+" "+getLatitude()+" "+getLongitude()+" "+population;
    }
    //Other getters and setters  will be directly inherited from super class LocatedPlace
    public String getPopulation(){
	return this.population;
    }
}
