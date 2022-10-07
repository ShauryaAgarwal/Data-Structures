public class TreeRunner1
{
	public TreeRunner1()
	{
		TreeSet<Integer> set=new TreeSet<Integer>();


		set.add(30);
		set.add(20);
		set.add(50);
		set.add(45);
		set.add(10);
		set.add(46);
		set.add(10);
		set.add(15);
		set.add(47);
		set.add(60);

		System.out.println("Original Set - PreOrder: Size: " +set.size() + set.preOrder());
		System.out.println();
		System.out.println("Original Set - InOrder: Size: "+ set.size()+ set.inOrder());
		System.out.println();
		System.out.println("Original Set - PostOrder: Size: "+ set.size()+ set.postOrder());
		System.out.println("****************************");



		set.remove(60);
		set.remove(44);
		set.remove(30);
		set.remove(30);


		System.out.println("Original Set - PreOrder: Size: " +set.size() + set.preOrder());
		System.out.println();
		System.out.println("Original Set - InOrder: Size: "+ set.size()+ set.inOrder());
		System.out.println();
		System.out.println("Original Set - PostOrder: Size: "+ set.size()+ set.postOrder());
		System.out.println("****************************");


	}

	public static void main(String[] args)
	{
		TreeRunner1 app=new TreeRunner1();
	}

}