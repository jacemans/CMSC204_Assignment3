import java.util.ArrayList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
/**
 * @author Justin Hixson
 *
 * @param <T> data element type
 */
public class BasicDoubleLinkedList<T> implements Iterable<T> {

	protected class Node {
		protected T data;
		protected Node next, previous;

		protected Node(T data, Node next, Node previous) {
			this.data = data;
			this.next = next;
			this.previous = previous;
		}
	}

	protected int size;
	protected Node head, tail;

	public BasicDoubleLinkedList() {
		size = 0;
		head = tail = null;
	}
	/**
	 * This method returns the value of the instance variable used to keep track of size.
	 * @return the size of the linked list
	 */
	public int getSize() {
		return size;
	}
	/**
	 * Adds an element to the end of the list.
	 * @param data the data for the Node within the linked list
	 * @return reference to the current object
	 */
	public BasicDoubleLinkedList<T> addToEnd(T data) {
		Node node = new Node(data, null, tail);
		if (tail != null) {
			tail.next = node;
		}
		tail = node;

		if (head == null) {
			head = node;
		}

		size++;
		return this;
	}
	/**
	 * Adds element to the front of the list.
	 * @param data the data for the Node within the linked list
	 * @return reference to the current object
	 */
	public BasicDoubleLinkedList<T> addToFront(T data) {
		Node node = new Node(data, head, null);
		if (head != null) {
			head.previous = node;
		}
		head = node;

		if (tail == null) {
			tail = node;
		}

		size++;
		return this;
	}
	/**
	 * Returns but does not remove the first element from the list.
	 * @return the first data element or null
	 */
	public T getFirst() {
		return head.data;
	}
	/**
	 * Returns but does not remove the last element from the list.
	 * @return the last data element or null
	 */
	public T getLast() {
		return tail.data;
	}
	/**
	 * This method is implemented using an inner class that implements ListIterator.
	 * @throws java.util.NoSuchElementException next() method throws NoSuchElementException if there are no more elements (at the end of the list and calling next() or at the beginning of the list and calling previous())
	 * @throws java.lang.UnsupportedOperationException does not need to implement the ListIterator's remove(), add(), nextIndex() and previousIndex() and set() methods, throw UnsupportedOperationException if called
	 */
	public ListIterator<T> iterator() throws UnsupportedOperationException, NoSuchElementException {
		return new Iterator();
	}
	/**
	 * Removes the first instance of the targetData from the list.
	 * @param targetData the data element to be removed
	 * @param comparator the comparator to determine equality of data elements
	 * @return data element or null
	 */
	public BasicDoubleLinkedList<T> remove(T targetData, java.util.Comparator<T> comparator) {
		Node prev = null, current = head;
		while (current != null) {
			if (comparator.compare(current.data, targetData) == 0) {
				if (current == head) {
					head = head.next;
					current = head;
				} 
				else if (current == tail) {
					current = null;
					tail = prev;
					prev.next = null;
				} 
				else {
					prev.next = current.next;
					current = current.next;
				}
				size--;
			} 
			else {
				prev = current;
				current = current.next;
			}
		}
		return this;
	}
	/**
	 * Removes and returns the first element from the list.
	 * @return data element or null
	 */
	public T retrieveFirstElement() {
		if (size == 0) {
			throw new NoSuchElementException("Linked list is empty");
		}
		Node node = head;
		head = head.next;
		head.previous = null;
		size--;
		return node.data;
	}
	/**
	 * Removes and returns the last element from the list.
	 * @return data element or null
	 */
	public T retrieveLastElement() {
		if (head == null) {
			throw new NoSuchElementException("Linked list is empty");
		}
		Node current = head;
		Node previousNode = null;
		while (current != null) {
			if (current.equals(tail)) {
				tail = previousNode;
				break;
			}
			previousNode = current;
			current = current.next;
		}
		size--;
		return current.data;
	}
	/**
	 * Returns an ArrayList of the items in the list from head of list to tail of list.
	 * @return an ArrayList of the items in the list
	 */
	public ArrayList<T> toArrayList() {
		ArrayList<T> temp = new ArrayList<T>();
		ListIterator<T> iterator1 = new Iterator();

		while (iterator1.hasNext()) {
			temp.add(iterator1.next());
		}
		return temp;
	}

	private class Iterator implements ListIterator<T> {

		private Node current;
		private Node last;

		public Iterator() {
			current = head;
			last = null;
		}

		public T next()  throws NoSuchElementException {
			if(current != null) {
				T data = current.data;
				last = current;
				current = current.next;
				if(current != null)
					current.previous = last;

				return data;
			}
			else
				throw new NoSuchElementException();
		}

		public boolean hasNext() {
			return current != null;
		}

		public T previous() {
			if (last != null) {
				current = last;
				last= current.previous;
				T data = current.data;

				return data;
			}
			else
				throw new NoSuchElementException();
		}

		@Override
		public void add(T e) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		public boolean hasPrevious() {
			return last!=null;
		}

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}

		public void set(T data) {
			throw new UnsupportedOperationException();
		}
	}

}