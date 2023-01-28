import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.sound.sampled.*;
import java.net.*;
import java.io.*;
import javax.swing.filechooser.*;

public class MusicBox extends JFrame implements Runnable, ActionListener, AdjustmentListener
{
	JToggleButton[][] box;
	JPanel boardPanel, menuButtonPanel, tempoPanel;
	JFrame frame;
	int dimR = 37, dimC = 50, colCounter = 0, tempo = 200;
	String[] clipNames = new String[]{"C4", "B4", "AS4", "A4", "GS3", "G3", "FS3", "F3", "E3", "DS3", "D3", "CS3", "C3", "B3", "AS3", "A3", "GS2", "G2", "FS2", "F2", "E2", "DS2", "D2", "CS2", "C2", "B2", "AS2", "A2", "GS1", "G1", "FS1", "F1", "E1", "DS1", "D1", "CS1", "C1"};;
	String[] instrumentNames = new String[]{"Bell", "Glockenspiel", "Marimba", "Oboe", "Oh_Ah", "Piano"};
	JScrollPane scrollPane;
	Thread timing;
	Clip[] clip;
	JMenuBar menuBar;
	JToggleButton playStopButton, clearButton;
	boolean currentlyPlaying = false;
	JMenu instrumentMenu, fileMenu;
	JMenuItem bellItem, glockenspielItem, marimbaItem, oboeItem, ohAhItem, pianoItem, saveItem, loadItem;
	JScrollBar tempoBar;
	JLabel tempoLabel;
	String currentDirectory;
	JFileChooser fileChooser;
	//Font font = new Font("Times New Roman", Font.PLAIN, 10);

