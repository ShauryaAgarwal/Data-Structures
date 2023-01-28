public class Car implements Comparable<Car>
{
	private int carID;
	private int mpg;
	private int engineSize;
	private int horsePower;
	private int weight;
	private int accel;
	private String country;
	private int numCylinders;

	public Car(int i, int m, int e, int h, int w, int a, int c, int n)
	{
		carID = i;
		mpg = m;
		engineSize = e;
		horsePower = h;
		weight = w;
		accel = a;
		numCylinders = n;

		if(c == 1)
			country = "Italy";
		else if(c == 2)
			country = "Japan";
		else if(c == 3)
			country = "Germany";
		else
			country = "None?";
	}

	public int compareTo(Car other)
	{
		if(this.getAccel() != other.getAccel())
			return this.getAccel() - other.getAccel();
		if(this.getMPG() != other.getMPG())
			return this.getMPG() - other.getMPG();
		if(this.getHP() != other.getHP())
			return this.getHP() - other.getHP();
		if(this.getSize() != other.getSize())
			return this.getSize() - other.getSize();
		if(this.getWeight() != other.getWeight())
			return this.getWeight() - other.getWeight();
		if(this.getNumCyl() != other.getNumCyl())
			return this.getNumCyl() - other.getNumCyl();
		return this.getID() - other.getID();
	}

	public int getID()
	{
		return carID;
	}

	public int getMPG()
	{
		return mpg;
	}

	public int getSize()
	{
		return engineSize;
	}

	public int getHP()
	{
		return horsePower;
	}

	public int getWeight()
	{
		return weight;
	}

	public int getAccel()
	{
		return accel;
	}

	public String getCountry()
	{
		return country;
	}

	public int getNumCyl()
	{
		return numCylinders;
	}

	public String toString()
	{
		String str = String.format("%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s", country, accel, mpg, horsePower, engineSize, weight, numCylinders, carID);
		/*
		String str = country + "\t";
		str += accel + "\t";
		str += mpg + "\t";
		str += horsePower + "\t";
		str += engineSize + "\t";
		str += weight + "\t";
		str += numCylinders + "\t";
		str += carID;
		*/
		return str;
	}

	public static void main(String[]args)
	{

	}
}