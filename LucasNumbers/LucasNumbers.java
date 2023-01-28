import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class LucasNumbers
{
	public static void main(String[]args)
	{
		File lucasNumText = new File("C:\\Users\\10015327\\Downloads\\Prob2Input.txt");

		try
		{
			BufferedReader br = new BufferedReader(new FileReader(lucasNumText));
			String contentLine = br.readLine();
			while(contentLine != null)
			{
				int index = Integer.valueOf(contentLine);
				System.out.println("Index: " + index);

				BigInteger i1 = new BigInteger("2");
				BigInteger i2 = new BigInteger("1");
				BigInteger val = new BigInteger("0");
				BigInteger temp = new BigInteger("0");
				for(int i = 0; i <= index; i++)
				{
					if(i == 0)
					{
						val = BigInteger.valueOf(2);
					}
					else if(i == 1)
					{
						val = BigInteger.valueOf(1);
					}
					else
					{
						val = i1.add(i2);
						temp = i2;
						i2 = i1.add(i2);
						i1 = temp;
					}
					//System.out.print(val + ", ");
				}

				System.out.println("Term at index: " + val);
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