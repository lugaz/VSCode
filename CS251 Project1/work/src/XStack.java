public class XStack {
    private MysteryX stack;

    public XStack() {
        stack = new MysteryX();
    }

    public void push(int value) {
        stack.insertRear(value);
    }

    public int pop() {
        return stack.deleteRear();
    }

    public int peek() {
            return stack.getRear();
    }
}