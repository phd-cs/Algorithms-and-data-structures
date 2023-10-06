import java.util.Comparator;

public class Land implements Comparable<Land>
{
    String namn;
    String huvudstad;
    int invånare;
    
    public Land(String namn, String huvudstad,int invånare ){
        this.namn = namn;
        this.huvudstad = huvudstad;
        this.invånare = invånare;
    }
    
    @Override
    public int compareTo(Land land) { 
        return 0;
    }
}
    
class compareByName implements Comparator<Land>
{
    
    @Override
    public int compare(Land land1, Land land2) {
        int one = Integer.parseInt(land1.namn);
        int two = Integer.parseInt(land2.namn);
        
         if (one < two) {
            return -1;
        }
        else if (one > two) {
            return 1;
        }
        else {
            return 0;
        }
    }
        
}

class compareByCapital implements Comparator<Land>
{

    @Override
    public int compare(Land land1, Land land2) {
        int one = Integer.parseInt(land1.huvudstad);
        int two = Integer.parseInt(land2.huvudstad);
        
        if (one < two) {
            return -1;
        }
        else if (one > two) {
            return 1;
        }
        else {
            return 0;
        }
    }
        
}

class compareByPopulation implements Comparator<Land>
{

    @Override
    public int compare(Land land1, Land land2) {
         if (land1.invånare < land2.invånare) {
            return -1;
        }
        else if (land1.invånare > land2.invånare) {
            return 1;
        }
        else {
            return 0;
        }
    }
        
}
    

