/**
* Name: Zaynab Ghazi
* File: RandomWalks.c
* Desc: program simulates random walks and prints to console statistical results
*/
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include <limits.h>
//N stores the dimension of the grid
#define N 10
#define UP 0
#define DOWN 1
#define RIGHT 2
#define LEFT 3
//TRIALS stores the # of trials on the grid walk experiment
#define TRIALS 100
//START stores the initial position ( either 5 or 10 )
#define START 5

int main(void){
  int trial_index=0;
  //leg_moves stores the number of legal moves possible in each trial
  int leg_moves[TRIALS]={0};
  //set seed for random generator to get different sequences for the trials
  srand(time(NULL));
  while(trial_index < TRIALS){
      int grid[N][N]={0};
      //start at position (START,START) or (10,10)
      grid[START][START]=1;
      //walked stores number of cells already visited from the grid
      int walked =1;
      //notice number of moves = walked -1 (the first position is not counted)
      //starting at (START,START) = (5,5) or (10,10)
      int row=START,col=START;
      //print the first position
      printf("s [%d,%d]\n",row,col);
      //set preconditions for legal moves
      while (walked <= (N*N) && ((col -1 >=0 && grid[row][col-1] != 1) || (col+1 < N && grid[row][col+1] !=1) || (row+1 < N && grid[row+1][col] !=1) || (row-1 >=0 && grid[row-1][col] !=1))){
        //there are 4 possibilities : UP - DOWN - RIGHT -LEFT for the moves
        int move = rand()%4;
        switch(move){
          case LEFT:
          if (col -1 >=0 && grid[row][col-1] != 1){
            col--;
            //setting a cell to 1 means it has been visited
            grid[row][col]=1;
            walked++;
            printf("%d [%d,%d]\n",walked-1,row,col);
          }
          break;
          case RIGHT:
          if(col+1 < N && grid[row][col+1] !=1){
            col++;
            grid[row][col]=1;
            walked++;
            printf("%d [%d,%d]\n",walked-1,row,col);
          }
          break;
          case DOWN:
          if(row+1 < N && grid[row+1][col] !=1){
            row++;
            grid[row][col]=1;
            walked++;
            printf("%d [%d,%d]\n",walked-1,row,col);
          }
          break;
          case UP:
          if(row-1 >=0 && grid[row-1][col] !=1){
            row--;
            grid[row][col]=1;
            walked++;
            printf("%d [%d,%d]\n",walked-1,row,col);
          }
          break;
        }//end-switch
      }//end of 1 trial
      printf("The number of legal moves is  %d\n",walked-1);
      //store data for statistics:
      //notice the first move is not counted (the starting position)
      //the # of legal moves in this trial is:
      leg_moves[trial_index]=walked-1;
      trial_index++;
    }//end of trials
    //compute the mean
    int size_arr = sizeof(leg_moves)/sizeof(leg_moves[0]);
    double mean=0;
    int i=0;
    while (i< size_arr){
      mean += leg_moves[i];
      i++;
    }
    mean = mean/size_arr;
    printf("The mean of the number of legal moves is %f\n",mean);
    //compute std deviation
    double stddev =0;
    int j=0;
    while (j<size_arr){
      stddev += pow(leg_moves[j]-mean,2);
      j++;
    }
    stddev = sqrt(stddev/size_arr);
    printf("The standard deviation of the number of legal moves is %f\n",stddev);
  }
