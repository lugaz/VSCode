class MysteryX {
    class Node {
        int value;
        Node next, prev;

        public Node(int value) {
            this.value = value;
            this.next = this.prev = null;
        }
    }

    Node front, rear;

    public MysteryX() {
        front = rear = null;
    }

    public void insertFront(int value) {
        Node newNode = new Node(value);
        if (front == null) {
            front = rear = newNode;
            front.next = front.prev = front; // Circular link
        } else {
            newNode.next = front;
            newNode.prev = rear;
            front.prev = newNode;
            rear.next = newNode;
            front = newNode;
        }
    }

    public void insertRear(int value) {
        Node newNode = new Node(value);
        if (rear == null) {
            front = rear = newNode;
            front.next = front.prev = front; // Circular link
        } else {
            newNode.next = front;
            newNode.prev = rear;
            front.prev = newNode;
            rear.next = newNode;
            rear = newNode;
        }
    }

    public int deleteFront() {
        if (front == null) {
            return -1; // Empty list
        } else if (front == rear) {
            int value = front.value;
            front = rear = null;
            return value;
        } else {
            int value = front.value;
            front = front.next;
            front.prev = rear;
            rear.next = front;
            return value;
        }
    }

    public int deleteRear() {
        if (rear == null) {
            return -1; // Empty list
        } else if (front == rear) {
            int value = rear.value;
            front = rear = null;
            return value;
        } else {
            int value = rear.value;
            rear = rear.prev;
            rear.next = front;
            front.prev = rear;
            return value;
        }
    }

    public int getFront() {
        return (front != null) ? front.value : -1;
    }

    public int getRear() {
        return (rear != null) ? rear.value : -1;
    }
}
