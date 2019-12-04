/*
* author: Zaynab Ghazi
* program: Huffman_compressor.c
* Desc: Main driver for compressing a text file
*/
#include <stdio.h>
#include <stdlib.h>
#include "MinHeap.h"
#define ASCII_NUM 128
int main(int argc, char* argv[]){
  FILE* finput = fopen(argv[1],"rb");
  FILE* foutput = fopen(argv[2],"wb");
  //handle I/O error
  if (!finput){
    printf("File opening has failed!\n");
    return EXIT_FAILURE;
  }
  //read file and create frequency table:
  char characters[ASCII_NUM]={0};
  int frequencies[ASCII_NUM]={0};
  int free_index=0;
  char c;
  //keep track of total number of chars in file
  int file_string_size =0;
  //store characters and their corresponding frequencies
  while((c=fgetc(finput)) != EOF){
    file_string_size++;
    int found = 0;
    for(int i=0; i< free_index;i++){
      if (characters[i] == c){
        found = 1;
        frequencies[i]++;
      }
    }
   if (found == 0){
     characters[free_index]=c;
     frequencies[free_index]=1;
     free_index++;
   }
  }
  //sort characters to be in ASCII order
  // to have same encoding when decompressing for case of equal frequencies
  // see implementation of min_heap
  char characters_ordered[free_index];
  int frequencies_ordered[free_index];
  //utility:
  char ascii_chars[ASCII_NUM];
  for (int k=0; k<ASCII_NUM; k++){
    ascii_chars[k]=(char)k;
  }
  //sort characters in ASCII order with their frequencies at same indices
  int free=0;
  for(int m=0; m< ASCII_NUM; m++){
    for (int j=0; j< free_index; j++){
      if (characters[j] == ascii_chars[m]){
        characters_ordered[free]= characters[j];
        frequencies_ordered[free]= frequencies[j];
        free++;
        break;
      }
    }
  }
  //create Huffman Tree
  min_heap* heap = huffman_heap(characters_ordered,frequencies_ordered,free_index);
  ArrayList* result = generate_codes(heap);
  //write compressed file:
  //write header of compressed file containing ASCII frequencies:
  int header[ASCII_NUM];
  for(int j=0; j<ASCII_NUM;j++){
    //check if character is in FILE
    int found=0;
    for(int l=0; l<free_index;l++){
      if (characters[l]== ascii_chars[j]){
        found =1;
        header[j] = frequencies[l];
        break;

      }
    }
    if (found ==0) {
      header[j]=0;
    }
  }
  //write header
  fwrite(header,sizeof(header),1,foutput);
  //encrypt the file:
  char* entire_file = malloc(file_string_size+1);
  //each character can have max 8 bits for Huffman code:
  char* entire_encrypted = malloc(file_string_size*8+1);
  fclose(finput);
  //open input for encryption:
  FILE* input = fopen(argv[1],"r");
  fread(entire_file,sizeof(char),file_string_size,input);
  //terminate string
  entire_file[file_string_size]='\0';
  //write code for each character of file_string :
  for(int m=0; m<strlen(entire_file);m++){
    //get code from huffman table:
    for (int k=0; k< result->size; k++){
      char c = ((compression_pair*)result->list[k])->c;
      if ( entire_file[m] == c){
        if (m==0) strcpy(entire_encrypted,((compression_pair*)result->list[k])->code );
        else strcat(entire_encrypted, ((compression_pair*)result->list[k])->code);
        break;
      }
    }
  }
  //write bitwise encryption
  // entire_encrypted contains the huffman representation of the complete input text
  int count=0;
  while (count < strlen(entire_encrypted)){
    unsigned char cc=0;
    for(int i=count; i<8+count; i++){
      if (i >= strlen(entire_encrypted)) break;
      int digit = entire_encrypted[i]-'0';
      cc |= digit << (7-(i-count));
    }
    fwrite(&cc,1,1,foutput);
    count +=8;
  }
  fclose(input);
  fclose(foutput);
  fclose(finput);
}
