/*
* author: Zaynab Ghazi
* program: header for MinHeap.c
*/
#include <stdlib.h>
#include <stdio.h>
#include "ArrayList.h"
// define Huffman Tree Node
typedef struct huff_tree_node{
  int ch;
  int freq;
  struct huff_tree_node* left;
  struct huff_tree_node* right;
}huff_tree_node;
// define Huffman tree/heap
typedef struct min_heap{
  int size;
  int capacity;
  huff_tree_node** list;
}min_heap;
//methods:
huff_tree_node* create_node(char c, int freq);
min_heap* create_heap(int cap);
void swap_nodes(huff_tree_node** a, huff_tree_node** b);
void heap_sort (min_heap* heap, int index);
void heap_insert(min_heap* heap, huff_tree_node* node);
void make_min_heap(min_heap* heap);
huff_tree_node* get_min(min_heap* heap);
min_heap* huffman_heap(char* characters, int* frequencies, int size);
//useful struct:
typedef struct compression_pair{
  char c;
  char* code;
}compression_pair;
//more methods:
ArrayList* generate_codes(min_heap* heap);
void store_codes(huff_tree_node* root, char arr[], ArrayList* list, int indx);
