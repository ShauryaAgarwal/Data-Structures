import java.awt.*;

public class Walls
{
	private int[] rows;
	private int[] cols;
	private String type;
	private int red, green, blue;
	private int dist;

	public Walls(int[] r, int[] c, String t, int red, int green, int blue)
	{
		rows = r;
		cols = c;
		type = t;
		dist = 50;
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public String getType()
	{
		return type;
	}

	public GradientPaint getPaint()
	{
		int endR = red-dist;
		int endG = green-dist;
		int endB = blue-dist;

		if(red < 0)
		{
			red = 0;
		}
		if(green < 0)
		{
			green = 0;
		}
		if(blue < 0)
		{
			blue = 0;
		}
		if(endR < 0)
		{
			endR = 0;
		}
		if(endG < 0)
		{
			endG = 0;
		}
		if(endB < 0)
		{
			endB = 0;
		}

		if(this.type.indexOf("Left") >= 0 || this.type.indexOf("Right") >= 0)
		{
			return new GradientPaint(cols[0], rows[0], new Color(red, green, blue), cols[1], rows[0], new Color(endR, endG, endB));
		}

		return new GradientPaint(cols[0], rows[0], new Color(red, green, blue), cols[0], rows[1], new Color(endR, endG, endB));
	}

	public Color getColor()
	{
		return new Color(red, green, blue);
	}

	public Polygon getPolygon()
	{
		return new Polygon(cols, rows, rows.length);
	}
}