import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JProgressBar;

public class MergeSort implements Runnable {

	private Integer[] data;
	private double progressCount = 0.0;
	private int numberOfPasses = 0;
	private long startTime, finishTime, timeElapsed;
	private JProgressBar bar;

	public MergeSort(Integer[] data, JProgressBar bar)
	{
		this.data = data;
		this.bar = bar;
	}

	public void run()
	{
		startTime = System.nanoTime();
		mergeSort();
		finishTime = System.nanoTime();
		timeElapsed = finishTime - startTime;
		System.out.println("Merge: " + timeElapsed);
		outputToFile();
	}

	private void outputToFile() {
		File outputFile = new File("output.dat");
		try {
			PrintWriter outStream = new PrintWriter(new FileWriter(outputFile, true));
			outStream.append("Merge Sort Thread elapsed in " + timeElapsed + " nanoseconds");
			outStream.close();
		} catch (IOException e) {
			System.out.println("Merge Sort didn't write.");
		}

	}

	public void getProgress()
	{
		int result;
		progressCount = 1 - (((double)data.length - (double)numberOfPasses)/(double)data.length);
		result = (int) (progressCount * 100);
		bar.setValue(result);
	}

	public void mergeSort()
	{
		mergeSort(data, 0, data.length - 1);
	}

	private void mergeSort(Integer[] data, int min, int max)
	{
		if (min < max)
		{
			int mid = (min + max) / 2;
			mergeSort(data, min, mid);
			mergeSort(data, mid+1, max);
			merge(data, min, mid, max);
		}
	}

	private void merge(Integer[] data, int first, int mid, int last)
	{
		int[] temp = new int[data.length];
		int first1 = first, last1 = mid;
		int first2 = mid+1, last2 = last; 
		int index = first1; 
		while (first1 <= last1 && first2 <= last2)
		{
			if (data[first1] < data[first2])
			{
				temp[index] = data[first1];
				first1++;
			}
			else
			{
				temp[index] = data[first2];
				first2++;
			}
			index++;
		}

		while (first1 <= last1)
		{
			temp[index] = data[first1];
			first1++;
			index++;
		}

		while (first2 <= last2)
		{
			temp[index] = data[first2];
			first2++;
			index++;
		}

		for (index = first; index <= last; index++)
		{
			data[index] = temp[index];
		}
		numberOfPasses++;
		getProgress();
	}
}
