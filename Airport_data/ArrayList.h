#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#ifndef COMPILED
typedef struct{
  int size;
  int capacity;
  //underlying generic array
  void** list;
  int unit_size;
}ArrayList;
ArrayList* construct(int unit_size);
void addElement(ArrayList* arr, void* element);
#define COMPILED
#endif
