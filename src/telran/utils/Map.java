package telran.utils;

import java.util.Objects;

public interface Map<K, V> {
	class Entry<K, V> implements Comparable<Entry<K, V>> {
		public Entry(K key, V value) {
			super();
			this.key = key;
			this.value = value;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}

		public K getKey() {
			return key;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(key);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Entry<K, V> other = (Entry<K, V>) obj;
			return Objects.equals(key, other.key);
		}

		private K key;
		private V value;

		@SuppressWarnings("unchecked")
		@Override
		public int compareTo(Entry<K, V> o) {
			return ((Comparable<K>) key).compareTo(o.key);
		}
	}

	V put(K key, V value);
	
	default public V putIfAbsent(K key, V value){
		V res = get(key);
		
		if(res == null) {
			put(key, value);
		}
		return res;
	}

	V get(K key);
	
	default public V getOrDefault(K key, V value) {
		V res = get(key);
		
		return res == null ? value : res;
	}
	/**
	 * 
	 * @param key
	 * @return true if there is an entry with a given key otherwise false
	 */
	boolean containsKey(K key);
	/************************************************************/
	/**
	 * 
	 * @param value
	 * @return true if there is an entry with a given value otherwise false
	 */
	boolean containsValue(V value);
	/*******************************************************************/
	/**
	 * 
	 * @return collection of values
	 */
	Collection<V> values();
	/*********************************************************************/
	/**
	 * 
	 * @return Set with key values
	 */
	Set<K> keySet();
	/********************************************************************/
	/**
	 * 
	 * @return set with entry objects
	 */
	Set<Entry<K,V>> entrySet() ;
	/************************************************************************/
	/**
	 * 
	 * @param key
	 * removes entry with a given key value
	 * @return either reference to value of removed entry (removed) or null (not removed)
	 */
	V remove(K key);
}
