import java.util.Comparator;


public class Uppgift2c<T> implements Comparable<T>
{
    private static Land min;
    private static Land max;
    private static Land temp;
    private static MyArrayList<Land> lists;

    public static MyArrayList<Land> findMinMax(MyArrayList<Land> list){
        min = null;
        max = null;
        temp = list.get(0);
        lists = new MyArrayList<Land>();

        for (int x = 0; x < list.Size(); x++) 
        {
            if ((x+1) < 4) 
            {
                if (temp.compareTo(list.get(x+1)) > 0) 
                {
                    max = temp;
                    temp = max;
                }else{
                    max = list.get(x + 1);
                    temp = max;
                }
            }
        }

        temp = list.get(0);
        for (int q = 0; q < list.Size(); q++)
        {
            if ((q+1) < 4) {
                if (temp.compareTo(list.get(q+1)) > 0) 
                {
                    min = list.get(q + 1);
                    temp = min;
                } else 
                {
                    min = temp;
                    temp = min;
                }
            }
        }
        lists.add(min);
        lists.add(max);
        return lists;
    }
    
    public static MyArrayList<Land> findMinMax(MyArrayList<Land> list, Comparator<Land> c){
        min = list.get(0);
        max = list.get(0);
        lists = new MyArrayList<Land>();

		for (int i = 0; i < list.size(); i++) {

			if (c.compare(list.get(i), max) == 1) {
				max = list.get(i);
			}

			if (c.compare(list.get(i), min) == -1) {
				min = list.get(i);
			}
		}
        lists.add(min);
        lists.add(max);
        return lists;
    }

    @Override
    public int compareTo(T o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
