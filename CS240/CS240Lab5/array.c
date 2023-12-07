
#include <stdio.h>

#include "array.h"

// Return sum of the array
/*double sumArray(int n, double * array) {
  double sum = 0;
  
  double * p = array;
  double * pend = p+n;

  while (p < pend) {
    sum += *p;
    p++;
  }

  return sum;
}*/
double sumArray( int n, double * array){
    double total = 0;
    for (int i = 0; i < n; i++){
        total = total + *(array + i);
    }
    return total;
}

// Return maximum element of array
double maxArray(int n, double * array) {
  double max = array[0];
  for(int i = 0; i< n; i++){
    if(*(array+i) > max){
      max = *(array+i);

    }
  }
  return max;
}

// Return minimum element of array
double minArray(int n, double * array) {
  double min = array[0];
  for(int i = 0; i < n; i++){
    if(*(array+i) < min){
      min = *(array+i);
    }
  }
  return min;
}

// Find the position int he array of the first element x 
// such that min<=x<=max or -1 if no element was found
int findArray(int n, double * array, double min, double max) {
  
  for(int i = 0; i < n; i++){
    if(*(array+i) < max && *(array+i) > min){
      
      return i;
    }
  }
  return -1;
}

// Sort array without using [] operator. Use pointers 
// Hint: Use a pointer to the current and another to the next element
int sortArray(int n, double * array) {
  int i, j, t;
    for (i = 0; i < n; i++) {
 
        for (j = i + 1; j < n; j++) {
 
            if (*(array + j) < *(array + i)) {
 
              t = *(array + i);
              *(array + i) = *(array + j);
              *(array + j) = t;
            }
        }
    }
}

// Print array
void printArray(int n, double * array) {
  for(int i = 0; i < n; i++){
    printf("%d:%f\n",i,*(array+i));
  }
}

