import java.util.ArrayList;
import java.util.Stack;

public class LinearProbingHashSet<Key> {
	HashElement<Key>[] hashTable;
	int currSize; 
	int maxSize; 

	public LinearProbingHashSet(int m) {
		hashTable = new HashElement[m];
		this.maxSize = m;
		currSize = 0;
	}

	public LinearProbingHashSet() {
		hashTable = new HashElement[10];
		maxSize = 10;
		currSize = 0;
	}

	public int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % maxSize;
	}

	public int getCapacity() {
		return maxSize;
	}
	
	public boolean contains(Key key) {
		int val = hash(key);
		int count = 0;

		while (hashTable[val] != null && count < maxSize) {
			if (hashTable[val].getKey().equals(key))
				return true;
			else {
				val = (val + 1) % maxSize;
				count++;
			}
		}
		return false;
	}
	
	public void insert(Key key) throws Exception {
		if (loadFactor() >= 0.5)
			resizeDouble();

		int val = hash(key);
		int count = 0;

		while (count != maxSize && hashTable[val] != null && !(hashTable[val]).getKey().equals(key)) {
			val = (val + 1) % maxSize;
			count++;
		}

		if (hashTable[val] == null) {
			HashElement<Key> el = new HashElement<Key>(key);
			hashTable[val] = el;
			currSize++;
		}
		else if ((hashTable[val]).getKey().equals(key))
			(hashTable[val]).increment();
		else if (count == maxSize)
			throw new Exception("The List is Full");
	}

	public void resizeDouble() {
		maxSize = hashTable.length * 2;
		HashElement<Key>[] dubbelHashList = new HashElement[maxSize];

		for (int i = 0; i < hashTable.length; i++) {
			if (hashTable[i] != null) {
				HashElement<Key> el =(hashTable[i]);
				Key k = (Key)(el.getKey());
				int val = hash(k);
				dubbelHashList[val] = el;
			}
		}
		hashTable = dubbelHashList;
	}

	public void resizeHalf() {
		maxSize = hashTable.length / 2;
		HashElement<Key>[] halvHashList = new HashElement[maxSize];

		for (int i = 0; i < hashTable.length; i++) {
			if (hashTable[i] != null) {
				HashElement<Key> el =(hashTable[i]);
				Key k = (Key)(el.getKey());
				int val = hash(k);
				halvHashList[val] = el;
			}
		}
		hashTable = halvHashList;
	}

	public double loadFactor() {
		double lf = (double) currSize / maxSize;
		return lf;
	}

	public void decrease(Key key) throws Exception {
		int val = hash(key);
		int count = 0;

		while (hashTable[val] != null && count < maxSize) {
			if (hashTable[val].getKey().equals(key)) {
				hashTable[val].decrement();
				if (hashTable[val].getFrequencey() == 0)
					delete((Key)hashTable[val].getKey());
				break;
			}
			else {
				val = (val + 1) % maxSize;
				count++;
			}
		}

		if (count == maxSize)
			System.out.println("Key not found");

	}

	public void delete(Key key) throws Exception {
		if (loadFactor() <= 0.125)
			resizeHalf();
		
		int val = hash(key);
		int count = 0;

		while (hashTable[val] != null && count < maxSize) {
			if (hashTable[val].getKey() == key) {
				hashTable[val] = null;
				currSize--;
				break;
			}
			else {
				val = (val + 1) % maxSize;
				count++;
			}
		}
		if (count == maxSize)
			System.out.println("Key not found");
	}
	
		public Iterable<Key> keys() {
		ArrayList<HashElement> iter = new ArrayList<HashElement>();
		ArrayList<Key> iter2 = new ArrayList<Key>();
		
		for (int i = 0; i < maxSize; i++) {
			if (hashTable[i] != null)
				iter.add(hashTable[i]);
			iter.sort(null);
		}

		Stack<Key> s = new Stack<>();
		for(int i = 0; i < iter.size(); i++){
			s.push((Key)iter.get(i).getKey());
		}
		while(!s.isEmpty())
			iter2.add(s.pop());
				
		return iter2;
	}

	public String toString() {
		String str = "";
		for (int i = 0; i < maxSize; i++) {
			str += hashTable[i] + " ";
		}
		return str;
	}
}
