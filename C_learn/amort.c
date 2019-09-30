/* Author: Zaynab Ghazi
 * Program: amort.c
 * This program prints an amortization table of a loan
 * provided important variables from the user.
 */
#include <stdio.h>
int main(void){
  //initialize three variables to respectively store loan amount, annual
  //interest rate and monthly payment
  float loan_amount, a_IR, m_payment;
  printf("Enter the loan amount: ");
  scanf("%f", &loan_amount);
  printf("Enter the annual interest rate: ");
  scanf(" %f", &a_IR);
  printf("Enter the monthly payment amount: ") ;
  scanf(" %f", &m_payment);
  float balance = loan_amount;
    //set initial conditions for user input
  if (loan_amount>=0 && m_payment>=0 && a_IR >= 0){
      printf(" pmnt   pmnt    Int Balance\n");
      printf("  Num    amt  Accru  Remain\n");
      printf("----- ------  ----- -------\n");
      int pmnt_num=0;
      //initialize variables for monthly payment amount and monthly interest
      float  pmnt_amt=0.00, int_acc=0.00;
      //set a marker for last payment *end*
      int end = 0;
      while (balance >=0 && end==0){
          printf("%5d %6.2f  %5.2f %7.2f\n",pmnt_num,pmnt_amt,int_acc,balance);
          pmnt_num++;
          pmnt_amt=m_payment;
          int_acc=balance*a_IR/1200;
          //identify the last payment
          if (balance+int_acc-m_payment< 0) {
              //compute last monthly payment
              pmnt_amt=balance+int_acc-0;
              balance=0;
              //mark last payment
              end =1;
              printf("%5d %6.2f  %5.2f %7.2f\n ",pmnt_num,pmnt_amt,int_acc,balance);
            }
          //identify regular payments
          else
              balance+=(int_acc - m_payment);
            }//end-while
        }//end-if
    //if user-input doesn't match preconditions
    else {
        printf("Values inserted are invalid.\n");
        }
}//end-main()
