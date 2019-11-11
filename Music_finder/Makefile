main_driver: music_finder.o ArrayList.o music.o
	gcc -o main_driver music_finder.o ArrayList.o music.o
music_finder.o: music_finder.c ArrayList.h music.h
	gcc -c music_finder.c
ArrayList.o: ArrayList.h ArrayList.c
	gcc -c ArrayList.c
music.o: music.h music.c ArrayList.h
	gcc -c music.c
