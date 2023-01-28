import java.util.*;
import java.io.*;

public class Airport
{
	Queue<Passenger> manifest = new LinkedList<Passenger>();
	PriorityQueue<Passenger> impending = new PriorityQueue<Passenger>();

	public Airport()
	{
		File list = new File("C:\\Users\\10015327\\Downloads\\PassengerInfo.txt");

		try
		{
			BufferedReader in = new BufferedReader(new FileReader(list));
			String name;

			while((name = in.readLine()) != null)
			{
				Passenger p = new Passenger(name, in.readLine(), in.readLine());
				manifest.add(p);
				if(p.getETD() < 60)
					impending.add(p);
			}
		}
		catch(IOException io)
		{
			System.out.println("File Not Found!");
		}
	}

	public void printManifest()
	{
		System.out.println("Impending Departures:\n");
		while(impending.peek() != null)
		{
			System.out.println(impending.poll());
		}
		System.out.println("\nAll Passengers:\n");
		while(manifest.peek() != null)
		{
			System.out.println(manifest.poll());
		}
	}

	public static void main(String[]args)
	{
		Airport newark = new Airport();
		newark.printManifest();
	}
}