import java.util.Comparator;

public class LinearProbingHashSetComparator implements Comparator<HashElement> {
	@Override
	public int compare(HashElement o1, HashElement o2) {
		if(o1.frequency == o2.frequency)
			return 0;
		if(o1.frequency > o2.frequency)
			return 1;

		return -1;
	}
}
