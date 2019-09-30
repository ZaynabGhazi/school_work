/**
* @author: Zaynab
* file : ToTwosComplement.java
* description: this program takes a decimal from standard input
* and converts it to Two's complement with a constant number of bits
* that can be changed through the cst variable NUMBER_OF_BITS
*/
import java.util.*;
import java.io.*;
public class ToTwosComplement{
  //specify the number of bits used in the conversion to 2's complement
  private static final int NUMBER_OF_BITS =8;
  public static void main(String[] args){
    int decimal = Integer.parseInt(args[0]);
    int copy_dec = decimal;
    //store a static copy of the input number
    String binary="";
    //operate on magnitude of input number
    decimal = Math.abs(decimal);
    //convert to decimal by powers of 2 computations: the resulting binary is reversed
    while(decimal != 0 && binary.length() < NUMBER_OF_BITS){
      if (decimal%2 == 0){
        binary+="0";
        decimal /=2;
      }
      else {
        binary+="1";
        decimal= (decimal-1)/2;
      }
    }
    //add 0s until number of bits used to encode is reached
    while (binary.length() < NUMBER_OF_BITS){
      binary+="0";
    }
    //check whether number can be converted using the number of bits specified above:
    if (decimal != 0 ) {
      System.out.println("The two's complement integer representation of the decimal value "+copy_dec+" cannot be expressed with "+NUMBER_OF_BITS+" binary digits.");
      return;
    }
    //check whether positive number cannot be represented using number of bits specified
    if ( binary.charAt(binary.length()-1)=='1' && copy_dec >= 0 ){
      System.out.println("The two's complement integer representation of the decimal value "+copy_dec+" cannot be expressed with "+NUMBER_OF_BITS+" binary digits.");
      return;
    }
    // tc = the string storing the 2's complement representation
    //binary is the reversed tc
    String tc="";
    for(int i=binary.length()-1; i>=0;i--)
      tc+=String.valueOf(binary.charAt(i));
    //check whether number was positive
    if (copy_dec >0 && decimal==0) System.out.println("The two's complement binary integer representation of the decimal value "+copy_dec+ " is "+ tc);
    else{
      //the number is negative
      //flip all bits to get its complement :
      String complement ="";
      for (int i=0; i<tc.length() ;i++){
        if (tc.charAt(i)=='0') complement +='1';
        else complement+='0';
      }
      //add 1 to complement
      //carried indicates whether the 1-carry-out was cleared
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
    //check whether negative number can be represented using number of bits specified:
    if (complement_arr[0]=='0' && copy_dec <0 ){
      System.out.println("The two's complement integer representation of the decimal value "+copy_dec+" cannot be expressed with "+NUMBER_OF_BITS+" binary digits.");
      return;
    }
    System.out.println("The two's complement binary integer representation of the decimal value "+copy_dec+ " is "+ String.valueOf(complement_arr));
  }
 }//main())
}
