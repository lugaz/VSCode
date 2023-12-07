
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//
// Separates the file into words
//

#define MAXWORD 200
char word[MAXWORD];
int wordLength;

// It returns the next word from fd.
// If there are no more more words it returns NULL. 
char * nextword(FILE * fd) {
  	int c;t
	wordLength = 0;

  while ((c = fgetc(fd)) != EOF) {
    
   
    if(c != 32 && c != '\t' && c != '\r' && c != '\n' && c!= '\0') {
    	  
             
        word[wordLength++] =c;
 
	
     }else{
	word[wordLength] = '\0';
	if(strlen(word) != 0){
		return word;
	}

     
   	
    	
	}
     }
     

	// While it is not EOF read char
		// While it is not EOF and it is a non-space char
		// store character in word.
		// if char is not in word return word so far.
		//
    	// Return null if there are no more words
	return NULL;

}


