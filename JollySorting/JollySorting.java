import java.util.*;
import java.io.*;

public class JollySorting
{
	public static void main(String[]args)
	{
		File jollyFile = new File("C:\\Users\\10015327\\Downloads\\Prob3Input (1).txt");

		try
		{
			BufferedReader br = new BufferedReader(new FileReader(jollyFile));
			String contentLine = br.readLine();
			boolean check = false;

			while(contentLine != null)
			{
				String[] numsArr = contentLine.split(" ");
				int[] nums = new int[numsArr.length];
				System.out.print("Initial:\t");
				for(int i = 0; i < nums.length; i++)
				{
					nums[i] = Integer.valueOf(numsArr[i]);
					System.out.print(nums[i] + " ");
				}
				System.out.println();
				Arrays.sort(nums);
				System.out.print("Ordered Sort:\t");
				for(int i = 0; i < nums.length; i++)
				{
					System.out.print(nums[i] + " ");
				}
				System.out.println();
				for(int i = 2; i < nums.length; i++)
				{
					if(i % 2 == 0)
					{
						int temp = nums[i-1];
						nums[i-1] = nums[i];
						nums[i] = temp;
					}
				}
				System.out.print("Jolly Sort:\t");
				for(int i = 0; i < nums.length; i++)
				{
					System.out.print(nums[i] + " ");
				}
				System.out.println("\n");
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