	public MusicBox()
	{
		frame = new JFrame("MusicBox");
		menuBar = new JMenuBar();
		menuButtonPanel = new JPanel();
		tempoPanel = new JPanel();
		tempoLabel = new JLabel("Tempo: " + tempo);
		tempoBar = new JScrollBar(JScrollBar.HORIZONTAL, tempo, 0, 50, 350);
		tempoBar.addAdjustmentListener(this);
		fileMenu = new JMenu("File");
		saveItem = new JMenuItem("Save", KeyEvent.VK_T);
		loadItem = new JMenuItem("Load", KeyEvent.VK_T);
		saveItem.addActionListener(this);
		loadItem.addActionListener(this);
		fileMenu.add(saveItem);
		fileMenu.add(loadItem);
		instrumentMenu = new JMenu("Instruments");
		menuButtonPanel.setLayout(new GridLayout(1, 2));
		playStopButton = new JToggleButton();
		playStopButton.addActionListener(this);
		playStopButton.setText("Play");
		clearButton = new JToggleButton();
		clearButton.addActionListener(this);
		clearButton.setText("Clear");
		tempoPanel.setLayout(new BorderLayout());
		tempoPanel.add(tempoLabel, BorderLayout.WEST);
		tempoPanel.add(tempoBar, BorderLayout.CENTER);
		menuButtonPanel.add(playStopButton);
		menuButtonPanel.add(clearButton);
		menuBar.add(fileMenu);
		menuBar.add(menuButtonPanel);

		bellItem = new JMenuItem("Bell", KeyEvent.VK_T);
		bellItem.putClientProperty("Instrument", "Bell");
		bellItem.addActionListener(this);
		instrumentMenu.add(bellItem);
		glockenspielItem = new JMenuItem("Glockenspiel", KeyEvent.VK_T);
		glockenspielItem.putClientProperty("Instrument", "Glockenspiel");
		glockenspielItem.addActionListener(this);
		instrumentMenu.add(glockenspielItem);
		marimbaItem = new JMenuItem("Marimba", KeyEvent.VK_T);
		marimbaItem.putClientProperty("Instrument", "Marimba");
		marimbaItem.addActionListener(this);
		instrumentMenu.add(marimbaItem);
		oboeItem = new JMenuItem("Oboe", KeyEvent.VK_T);
		oboeItem.putClientProperty("Instrument", "Oboe");
		oboeItem.addActionListener(this);
		instrumentMenu.add(oboeItem);
		ohAhItem = new JMenuItem("Oh_Ah", KeyEvent.VK_T);
		ohAhItem.putClientProperty("Instrument", "Oh_Ah");
		ohAhItem.addActionListener(this);
		instrumentMenu.add(ohAhItem);
		pianoItem = new JMenuItem("Piano", KeyEvent.VK_T);
		pianoItem.putClientProperty("Instrument", "Piano");
		pianoItem.addActionListener(this);
		instrumentMenu.add(pianoItem);
		menuBar.add(instrumentMenu);

		currentDirectory = System.getProperty("user.dir");
		fileChooser = new JFileChooser("currentDirectory");
		createBoard(dimR, dimC);
		frame.add(tempoPanel, BorderLayout.SOUTH);
		frame.add(menuBar, BorderLayout.NORTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		timing = new Thread(this);
		timing.start();
	}

	public void createBoard(int row, int col)
	{
		if(boardPanel != null)
		{
			frame.remove(boardPanel);
		}
		box = new JToggleButton[row][col];
		boardPanel = new JPanel();
		boardPanel.setLayout(new GridLayout(row, col));
		scrollPane = new JScrollPane(boardPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		for(int r = 0; r < box.length; r++)
		{
			for(int c = 0; c < box[r].length; c++)
			{
				box[r][c] = new JToggleButton();
				box[r][c].setPreferredSize(new Dimension(30, 30));
				box[r][c].setMargin(new Insets(0, 0, 0, 0));
				if(!clipNames[r].contains("S"))
					box[r][c].setText(clipNames[r]);
				else
					box[r][c].setText(clipNames[r].substring(0, 1) + "#" + clipNames[r].substring(2, 3));
				boardPanel.add(box[r][c]);
			}
		}

		frame.setSize(30*col, 20*row);
		//frame.add(boardPanel, BorderLayout.CENTER);
		frame.add(scrollPane, BorderLayout.CENTER);

		clip = new Clip[clipNames.length];

		/*
		String initInstrument = instrumentNames[0];
		try
		{
			for(int x = 0; x < clipNames.length; x++)
			{
				URL url = this.getClass().getClassLoader().getResource(initInstrument + "\\" + initInstrument + " - " + clipNames[x] + ".wav");
				System.out.println(url);
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
				clip[x] = AudioSystem.getClip();
				clip[x].open(audioIn);
			}
		}
		catch(UnsupportedAudioFileException|IOException|LineUnavailableException e)
		{

		}
		*/
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == playStopButton)
		{
			if(currentlyPlaying)
			{
				currentlyPlaying = false;
				playStopButton.setText("Play");
			}
			else
			{
				currentlyPlaying = true;
				playStopButton.setText("Stop");
			}
		}
		else if(e.getSource() == clearButton)
		{
			for(int r = 0; r < box.length; r++)
			{
				for(int c = 0; c < box[r].length; c++)
				{
					if(box[r][c].isSelected())
					{
						box[r][c].setSelected(false);
					}
				}
			}
			reset();
		}
		else if(e.getSource() == saveItem)
		{
			reset();
			saveSong();
		}
		else if(e.getSource() == loadItem)
		{
			reset();
			loadFile();
		}
		else
		{
			loadTones((String)((JMenuItem)e.getSource()).getClientProperty("Instrument"));
			reset();
		}
	}

	public void adjustmentValueChanged(AdjustmentEvent evt)
	{
		if(evt.getSource() == tempoBar)
		{
			tempo = tempoBar.getValue();
			tempoLabel.setText("Tempo: " + tempo);
		}
	}

	public void run()
	{
		while(true)
		{
			if(!currentlyPlaying)
			{
				try
				{
					Thread.sleep(0);
				}
				catch(InterruptedException e)
				{

				}
			}
			else if(clip[0] != null)
			{
				try
				{
					for(int i = 0; i < box.length; i++)
					{
						if(box[i][colCounter].isSelected())
						{
							clip[i].start();
						}
					}
					Thread.sleep((60000/tempo)/2);
					for(int i = 0; i < box.length; i++)
					{
						if(box[i][colCounter].isSelected())
						{
							clip[i].stop();
							clip[i].setMicrosecondPosition(0);
						}
					}
					colCounter++;
					if(colCounter == 50)
					{
						colCounter = 0;
					}
				}
				catch(InterruptedException e)
				{

				}
			}
		}
	}

	public void reset()
	{
		if(playStopButton.isSelected())
		{
			playStopButton.setSelected(false);
			playStopButton.setText("Play");
			currentlyPlaying = false;
		}
		clearButton.setSelected(false);
		colCounter = 0;
	}

	public void loadTones(String instrument)
	{
		try
		{
			for(int x = 0; x < clipNames.length; x++)
			{
				URL url = this.getClass().getClassLoader().getResource(instrument + "\\" + instrument + " - " + clipNames[x] + ".wav");
				System.out.println(url);
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
				clip[x] = AudioSystem.getClip();
				clip[x].open(audioIn);
			}
		}
		catch(UnsupportedAudioFileException|IOException|LineUnavailableException|NullPointerException e)
		{

		}
	}

	public void saveSong()
	{
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", ".txt", "sent");
		fileChooser.setFileFilter(filter);

		if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			File file = fileChooser.getSelectedFile();
			try
			{
				String str = file.getAbsolutePath();
				if(str.substring(str.length()-4, str.length()).equals(".txt"))
				{
					str = str.substring(0, str.length()-4);
				}
				String currSong = "";
				String[] noteNames = {" ","c ","b ","a-","a ","g-","g ","f-","f ","e ","d-","d ","c-","c ","b ","a-","a ","g-","g ", "f-","f ","e ","d-","d ","c-","c ","b ","a-","a ","g-","g ","f-","f ","e ","d-","d ","c-","c"};
				for(int r = 0; r < box.length; r++)
				{
					if(r == 0)
					{
						currSong += tempo + " " + dimC + "\n";
						for(int x = 0; x < box[0].length; x++)
						{
							currSong += " ";
						}
					}
					else
					{
						currSong += noteNames[r];
						for(int c = 0; c < box[r].length; c++)
						{
							if(box[r-1][c].isSelected())
								currSong += "x";
							else
								currSong += "-";
						}
					}
					currSong += "\n";
				}
				BufferedWriter outputStream = new BufferedWriter(new FileWriter(str + ".txt"));
				outputStream.write(currSong);
                outputStream.close();
			}
			catch(IOException e)
			{

			}
		}
	}

