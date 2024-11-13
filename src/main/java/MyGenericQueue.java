/*
 * NAME: Maxwell Klema
 * PROJECT: Project Three
 * COURSE: CS26000
 * INSTRUCTOR: KIM, B.
 * LAB TIME: N/A
 * DUE DATE: 11/14/24
 *
 * DESCRIPTION
 *  This queue class represents a circular queue where the rear points back to the front. It is used in the path finding algorithm.
 *
 * CONSTRUCTOR METHODS
 *	MyGeneric Queue
 *		Sets manyItems to 0, and initializes the front and rear node to null.
 *
 * INSTANCE AND DATA VARIABLES
 * 	manyItems
 * 		An integer representing the number of elements in the queue.
 *  front
 *      A node that represents the front of the queue.
 *  rear
 *      A node that represents the back of the queue.
 *
 * INSTANCE METHODS
 * 	enqueue
 * 		Places a new element node at the end of the queue.
 *  dequeue
 * 		Removes the oldest element from the queue and returns that element.
 *  size
 * 		Returns the size of the queue (manyItems).
 *  isEmpty
 *      Returns a boolean value based upon if the queue is empty or not.
 *  getFront
 *      Returns the front node in the queue.
 *  getRear
 *      Returns the rear node in the queue.
 */

import java.util.NoSuchElementException;

public class MyGenericQueue<E> {

    private int manyItems; // An integer representing the number of elements in the queue
    private Node<E> front; // A node that represents the front of the queue
    private Node<E> rear; // A node that represents the back of the queue

    //
    // Sets manyItems to 0 and initializes the front and rear nodes to null
    //
    public MyGenericQueue() {
        this.manyItems = 0;
        this.front = null;
        this.rear = null;
    } //end MyGenericQueue

    //
    // Places a new element node at the end of the queue
    //
    public void enqueue(E item) {
        if (isEmpty()) {
            front = new Node<E>(item, front);
            front.setLink(front);
            rear = front;
        } else {
            rear.addNodeAfter(item);
            rear = rear.getLink();
        }
        manyItems++;
    } //end enqueue

    //
    // Removes the oldest element from the queue and returns that element
    //
    public E dequeue() {
        E item;
        if (isEmpty()) {
            throw new NoSuchElementException("Queue Underflow");
        }
        item = front.getElement();
        front = front.getLink();
        manyItems--;
        if (manyItems == 0) {
            rear = null;
        }
        return item;
    } //end dequeue

    //
    // Returns the size of the queue (manyItems)
    //
    public int size() {
        return manyItems;
    } //end size

    //
    // Returns a boolean value based on whether the queue is empty
    //
    public boolean isEmpty() {
        return manyItems == 0;
    } //end isEmpty

    //
    // Returns the front node in the queue
    //
    public Node<E> getFront() {
        return front;
    } //end getFront

    //
    // Returns the rear node in the queue
    //
    public Node<E> getRear() {
        return rear;
    } //end getRear
} //end MyGenericQueue