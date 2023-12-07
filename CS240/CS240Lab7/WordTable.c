
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "WordTable.h"

// Initializes a word table
void wtable_init(WordTable * wtable)
{
	// Allocate and initialize space for the table
	wtable->nWords = 0;
	wtable->maxWords = 10;
	wtable->wordArray = (WordInfo *) malloc(wtable->maxWords * sizeof(WordInfo));
	for (int i = 0; i < wtable->maxWords; i++) {
		llist_init(&wtable->wordArray[i].positions);
	}
}

// Add word to the tableand position. Position is added to the corresponding linked list.
void wtable_add(WordTable * wtable, char * word, int position)
{
	// Find first word if it exists
	for (int i = 0; i < wtable->nWords; i++) {
		if ( strcmp(wtable->wordArray[i].word, word)== 0 ) {
			// Found word. Add position in the list of positions
			llist_insert_last(&wtable->wordArray[i].positions, position);
			return;
		}
	}

	// Word not found.

	// Make sure that the array has space.
	// Expand the wordArray here.

	// Add new word and position
	wtable->wordArray[wtable->nWords].word = strdup(word);
	llist_insert_last(&wtable->wordArray[wtable->nWords].positions, position);
	wtable->nWords++;
}

// Print contents of the table.
void wtable_print(WordTable * wtable, FILE * fd)
{
	fprintf(fd, "------- WORD TABLE -------\n");

	// Print words
	for (int i = 0; i < wtable->nWords; i++) {
		fprintf(fd, "%d: %s: ", i, wtable->wordArray[i].word);
		llist_print( &wtable->wordArray[i].positions);
	}
}

// Get positions where the word oc	curs
LinkedList * wtable_getPositions(WordTable * wtable, char * word){
	for (int i = 0; i < wtable->nWords; i++) {
		if ( strcmp(wtable->wordArray[i].word, word)== 0 ) {
			// Found word. return possition
			return &(wtable->wordArray[i].positions);
		}
	}
	
}

//
// Separates the string into words
//

#define MAXWORD 200
char word[MAXWORD];
int wordLength;
int wordCount;
int charCount;
int wordPos;

// It returns the next word from stdin.
// If there are no more more words it returns NULL.
// A word is a sequence of alphabetical characters.
static char * nextword(FILE * fd) {
	char * word = (char*) malloc(20*sizeof(char));
	int i;
	if((i = fgetc(fd)) == EOF){
		return NULL;
	}
	while (i != -1){
		if (((i >= 65) && ( i <=90)) || ((i >= 97) && (i <= 122))){
			if (charCount > 0){
				word[wordLength] = '\0';
				wordCount++;
				ungetc(i,fd);
				return word;
			}
			word[wordLength++] = i;
		}else{
			charCount++;
		}
		i = fgetc(fd);
	}
	if (strlen(word) != 0){
		word[wordLength] = '\0';
		wordCount ++;
		return word;
	}

}

// Conver string to lower case
void toLower(char *s) {
	int i = 0;
	while (*(s + i) != '\0'){
		if (*(s+i) >= 65 && (*(s + i) <= 90)){
			*(s+i) = *(s+i) + 32;
		}
		i++;
	}
	
}


// Read a file and obtain words and positions of the words and save them in table.
int wtable_createFromFile(WordTable * wtable, char * fileName, int verbose) {
	// Write your code here
	FILE * fd = fopen(fileName, "r");
	if (fd == NULL){
		return 0;
	}
	int c = 0;
	char * word;
	while ((word = nextword(fd)) != NULL){
		toLower(word);
		wtable_add(wtable, word, wordPos);
		if(verbose == 1){
			printf("%d: word=%s, pos=%d\n",c++,word,wordPos);
		}
		wordPos += wordLength;
		wordPos += charCount;
		wordLength = 0;
		charCount = 0;
	}
	return 0;
}

// Sort table in alphabetical order.
void wtable_sort(WordTable * wtable) {
	// Write your code here
	WordInfo temp;
	for (int i = 0; i < wtable -> nWords; i++){
		for (int j = i + 1; j < wtable -> nWords; j++){
			if(strcmp(wtable ->wordArray[i].word,wtable->wordArray[j].word) >= 0){
				temp = wtable -> wordArray[j];
				wtable -> wordArray[j] = wtable -> wordArray[i];
				wtable -> wordArray[i] = temp;
			}
		}
	}
}

// Print all segments of text in fileName that contain word.
// at most 200 character. Use fseek to position file pointer.
// Type "man fseek" for more info. 
int wtable_textSegments(WordTable * wtable, char * word, char * fileName){
	// Write your code here
	printf("===== Segments for word \"%s\" in book \"%s\" =====\n",word, fileName);
	LinkedList *temp = wtable_getPositions(wtable, word);
	FILE * fd = fopen(fileName, "r");
	ListNode * c = temp -> head;
	while (c != NULL){
		int filePos = c -> value;
		printf("---------- pos=%d-----\n",filePos);
		printf("......");
		fseek(fd,filePos, SEEK_SET);
		char d;
		for (int i = 0; i<200; i++){
			c = fgetc(fd);
			printf("%c",c);
		}
		printf("......");
		printf("\n");
		c = c -> next;

	}
}

