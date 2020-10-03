import java.util.ListIterator;
import java.util.Comparator;
/**
 * Implements a generic sorted double list using a provided Comparator. It extends BasicDoubleLinkedList class.
 * 
 * @author Justin Hixson
 *
 * @param <T> data element type
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {

	Comparator<T> comparable2;
	/**
	 * Creates an empty list that is associated with the specified comparator.
	 * @param comparable2 Comparator to compare data elements
	 */
	public SortedDoubleLinkedList(Comparator<T> comparable2) {
		this.comparable2 = comparable2;
	}
	/**
	 * Inserts the specified element at the correct position in the sorted list.
	 * @param data the data to be added to the list
	 * @return a reference to the current object
	 */
	public SortedDoubleLinkedList<T> add(T data) {
		if (data == null) {
			return this;
		}

		Node node = new Node(data, null, null);
		if (head == null) {
			head = tail = new Node(data, null, null);
		}
		else {
			if (comparable2.compare(data, head.data) <= 0) {
				node.next = head;
				head = node;
			} 
			else if (comparable2.compare(data, tail.data) >= 0) {
				tail.next = node;
				tail = node;
			} 
			else {
				Node next = head.next;
				Node previous = head;
				while (comparable2.compare(data, next.data) > 0) {
					previous = next;
					next = next.next;
				}
				previous.next = node;
				node.next = next;
			}
		}
		size++;
		return this;
	}
	/**
	 * This operation is invalid for a sorted list. An UnsupportedOperationException will be generated using the message "Invalid operation for sorted list."
	 * @throws UnsupportedOperationException if method is called
	 */
	@Override
	public BasicDoubleLinkedList<T> addToEnd(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}
	/**
	 * This operation is invalid for a sorted list. An UnsupportedOperationException will be generated using the message "Invalid operation for sorted list."
	 * @throws UnsupportedOperationException if method is called
	 */
	@Override
	public BasicDoubleLinkedList<T> addToFront(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}
	/**
	 * Implements the iterator by calling the super class iterator method.
	 * @return an iterator positioned at the head of the list
	 */
	@Override
	public ListIterator<T> iterator() {
		return super.iterator();
	}
	/**
	 * Implements the remove operation by calling the super class remove method.
	 */
	@Override
	public SortedDoubleLinkedList<T> remove(T data, Comparator<T> comparable2) {
		super.remove(data, comparable2);

		return this;
	}
}