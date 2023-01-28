public class Passenger implements Comparable<Passenger>
{
	private String lastName;
	private String firstName;
	private String flightCity;
	private String flightTime;
	private int etd;
	private static String currentTime = "9:03 AM";

	public Passenger(String name, String city, String time)
	{
		firstName = name.substring(0, name.indexOf(" "));
		lastName = name.substring(name.indexOf(" ") + 1);
		flightCity = city;
		flightTime = time;
		etd = etdCalc(currentTime);
	}

	public String getLast()
	{
		return lastName;
	}

	public String getFirst()
	{
		return firstName;
	}

	public String getCity()
	{
		return flightCity;
	}

	public int getETD()
	{
		return etd;
	}

	public int compareTo(Passenger other)
	{
		return etd - other.getETD();
	}

	public int etdCalc(String currentTime)
	{
		int minutes = 0;

		int currentHour = Integer.valueOf(currentTime.substring(0, currentTime.indexOf(":")));
		int flightHour = Integer.valueOf(flightTime.substring(0, flightTime.indexOf(":")));

		int currentMinutes = Integer.valueOf(currentTime.substring(currentTime.indexOf(":") + 1, currentTime.indexOf(" ")));
		int flightMinutes = Integer.valueOf(flightTime.substring(flightTime.indexOf(":") + 1, flightTime.indexOf(" ")));

		if(currentTime.substring(currentTime.length() - 2).equals("PM"))
		{
			if(currentHour < 12)
			{
				currentHour += 12;
			}
		}
		if(flightTime.substring(flightTime.length() - 2).equals("PM"))
		{
			if(flightHour < 12)
			{
				flightHour += 12;
			}
		}
		if(currentTime.substring(currentTime.length() - 2).equals("AM"))
		{
			if(currentHour == 12)
			{
				currentHour -= 12;
			}
		}
		if(flightTime.substring(flightTime.length() - 2).equals("AM"))
		{
			if(flightHour == 12)
			{
				flightHour -= 12;
			}
		}

		minutes = (flightHour - currentHour)*60;
		minutes += (flightMinutes - currentMinutes);

		return minutes;
	}

	public String toString()
	{
		String str = lastName + ", " + firstName + " - " + flightCity + " - " + flightTime + " - Minutes till Departure: " + etd;
		return str;
	}

	public static void main(String[]args)
	{

	}
}