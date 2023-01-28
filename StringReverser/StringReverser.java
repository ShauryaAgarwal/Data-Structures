import java.util.Stack;

public class StringReverser
{
	private String str;
	private String revStr;
	private Stack<String> s;

	public StringReverser(String st)
	{
		str = st;
		s = new Stack<String>();
		revStr = reverse(str);
	}

	public String getInitialString()
	{
		return str;
	}

	public String getReversedString()
	{
		return revStr;
	}

	public String reverse(String st)
	{
		String temp = "";

		while(st.length() > 0)
		{
			s.push(st.substring(0, 1));
			st = st.substring(1, st.length());
		}
		while(!s.empty())
		{
			temp += s.pop();
		}

		return temp;
	}

	public static void main(String[]args)
	{
		StringReverser s1 = new StringReverser("Test");
		System.out.println(s1.getInitialString() + ": " + s1.getReversedString());
	}
}