import java.util.*;
import java.io.*;
import java.text.*;

public class TimeTravel
{
	public static void main(String[]args)
	{
		File timeText = new File("C:\\Users\\10015327\\Downloads\\Prob8Input.txt");

		try
		{
			BufferedReader br = new BufferedReader(new FileReader(timeText));
			String contentLine = br.readLine();
			int count = 1;

			while(contentLine != null)
			{
				String[] contentLineNums = contentLine.split(" ");

				Calendar cal1 = Calendar.getInstance();
				cal1.setTime(new Date());
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(new Date());

				cal2.add(Calendar.DATE, Integer.valueOf(contentLineNums[0]));
				cal2.add(Calendar.HOUR, Integer.valueOf(contentLineNums[1]));
				cal2.add(Calendar.MINUTE, Integer.valueOf(contentLineNums[2]));

				SimpleDateFormat d1 = new SimpleDateFormat("hh:mm aa");
				SimpleDateFormat d2 = new SimpleDateFormat("MM/dd/yyyy");

				System.out.println("Trip " + count + ": " + contentLine);
				System.out.println("\tDeparture Date and Time: " + d1.format(cal1.getTime()) + " on " + d2.format(cal1.getTime()));
				System.out.println("\tArrival Date and Time: " + d1.format(cal2.getTime()) + " on " + d2.format(cal2.getTime()));
				count++;
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