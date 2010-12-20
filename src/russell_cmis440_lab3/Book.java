package russell_cmis440_lab3;


/**
* Program Name: CMIS440 Lab 3 JavaDB Address Book Program
* @author Brandon R Russell
* @Course CMIS440
* Date: Dec 15,2010
*/

/** This object is the basic definition of a book for the purpose of the
 * Book program. It lays the foundation of what data on a book should
 * be known.
*|----------------------------------------------------------------------------|
*|                           CRC: Book                                        |
*|----------------------------------------------------------------------------|
*|Provides setter/getter methods for Book object                 BooksDisplay |
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



public class Book
{
   private String ISBN = "";
   private String title = "";
   private String editionNumber = "";
   private String copyright = "";
   private int authorID = 0;
   private String authorFirstName = "";
   private String authorLastName = "";

   /** A blank constructor
   * @TheCs Cohesion - A blank constructor
   * Completeness - Completely constructs person with no parameters.
   * Convenience - Simply constructs person with no parameters.
   * Clarity - It is simple to understand that this constructs person with no
   *           parameters.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   */
   public Book(){
   } 

   /** A Constructor with initial values
   * @TheCs Cohesion -  A Constructor with initial values.
   * Completeness - Completely constructs a person object with initial values.
   * Convenience - Simply constructs a person object with initial values.
   * Clarity - It is simple to understand that this constructs a person object
   *           with initial values.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   */
   public Book( String aISBN, String aTitle, String aEditionNumber,
           String aCopyright, int aAuthorID, String aAuthorFirstName, String aAuthorLastName ){
       /**
        * Basically this will take in values when the Person object is created
        * and below it uses all the setter methods of this class to assign this
        * person object these values.
        */
       setISBN( aISBN );
       setTitle( aTitle );
       setEditionNumber( aEditionNumber );
       setCopyright( aCopyright );
       setAuthorID( aAuthorID );
       setAuthorFirstName( aAuthorFirstName );
       setAuthorLastName( aAuthorLastName );
   } 


   /** Sets the addressId of the person object.
   * @TheCs Cohesion - Sets the addressId of the person object.
   * Completeness - Completely sets the addressId of the person object.
   * Convenience - Simply sets the addressId of the person object.
   * Clarity - It is simple to understand that this sets the addressId of the
   *           person object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   */
   private void setAuthorID( int aAuthorID ){
       authorID = aAuthorID;
   }

   /** Gets the addressId of the person object.
   * @TheCs Cohesion - Gets the addressId of the person object.
   * Completeness - Completely gets the addressId of the person object.
   * Convenience - Simply gets the addressId of the person object.
   * Clarity - It is simple to understand that this gets the addressId of the
   *           person object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   */
   public int getAuthorID(){
       return authorID;
   }

   /** Sets the addressId of the person object.
   * @TheCs Cohesion - Sets the addressId of the person object.
   * Completeness - Completely sets the addressId of the person object.
   * Convenience - Simply sets the addressId of the person object.
   * Clarity - It is simple to understand that this sets the addressId of the
   *           person object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   */
   private void setISBN( String aISBN ){
       ISBN = aISBN;
   }

   /** Gets the addressId of the person object.
   * @TheCs Cohesion - Gets the addressId of the person object.
   * Completeness - Completely gets the addressId of the person object.
   * Convenience - Simply gets the addressId of the person object.
   * Clarity - It is simple to understand that this gets the addressId of the
   *           person object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   */
   public String getISBN(){
       return ISBN;
   } 
   

   /** Sets the firstName of the person object.
   * @TheCs Cohesion - Sets the firstName of the person object.
   * Completeness - Completely sets the firstName of the person object.
   * Convenience - Simply sets the firstName of the person object.
   * Clarity - It is simple to understand that this sets the firstName of the
   *           person object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   */
   private void setTitle( String aTitle ){
       title = aTitle;
   }


   /** Gets the firstName of the person object.
   * @TheCs Cohesion - Gets the firstName of the person object.
   * Completeness - Completely gets the firstName of the person object.
   * Convenience - Simply gets the firstName of the person object.
   * Clarity - It is simple to understand that this gets the firstName of the
   *           person object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   */
   public String getTitle(){
       return title;
   }
   
   /** Sets the lastName of the person object.
   * @TheCs Cohesion - Sets the lastName of the person object.
   * Completeness - Completely sets the lastName of the person object.
   * Convenience - Simply sets the lastName of the person object.
   * Clarity - It is simple to understand that this sets the lastName of the
   *           person object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   */
   private void setEditionNumber( String aEditionNumber ){
       editionNumber = aEditionNumber;
   }

   /** Gets the lastName of the person object.
   * @TheCs Cohesion - Gets the lastName of the person object.
   * Completeness - Completely gets the lastName of the person object.
   * Convenience - Simply gets the lastName of the person object.
   * Clarity - It is simple to understand that this gets the lastName of the
   *           person object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   */
   public String getEditionNumber(){
       return editionNumber;
   }
   
   /** Sets the email of the person object.
   * @TheCs Cohesion - Sets the email of the person object.
   * Completeness - Completely sets the email of the person object.
   * Convenience - Simply sets the email of the person object.
   * Clarity - It is simple to understand that this sets the email of the
   *           person object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   */
   private void setCopyright( String aCopyright ){
       copyright = aCopyright;
   }

   /** Gets the email of the person object.
   * @TheCs Cohesion - Gets the email of the person object.
   * Completeness - Completely gets the email of the person object.
   * Convenience - Simply gets the email of the person object.
   * Clarity - It is simple to understand that this gets the email of the
   *           person object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   */
   public String getCopyright(){
       return copyright;
   }
   
   /** Sets the phoneNumber of the person object.
   * @TheCs Cohesion - Sets the phoneNumber of the person object.
   * Completeness - Completely sets the phoneNumber of the person object.
   * Convenience - Simply sets the phoneNumber of the person object.
   * Clarity - It is simple to understand that this sets the phoneNumber of the
   *           person object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   */
   private void setAuthorFirstName( String aAuthorFirstName ){
       authorFirstName = aAuthorFirstName;
   }

   /** Gets the phoneNumber of the person object.
   * @TheCs Cohesion - Gets the phoneNumber of the person object.
   * Completeness - Completely gets the phoneNumber of the person object.
   * Convenience - Simply gets the phoneNumber of the person object.
   * Clarity - It is simple to understand that this gets the phoneNumber of the
   *           person object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   */
   public String getAuthorFirstName(){
       return authorFirstName;
   }

   /** Sets the phoneNumber of the person object.
   * @TheCs Cohesion - Sets the phoneNumber of the person object.
   * Completeness - Completely sets the phoneNumber of the person object.
   * Convenience - Simply sets the phoneNumber of the person object.
   * Clarity - It is simple to understand that this sets the phoneNumber of the
   *           person object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   */
   private void setAuthorLastName( String aAuthorLastName ){
       authorLastName = aAuthorLastName;
   }

   /** Gets the phoneNumber of the person object.
   * @TheCs Cohesion - Gets the phoneNumber of the person object.
   * Completeness - Completely gets the phoneNumber of the person object.
   * Convenience - Simply gets the phoneNumber of the person object.
   * Clarity - It is simple to understand that this gets the phoneNumber of the
   *           person object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   */
   public String getAuthorLastName(){
       return authorLastName;
   }
} 



 