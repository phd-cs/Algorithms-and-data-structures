
import java.io.*;

public class Trader extends Thread
{
     DataBuffer<StockPick> stockPicks;
    // Fler intstansvariabler?

    int nrPicks; // nr of stock picks for printing to log-file each second
    int endTime; // time to run in seconds
    
    public Trader(DataBuffer<StockPick> stockPicks,
    int bufferSize, int nrPicks, int endTime)
    {
        this.stockPicks = stockPicks;
        this.nrPicks = nrPicks;
        this.endTime = endTime;
    }
    
    public void run()
    {
         try
         {
             OutputStreamWriter writer = 
             new OutputStreamWriter(new 
             FileOutputStream("log.txt"));
             writer.write("Start\n");
             writer.close();
         }
         catch(IOException e){}
         
         
         int time = 0;
         while (time < endTime)
         {


             try
             {
                 sleep(1000);
             }
             catch( InterruptedException e){}
             

             MaxPQ<StockPick> stockPickMaxPQ=new MaxPQ<>();
             for (int i = 0; i <stockPicks.Size() ; i++) {
                 stockPickMaxPQ.insert(stockPicks.dequeue());
             }


             try {
                 OutputStreamWriter dataOut = new OutputStreamWriter (new
                         FileOutputStream ("log.txt", true));
                 for (int i = 0; i <nrPicks ; i++) {

                     StockPick s=stockPickMaxPQ.delMax();

                     if (s==null)
                     {
                         break;
                     }
                     dataOut.write(s +"\n");
                 }
                 dataOut.close();

             } catch (IOException e) {
                 e.printStackTrace();
             }

             time++;
             
             System.out.println("Time elapsed: " + time 
             + " seconds.");
         }
    }
    
    
    public static void main(String[] cmdLn)
    {
        int bufferSize = 50;
        DataBuffer<StockPick> stockPicks =
        new DataBuffer<StockPick>(bufferSize);
        
        // StockPicker 1
        String[] stocks1 = new String[]{
            "TSLA", "CCJ", "GME", "UUUU", 
            "MFST", "GOOGL", "AAPL",
            "AMZN"};
            
        StockPicker Stockpicker1 = 
        new StockPicker("North America analyzer", 
        stockPicks, stocks1, 10);
        
        
        // StockPicker 2
        String[] stocks2 = new String[]{
            "ETH", "BTC"};
        
        StockPicker Stockpicker2 = 
        new StockPicker("Cryptocurrencices analyzer", 
        stockPicks, stocks2, 10);
        
        // trader
        Trader trader = new Trader(stockPicks, bufferSize, 3, 10);
        
        // run simulation
       Stockpicker1.start();
        Stockpicker2.start();
       trader.start();
    }
    
    
    
    
}
