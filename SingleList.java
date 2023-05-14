package cp213;

/**
 * A single linked list structure of <code>Node T</code> objects. These data
 * objects must be Comparable - i.e. they must provide the compareTo method.
 * Only the <code>T</code> value contained in the priority queue is visible
 * through the standard priority queue methods. Extends the
 * <code>SingleLink</code> class.
 *
 * @param <T> this SingleList data type.
 */
public class SingleList<T extends Comparable<T>> extends SingleLink<T> {

    /**
     * Searches for the first occurrence of key in this SingleList. Private helper
     * methods - used only by other ADT methods.
     *
     * @param key The value to look for.
     * @return A pointer to the node previous to the node containing key.
     */
    private SingleNode<T> linearSearch(final T key) {

	SingleNode<T> previous = null;
	SingleNode<T> current = null;
	int index = 0;

	while ((current != null) && (current.getDatum() != key)) {
	    // print(current._value, end=", ")
	    previous = current;
	    current = current.getNext();
	    index += 1;
	}

	if (current == null) {
	    index = -1;
	}
	return previous;
    }

    /**
     * Appends data to the end of this SingleList.
     *
     * @param datum The value to append.
     */
    public void append(final T datum) {

	SingleNode node = new SingleNode(datum, null);

	if (this.front == null) {
	    // list is empty - update the front of the list.
	    this.front = node;
	} else {
	    this.rear.setNext(node);
	}

	// update the rear of the list
	this.rear = node;
	this.length += 1;

	return;
    }

    /**
     * Removes duplicates from this SingleList. The list contains one and only one
     * of each value formerly present in this SingleList. The first occurrence of
     * each value is preserved.
     */
    public void clean() {

	SingleNode<T> key_node = this.front;

	while (key_node != null) {
	    // loop through every node - compare each node with the rest
	    SingleNode<T> previous = key_node;
	    SingleNode<T> current = key_node.getNext();

	    while (current != null) {
		// always search to the end f the list (may have > 1 duplicate)
		if (current.getDatum() == key_node.getDatum()) {
		    // remove the current node by connecting the node before it to the node after it
		    this.length -= 1;
		    previous.setNext(current.getNext());
		} // end of if statement

		else {
		    previous = current;
		}
		// move to the next node
		current = current.getNext();
	    } // end of inner while loop

	    // update rear
	    this.rear = previous;

	    // check duplicates of the next remaining node in the list
	    key_node = key_node.getNext();
	}

	return;
    }

    /**
     * Combines contents of two lists into a third. Values are alternated from the
     * origin lists into this SingleList. The origin lists are empty when finished.
     * NOTE: data must not be moved, only nodes.
     *
     * @param left  The first list to combine with this SingleList.
     * @param right The second list to combine with this SingleList.
     */
    public void combine(final SingleList<T> left, final SingleList<T> right) {

	while ((left.front != null) && (right.front != null)) {
	    this.moveFrontToRear(left);
	    this.moveFrontToRear(right);
	}

	if (left.front != null) {
	    super.append(left);
	}

	if (right.front != null) {
	    super.append(right);
	}

	return;
    }

    /**
     * Determines if this SingleList contains key.
     *
     * @param key The key value to look for.
     * @return true if key is in this SingleList, false otherwise.
     */
    public boolean contains(final T key) {
	boolean found = true;
	SingleNode<T> previous = this.linearSearch(key);

	if ((previous == null) || (previous.getNext() == null)) {
	    found = false;
	}

	return found;
    }

    /**
     * Finds the number of times key appears in list.
     *
     * @param key The value to look for.
     * @return The number of times key appears in this SingleList.
     */
    public int count(final T key) {
	int number = 0;
	SingleNode<T> current = this.front;

	while (current != null) {
	    if (key == current.getDatum()) {
		number += 1;
	    }
	    current = current.getNext();
	}

	return number;
    }

