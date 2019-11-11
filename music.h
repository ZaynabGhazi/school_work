/* Name:    Zaynab Ghazi
 * File:    music.h
 * Desc:
 * Header corresponding to "music.c"
 */
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "ArrayList.h"
//create s ong struct:
struct song{
  char* title;
  char* artist;
  char* duration;
  char* release;
  char* fade_out_t0;
  char* tempo;
  char* terms;
  char* year;
};
//signatures of fucntions in c_file:
void print_song(struct song * c_song);
int comp_1(const void* p,const void* q);
int comp_2(const void* p,const void* q);
int comp_3(const void* p,const void* q);
int comp_4(const void* p,const void* q);
int comp_5(const void* p,const void* q);
int comp_6(const void* p,const void* q);
int comp_7(const void* p,const void* q);
int comp_8(const void* p,const void* q);
struct song* binary_search(ArrayList* list, struct song * target, int min_ind, int max_ind, int (*comp )(const void*,const void*));
char* string_copy(char* original);
