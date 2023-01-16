package telran.utils;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeSet<T> extends AbstractCollection<T> implements Set<T> {
	static private class Node<T> {
		T obj;
		Node<T> parent;
		Node<T> left;
		Node<T> right;

		Node(T obj) {
			this.obj = obj;
		}
	}

	private class TreeSetIterator implements Iterator<T> {
		int current = 0;
		Node<T> currentNode = lowestElement(root);

		@Override
		public boolean hasNext() {
			return current < size;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			Node<T> res = currentNode;
			boolean fl = false;

			while (!fl) {
				if (currentNode.right != null) {
					fl = setCurrentNode(true);
				} else {
					T oldItem = currentNode.obj;
					while (currentNode.parent != null && comp.compare(currentNode.parent.obj, oldItem) < 0) {
						currentNode = currentNode.parent;
					}
					fl = setCurrentNode(false);
				}
			}

			return res.obj;
		}

		private boolean setCurrentNode(boolean pos) {
			if (pos) {
				currentNode = lowestElement(currentNode.right);
			} else {
				currentNode = currentNode.parent;
			}
			current++;
			return true;
		}

		private Node<T> lowestElement(Node<T> currentNode) {
			while (currentNode.left != null) {
				currentNode = currentNode.left;
			}
			return currentNode;
		}

	}

	private Node<T> root;
	private Comparator<T> comp;

	public TreeSet(Comparator<T> comp) {
		this.comp = comp;
	}

	@SuppressWarnings("unchecked")
	public TreeSet() {
		this((Comparator<T>) Comparator.naturalOrder());
	}

	@Override
	public boolean add(T element) {
		boolean res = false;
		if (root == null) {
			root = new Node<>(element);
			res = true;
			size++;
		} else {
			Node<T> currentNode = root;

			while (currentNode != null) {
				if (comp.compare(element, currentNode.obj) > 0) {
					if (currentNode.right == null) {
						res = addLink(currentNode, element, false);
					} else {
						currentNode = currentNode.right;
					}
				} else if (comp.compare(element, currentNode.obj) < 0) {
					if (currentNode.left == null) {
						res = addLink(currentNode, element, true);
					} else {
						currentNode = currentNode.left;
					}
				} else {
					currentNode = null;
				}
			}
		}
		return res;
	}

	private boolean addLink(Node<T> currentNode, T element, boolean pos) {
		Node<T> node = new Node<>(element);
		if (pos) {
			currentNode.left = node;
		} else {
			currentNode.right = node;
		}
		node.parent = currentNode;
		currentNode = null;
		size++;
		return true;
	}

	@Override
	public boolean remove(T pattern) {
		// Not implemented yet
		return false;
	}

	@Override
	public boolean contains(T pattern) {
		Node<T> currentNode = root;
		boolean res = false;
		while (currentNode != null && !res) {
			if (comp.compare(pattern, currentNode.obj) > 0) {
				currentNode = (currentNode.right == null) ? null : currentNode.right;
			} else if (comp.compare(pattern, currentNode.obj) < 0) {
				currentNode = (currentNode.left == null) ? null : currentNode.left;
			} else {
				res = true;
			}
		}
		return res;
	}

	@Override
	public Iterator<T> iterator() {
		return new TreeSetIterator();
	}
}