    /**
     * Finds and returns the value in list that matches key.
     *
     * @param key The value to search for.
     * @return The value that matches key, null otherwise.
     */
    public T find(final T key) {

	SingleNode<T> previous = this.linearSearch(key);
	SingleNode<T> current = previous.getNext();
	T value = null;

	if (current != null) {
	    value = current.getDatum();
	}

	return value;
    }

    /**
     * Get the nth item in this SingleList.
     *
     * @param n The index of the item to return.
     * @return The nth item in this SingleList.
     * @throws ArrayIndexOutOfBoundsException if n is not a valid index.
     */
    public T get(final int n) throws ArrayIndexOutOfBoundsException {

	SingleNode<T> current = this.front;
	int x = n;
	int j = 0;

	// dealing with negative index values
	if (n < 0) {
	    // negative index - convert to positive
	    x = this.length + n;
	}

	// once you have proper value in positive, now you can loop
	while (j < x) {
	    current = current.getNext();
	    j += 1;
	}

	T value = current.getDatum();
	return value;
    }

    /**
     * Determines whether two lists are identical.
     *
     * @param source The list to compare against this SingleList.
     * @return true if this SingleList contains the same values in the same order as
     *         source, false otherwise.
     */
    public boolean identical(final SingleList<T> source) {

	boolean identical = true;

	if (this.length != source.length) {
	    identical = false;
	} else {
	    SingleNode<T> source_node = this.front;
	    SingleNode<T> target_node = source.front;

	    while ((source_node != null) && (source_node.getDatum() == target_node.getDatum())) {
		source_node = source_node.getNext();
		target_node = target_node.getNext();
	    }
	    identical = (source_node == null);

	} // end of else

	return identical;
    }

    /**
     * Finds the first location of a value by key in this SingleList.
     *
     * @param key The value to search for.
     * @return The index of key in this SingleList, -1 otherwise.
     */
    public int index(final T key) {

	SingleNode<T> previous = null;
	SingleNode<T> current = null;
	int index = 0;

	while ((current != null) && (current.getDatum() != key)) {
	    // print(current._value, end=", ")
	    previous = current;
	    current = current.getNext();
	    index += 1;
	}

	if (current == null) {
	    index = -1;
	}

	return index;
    }

    /**
     * Inserts value into this SingleList at index i. If i greater than the length
     * of this SingleList, append data to the end of this SingleList.
     *
     * @param i     The index to insert the new data at.
     * @param datum The new value to insert into this SingleList.
     */
    public void insert(int i, final T datum) {

	// negative index adjustment
	if (i < 0) {
	    i = this.length + i;
	}

	if (i <= 0) {
	    // add value to the front of the list
	    SingleNode node = new SingleNode(datum, this.front);

	    if (this.rear == null) {
		// list is empty - update the rear of the list...
		this.rear = node;
	    }
	    // update the front of the list
	    this.front = node;
	} // end of outer if
	else if (i >= this.length) {
	    // add value to the rear of the list
	    SingleNode node = new SingleNode(datum, null);

	    if (this.front == null) {
		// list is empty - update the front of the list
		this.front = node;
	    } else {
		this.rear.setNext(node);
	    }
	    // update the rear of the list
	    this.rear = node;
	} // end of else if
	else {
	    // add elsewhere in the list - not to front or rear
	    int j = 0;
	    SingleNode<T> previous = null;
	    SingleNode<T> current = this.front;

	    while (j < i) {
		// find proper location in the list
		previous = current;
		current = current.getNext();
		j += 1;
	    } // end of while

	    // create the new node
	    SingleNode node = new SingleNode(datum, current);
	    previous.setNext(node);
	} // end of else

	// increment the count
	this.length += 1;
	return;
    }

