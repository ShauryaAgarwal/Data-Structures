import java.util.*;
import java.io.*;

public class GuitarHero
{
	public static void main(String[]args)
	{
		File guitarText = new File("C:\\Users\\10015327\\Downloads\\Prob10Input.txt");
		String[][] notePositions = {{"E", "A", "D", "G", "B", "E"},{"F", "A#", "D#", "G#", "C", "F"},{"F#", "B", "E", "A", "C#", "F#"},{"G", "C", "F", "A#", "D", "G"},{"G#", "C#", "F#", "B", "D#", "G#"}};
		boolean initialCheck = true;
		String[][] measures = new String[0][0];

		try
		{
			BufferedReader br = new BufferedReader(new FileReader(guitarText));
			String contentLine = br.readLine();
			int tempCount = 0;
			while(contentLine != null)
			{
				String[] brLine = contentLine.split(",");
				if(initialCheck)
				{
					measures = new String[5][brLine.length];
					initialCheck = false;
				}
				for(int i = 0; i < measures[0].length; i++)
				{
					measures[tempCount][i] = brLine[i];
				}
				tempCount++;
				contentLine = br.readLine();
			}
			for(int i = 0; i < measures.length; i++)
			{
				for(int j = 0; j < measures[0].length; j++)
				{
					System.out.print(measures[i][j] + "\t");
				}
				System.out.println();
			}
		}
		catch(Exception e)
		{
			System.out.println("File not found");
			e.printStackTrace();
		}
	}
}