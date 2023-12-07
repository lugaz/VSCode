#include <math.h>
#include <stdio.h>

// It prints the bits in bitmap as 0s and 1s.
void printBits(unsigned int bitmap);
{

	
	decToBinary(bitmap);
	
//	printf("%ld",  decimalToBinary(bitmap));
	printf("\n");	
	printf("10987654321098765432109876543210\n");

}


// Sets the ith bit in *bitmap with the value in bitValue (0 or 1)
void setBitAt( unsigned int *bitmap, int i, int bitValue ) {
	int temp = 1 << i;
	*bitmap = (*bitmap & ~temp) | ((bitValue << i) & temp);
	return;	// Add your code here
}

// It returns the bit value (0 or 1) at bit i
int getBitAt( unsigned int bitmap, unsigned int i) {
	// int one = 0;
    // int zero = 0;
	 int k;
	 for (int j = 31; j >= i; j--) {
            k = bitmap >> j; // right shift
           
	}
		if (k & 1){
			return 1;
		}else{
			return 0;
		}
}

// It returns the number of bits with a value "bitValue".
// if bitValue is 0, it returns the number of 0s. If bitValue is 1, it returns the number of 1s.
int countBits( unsigned int bitmap, unsigned int bitValue) {
	int one = 0;
	int zero = 0;
for (int i = 31; i >= 0; i--) {
          int k = bitmap >> i; // right shift
          if (k & 1){
				one++;
			} // helps us know the state of first bit
                
          else{
			zero++;
			} 
	
      }	// Add your code here
 if (bitValue == 1){
	return one;
}
if (bitValue == 0){
	return zero;
}
}

// It returns the number of largest consecutive 1s in "bitmap".
// Set "*position" to the beginning bit index of the sequence.
int maxContinuousOnes(unsigned int bitmap, int * position) {
	
	int j;
	int maxOnes = 0;
	*	position = 0;
	int tempmax = 0;
	for (int i = 0; i <= 31; i++){
		
		
		j = bitmap >> i;
		if (j & 1){
			tempmax++;
			if (tempmax > maxOnes){
				maxOnes = tempmax;
				*position = i - maxOnes + 1;
			}
		}else{
		tempmax = 0;
}
}
return maxOnes;
}



int decToBinary(int n)
{
    // Size of an integer is assumed to be 32 bits
    for (int i = 31; i >= 0; i--) {
        int k = n >> i; // right shift
        if (k & 1) // helps us know the state of first bit
              printf("1");
        else printf("0");
    }
}
/*
int  decimalToBinary(int decimalnum)
{
    int  binarynum = 0;
    int rem, temp = 1;
	int binary[32];
    while (decimalnum!=0)
    {
        rem = decimalnum%2;
        decimalnum = decimalnum / 2;
        binarynum = binarynum + rem*temp;
        temp = temp * 10;
    }
    return binarynum;
}
*/