    /**
     * Creates an intersection of two other SingleLists into this SingleList. Copies
     * data to this SingleList. left and right SingleLists are unchanged. Values
     * from left are copied in order first, then values from right are copied in
     * order.
     *
     * @param left  The first SingleList to create an intersection from.
     * @param right The second SingleList to create an intersection from.
     */
    public void intersection(final SingleList<T> left, final SingleList<T> right) {

	SingleNode<T> left_node = left.front;

	while (left_node != null) {
	    T value = left_node.getDatum();

	    SingleNode<T> previous = right.linearSearch(value);
	    SingleNode<T> current = previous.getNext();

	    if (current != null) {
		// value exists in both source lists.
		SingleNode<T> previous2 = this.linearSearch(value);
		SingleNode<T> current2 = previous.getNext();

		if (current2 == null) {
		    // value does not exists in target list
		    this.append(value);
		} // end of inner if
	    } // end of outer if

	    left_node = left_node.getNext();

	} // end of while loop

	return;
    }

    /**
     * Finds the maximum value in this SingleList.
     *
     * @return The maximum value.
     */
    public T max() {
	assert this.front != null : "Cannot find maximum of an empty list";

	SingleNode<T> max_node = this.front;
	SingleNode<T> current = this.front.getNext();

	while (current != null) {
	    int compVal = max_node.getDatum().compareTo(current.getDatum());
	    if (compVal < 0) {
		max_node = current;
	    }
	    current = current.getNext();
	}

	T max_data = max_node.getDatum();

	return max_data;
    }

    /**
     * Finds the minimum value in this SingleList.
     *
     * @return The minimum value.
     */
    public T min() {

	assert this.front != null : "Cannot find minimum of an empty list";

	SingleNode<T> min_node = this.front;
	SingleNode<T> current = this.front.getNext();

	while (current != null) {
	    int compVal = min_node.getDatum().compareTo(current.getDatum());
	    if (compVal > 0) {
		min_node = current;
	    }
	    current = current.getNext();
	}

	T min_data = min_node.getDatum();

	return min_data;
    }

    /**
     * Inserts value into the front of this SingleList.
     *
     * @param datum The value to insert into the front of this SingleList.
     */
    public void prepend(final T datum) {

	SingleNode node = new SingleNode(datum, this.front);

	if (this.rear == null) {
	    // list is empty - update the rear of the list
	    this.rear = node;
	}

	// update front of the list
	this.front = node;
	this.length += 1;

	return;
    }

    /**
     * Finds, removes, and returns the value in this SingleList that matches key.
     *
     * @param key The value to search for.
     * @return The value matching key, null otherwise.
     */
    public T remove(final T key) {
	// search list for key
	SingleNode<T> previous = this.linearSearch(key);
	SingleNode<T> current = previous.getNext();
	T value = null;

	if (current == null) {
	    // key is not found
	    value = null;
	} else {
	    value = current.getDatum();
	    this.length -= 1;

	    if (previous == null) {
		// remove the firts node
		this.front = this.front.getNext();

		if (this.front == null) {
		    // lis is empty, update rear
		    this.rear = null;
		} // end of most inner if
	    } else {
		// remove any other node
		previous.setNext(current.getNext());

		if (previous.getNext() == null) {
		    // last node was removed, update rear
		    this.rear = previous;
		}
	    }

	}

	return value;
    }

    /**
     * Removes the value at the front of this SingleList.
     *
     * @return The value at the front of this SingleList.
     */
    public T removeFront() {
	assert this.front != null : "Cannot remove from an empty list";

	T value = this.front.getDatum();
	this.front = this.front.getNext();
	this.length -= 1;

	if (this.front == null) {
	    // last node has been removed
	    this.rear = null;
	}

	return value;
    }

