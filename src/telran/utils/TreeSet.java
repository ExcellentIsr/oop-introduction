package telran.utils;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeSet<T> extends AbstractCollection<T> implements Sorted<T> {
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
		Node<T> currentNode = lowestElement(root);
		Node<T> prev = null;
		boolean fl = false;

		TreeSetIterator() {
			if (currentNode != null) {
				currentNode = lowestElement(currentNode);
			}
		}

		@Override
		public boolean hasNext() {
			return currentNode != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			Node<T> res = currentNode;
			prev = currentNode;

			currentNode = setNextNode(currentNode);
			fl = true;
			return res.obj;
		}

		private Node<T> setNextNode(Node<T> current) {
			return current.right == null ? getGreaterParent(current) : lowestElement(current.right);
		}

		private Node<T> getGreaterParent(Node<T> current) {
			while (current.parent != null && current.parent.left != current) {
				current = current.parent;
			}
			return current.parent;
		}

		@Override
		public void remove() {
			if (!fl) {
				throw new IllegalStateException();
			}
			TreeSet.this.remove(prev.obj);

			fl = false;
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

	private Node<T> lowestElement(Node<T> currentNode) {
		while (currentNode.left != null) {
			currentNode = currentNode.left;
		}
		return currentNode;
	}

	private Node<T> largestElement(Node<T> currentNode) {
		while (currentNode.right != null) {
			currentNode = currentNode.right;
		}
		return currentNode;
	}

	@Override
	public boolean add(T element) {
		boolean res = false;
		if (root == null) {
			res = addRoot(element);
		} else {
			res = addElementWithChild(element);
		}
		return res;
	}

	private boolean addElementWithChild(T element) {
		Node<T> currentNode = root;
		boolean res = false;
		while (currentNode != null) {
			int compRes = comp.compare(element, currentNode.obj);

			if (compRes > 0) {
				if (currentNode.right == null) {
					res = addElementToRight(currentNode, element);
				} else {
					currentNode = currentNode.right;
				}
			} else if (compRes < 0) {
				if (currentNode.left == null) {
					res = addElementToLeft(currentNode, element);
				} else {
					currentNode = currentNode.left;
				}
			} else {
				currentNode = null;
			}
		}
		return res;
	}

	private boolean addRoot(T element) {
		root = new Node<>(element);
		size++;
		return true;
	}

	private boolean addElementToRight(Node<T> currentNode, T element) {
		Node<T> node = new Node<>(element);
		currentNode.right = node;
		node.parent = currentNode;
		size++;
		return true;
	}

	private boolean addElementToLeft(Node<T> currentNode, T element) {
		Node<T> node = new Node<>(element);
		currentNode.left = node;
		node.parent = currentNode;
		size++;
		return true;
	}

	@Override
	public boolean remove(T pattern) {
		Node<T> current = getNode(pattern);
		boolean res = false;
		if (current != null && comp.compare(current.obj, pattern) == 0) {
			if (current.left != null && current.right != null) {
				Node<T> node = lowestElement(current.right);
				current.obj = node.obj;
				removeCurrentNode(node);
			} else {
				removeCurrentNode(current);
			}
			res = true;
		}
		return res;
	}

	private void removeCurrentNode(Node<T> current) {
		Node<T> child = current.left != null ? current.left : current.right;
		if (child != null) {
			child.parent = current.parent;
		}
		if (current.parent == null) {
			removeRoot(child);
		} else {
			if (comp.compare(current.obj, current.parent.obj) < 0) {
				current.parent.left = child;
			} else {
				current.parent.right = child;
			}
		}
		current.parent = null;
		current.left = null;
		current.right = null;
		size--;
	}

	private void removeRoot(Node<T> child) {
		if (size == 1) {
			root = null;
		} else {
			root = child;
		}
	}

	@Override
	public boolean contains(T pattern) {
		Node<T> node = getNode(pattern);
		return node != null && comp.compare(pattern, node.obj) == 0;
	}

	private Node<T> getNode(T element) {
		Node<T> current = root;
		Node<T> parent = null;
		int compRes;
		while (current != null && (compRes = comp.compare(element, current.obj)) != 0) {
			parent = current;
			current = compRes < 0 ? current.left : current.right;
		}
		return current == null ? parent : current;
	}

	@Override
	public Iterator<T> iterator() {
		return new TreeSetIterator();
	}

	@Override
	public T floor(T element) {
		Iterator<T> it = iterator();
		boolean fl = false;
		T current = null;
		T prev = null;

		while (!fl && it.hasNext()) {
			current = it.next();
			if (comp.compare(current, element) > 0) {
				fl = true;
			}
			if (!fl)
				prev = current;
		}

		return prev;
	}

	@Override
	public T ceiling(T element) {
		Iterator<T> it = iterator();
		T res = null;
		T current = null;
		while (res == null && it.hasNext()) {
			current = it.next();
			if (comp.compare(current, element) >= 0) {
				res = current;
			}
		}

		return res;
	}

	@Override
	public T first() {
		return root != null ? lowestElement(root).obj : null;
	}

	@Override
	public T last() {
		return root != null ? largestElement(root).obj : null;
	}
}
