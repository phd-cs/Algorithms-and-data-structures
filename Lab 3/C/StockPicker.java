
import java.time.LocalDateTime;
import java.util.*;
public class StockPicker extends Thread
{
    DataBuffer<StockPick> stockPicks;
    String name;
    String[] stocks;
    int endTime; // time to run in seconds
    
    public StockPicker(String name, 
    DataBuffer<StockPick> stockPicks, 
    String[] stocks, int endTime)
    { 
        this.stockPicks = stockPicks;
        this.name = name;
        this.stocks = stocks;
        this.endTime = endTime;
    }
    
    public void run()
    {
        int time = 0; //milliseconds
        int maxUpdateTime = 100; // milliseconds
        Random r = new Random();
        
        while (time < 1000*endTime)
        {
            int nextTime = r.nextInt(maxUpdateTime);
            try
            {
                sleep(nextTime);
         
            }
            catch( InterruptedException e){}
            
            StockPick pq = new StockPick(
            stocks[r.nextInt(stocks.length)],
            LocalDateTime.now(), r.nextInt(100),
            r.nextBoolean(), name, r.nextInt(100));
            time += nextTime;

            try
            {
                stockPicks.enqueue(pq);
            }
            catch(Exception e){}      
        }
    }    
}
