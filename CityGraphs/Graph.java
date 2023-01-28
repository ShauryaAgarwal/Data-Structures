import java.util.*;

public class Graph
{
	HashSet<City> citySet;
	HashSet<Edge> edgeSet;

	public Graph(HashSet<City> city, HashSet<Edge> edge)
	{
		citySet = city;
		edgeSet = edge;
	}

	public HashSet<City> getCities()
	{
		return citySet;
	}

	public HashSet<Edge> getEdges()
	{
		return edgeSet;
	}
}