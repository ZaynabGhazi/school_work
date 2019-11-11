/* Name:    Zaynab Ghazi
 * File:    ArrayList.c
 * Desc:
 * Simplistic/incomplete implementation of an ArrayList (serves our purposes)
 */
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "ArrayList.h"
#define GOLDEN_RATIO 1.62

//equivalent of java-constructor() to init. ArrayList struct
ArrayList* construct(int unit_size){
  ArrayList * listp = malloc(sizeof(ArrayList));
  listp->unit_size=unit_size;
  listp->capacity = 100;
  listp->size=0;
  if (listp != NULL){
    listp->list = calloc(listp->capacity,unit_size);
    if (listp->list == NULL){
      printf("Unable to create arrayList.\n");
      free (listp);
      return NULL;
    }
    listp->list[0]=NULL;
  }
  return listp;
}
//add element to list:
void addElement(ArrayList* arr, void* element){
  //grow list if full
  if (arr->size >= arr->capacity){
    void** newest = realloc(arr->list,(arr->capacity)*GOLDEN_RATIO*(arr->unit_size));

    if (newest != NULL){
      newest[arr->size]=malloc(sizeof(element));
      newest[arr->size]= element;
      arr->list = newest;
      arr->capacity*=GOLDEN_RATIO;
      arr->size++;
    }
  }
  else{
    //if there is still space:
    arr->list[arr->size]=malloc(sizeof(element));
    arr->list[arr->size]=element;
    arr->size++;
  }
}
