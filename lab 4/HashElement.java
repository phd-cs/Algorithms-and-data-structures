public class HashElement<Key> implements Comparable<HashElement<Key>> {
	Key key;
	int frequency;

	public HashElement(Key key) {
		this.key = key;
		frequency = 1;
	}

	public HashElement(Key key, int frequency) throws Exception {
		if(key == null)
			throw new Exception("Key cannot be null");
		else
			this.key = key;	
		
		if(frequency < 0)
			throw new Exception("The frequency cannot be negative!");
		else
			this.frequency = frequency;
	}

	public void increment() {
		frequency++;
	}

	public void decrement() {
		frequency--;
	}

	public int getFrequencey() {
		return frequency;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public int compareTo(HashElement<Key> that) {
        if(this.frequency == that.frequency)
            return 0;
        else if(this.frequency > that.frequency)
            return 1;
        else
            return -1;
	}

}
