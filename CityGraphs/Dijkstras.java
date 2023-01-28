import java.util.*;

public class Dijkstras
{
	ArrayList<City> cities;
	ArrayList<Edge> edges;
	HashSet<City> visitedCities;
	HashSet<City> unvisitedCities;
	HashMap<City, City> predecessors;
	HashMap<City, Integer> distance;

	public Dijkstras(Graph graph)
	{
		this.cities = new ArrayList<City>(graph.getCities());
		this.edges = new ArrayList<Edge>(graph.getEdges());
	}

	public void createTravelsPaths(City source)
	{
		visitedCities = new HashSet<City>();
		unvisitedCities = new HashSet<City>();
		predecessors = new HashMap<City, City>();
		distance = new HashMap<City, Integer>();

		distance.put(source, 0);
		unvisitedCities.add(source);

		while(unvisitedCities.size() > 0)
		{
			City city = getMinimum(unvisitedCities);
			visitedCities.add(city);
			unvisitedCities.remove(city);
			findMinimalDistances(city);
		}
	}

	public HashMap<City, City> getPred()
	{
		return predecessors;
	}

	public void findMinimalDistances(City tempCity)
	{
		ArrayList<City> adjacentNodes = getNeighbors(tempCity);

		for(City targetCity : adjacentNodes)
		{
			if(getShortestDistance(targetCity) > getShortestDistance(tempCity)+ getDistance(tempCity, targetCity))
			{
				distance.put(targetCity, getShortestDistance(tempCity) + getDistance(tempCity, targetCity));
				predecessors.put(targetCity, tempCity);
				unvisitedCities.add(targetCity);
			}
		}
	}

	public int getDistance(City tempCity, City targetCity)
	{
		for(Edge edge : edges)
		{
			if((edge.getStartCity() == tempCity && edge.getDestinationCity() == targetCity) || (edge.getStartCity() == targetCity && edge.getDestinationCity() == tempCity))
			{
				return edge.getDistance();
			}
		}
		throw new RuntimeException();
	}

	public ArrayList<City> getNeighbors(City tempCity)
	{
		ArrayList<City> neighbors = new ArrayList<City>();

		for(Edge edge : edges)
		{
			if(edge.getStartCity() == tempCity && !wasVisited(edge.getDestinationCity()))
			{
				neighbors.add(edge.getDestinationCity());
			}
			if(edge.getDestinationCity() == tempCity && !wasVisited(edge.getStartCity()))
			{
				neighbors.add(edge.getStartCity());
			}
		}

		return neighbors;
	}

	public City getMinimum(HashSet<City> cities)
	{
		City minimum = null;

		for(City city : cities)
		{
			if(minimum == null)
			{
				minimum = city;
			}
			else if(getShortestDistance(city) < getShortestDistance(minimum))
			{
				minimum = city;
			}
		}
		return minimum;
	}

	public boolean wasVisited(City city)
	{
		return visitedCities.contains(city);
	}

	public int getShortestDistance(City destination)
	{
		Integer dist = distance.get(destination);
		if(dist == null)
		{
			return Integer.MAX_VALUE;
		}
		return dist;
	}

	public ArrayList<City> getShortestPath(City target)
	{
		ArrayList<City> connectingCities = new ArrayList<City>();
		City step = target;
		if(predecessors.get(step) == null)
		{
			return null;
		}
		connectingCities.add(step);

		while(predecessors.get(step) != null)
		{
			step = predecessors.get(step);
			connectingCities.add(step);
		}
		Collections.reverse(connectingCities);
		return connectingCities;
	}
}