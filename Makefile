main_driver: Airport_finder.o ArrayList.o
	gcc -o main_driver Airport_finder.o ArrayList.o
Airport_finder.o: Airport_finder.c ArrayList.h
	gcc -c Airport_finder.c
ArrayList.o: ArrayList.h ArrayList.c
	gcc -c ArrayList.c

 