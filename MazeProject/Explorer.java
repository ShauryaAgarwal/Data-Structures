import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;
import java.awt.image.*;
import java.applet.*;

public class Explorer
{
	Location location;
	int direction; // 0 = up, 1 = right, 2 = down, 3 = left
	int size;
	Color color;

	public Explorer(Location l, int d)
	{
		location = l;
		direction = d;
		size = 20;
		color = Color.RED;
	}

	public Color getColor()
	{
		return color;
	}

	public Location getLoc()
	{
		return location;
	}

	public int getDir()
	{
		return direction;
	}

	public Rectangle getRect(int check, int i, int j)
	{
		if(check==0)
			return new Rectangle(location.getC()*size, location.getR()*size, size, size);
		return new Rectangle(j+location.getC()*7, i+location.getR()*7, 7, 7);
	}

	public void move(int keyCode, String[][] maze)
	{
		/*
		if(keyCode == 37)
		{
			if(!(maze[location.getR()][location.getC() - 1].equals("#")))
				location.setC(-1);
		}
		if(keyCode == 38)
		{
			if(!(maze[location.getR() - 1][location.getC()].equals("#")))
				location.setR(-1);
		}
		if(keyCode == 39)
		{
			if(maze[location.getR()][location.getC() + 1].equals("E"))
			{
				location.setC(1);
			}
			else if(!(maze[location.getR()][location.getC() + 1].equals("#")))
				location.setC(1);
		}
		if(keyCode == 40)
		{
			if(!(maze[location.getR() + 1][location.getC()].equals("#")))
				location.setR(1);
		}
		*/
		if(keyCode == 37)
		{
			//counterclockwise
			direction = (direction + 1)%4;
		}
		if(keyCode == 38)
		{
			System.out.println(direction);
			if(direction == 3 && !(maze[location.getR()][location.getC() - 1].equals("#")))
			{
				location.setC(-1);
			}
			if(direction == 2 && !(maze[location.getR() - 1][location.getC()].equals("#")))
			{
				location.setR(-1);
			}
			if(direction == 1 && !(maze[location.getR()][location.getC() + 1].equals("#")))
			{
				location.setC(1);
			}
			if(direction == 0 && !(maze[location.getR() + 1][location.getC()].equals("#")))
			{
				location.setR(1);
			}
		}
		if(keyCode == 39)
		{
			//clockwise
			if(direction == 0)
				direction = 3;
			else
				direction = (direction - 1)%4;
		}
	}

	public static void main(String[]args)
	{

	}
}