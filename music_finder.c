/* Name:    Zaynab Ghazi
 * File:    music_finder.c
 * Desc:
 * Main driver for a7.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "ArrayList.h"
#include "music.h"
#define LINE_SIZE 4096
#define SEP ","
#define USER 40


int main(int argc, char* argv[]){
  ArrayList* songs = construct(sizeof(struct song *));
  char* filename = argv[1];
  FILE* f = fopen(filename,"r");
  if (f==NULL){
    printf("Cannot open file %s\n",filename);
    return -1;
  }
  char line[LINE_SIZE]={'a'};
  while(NULL!= fgets(line,sizeof(line),f)){
    //create a song struct and fill corresponding fields:
    struct song* c_song= malloc(sizeof(struct song));
    char* stitle = strtok(line,SEP);
    c_song->title=string_copy(stitle);
    char* sartist = strtok(NULL,SEP);
    c_song->artist=string_copy(sartist);
    char* sdur = strtok(NULL,SEP);
    c_song->duration=string_copy(sdur);
    char* srelease = strtok(NULL,SEP);
    c_song->release=string_copy(srelease);
    char* sfade = strtok(NULL,SEP);
    c_song->fade_out_t0 = string_copy(sfade);
    char* stempo=strtok(NULL,SEP);
    c_song->tempo=string_copy(stempo);
    char* sterms = strtok(NULL,SEP);
    c_song->terms=string_copy(sterms);
    char* syear = strtok(NULL,"\n");
    c_song->year=string_copy(syear);
    //add struct to list:
    addElement(songs,c_song);
  }
  fclose(f);
  //user-interaction:
  int user =1;
  while(user!=99){
    printf("Sort by:  1: Title\n       :  2: Artist\n       :  3: Duration\n       :  4: Album\n       :  5: Fade Start\n       :  6: Tempo\n       :  7: Terms\n       :  8: Year\n         99 -- quit\n");
    scanf("%d",&user);
    //sort list of songs:
    switch(user){
      case 1:
            qsort(songs->list,songs->size,sizeof(struct song*),comp_1);
            break;
      case 2:
            qsort(songs->list,songs->size,sizeof(struct song*),comp_2);
            break;
      case 3:
            qsort(songs->list,songs->size,sizeof(struct song*),comp_3);
            break;
      case 4:
             qsort(songs->list,songs->size,sizeof(struct song*),comp_4);
             break;
      case 5:
            qsort(songs->list,songs->size,sizeof(struct song*),comp_5);
            break;
      case 6:
            qsort(songs->list,songs->size,sizeof(struct song*),comp_6);
             break;
      case 7:
            qsort(songs->list,songs->size,sizeof(struct song*),comp_7);
            break;
      case 8:
            qsort(songs->list,songs->size,sizeof(struct song*),comp_8);
            break;
      default:
            return(1);
            break;
    }
    printf("How many items do you want to see?\n\t<0 show all:\n\t==0 search for a particular record:\n");
    int song_num;
    scanf("%d",&song_num);
    if (song_num < 0 ) {
      for(int i=0; i<songs->size;i++) print_song(songs->list[i]);
    }
    else if (song_num == 0){
      //binary_search on previously sorted list:
      printf("Enter search query:\n");
      char query[USER];
      scanf("%s",&query[0]);
      //switch to chose comparator function:
      switch(user){
        case 1:{
              struct song* query_song = malloc(sizeof(struct song));
              //create new struct because of the signature of the comparators:
              query_song->title=string_copy(&query[0]);
              struct song* found = binary_search(songs,query_song,0,songs->size-1,comp_1);
              if (NULL != found )print_song(found);
              else printf("Match not found! \n");
              free(query_song);
              free(found);
              break;
            }
        case 2:{

              struct song* query_song = malloc(sizeof(struct song));
              query_song->artist = string_copy(query);
              struct song* found = binary_search(songs,query_song,0,songs->size-1,comp_2);
              if (NULL != found )print_song(found);
              else printf("Match not found! \n");
              free(query_song);
              free(found);
              break;
            }
        case 3:{
              struct song* query_song = malloc(sizeof(struct song));
              query_song->duration = string_copy(query);
              struct song* found = binary_search(songs,query_song,0,songs->size-1,comp_3);
              if (NULL != found )print_song(found);
              else printf("Match not found! \n");
              free(query_song);
              free(found);
              break;
            }
        case 4:{
              struct song* query_song = malloc(sizeof(struct song));
              query_song->release = string_copy(query);
              struct song* found = binary_search(songs,query_song,0,songs->size-1,comp_4);
              if (NULL != found )print_song(found);
              else printf("Match not found! \n");
              free(query_song);
              free(found);
              break;
            }
        case 5:{
              struct song* query_song = malloc(sizeof(struct song));
              query_song->fade_out_t0 = string_copy(query);
              struct song* found = binary_search(songs,query_song,0,songs->size-1,comp_5);
              if (NULL != found )print_song(found);
              else printf("Match not found! \n");
              free(query_song);
              free(found);
              break;
            }
        case 6:{
              struct song* query_song = malloc(sizeof(struct song));
              query_song->tempo = string_copy(query);
              struct song* found = binary_search(songs,query_song,0,songs->size-1,comp_6);
              if (NULL != found )print_song(found);
              else printf("Match not found! \n");
              free(query_song);
              free(found);
              break;
            }
        case 7:{
              struct song* query_song = malloc(sizeof(struct song));
              query_song->terms = string_copy(query);
              struct song* found = binary_search(songs,query_song,0,songs->size-1,comp_7);
              if (NULL != found )print_song(found);
              else printf("Match not found! \n");
              free(query_song);
              free(found);
              break;
            }
        case 8:{
              struct song* query_song = malloc(sizeof(struct song));
              query_song->year = string_copy(query);
              struct song* found = binary_search(songs,query_song,0,songs->size-1,comp_8);
              if (NULL != found )print_song(found);
              else printf("Match not found! \n");
              free(query_song);
              free(found);
              break;
            }
          }//switch-end
        }//binary_search
    else{
      //get top song_num songs corresponding to user's preferences:
      for (int i=0; i< song_num;i++) print_song(songs->list[i]);
    }
  }//while
  free(songs);
}
