import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JProgressBar;

public class QuickSort implements Runnable {

	private Integer[] data;
	private double progressCount = 0.0;
	private int numberOfPasses = -1;
	private long startTime, finishTime, timeElapsed;
	private JProgressBar bar;
	
	public QuickSort(Integer[] data, JProgressBar bar)
	{
		this.data = data;
		this.bar = bar;
	}
	
	public void run()
	{
		startTime = System.nanoTime();
		quickSort(data, 0, data.length - 1);
		finishTime = System.nanoTime();
		timeElapsed = finishTime - startTime;
		System.out.println("Quick: " + timeElapsed);
		outputToFile();
	}
	
	  private void outputToFile() {
			File outputFile = new File("output.dat");
			try {
				PrintWriter outStream = new PrintWriter(new FileWriter(outputFile, true));
				outStream.append("Quick Sort Thread elapsed in " + timeElapsed + " nanoseconds");
				outStream.close();
			} catch (IOException e) {
				System.out.println("Quick Sort didn't write.");
			}
			
		}
	  
	  public int getProgress()
	  {
	  	int result;
	  	progressCount = 1 - (((double)data.length - (double)numberOfPasses)/(double)data.length);
	  	result = (int) (progressCount * 100);
	  	bar.setValue(result);
	  	return result;
	  }
	  
	  private void quickSort(Integer[] data, int min, int max)
	  {
		  if(min < max)
		  {
			  int indexofpartition = partition(data, min, max);
			  quickSort(data, min, indexofpartition - 1);
			  quickSort(data, indexofpartition + 1, max);
		  }
	  }
	  
	  private int partition(Integer[] data, int min, int max)
	  {
		  Integer partitionelement;
		  int left, right;
		  int middle = (min + max) / 2;
		  
		  partitionelement = data[middle];
		  
		  swap(data, middle, min);
		  
		  left = min;
		  right = max;
		  
		  while(left < right)
		  {
			
				while (left < right && data[left].compareTo(partitionelement) <= 0)
					left++;
				
			
				while (data[right].compareTo(partitionelement) > 0)
					right--;
				
				
				if (left < right)
					swap(data, left, right);
				
				numberOfPasses++;
				getProgress();
		  }
		  
		  swap(data, min, right);
		  
		  return right;
	  }
	
	
	private void swap(Integer[] data, int index1, int index2)
	{
		Integer temp = data[index1];
		data[index1] = data[index2];
		data[index2] = temp;
	}
}
