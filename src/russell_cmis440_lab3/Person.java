package russell_cmis440_lab3;

// Fig. 28.30: Person.java
// Person class that represents an entry in an address book.
public class Person
{
   private int addressID;
   private String firstName;
   private String lastName;
   private String email;
   private String phoneNumber;

   // no-argument constructor
   public Person()
   {
   } // end no-argument Person constructor

   // constructor
   public Person( int id, String first, String last, 
      String emailAddress, String phone )
   {
      setAddressID( id );
      setFirstName( first );
      setLastName( last );
      setEmail( emailAddress );
      setPhoneNumber( phone );
   } // end five-argument Person constructor 

   // sets the addressID
   private void setAddressID( int id )
   {
      addressID = id;
   } // end method setAddressID

   // returns the addressID 
   public int getAddressID()
   {
      return addressID;
   } // end method getAddressID
   
   // sets the firstName
   private void setFirstName( String first )
   {
      firstName = first;
   } // end method setFirstName

   // returns the first name 
   public String getFirstName()
   {
      return firstName;
   } // end method getFirstName
   
   // sets the lastName
   private void setLastName( String last )
   {
      lastName = last;
   } // end method setLastName

   // returns the last name 
   public String getLastName()
   {
      return lastName;
   } // end method getLastName
   
   // sets the email address
   private void setEmail( String emailAddress )
   {
      email = emailAddress;
   } // end method setEmail

   // returns the email address
   public String getEmail()
   {
      return email;
   } // end method getEmail
   
   // sets the phone number
   private void setPhoneNumber( String phone )
   {
      phoneNumber = phone;
   } // end method setPhoneNumber

   // returns the phone number
   public String getPhoneNumber()
   {
      return phoneNumber;
   } // end method getPhoneNumber
} // end class Person



 