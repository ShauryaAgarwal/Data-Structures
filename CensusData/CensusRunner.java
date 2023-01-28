import java.io.*;
import java.util.*;

public class CensusRunner
{
	ArrayList<Citizen> list = new ArrayList<Citizen>();

	public CensusRunner()
	{
		File dataText = new File("C:\\Users\\10015327\\Downloads\\FedCensus1930_CambriaCountyPA.txt");

		try
		{
			BufferedReader br = new BufferedReader(new FileReader(dataText));
			String contentLine = br.readLine();
			while(contentLine != null)
			{
				if(contentLine.length() > 2 && contentLine.substring(0, 2).equals("17"))
				{
					//System.out.println(contentLine);
					String firstName = contentLine.substring(71, 88).trim();
					String lastName = contentLine.substring(55, 71).trim();
					String street = contentLine.substring(20, 36).trim();
					String streetNumber = contentLine.substring(16, 20).trim();
					String relation = contentLine.substring(88, 108).trim();
					String property = contentLine.substring(108, 113).trim();
					String propertyValue = contentLine.substring(113, 121).trim();
					String gender = contentLine.substring(133, 138).trim();
					String age = contentLine.substring(143, 151).trim();
					String maritalStatus = contentLine.substring(151, 156).trim();
					String ageFirstMarriage = contentLine.substring(157, 162).trim();
					String attendSchool = contentLine.substring(162, 167).trim();
					String canRead = contentLine.substring(167, 173).trim();
					String birthPlace = contentLine.substring(173, 190).trim();
					String fatherBirthPlace = contentLine.substring(190, 207).trim();
					String motherBirthPlace = contentLine.substring(207, 223).trim();
					String motherTongue = contentLine.substring(224, 235).trim();
					String yearImmigrated = contentLine.substring(235, 241).trim();
					String occupation = contentLine.substring(252, 274).trim();
					String industry = contentLine.substring(274, 303).trim();
					String transcriberRemarks = contentLine.substring(342).trim();

					Citizen c = new Citizen(firstName, lastName, street, streetNumber, relation, property, propertyValue, gender, age, maritalStatus, ageFirstMarriage, attendSchool, canRead, birthPlace, fatherBirthPlace, motherBirthPlace, motherTongue, yearImmigrated, occupation, industry, transcriberRemarks);
					list.add(c);
				}
				contentLine = br.readLine();
			}
		}
		catch(Exception e)
		{
			System.out.println("File not found");
			e.printStackTrace();
		}
	}

	public void streetSort()
	{
		TreeMap<String, TreeSet<Citizen>> streetMap = new TreeMap<String, TreeSet<Citizen>>();

		for(Citizen citizen : list)
		{
			if(streetMap.get(citizen.getStreet()) == null)
			{
				TreeSet<Citizen> citizenSet = new TreeSet<Citizen>();
				citizenSet.add(citizen);
				streetMap.put(citizen.getStreet(), citizenSet);
			}
			else
			{
				TreeSet<Citizen> citizenSet = streetMap.get(citizen.getStreet());
				citizenSet.add(citizen);
				streetMap.put(citizen.getStreet(), citizenSet);
			}
		}

		Iterator<Map.Entry<String, TreeSet<Citizen>>> itr = streetMap.entrySet().iterator();
		while(itr.hasNext())
		{
			Map.Entry<String, TreeSet<Citizen>> entry = itr.next();
			System.out.println("Key = " + entry.getKey());
			for(Citizen cit : entry.getValue())
			{
				System.out.println("\t" + cit.getFirstName() + " " + cit.getLastName());
			}
		}
	}

	public void fatherBirthSort()
	{
		TreeMap<String, HashSet<String>> bpMap = new TreeMap<String, HashSet<String>>();

		for(Citizen citizen : list)
		{
			if(bpMap.get(citizen.getOccupation()) == null)
			{
				HashSet<String> bpSet = new HashSet<String>();
				bpSet.add(citizen.getFatherBirthPlace());
				bpMap.put(citizen.getOccupation(), bpSet);
			}
			else
			{
				HashSet<String> bpSet = bpMap.get(citizen.getOccupation());
				bpSet.add(citizen.getFatherBirthPlace());
				bpMap.put(citizen.getOccupation(), bpSet);
			}
		}

		Iterator<Map.Entry<String, HashSet<String>>> itr = bpMap.entrySet().iterator();
		while(itr.hasNext())
		{
			Map.Entry<String, HashSet<String>> entry = itr.next();
			System.out.println("Key = " + entry.getKey());
			for(String str : entry.getValue())
			{
				System.out.println("\t" + str);
			}
		}
	}

	public void genderSort()
	{
		TreeMap<String, TreeSet<Double>> ageMap = new TreeMap<String, TreeSet<Double>>();

		for(Citizen citizen : list)
		{
			if(ageMap.get(citizen.getGender()) == null)
			{
				TreeSet<Double> ageSet = new TreeSet<Double>();
				ageSet.add(citizen.getAge());
				ageMap.put(citizen.getGender(), ageSet);
			}
			else
			{
				TreeSet<Double> ageSet = ageMap.get(citizen.getGender());
				ageSet.add(citizen.getAge());
				ageMap.put(citizen.getGender(), ageSet);
			}
		}

		Iterator<Map.Entry<String, TreeSet<Double>>> itr = ageMap.entrySet().iterator();
		while(itr.hasNext())
		{
			Map.Entry<String, TreeSet<Double>> entry = itr.next();
			System.out.println("Key = " + entry.getKey());
			for(Double dbl : entry.getValue())
			{
				System.out.println("\t" + dbl);
			}
		}
	}

	public static void main(String[]args)
	{
		CensusRunner cr = new CensusRunner();
		cr.streetSort();
		System.out.println("\n");
		cr.fatherBirthSort();
		System.out.println("\n");
		cr.genderSort();
		System.out.println("\n");
	}
}