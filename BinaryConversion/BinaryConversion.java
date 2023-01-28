import java.util.Stack;

public class BinaryConversion
{
	private int decimal;
	private String binary;
	private Stack<Integer> s;

	public BinaryConversion(int num)
	{
		decimal = num;
		s = new Stack<Integer>();
		binary = convert(decimal);
	}

	public int getDecimal()
	{
		return decimal;
	}

	public String getBinary()
	{
		return binary;
	}

	public String convert(int num)
	{
		String binaryText = "";

		while(num > 0)
		{
			s.push(num%2);
			num /= 2;
		}
		while(!s.empty())
		{
			binaryText += s.pop().toString();
		}

		return binaryText;
	}

	public static void main(String[]args)
	{
		BinaryConversion b1 = new BinaryConversion(11);
		System.out.println(b1.getDecimal() + ": " + b1.getBinary());

		BinaryConversion b2 = new BinaryConversion(574);
		System.out.println(b2.getDecimal() + ": " + b2.getBinary());

		BinaryConversion b3 = new BinaryConversion(5);
		System.out.println(b3.getDecimal() + ": " + b3.getBinary());
	}
}