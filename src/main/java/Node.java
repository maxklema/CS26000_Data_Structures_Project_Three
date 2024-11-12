public class Node<E> {

    private E element;
    private Node<E> link;

    public Node(E element, Node<E> link){
        this.element = element;
        this.link = link;
    }

    public void addNodeAfter(E element){
        this.link = new Node<E>(element, this.link);
    }

    public E getElement(){
        return element;
    }

    public Node<E> getLink(){
        return link;
    }

    public void setLink(Node<E> link){
        this.link = link;
    }

    public void setElement(E element){
        this.element = element;
    }
}
