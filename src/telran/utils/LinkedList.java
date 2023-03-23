package telran.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class LinkedList<T> extends AbstractCollection<T> implements List<T> {
	public static class Node<T> {
		T obj;
		Node<T> prev;
		Node<T> next;

		Node(T obj) {
			this.obj = obj;
		}
	}

	Node<T> head;
	Node<T> tail;

	private class LinkedListIterator implements Iterator<T> {
		Node<T> current = head;
		boolean flNext = false;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T res = current.obj;
			current = current.next;
			flNext = true;
			return res;
		}

		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			Node<T> removedNode = current == null ? tail : current.prev;
			removeNode(removedNode);
			flNext = false;
		}

	}

	public void removeNode(Node<T> current) {
		if (head == current) {
			removeHead();
		} else if (tail == current) {
			removeTail();
		} else {
			removeMiddle(current);
		}
		size--;
	}

	@Override
	public T remove(int index) {
		checkIndex(index, 0, size());
		Node<T> removedNode = getNode(index);

		if (removedNode == null) {
			throw new IllegalStateException("removedNode in method remove is null");
		}

		removeNode(removedNode);
		return removedNode.obj;
	}

	private void removeMiddle(Node<T> current) {
		(current.prev).next = current.next;
		(current.next).prev = current.prev;
	}

	private void removeTail() {
		tail = tail.prev;
		tail.next = null;
	}

	private void removeHead() {
		if (size == 1) {
			head = tail = null;
		} else {
			head = head.next;
			head.prev = null;
		}
	}

	/************************************************************************************/
	// Comments only for LinkedList task of loop existence
	public void setNext(int index1, int index2) {
		// sets next of element at index1 to element at index2
		if (index1 < index2) {
			throw new IllegalArgumentException();
		}
		checkIndex(index1, 0, size() - 1);
		checkIndex(index2, 0, size() - 1);
		getNode(index1).next = getNode(index2);
	}

	public boolean hasLoop() {
		Node<T> step = head;
		Node<T> step2 = head;
		boolean res = false;
		while (step2 != null && step2.next != null && !res) {
			step = step.next;
			step2 = step2.next.next;

			res = step == step2;
		}
		return res;
	}

	/*********************************************************************************************/

	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}

	@Override
	public boolean add(T element) {
		Node<T> node = new Node<>(element);
		if (head == null) {
			head = tail = node;
		} else {
			tail.next = node;
			node.prev = tail;
			tail = node;
		}
		size++;
		return true;
	}

	@Override
	public void add(int index, T element) {
		checkIndex(index, 0, size() + 1);
		if (index == size) {
			add(element);
		} else if (index == 0) {
			addHead(element);
		} else {
			addMiddle(index, element);
		}
	}

	private void addHead(T element) {
		Node<T> node = new Node<>(element);
		node.next = head;
		head.prev = node;
		head = node;
		size++;
	}

	private void addMiddle(int index, T element) {
		Node<T> node = new Node<>(element);
		Node<T> nodeIndex = getNode(index);
		Node<T> nodePrev = nodeIndex.prev;
		nodePrev.next = node;
		node.prev = nodePrev;
		nodeIndex.prev = node;
		node.next = nodeIndex;
		size++;
	}

	private Node<T> getNode(int index) {
		return index < size() / 2 ? getNodeFromLeft(index) : getNodeFromRight(index);
	}

	private Node<T> getNodeFromRight(int index) {
		Node<T> current = tail;
		for (int i = size - 1; i > index; i--) {
			current = current.prev;
		}
		return current;
	}

	private Node<T> getNodeFromLeft(int index) {
		Node<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		Node<T> current = head;
		int oldSize = size();
		for (int i = 0; i < oldSize; i++) {
			if (predicate.test(current.obj)) {
				remove(current.obj);
			}
			current = current.next;
		}
		return oldSize > size();
	}

	@Override
	public int indexOf(T pattern) {
		Node<T> current = head;
		int res = -1;
		int index = 0;
		while (index < size && res < 0) {
			if (pattern.equals(current.obj)) {
				res = index;
			}
			current = current.next;
			index++;
		}
		return res;
	}

	@Override
	public int lastIndexOf(T pattern) {
		Node<T> current = tail;
		int res = -1;
		int index = size() - 1;

		while (index > 0 && res < 0) {
			if (pattern.equals(current.obj)) {
				res = index;
			}
			current = current.prev;
			index--;
		}

		return res;
	}

	@Override
	public T get(int index) {
		checkIndex(index, 0, size());
		return getNode(index).obj;
	}

	@Override
	public void set(int index, T element) {
		checkIndex(index, 0, size());
		Node<T> node = getNode(index);
		node.obj = element;
	}
}
