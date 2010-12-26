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
*|Used to test equality of two Book objects                      BooksQuery   |
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
   * Completeness - Completely constructs book with no parameters.
   * Convenience - Simply constructs book with no parameters.
   * Clarity - It is simple to understand that this constructs book with no
   *           parameters.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   */
   public Book(){
   } 

   /** A Constructor with initial values
   * @TheCs Cohesion -  A Constructor with initial values.
   * Completeness - Completely constructs a book object with initial values.
   * Convenience - Simply constructs a book object with initial values.
   * Clarity - It is simple to understand that this constructs a book object
   *           with initial values.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @param aISBN ISBN of book object
   * @param aTitle Title of book object
   * @param aEditionNumber edition number of book object
   * @param aCopyright copyright year of book object
   * @param aAuthorID unique author id of book object
   * @param aAuthorFirstName first name of author of book object
   * @param aAuthorLastName last name of author of book object
   */
   public Book( String aISBN, String aTitle, String aEditionNumber,
           String aCopyright, int aAuthorID, String aAuthorFirstName,
           String aAuthorLastName ){
       /**
        * Basically this will take in values when the Book object is created
        * and below it uses all the setter methods of this class to assign this
        * book object these values.
        */
       setISBN( aISBN );
       setTitle( aTitle );
       setEditionNumber( aEditionNumber );
       setCopyright( aCopyright );
       setAuthorID( aAuthorID );
       setAuthorFirstName( aAuthorFirstName );
       setAuthorLastName( aAuthorLastName );
   } 


   /** Sets the authorId of the book object.
   * @TheCs Cohesion - Sets the authorId of the book object.
   * Completeness - Completely sets the authorId of the book object.
   * Convenience - Simply sets the authorId of the book object.
   * Clarity - It is simple to understand that this sets the authorId of the
   *           book object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @param aAuthorID unique author id of book object
   */
   private void setAuthorID( int aAuthorID ){
       authorID = aAuthorID;
   }

   /** Gets the authorId of the book object.
   * @TheCs Cohesion - Gets the authorId of the book object.
   * Completeness - Completely gets the authorId of the book object.
   * Convenience - Simply gets the authorId of the book object.
   * Clarity - It is simple to understand that this gets the authorId of the
   *           book object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @return unique author id of book object
   */
   public int getAuthorID(){
       return authorID;
   }

   /** Sets the ISBN of the book object.
   * @TheCs Cohesion - Sets the ISBN of the book object.
   * Completeness - Completely sets the ISBN of the book object.
   * Convenience - Simply sets the ISBN of the book object.
   * Clarity - It is simple to understand that this sets the ISBN of the book
   *           object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @param aISBN ISBN of book object
   */
   private void setISBN( String aISBN ){
       ISBN = aISBN;
   }

   /** Gets the ISBN of the book object.
   * @TheCs Cohesion - Gets the ISBN of the book object.
   * Completeness - Completely gets the ISBN of the book object.
   * Convenience - Simply gets the ISBN of the book object.
   * Clarity - It is simple to understand that this gets the ISBN of the book
   *           object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @return ISBN of book object
   */
   public String getISBN(){
       return ISBN;
   } 
   

   /** Sets the title of the book object.
   * @TheCs Cohesion - Sets the title of the book object.
   * Completeness - Completely sets the title of the book object.
   * Convenience - Simply sets the title of the book object.
   * Clarity - It is simple to understand that this sets the title of the book
   *           object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @param aTitle title of book object
   */
   private void setTitle( String aTitle ){
       title = aTitle;
   }


   /** Gets the title of the book object.
   * @TheCs Cohesion - Gets the title of the book object.
   * Completeness - Completely gets the title of the book object.
   * Convenience - Simply gets the title of the book object.
   * Clarity - It is simple to understand that this gets the title of the book
   *           object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @return title of book object
   */
   public String getTitle(){
       return title;
   }
   
   /** Sets the edition # of the book object.
   * @TheCs Cohesion - Sets the edition # of the book object.
   * Completeness - Completely sets the edition # of the book object.
   * Convenience - Simply sets the edition # of the book object.
   * Clarity - It is simple to understand that this sets the edition # of the
   *           book object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @param aEditionNumber edition number of book object
   */
   private void setEditionNumber( String aEditionNumber ){
       editionNumber = aEditionNumber;
   }

   /** Gets the edition # of the book object.
   * @TheCs Cohesion - Gets the edition # of the book object.
   * Completeness - Completely gets the edition # of the book object.
   * Convenience - Simply gets the edition # of the book object.
   * Clarity - It is simple to understand that this gets the edition # of the
   *           book object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @return edition number of book object
   */
   public String getEditionNumber(){
       return editionNumber;
   }
   
   /** Sets the copyright year of the book object.
   * @TheCs Cohesion - Sets the copyright year of the book object.
   * Completeness - Completely sets the copyright year of the book object.
   * Convenience - Simply sets the copyright year of the book object.
   * Clarity - It is simple to understand that this sets the copyright year of
   *           the book object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @param aCopyright copyright year of book object
   */
   private void setCopyright( String aCopyright ){
       copyright = aCopyright;
   }

   /** Gets the copyright year of the book object.
   * @TheCs Cohesion - Gets the copyright year of the book object.
   * Completeness - Completely gets the copyright year of the book object.
   * Convenience - Simply gets the copyright year of the book object.
   * Clarity - It is simple to understand that this gets the copyright year of
   *           the book object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @return copyright year of book object
   */
   public String getCopyright(){
       return copyright;
   }
   
   /** Sets the author first name of the book object.
   * @TheCs Cohesion - Sets the author first name of the book object.
   * Completeness - Completely sets the author first name of the book object.
   * Convenience - Simply sets the author first name of the book object.
   * Clarity - It is simple to understand that this sets the author first name
   *           of the book object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @param aAuthorFirstName  author first name of book object
   */
   private void setAuthorFirstName( String aAuthorFirstName ){
       authorFirstName = aAuthorFirstName;
   }

   /** Gets the author first name of the book object.
   * @TheCs Cohesion - Gets the author first name of the book object.
   * Completeness - Completely gets the author first name of the book object.
   * Convenience - Simply gets the author first name of the book object.
   * Clarity - It is simple to understand that this gets the author first name
   *           of the book object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @return author first name of book object
   */
   public String getAuthorFirstName(){
       return authorFirstName;
   }

   /** Sets the author last name of the book object.
   * @TheCs Cohesion - Sets the author last name of the book object.
   * Completeness - Completely sets the author last name of the book object.
   * Convenience - Simply sets the author last name of the book object.
   * Clarity - It is simple to understand that this sets the author last name
   *           of the book object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @param aAuthorLastName author last name of book object
   */
   private void setAuthorLastName( String aAuthorLastName ){
       authorLastName = aAuthorLastName;
   }

   /** Gets the author last name of the book object.
   * @TheCs Cohesion - Gets the author last name of the book object.
   * Completeness - Completely gets the author last name of the book object.
   * Convenience - Simply gets the author last name of the book object.
   * Clarity - It is simple to understand that this gets the author last name
   *           of the book object.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @return author last name of book object
   */
   public String getAuthorLastName(){
       return authorLastName;
   }


    /**
    * This method takes the author first/last name and the book isbn, and
    * multiplies their hashCodes together and by 17, a prime number,
    * to create a unique hashCode that will later be used to test for equality
    * of Book objects in the BooksQuery class.
    * @TheCs Cohesion - Sets the hashCode of the Book object to 17 times
    *                   the first/last name and isbn. 17 used since it's a prime
    * Completeness - Completely sets the hashCode of the Book object.
    * Convenience -  Simply sets the hashCode of the Book object.
    * Clarity - It is simple to understand that this sets the hashCode of the
    *           Book object.
    * Consistency - It uses the same syntax rules as the rest of the class and
    *               continues to use proper casing and indentation.
    * @return unique hashCode of book object
    */
    @Override
    public int hashCode(){
        return 17 * getAuthorFirstName().hashCode() * 
                getAuthorLastName().hashCode() *
                getISBN().hashCode() ;
    }

    /**
    * This method first checks to see if the object in question is null and
    * if so returns false. It then test to see if the objects refer to the same
    * object and returns true if they do. It then checks to see if obj is an
    * instance of Book and returns false if it is not or if so it then cast
    * the object to an Book object and test the hashCode of each object and
    * returns true or false depending on if the hashCodes are equals or not.
    * @TheCs Cohesion - Overrides the equals method. Test for equality of two
    *                   objects.
    * Completeness - Completely test for equality of two objects.
    * Convenience -  Simply test for equality of two objects.
    * Clarity - It is simple to understand that this test for equality of two
    *           Book objects.
    * Consistency - It uses the same syntax rules as the rest of the class and
    *               continues to use proper casing and indentation.
    * @param obj Check for equality against this object.
    * @precondition obj be a Book object, otherwise return false.
    * @return true or false depending on if objects are equal or not
    */
    @Override
    public boolean equals(Object obj){

        if (obj == null) return false;
        if (this == obj) return true;
        if (obj instanceof Book){
            Book other = (Book) obj;
            return this.hashCode() == other.hashCode();
        }else{
            return false;
        }

    }
} 



 