/* Name:   Zaynab Ghazi 
 * File:    Person.java
 * Desc:
 *
 * This program creates a new object Person to store 
 * identification data of suspects.
 */

import java.util.*;
import java.io.*;
public class Person implements Comparable<Person>{
    //initialize instance variables to respectively store suspect's gender,
    // race,date of birth (dob), height in feet, eye color and general build.
    private char sex;
    private char race;
    private int dob;
    private int heightFt;
    private String eyeColor;
    private char build;
    //intialize constructor()
    public Person(char a, char b, int c, int d, String e, char f){
	this.sex =a;
	this.race=b;
	this.dob=c;
	this.heightFt=d;
	this.eyeColor=e;
	this.build=f;
    }
    //override Object's compareTo
    /* returns 0 if Persons are equal or string comparison
     * of their references => see getRef()
     * @param other the person to be compare to this
     * @return comparison integer
     */
    public int compareTo(Person other){
	return this.getRef().compareTo(other.getRef());
	
    }
    /*override toString()
     * @return person's reference => see getRef()
     */
    public String toString(){
        return this.getRef();
    }
    //override Object's equals()
    public boolean equals(Person other){
	return this.compareTo(other) ==0;
    }
    /* @return long string containing all comparison creteria for persons
     */
    public String getRef(){
	return this.sex+" "+this.race+" "+this.dob+" "+this.heightFt+" "+this.eyeColor+" "+this.build;
    }
    
    //Unit-Test
    public static void main(String[] args){
	Person a = new Person('M','B',12,8,"BR",'M');
	Person b = new Person('M','B',12,8,"BR",'M');
	Person c = new Person('M','C',12,8,"BR",'M');
	System.out.println( a.compareTo(b)+" "+ b.compareTo(c));
    }
	
}
    
