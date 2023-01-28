import java.util.*;
import java.io.*;

public class FFLTask
{
	public static void main(String[]args)
	{
		File fflText = new File("C:\\Users\\10015327\\Downloads\\FFLAverages.txt");

		try
		{
			BufferedReader br = new BufferedReader(new FileReader(fflText));
			String contentLine = br.readLine();
			contentLine = br.readLine();

			while(contentLine != null)
			{
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