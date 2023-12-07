
#include <stdio.h>
#include "stack.h"
#include <stdlib.h>

int top=0;
double stack[MAXSTACK];

void stack_clear() 
{
  top = 0;
}

double stack_pop(){
	
	
	double reset = stack[top-1];
	top--;
	return reset;
	
	// Add implementation here`
	 
}

void stack_push(double val)
{
	stack[top] = val;
	top++;


	// Add implementation here
}

void stack_print()
{
	printf("Stack:\n");
	if (top == 0) {
		printf("Stack is empty");
		
	}else{
		for (int n = 0; n < top; n++)	{
			printf("%d: %f\n", n, stack[n]);
	}
}
} 
int stack_top()
{
  return top;
}

int stack_max()
{
  return MAXSTACK;
}

int stack_is_empty()
{
  return top == 0;
}


