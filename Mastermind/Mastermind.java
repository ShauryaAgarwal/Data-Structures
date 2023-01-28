import java.util.*;
import java.io.*;

public class Mastermind
{
	public static void main(String[]args)
	{
		File masterText = new File("C:\\Users\\10015327\\Downloads\\MastermindFile.txt");

		try
		{
			BufferedReader br = new BufferedReader(new FileReader(masterText));
			String contentLine = br.readLine();
			String prevLine = "";
			int count = 0;

			while(contentLine != null)
			{
				if(count > 0 && count % 2 == 1)
				{
					int cg = 0;
					int ig = 0;
					System.out.println("Code: " + prevLine);
					System.out.println("Guess: " + contentLine);
					for(int i = prevLine.length() - 1; i >= 0; i--)
					{
						if(contentLine.substring(i, i+1).equals(prevLine.substring(i, i+1)))
						{
							cg++;
							contentLine = contentLine.substring(0, i) + contentLine.substring(i+1, contentLine.length());
							prevLine = prevLine.substring(0, i) + prevLine.substring(i+1, prevLine.length());
						}
					}
					//System.out.println(prevLine + "\t" + contentLine);
					for(int i = contentLine.length() - 1; i >= 0; i--)
					{
						for(int j = prevLine.length() - 1; j >= 0; j--)
						{
							if(contentLine.substring(i, i+1).equals(prevLine.substring(j, j+1)))
							{
								ig++;
								contentLine = contentLine.substring(0, i) + contentLine.substring(i+1, contentLine.length());
								prevLine = prevLine.substring(0, j) + prevLine.substring(j+1, prevLine.length());
							}
						}
					}
					//System.out.println(prevLine + "\t" + contentLine);
					System.out.println("Correct Color and Correctly Placed: " + cg);
					System.out.println("Correct Color but Incorrectly Placed: " + ig);
					System.out.println();
				}
				count++;
				prevLine = contentLine;
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