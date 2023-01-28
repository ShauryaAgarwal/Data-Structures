import java.util.*;
import java.io.*;

public class Reducer
{
	public static void main(String[]args)
	{
		File reducerTxt = new File("C:\\Users\\10015327\\Downloads\\Reducer.txt");

		try
		{
			BufferedReader br = new BufferedReader(new FileReader(reducerTxt));
			String contentLine = br.readLine();
			while(contentLine != null)
			{
				System.out.println("Initial: " + contentLine);
				String[] nums = contentLine.split("/");
				int numer = Integer.valueOf(nums[0]);
				int denom = Integer.valueOf(nums[1]);
				int gcf = findGCF(numer, denom);
				numer /= gcf;
				denom /= gcf;

				String reduced = "";
				if(numer > denom)
				{
					int bigNum = (numer-(numer%denom))/denom;
					reduced = bigNum + " " + (numer%denom) + "/" + denom;
				}
				else
				{
					reduced = numer + "/" + denom;
				}

				System.out.println("Reduced: " + reduced + "\n");
				contentLine = br.readLine();
			}
		}
		catch(IOException e)
		{
			System.out.println("File not found");
			e.printStackTrace();
		}
	}

	public static int findGCF(int num, int den)
	{
		int gcf = Math.min(num, den);

		while(num%gcf != 0 || den%gcf != 0)
		{
			gcf--;
		}
		return gcf;
	}
}