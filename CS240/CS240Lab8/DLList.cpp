#include "DLList.h"

/**	
 * @brief      Constructor for the DLList.
 */
DLList::DLList() {
  /** @todo Write a constructor for a dllist. Check slides! **/
  DLNode * ln = new DLNode();
  head = ln;
  int nElements = 0;
  head -> next = head;
  head -> prev = head;

}

/**
 * @brief      Destructor for the DLList.
 */
DLList::~DLList() {
  delete head;
  /** @todo Clean up your mess! **/
}

/**
 * @brief      Print the DLList line by line.
 */
void DLList::print() {
  DLNode * ln = head;
  while (ln -> next != head) {
    int value = ln -> data;
    printf("%d\n", value);
    ln = ln -> next;
  }

  /** @todo Print this list line by line **/
}

/**
 * @brief      Sort and print the list.
 * 
 * This function should sort and print the list.
 * Note: the actual list is NOT to be modified.
 * In other words, it should only *print* in a
 * sorted order, not actually sort the list.
 */
void DLList::printSorted() {
  int count = 0;
  DLNode * ln = head;
  while (ln -> next != head) {
    count++;
    ln = ln -> next;
  }
  int temp[count];
  DLNode * e = head;
  for (int i = 0; i < count; i++) {
    temp[i] = e -> data;
    e = e -> next;
  }
  for (int i = 0; i < count - 1; i++) {
    for (int j = 0; j < count - i - 1; j++) {
      if (temp[j] > temp[j + 1]) {
        int k = temp[j];
        temp[j] = temp[j + 1];
        temp[j + 1] = k;
      }
    }
  }
  for (int i = 0; i < count; i++) {
    printf("%d\n", temp[i]);
  }

  /** @todo Print a sorted copy of this list **/
}

/**
 * @brief      Add to the front of the list.
 *
 * @param[in]  data  Item to add to front.
 */
void DLList::insertFront(int data) {
  DLNode * temp = new DLNode();
  temp -> data = data;
  DLNode * ln = head;
  while (ln -> next != head) {
    ln = ln -> next;
  }
  ln -> next = temp;
  temp -> prev = ln;
  head -> prev = temp;
  temp -> next = head;
  head = temp;

  /** @todo Insert to the front of the list **/
}

/**
 * @brief      Removes & stores the last element.
 *
 * The last element is removed and stored in the
 * referenced variable data.
 * 
 * @param      data  Thing in which we are storing the data.
 *
 * @return     True upon successful removal.
 */
bool DLList::removeLast(int & data) {
  if (head -> next == head) {
    head = NULL;
    return true;

  } else {
    DLNode * e = head;
    while (e -> next != head) {
      e = e -> next;
    }
    data = 1;

    e -> prev -> next = head;
    head -> prev = e -> prev;
    return true;
  }
  return false;

  /** @todo Remove the last thing **/

}

/**
 * @brief      Difference of two lists.
 *
 * @param      list  Subtrahend.
 *
 * @return     Returns a pointer to the difference.
 */
DLList * DLList::difference(DLList & list) {
  DLList * diff = new DLList();
  DLNode * res = diff -> head;
  diff -> head = res;
  DLNode * orr = head;
  DLNode * ou = list.head;
  while (orr -> next != head) {
    int temp = orr -> data;
    int count = 0;
    while (ou -> next != list.head) {
      int i = ou -> data;
      if (temp == i) {
        count++;
      }
      ou = ou -> next;
    }
    if (count == 0) {
      diff -> insertFront(temp);
    }
    orr = orr -> next;
    ou = list.head;
  }
  return diff;
}

/**
 * @brief      Returns a sublist of items in a range.
 *
 * @param[in]  start  First index.
 * @param[in]  end    Second index.
 *
 * @return     Elements between first and second index.
 */
DLList * DLList::getRange(int start, int end) {
  DLList * range = new DLList();
  return range;

}

/**
 * @brief      Intersection of this list and another list.
 *
 * @param      list  The other list.
 *
 * @return     Elements list shares with this one.
 */
DLList * DLList::intersection(DLList & list) {
  DLList * a = new DLList();
  DLList * inter = new DLList();
  DLNode * res = inter -> head;
  inter -> head = res;
  DLNode * original = head;
  DLNode * out = list.head;

  while (original -> next != head) {
    int temp = original -> data;
    int count = 0;
    while (out -> next != list.head) {
      int b = out -> data;
      if (temp == b) {
        count++;
      }
      out = out -> next;
    }
    //found element
    if (count != 0) {
      printf("%d\n", temp);
      DLNode * ln = new DLNode();
      ln -> data = temp;
      res -> next = ln;
      ln -> prev = res;
      inter -> head -> prev = ln;
      ln -> next = inter -> head;
    }
    original = original -> next;
    out = list.head;

    

  }
  return a;
}

/**
 * @brief      Removes nodes in the start-end range.
 *
 * @param[in]  start  First node.
 * @param[in]  end    Second node.
 */
void DLList::removeRange(int start, int end) {
  /** @todo Remove a range of elements **/
}