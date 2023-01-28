import java.util.*;
import java.io.*;

public class AmicableNumbers
{
	public static void main(String[]args)
	{
		File numbersText = new File("C:\\Users\\10015327\\Downloads\\Prob3Input.txt");

		try
		{
			BufferedReader br = new BufferedReader(new FileReader(numbersText));
			String contentLine = br.readLine();
			while(contentLine != null)
			{
				String[] nums = contentLine.split(" ");
				int firstNum = Integer.valueOf(nums[0]);
				int secondNum = Integer.valueOf(nums[1]);
				System.out.println("Numbers: " + firstNum + " & " + secondNum);

				ArrayList<Integer> firstFactors = getNumFactors(firstNum);
				ArrayList<Integer> secondFactors = getNumFactors(secondNum);

				String firstFactorsString = factorsToString(firstFactors);
				String secondFactorsString = factorsToString(secondFactors);

				int firstFactorsSum = getFactorsSum(firstFactors);
				int secondFactorsSum = getFactorsSum(secondFactors);

				if(firstFactorsSum == secondNum && firstNum == secondFactorsSum)
				{
					System.out.println("The numbers " + firstNum + " and " + secondNum + " are amicable.");
				}
				else
				{
					System.out.println("The numbers " + firstNum + " and " + secondNum + " are not amicable.");
				}
				System.out.println("Factors of " + firstNum + " are " + firstFactorsString + ". Sum is " + firstFactorsSum + ".");
				System.out.println("Factors of " + secondNum + " are " + secondFactorsString + ". Sum is " + secondFactorsSum + ".\n");
				contentLine = br.readLine();
			}
		}
		catch(Exception e)
		{
			System.out.println("File not found");
			e.printStackTrace();
		}
	}

	public static ArrayList<Integer> getNumFactors(int num)
	{
		ArrayList<Integer> factors = new ArrayList<Integer>();
		for(int i = 1; i < num; i++)
		{
			if(num%i == 0)
			factors.add(i);
		}
		return factors;
	}

	public static String factorsToString(ArrayList<Integer> factors)
	{
		String str = "";
		for(int i = 0; i < factors.size() - 1; i++)
		{
			str += factors.get(i) + ", ";
		}
		str += "and " + factors.get(factors.size()-1);
		return str;
	}

	public static int getFactorsSum(ArrayList<Integer> factors)
	{
		int sum = 0;
		for(int i = 0; i < factors.size(); i++)
		{
			sum += factors.get(i);
		}
		return sum;
	}
}