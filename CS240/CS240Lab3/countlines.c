#include <stdio.h>
#include <stdlib.h>

int
main(int argc, char ** argv) {
        int c;
        int lines=0;
        printf("Program that counts lines.\n");  
       	if (argc < 2) {
          
          exit(1);
        }

        // Get file from first argument.
        char * fileName = argv[1];
        FILE * fd = fopen(fileName, "r");
        if (fd == NULL) {
          printf("Could not open file %s\n", fileName);
          exit(1);
        }

        // Iterate over all characters in file and pront them
        while ((c=fgetc(fd))!=-1) {
		int nlines = 0;
		int c;
		while ((c = fgetc(fd)) != EOF) {
			if (c == '\n') nlines++;
		}
                printf("Total lines: %d\n", nlines);
        }

        fclose(fd);
	exit(0);
}
        // Add your implementation here

        


