package cp213;

/**
 * A single linked priority queue structure of <code>Node T</code> objects.
 * These data objects must be Comparable - i.e. they must provide the compareTo
 * method. Only the <code>T</code> data contained in the priority queue is
 * visible through the standard priority queue methods. Extends the
 * <code>SingleLink</code> class.
 *
 * @param <T> the SinglePriorityQueue data type.
 */
public class SinglePriorityQueue<T extends Comparable<T>> extends SingleLink<T> {

    /**
     * Combines the contents of the left and right SinglePriorityQueues into the
     * current SinglePriorityQueue. Moves nodes only - does not move value or call
     * the high-level methods insert or remove. left and right SinglePriorityQueues
     * are empty when done. Nodes are moved alternately from left and right to this
     * SinglePriorityQueue. When finished all nodes must be in priority order from
     * front to rear.
     *
     * Do not use the SinglePriorityQueue insert and remove methods.
     *
     * Do not assume that both right and left priority queues are of the same
     * length.
     *
     * @param left  The first SinglePriorityQueue to extract nodes from.
     * @param right The second SinglePriorityQueue to extract nodes from.
     */
    public void combine(final SinglePriorityQueue<T> left, final SinglePriorityQueue<T> right) {
	assert this.front == null : "May combine into an empty Priority Queue only";

	// could possible be approved by a lot
	while ((left.length > 0) || (right.length > 0)) {
	    if (left.length > 0) {

		T valLeft = left.front.getDatum();
		T valRight = right.front.getDatum();
		int comparison = valLeft.compareTo(valRight);

		if ((right.length == 0) || (comparison <= 0)) {
		    this.moveFrontToRear(left);
		} else {
		    this.moveFrontToRear(right);
		}
	    } // end of outer if
	    else if (right.length > 0) {
		this.moveFrontToRear(right);
	    }
	}

	return;
    }

    /**
     * Adds value to the SinglePriorityQueue. Data is stored in priority order, with
     * highest priority value at the front of the SinglePriorityQueue, and lowest at
     * the rear. Priority is determined by simple comparison - lower values have
     * higher priority. For example, 1 has a higher priority than 2 because 1 is a
     * lower value than 2. (Think of the phrase, "We're number one!" as an
     * indication of priority.)
     *
     * When inserting value to the priority queue, the queue must remain sorted.
     * Hence you need to find the proper location of inserting value. use the head
     * pointer to go through the queue. e.g., use SingleNode&lt;T&gt; current =
     * this.head;
     *
     * use current = current.getNext(); to traverse the queue.
     *
     * To get access to the value inside a node of queue use current.getValue().
     *
     * @param datum value to insert in sorted order in priority queue.
     */
    public void insert(final T datum) {

	if (this.front == null) {// priority queue is empty
	    SingleNode node = new SingleNode(datum, null);
	    this.front = node;
	    this.rear = node;
	} else {
	    int comparison1 = datum.compareTo(this.front.getDatum());
	    int comparison2 = datum.compareTo(this.rear.getDatum());

	    if (comparison1 < 0) {// new value has highest priority
		SingleNode node = new SingleNode(datum, this.front);
		this.front = node;
	    } else if (comparison2 >= 0) {// new value has lowest priority
		SingleNode node = new SingleNode(datum, null);
		this.rear.setNext(node);
		this.rear = node;
	    } else {
		// find the proper position for value
		SingleNode<T> prev = null;
		SingleNode<T> curr = this.front;

		int comparison3 = datum.compareTo(curr.getDatum());

		while (comparison3 >= 0) {
		    prev = curr;
		    if (curr != null) {
			curr = curr.getNext();
		    }
		}

		// create the new node and link it to curr
		SingleNode node = new SingleNode(datum, curr);
		// the previous node is linked to the new node
		prev.setNext(node);
	    }
	}

	// increment the priority queue size
	this.length += 1;

	return;
    }

    /**
     * Returns the highest priority value in the SinglePriorityQueue. Decrements the
     * count.
     *
     * @return the highest priority value currently in the SinglePriorityQueue.
     */
    public T remove() {
	assert this.length > 0 : "Cannot remove from an empty priority queue";

	SingleNode<T> remove_node = this.front;

	if (this.front == this.rear) {
	    this.rear = null;
	}

	this.front = this.front.getNext();
	this.length -= 1;

	return remove_node.getDatum();
    }

    /**
     * Splits the contents of this SinglePriorityQueue into the higher and lower
     * SinglePriorityQueue. Moves nodes only - does not move value or call the
     * high-level methods insert or remove. this SinglePriorityQueue is empty when
     * done. Nodes with priority value higher than key are moved to the
     * SinglePriorityQueue higher. Nodes with a priority value lower than or equal
     * to key are moved to the SinglePriorityQueue lower.
     *
     * Do not use the SinglePriorityQueue insert and remove methods.
     *
     * @param key    value to compare against node values in SinglePriorityQueue
     * @param higher an initially empty SinglePriorityQueue queue that ends up with
     *               all values with priority higher than key.
     * @param lower  an initially empty SinglePriorityQueue queue that ends up with
     *               all values with priority lower than or equal to key.
     */
    public void splitByKey(final T key, final SinglePriorityQueue<T> higher, final SinglePriorityQueue<T> lower) {

	while (this.length > 0) {
	    int comparison = this.front.getDatum().compareTo(key);
	    if (comparison < 0) {
		higher.moveFrontToRear(this);
	    } else {
		lower.moveFrontToRear(this);
	    }
	} // end of while loop

	if (this.length == 0) {
	    this.rear = null;
	}

	return;
    }
}
