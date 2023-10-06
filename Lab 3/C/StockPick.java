

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime; 

public class StockPick implements Comparable<StockPick>
{
    public String stockName;
    public LocalDateTime dateAndTime;
    public int confidence;
    public boolean goLong;
    public String AlgorithmName;
    public int volume;
    
    
    public StockPick(String stockName, LocalDateTime dateAndTime,
    int confidence, boolean goLong, String AlgorithmName,
    int volume)
    {
        this.stockName = stockName;
        this.dateAndTime = dateAndTime; 
        this.confidence = confidence;
        this.goLong = goLong;
        this.AlgorithmName = AlgorithmName;
        this.volume = volume;
    }
    
    /**
    * Compares the confidence of this with the confidence of that.
    */
    public int compareTo(StockPick that)
    {
        return ((Integer)confidence).compareTo(that.confidence);
    }
    
    /**
    * Converts to string.
    */
    public String toString()
    {
        DateTimeFormatter dtf = 
        DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String s = "Time: " + dtf.format(dateAndTime) + ". " + AlgorithmName 
        + " picks: " + stockName + "." + " Confidence: " + confidence
        + ".";
        if (goLong) s += " Direction: long. ";
        else s += " Direction: short. ";
        s += "Volume: " + volume + ".";
        return s;
    }
    
    public static void main(String[] cmdLn)
    {
        StockPick sp = new StockPick(
        "TSLA", LocalDateTime.now(),
        10, true, "SuperAlgorithm", 100);
        System.out.println(sp);
    }
    
    
    
}
