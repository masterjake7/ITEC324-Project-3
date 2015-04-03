import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JProgressBar;

public class BubbleSort implements Runnable  {

	private Integer[] data;
	private double progressCount = 0.0;
	private int numberOfPasses = -1;
	private long startTime, finishTime, timeElapsed;
	private JProgressBar bar;
	//private int bullshit;
	
	public BubbleSort(Integer[] data, JProgressBar bar)
	{
		this.data = data;
		this.bar = bar;
	}
	
	public void run()
	{
		startTime = System.nanoTime();
		bubbleSort();
		finishTime = System.nanoTime();
		timeElapsed = finishTime - startTime;
		System.out.println("Bubble: " + timeElapsed);
		outputToFile();
	}
	
private void outputToFile() {
		File outputFile = new File("output.dat");
		try {
			PrintWriter outStream = new PrintWriter(new FileWriter(outputFile, true));
			outStream.append("BubbleSort Thread elapsed in " + timeElapsed + " nanoseconds");
			outStream.close();
		} catch (IOException e) {
			System.out.println("BubbleSort didn't print you faggot");
		}
		
		
	}

	  
	  private void bubbleSort()
  {
      int position, scan;
		
      for (position =  data.length - 1; position >= 0; position--)
      {
    	  //This updates the number off passes every time it goes thru
    	  numberOfPasses++;
    	  getProgress();
          for (scan = 0; scan <= position - 1; scan++)
          {
              if (data[scan].compareTo(data[scan+1]) > 0)
                  swap(scan, scan + 1);
          }
      }
  }

private void swap( int index1, int index2)
{
	int temp = data[index1];
	data[index1] = data[index2];
	data[index2] = temp;
}
	

//This returns an int that represents progress
public void getProgress()
{
	int result;
	
	//formula for getting an int between 1 and 100 for progress
	progressCount = 1 - (((double)data.length - (double)numberOfPasses)/(double)data.length);
	result = (int) (progressCount * 100);
	bar.setValue(result);
	
	
}
	
//public String toString()
//{
//	return bullshit + "";
//	
//}
	  
}
