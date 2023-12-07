#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
int convertToA(int n);
char * convert(char *, char*);
char * bn (char *, char *);
int myPow(int,int);
int main(int argc, char ** argv){
    

	if (argc != 4){
        printf("Usage:  convert <basefrom> <baseto> <number>\n");
        exit(1);
    }
    char * baseform = argv[1];
    char * baseto = argv[2];
    char * number = argv[3];

   
    int x = atoi(baseform);
    int y = atoi(baseto);
    int z = atoi(number);
    for ( int i = 0; i < strlen(number); i++){
        if (0 <= number[i] - '0' && number[i] - '0' <= 9){
            if ( number [i] - '0' >=x){
                printf("Number read in base %d: %s\n",atoi(baseform), number);
                printf("Wrong digit in number.\n");
                exit(1);
            }    
        }
    }
    printf("Number read in base %d: %s\n",atoi(baseform),number);
    char* base10 = bn(baseform,number);
    char * con = convert(baseto,base10);
    printf("Converted to base 10: %s\n",base10);
    printf("Converted to base %d: %s\n",atoi(baseto),con);
}





char* bn(char * baseform,char* number){
    int ln = strlen(number) - 1;
    int sum = 0;
    int num = atoi(number);
    int base = atoi(baseform);
    int j = 0;
    int indexof = 0;

    for(int i = ln; i >= 0; i--){
        char n = number[j];
        switch(n){
            case 'A':
                indexof = 10;
                break;
            case 'B':
                indexof = 11;
                break;
            case 'C':
                indexof = 12;
                break;
            case 'D':
                indexof = 13;
                break;
            case 'E':
                indexof = 14;
                break;
            case 'F':
                indexof = 15;
                break;
            case 'G':
                indexof = 16;
                break;
            case 'H':
                indexof = 17;
                break;
            case 'I':
                indexof = 18;
                break;
            case 'J':
                indexof = 19;
                break;
            case 'K':
                indexof = 20;
                break;
            case 'L':
                indexof = 21;
                break;
            case 'M':
                indexof = 22;
                break;
            case 'N':
                indexof = 23;
                break;
            case 'O':
                indexof = 24;
                break;
            case 'P':
                indexof = 25;
                break;
            default:
                indexof =  n - '0';

        }
        sum += (indexof * myPow(base,i));
        j++;
    }
    char * toten = (char *)malloc(sizeof(char*)*1000);
    sprintf(toten,"%d",sum);
    return toten;
}

int myPow(int base, int power)
{
    int result = 1;

    while (power) {
        if (power & 1) {
            result *= base;
        }

        power >>= 1;
        base *= base;
    }

    return result;
}
                             


char * convert(char * baseto, char* number){
    int baseten = atoi(number);
    int gb = atoi(baseto);
    int remainder = baseten %  gb;
    int x;
    int i = 0;
    char y;
    char* t = (char*)malloc(sizeof(char*)*1000);


    while (baseten != 0){
        baseten = baseten / gb;
		if (remainder <= 25 && remainder >= 10){
			x = remainder +31;
	
		}
        
        if ( 0 <= x && x <= 9) {
            char l= x +'0';
            t[i] = l;
            
        }else{
            t[i] = x;
        }
        i++;
        remainder = baseten % gb;
    }
    t[i] = '\0';
    int q = 0;
    char * ton = (char*)malloc(sizeof(t));
    int w = 0;
    for (int f = strlen(t) - 1; q>=0; f--){
        ton[w] = t[q];
        w++;
    }
return ton;
}
