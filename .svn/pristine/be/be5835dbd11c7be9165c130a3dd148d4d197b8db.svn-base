package edu.uprm.ece.icom4035.mygrades.managers;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SortedArrayList is an implementation of SortedList that keeps the elements in
 * the list sorted in increasing order.
 * 
 * <h4>Description</h4>
 * 
 * This implementation is essentially similar to the implementation of a
 * LinkedList. The only difference lies within the <strong>add()</strong> method,
 * which stores every added object sorted in increasing order. Being an
 * implementation of the SortedList, it provides all of the methods established
 * by this ADT, such as iteration capabilities.
 * 
 * <h4>Notes</h4>
 * 
 * The user of this class needs to be careful when modifying items already added
 * to an instance of an SortedArrayList, since it only sorts when new items are
 * added into the list itself.
 * 
 * @author Lixhjideny Mendez Rios
 * 
 * @version 1.5
 * 
 */
public class SortedDoublyList<E extends Comparable<E>> implements SortedList<E> {

	private class Node {
		private E value;
		private Node next;
		private Node prev;

		public E getValue() {
			return value;
		}

		public void setValue(E value) {
			this.value = value;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public Node getPrev() {
			return prev;
		}

		public void setPrev(Node prev) {
			this.prev = prev;
		}
	}

	private class ListIterator implements Iterator<E> {

		private Node nextNode;

		public ListIterator() {
			this.nextNode = header.getNext();
		}

		public ListIterator(int index) {
			if ((index < 0) || (index >= size())) {
				throw new IndexOutOfBoundsException();
			} else {
				int counter = 0;
				Node temp = null;
				for (temp = header.getNext(); counter < index; temp = temp
						.getNext(), counter++)
					;
				this.nextNode = temp;
			}
		}

		@Override
		public boolean hasNext() {
			return nextNode != tail;
		}

