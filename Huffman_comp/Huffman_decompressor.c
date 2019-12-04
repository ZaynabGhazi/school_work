/*
* author: Zaynab Ghazi
* program: Huffman_decompressor.c
* Desc: Main driver for decompressing a text file
*/
#include <stdio.h>
#include <stdlib.h>
#include "MinHeap.h"
#define ASCII_NUM 128


int main(int argc, char* argv[]){
  //keep track of number of characters in original text
  int num_chars=0;
  //I/O from cmmd line:
  FILE* finput = fopen(argv[1],"rb");
  FILE* foutput = fopen(argv[2],"wb");
  if (!finput){
    printf("File opening has failed!\n");
    return EXIT_FAILURE;
  }
  //read header of compressed file:
  int frequencies[ASCII_NUM]={0};
  fread(frequencies,sizeof(frequencies),1,finput);
  //establish char Array utility:
  char ascii_chars[ASCII_NUM];
  for (int k=0; k<ASCII_NUM; k++){
    ascii_chars[k]=(char)k;
  }
  //get characters and frequencies for Huffman Tree
  int count=0;
  for( int k=0; k< ASCII_NUM;k++){
    if (frequencies[k] != 0){
      count++;
    }
  }
  int huff_freqs[count];
  char huff_chars[count];
  int idx=0;
  for(int j=0; j< ASCII_NUM;j++){
    if (frequencies[j] != 0){
      huff_freqs[idx]=frequencies[j];
      huff_chars[idx]= (char)j;
      idx++;
    }
  }
  //compute number of characters from header data => be able to ignore padding bits from compression
  for(int i=0; i< count; i++) num_chars+= huff_freqs[i];
  //re-create compression Huffman tree:
  min_heap* heap = huffman_heap(huff_chars,huff_freqs,count);
  //read text and decode it:
  int string_length=0;
  ArrayList* encoding = construct(sizeof(char*));
  while (!feof(finput)){
    unsigned char read;
    fread(&read,1,1,finput);
    //store bits of read character:
    char* binaries = malloc(9);
    for(int i=7; i>=0 ; i--){
      int digit = read >>i & 0x01;
      binaries[7-i]= (digit ==0)?'0':'1';
    }
    binaries[8]='\0';
    //encoding will have the complete binary representation of the text as elements in its field list:
    addElement(encoding,binaries);
  }
  //concatenate all binary data to get complete text in binary:
  for(int i=0; i< encoding->size; i++){
    string_length += strlen((char*) encoding->list[i]);
  }
  char* code = malloc(string_length+1);
  for(int i=0; i< encoding->size; i++){
    if (i==0) strcpy(code, ((char*) encoding->list[i]));
    else strcat(code, ((char*) encoding->list[i]));
  }
  //decode through tree-traversal:
  huff_tree_node* current = heap->list[0];
  int char_count=0;
  for(int j=0; j< strlen(code) ;j++){
    if (char_count == num_chars) break; // ignore padding bits
    if (code[j]=='0') current= current->left;
    else current = current->right;
    //reached a leaf:
    if (current->left == NULL && current->right == NULL){
      fputc(current->ch,foutput);
      current = heap->list[0];
      char_count++;
    }
  }
  fclose(foutput);
  fclose(finput);
  free(encoding);
  free(code);
  free(heap);
}
