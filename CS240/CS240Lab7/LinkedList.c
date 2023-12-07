#include <assert.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "LinkedList.h"

//
// Initialize a linked list
//

void llist_init(LinkedList * list){//good
	list->head = NULL;
}

//
// It prints the elements in the list in the form:
// 4, 6, 2, 3, 8,7
//
void llist_print(LinkedList * list) {//good
	
	ListNode * e;

	if (list->head == NULL) {
		printf("{EMPTY}\n");
		return;
	}

	printf("{");

	e = list->head;
	while (e != NULL) {
		printf("%d", e->value);
		e = e->next;
		if (e!=NULL) {
			printf(", ");
		}
	}
	printf("}\n");
}

//
// Appends a new node with this value at the beginning of the list
//
void llist_add(LinkedList * list, int value) {//good
	// Create new node
	ListNode * n = (ListNode *) malloc(sizeof(ListNode));
	n->value = value;
	
	// Add at the beginning of the list
	n->next = list->head;
	list->head = n;
}

//
// Returns true if the value exists in the list.
//
int llist_exists(LinkedList * list, int value) {//good
	 ListNode *e;
      e = list->head;

      while(e != NULL)
      {
              if(e->value == value)
              {
                      return 1;
                      break;
              }
              e = e->next;
      }
      return 0;
}

//
// It removes the entry with that value in the list.
//
int llist_remove(LinkedList * list, int value) {//good
	ListNode * ln;
	ln = list -> head;
	

	while (ln->next != NULL){
		ListNode * after = ln -> next;
		
		if (after -> value == value){
			ln->next = after->next;
			free(after);
			return 1;
			
		}
		
		ln = ln -> next;
	}
	return 0;
}

//
// It stores in *value the varUBIXClue that correspond to the ith entry.
// It returns 1 if success or 0 if there is no ith entry.
//
int llist_get_ith(LinkedList * list, int ith, int * value) {//good
	ListNode *ln;    
    int i = 0; 
    ln = list->head; 
    while (ln != NULL){
        if (i == ith)  {
            *value = ln -> value; 
            return 1;   //if successs
        }
        ln = ln -> next;    
        i++;   
    }
   
	return 0;// if fails 
}

//
// It removes the ith entry from the list.
// It returns 1 if success or 0 if there is no ith entry.
//
int llist_remove_ith(LinkedList * list, int ith) {//good
	ListNode *ln;    
    ListNode *a = NULL; 
    int i = 0; 
    ln = list -> head; 
   
    while (ln != NULL) {
        if (i == ith)  {
            if (a == NULL) {
                list -> head = ln -> next;  
            }
            else {
                a -> next = ln -> next;
            }
            free(ln);    
            return 1;   // if succesess
        }
        a = ln;  
        ln = ln -> next;   
        i++;  
    }
    return 0;  // if fails 
}

//
// It returns the number of elements in the list.
//
int llist_number_elements(LinkedList * list) {//good
	ListNode *e;
    e = list->head;
	int counter = 0;
      while(e != NULL)
      {
        counter ++;
        e = e->next;
      }
      return counter;
}


//
// It saves the list in a file called file_name. The format of the
// file is as follows:
//
// value1\n
// value2\n
// ...
//
/*int llist_save(LinkedList * list, char * file_name) {//good
	struct ListNode *ln = list -> head;
	FILE * fd = fopen(file_name, "w+");
	if (fd == NULL){
		return 0;
	}
	while(ln != NULL){
		fprintf(fd,"%d\n",ln -> value);
		ln = ln -> next;
	}
    fclose(fd);
	return 0;
}*/
int llist_save(LinkedList * list, char * file_name){
    ListNode * ln = list -> head;
    FILE * fd = fopen(file_name,"w+");
    if (fd == NULL){
        return 0;;
    }
    while (ln != NULL){
        fprintf(fd,"%d\n", ln -> value);
        ln = ln -> next;
    }
    fclose(fd);
    return 0;
}

//
// It reads the list from the file_name indicated. If the list already has entries, 
// it will clear the entries.
//
int llist_read(LinkedList * list, char * file_name) {//bad
	int i; //value
    FILE *fd;   
    fd = fopen(file_name, "r");  
    if (fd == NULL)  {
        return 0;   
    }
     
    while (fscanf(fd, "%d", &i) == 1) {
        llist_add(list, i); 
    }
    fclose(fd); 
    return 1; 
	
}


