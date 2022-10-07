import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Minesweeper extends JFrame implements ActionListener, MouseListener
{
	JPanel boardPanel;
	JToggleButton[][] board;
	int dimR = 9, dimC = 9;
	ImageIcon flag, mine, smile, loseSmile, winSmile, waitSmile, incorrectMine, one, two, three, four, five, six, seven, eight;
	ImageIcon[] numberIcons;
	boolean firstClick = true, gameOn = true;
	int numMines = 10, numNotClicked = 81;
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem menuItem1, menuItem2, menuItem3;

	public Minesweeper()
	{
		flag = new ImageIcon("C:\\Users\\10015327\\Downloads\\Minesweeper Images\\flag.png");
		flag = new ImageIcon(flag.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

		mine = new ImageIcon("C:\\Users\\10015327\\Downloads\\Minesweeper Images\\mine.png");
		mine = new ImageIcon(mine.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

		smile = new ImageIcon("C:\\Users\\10015327\\Downloads\\Minesweeper Images\\smile0.png");
		smile = new ImageIcon(smile.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

		loseSmile = new ImageIcon("C:\\Users\\10015327\\Downloads\\Minesweeper Images\\dead0.png");
		loseSmile = new ImageIcon(loseSmile.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

		winSmile = new ImageIcon("C:\\Users\\10015327\\Downloads\\Minesweeper Images\\win0.png");
		winSmile = new ImageIcon(winSmile.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

		waitSmile = new ImageIcon("C:\\Users\\10015327\\Downloads\\Minesweeper Images\\wait0.png");
		waitSmile = new ImageIcon(waitSmile.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

		//incorrectMine = new ImageIcon();
		//incorrectMine = new ImageIcon(incorrectMine.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));

		one = new ImageIcon("C:\\Users\\10015327\\Downloads\\Minesweeper Images\\1.png");
		one = new ImageIcon(one.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

		two = new ImageIcon("C:\\Users\\10015327\\Downloads\\Minesweeper Images\\2.png");
		two = new ImageIcon(two.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

		three = new ImageIcon("C:\\Users\\10015327\\Downloads\\Minesweeper Images\\3.png");
		three = new ImageIcon(three.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

		four = new ImageIcon("C:\\Users\\10015327\\Downloads\\Minesweeper Images\\4.png");
		four = new ImageIcon(four.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

		five = new ImageIcon("C:\\Users\\10015327\\Downloads\\Minesweeper Images\\5.png");
		five = new ImageIcon(five.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

		six = new ImageIcon("C:\\Users\\10015327\\Downloads\\Minesweeper Images\\6.png");
		six = new ImageIcon(six.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

		seven = new ImageIcon("C:\\Users\\10015327\\Downloads\\Minesweeper Images\\7.png");
		seven = new ImageIcon(seven.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

		eight = new ImageIcon("C:\\Users\\10015327\\Downloads\\Minesweeper Images\\8.png");
		eight = new ImageIcon(eight.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

		menuBar = new JMenuBar();
		menu = new JMenu("Difficulty");
		menu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(menu);
		menu.addSeparator();

		menuItem1 = new JMenuItem("Beginner", KeyEvent.VK_T);
		//menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem1.addActionListener(this);
		menu.add(menuItem1);
		menuItem2 = new JMenuItem("Intermediate", KeyEvent.VK_T);
		//menuItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem2.addActionListener(this);
		menu.add(menuItem2);
		menuItem3 = new JMenuItem("Expert", KeyEvent.VK_T);
		//menuItem3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem3.addActionListener(this);
		menu.add(menuItem3);

		/*
		JRadioButtonMenuItem rbMenuItem1 = new JRadioButtonMenuItem("Beginner");
		menu.add(rbMenuItem1);
		JRadioButtonMenuItem rbMenuItem2 = new JRadioButtonMenuItem("Intermediate");
		menu.add(rbMenuItem1);
		JRadioButtonMenuItem rbMenuItem3 = new JRadioButtonMenuItem("Expert");
		menu.add(rbMenuItem1);
		*/
		this.setJMenuBar(menuBar);

		createBoard(dimR, dimC);
		this.setVisible(true);
	}

	public void createBoard(int row, int col)
	{
		if(boardPanel != null)
		{
			this.remove(boardPanel);
		}

		board = new JToggleButton[row][col];
		boardPanel = new JPanel();
		boardPanel.setLayout(new GridLayout(row, col));

		for(int r = 0; r < board.length; r++)
		{
			for(int c = 0; c < board[r].length; c++)
			{
				board[r][c] = new JToggleButton();
				board[r][c].putClientProperty("row", r);
				board[r][c].putClientProperty("col", c);
				board[r][c].putClientProperty("state", 0);
				board[r][c].addMouseListener(this);
				board[r][c].setText("");
				boardPanel.add(board[r][c]);
			}
		}
		firstClick = true;
		gameOn = true;
		//timePassed = 0;
		numNotClicked = dimR*dimC;

		this.add(boardPanel);
		this.setSize(board[0].length*50, board.length*50);
		this.revalidate();
	}

	public void setBombsAndNums(int selectedRow, int selectedCol)
	{
		int count = numMines;

		while(count > 0)
		{
			int row = (int)(Math.random()*dimR);
			int col = (int)(Math.random()*dimC);
			int state = Integer.parseInt("" + board[row][col].getClientProperty("state"));

			if(state == 0 && (row < selectedRow - 1 || row > selectedRow + 1 || col < selectedCol - 1 || col > selectedCol + 1))
			{
				board[row][col].putClientProperty("state", -1);
				//board[row][col].setText("-1");
				count--;
			}
		}

		for(int r = 0; r < dimR; r++)
		{
			for(int c = 0; c < dimC; c++)
			{
				count = 0;
				int state = Integer.parseInt("" + board[r][c].getClientProperty("state"));

				if(state != -1)
				{
					for(int smallR = r - 1; smallR <= r + 1; smallR++)
					{
						for(int smallC = c - 1; smallC <= c + 1; smallC++)
						{
							try
							{
								state = Integer.parseInt("" + board[smallR][smallC].getClientProperty("state"));

								if(state == -1 && (smallR != r || smallC != c))
								{
									count++;
								}
							}
							catch(ArrayIndexOutOfBoundsException e)
							{

							}
						}
					}
					board[r][c].putClientProperty("state", count);
					//board[r][c].setText("" + count);
				}
			}
		}
	}

	public void click(int row, int col)
	{
		if(!board[row][col].isSelected())
		{
			board[row][col].setSelected(true);
		}

		int state = (int)board[row][col].getClientProperty("state");

		if(state != 0)
		{
			//board[row][col].setText("" + state);
			if(board[row][col].getIcon() == null)
			{
				board[row][col].setIcon(flag);
				board[row][col].setEnabled(false);
				if(state == 1)
				{
					board[row][col].setDisabledIcon(one);
				}
				else if(state == 2)
				{
					board[row][col].setDisabledIcon(two);
				}
				else if(state == 3)
				{
					board[row][col].setDisabledIcon(three);
				}
				else if(state == 4)
				{
					board[row][col].setDisabledIcon(four);
				}
				else if(state == 5)
				{
					board[row][col].setDisabledIcon(five);
				}
				else if(state == 6)
				{
					board[row][col].setDisabledIcon(six);
				}
				else if(state == 7)
				{
					board[row][col].setDisabledIcon(seven);
				}
				else if(state == 8)
				{
					board[row][col].setDisabledIcon(eight);
				}
			}
			else
			{
				board[row][col].setIcon(null);
				board[row][col].setEnabled(false);
			}
		}
		else
		{
			for(int smallR = row - 1; smallR <= row + 1; smallR++)
			{
				for(int smallC = col - 1; smallC <= col + 1; smallC++)
				{
					try
					{
						if(!board[smallR][smallC].isSelected())
						{
							click(smallR, smallC);
							board[smallR][smallC].setEnabled(false);
						}
					}
					catch(ArrayIndexOutOfBoundsException e)
					{

					}
				}
			}
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == menuItem1)
		{
			numMines = 10;
			dimR = 9;
			dimC = 9;
		}
		if(e.getSource() == menuItem2)
		{
			numMines = 35;
			dimR = 16;
			dimC = 16;
		}
		if(e.getSource() == menuItem3)
		{
			numMines = 99;
			dimR = 16;
			dimC = 35;
		}
		numNotClicked = dimR*dimC;
		createBoard(dimR, dimC);
	}

	public void mouseReleased(MouseEvent e)
	{
		int row = (int)((JToggleButton)e.getComponent()).getClientProperty("row");
		int col = (int)((JToggleButton)e.getComponent()).getClientProperty("col");

		if(gameOn)
		{
			if(e.getButton() == MouseEvent.BUTTON1)
			{
				if(firstClick)
				{
					setBombsAndNums(row, col);
					firstClick = false;
				}

				int state = (int)board[row][col].getClientProperty("state");

				if(state == -1)
				{
					for(int r = 0; r < dimR; r++)
					{
						for(int c = 0; c < dimC; c++)
						{
							if((int)board[r][c].getClientProperty("state") == -1)
							{
								click(r, c);
								if(r == row && c == col)
								{
									board[r][c].setContentAreaFilled(false);
									board[r][c].setOpaque(true);
									board[r][c].setBackground(Color.RED);
								}
								board[r][c].setIcon(mine);
								board[r][c].setDisabledIcon(mine);
							}
							else if(!board[r][c].isSelected())
							{
								click(r, c);
							}
							board[r][c].setEnabled(false);
						}
					}
					gameOn = false;
				}
				else
				{
					click(row, col);
				}
			}
			else if(e.getButton() == MouseEvent.BUTTON3)
			{
				if(!board[row][col].isSelected())
				{
					if(board[row][col].getIcon() == null)
					{
						board[row][col].setIcon(flag);
						board[row][col].setDisabledIcon(flag);
						board[row][col].setEnabled(false);
					}
					else
					{
						board[row][col].setIcon(null);
						board[row][col].setEnabled(true);
					}
				}
			}
		}
	}

	public void mouseClicked(MouseEvent e)
	{

	}

	public void mousePressed(MouseEvent e)
	{

	}

	public void mouseExited(MouseEvent e)
	{

	}

	public void mouseEntered(MouseEvent e)
	{

	}

	public static void main(String[]args)
	{
		Minesweeper app = new Minesweeper();
	}
}