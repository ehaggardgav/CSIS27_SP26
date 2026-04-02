/**
   A mutable class that represents a person's name 
   that can be compared and cloned.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public class ComparableCopyableName
             implements ComparableAndCopyable<ComparableCopyableName>
{
	private String first; // First name
	private String last;  // Last name

	public ComparableCopyableName()
	{
		this("", "");
	} // end default constructor

	public ComparableCopyableName(String firstName, String lastName)
	{
		first = firstName;
		last = lastName;
	} // end constructor

	public void setName(String firstName, String lastName)
	{
		setFirst(firstName);
		setLast(lastName);
	} // end setName

	public String getName()
	{
		return toString();
	} // end getName

	public void setFirst(String firstName)
	{
		first = firstName; 
	} // end setFirst

	public String getFirst()
	{
		return first;
	} // end getFirst

	public void setLast(String lastName)
	{
		last = lastName;
	} // end setLast

	public String getLast()
	{
		return last;
	} // end getLast

	public void giveLastNameTo(ComparableCopyableName aName)
	{
		aName.setLast(last);
	} // end giveLastNameTo

	public String toString()
	{
		return first + " " + last;
	} // end toString

	public boolean equals(Object other)
	{
      boolean result;

      if ((other == null) || (getClass() != other.getClass()))
         result = false;
      else
      {
         ComparableCopyableName otherName = (ComparableCopyableName)other;
         result = first.equals(otherName.first) && 
                  last.equals(otherName.last);
      } // end if

      return result;
	} // end equals

	public int compareTo(ComparableCopyableName otherName)
	{
		int result = last.compareTo(otherName.last);
		
		// If last names are equal, check first
		if (result == 0)
		{
			result = first.compareTo(otherName.first);
		} // end if
		
		return result;
	} // end compareTo
   
	public Object clone()
   {
      ComparableCopyableName theCopy = null;
      try
      {
         theCopy = (ComparableCopyableName)super.clone(); // Object can throw an exception
      }
      catch (CloneNotSupportedException e)
      {
         System.err.println("Name cannot clone: " + e.toString());
      }
      
      return theCopy;
   } // end clone
} // end ComparableCopyableName
