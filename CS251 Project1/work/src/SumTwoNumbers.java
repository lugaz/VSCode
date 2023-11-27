public class SumTwoNumbers {
    public class Node {
        int key;
        Node previous, next;

        public Node(int key) {
            this.key = key;
        }
    }

    Node num1_list = null;
    Node num2_list = null;
    private Node construct(String num){
        Node temp = new Node(-1);
        Node curr = temp;
        for (int i = 0; i< num.length(); i++){
           int digit = Character.getNumericValue(num.charAt(i));
           curr.next = new Node(digit);
           curr.next.previous = curr;
           curr = curr.next;
        }
        return temp.next;
    }
    // Add a node to the doubly-linked list
    public void addNode(Node list, int key) {
        Node temp = new Node(key);
        if (list == null) {
            list = temp;
            list.previous = null;
            list.next = null;
        } else {
            Node tail = list;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = temp;
            temp.previous = tail;
            temp.next = null;
        }
    }

    // Add a number represented as a string to the doubly-linked list
    public Node addNumberToList(String num, Node list) {
        for (int i = num.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(num.charAt(i));
            addNode(list, digit);
        }
        return list;
    }

    private Node calc(Node num1,Node num2){
        Node temp = new Node(-1);
        Node curr = temp;
        int carry =0;
        while (true){
            if(num1 == null && num2 == null && carry == 0){
                break;
            }
            int sum = carry;
            if (num1 != null){
                sum += num1.key;
                num1 = num1.next;
            }
            if (num2 != null){
                sum += num2.key;
                num2 = num2.next;
            }
            carry = sum/10;
            curr.next = new Node(sum%10);
            curr = curr.next;
            
        }
        return temp.next;
    }

    public Node add(String num1, String num2) {
        num1 = new StringBuilder(num1).reverse().toString();
        num2 = new StringBuilder(num2).reverse().toString();
        num1_list = construct(num1);
        num2_list = construct(num2);

        Node result = calc(num1_list, num2_list);
        //printList(num1_list);
    
        // Initialize carry and result lists
        num1_list = reverse(num1_list);
        num2_list = reverse(num2_list);
        result = reverse(result);
        result = convertSinglNode(result);
        

        // Traverse both linked lists from right to left and add digits
        

        return result;
    }

    private Node reverse(Node head){
        Node prev = null;
        Node curr = head;
        Node next;
        while (true){
            if(curr == null){
                break;
            }
            next = curr.next;
            curr.next = prev;
            curr.previous = next;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static Node convertSinglNode(Node head){
        Node curr = head;
        while (curr != null){
            curr.previous = null;
            curr = curr.next;
        }
        return head;
    }

    public void printList(Node list) {
        Node current = list;
        while (current != null) {
            System.out.print(current.key + " ");
            current = current.next;
        }
        System.out.println(); // Move to the next line after printing the list
    }

}