    /**
     * Finds and removes all values in this SingleList that match key.
     *
     * @param key The value to search for.
     */
    public void removeMany(final T key) {

	while ((this.front != null) && (this.front.getDatum() == key)) {
	    // the front node contains the value to be removed
	    this.front = this.front.getNext();
	    this.length -= 1;
	}

	if (this.front == null) {
	    // all nodes have been removed
	    this.front = null;
	    this.rear = null;
	    this.length = 0;
	} else {
	    // remove key from the rest of the list
	    SingleNode<T> previous = this.front;
	    SingleNode<T> current = this.front.getNext();

	    while (current != null) {
		if (current.getDatum() == key) {
		    // do not update previous
		    this.length -= 1;
		    previous.setNext(current.getNext());
		} else {
		    previous = current;
		}
		current = current.getNext();
	    }

	    // update the rear node
	    this.rear = previous;
	}

	return;
    }

    /**
     * Reverses the order of the values in this SingleList.
     */
    public void reverse() {

	this.rear = this.front;
	SingleNode<T> current = this.front;
	this.front = null;

	while (current != null) {
	    SingleNode<T> temp = current.getNext();
	    current.setNext(this.front);
	    this.front = current;
	    current = temp;

	}

	return;
    }

    /**
     * Splits the contents of this SingleList into the left and right SingleLists.
     * Moves nodes only - does not move value or call the high-level methods insert
     * or remove. this SingleList is empty when done. The first half of this
     * SingleList is moved to left, and the last half of this SingleList is moved to
     * right. If the resulting lengths are not the same, left should have one more
     * item than right. Order is preserved.
     *
     * @param left  The first SingleList to move nodes to.
     * @param right The second SingleList to move nodes to.
     */
    public void split(final SingleList<T> left, final SingleList<T> right) {

	// java naturally rounds down
	int middle = this.length / (2 + this.length % 2);
	SingleNode<T> prev = null;
	SingleNode<T> curr = this.front;

	for (int i = 0; i < middle; i++) {
	    prev = curr;
	    curr = curr.getNext();
	}

	if (prev != null) {
	    // break the source list between prev and curr
	    prev.setNext(null);
	}

	// add to left
	left.length += middle;
	left.front = this.front;
	left.rear = prev;

	// add to right
	right.length += this.length - middle;
	right.front = curr;

	if (right.length > 0) {
	    right.rear = this.rear;
	}

	// clean up source
	this.front = null;
	this.rear = null;
	this.length = 0;

	return;
    }

    /**
     * Splits the contents of this SingleList into the left and right SingleLists.
     * Moves nodes only - does not move value or call the high-level methods insert
     * or remove. this SingleList is empty when done. Nodes are moved alternately
     * from this SingleList to left and right. Order is preserved.
     *
     * @param left  The first SingleList to move nodes to.
     * @param right The second SingleList to move nodes to.
     */
    public void splitAlternate(final SingleList<T> left, final SingleList<T> right) {

	boolean leftSplitSide = true;

	while (this.front != null) {
	    if (leftSplitSide) {
		left.moveFrontToRear(this);
	    } else {
		right.moveFrontToRear(this);
	    }

	    leftSplitSide = !leftSplitSide;
	}

	return;
    }

    /**
     * Creates a union of two other SingleLists into this SingleList. Copies value
     * to this list. left and right SingleLists are unchanged. Values from left are
     * copied in order first, then values from right are copied in order.
     *
     * @param left  The first SingleList to create a union from.
     * @param right The second SingleList to create a union from.
     */
    public void union(final SingleList<T> left, final SingleList<T> right) {
	assert this.front == null : "target list must be empty";

	SingleNode<T> left_node = left.front;

	while (left_node != null) {
	    T value = left_node.getDatum();
	    SingleNode<T> previous = this.linearSearch(value);
	    SingleNode<T> current = previous.getNext();

	    if (current == null) {
		// value does not exist in the new list
		this.append(value);
	    }
	    left_node = left_node.getNext();

	}

	SingleNode<T> right_node = right.front;

	while (right_node != null) {
	    T value = right_node.getDatum();
	    SingleNode<T> previous = this.linearSearch(value);
	    SingleNode<T> current = previous.getNext();

	    if (current == null) {
		// value does not exist in the new list
		this.append(value);
	    }
	    right_node = right_node.getNext();

	}

	return;
    }
}
