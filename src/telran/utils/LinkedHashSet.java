package telran.utils;

import java.util.Iterator;

import telran.utils.LinkedList.Node;

public class LinkedHashSet<T> extends AbstractCollection<T> implements Set<T> {
	protected LinkedList<T> list = new LinkedList<T>();
	protected HashMap<T, Node<T>> map = new HashMap<T, Node<T>>();

	private class LinkedHashSetIterator implements Iterator<T> {
		Iterator<T> listIterator = list.iterator();
		T current = null;

		@Override
		public boolean hasNext() {
			return listIterator.hasNext();
		}

		@Override
		public T next() {
			current = listIterator.next();
			return current;
		}

		@Override
		public void remove() {
			listIterator.remove();
			map.remove(current);
			size--;
		}
	}

	@Override
	public boolean add(T element) {
		boolean res = false;
		if (!map.containsKey(element)) {
			list.add(element);
			map.put(element, list.tail);
			res = true;
			size++;
		}
		return res;
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		Node<T> node = map.get(pattern);
		if (node != null) {
			list.removeNode(node);
			map.remove(pattern);
			res = true;
			size--;
		}
		return res;
	}

	@Override
	public boolean contains(T pattern) {
		return map.containsKey(pattern);
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedHashSetIterator();
	}

	@Override
	public T get(T patter) {
		return map.get(patter) == null ? null : map.get(patter).obj;
	}

}
