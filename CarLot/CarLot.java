import java.io.*;
import java.util.*;

public class CarLot
{
	Stack<Car> carStack = new Stack<Car>();
	Queue<Car> carQueue = new LinkedList<Car>();
	PriorityQueue<Car> carPQ = new PriorityQueue<Car>();

	public CarLot()
	{
		File cars = new File("C:\\Users\\10015327\\Downloads\\CarData.txt");

		try
		{
			BufferedReader in = new BufferedReader(new FileReader(cars));
			in.readLine();
			String str;

			while((str = in.readLine()) != null)
			{
				String[] data = str.split("\t");
				int[] nums = new int[data.length];

				for(int i = 0; i < data.length; i++)
				{
					nums[i] = Integer.valueOf(data[i]);
				}

				Car c = new Car(nums[0], nums[1], nums[2], nums[3], nums[4], nums[5], nums[6], nums[7]);
				carStack.push(c);
				carQueue.add(c);
				carPQ.add(c);
			}
		}
		catch(IOException io)
		{
			System.out.println("File does not exist!");
		}
	}

	public void printStack()
	{
		while(!carStack.empty())
		{
			System.out.println(carStack.pop());
		}
	}

	public void printQueue()
	{
		while(carQueue.peek() != null)
		{
			System.out.println(carQueue.poll());
		}
	}

	public void printPQ()
	{
		while(carPQ.peek() != null)
		{
		System.out.println(carPQ.poll());
		}
	}

	public static void main(String[]args)
	{
		CarLot lot = new CarLot();
		lot.printStack();
		lot.printQueue();
		lot.printPQ();
	}
}