/* Name:    Zaynab Ghazi
 * File:    DataProcessor.java
 * Desc:
 *
 * 
 *
 * This program creates a constructor that reads a csv file (as specified
 * by its argument) and stores some of its targeted data. It also creates
 * multiple methods to deduplicate the targeted data.
 */

import java.util.*;
import java.io.*;
public class DataProcessor{
    //initialize CONSTANTS to correspond to specific fields in the csv file and store creteria for deduplication; respectively:
    // gender, race, date of birth, height in feet, eye color and build of suspect:
    private static final int SEX=80;
    private static final int RACE=81;
    private static final int DOB=82;
    public static final int HEIGHTFT=84;
    public static final int EYECOLOR=88;
    public static final int BUILD=89;
    //initialize instance variable to hold the reference to the list to be filled with processed data from the file:
    private ArrayList<Person> lst;
    //initialize instance variable to count number of records to be used in Main=> public variable (note that the first line that's escaped is not counted):
    public int recordCount=0;
    
    //intialize constructor() to process data of the file referenced from its argument:
    public DataProcessor(String filename) throws FileNotFoundException{
	lst = new ArrayList();
	Scanner input = new Scanner(new FileReader(filename));
        //skip the first line of the csv file
	String escapedLine = input.nextLine();
       	while(input.hasNextLine()){
	    recordCount++;
	    String line = input.nextLine();
	    //CORRECTION BLOC to skip quoted commas
	    if (line.contains("\"")){
		int first= line.indexOf("\"");
		while(first >0 ){
		     String problem = line.substring(first,line.indexOf("\"",first+1)+1);
		  	int second = line.indexOf("\"",first+1);
			//look for comma between quoted
			if(problem.contains(",")){
			    //replace comma by space not to change string indices
			    problem=problem.replace(","," ");
			    line=line.replace(line.substring(first,line.indexOf("\"",first+1)+1),problem);
			}
			//look for another quote symbol
			first = line.indexOf("\"",second+1);
		}
	    }
	    String[] fields = line.split(",");
	    //create Person object to store the suspect's data
	    Person suspect = new Person(fields[SEX].charAt(0),fields[RACE].charAt(0),Integer.parseInt(fields[DOB]),Integer.parseInt(fields[HEIGHTFT]),fields[EYECOLOR],fields[BUILD].charAt(0));
	    lst.add(suspect);
	}
	//close scanner
	input.close();
    }//DataProcessor-end

    //THE DEDUPLICATION METHODS:
    //METHOD 1
    /* deduplicates the data of the DataProcessor list
     * @return ArrayList of only singular data
     * uses linear search
     */
    public  ArrayList<Person> allPairsDeduplication(){
	ArrayList<Person> unduplicated = new ArrayList<>();
	for (int i=0;i<this.lst.size();i++){
	    int dup =0;
	    //compare each element to elements after it in the list
	    for(int j=i+1; j< this.lst.size();j++){
		if (lst.get(i).compareTo(lst.get(j)) == 0 ) dup++;
	    }
	    if (dup == 0) unduplicated.add(lst.get(i));
	}
	return unduplicated;
    }

    //initialise a constant to hold value for the HashMaps' size
    private static final int SIZE = 1000003;
    
    //METHOD2
    /* deduplicates the data of the DataProcessor list
     * @return ArrayList of only singular data
     * uses a linear probing(open addressing) hashMap
     */
    public ArrayList<Person> hashLinearDeduplication(){
	ArrayList<Person> unduplicated = new ArrayList<>();
	ProbeHashMap<String, Person> map = new ProbeHashMap(SIZE);
	//The probe count is adapted to the implementation of the maps
	// and use of public instance variable probes() 
	double average=0.0;
	int  max = 0;
	int insertCount =0;
	for(int i=0; i< this.lst.size(); i++){
	    map.put(lst.get(i).getRef(),lst.get(i));
	    //count insertions:
	    insertCount++;
	    //increment sum of probes:
	    average += map.probes;
	    //compute max # of probes:
	    if ( map.probes >= max) max = map.probes;
	}
	System.out.println ("Average number of probes:  "+ average/insertCount );
	System.out.println("Max number of probes: "+ max);
	System.out.println("Load-factor: "+ (double)map.size()/SIZE );
	//initialize iterator to collect singular suspects from the map:
        Iterator<Person> coll = map.values().iterator();
	while(coll.hasNext()){
	    Person suspect = coll.next();
	    unduplicated.add(suspect);
	}
	return unduplicated;
    }
    
