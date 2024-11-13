/*
 * NAME: Maxwell Klema
 * PROJECT: Project Three
 * COURSE: CS26000
 * INSTRUCTOR: KIM, B.
 * LAB TIME: N/A
 * DUE DATE: 11/14/24
 *
 * DESCRIPTION
 *  A set is like a bag, except duplicate values are not allowed. It is used for the pathfinding algorithm.
 *
 * CONSTRUCTOR METHODS
 *	Set
 *		sets the first node, front, to null and manyItems to 0.
 *
 * INSTANCE AND DATA VARIABLES
 * 	front
 * 		A Node<E> object that represents the front of the set, which is built on a linked list.
 *  manyItems
 *      An integer representing the number of elements in the set.
 *
 * INSTANCE METHODS
 * 	enter
 * 		Places a new element into the set if and only if the same value does not already exist.
 *  isElement
 * 		Checks if an input argument is present in the set.
 *  getFront
 * 		Returns the front node of the set.
 *  setFront
 *      sets the front node of the set.
 *  size
 *      Returns the size (manyItems) of the set.
 */

public class Set<E> {

    private Node<E> front; // A Node<E> object that represents the front of the set, based on a linked list
    private int manyItems; // Integer representing the number of elements in the set

    //
    // Sets the first node, front, to null and manyItems to 0
    //
    public Set() {
        front = null;
        manyItems = 0;
    } //end Set

    //
    // Places a new element into the set if it does not already exist
    //
    public void enter(E item) {
        if (!isElement(item)) {
            front = new Node<E>(item, front);
            manyItems++;
        }
    } //end enter

    //
    // Checks if an input argument is present in the set
    //
    public boolean isElement(E item) {
        Node<E> cursor;
        for (cursor = front; cursor != null; cursor = cursor.getLink()) {
            if (cursor.getElement().equals(item)) {
                return true;
            }
        }
        return false;
    } //end isElement

    //
    // Returns the front node of the set
    //
    public Node<E> getFront() {
        return front;
    } //end getFront

    //
    // Sets the front node of the set
    //
    public void setFront(Node<E> front) {
        this.front = front;
    } //end setFront

    //
    // Returns the size (manyItems) of the set
    //
    public int size() {
        return manyItems;
    } //end size
} //end Set
