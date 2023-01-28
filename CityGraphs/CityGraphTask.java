import java.util.*;
import java.io.*;

public class CityGraphTask
{
	HashMap<City, HashSet<Edge>> cityMap;
	HashSet<City> cities;
	HashSet<Edge> edges;
	City start, end;

	public CityGraphTask()
	{
		cityMap = new HashMap<City, HashSet<Edge>>();
		cities = new HashSet<City>();
		edges = new HashSet<Edge>();
		ArrayList<String> cityList = new ArrayList<String>();
		File file = new File("C:\\Users\\Owner\\Downloads\\City Distances.txt");

		try
		{
			BufferedReader input = new BufferedReader(new FileReader(file));
			String text = "";
			while((text = input.readLine()) != null)
			{
				String[] info = text.split(",");
				City c1 = new City(info[0]);
				City c2 = new City(info[1]);
				int distance = Integer.valueOf(info[2]);
				if(!cityList.contains(c1.getName()))
				{
					cityList.add(c1.getName());
				}
				if(!cityList.contains(c2.getName()))
				{
					cityList.add(c2.getName());
				}
				cities.add(c1);
				cities.add(c2);
				edges.add(new Edge(c1, c2, distance));
				edges.add(new Edge(c2, c1, distance));
				if(!cityMap.containsKey(c1))
				{
					cityMap.put(c1, new HashSet<Edge>());
				}
				if(!cityMap.containsKey(c2))
				{
					cityMap.put(c2, new HashSet<Edge>());
				}
				cityMap.get(c1).add(new Edge(c1, c2, distance));
				cityMap.get(c2).add(new Edge(c2, c1, distance));
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		System.out.println("Vertices - Cities");
		for(City city : cities)
		{
			System.out.println("\t" + city.getName());
		}
		System.out.println("Edges - Connecting cities and distances between");
		for(Edge edge : edges)
		{
			System.out.println("\t" + edge.getStartCity().getName() + " to " + edge.getStartCity().getName() + ": " + edge.getDistance());
		}

		for(int i = 0; i < cityList.size(); i++)
		{
			for(int j = i + 1; j < cityList.size(); j++)
			{
				for(City city : cities)
				{
					if(city.getName().equals(cityList.get(i)))
					{
						start = city;
					}
					if(city.getName().equals(cityList.get(j)))
					{
						end = city;
					}
				}
				Graph graph = new Graph(cities, edges);
				Dijkstras dijkstra = new Dijkstras(graph);
				ArrayList<City> shortestPath = dijkstra.getShortestPath(end);
				int distance = 0;
				System.out.println("Shortest path between " + start.getName() + " to " + end.getName() + ".");

				for(int x = 0; x < shortestPath.size() - 1; x++)
				{
					City c1 = shortestPath.get(x);
					City c2 = shortestPath.get(x+1);
					System.out.println("\t" + c1 + " to " + c2);
					for (Edge edge : cityMap.get(c1))
					{
						if (edge.getStartCity().equals(c1) && edge.getDestinationCity().equals(c2)) distance += edge.getDistance();
						if (edge.getStartCity().equals(c2) && edge.getDestinationCity().equals(c1)) distance += edge.getDistance();
					}
				}
				System.out.println("Distance between: " + distance + " miles\n\n");
			}
		}
	}

	public static void main(String[]args)
	{
		CityGraphTask cityGraphTask = new CityGraphTask();
	}
}