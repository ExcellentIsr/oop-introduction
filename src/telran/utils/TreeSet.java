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
			fl = true;
			Node<T> res = currentNode;
			prev = currentNode;

			currentNode = setNextNode(currentNode);
			return res.obj;
		}

		@Override
		public void remove() {
			if (!fl) {
				throw new IllegalStateException();
			}
			fl = false;
			if (isJunction(prev)) {
				currentNode = prev;
			}
			removeNode(prev);
		}

	}

	private static final String SYMBOL = " ";
	private static final int NUMBER_SYMBOLS_PER_LEVEL = 3;

	private Node<T> root;
	private Comparator<T> comp;

	public TreeSet(Comparator<T> comp) {
		this.comp = comp;
	}

	@SuppressWarnings("unchecked")
	public TreeSet() {
		this((Comparator<T>) Comparator.naturalOrder());
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
			removeNode(current);
			res = true;
		}
		return res;
	}

	private void removeNode(Node<T> current) {
		if (isJunction(current)) {
			removeNodeJunction(current);
		} else {
			removeNodeNonJunction(current);
		}
	}

	private boolean isJunction(Node<T> node) {
		return node.left != null && node.right != null;
	}

	private void removeNodeJunction(Node<T> current) {
		Node<T> node = lowestElement(current.right);
		current.obj = node.obj;
		removeNodeNonJunction(node);
	}

	private void removeNodeNonJunction(Node<T> current) {
		Node<T> parent = current.parent;
		Node<T> child = current.left != null ? current.left : current.right;
		if (parent == null) {
			root = child;
		} else {
			if (parent.left == current) {
				parent.left = child;
			} else {
				parent.right = child;
			}
		}
		if (child != null) {
			child.parent = current.parent;
		}
		size--;
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
		return floorCeiling(element, true);
	}

	@Override
	public T ceiling(T element) {
		return floorCeiling(element, false);
	}

	private T floorCeiling(T pattern, boolean isFloor) {
		T res = null;
		int compRes = 0;
		Node<T> current = root;
		while (current != null && (compRes = comp.compare(pattern, current.obj)) != 0) {
			if ((compRes < 0 && !isFloor) || (compRes > 0 && isFloor)) {
				res = current.obj;
			}
			current = compRes < 0 ? current.left : current.right;
		}
		return current == null ? res : current.obj;
	}

	@Override
	public T first() {
		return root != null ? lowestElement(root).obj : null;
	}

	@Override
	public T last() {
		return root != null ? largestElement(root).obj : null;
	}

	public void displayTreeRotated() {
		displayTreeRotated(root, 0);
	}

	private void displayTreeRotated(Node<T> root, int level) {
		if (root != null) {
			displayTreeRotated(root.right, level + 1);
			displayRoot(root, level);
			displayTreeRotated(root.left, level + 1);
		}
	}

	private void displayRoot(Node<T> root, int level) {
		System.out.printf("%s%s\n", SYMBOL.repeat(NUMBER_SYMBOLS_PER_LEVEL * level), root.obj);
	}

	public int height() {
		return height(root);
	}

	public int height(Node<T> root) {
		int res = 0;

		if (root != null) {
			int heightRight = height(root.right);
			int heightLeft = height(root.left);
			res = Math.max(heightRight, heightLeft) + 1;
		}

		return res;
	}

	public int width() {
		return width(root);
	}

	private int width(Node<T> root) {
		int res = 0;
		if (root != null) {
			if (root.left == null && root.right == null) {
				res++;
			} else {
				res = width(root.left) + width(root.right);
			}
		}

		return res;
	}

	public void inversion() {
		inversion(root);
		comp = comp.reversed();
	}

	private void inversion(Node<T> root) {
		if (root != null) {
			inversion(root.left);
			inversion(root.right);
			swap(root);
		}
	}

	private void swap(Node<T> root) {
		Node<T> container = root.left;
		root.left = root.right;
		root.right = container;
	}

	public void balance() {
		Node<T>[] array = getNodesArray();
		root = balance(array, 0, array.length - 1, null);
	}

	private Node<T> balance(Node<T>[] array, int left, int right, Node<T> parent) {
		Node<T> root = null;
		if (left <= right) {
			final int rootIndex = (left + right) / 2;
			root = array[rootIndex];
			root.parent = parent;
			root.left = balance(array, left, rootIndex - 1, root);
			root.right = balance(array, rootIndex + 1, right, root);
		}
		return root;
	}

	private Node<T>[] getNodesArray() {
		@SuppressWarnings("unchecked")
		Node<T>[] result = new Node[size];
		int index = 0;
		if (root != null) {
			Node<T> current = lowestElement(root);
			while (current != null) {
				result[index++] = current;
				current = setNextNode(current);
			}
		}
		return result;
	}

	@Override
	public T get(T pattern) {
		T res = floor(pattern);
		if(!isEqual(res, pattern)) {
			res = null;
		}
		
		return res;
	}
}
