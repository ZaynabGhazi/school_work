/* Name:    Zaynab Ghazi
 * File:    music.c
 * Desc:
 * Useful methods to perform on song struct
 */

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "music.h"

//print a song:
void print_song(struct song * c_song){
  printf("%s ALB:%s\tART:%s\tLEN: %s\n",c_song->year,c_song->release,c_song->artist,c_song->duration);
}
//comparator functions based on song's:
//title
int comp_1(const void* p,const void* q){
  struct song * song1 = *(struct song **)p;
  struct song * song2 = *(struct song **)q;
  return strcmp(song1->title,song2->title);
}
//artist
int comp_2(const void* p,const void* q){
  struct song * song1 = *(struct song **)p;
  struct song * song2 = *(struct song **)q;
  return strcmp(song1->artist,song2->artist);
}
//duration
int comp_3(const void* p,const void* q){
  struct song * song1 = *(struct song **)p;
  struct song * song2 = *(struct song **)q;
  return strcmp(song1->duration,song2->duration);
}
//release album
int comp_4(const void* p,const void* q){
  struct song * song1 = *(struct song **)p;
  struct song * song2 = *(struct song **)q;
  return strcmp(song1->release,song2->release);
}
//start of fade-out
int comp_5(const void* p,const void* q){
  struct song * song1 = *(struct song **)p;
  struct song * song2 = *(struct song **)q;
  return strcmp(song1->fade_out_t0,song2->fade_out_t0);
}
//tempo
int comp_6(const void* p,const void* q){
  struct song * song1 = *(struct song **)p;
  struct song * song2 = *(struct song **)q;
  return strcmp(song1->tempo,song2->tempo);
}
//terms
int comp_7(const void* p,const void* q){
  struct song * song1 = *(struct song **)p;
  struct song * song2 = *(struct song **)q;
  return strcmp(song1->terms,song2->terms);
}
//year of release
int comp_8(const void* p,const void* q){
  struct song * song1 = *(struct song **)p;
  struct song * song2 = *(struct song **)q;
  return strcmp(song1->year,song2->year);
}
//binary search function
//takes a comparator as a parameter from set {compi_i/i in [1,8]}
struct song* binary_search(ArrayList* list, struct song * target, int min_ind, int max_ind, int (*comp )(const void*,const void*)){
    if (max_ind < min_ind)return NULL;
    int middle = (min_ind+max_ind)/2;
    struct song * mid_el= list->list[middle];
    if (comp(&mid_el,&target)==0) return mid_el;
    else if (comp(&mid_el,&target)<0) return binary_search(list,target,middle+1,max_ind,comp);
    else return binary_search(list,target,min_ind,middle-1,comp);
}
//copies string by dynamic allocation to fields of struct 
char* string_copy(char* original){
  char* newest = malloc((strlen(original)+1)*sizeof(char));
  strcpy(newest,original);
  return newest;
}
