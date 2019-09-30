/**
 * Name: Zaynab Ghazi
 * File: timedQuickSort.c
 * Desc: This program times quicksort on a variable-sized array and returns
 * relevant statistical data for 30 trials.
 *
 */
#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#define TRIALS 30
//computer minimum of two numbers
#define MIN(a,b) ((a)<(b)? (a):(b))
//compute maximum of two numbers
#define MAX(a,b) ((a)>(b)? (a):(b))
//compute median of two numbers
#define MEDIAN(a,b,c) ((MIN(a,b)==a)?(MIN(b,c)==b?b:MAX(a,c)) : (MIN(b,c)==b?MIN(a,c):b))
//size cutoff not to use quicksort in order to shorten runtime
#define CUTOFF 25
//signatures of functions below main()
void quicksort(int a[], int low, int high);
int split(int a[], int low, int high);
void insertionSort(int a[], int low, int high);
int main(int argc, char* argv[]){
  //store statistical variables:
  double mean=0;
  double stddev=0;
  double trials[TRIALS]={0};
  //generate N=size random numbers
  srand(time(NULL));
  for(int j=0; j< TRIALS; j++){
    //initialize CPU clock
    clock_t start, end;
    start = clock();
    int size = atoi(argv[1]);
    //avoid variable-sized object error
    int arr[size];
    memset(arr,0,size);
    // set the range of the random numbers to [0;1000,000]
    for (int i=0; i< size; i++){
      arr[i]= rand()%1000001;
    }
    printf("Unsorted array: [");
    for (int i=0; i< size-1; i++) printf("%d, ",arr[i]);
    printf("%d ]\n",arr[size-1]);
    printf("Sorted array: [");
    quicksort(arr,0,size-1);
    for (int i=0; i< size-1; i++) printf("%d, ",arr[i]);
    printf("%d ]\n",arr[size-1]);
    end = clock();
    float cpu_time_used = ((float) (end - start)) / CLOCKS_PER_SEC;
    printf("T: %10.6f\n", cpu_time_used );
    trials[j]=cpu_time_used;
  }//end-of-trials
  //compute statistical results:
  //compute mean
  int k=0;
  while (k< TRIALS){
    mean += trials[k];
    k++;
  }
  mean = mean/TRIALS;
  printf("The mean of the number of runtimes is %f\n",mean);
  //compute std deviation
  int f=0;
  while (f<TRIALS){
    stddev += pow(trials[f]-mean,2);
    f++;
  }
  stddev = sqrt(stddev/TRIALS);
  printf("The standard deviation of the number of runtimes is %f\n",stddev);
}
//Book implementation of quisort using 2 improvements:
// 1. Median-of-Three partitioning
// 2. size cutoff for arrays <= 25 elements
void quicksort(int a[], int low, int high){
    int size = high - low +1;
    //IMPROV 2: insertionSort is faster for smaller arrays
    if (size <= CUTOFF) {
        insertionSort(a, low,high);
        return;
    }
  int middle;
  if (low >= high) return;
  middle = split(a, low, high);
  quicksort(a, low, middle-1);
  quicksort(a, middle+1, high);
}
int split(int a[], int low, int high){
    //IMPROV 1: Use median of three as pivot and store it in a[low
 int part_element = MEDIAN(a[low],a[high],a[(low+high)/2]);
  //find index of median
  if (part_element == a[high]){
    //swap low and high
    int temp = a[low];
    a[low] = a[high];
    a[high] = temp;
  }
  if (part_element == a[(low+high)/2]){
    int temp = a[low];
    a[low] = a[(low+high)/2];
    a[(low+high)/2]=temp;
  }
  part_element = a[low];
  for(;;){
    while(low < high && part_element <= a[high]) high--;
    if (low >= high) break;
    a[low++]=a[high];
    while( low < high && a[low] <= part_element) low++;
    if (low>= high) break;
    a[high--]=a[low];
  }
  a[high]=part_element;
  return high;
}
//insertionSort implementation for small arrays
void insertionSort(int arr[], int low, int high){
   int i, key, j;
    for (i = low+1; i < high+1; i++) {
        key = arr[i];
        j = i - 1;
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j = j - 1;
        }
        arr[j + 1] = key;
   }
}
