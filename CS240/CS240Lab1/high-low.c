#include <stdio.h>
#include <stdbool.h>
int
main(int argc, char **argv) {
  char playAgain;
  printf("Welcome to the High Low game...\n");
  do {
  printf("Think of a number between 1 and 100 and press <enter>\n");
  bool trueGuess = false;
  int high = 100;
  int low = 1;
  int avg;
  char highOrLower;
  
  //  char question = "Is it higher than %d? (y/n)";
 
  do {
          avg = (high+low)/2;
	  printf("Is it higher than %d? (y/n)\n",avg);
	  
	  
	  scanf("%s",&highOrLower);
      	 
	 
	 if (highOrLower == 'y'){
		low = avg + 1;
	       if (avg ==(high+low)/2 + 1){
	       trueGuess = true;
              }else if (avg == (high+low)/2){ 
		 trueGuess = true;
		 avg = avg +1;
             }		      
	       
	 }  else if (highOrLower == 'n'){
		 high = avg - 1;
		 if (avg == (high+low)/2 +1){
			trueGuess = true; 
		 }else if (avg == (high+low)/2){
			 trueGuess = true;
		         avg = avg + 1;
		 }
	 } else {
		printf("Type y or n\n");
	}

	


  }  while (trueGuess == false);
  	printf("\n>>>>>> The number is %d\n",avg);
  	printf("\nDo you want to continue playing (y/n)?\n");
        scanf("%s",&playAgain);
  } while (playAgain == 'y');
  	printf("Thanks for playing!!!");
  // Write your implementation here...
}