//
// It sorts the list. The parameter ascending determines if the
// order si ascending (1) or descending(0).
//
void swap(ListNode *a, ListNode *b) 
{ 
    int temp = a->value; 
    a->value = b->value; 
    b->value = temp; 
} 
void llist_sort(LinkedList * list, int ascending){
    int swapped;
    ListNode * ln;
    ListNode * after = NULL;
    if (list == NULL){
        exit(1);
    }
    if (ascending){
        do{
            swapped = 0;
            ln = list -> head;
            while(ln -> next != after){
                if (ln -> value > ln -> next -> value){
                    int temp = ln -> value;
                    ln -> value = ln -> next -> value;
                    ln -> next -> value = temp;
                    swapped = 1;
                }
                ln = ln -> next;
                
            }
            after = ln;
        }while(swapped);
    }else{
        do{
            swapped = 0;
            ln = list -> head;
            while(ln -> next != after){
                if (ln -> value < ln -> next -> value){
                    int temp = ln -> value;
                    ln -> value = ln -> next -> value;
                    ln -> next -> value = temp;
                    swapped = 1;
                }
                ln = ln -> next;
                
            }
            after = ln;
        }while(swapped);
    }
}


	/*ListNode * ln;
	ListNode *temp;
	temp = ln->next;
	int t;
	ln = list -> head;
    while(ln != NULL && ln->next != NULL)
    {
        for(int j=0; j<5; j++)//value 5 because I am taking only 5 nodes
        {
			if (ascending == 1){
            	if(ln->data > temp->data)//swap node data
            	{
                	t = ln->data;
                	ln->data = temp->data;
                	temp->data = t;
            	}
			} else if (ascending == 1){
            	if(ln->data < temp->data)//swap node data
            	{
                	t = ln->data;
                	ln->data = temp->data;
                	temp->data = t;
            	}
			}
            temp = temp->next;
        }
        ln = ln->next;
      }*/
    


//
// It removes the first entry in the list and puts value in *value.
// It also frees memory allocated for the node
//
int llist_remove_first(LinkedList * list, int * value) {
	ListNode *ln;   
    ln = list -> head; 
    if (list -> head == NULL){
        return 0; 
    }
    *value = ln -> value;
    list -> head = ln -> next; 
    free(ln);    
    return 1;   
}

//
// It removes the last entry in the list and puts value in *value/
// It also frees memory allocated for node.
//
int llist_remove_last(LinkedList * list, int *value) {
	
    if (list -> head == NULL) {
        return 0;   
    }
    struct ListNode *ln = list -> head;    
    struct ListNode *prev; 
     
       
    if(ln -> next == NULL){
        *value = ln -> value;
        list -> head = NULL;
       
        return 1;
    }
    while (ln -> next != NULL) {
        prev = ln;   
        ln = ln -> next;  
    }
    
    prev -> next = NULL;
    *value = ln -> value;
    free(ln);   
    return 1; 
}

	

//
// Insert a value at the beginning of the list.
// There is no check if the value exists. The entry is added
// at the beginning of the list.
//
void llist_insert_first(LinkedList * list, int value) {//good
	ListNode *ln;   
    ln = (ListNode *)malloc(sizeof(ListNode));    
    ln -> value = value; 
    ln -> next = list -> head;  
    list -> head = ln; 
}

//
// Insert a value at the end of the list.
// There is no check if the name already exists. The entry is added
// at the end of the list.
//
void llist_insert_last(LinkedList * list, int value) {//good
	ListNode *ln;   
    ListNode *prev; 
    ln = list -> head; 
    prev = NULL;    
    while (ln != NULL) {
        prev = ln;  
        ln = ln -> next;    
    }
    ln = (ListNode *)malloc(sizeof(ListNode));  
    ln -> value = value; 
    ln -> next = NULL; 
    if (prev == NULL)   {
        list -> head = ln; 
    }
    else {
        prev -> next = ln;
    }
}


//
// Clear all elements in the list and free the nodes
//
void llist_clear(LinkedList *list)//good
{
	ListNode *ln, *after;
	ln = list -> head;
	while (ln != NULL){
		after = ln -> next;
		free (ln);
		ln = after;
	}
    list -> head = NULL;
}
