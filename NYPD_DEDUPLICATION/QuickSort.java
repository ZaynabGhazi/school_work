
/* Name:    Zaynab Ghazi
 * File:    QuickSort.java
 * Desc:
 *

 *
 * This program implement the quick-sort algorithm.
 */

import java.util.*;
import java.io.*;
    public class QuickSort{
	
	/* sorts pivot element and puts other elements inferior to it before it while
	 * the ones superior to it are inserted after it
	 * @param arr the array to be partitioned
	 * @param low lowest index of arr
	 * @param high highest index of arr
	 * @return index of sorted pivot
	 */
	private static<E extends Comparable<E>> int partition(E[] arr, int low, int high){
	    // make last element the pivot
	    E pivot= arr[high];
	    int i = low-1;
	    for(int j=low; j<high; j++){
		//get element if it is less than pivot
		if(arr[j].compareTo(pivot) <=0){
		    //increment index of smaller elemnt
		    i++;
		    E temp =arr[i];
		    arr[i]=arr[j];
		    arr[j]=temp;
		}
	    }
	    E temp = arr[i+1]; 
	    arr[i+1] = arr[high]; 
	    arr[high] = temp; 
	    
	    return i+1;
	}
	
	/* main function that implements quickSort recursively
	 * @param arr the array to be sorted
	 * @param low smallest index for local  sort
	 * @param high highest index for local sort
	 * @return void
	 */
	public static<E extends Comparable<E>> void sort(E[] arr, int low, int high){
	    if(low<high){
		//sort the pivot by partitioning
		int pivot = partition(arr,low,high);
		//sort elements other than pivot
		sort(arr,low,pivot-1);
		sort(arr,pivot+1,high);
	    }
	}
	//UNIT-TEST
	public static void main(String[] args){
	    Integer[] arr = {10,7,7,8,9,1,5,7};
	    sort(arr,0,arr.length-1);
	    for (int i=0; i<arr.length; ++i) 
            System.out.print(arr[i]+" ");
	}
    }
    
	    
