public class Pi
{
	public static void main(String[]args)
	{
		double x;
		double y;
		double NUM = Integer.MAX_VALUE;
		int count = 0;

		for(int i = 0; i < NUM; i++)
		{
			x = Math.random();
			y = Math.random();
			if(x*x + y*y < 1)
			{
				count++;
			}
		}
		System.out.print(4*(count/NUM));
	}
}