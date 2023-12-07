
#include <stdlib.h>
#include "mystring.h"

int mystrlen(char * s) {
//
	int count = 0;
	while (* s != '\0'){
		count ++;
		s++;

	}

	return count;
}

char * mystrcpy(char * dest, char * src) {
	int i = 0;
    char *save = dest;

	while(*src){
		*dest++ = *src++;
		i++;
	}
	*dest = 0;
	return save;
}

char * mystrcat(char * dest, char * src) {
	
	int destlen = mystrlen(dest);	
	int srclen = mystrlen(src);
	
	int i;
	for(i  =  0; i < srclen; i++){
		*(dest + i + destlen) = *(src+i);
		
	}

	
	*(dest + i + destlen)  = '\0';
	return dest;
}

int mystrcmp(char * s1, char * s2) {
	int i = 0;
       
        
    while (*(s1 +i) != '\0' ){
   
		if(*(s1+i) != *(s2+i)){

            if ((int) *(s1+i) < (int) *(s2+i)) {
                return -1;
			
            } else{
                return 1;
            }
        }
            i++;

	}
	 
	if(*(s1+i) == '\0' && *(s2+i) == '\0'){
	return 0;
	}	
       
			

    return -1;
}


char * mystrstr(char * hay, char * needle) {
	const char *a;
    const char *b;

    b = needle;

    if (*b == 0) {
        return (char *) hay;
    }

    for ( ; *hay != 0; hay += 1) {
        if (*hay != *b) {
            continue;
        }

        a = hay;
        while (1) {
            if (*b == 0) {
                return (char *) hay;
            }
            if (*a++ != *b++) {
                break;
            }
        }
        b = needle;
    }

    return NULL;
    	
	
	
}

char * mystrdup(char * s) {
	int i;
	char * returnstring = malloc(mystrlen(s) +1);
	for (i = 0; i < mystrlen(s); i++){
		*(returnstring+i) = *(s+i);
	}
	*(returnstring+i) = '\0';
	return returnstring;
}

char * mymemcpy(char * dest, char * src, int n)
{
	int i;
    for (i = 0; i < n; i++){
            *(dest+i) = *(src+i);
    }
 
    return dest;
}

