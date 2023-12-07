#include <stdio.h>
#include <stdlib.h>

#include "rpn.h"

#define MAXCOLS 80
#define MAXROWS 40

/*char plot[MAXROWS][MAXCOLS];
int max,min;
void clearPlot()
{
  for (int i = 0; i < MAXROWS; i++) {
    for (int j = 0; j < MAXCOLS; j++) {
      plot[i][j] = ' ';
    }
  }
}

void printPlot()
{
  for (int i = 0; i < MAXROWS; i++) {
    for (int j = 0; j < MAXCOLS; j++) {
      printf("%c", plot[i][j]);
    }
    printf("\n");
  }
}

void plotXY(int x, int y, char c) {
  if ( x <0 || x>=MAXCOLS || y < 0 || y >=MAXROWS) return;
  plot[y][x]=c;
}

void createPlot( char * funcFile, double minX, double maxX) {
  int nvals = MAXCOLS;
  double yy[MAXCOLS];

  clearPlot();
double maximum(double * array, int length){

	for (int i = 0; i < MAXCOLS-1; i++)
    {
		int max = plot[i][0];
         for (int j = 0; j < MAXROWS -1; j++)
         {
             if (plot[i][j] > max)
             {
                 max = plot[i][j];
             }
         }
    
    }
return max;
}  
int smallest_number()
{
   int min = b[0][0];
   int x,y;

   for (x = 0; x < n; x++)
   {
       for (y = 0; y < n; y++)
       {
           if (min > b[x][y])
           {
               min = b[x][y];
           }
       } 
   }  

   return min;
}// Evaluate function and store in vector yy

  //Compute maximum and minimum y in vector yy
  
  //Plot x axis

  
//Plot y axis

  // Plot function. Use scaling.
  // minX is plotted at column 0 and maxX is plotted ar MAXCOLS-1
  // minY is plotted at row 0 and maxY is plotted at MAXROWS-1
void creatPlot (double minX, double maxX, char * funcFile){
clearPlot();
double xx[MAXCOLS];
double yy[MAXCOLS];
int val = MAXCOLS;

for(int i = 0; i <val; i++){
	double x = minX + (maxX-minX)*i . MAXCOLS;
	double y = rpn_eval(funcFile,x);
	xx[i] = x;
	yy[i] = y

	
}
int l = val;
//double maxY = max(yy,l);
//double minY = min(yy,l);
int xy;
if(maxY + minY == 0){
	xy = MAXROWS/2;
}else{
	xy = (maxY+minY)/MAXROWS;

}

for (int i = 0; i < MAXROWS; i++){
	plotXY(MAXCOLS/2,i,'|');

}

for (int i = 0; i < MAXCOLS; i++){
	plotXY(i,xy,'_');
}

for(int i=0; i < MAXCOLS; i++){
	int x = i;
	int y = (int) ((yy[i] - minY) * MAXROWS / (maxY - minY));
plotXY(x, y, '*');
}
}

  printPlot();


*/

int main(int argc, char ** argv)
{
/*  printf("RPN Plotter.\n");
  
  if (argc < 4) {
    printf("Usage: plot func-file xmin xmax\n");
    exit(1);
  }

  // Get arguments
  
  //createPlot(funcName, xmin, xmax);

*/
}

