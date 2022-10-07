import java.util.*;

public class HashSets
{
	ArrayList<HashSet> setArray = new ArrayList<HashSet>();

	public HashSets()
	{
		fillArray((int)(Math.random()*11)+2);
	}

	public void fillArray(int size)
	{
		for(int i = 0; i < size; i++)
		{
			HashSet<Integer> hs = new HashSet<Integer>();
			while(hs.size() < 10)
			{
				hs.add((int)(Math.random()*30)+1);
			}
			setArray.add(hs);
		}
	}

	public Set<Integer> intersection(HashSet<Integer> setOne, HashSet<Integer> setTwo)
	{
		HashSet<Integer> intSet = new HashSet<Integer>();
		for(int i = 1; i <= 30; i++)
		{
			if(setOne.contains(i) && setTwo.contains(i))
			{
				intSet.add(i);
			}
		}
		return intSet;
	}

	public Set<Integer> union(HashSet<Integer> setOne, HashSet<Integer> setTwo)
	{
		TreeSet<Integer> uniSet = new TreeSet<Integer>();
		Iterator<Integer> itOne = setOne.iterator();
		Iterator<Integer> itTwo = setTwo.iterator();
		for(int i = 0; i < setOne.size(); i++)
		{
			int current = itOne.next();
			if(!(uniSet.contains(current)))
			{
				uniSet.add(current);
			}
		}
		for(int i = 0; i < setTwo.size(); i++)
		{
			int current = itTwo.next();
			if(!(uniSet.contains(current)))
			{
				uniSet.add(current);
			}
		}
		return uniSet;
	}

	public Set<Integer> arrayIntersection(ArrayList<HashSet> setArr)
	{
		TreeSet<Integer> intSet = new TreeSet<Integer>();
		for(int i = 1; i <= 30; i++)
		{
			int sum = 0;
			for(int j = 0; j < setArr.size(); j++)
			{
				if(setArr.get(j).contains(i))
				{
					sum++;
				}
			}
			if(sum == setArr.size())
			{
				intSet.add(i);
			}
		}
		return intSet;
	}

	public Set<Integer> arrayUnion(ArrayList<HashSet> setArr)
	{
		TreeSet<Integer> uniSet = new TreeSet<Integer>();
		for(int i = 0; i < setArr.size(); i++)
		{
			Iterator<Integer> it = (setArr.get(i)).iterator();
			for(int j = 0; j < setArr.get(i).size(); j++)
			{
				int current = it.next();
				if(!(uniSet.contains(current)))
				{
					uniSet.add(current);
				}
			}
		}
		return uniSet;
	}

	public static void main(String[]args)
	{
		HashSets app = new HashSets();
		for(int i = 0; i < app.setArray.size(); i++)
		{
			System.out.println("Set " + (i+1) + ": " + app.setArray.get(i));
		}

		System.out.println("Intersection: " + app.arrayIntersection(app.setArray));
		System.out.println("Union: " + app.arrayUnion(app.setArray));

		System.out.println();

		HashSet<Integer> test1 = new HashSet<Integer>();
		for(int i = 0; i < 10; i++)
		{
			test1.add((int)(Math.random()*30)+1);
		}
		HashSet<Integer> test2 = new HashSet<Integer>();
		for(int i = 0; i < 10; i++)
		{
			test2.add((int)(Math.random()*30)+1);
		}

		System.out.println("Set 1: " + test1);
		System.out.println("Set 2: " + test2);
		System.out.println("Intersection: " + app.intersection(test1, test2));
		System.out.println("Union: " + app.union(test1, test2));
	}
}