
public class SuperListRunner
{
	public static void main(String[] args)
	{
		SuperList list = new SuperList();
		/*
		list.add(2);
		list.add(8);
		list.add(14);

		System.out.println(list.size());
		System.out.println(list);

		list.add(0, 1);

		System.out.println(list.size());
		System.out.println(list);

		list.add(2, 5);

		System.out.println(list.size());
		System.out.println(list);

		list.add(7, 25);

		System.out.println(list.size());
		System.out.println(list);

		list.add(-5, 1);
		list.add(20, 1);
		*/

		list.add(0, 20);

		list.add(2);
		list.add(8);
		list.add(14);

		list.add(10, 100);
		list.add(0, 20);
		list.add(-1, 47);
		list.add(5, 16);
		list.add(4, 2);

		System.out.println(list.size());
		System.out.println(list);
		System.out.println();

		//output
		//3
		//[2, 8, 14]

		list.push(5);
		System.out.println(list);

		System.out.println(list.pop().getValue());
		System.out.println(list);

		System.out.println(list.poll().getValue());
		System.out.println(list);

		System.out.println(list.stackPeek().getValue() + "\n" + list.queuePeek().getValue());
		System.out.println();

		System.out.println(list.get(3).getValue());
		System.out.println(list.get(-1));
		System.out.println(list);
		for(int i = 0; i < list.size(); i++)
		{
			System.out.println(list.get(i).getValue());
		}
		list.set(5, 2);
		System.out.println(list);
		System.out.println();

		System.out.println(list.remove(3).getValue());
		System.out.println(list);
		System.out.println(list.isEmpty());
		System.out.println(list.contains(14));
		list.clear();
		System.out.println(list);
	}
}