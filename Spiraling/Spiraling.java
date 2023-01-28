import java.util.*;
import java.io.*;

public class Spiraling
{
	public static void main(String[]args)
	{
		File spiralText = new File("C:\\Users\\10015327\\Downloads\\Prob9Input.txt");

		try
		{
			BufferedReader br = new BufferedReader(new FileReader(spiralText));
			String contentLine = br.readLine();

			while(contentLine != null)
			{
				int dim = Integer.valueOf(contentLine);
				String[][] spiral = new String[dim][dim];

				/*
				int size = dim*dim;
				int row = 0;
				int col = 0;
				int reduce = 0;
				while(size > 0)
				{
					while(col < (spiral[0].length - reduce))
					{
						spiral[row][col] = "*";
						col++;
						size--;
					}
					col--;

					while(row < (spiral.length - reduce))
					{
						spiral[row][col] = "*";
						row++;
						size--;
					}
					row--;

					while(col >= (0 + reduce))
					{
						spiral[row][col] = "*";
						col--;
						size--;
					}
					col++;
					reduce += 2;

					while(row >= (0 + reduce))
					{
						spiral[row][col] = "*";
						row--;
						size--;
					}
					row++;
				}
				*/

				int size = 0;
				int minCol = 0;
				int maxCol = dim - 1;
				int minRow = 0;
				int maxRow = dim - 1;

				while(size < dim*dim)
				{
					for(int i = minCol; i <= maxCol; i++)
					{
						size++;
					}

					for(int i = minRow + 1; i <= maxRow; i++)
					{
						size++;
					}

					for(int i = maxCol - 1; i >= minCol; i--)
					{
						size++;
					}

					for(int i = maxRow - 1; i >= minRow + 1; i--)
					{
						size++;
					}

					minCol++;
					minRow++;
					maxCol--;
            		maxRow--;
				}

				for(int i = 0; i < spiral.length; i++)
				{
					for(int j = 0; j < spiral[0].length; j++)
					{
						if(spiral[i][j] == null)
							spiral[i][j] = "-";
						System.out.print(spiral[i][j]);
					}
					System.out.println();
				}
				System.out.println();
				contentLine = br.readLine();
			}
		}
		catch(Exception e)
		{
			System.out.println("File not found");
			e.printStackTrace();
		}
	}
}