import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JProgressBar;

public class InsertionSort implements Runnable {

	private Integer[] data;
	private double progressCount = 0.0;
	private int numberOfPasses = -1;
	private long startTime, finishTime, timeElapsed;
	JProgressBar bar;
	//private int bullshit;
	
	public InsertionSort(Integer[] data, JProgressBar bar)
	{
		this.data = data;
		this.bar = bar;
	}
	
	
	public void run()
	{
		startTime = System.nanoTime();
		insertionSort();
		finishTime = System.nanoTime();
		timeElapsed = finishTime - startTime;
		System.out.println("Insertion: " + timeElapsed);
		outputToFile();
	}
	
	
	  public void insertionSort()
	    {
	        for (int index = 1; index < data.length; index++)
	        {
	            int key = data[index];
	            int position = index;
				numberOfPasses++;
				getProgress();
	          
	            while (position > 0 && data[position-1].compareTo(key) > 0)
	            {
	                data[position] = data[position-1];
	                position--;
	            }
				
	            data[position] = key;
	        }
	    }
	  
	  private void outputToFile() {
			File outputFile = new File("output.dat");
			try {
				PrintWriter outStream = new PrintWriter(new FileWriter(outputFile, true));
				outStream.append("InsertionSort Thread elapsed in " + timeElapsed + " nanoseconds");
				outStream.close();
			} catch (IOException e) {
				System.out.println("InsertionSort didn't write.. gg");
			}
			
		}
	  
	  public int getProgress()
	  {
	  	int result;
	  	progressCount = 1 - (((double)data.length - (double)numberOfPasses)/(double)data.length);
	  	result = (int) (progressCount * 100);
	  	//System.out.println(result);
	  	//System.out.println(numberOfPasses);
	  	//bullshit = result;
	  	bar.setValue(result);
	  	return result;
	  }
	  
	//  public String toString()
	//  {
	//  	return bullshit + "";
	//  	
	 // }
	  
}
