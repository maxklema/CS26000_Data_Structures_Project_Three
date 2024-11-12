import java.util.NoSuchElementException;

public class MyGenericQueue<E> {

    private int manyItems;
    private Node<E> front;
    private Node<E> rear;

    public MyGenericQueue() {
        this.manyItems = 0;
        this.front = null;
        this.rear = null;
    }

    public void enqueue(E item){
        if (isEmpty()){
            front = new Node<E>(item, front);
            front.setLink(front);
            rear = front;
        } else {
            rear.addNodeAfter(item);
            rear = rear.getLink();
        }
        manyItems++;
    }

    public E dequeue(){
        E item;
        if (isEmpty()){
            throw new NoSuchElementException("Queue Underflow");
        }
        item = front.getElement();
        front = front.getLink();
        manyItems--;
        if (manyItems == 0){
            rear = null;
        }
        return item;
    }

    public int size(){
        return manyItems;
    }

    public boolean isEmpty(){
        return manyItems == 0;
    }

    public Node<E> getFront(){
        return front;
    }

    public Node<E> getRear(){
        return rear;
    }

    public void setFront(Node<E> front){
        this.front = front;
    }

    //for testing
    public void printQueue(){
        Node<E> cursor = front;
        for (int i = 0; i < manyItems; cursor = cursor.getLink(), i++){
            System.out.println(cursor.getElement());
        }
    }

    //clone method
}