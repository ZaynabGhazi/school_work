/* Name:    Zaynab Ghazi
 * File:    Name.java
 * Desc:
 *
 * 
 *
 * This program creates a new object "Name"
 * 
 * 
 */

import java.util.*;
import java.util.*;

public class Name{
    //create instance variables: name, linkedListRank and yearlyData to respectively store the string name and its corresponding yearly stats
    private String name;
    private ArrayList<Data> yearlyData;

    public Name(String name){
	this.name=name;
	yearlyData = new ArrayList<Data>();
    }
    //getter returns String name 
    public String getName(){
	return this.name;
    }
    //override toString()
    public String toString(){
	return this.name+""+yearlyData;
    }
    //getter returns ArrayList of yearlyData 
    public ArrayList<Data>  getYearlyData(){
	return this.yearlyData;
    }
}// class Name
