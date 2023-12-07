
// String Implementation
// IMPORTANT: Do not use any of the functions in the string C runtime library
// Example. Do not use strcpy, strcmp, etc. Implement your own

// IMPORTANT: See the MyString.h file for a description of
// what each method needs to do.

#include <stdio.h>
#include "MyString.h"
#include <cstdlib>

// My own implementation of strlen
int
MyString::slength(const char *s) const
{
  int count = 0;
  while (*(s + count) != '\0'){
    count++;

  }
  return count;
}

// Initialize _s. Allocate memory for _s and copy s into _s
void
MyString::initialize(const char * s)
{
  int len = slength(s);
  _s = new char(slength(s));
  for (int i = 0; i < len; i++ ){
    _s[i] = s[i];
  }
  // Add implementation here
  // Allocate memory for _s.

  // Copy s into _s
}

// Create a MyString from a C string
MyString::MyString(const char * s)
{
  initialize(s);
}

// Create a MyString from a copy of another string
MyString::MyString(const MyString &s)
{
  initialize(s._s);
}

// Create a MyString with an empty string
MyString::MyString()
{
  _s = new char[1];
  *_s = 0;
}

// Assignment operator. Without this operator the assignment is
// just a shallow copy that will copy the pointer _s. If the original _s
// goes away then the assigned _s will be a dangling reference.
MyString &
MyString::operator = (const MyString & other) {
  if (this != &other) // protect against invalid self-assignment
  {
    // deallocate old memory
    delete [] _s;

    // Initialize _s with the "other" object.
    initialize(other._s);

    // by convention, always return *this
    return *this;
  }
}

// Obtain a substring of at most n chars starting at location i
// if i is larger than the length of the string return an empty string.
MyString
MyString::substring(int i, int n)
{
  //char * sub = new char(n+1);
  char* sub = new char[n + 1];
  if (i > slength(_s)){
    return sub;
  }
  
    for (int j = 0; j < n; j++){
        sub[j] = *(_s + i + j);
    }
    sub[n] = '\0';
    return sub;
   
}
  // Add implementation here

  // Make sure that i is not beyond the end of string

  // Allocate memory for substring

  // Copy characters of substring


  // Return substring
 


// Remove at most n chars starting at location i
void
MyString::remove(int i, int n)
{
    // int length = slength(_s) - n + 1;
    // char * s1 = (char *) malloc(length);

    // //char* sub = new char[_s + 1];
    // int count = 0;
   
    //  for (int j = 0; j < slength(_s); j++){
    //     if (j == i){
    //      j = j + n;
         
    //   }
    //    s1[j] = *(_s + j);
      
    // }
    // _s = (char *) malloc(length);
    // for (int k = 0; k < length; k++){
    //     _s[k] = s1[k];
    // }
    
    // _s = "He world";
  
   
  // Add implementation here

  // If i is beyond the end of string return

  // If i+n is beyond the end trunc string

  // Remove characters
}

//this -> s and s._s

/*int 
MyString:: cmp(char * s1, char * s2){
  int i = 0;
        
  while (s1[i] != '\0' ){
   
	  if(s1[i] != s2[i]){

      if ((int) s1[i] < (int) s2[i]) {
        return -1;
      } else{
        return 1;
      }
    }
      i++;
	} 
	if(s1[i] == '\0' && s2[i] == '\0'){
	return 0;
	}	
    return -1;
}*/
// Return true if strings "this" and s are equal
bool
MyString::operator == (const MyString & s)
{
  char* s1 = this -> _s;
  char* s2 = s._s;

  int i = 0;
        
  while (s1[i] != '\0' ){
   
	  if(s1[i] != s2[i]){
      if ((int) s1[i] < (int) s2[i]) {
        return 0;
			
      } else{
        return 1;
      }
    }
      i++;
	}
	if(s1[i] == '\0' && s2[i] == '\0'){
	  return 1;
	}	
    return 0;
  }
 
  // Add implementation here
  


// Return true if strings "this" and s are not equal
bool
MyString::operator != (const MyString &s)
{
  char* s1 = this -> _s;
  char* s2 = s._s;
  int i = 0;   
  while (s1[i] != '\0' ){
   
	  if(s1[i] != s2[i]){
      if ((int) s1[i] < (int) s2[i]) {
        return 1;
			
      } else{
        return 0;
      }
    }
      i++;
	}
	if(s1[i] == '\0' && s2[i] == '\0'){
	  return 0;
	}	
    return 1;
  }

// Return true if string "this" and s is less or equal
bool
MyString::operator <= (const MyString &s)
{
  char* s1 = this -> _s;
  char* s2 = s._s;
  int i = 0;   
  while (s1[i] != '\0' ){
   
	  if(s1[i] != s2[i]){
      if ((int) s1[i] <= (int) s2[i]) {
        return true;
      } else{
        return false;
      }
    }
      i++;
	}
	if(s1[i] == '\0' && s2[i] == '\0'){
	  return true;
	}	else if (s1[i] == '\0'){
      return true;
  }
    return false;
  }

// Return true if string "this" is greater than s
bool
MyString::operator > (const MyString &s)
{
  char* s1 = this -> _s;
  char* s2 = s._s;
  int i = 0;   
  while (s1[i] != '\0' ){
   
	  if(s1[i] != s2[i]){
      if ((int) s1[i] > (int) s2[i]) {
        return true;
			
      } else{
        return false;
      }
    }
      i++;
	}
	if(s1[i] == '\0' && s2[i] == '\0'){
	  return false;
	}	else if (s2[i] == '\0'){
    return true;
  }
    return true;
  }

// Return character at position i.  Return '\0' if out of bounds.
char
MyString::operator [] (int i)
{
  // Add implementation here
  return 'W';
}

// Return C representation of string
const char *
MyString::cStr()
{
  // Add implementation here
  return _s;
}

// Get string length of this string.
int
MyString::length() const
{
  // Add implementation here
  return slength(_s);
}

// Destructor. Deallocate the space used by _s
MyString::~MyString()
{
  // Add implementation here
  delete [] _s;
}

// Concatanate two strings (non member method)
MyString operator + (const MyString &s1, const MyString &s2)
{
  char* s1l = s1._s;
  char* s2l = s2._s;
  int s1len = 0;	
	int s2len = 0;
  while (s1l[s1len] != '\0'){
    s1len++;
  }
  while (s2l[s2len] != '\0'){
    s2len++;
  }
	
	int i;
	for(i  =  0; i < s2len; i++){
		s1l[i + s1len] = s2l[i];
		
	}

	
	s1l[i + s1len] = '\0';
    MyString s = s1l;
    return s;
  // Add implementation here
  
  // Allocate memory for the concatenated string

  // Add s1

  // Add s2

  
  return s;
}

