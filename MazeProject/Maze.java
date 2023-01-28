import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;

public class Maze extends JPanel implements KeyListener, MouseListener
{
	JFrame frame;
	String[][] wallArray = new String[33][73];
	Explorer exp;
	boolean firstRunCheck = false;
	ArrayList<Walls> walls;
	int shrink = 50;
	boolean draw3D = false;
	boolean canMove = true;
	Toolkit t = Toolkit.getDefaultToolkit();
	Image img = t.getImage("C:\\Users\\10015327\\Desktop\\you-win-splash-screen.jpg");

	public Maze()
	{
		frame = new JFrame();
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1600, 1000);
		frame.setVisible(true);
		frame.addKeyListener(this);
		frame.addMouseListener(this);
	}

	public void setBoard()
	{
		File name = new File("C:\\Users\\10015327\\Downloads\\maze1.txt");

		try
		{
			BufferedReader input = new BufferedReader(new FileReader(name));
			String txt;
			int count = 0;

			while((txt = input.readLine()) != null)
			{
				for(int i = 0; i < txt.length(); i++)
				{
					wallArray[count][i] = txt.substring(i, i + 1);
				}

				if(txt.indexOf("1") >= 0)
				{
					//exp = new Explorer(new Location(count, txt.indexOf("1")), 1);
				}

				System.out.println(txt);
				count++;
			}

			/*
			for(int i = 0; i < wallArray.length; i++)
			{
				for(int j = 0; j < wallArray[0].length; j++)
				{
					System.out.print(wallArray[i][j]);
				}
				System.out.println();
			}
			*/
		}
		catch(IOException e)
		{
			System.err.print("File not found");
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;

		/*
		g.setColor(Color.BLACK);
		g.fillRect(100, 100, 400, 600);

		g.setColor(Color.WHITE);
		g.fillOval(50, 50, 100, 100);

		g.setColor(Color.MAGENTA);
		g.fillOval(450, 50, 100, 100);

		g.setColor(Color.RED);
		g.fillArc(200, 200, 100, 100, 120, 180);

		g.setColor(Color.GREEN);
		g.drawOval(50, 650, 100, 100);

		g.setColor(Color.CYAN);
		g.drawOval(450, 650, 100, 100);

		frame.add(new JLabel(new ImageIcon("C:\\Users\\10015327\\Desktop\\rocket.jpg")));
		*/

		if(!draw3D)
		{
			drawMaze(0, 0, 20, g, 0);
		}
		else
		{
			for(Walls w : walls)
			{
				if(!(w.getType().equals("Front")))
				{
					g2.setPaint(w.getPaint());
					g2.fillPolygon(w.getPolygon());
					g2.setColor(w.getColor());
					g2.drawPolygon(w.getPolygon());
				}
			}
			for(int i = walls.size() - 1; i >= 0; i--)
			{
				if(walls.get(i).getType().equals("Front"))
				{
					g2.setPaint(walls.get(i).getPaint());
					g2.fillPolygon(walls.get(i).getPolygon());
					g2.setColor(walls.get(i).getColor());
					g2.drawPolygon(walls.get(i).getPolygon());
				}
			}
			drawMaze(300, 900, 7, g, 1);
		}
		if(wallArray[exp.location.getR()][exp.location.getC()].equals("E"))
		{
			//g2.setColor(Color.WHITE);
			//g2.fillRect(100, 100, 300, 300);
			g2.drawImage(img, 500, 150, this);
			//printEndScreen(g2);
			canMove = false;
			System.out.println("Finished");
		}
	}

	public void drawMaze(int iInitial, int jInitial, int size, Graphics g, int checkVar)
	{
		Graphics2D g2 = (Graphics2D)g;
		for(int i = 0; i < wallArray.length; i++)
		{
			for(int j = 0; j < wallArray[0].length; j++)
			{
				if(wallArray[i][j].equals("#"))
				{
					g.setColor(Color.BLACK);
					//g.fillRect(j*20, i*20, 20, 20);
				}
				else if(wallArray[i][j].equals("E"))
				{
					g.setColor(Color.GREEN);
					//g.fillRect(j*20, i*20, 20, 20);
				}
				else if(wallArray[i][j].equals("1") && firstRunCheck == false)
				{
					exp = new Explorer(new Location(i, j), 1);
					firstRunCheck = true;
					//g.setColor(Color.RED);
					//g.fillRect(j*20, i*20, 20, 20);
				}
				else
				{
					//if(wallArray[i][j].equals(" "))
					g.setColor(Color.GRAY);
					//g.fillRect(j*20, i*20, 20, 20);
				}
				g.fillRect(jInitial+(j*size), iInitial+(i*size), size, size);
			}
		}
		//exp = new Explorer(new Location(20, 20), 1);
		g.setColor(exp.getColor());
		g2.fill(exp.getRect(checkVar, iInitial, jInitial));
	}

	public void printEndScreen(Graphics2D g2)
	{
		g2.setColor(Color.WHITE);
		g2.fillRect(100, 100, 300, 300);
		g2.drawImage(img, 500, 150, null);
	}

	public void createWalls()
	{
		walls = new ArrayList<Walls>();


		for(int n = 0; n < 5; n++)
		{
			walls.add(getCeiling(n));
			walls.add(getFloor(n));
			walls.add(getLeftPath(n+1));
			walls.add(getLeftCeil(n));
			walls.add(getLeftFloor(n));
			walls.add(getRightPath(n+1));
			walls.add(getRightCeil(n));
			walls.add(getRightFloor(n));
		}

		int expR = exp.getLoc().getR();
		int expC = exp.getLoc().getC();
		int expDir = exp.getDir();

		switch(expDir)
		{
			case 0:
				for(int n = 0; n < 5; n++)
				{
					try
					{
						if((wallArray[expR + n][expC]).equals("#"))
						{
							walls.add(getFront(n));
						}
						if((wallArray[expR + n][expC + 1]).equals("#"))
						{
							walls.add(getLeft(n));
						}
						if((wallArray[expR + n][expC - 1]).equals("#"))
						{
							walls.add(getRight(n));
						}
					}
					catch(ArrayIndexOutOfBoundsException e)
					{
						e.printStackTrace();
					}
				}
			break;

			case 1:
				for(int n = 0; n < 5; n++)
				{
					try
					{
						if((wallArray[expR][expC + n]).equals("#"))
						{
							walls.add(getFront(n));
						}
						if((wallArray[expR + 1][expC + n]).equals("#"))
						{
							walls.add(getRight(n));
						}
						if((wallArray[expR - 1][expC + n]).equals("#"))
						{
							walls.add(getLeft(n));
						}
					}
					catch(ArrayIndexOutOfBoundsException e)
					{
						e.printStackTrace();
					}
				}
			break;

			case 2:
				for(int n = 0; n < 5; n++)
				{
					try
					{
						if((wallArray[expR - n][expC]).equals("#"))
						{
							walls.add(getFront(n));
						}
						if((wallArray[expR - n][expC - 1]).equals("#"))
						{
							walls.add(getLeft(n));
						}
						if((wallArray[expR - n][expC + 1]).equals("#"))
						{
							walls.add(getRight(n));
						}
					}
					catch(ArrayIndexOutOfBoundsException e)
					{
						e.printStackTrace();
					}
				}
			break;

			case 3:
				for(int n = 0; n < 5; n++)
				{
					try
					{
						if((wallArray[expR][expC - n]).equals("#"))
						{
							walls.add(getFront(n));
						}
						if((wallArray[expR - 1][expC - n]).equals("#"))
						{
							walls.add(getRight(n));
						}
						if((wallArray[expR + 1][expC - n]).equals("#"))
						{
							walls.add(getLeft(n));
						}
					}
					catch(ArrayIndexOutOfBoundsException e)
					{
						e.printStackTrace();
					}
				}
			break;
		}
		walls.add(getFront(5));
	}

	public Walls getRightCeil(int n)
	{
		int[] rLocs = {100 + n*shrink, 150 + n*shrink, 150 + n*shrink};
		int[] cLocs = {700 - n*shrink, 700 - n*shrink, 650 - n*shrink};

		return new Walls(rLocs, cLocs, "RightCeil", 255-n*shrink, 255-n*shrink, 255-n*shrink);
	}

	public Walls getRightFloor(int n)
	{
		int[] rLocs = {700 - n*shrink, 650 - n*shrink, 650 - n*shrink};
		int[] cLocs = {700 - n*shrink, 700 - n*shrink, 650 - n*shrink};

		return new Walls(rLocs, cLocs, "RightFloor", 255-n*shrink, 255-n*shrink, 255-n*shrink);
	}

	public Walls getRightPath(int n)
	{
		int[] rLocs = {100 + n*shrink, 100 + n*shrink, 700 - n*shrink, 700 - n*shrink};
		int[] cLocs = {700 - n*shrink, 750 - n*shrink, 750 - n*shrink, 700 - n*shrink};

		return new Walls(rLocs, cLocs, "RightPath", 255-n*shrink, 255-n*shrink, 255-n*shrink);
	}

	public Walls getLeftCeil(int n)
	{
		int[] rLocs = {100 + n*shrink, 150 + n*shrink, 150 + n*shrink};
		int[] cLocs = {100 + n*shrink, 150 + n*shrink, 100 + n*shrink};

		return new Walls(rLocs, cLocs, "LeftCeil", 255-n*shrink, 255-n*shrink, 255-n*shrink);
	}

	public Walls getLeftFloor(int n)
	{
		int[] rLocs = {700 - n*shrink, 650 - n*shrink, 650 - n*shrink};
		int[] cLocs = {100 + n*shrink, 100 + n*shrink, 150 + n*shrink};

		return new Walls(rLocs, cLocs, "LeftFloor", 255-n*shrink, 255-n*shrink, 255-n*shrink);
	}

	public Walls getLeftPath(int n)
	{
		int[] rLocs = {100 + n*shrink, 100 + n*shrink, 700 - n*shrink, 700 - n*shrink};
		int[] cLocs = {50 + n*shrink, 100 + n*shrink, 100 + n*shrink, 50 + n*shrink};

		return new Walls(rLocs, cLocs, "LeftPath", 255-n*shrink, 255-n*shrink, 255-n*shrink);
	}

	public Walls getFront(int n)
	{
		int[] rLocs = {100 + n*shrink, 100 + n*shrink, 700 - n*shrink, 700 - n*shrink};
		int[] cLocs = {100 + n*shrink, 700 - n*shrink, 700 - n*shrink, 100 + n*shrink};

		return new Walls(rLocs, cLocs, "Front", 255-n*shrink, 255-n*shrink, 255-n*shrink);
	}

	public Walls getLeft(int n)
	{
		int[] rLocs = {100 + n*shrink, 150 + n*shrink, 650 - n*shrink, 700 - n*shrink};
		int[] cLocs = {100 + n*shrink, 150 + n*shrink, 150 + n*shrink, 100 + n*shrink};

		return new Walls(rLocs, cLocs, "Left", 255-n*shrink, 255-n*shrink, 255-n*shrink);
	}

	public Walls getRight(int n)
	{
		int[] rLocs = {100 + n*shrink, 150 + n*shrink, 650 - n*shrink, 700 - n*shrink};
		int[] cLocs = {700 - n*shrink, 650 - n*shrink, 650 - n*shrink, 700 - n*shrink};

		return new Walls(rLocs, cLocs, "Right", 255-n*shrink, 255-n*shrink, 255-n*shrink);
	}

	public Walls getCeiling(int n)
	{
		int[] rLocs = {100 + n*shrink, 150 + n*shrink, 150 + n*shrink, 100 + n*shrink};
		int[] cLocs = {100 + n*shrink, 150 + n*shrink, 650 - n*shrink, 700 - n*shrink};

		return new Walls(rLocs, cLocs, "Ceiling", 255-n*shrink, 255-n*shrink, 255-n*shrink);
	}

	public Walls getFloor(int n)
	{
		int[] rLocs = {700 - n*shrink, 650 - n*shrink, 650 - n*shrink, 700 - n*shrink};
		int[] cLocs = {100 + n*shrink, 150 + n*shrink, 650 - n*shrink, 700 - n*shrink};

		return new Walls(rLocs, cLocs, "Floor", 255-n*shrink, 255-n*shrink, 255-n*shrink);
	}

	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == 32)
			draw3D = !draw3D;
		if(canMove)
			exp.move(e.getKeyCode(), wallArray);
		if(draw3D)
			createWalls();
		repaint();
	}

	public void keyReleased(KeyEvent e)
	{

	}

	public void keyTyped(KeyEvent e)
	{

	}

	public void mouseClicked(MouseEvent e)
	{

	}

	public void mousePressed(MouseEvent e)
	{

	}

	public void mouseReleased(MouseEvent e)
	{

	}

	public void mouseEntered(MouseEvent e)
	{

	}

	public void mouseExited(MouseEvent e)
	{

	}

	public static void main(String[]args)
	{
		Maze app = new Maze();
		app.setBoard();
	}
}