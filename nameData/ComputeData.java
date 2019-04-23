/* Name:    Zaynab Ghazi
 * File:    ComputeData.java
 * Desc:
 *
 * This program contains methods that do total, percentage and
 * total rank computations on data stored in Linked Lists and 
 * ArrayList, as well as execute lookups and print to the console 
 * window.
 * 
 */

import java.util.*;
import java.io.*;

public class ComputeData{
    /* updates totals of babies of a certain name/gender
     * matches element (i) from the list to data (i-1) from the ArrayList
     * @param genderNames  The Doubly-linked list of names to update
     * @param totalDataGender The ArrayList containing total data
     * @param totalNumberOfGender The total number of babies of that gender
     * @return  void
     */
    public static void updateTotalNumber(NameDLL genderNames, ArrayList<Data> totalDataGender){
	
	for(int i=1; i<= genderNames.size(); i++){
	    //create placeholder for total number of babies named a certain way for all years
	    int totalNumberOfNamedBabies = 0;
	    //get a Name element from the linked list
	    Name nameRead =  genderNames.getListElement(i);
	    for(int j=0; j< nameRead.getYearlyData().size(); j++){
		totalNumberOfNamedBabies += nameRead.getYearlyData().get(j).getNumber();
	    }
	    //update arrayList with computed values : total number and set year and rank to 0 (year is useless for this computation/rank will be updated lated = total rank)
	    totalDataGender.add(i-1, new Data(0,0,totalNumberOfNamedBabies));
	}
    }
    
    /* updates total ranking of a certain name of a certain gender
     * @param genderNames The Doubly-linked list of names to rank
     * @param totalDataGender the Arraylist of total data of each name of a gender
     * @return void
     */
    public static void updateTotalRank (NameDLL genderNames, ArrayList<Data> totalDataGender){
	for(int i=1; i<= genderNames.size(); i++){
	    //create placeholder for number of totals equal to or less than the total number of the name in question
	    //knowing that name in (i) is matched with total data in (i-1)
	    int countsup =0;
	    for(int j=1; j<= genderNames.size() ; j++){
		if (totalDataGender.get(i-1).getNumber() >= totalDataGender.get(j-1).getNumber()){
		    countsup++;
		}
	    }
	    //The formula to compute the rank is :genderNames.size()-countsup+1
	    totalDataGender.get(i-1).setRank( genderNames.size()-countsup+1);
	}
    }



    /* executes search for queries and prints their corresponding stats
     * 
     * @param genderQueries  The ArrayList of queries for each gender
     * @param genderNames The LinkedList contaning the name objects of a gender
     * @param totalDataGender The ArrayList contaning total stats of every name of a gender
     * @param totalNumberOfGender The total number of babies of that gender
     * @param correspondingYear A String ArrayList to help select the correct yearly totals for the percentage formula
     * @param correspondingGenderFileTotal An Integer ArrayList of all yearly totals of that gender
     * @return  void
     */
    public static void executeQueries (ArrayList<String> genderQueries,NameDLL genderNames ,ArrayList<Data> totalDataGender , int totalNumberOfGender, ArrayList<String> correspondingYears, ArrayList<Integer> correspondingGenderFileTotal){
	System.out.println();
	for(int k=0; k< genderQueries.size(); k++){
	    
	    for(int i=1; i<= genderNames.size(); i++){
		if (genderQueries.get(k).equals(genderNames.getListElement(i).getName())){
		    // print the Name's ranking in the LinkedList genderNames 
		    System.out.println( genderNames.getLLRank(genderQueries.get(k)));
		    System.out.println();
		    // print stats for each year for the name
		    for(int a=0; a< genderNames.getListElement(i).getYearlyData().size(); a++){
			Data  yearData = genderNames.getListElement(i).getYearlyData().get(a);
			System.out.println(yearData.getYear());
			System.out.print(genderQueries.get(k)+": ");
			//print formatted the stats for the year
			for(int m=0; m< correspondingYears.size(); m++){
			    //look for the yearly total corresponding to the year being processed 
			    if(Integer.parseInt(correspondingYears.get(m)) == yearData.getYear()){
				System.out.printf("%d, %d, %7.6f\n", yearData.getRank(),yearData.getNumber(),((double)yearData.getNumber())/correspondingGenderFileTotal.get(m) );
				System.out.println();
			    }
			}
		    }
		    
		    //Get the matching total stats from element (i-1) of the arrayList and print it formatted
		    System.out.println("Total");
		    System.out.print(genderQueries.get(k)+": ");
		    System.out.printf("%d, %d, %7.6f\n",totalDataGender.get(i-1).getRank(),totalDataGender.get(i-1).getNumber(),((double)totalDataGender.get(i-1).getNumber())/totalNumberOfGender);
		    System.out.println();
		    
		    
			
		}
	    }
	}
    }
}//class
