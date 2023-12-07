#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void mymemdump(FILE * fd, char * p, int len) {
   int j,i,c;
  

  for (int i = 0; i < len; i += 16) {

    fprintf(fd, "0x%016lX: ", (unsigned long) &p[i]);
    for (j = 0; j < 16; j++) {
      c = p[i+j] & 0xFF;
      if (i+j < len) {
				fprintf(fd, "%02X ", c);
			}
			else {
				fprintf(fd, "   ");
			}
    }
    fprintf(fd, " ");
    for (j = 0; j < 16 && j+i < len; j++) {
      c = p[i+j] & 0xFF;
      fprintf(fd, "%c", (c>=32 && c<=127)?c:'.');
    }
    if (i % 16 == 0) {
      fprintf(fd, "\n");
    }
  }

}
/*
   int j,i;
  

  for (int i = 0; i < len; i += 16) {

    fprintf(fd, "0x%016lX: ", (unsigned long) &p[i]);
    for (j = 0; j < 16; j++) {
      int c = p[i+j] & 0xFF;
      if (i+j < len) {
				fprintf(fd, "%02X ", c);
			}
			else {
				fprintf(fd, "   ");
			}
    }
    fprintf(fd, " ");
    for (j = 0; j < 16 && j+i < len; j++) {
      int c = p[i+j] & 0xFF;
      fprintf(fd, "%c", (c>=32 && c<=127)?c:'.');
    }
    if (i % 16 == 0) {
      fprintf(fd, "\n");
    }
  }

}*/
  

    // Add your code here.
    // You may see p as an array.
    // p[0] will return the element 0 
//     // p[1] will return the element 1 and so on
    
//     int i;
//     fprintf(fd, "0x%016lX: ", (unsigned long) p); // Print address of the beginning of p. You need to print it every 16 bytes

//     for (i=0; i < len; i++) {
//         int c = p[i]&0xFF; // Get value at [p]. The &0xFF is to make sure you truncate to 8bits or one byte.

//         // Print first byte as hexadecimal
//         fprintf(fd, "%02X ", c);

//         // Print first byte as character. Only print characters >= 32 that are the printable characters.
//         fprintf(fd, "%c ", (c>=32)?c:'.');

//        if (i % 16 == 0 ) {
//         fprintf(fd,"\n");
//       }
//     }

// }

