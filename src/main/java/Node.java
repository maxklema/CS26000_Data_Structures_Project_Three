/*
 * NAME: Maxwell Klema
 * PROJECT: Project Three
 * COURSE: CS26000
 * INSTRUCTOR: KIM, B.
 * LAB TIME: N/A
 * DUE DATE: 11/14/24
 *
 * DESCRIPTION
 *  A node object represent a single link in a linked list. These nodes help build the queue and set classes.
 *
 * CONSTRUCTOR METHODS
 *	Node
 *	    Creates a new node object by taking in an element of a generic type, and a Node<E> link object.
 *
 * INSTANCE AND DATA VARIABLES
 * 	element
 * 		An element that is stored in the node.
 *  link
 *      A Node<E> object that represent the current node's next link.
 *
 * INSTANCE METHODS
 * 	addNodeAfter
 * 		Adds a new Node<E> object after the current node.
 *  getElement
 * 	    Returns the element that is attached to this node.
 *  getLink
 * 		Returns the Node<E> object that is after the current node.
 *  setLink
 *      Sets the node's link to a new Node<E> object.
 *  setElement
 *      Sets the node's element to a new element.
 */

public class Node<E> {

    private E element; // An element that is stored in the node
    private Node<E> link; // A Node<E> object that represents the current node's next link

    //
    // Creates a new node object by taking in an element of a generic type, and a Node<E> link object
    //
    public Node(E element, Node<E> link) {
        this.element = element;
        this.link = link;
    } //end Node

    //
    // Adds a new Node<E> object after the current node
    //
    public void addNodeAfter(E element) {
        this.link = new Node<E>(element, this.link);
    } //end addNodeAfter

    //
    // Returns the element that is attached to this node
    //
    public E getElement() {
        return element;
    } //end getElement

    //
    // Returns the Node<E> object that is after the current node
    //
    public Node<E> getLink() {
        return link;
    } //end getLink

    //
    // Sets the node's link to a new Node<E> object
    //
    public void setLink(Node<E> link) {
        this.link = link;
    } //end setLink

    //
    // Sets the node's element to a new element
    //
    public void setElement(E element) {
        this.element = element;
    } //end setElement
} //end Node

