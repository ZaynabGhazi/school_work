/* Name:    Zaynab Ghazi
 * File:   ReadFiles.java
 * Desc:
 *
 * This program reads a file and updates two LinkedLists with specific
 * stats from the file and computes total number of babies from all files
 *
 */
import java.io.*;
import java.util.*;
public class ReadFiles{
    
    //initialize constants
    public static final int MALENAME =1;
    public static final int FEMALENAME=3;
    public static final int RANK =0;
    public static final int  NUMBEROFMALES=2;
    public static final int  NUMBEROFFEMALES=4;
    
    /* reads a file and updates the parameters 
     * @param filename : file to be read
     * @param maleNames: linked list of male babies to be updated
     * @param fileNames: linked list of female babies to be updated
     * @return ArrayList<Integer> Totals contaning total numbers of male and female babies from all years
     */
    public static ArrayList<Integer> parseFile (String filename, NameDLL maleNames, NameDLL femaleNames) throws FileNotFoundException{
	int totalMale =0;
	int totalFemale=0;
	ArrayList<Integer> Totals = new ArrayList<Integer>(2);
	
	Scanner input = new Scanner(new File(filename));
	int yearOfFile= Integer.parseInt(filename.substring(filename.length()-8,filename.length()-4));
	
	while(input.hasNextLine()){
	    String line= input.nextLine();
	    String[] fields= line.split(",");
	    
	    //initialize boolean variables to check if name is already in the list
	    boolean maleplaced=false;
	    boolean femaleplaced=false;
	    
	    for(int i=1; i<= maleNames.size(); i++){
		
		if (maleNames.getListElement(i).getName().equals(fields[MALENAME])) {
		    maleNames.getListElement(i).getYearlyData().add(new Data(yearOfFile,Integer.parseInt(fields[RANK]),Integer.parseInt(fields[NUMBEROFMALES])));
		    maleplaced=true;
		    //update yearly total number
		    totalMale+=Integer.parseInt(fields[NUMBEROFMALES]);
		    
		    break;
		}
	    }
	    if (!maleplaced){
		Name maleName = new Name(fields[MALENAME]);
		maleName.getYearlyData().add(new Data(yearOfFile,Integer.parseInt(fields[RANK]),Integer.parseInt(fields[NUMBEROFMALES])));
		maleNames.insertSorted(maleName);
		//update yearly total number
		totalMale+=Integer.parseInt(fields[NUMBEROFMALES]);
	    
	    }
	    
	    for(int i=1; i<= femaleNames.size(); i++){
		
		if (femaleNames.getListElement(i).getName().equals(fields[FEMALENAME])) {
		    femaleNames.getListElement(i).getYearlyData().add(new Data(yearOfFile,Integer.parseInt(fields[RANK]),Integer.parseInt(fields[NUMBEROFFEMALES])));
		    femaleplaced=true;
		    //update yearly total number
		     totalFemale+=Integer.parseInt(fields[NUMBEROFFEMALES]);
		    break;
		}
	    }
	    if (!femaleplaced){
		Name femaleName = new Name(fields[FEMALENAME]);
		femaleName.getYearlyData().add(new Data(yearOfFile,Integer.parseInt(fields[RANK]),Integer.parseInt(fields[NUMBEROFFEMALES])));
		femaleNames.insertSorted(femaleName);
		//update yearly total number
		totalFemale+=Integer.parseInt(fields[NUMBEROFFEMALES]);
		    
	    }	
	}
	Totals.add(0,totalMale);
	Totals.add(1,totalFemale);
	return Totals;
    }
}


