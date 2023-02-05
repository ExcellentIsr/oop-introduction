package telran;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class MdArray<T> {
	private MdArray<T>[] array;
	private T value;

	public MdArray(int[] dimensions, T value) {
		this(dimensions, 0, value);
	}

	public MdArray(int[] dimensions, int firstDim, T value) {
		if (firstDim == dimensions.length) {
			this.value = value;
			array = null;
		} else {
			this.value = null;
			array = new MdArray[dimensions[firstDim]];
			for (int i = 0; i < array.length; i++) {
				array[i] = new MdArray<T>(dimensions, firstDim + 1, value);
			}
		}
	}
	
	public void forEach(Consumer <T> consumer) {
		if (array == null) {
			consumer.accept(value);
		} else {
			for (int i = 0; i < array.length; i++) {
				array[i].forEach(consumer);
			}
		}
	}
	
	public T[] toArray(T[] array){
		ArrayList<T> list = new ArrayList<>();
		forEach(n -> list.add(n));
		return list.toArray(array);
	}
	
	public void set(int[] indexes, T value) {
		MdArray<T> current = getCurrentMdArray(indexes);
		checkCurrent(current);
		current.value = value;
	}

	private MdArray<T> getCurrentMdArray(int[] indexes) {
		MdArray<T> current = this;
		try {
			for(Integer i : indexes) {
				current = current.array[i];
			}
		} catch (Exception e) {
			throw new NoSuchElementException();
		}
		return current;
	}

	public T get(int[] indexes) {
		MdArray<T> current = getCurrentMdArray(indexes);
		checkCurrent(current);
		return current.value;
	}
	
	private void checkCurrent(MdArray<T> current) {
		if(current.array != null) {
			throw new IllegalArgumentException();
		}
	}
}
