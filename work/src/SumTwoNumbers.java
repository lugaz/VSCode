public class SumTwoNumbers {
    public class Node {//node class
        int key;
        Node previous,next;
        public Node(int key) {
            this.key  = key;
        }
    }
    Node head, tail = null;
    public Node num1_list;
    public Node num2_list;
    public void addNode (int key){//adds new node to the dd linked list
        Node temp = new Node(key);
        if(head == null){
            head = tail = temp;
            head.previous = null;
            head.next = null;

        }else{
            tail.next = temp;
            temp.previous = tail;
            tail = temp;
            tail.next = null;
        }
    }

    public void printNodes(){//for testing if the nodes are being added
        Node curr = head;
        if(head == null){
            System.out.println("empty");
            return;
        }
        while(curr != null){
            System.out.println(curr.key+ " ");
            curr = curr.next;
        }
    }






    public Node add(String num1, String num2) {//function to add two numbers in a dd linked list
        num1_list = new Node();
        num1_list.add
        for(int i = 0; i < num1.length(); i++){
            int c = num1.charAt(i);
           // num1_list.addNode(c);

        }
        int a =1;
        return new Node();
    }
}
