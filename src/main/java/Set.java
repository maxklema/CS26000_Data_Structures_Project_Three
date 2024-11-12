public class Set<E> {

    private Node<E> front;
    private int manyItems;

    public Set() {
        front = null;
        manyItems = 0;
    }

    public void enter(E item){
        if (!isElement(item)){
            front = new Node<E>(item, front);
            manyItems++;
        }
    }

    public boolean isElement(E item){
        Node<E> cursor;
        for (cursor = front; cursor != null; cursor = cursor.getLink()){
            if (cursor.getElement().equals(item)){
                return true;
            }
        }
        return false;
    }

    public Node<E> getFront(){
        return front;
    }

    public void setFront(Node<E> front){
        this.front = front;
    }

    public int size(){
        return manyItems;
    }

    //for testing
    public void printSet(){
        Node<E> cursor;
        for (cursor = front; cursor != null; cursor = cursor.getLink()){
            System.out.println(cursor.getElement());
        }
    }

    //clone method

}
