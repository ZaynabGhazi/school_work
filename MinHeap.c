/*
* author: Zaynab Ghazi
* program: MinHeap.c
* Desc: implenetation of a min-heap with huffman functionality
*/
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "MinHeap.h"

int size_chars =0;
//method to create a node:
huff_tree_node* create_node(char c, int freq){
  huff_tree_node* newest = malloc(sizeof(huff_tree_node));
  newest->ch = c;
  newest->freq=freq;
  newest->left = newest->right = NULL;
  return newest;
}
//method to create a heap:
min_heap* create_heap(int cap){
  min_heap* newest = malloc(sizeof(min_heap));
  newest->size = 0;
  newest->capacity = cap;
  newest->list = malloc(cap*sizeof(huff_tree_node*));
  return newest;
}
//swap nodes utility:
void swap_nodes(huff_tree_node** a, huff_tree_node** b){
  huff_tree_node* temp = *a;
  *a = *b;
  *b =  temp;
}
//maintain min-heap sort:
void heap_sort (min_heap* heap, int index){
  int min = index;
  int left = 2*index +1;
  int right = 2*index +2;
  //to handle order issues, list is sorted in ASCII ( see compression src)
  if (left < heap->size && ((heap->list[left]->freq < heap->list[min]->freq) || (heap->list[left]->freq == heap->list[min]->freq && heap->list[left]->ch < heap->list[min]->ch ))){
    min = left;
  }
  if (right< heap->size && ((heap->list[right]->freq < heap->list[min]->freq) || (heap->list[right]->freq == heap->list[min]->freq && heap->list[right]->ch < heap->list[min]->ch ))){
    min = right;
  }
  if (min != index) {
    swap_nodes(&heap->list[index],&heap->list[min]);
    heap_sort(heap,min);
  }
}
//add_element to heap:
void heap_insert(min_heap* heap, huff_tree_node* node){
  if (heap->size < heap->capacity-1){
    heap->size +=1;
    int free_idx=heap->size - 1;
    heap->list[free_idx]=node;
    while (free_idx!=0 && heap->list[(free_idx-1)/2]->freq  > heap->list[free_idx]->freq){
      //restore heap order:
      swap_nodes(&heap->list[(free_idx-1)/2],&heap->list[free_idx]);
      free_idx= (free_idx-1)/2;
    }
  }
}
//re-establish min-heap order:
void make_min_heap(min_heap* heap){
  int last = heap->size-1;
  for (int i= (last-1)/2; i>=0; i--){
    heap_sort(heap,i);
  }
}
//extract top of heap:
huff_tree_node* get_min(min_heap* heap){
  huff_tree_node* min = heap->list[0];
  heap->list[0]=heap->list[heap->size - 1];
  heap->size --;
  heap_sort(heap,0);
  return min;
}
//create Huffman-heap:
min_heap* huffman_heap(char* characters, int* frequencies, int size){
  size_chars=size;
  min_heap* heap = create_heap(size);
  heap->size = size;
  for (int i=0; i< size; i++){
    heap->list[i] = create_node(characters[i],frequencies[i]);
  }
  //correct order:
  make_min_heap(heap);
  //start huffman algorithm implementation:
  huff_tree_node* left;
  huff_tree_node* right;
  huff_tree_node* result;
  //name is useless => set to meaningless character:
  char name ='$';
  while(heap->size != 1){
    left = get_min(heap);
    right = get_min(heap);
    result = create_node(name,left->freq+right->freq);
    result->left = left;
    result->right = right;
    heap_insert(heap,result);
  }
  return heap;
  }
    //get the codes corresponding to each character from traversing tree:
ArrayList* generate_codes(min_heap* heap){
    ArrayList* codes = construct(sizeof(compression_pair*));
    //max length of binary tree is N/2
    char arr[size_chars/2];
    store_codes(get_min(heap),arr,codes,0);
    return codes;
  }
  //helper-function for code-generation:
void store_codes(huff_tree_node* root, char arr[], ArrayList* list, int idx){
    if (root->left){
      arr[idx]='0';
      store_codes(root->left,arr,list,idx+1);
    }
    if (root->right){
      arr[idx]='1';
      store_codes(root->right,arr,list,idx+1);
    }
    //reached node:
    if (root->left == NULL && root->right == NULL){
      compression_pair* newest = malloc(sizeof(compression_pair));
      newest->c = root->ch;
      newest->code= malloc(strlen(arr)+1);
      strncpy(newest->code,arr,idx);
      addElement(list,newest);
    }
  }
