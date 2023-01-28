public class City
{
	String cityName;
	int id;

	public City(String cn)
	{
		cityName = cn;
		id = cityName.hashCode();
	}

	public String getName()
	{
		return cityName;
	}

	public boolean equals(Object obj)
	{
		if(this == obj)
		{
			return true;
		}
		City other = (City)obj;
		return (this.hashCode() == other.hashCode());
	}

	public int getHashCode()
	{
		return id;
	}

	public String toString()
	{
		return cityName;
	}
}