	public void loadFile()
	{
		if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
		{
			try
			{
				File loadFile = fileChooser.getSelectedFile();
				BufferedReader input = new BufferedReader(new FileReader(loadFile));
				String temp = input.readLine();
				String[] tempArr = temp.split(" ");
				tempo = Integer.valueOf(tempArr[0]);
				tempoLabel.setText("Tempo: " + tempo);
				tempoBar.setValue(tempo);
				dimC = Integer.valueOf(tempArr[1]);
				Character[][] song = new Character[37][dimC];
				int tempR = 0;
				//System.out.println("song: " + song.length + ", " + song[0].length);
				while((temp = input.readLine()) != null)
				{
					for(int c = 2; c < dimC + 2; c++)
					{
						song[tempR][c - 2] = temp.charAt(c - 2);
					}
					tempR++;
				}
				setNotes(song);
			}
			catch(IOException e)
			{

			}
			reset();
		}
	}

	public void setNotes(Character[][] charArray)
	{
		scrollPane.remove(boardPanel);

	    boardPanel = new JPanel();
	    boardPanel.setLayout(new GridLayout(box.length, box[0].length));
	    box = new JToggleButton[37][charArray[0].length];
	    for(int r = 0; r < box.length; r++)
	    {
			String name = clipNames[r].replaceAll("S", "#");
	        for(int c = 0; c < box[0].length; c++)
	        {
	            box[r][c] = new JToggleButton();
	            //box[r][c].setFont(font);
	            box[r][c].setText(name);
	            box[r][c].setPreferredSize(new Dimension(30, 30));
	            box[r][c].setMargin(new Insets(0, 0, 0, 0));
	            boardPanel.add(box[r][c]);
	        }
	   	}
	    frame.remove(scrollPane);
	    scrollPane = new JScrollPane(boardPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	    frame.add(scrollPane, BorderLayout.CENTER);

	    for(int r = 0; r < box.length; r++)
	    {
			for(int c = 0; c < box[0].length; c++)
			{
				System.out.println(charArray[r][c]);
	          	try
	          	{
	              	if(charArray[r][c] == 'x')
	                   	box[r][c].setSelected(true);
	              	else
	                  	box[r][c].setSelected(false);
	          	}
	          	catch (NullPointerException npe)
	          	{

	           	}
	           	catch(ArrayIndexOutOfBoundsException ae)
	           	{

	          	}
	       	}
	   	}
	 	frame.revalidate();
    }

	public static void main(String[]args)
	{
		MusicBox musicBox = new MusicBox();
	}
}