/**
* @author: Zaynab
* file : ToDecimal.java
* description: this program takes a 2's complement from standard input
* and converts it to a decimal.
*/
import java.util.*;
import java.io.*;
public class ToDecimal{
  public static void main(String[] args){
    String twoscomplement = args[0];
    long decimal=0;
    //check if number is positive
    if (twoscomplement.charAt(0) == '0')
      //simply convert magnitude using powers of 2 computations:
      for (int i=twoscomplement.length()-1; i>0 ;i--){
        int digit = Integer.parseInt(String.valueOf(twoscomplement.charAt(i)));
        int index = twoscomplement.length()-1-i;
        decimal += digit*Math.pow(2,index);
      }
      //if number is negative:
      else{
        String complement ="";
          //compute number's complement by flipping bits:
          for (int i=0; i<twoscomplement.length() ;i++){
            if (twoscomplement.charAt(i)=='0') complement +='1';
            else complement+='0';
          }
          //add 1 to binary representation:
          //carried indicates whether a carry-out was cleared:
          boolean carried = false;
          char[] complement_arr = complement.toCharArray();
          for(int i=complement_arr.length-1; i>=0;i--){
            if(!carried){
              if (complement_arr[i]=='1'){
                complement_arr[i] ='0';
              }
              else{
                complement_arr[i] ='1';
                carried=true;
               }
             }
          }
          //convert new  binary to decimal using powers of 2:
          for(int i=complement_arr.length-1;i>=0;i--){
            int digit = Integer.parseInt(String.valueOf(complement_arr[i]));
            int index = complement_arr.length-1-i;
            decimal += digit*Math.pow(2,index);
          }
          //add - sign to represent negative number
          decimal = decimal*(-1);
      }
  System.out.println("The decimal value of the two's complement binary integer "+twoscomplement+" is "+decimal);
  }//main()
}//class()
