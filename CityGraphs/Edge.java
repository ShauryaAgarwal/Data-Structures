public class Edge
{
	City startCity, destinationCity;
	int distanceBetween, uniqueID;

	public Edge(City city1, City city2, int dist)
	{
		startCity = city1;
		destinationCity = city2;
		distanceBetween = dist;
		uniqueID = startCity.hashCode() + destinationCity.hashCode();
	}

	public int getHashCode()
	{
		return uniqueID;
	}

	public City getDestinationCity()
	{
		return destinationCity;
	}

	public City getStartCity()
	{
		return startCity;
	}

	public int getDistance()
	{
		return distanceBetween;
	}

	public String toString()
	{
		return startCity + " to " + destinationCity + ": " + distanceBetween;
	}

	public boolean equals(Object obj)
	{
		if(this == obj)
		{
			return true;
		}
		Edge other = (Edge)obj;
		return (this.hashCode() == other.hashCode());
	}
}