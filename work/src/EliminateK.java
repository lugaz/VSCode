import org.w3c.dom.Node;

public class EliminateK {

    public int computeSurvivor(int n,int k){
            
        LinkedList list = new LinkedList();

        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
    
        LinkedList.Node current = list.head; 
        LinkedList.Node previous = null;
    
        int count = 0;
    
        while (list.size() > 1) {
            count++;
            if (count == k) {
                list.remove(current.person);
                count = 0;
            }
    
            previous = current;
            current = current.next;
    
            if (current == null) {
                current = list.head;
            }
        }
    
        return list.peek();

    }

}


    class LinkedList {
        Node head;
        class Node {
            int person;
            Node next;
    

            Node(int person) { 
                this.person = person; 
            }
        }


        public void add(int person){
            Node toinsert = new Node(person);
            if (head == null){
                head = toinsert;
                return;
            }
            Node temp = head;

            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = toinsert;
        }
        public void print(){
            Node temp = head;
            while(temp != null){
                System.out.println(temp.person);
                temp = temp.next;
            }
        }
        public int peek(){
            if(head == null){
                return 0;
            }
            return head.person;

        }

        public void remove(int value) {
            Node curr = head;
            Node prev = null;

            if (curr != null && curr.person == value) {
                head = curr.next; 
                return;
            }

            while (curr != null && curr.person != value) {
                prev = curr;
                curr = curr.next;
            }

            if (curr == null) {
                return;
            }

            prev.next = curr.next;
        }

        public int size(){
            int count = 0;
            Node temp = head;
            while(temp != null){
                count ++;
                temp = temp.next;
            }
            return count;
        }


    }



