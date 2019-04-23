/* Name:    Zaynab Ghazi
 * File:    Data.java
 * Desc:
 *
 * Creates new object "Data"
 *
 *
 */
public class Data{
    //create instance variables to respectively store year, rank and number
    private int year;
    private int rank;
    private int number;
    public Data(int year, int rank, int number){
	this.year=year;
	this.rank=rank;
	this.number=number;
    }
    //getter returns year
    public int getYear(){
	return this.year;
    }
    //getter returns Rank
    public int getRank(){
	return this.rank;
    }
    //getter returns number
    public int getNumber(){
	return this.number;
    }
    //setters updates year with parameter
    public void setYear(int year){
	 this.year=year;
    }
    //setter updates rank with parameter
    public void setRank(int rank){
	this.rank=rank;
    }
    //setter updates number with parameter
    public void setNumber(int number){
	this.number=number;
    }
      //overriding toString()
    public String toString(){
	return	year+", "+rank+", "+number+" ";
    }
    
}
