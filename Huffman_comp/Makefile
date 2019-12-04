huff: Huffman_compressor.o ArrayList.o MinHeap.o
	gcc -o huff Huffman_compressor.o ArrayList.o MinHeap.o
Huffman_compressor.o: Huffman_compressor.c ArrayList.h MinHeap.h
	gcc -c Huffman_compressor.c
puff: Huffman_decompressor.o ArrayList.o MinHeap.o
	gcc -o puff Huffman_decompressor.o ArrayList.o MinHeap.o
Huffman_decompressor.o: Huffman_decompressor.c ArrayList.h MinHeap.h
	gcc -c Huffman_decompressor.c
MinHeap.o:  MinHeap.c MinHeap.h ArrayList.h 
	gcc -c MinHeap.c
ArrayList.o: ArrayList.c ArrayList.h
	gcc -c ArrayList.c


