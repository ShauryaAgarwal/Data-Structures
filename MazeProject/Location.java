public class Location
{
	int row;
	int col;

	public Location(int r, int c)
	{
		row = r;
		col = c;
	}

	public void setR(int r)
	{
		row += r;
	}

	public void setC(int c)
	{
		col += c;
	}

	public int getR()
	{
		return row;
	}

	public int getC()
	{
		return col;
	}
}