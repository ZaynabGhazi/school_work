/* Name:    Zaynab Ghazi
 * File:    CypherP.c
 * Desc:
 *
 * This program encrypts/decrypts input using the Caesae cypher
 * method and a code book, using pointer arithmetic.
 *
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#define SIZE 1000
//correct C's std % operation for negative numbers
#define MODULO(X,Y) ((X%Y+Y)%Y)

void encryptP(char* inputFile, int bookSize, char* book, int begin_offset);
void decryptP(char* inputFile, int bookSize, char* book, int begin_offset);

int main(int argc, char *argv[]){
  if (argc < 5){
    printf("You need five arguments");
    return 1;
  }
  int task = atoi(argv[1]);
  char * input = argv[2];
  int begin_offset = atoi(argv[4]);
  //read book and store its characters in an array
  FILE *bk = fopen(argv[3],"r");
  char book[SIZE]={'a'};
  //create pointer to array
  char*  bookP = book;
  int i=0;
  char c;
  do{
    c=fgetc(bk);
    if (feof(bk)) break;
    if (isalpha(c)){
      c=toupper(c);
      //store elements in the array using pointer assignment
      *(book+i)=c;
      i++;
      }
  } while (!feof(bk) && i< SIZE);
  fclose(bk);
  //at this point, the array had i-1+1 elements => size of arr = i
  if (task == 1) {
    encryptP(input,i,bookP,begin_offset);
  }
  else decryptP(input,i,bookP,begin_offset);
  }
  /*
  * encrypts text and prints result to console
  * @param pointer to input file
  * @param size of book used to encode
  * @param pointer to array of book characters
  * @param the starting index from the book
  * @return void
  */
  void encryptP(char* inputFile, int bookSize, char* book, int begin_offset){
    FILE *ip = fopen(inputFile,"r");
    char c;
    int parseIndex = begin_offset-1;
    do{
      c=fgetc(ip);
      if (feof(ip)) break;
      if (isalpha(c)){
          char parseChar = *(book+parseIndex);
          c= ((toupper(c)-'A')+(parseChar-'A'))%26+'A';
          parseIndex = (parseIndex+1)%bookSize;
      }
      printf("%c",c) ;
    } while ( !feof(ip));
    fclose(ip);
  }
  /*
  * decrypts text and prints result to console
  * @param pointer to input file
  * @param size of book used to encode
  * @param pointer to array of book characters
  * @param the starting index from the book
  * @return void
  */
  void decryptP(char* inputFile, int bookSize, char* book, int begin_offset){
    FILE *ip = fopen(inputFile,"r");
    char c;
    int parseIndex = begin_offset-1;
    do{
      c=fgetc(ip);
      if (feof(ip)) break;
      if (isalpha(c)){
          char parseChar = *(book+parseIndex);
          c= MODULO((c-'A')-(parseChar-'A'),26)+'A';
          parseIndex = (parseIndex+1)%bookSize;
      }
      printf("%c",c) ;
    } while ( !feof(ip));
    fclose(ip);
  }
