/* Name:    Zaynab Ghazi
 * File:    Main.java
 * Desc:
 *
 * The main driver program for Assignment 8.
 *
 * This program reads the file specified by the first command line
 * argument and deduplicates its data before printing to the console
 * the number of duplicates found and the criteria used to that end.
 */
import java.util.*;
import java.io.*;
public class Main{
    public static void main(String[] args) throws FileNotFoundException{
	
	DataProcessor nypd = new DataProcessor(args[0]);
	ArrayList<Person> lst = nypd.hashDoubleDeduplication();
        System.out.println("Records given: "+nypd.recordCount);
	System.out.println("Attributes checked: gender, race, date of birth, height in feet, eye color, build.");
	int duplicates = nypd.recordCount - lst.size();
	System.out.println("Duplicates found: "+ duplicates);
    }
}
	
