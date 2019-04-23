//File         : HiLo.java
// Written by : Zaynab Ghazi
// Date       : 11/11/2018
//Purpose     : Simulates the game of HiLo. In this game, one player selects a secret number between 1 and 1000 and the other player has to guess it in 20 or fewer tries. The user and computer will compete against each other in this model.
// Usage      : java HiLo

public class HiLo{
  //Create a function that generates a random number in [a,b]
  public static int RandInt(int a, int b){
    return a+(int)(Math.random()*(b-a+1));
  }//RandInt()
  //Create a function that simulates the HiLo game where the user is the guesser and returns the number of tries used by the user
  public static int YouGuess(int n){
    StdOut.println("The HiLo Game\n\n");
    StdOut.println("First, I will pick a number between 1 and 1000.\nYou will have a maximum of 20 tries to guess the number.\n");
    int tries=0;
    boolean solved= false ;
    StdOut.print("Press ENTER when ready.");
    StdIn.readChar();

    for(int i=0 ; i< 20 ; i++){
      StdOut.print("Enter a guess: ");
      int guess= StdIn.readInt();
      tries++;
      if (guess<n) StdOut.println("Your guess is low.");
      else if (guess>n) StdOut.println("Your guess is high.");
      else {
        StdOut.print("You got it!\nIt took you "+ tries+" tries.\n");
        solved = true;
        break;
       }
     }
     if (!solved ) StdOut.print("Sorry, you ran out of tries. Only 20 tries are allowed.\nThe number I was thinking of was "+n+" .");
     return tries;
   }//YouGuess()
   //Create a function that simulates the HiLo game where the computer is the guesser and returns the number of tries used by the computer
   public static int IGuess(){
     StdOut.print("\n\n\n");
     StdOut.println("Ok, my turn to guess.\nThink of a number between 1 and 1000.\nWhen ready, press ENTER.");
     int t=0;
     int a=1 , b=1000;
     StdIn.readChar();
     StdIn.readChar();
     while(t<=20){
       int guess= RandInt(a,b);
       t++;
       StdOut.println("My guess is "+guess+".");
       StdOut.print("Enter -1 (Lo), 0 (Got it), or 1 (Hi): ");
       double answer=StdIn.readDouble();
       //Change the size of the range of guesses depending on the user's feedback
       if(answer == -1) a=guess+1 ;
       else if (answer == 1) b=guess-1;
       else {
        StdOut.print("Yay! I got it!\nIt took me " + t + " tries.\n");
        break;
       }
    }

   return t;
  }//IGuess()

   public static void main(String[] args){
     int n = RandInt(1,1000);
     // store the number of tries used by both the user (a) and the computer (b)
     int a=YouGuess(n),b=IGuess();
     StdOut.print("\n\n\n");
     StdOut.print("It took you "+a+" tries to guess my number.\nIt took me "+b + " tries to guess your number.\n");
     if( a < b) StdOut.print("Therefore, You win!\n-----");
     else if(a>b) StdOut.println("Therefore, I win!\n-----");
     else StdOut.println("Therefore, it's a tie!");

   }//main()
}// class HiLo
