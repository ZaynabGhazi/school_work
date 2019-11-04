/* Name:    Zaynab Ghazi
 * File:    Airport_finder.c
 * Desc:
 * Main program for assignment 6. Finds airport data corresponding to input airport code
 * using binary search.
 */
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "ArrayList.h"
#define LINE_SIZE (CODE_SIZE+NAME_SIZE+CITY_SIZE+STATE_SIZE+COUNTRY_SIZE+1)
#define SEP ","
#define CODE_SIZE 5
#define NAME_SIZE 50
#define CITY_SIZE 50
#define STATE_SIZE 20
#define COUNTRY_SIZE 30
int rec_counter = 0;
struct airport{
  // +1 (for null terminators)
  char code[CODE_SIZE+1];
  char name[NAME_SIZE+1];
  char city[CITY_SIZE+1];
  char state[STATE_SIZE+1];
  char country[COUNTRY_SIZE+1];
};
struct airport* binary_search(ArrayList* list, char* code, int min_ind, int max_ind);

int main(int argc, char* argv[]){
  ArrayList* airports = construct(sizeof(struct airport *));
  char * filename = argv[1];
  FILE* f = fopen(filename,"r");
  if (f==NULL){
    printf("Cannot open file %s\n",filename);
    return -1;
  }
  else{
    printf("Able to open file: %s\nReading data...",filename);
  }
  char line[LINE_SIZE]={'a'};
  int l_num =0;
  while (NULL != fgets(line,sizeof(line),f)){
    //skip the first line:
    if (l_num ==0) {l_num++;continue;}
    l_num++;
    //create airport struct corresponding to current line:
    struct airport*  aport=malloc(sizeof(struct airport));
    //copy data from line to corresponding fields:
    strcpy((*aport).code,strtok(line,SEP));
    strcpy((*aport).name,strtok(NULL,SEP));
    strcpy((*aport).city ,strtok(NULL,SEP));
    strcpy((*aport).state , strtok(NULL,SEP));
    strcpy((*aport).country,strtok(NULL,"\n"));
    //add new airport* to list:
    addElement(airports,aport);
    }
    printf("done[%d airports!]\n",l_num-1);
    //user-interaction:
    char user ='Y';
    while (user == 'Y'){
      int found=0;
      //store code:
      char query[5];
      printf("\nEnter the three-letter code for an airport: ");
      scanf("%s",query);
      rec_counter=0;
      struct airport * target = binary_search(airports,query,0,airports->size-1);
      if (target != NULL ) {
        printf("\nSuccess: %s - %s, %s, %s (%s)\n",target->code, target->name, target->city, target->state, target->country);
        printf("\n Number of binary search recursions executed: %d\n",rec_counter);
      }
      else printf("\nUnsuccessful: %s is not a known airport.\n",&query[0]);

      printf("Would you like to search again (Y/N)?:");
      scanf(" %c",&user);
      }//while--end
    printf("Goodbye!\n");
}
struct airport* binary_search(ArrayList* list, char* code, int min_ind, int max_ind){
    rec_counter++;
    if (max_ind < min_ind)return NULL;
    int middle = (min_ind+max_ind)/2;
    char* code_ = ((struct airport*)list->list[middle])->code;
    //compare target and middle codes:
    if (strcmp(code_,code)==0) return  (struct airport*)list->list[middle];
    else if (strcmp(code_,code)<0) return binary_search(list,code,middle+1,max_ind);
    else return binary_search(list,code,min_ind,middle-1);
}
