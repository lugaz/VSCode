
#include <string.h>
#include <stdio.h>
#include <errno.h>
#include <stdlib.h>
#include <math.h>

#include "rpn.h"
#include "nextword.h"
#include "stack.h"

double rpn_eval(char * fileName, double x) {
	FILE *fd;
	char * words;
	int flag = 0;
	int number = 0;
	double power,logz, exponent,div,sine,cosine,iv,num,w,f,a,b,c,d,e,g,h,i,j,k,l,m,n,z,sum,mul,sub;
	fd = fopen(fileName, "r");
	if (fd == NULL){
		printf("Could not open file %s\n",fileName);
		exit(1);
}
	while((words = nextword(fd)) != NULL){
		if(strcmp(words,"+") == 0) {
  				   w = stack_pop();
                 f = stack_pop();
                sum = f + w;
                  stack_push(sum);
				flag++;
		
		}else if(strcmp(words,"*") == 0){
				  a = stack_pop();
                  b = stack_pop();
                  mul = b * a;
                  stack_push(mul);
                  flag++;
				
		}else if(strcmp(words, "-") == 0){
				  c = stack_pop();
                 d = stack_pop();
               sub = d - c;
                stack_push(sub);
           flag++;
		}else if(strcmp(words, "/") == 0){
				 e = stack_pop();
                 z = stack_pop();
                 div = z / e;
				 stack_push(div);
                 flag++;
		}else if(strcmp(words, "sin") == 0){
			      g = stack_pop();
                  sine = sin(g);
                  stack_push(sine);
		}else if(strcmp(words, "cos") == 0){
				 h = stack_pop();
                 cosine = cos(h);
 			stack_push(cosine);
		}else if(strcmp(words, "pow") == 0){
				i = stack_pop();
                 j = stack_pop();
                 power = pow(j,i);
               stack_push(power);
		}else if(strcmp(words, "log") == 0){
			 k = stack_pop();
                 logz = log(k);
                stack_push(logz);
		}else if(strcmp(words, "exp") == 0){
			     l = stack_pop();
                exponent = exp(l);
               stack_push(exponent);
		}else if(strcmp(words, "x") == 0){
			  stack_push(x);
                 flag = 0;
		}else {
			 stack_push(atof(words));
			flag = 0;
		}
}
		
		
		
	
			if(flag > 2) {
				printf("Stack underflow\n");
				exit(0);
			}
			if (stack_top() != 1){
				printf("Elements remain in the stack\n");
				exit(0);
			}
			else{
				return stack_pop();
			}
			fclose(fd);


	
	


  
}

