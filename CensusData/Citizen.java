public class Citizen implements Comparable<Citizen>
{
	String firstName;
	String lastName;
	String street;
	int streetNumber;
	String relation;
	String property;
	double propertyValue;
	String gender;
	double age;
	String maritalStatus;
	int ageFirstMarriage;
	boolean attendSchool;
	boolean canRead;
	String birthPlace;
	String fatherBirthPlace;
	String motherBirthPlace;
	String motherTongue;
	int yearImmigrated;
	String occupation;
	String industry;
	String transcriberRemarks;

	public Citizen(String firstName, String lastName, String street,String streetNumber, String relation, String property, String propertyValue, String gender, String age, String maritalStatus,String ageFirstMarriage, String attendSchool, String canRead, String birthPlace, String fatherBirthPlace, String motherBirthPlace, String motherTongue, String yearImmigrated, String occupation, String industry, String transcriberRemarks)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		try
		{
			this.streetNumber=Integer.parseInt(streetNumber);
		}
		catch(NumberFormatException e)
		{
			this.streetNumber=-1;
		}
		this.relation = relation;
		this.property = property.substring(0,1);

		if(propertyValue.charAt(0)=='$')
			propertyValue=propertyValue.substring(1);
		try
		{
			this.propertyValue=Double.parseDouble(propertyValue);
		}
		catch(NumberFormatException e)
		{
			if(propertyValue.contains("/"))
			{
				String whole=propertyValue.substring(0,propertyValue.indexOf(" "));
				String numer=propertyValue.substring(propertyValue.indexOf(" ")+1,propertyValue.indexOf("/"));
				String denom=propertyValue.substring(propertyValue.indexOf("/")+1);
				this.propertyValue=Double.parseDouble(whole)+Double.parseDouble(numer)/Double.parseDouble(denom);
			}
		}
		this.gender=gender;
		try
		{
			this.age=Double.parseDouble(age);
		}
		catch(NumberFormatException e)
		{
			if(age.charAt(0)=='.' || age.equals("un"))
				this.age=-1;
			else if(age.charAt(1)==' ' && age.contains("/"))
			{
				String whole=age.substring(0,age.indexOf(" "));
				double dec;

				if(age.substring(age.indexOf(" ")+1,age.indexOf("/")).contains("*"))
					dec=0.5;
				else
				{
					String numer=age.substring(age.indexOf(" ")+1,age.indexOf("/"));
					String denom=age.substring(age.indexOf("/")+1);
					dec=Double.parseDouble(numer)/Double.parseDouble(denom);
				}
				this.age=Double.parseDouble(whole)+dec;
			}
			else if(age.contains("*"))
			{
				this.age=Double.parseDouble(age.substring(0,age.indexOf("*")));
			}
			else
			{
				String numer=age.substring(0,age.indexOf("/"));
				String denom=age.substring(age.indexOf("/")+1);
				this.age=Double.parseDouble(numer)/Double.parseDouble(denom);
			}
		}
		this.maritalStatus=maritalStatus;
		try
		{
			this.ageFirstMarriage=Integer.parseInt(ageFirstMarriage);
		}
		catch(NumberFormatException e)
		{
			this.ageFirstMarriage=-1;
		}
		if(attendSchool.equals("Yes"))
			this.attendSchool=true;
		else
			this.attendSchool=false;

		this.canRead=false;
		if(canRead.equals("Yes"))
			this.canRead=true;

		this.birthPlace=birthPlace;
		this.fatherBirthPlace=fatherBirthPlace;
		this.motherBirthPlace=motherBirthPlace;
		this.motherTongue=motherTongue;
		try
		{
			this.yearImmigrated=Integer.parseInt(yearImmigrated);
		}
		catch(NumberFormatException e)
		{
			this.yearImmigrated=-1;
		}
		this.occupation=occupation.substring(0,1).toUpperCase()+occupation.substring(1).toLowerCase();
		this.industry=industry;
		this.transcriberRemarks = transcriberRemarks;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public String getStreet()
	{
		return street;
	}

	public int getStreetNumber()
	{
		return streetNumber;
	}

	public String getRelation()
	{
		return relation;
	}

	public String getProperty()
	{
		return property;
	}

	public double getPropertyValue()
	{
		return propertyValue;
	}

	public String getGender()
	{
		return gender;
	}

	public double getAge()
	{
		return age;
	}

	public String getMaritalStatus()
	{
		return maritalStatus;
	}

	public int getAgeFirstMarriage()
	{
		return ageFirstMarriage;
	}

	public boolean attendSchool()
	{
		return attendSchool;
	}

	public boolean canRead()
	{
		return canRead;
	}

	public String getBirthPlace()
	{
		return birthPlace;
	}

	public String getFatherBirthPlace()
	{
		return fatherBirthPlace;
	}

	public String getMotherBirthPlace()
	{
		return motherBirthPlace;
	}

	public String getMotherTongue()
	{
		return motherTongue;
	}

	public int getYearImmigrated()
	{
		return yearImmigrated;
	}

	public String getOccupation()
	{
		return occupation;
	}

	public String getIndustry()
	{
		return industry;
	}

	public String getTranscriberRemarks()
	{
		return transcriberRemarks;
	}

	public int compareTo(Citizen c)
	{
		if(getFirstName().compareTo(c.getFirstName()) != 0)
		{
			return getFirstName().compareTo(c.getFirstName());
		}
		else if(getLastName().compareTo(c.getLastName()) != 0)
		{
			return getFirstName().compareTo(c.getFirstName());
		}
		else if(getRelation().compareTo(c.getRelation()) != 0)
		{
			return getRelation().compareTo(c.getRelation());
		}
		else
		{
			return 0;
		}
	}
}