package basic.iter;

import java.util.ArrayList;
import java.util.Iterator;

public class ListOnly<I> implements Iterable{
	public static void main(String[] args) {
		ListOnly<Integer> ListOnly = new ListOnly<>();
		ListOnly.add(1);
		ListOnly.add(2);
		Iterator iterator = ListOnly.iterator();

		while (iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}
	ArrayList<I> buffer = new ArrayList<I>();

	public void add(I data){
		buffer.add(data);
	}

	@Override
	public Iterator iterator() {
		return new IterA();
	}

	class IterA implements Iterator{
		int index= 0;

		@Override
		public boolean hasNext() {
			boolean boo = false;
			if (index < buffer.size()){
				boo = true;
			}
			return boo;
		}

		@Override
		public Object next() {
			I value = buffer.get(index);
			index++;
			return value;
		}
	}
}