    //METHOD3
    /* deduplicates the data of the DataProcessor list
     * @return ArrayList of only singular data
     * uses a double probing hashMap
     */
    public ArrayList<Person> hashDoubleDeduplication(){
	ArrayList<Person> unduplicated1 = new ArrayList<>();
	DoubleHashMap<String, Person> map = new DoubleHashMap(SIZE);
	//The probe count is adapted to the implementation of the maps
	 // and use of public instance variable probes() 
	double average=0.0;
	int  max = 0;
	int insertCount =0;
	for(int i=0; i< this.lst.size(); i++){
	    map.put(lst.get(i).getRef(),lst.get(i));
	    //count insertions:
	    insertCount++;
	    //increment sum of probes:
	    average += map.probes;
	    //compute max # of probes:
	    if ( map.probes >= max) max = map.probes;
	}
	//The following lines of codes were commented after usage in the first part of the assignment:
	//System.out.println ("Average number of probes:  "+ average/insertCount );
	//System.out.println("Max number of probes: "+ max);
	//System.out.println("Load-factor: "+ (double)map.size()/SIZE );
	//initialize iterator to collect singular suspects from the map:
	Iterator<Person> coll = map.values().iterator();
	while(coll.hasNext()){
	    Person suspect = coll.next();
	    unduplicated1.add(suspect);
	}
	return unduplicated1;
    }
    
    //METHOD4
    /* deduplicates the data of the DataProcessor list
     * @return ArrayList of only singular data
     * uses personal implementation of quickSort
     */
    public ArrayList<Person> quicksortDeduplication(){
	//copy data from list to array arr
	Person[] arr = new Person[this.lst.size()];
	for(int i=0; i< lst.size();i++){
	    arr[i] = this.lst.get(i);
	}
	//sort array => see QuickSort class
	QuickSort.sort(arr,0,arr.length-1);
	//create list to store singular data
	ArrayList<Person> unduplicated2 = new ArrayList<>();
	//compare successive elements of array to find duplicates
	int limit =0;
	if (arr.length ==0 || arr.length==1) limit =arr.length;
	int j=0;
	//store only singular data in the beginning of the array
	for (int i = 0; i < arr.length-1; i++) 
            if (arr[i].compareTo( arr[i+1]) != 0) 
                arr[j++] = arr[i]; 
	// add last element to array
        arr[j++] = arr[arr.length-1];
	//record last index of singular element
	limit =j;
	//copy elements up to the singularity index to the list
	for(int k=0; k< limit; k++){
	    unduplicated2.add(arr[k]);
	}
	return unduplicated2;
    }
    //METHOD 5
    /* deduplicates the data of the DataProcessor list
     * @return ArrayList of only singular data
     * uses java's built-in quickSort
     */
    public ArrayList<Person> builtinSortDeduplication(){
	//copy data from list to array arr
	Person[] arr = new Person[this.lst.size()];
	for(int i=0; i< lst.size();i++){
	    arr[i] = this.lst.get(i);
	}
	// use java's built-in sort
	Arrays.sort(arr);
	//create list to store singular data
	ArrayList<Person> unduplicated3 = new ArrayList<>();
	//compare successive elements of array to find duplicates
	int limit =0;
	if (arr.length ==0 || arr.length==1) limit =arr.length;
	int j=0;
	//store only singular data in the beginning of the array
	for (int i = 0; i < arr.length-1; i++) 
            if (arr[i].compareTo( arr[i+1]) != 0) 
                arr[j++] = arr[i]; 
	// add last element to array
	arr[j++] = arr[arr.length-1];
	//record last index of singular element
	limit =j;
	//copy elements up to the singularity index to the list
	for(int k=0; k< limit; k++){
	    unduplicated3.add(arr[k]);
	}
	return unduplicated3;
    }
    
    // UNIT_TEST FOR THE DATAPROCESSOR CLASS
    public static void main(String[] args) throws FileNotFoundException {
	DataProcessor data = new DataProcessor("2011.csv");
	
	//method1
	ArrayList<Person> answer = data.allPairsDeduplication();
	System.out.println(answer.size());
	
	//method2
	ArrayList<Person> answer1 = data.hashLinearDeduplication();
	System.out.println(answer1.size());
        
	//method3
	ArrayList<Person> answer2 = data.hashDoubleDeduplication();
	System.out.println(answer2.size());
        
	//method4
	ArrayList<Person> answer3 = data.quicksortDeduplication();
	System.out.println(answer3.size());
        
	//method5
	ArrayList<Person> answer4 = data.builtinSortDeduplication();
	System.out.println(answer4.size());
        

    }//main-end
}//class-end


	    
