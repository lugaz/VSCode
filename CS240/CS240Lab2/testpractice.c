#include "mystring.h"
#include <assert.h>
#include <stdlib.h>
#include <stdio.h>
void test() {
	char a[200];
	char * b;
        mystrcpy(a, "hello world");
	printf("\"%s\"\n",a);
	b = mystrcat(a,", cs240");
	b = mystrcat(a, "done");
}
	 
