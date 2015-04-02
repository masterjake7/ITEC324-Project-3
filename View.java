package Project3;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class View implements ActionListener
{
	
	private boolean oneDone = false;
	private boolean twoDone = false;
	private boolean threeDone = false;
	private boolean fourDone = false;
	private boolean fiveDone = false;
	
	private JFrame frame = new JFrame("Project 3");
	private JPanel pane = new JPanel();
	
	private FlowLayout layout = new FlowLayout();
	
	private JButton go = new JButton("Go");
	
	private JTextField input = new JTextField();
	
	private JProgressBar bubbleBar = new JProgressBar();
	private JProgressBar insertionBar = new JProgressBar();
	private Timer t;
	
	private int userInput;
	
	private JLabel error = new JLabel("You must enter in a value between 1000 and 100000000.");
	private JLabel bubbleLabel = new JLabel("Bubble Sort:");
	private JLabel insertionLabel = new JLabel("Insertion Sort:");
	
	public View()
	{
		//GUI using flow layout
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		pane.setLayout(layout);
		
		input.setPreferredSize(new Dimension(150, 25));
		
		pane.add(error);
		pane.add(input);
		pane.add(go);
		pane.add(bubbleLabel);
		pane.add(bubbleBar);
		pane.add(insertionLabel);
		pane.add(insertionBar);
		
		go.addActionListener(this);
		
		frame.add(pane);
		frame.pack();
		error.setText("");
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		if(arg0.getSource() == go)
		{
			//checks user input
			if(Integer.parseInt(input.getText()) > 10000000 || Integer.parseInt(input.getText()) < 1000)
				error.setText("You must enter in a value between 1000 and 100000000.");
			else
			{
				userInput = Integer.parseInt(input.getText());
				error.setText("");
				//look at runThreads method, private helper method below
				runThreads();
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
		Integer[] dataClone = data.clone();
		Integer[] dataCloneTwo = data.clone();
		
		//passes these copies into runnable classes
		Runnable insertionS = new InsertionSort(dataClone, insertionBar);
		Runnable bubbleS = new BubbleSort(dataCloneTwo, bubbleBar);
		
		//instantiating threads
		Thread thread1 = new Thread(bubbleS);
		Thread thread2 = new Thread(insertionS);
		
		//starting threads
		thread1.start();
		thread2.start();
		
		
	}	
	
	//We need to figure out a way to update the progress bars. I have a method in InsertionSort and BubbleSort
	//classes that will return an int between 0 and 100 of the progress
	
}