		@Override
		public E next() {
			if (hasNext()) {
				E result = this.nextNode.getValue();
				this.nextNode = this.nextNode.getNext();
				return result;
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	private class ReverseListIterator implements ReverseIterator<E> {

		private Node prevNode;

		public ReverseListIterator() {
			this.prevNode = tail.getPrev();
		}

		public ReverseListIterator(int index) {
			if ((index < 0) || (index >= size())) {
				throw new IndexOutOfBoundsException();
			} else {
				int counter = 0;
				Node temp = null;
				for (temp = header.getNext(); counter < index; temp = temp
						.getNext(), counter++)
					;
				this.prevNode = temp;
			}
		}

		@Override
		public boolean hasPrev() {
			return prevNode != header;
		}

		@Override
		public E prev() {
			if (hasPrev()) {
				E result = prevNode.getValue();
				prevNode = prevNode.getPrev();
				return result;
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	private Node header;
	private Node tail;
	private int currentSize;

	/**
	 * Creates a new instance of a SortedArrayList with references to it's
	 * header and tail elements.
	 */
	public SortedDoublyList() {
		this.header = new Node();
		this.tail = new Node();

		this.header.setNext(tail);
		this.tail.setPrev(header);

		this.currentSize = 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new ListIterator();
	}

	public Iterator<E> iterator(int index) {
		return new ListIterator(index);
	}

	public ReverseIterator<E> reverseIterator() {
		return new ReverseListIterator();
	}

	public ReverseIterator<E> reverseIterator(int index) {
		return new ReverseListIterator(index);
	}

	/**
	 * Adds a new element to the list, sorted in increasing order. If an
	 * element(s) with the same properties already exists in the list, the new
	 * element is added after the element(s) with the same properties.
	 * 
	 * @param obj
	 *            Reference to the new element.
	 * 
	 * @return A boolean value indicating if the operation was successful.
	 **/
	@Override
	public boolean add(E obj) {
		if (obj == null) {
			throw new IllegalArgumentException("Parameter cannot be null.");
		} else {
			Node temp = header;
			if (!this.isEmpty()) {
				// if not empty, loop through all the elements in the DLList
				for (temp = header.getNext(); temp.getNext() != null; temp = temp
						.getNext()) {
					// break when the element to be added is smaller than the
					// element in the current node
					if (obj.compareTo(temp.getValue()) < 0) {
						break;
					}
				}
				// reduce temp by one since the loop breaks with the bigger
				// value
				temp = temp.getPrev();
			}

			// newNode setup
			Node newNode = new Node();
			newNode.setValue(obj);
			newNode.setNext(temp.getNext());
			newNode.setPrev(temp);

			// Insert the newNode in the list
			temp.getNext().setPrev(newNode);
			temp.setNext(newNode);
			this.currentSize++;
			return true;
		}
	}

	@Override
	public boolean set(int index, E obj) {
		if ((index < 0) || (index >= this.size())) {
			throw new IndexOutOfBoundsException();
		} else if (obj == null) {
			throw new IllegalArgumentException("Parameter cannot be null.");
		} else {
			remove(index);
			add(obj);
			return true;
		}
	}

	@Override
	public boolean remove(E obj) {
		if (obj == null) {
			throw new IllegalArgumentException("Parameter cannot be null.");
		}
		Node temp = null;
		for (temp = header.getNext(); temp != tail; temp = temp.getNext()) {
			if (temp.getValue().equals(obj)) {
				// found first copy
				if (temp.getNext() != null) {
					temp.getNext().setPrev(temp.getPrev());
				}
				temp.getPrev().setNext(temp.getNext());
				temp.setValue(null);
				temp.setNext(null);
				temp.setPrev(null);
				this.currentSize--;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean remove(int index) {
		if ((index < 0) || (index >= this.size())) {
			throw new IndexOutOfBoundsException();
		}
		Node temp = null;
		int counter = 0;
		for (temp = header.getNext(); counter < index; temp = temp.getNext(), counter++)
			;
		if (temp.getNext() != null) {
			// i am not at the end of list
			temp.getNext().setPrev(temp.getPrev()); // null if temp is the last
													// one
		}
		temp.getPrev().setNext(temp.getNext());
		temp.setValue(null);
		temp.setNext(null);
		temp.setPrev(null);
		this.currentSize--;
		return true;
	}

	@Override
	public int removeAll(E obj) {
		int amountRemoved = 0;
		while (this.remove(obj)) {
			amountRemoved++;
		}
		return amountRemoved;
	}

	@Override
	public E get(int index) {
		if ((index < 0) || (index >= this.size())) {
			throw new IndexOutOfBoundsException();
		} else {
			int counter = 0;
			Node temp = null;
			for (temp = header.getNext(); counter < index; temp = temp
					.getNext(), counter++)
				;
			return temp.getValue();
		}
	}

	@Override
	public E first() {
		if (this.isEmpty()) {
			return null;
		} else {
			return header.getNext().getValue();
		}
	}

	@Override
	public E last() {
		if (this.isEmpty()) {
			return null;
		} else {
			return tail.getPrev().getValue();
		}
	}

	@Override
	public int firstIndex(E obj) {
		if (obj == null) {
			throw new IllegalArgumentException("Parameter cannot be null.");
		} else {
			int counter = 0;
			Node temp = null;
			for (temp = header.getNext(); temp != tail; temp = temp.getNext(), counter++) {
				if (temp.getValue().equals(obj)) {
					return counter;
				}
			}
			return -1;
		}
	}

	@Override
	public int lastIndex(E obj) {
		if (obj == null) {
			throw new IllegalArgumentException("Parameter cannot be null.");
		} else {
			int counter = 0, lastSeen = -1;
			Node temp = null;
			for (temp = header.getNext(); temp != tail; temp = temp.getNext(), counter++) {
				if (temp.getValue().equals(obj)) {
					lastSeen = counter;
				}
			}
			return lastSeen;
		}
	}

	@Override
	public int size() {
		return this.currentSize;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public boolean contains(E obj) {
		return this.firstIndex(obj) >= 0;
	}

	@Override
	public void clear() {
		while (!this.isEmpty()) {
			this.remove(0);
		}
	}
}
