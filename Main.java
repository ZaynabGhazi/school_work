/* Name:    Zaynab Ghazi
 * File:    Main.java
 * Desc:
 *
 * The main driver program for Assignment 2.
 *
 * This program reads two files containing zipcode data and
 * takes a zipcode from the user (Std.Input) and returns
 * the town, state and potentially population, latitude and longitude
 * corresponding to the zipcode location.
 *
 */
import java.util.*;
import java.io.*;

public class Main{
    //create constant for the escape zipcode to exit the program
    public static final String ESCAPECODE = "00000";
    /**
     * This is the main method which makes use of LookupZip class
     * @param args Unused.
     * @return Nothing.
     * @exception FileNotFound
     *
     */
    public static void main(String[] args)throws FileNotFoundException {
	ArrayList<Place> places =  LookupZip.readZipCodes("uszipcodes.csv","ziplocs.csv");
	Scanner user = new Scanner(System.in);
	String query = ""; //create variable for user input

	while (!query.matches(ESCAPECODE)){
	    System.out.print("zipcode: ");
	    query = user.nextLine();
	    if (query.matches(ESCAPECODE)) {
		System.out.println("Good Bye!");
		break;
	    }
	    Place searched = LookupZip.lookupZip(places,query);
	    if(searched == null )System.out.println("No such zipcode"); 
	    else  System.out.println(searched);
	    System.out.println();
	}
    }
}
