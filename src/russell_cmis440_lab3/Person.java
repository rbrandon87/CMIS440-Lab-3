package russell_cmis440_lab3;


/**
* Program Name: CMIS440 Lab 3 JavaDB Address Book Program
* @author Brandon R Russell
* @Course CMIS440
* Date: Dec 15,2010
*/

/** This object is the basic definition of a person for the purpose of the
 * AddressBook program. It lays the foundation of what data on a person should
 * be known.
*|----------------------------------------------------------------------------|
*|                           CRC: Person                                      |
*|----------------------------------------------------------------------------|
*|Provides setter/getter methods for Person object         AddressBookDisplay |
*|----------------------------------------------------------------------------|
*
* @TheCs Cohesion - All methods in this class work together on similar task.
* Completeness - Completely creates word counter threads to process file input
* Convenience - There are sufficient methods and variables to complete the
*                required task.
* Clarity - The methods and variables are distinguishable and work in a
*           uniform manner to provide clarity to other programmers.
* Consistency - All names,parameters ,return values , and behaviors follow
*               the same basic rules.
*/



public class Person
{
   private int addressID;
   private String firstName;
   private String lastName;
   private String email;
   private String phoneNumber;

   /** A blank constructor
   * @TheCs Cohesion - A blank constructor
   * Completeness - Completely constructs person with no parameters.
   * Convenience - Simply constructs person with no parameters.
   * Clarity - It is simple to understand that this constructs person with no
   *           parameters.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   */
   public Person(){
   } 

   /** A Constructor with initial values
   * @TheCs Cohesion -  A Constructor with initial values.
   * Completeness - Completely constructs a person object with initial values.
   * Convenience - Simply constructs a person object with initial values.
   * Clarity - It is simple to understand that this constructs a person object
   *           with initial values.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @param id unique id of person object
   * @param first first name of person for person object
   * @param last last name of person for person object
   * @param emailAddress email address of person for person object
   * @param phone phone number of person for person object
   */
   public Person( int id, String first, String last,
           String emailAddress, String phone ){
       /**
        * Basically this will take in values when the Person object is created
        * and below it uses all the setter methods of this class to assign this
        * person object these values.
        */
       setAddressID( id );
       setFirstName( first );
       setLastName( last );
       setEmail( emailAddress );
       setPhoneNumber( phone );
   } 


   /** Sets the addressId of the person object.
   * @TheCs Cohesion - Sets the addressId of the person object.
   * Completeness - Completely sets the addressId of the person object.
   * Convenience - Simply sets the addressId of the person object.
   * Clarity - It is simple to understand that this sets the addressId of the
   *           person object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @param unique id of person object
   */
   private void setAddressID( int id ){
       addressID = id;
   }

   /** Gets the addressId of the person object.
   * @TheCs Cohesion - Gets the addressId of the person object.
   * Completeness - Completely gets the addressId of the person object.
   * Convenience - Simply gets the addressId of the person object.
   * Clarity - It is simple to understand that this gets the addressId of the
   *           person object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @return unique id of person object
   */
   public int getAddressID(){
       return addressID;
   } 
   

   /** Sets the firstName of the person object.
   * @TheCs Cohesion - Sets the firstName of the person object.
   * Completeness - Completely sets the firstName of the person object.
   * Convenience - Simply sets the firstName of the person object.
   * Clarity - It is simple to understand that this sets the firstName of the
   *           person object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @param first first name of person object
   */
   private void setFirstName( String first ){
       firstName = first;
   }


   /** Gets the firstName of the person object.
   * @TheCs Cohesion - Gets the firstName of the person object.
   * Completeness - Completely gets the firstName of the person object.
   * Convenience - Simply gets the firstName of the person object.
   * Clarity - It is simple to understand that this gets the firstName of the
   *           person object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @return return first name of person object
   */
   public String getFirstName(){
       return firstName;
   }
   
   /** Sets the lastName of the person object.
   * @TheCs Cohesion - Sets the lastName of the person object.
   * Completeness - Completely sets the lastName of the person object.
   * Convenience - Simply sets the lastName of the person object.
   * Clarity - It is simple to understand that this sets the lastName of the
   *           person object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @param last last name of person object
   */
   private void setLastName( String last ){
       lastName = last;
   }

   /** Gets the lastName of the person object.
   * @TheCs Cohesion - Gets the lastName of the person object.
   * Completeness - Completely gets the lastName of the person object.
   * Convenience - Simply gets the lastName of the person object.
   * Clarity - It is simple to understand that this gets the lastName of the
   *           person object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @return last name of person object
   */
   public String getLastName(){
       return lastName;
   }
   
   /** Sets the email of the person object.
   * @TheCs Cohesion - Sets the email of the person object.
   * Completeness - Completely sets the email of the person object.
   * Convenience - Simply sets the email of the person object.
   * Clarity - It is simple to understand that this sets the email of the
   *           person object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @param emailAddress email address of person object
   */
   private void setEmail( String emailAddress ){
       email = emailAddress;
   }

   /** Gets the email of the person object.
   * @TheCs Cohesion - Gets the email of the person object.
   * Completeness - Completely gets the email of the person object.
   * Convenience - Simply gets the email of the person object.
   * Clarity - It is simple to understand that this gets the email of the
   *           person object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @return email address of person object
   */
   public String getEmail(){
       return email;
   }
   
   /** Sets the phoneNumber of the person object.
   * @TheCs Cohesion - Sets the phoneNumber of the person object.
   * Completeness - Completely sets the phoneNumber of the person object.
   * Convenience - Simply sets the phoneNumber of the person object.
   * Clarity - It is simple to understand that this sets the phoneNumber of the
   *           person object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @param phone phone number of person object
   */
   private void setPhoneNumber( String phone ){
       phoneNumber = phone;
   }

   /** Gets the phoneNumber of the person object.
   * @TheCs Cohesion - Gets the phoneNumber of the person object.
   * Completeness - Completely gets the phoneNumber of the person object.
   * Convenience - Simply gets the phoneNumber of the person object.
   * Clarity - It is simple to understand that this gets the phoneNumber of the
   *           person object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @return phone number of person object
   */
   public String getPhoneNumber(){
       return phoneNumber;
   } 
} 



 