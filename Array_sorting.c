/**
* Name: Zaynab Ghazi
* File: Array_sorting.c
* Desc: program determines whether an  array is sorted
*/
#include <stdio.h>
int sorted(int n, int arr[n]){
  int i;
  //if array is empty or has 1 element:
  if (n == 0 || n==1 )return 1;
  for(i=0; i< n-1; i++){
    //check for descending order
    //wrong order found in a pair
    if (arr[i] > arr[i+1]) return 0;
  }
  return 1;
}
int main(void){
  //1st part:
  //initialize desired array
  // use of for loop not needed for ten element
  int arr[10]={0,1,2,3,4,5,6,7,8,9};
  int i;
  //2nd part :
  //make 5th element of array equal to -9
  //For Part2, un-comment line below:
  //*(arr+4)=-19;
  //test whether new array is sorted:
  for(i=0; i< 10;i++) printf("%d ",arr[i]);
  if (sorted(10,arr) == 0) printf(" unsorted.\n");
  else printf(" sorted.\n");
}
