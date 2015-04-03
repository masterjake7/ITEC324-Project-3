import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class View implements ActionListener
{
	private JFrame frame = new JFrame("Project 3");
	private JPanel pane = new JPanel(new GridLayout(6,1));

	private JButton go = new JButton("Go");

	private JTextField input = new JTextField();

	private JPanel inputPanel = new JPanel(new FlowLayout());
	private JPanel bubblePanel = new JPanel(new FlowLayout());
	private JPanel insertionPanel = new JPanel(new FlowLayout());
	private JPanel mergePanel = new JPanel(new FlowLayout());
	private JPanel quickPanel = new JPanel(new FlowLayout());
	private JPanel radixPanel = new JPanel(new FlowLayout());

	private JProgressBar bubbleBar = new JProgressBar();
	private JProgressBar insertionBar = new JProgressBar();
	private JProgressBar mergeBar = new JProgressBar();
	private JProgressBar quickBar = new JProgressBar();
	private JProgressBar radixBar = new JProgressBar();

	private int userInput;

	private JLabel bubbleLabel = new JLabel("Bubble Sort:");
	private JLabel insertionLabel = new JLabel("Insertion Sort:");
	private JLabel mergeLabel = new JLabel("Merge Sort:");
	private JLabel quickLabel = new JLabel("Quick Sort:");
	private JLabel radixLabel = new JLabel("Radix Sort:");

	public View()
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		input.setPreferredSize(new Dimension(150, 25));

		inputPanel.add(input);
		inputPanel.add(go);
		bubblePanel.add(bubbleLabel);
		bubblePanel.add(bubbleBar);
		insertionPanel.add(insertionLabel);
		insertionPanel.add(insertionBar);
		mergePanel.add(mergeLabel);
		mergePanel.add(mergeBar);
		quickPanel.add(quickLabel);
		quickPanel.add(quickBar);
		radixPanel.add(radixLabel);
		radixPanel.add(radixBar);

		pane.add(inputPanel);
		pane.add(bubblePanel);
		pane.add(insertionPanel);
		pane.add(mergePanel);
		pane.add(quickPanel);
		pane.add(radixPanel);

		go.addActionListener(this);

		frame.add(pane);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		if(arg0.getSource() == go)
		{
			//checks user input
			if(Integer.parseInt(input.getText()) > 10000000 || Integer.parseInt(input.getText()) < 1000)
				JOptionPane.showMessageDialog(null,"You must enter in a value between 1000 and 100000000");
			else
			{
				try{
					userInput = Integer.parseInt(input.getText());
					runThreads();
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,"You must enter in a value between 1000 and 100000000");
				}
			}
		}
	}

	private void runThreads()
	{
		//creates array 
		Integer[] data = new Integer[userInput];

		Random generator = new Random();

		//loads array with random ints
		for(int x = 0; x < data.length; x++)
		{
			data[x] = generator.nextInt(1000000);

		}
		//creates "deep copies of array"
		Integer[] insertionData = data.clone();
		Integer[] bubbleData = data.clone();
		Integer[] mergeData = data.clone();
		Integer[] quickData = data.clone();
		Integer[] radixData = data.clone();

		//passes these copies into runnable classes
		Runnable insertionSort = new InsertionSort(insertionData, insertionBar);
		Runnable bubbleSort = new BubbleSort(bubbleData, bubbleBar);
		Runnable mergeSort = new MergeSort(mergeData, mergeBar);
		Runnable quickSort = new QuickSort(quickData, quickBar);
		Runnable radixSort = new RadixSort(radixData, radixBar);

		//instantiating threads
		Thread bubbleThread = new Thread(bubbleSort);
		Thread insertionThread = new Thread(insertionSort);
		Thread mergeThread = new Thread(mergeSort);
		Thread quickThread = new Thread(quickSort);
		Thread radixThread = new Thread(radixSort);

		//starting threads
		bubbleThread.start();
		insertionThread.start();
		mergeThread.start();
		quickThread.start();
		radixThread.start();
	}
}

