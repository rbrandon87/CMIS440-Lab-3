package russell_cmis440_lab3;

/**
* Program Name: CMIS440 Lab 3 JavaDB Address Book Program
* @author Brandon R Russell
* @Course CMIS440
* Date: Dec 15,2010
*/

/** This class is used to perform various queries on the Book database
* Also, this class extends the AbstractTableModel in order for a JTable to be
* modeled after this class so that it can display its data.
*|----------------------------------------------------------------------------|
*|                           CRC: BooksQuery                                  |
*|----------------------------------------------------------------------------|
*|Used to query database to insert/update/delete entries         BooksDisplay |
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


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class BooksQuery extends AbstractTableModel
{
   private static final String URL = "jdbc:derby:Books";
   private static final String USERNAME = "deitel";
   private static final String PASSWORD = "deitel";

   private Connection connection = null; // manages connection

   //****Begin variable initializing here****
   private PreparedStatement selectAll = null;
   private PreparedStatement selectAllAuthorISBN = null;
   private PreparedStatement selectByAuthorLastName = null;

   private PreparedStatement insertNewBook = null;
   private PreparedStatement insertNewAuthor = null;
   private PreparedStatement insertAuthorISBN = null;

   private PreparedStatement deleteBook = null;
   private PreparedStatement deleteAuthor = null;
   private PreparedStatement deleteAuthorISBN = null;
  

   private PreparedStatement updateAuthor = null;
   private PreparedStatement updateByDeleteAuthorISBN = null;

   private String selectAllAuthorISBNSQL = "";
   private String selectAllSQL = "";
   private String selectByAuthorLastNameSQL = "";

   private String insertNewAuthorSQL = "";
   private String insertIntoAuthorISBNSQL = "";
   private String insertNewBookSQL = "";

   private String deleteBookSQL = "";
   private String deleteAuthorSQL = "";
   private String deleteFromAuthorISBNSQL = "";
   
   private String updateAuthorSQL = "";
   private String updateByDeleteAuthorISBNSQL = "";
   
   private int numberOfRows;
   private Statement statement;
   private ResultSetMetaData metaData;
   private ResultSet resultSet;

   // keep track of database connection status
   private boolean connectedToDatabase = false;
   private String lastError = null;
   //****End variable initializing here****

   
   /** Constructs a BookQuery object. Sets up SQl statements.
   * @TheCs Cohesion - Constructs a BookQuery object. Sets up SQl
   *                   statements.
   * Completeness - Completely constructs a BookQuery object. Sets up SQl
   *                statements.
   * Convenience - Simply constructs a BookQuery object. Sets up SQl
   *               statements.
   * Clarity - It is simple to understand that this constructs a BookQuery
   *           object. Sets up SQl statements.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @exception SQLException if any problems w/ SQL statement preparation
   * @exception Exception  general exception capture
   */
   public BooksQuery(){
       try{
           lastError = null; //Will hold any errors from method
           //*****Begin SQL Definitions******
           /**
            * This select is used during the update/delete methods
            * to ensure the integrity of my key constraints. I tried using the
            * select w/ the joins, but it would not provide the information
            * I needed so I had to use separate sql statement w/o the joins.
            */

           selectAllAuthorISBNSQL = "select * from authorISBN";

           /**
            * Originally I was designing the program to allow author without
            * books or vice versa and that is why I choose the LEFT/RIGHT
            * OUTER JOINS here, however this proved to be difficult given the
            * nature of isbn being used as a foreign key constraint. However,
            * even though I am no longer following this design the outer joins
            * still proved to work in the new context so I didn't see a reason
            * to go back to the inner joins; however, just to be on the safe
            * side I decided to go back to them anyways.
            */
           selectAllSQL = "SELECT * FROM authors "
            + "INNER JOIN authorISBN "
            + "on authors.authorID=authorISBN.authorID "
            + "INNER JOIN Titles on AuthorISBN.isbn=Titles.isbn";

           
           selectByAuthorLastNameSQL = "SELECT * FROM authors "
            + "INNER JOIN authorISBN "
            + "on authors.authorID=authorISBN.authorID "
            + "INNER JOIN Titles on AuthorISBN.isbn=Titles.isbn "
            + "WHERE authors.LastName = ?";

           insertNewBookSQL = "INSERT INTO Titles "
                   + "( ISBN, Title, EditionNumber, Copyright) "
                   + "VALUES ( ?, ?, ?, ? )";

           insertIntoAuthorISBNSQL = "INSERT INTO authorISBN "
                   + "( AuthorID, ISBN ) VALUES ( ?, ? )";

           insertNewAuthorSQL = "INSERT INTO authors ( FirstName, LastName)"
                   + " VALUES ( ?, ? )";

           deleteBookSQL = "DELETE FROM Titles WHERE ISBN = ?";

           deleteFromAuthorISBNSQL = "DELETE FROM authorISBN "
                   + "WHERE AuthorID = ? AND ISBN = ?";

           deleteAuthorSQL = "DELETE FROM authors WHERE AuthorID = ?";
           /**
            * While there are three separate insert/delete statements, one for
            * each table, there is only one update commands for the authors
            * table and one delete command used in conjunction with updating
            * the authorISBN table. Given the nature of the key constraints
            * on the other two tables I was forced to do a delete/insert
            * on those instead of a update command. IF JavaDB supported
            * deferred foreign keys it would be another situation, but given
            * this limitation the insert/delete will have to suffice.
            */
           updateAuthorSQL = "UPDATE authors SET FirstName=?, LastName=? "
                   + "WHERE AuthorID=?";
           updateByDeleteAuthorISBNSQL = "DELETE FROM authorISBN "
                   + "WHERE ISBN = ?";
           //*****End SQL Definitions******
           
           connection = DriverManager.getConnection( URL, USERNAME, PASSWORD );

           selectAllAuthorISBN =
                   connection.prepareStatement(selectAllAuthorISBNSQL);
           // create query that selects all entries in the Book Database
           selectAll = connection.prepareStatement( selectAllSQL );
           // create query that selects entries with a specific last name
           selectByAuthorLastName = connection.prepareStatement(
                   selectByAuthorLastNameSQL );
           // create inserts that adds new entries into the database
           insertNewBook = connection.prepareStatement(insertNewBookSQL );
           insertAuthorISBN =
                   connection.prepareStatement(insertIntoAuthorISBNSQL );
           insertNewAuthor = connection.prepareStatement(insertNewAuthorSQL,  Statement.RETURN_GENERATED_KEYS );

           // create update that updates a entry in the database
           updateAuthor = connection.prepareStatement(updateAuthorSQL);
           updateByDeleteAuthorISBN =
                   connection.prepareStatement(updateByDeleteAuthorISBNSQL);
           // create deletes that delete entries from the database
           deleteBook = connection.prepareStatement(deleteBookSQL);
           deleteAuthorISBN =
                   connection.prepareStatement(deleteFromAuthorISBNSQL);
           deleteAuthor = connection.prepareStatement(deleteAuthorSQL);
           
           // create Statement to query database
           statement = connection.createStatement(
                   ResultSet.TYPE_SCROLL_INSENSITIVE,
                   ResultSet.CONCUR_READ_ONLY );

           // update database connection status
           connectedToDatabase = true;

           // set query and execute it
           setQuery( selectAllSQL );
       }
       catch ( SQLException sqlException ){
           lastError += sqlException.toString();
       }catch ( Exception exception){
           lastError += exception.toString();
      }
   } 
   
   /** Returns a list of books/authors currently in database
   * @TheCs Cohesion - Returns a list of books/authors currently in database
   * Completeness - Completely returns a list of books/authors currently
   *                in database.
   * Convenience - Simply returns a list of books/authors currently in database.
   * Clarity - It is simple to understand that this returns a list of books/
   *           authors currently in database.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @exception SQLException if problems with executing SQL statements
   * @exception Exception general exception capture
   * @return results List of persons in database
   */
   public List< Book > getAllBooks(){
       lastError = null;  //Will hold any errors from method
       List< Book > results = null;
       ResultSet myResultSet = null;
      
       try{
           /**
            * setQuery has to be called here for the purpose of keeping the
            * JTable data up-to-date. Next the selectAllSQL SQL is executed
            * and places into myResultSet all books from the database.
            */
           setQuery( selectAllSQL );
           myResultSet = selectAll.executeQuery();
           results = new ArrayList< Book >();
           /**
            * Loops through the Resultset and creates a new Book object for
            * each entry and places this is the results ArrayList.
            *
            */
           while ( myResultSet.next() ){
               results.add( new Book(
                       myResultSet.getString( "ISBN" ),
                       myResultSet.getString( "title" ),
                       myResultSet.getString( "editionNumber" ),
                       myResultSet.getString( "copyright" ),
                       myResultSet.getInt( "authorID" ),
                       myResultSet.getString("firstName"),
                       myResultSet.getString("lastName")) );
           }
       }catch ( SQLException sqlException ){
           lastError += sqlException.toString();
       }catch ( Exception exception){
           lastError += exception.toString();
      }finally{
          try{
              myResultSet.close();
          }catch ( SQLException sqlException ){
            lastError += sqlException.toString();
            close();
         }finally{
             return results;
         }
      }
   } 

   
   /** Return entries with given last name
   * @TheCs Cohesion - Return entries with given last name.
   * Completeness - Completely returns entries with given last name.
   * Convenience - Simply returns entries with given last name.
   * Clarity - It is simple to understand that this returns entries with given
   *           last name.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @exception SQLException if problems with executing SQL statements
   * @exception Exception general exception capture
   * @return results List of books in database w/ given last name of author
   * @param name author last name to find in entries
   *
   */
   public List< Book > getBookByAuthorLastName( String name ){
       lastError = null;  //Will hold any errors from method
       List< Book > results = null;
       ResultSet myResultSet = null;
       try{
           /**
            * The setString method will insert the name variable into the sql
            * statement that was defined in the constructor. setQuery is needed
            * to keep JTable updated.
            */
           selectByAuthorLastName.setString( 1, name ); // specify last name
           /**
            * Interestingly enough you cannot get a string from a
            * PreparedStatement that represents the SQL statement so I had to
            * use my string definition from before and replace the ? w/ name
            * manually below.
            */
           setQuery(selectByAuthorLastNameSQL.replace("?", "'" +name + "'"));
           // executeQuery returns ResultSet containing matching entries
           myResultSet = selectByAuthorLastName.executeQuery();

           results = new ArrayList< Book >();
           /**
            * Loops through the Resultset and creates a new Book object for
            * each entry and places this is the results ArrayList.
            */
           while ( myResultSet.next() ){
               results.add( new Book(
                       myResultSet.getString( "ISBN" ),
                       myResultSet.getString( "title" ),
                       myResultSet.getString( "editionNumber" ),
                       myResultSet.getString( "copyright" ),
                       myResultSet.getInt( "authorID" ),
                       myResultSet.getString("firstName"),
                       myResultSet.getString("lastName")) );
           }
       } catch ( SQLException sqlException ){
           lastError += sqlException.toString();
      }catch ( Exception exception){
          lastError += exception.toString();
      }finally{
          try{
              myResultSet.close();
          }catch ( SQLException sqlException ){
              lastError += sqlException.toString();
              close();
          }finally{
              return results;      
          }
      }
   } 
   
   /** Adds a book/author to the database
   * @TheCs Cohesion -  Adds a book/author to the database.
   * Completeness - Completely  adds a book/author to the database.
   * Convenience - Simply adds a book/author to the database.
   * Clarity - It is simple to understand that this adds a book/author to the
   *           database.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @throws Exception if author/book already exist in database
   * @exception SQLException for sql string being executed
   * @exception Exception general exception capture
   * @param aISBN ISBN of book to be added
   * @param aTitle Title of book to be added
   * @param aEditionNumber Edition Number of book to be added
   * @param aCopyright copyright year of book to be added
   * @param aAuthorFirstName first name of book author
   * @param aAuthorLastName last name of book author
   * @return result returns number of rows affected by sql statement
   */
   public int addBook(String aISBN, String aTitle, int aEditionNumber, 
           String aCopyright, String aAuthorFirstName,
           String aAuthorLastName){
       
       ResultSet tempResultSet = null;
       List< Book > results = getAllBooks();
       /**
        * tempBook makes a temporary book object w/ only the information needed
        * for comparison to determine of the book/author being entered already
        * exist or not.
        */
       Book tempBookCheck =
               new Book(aISBN,"","","",0,aAuthorFirstName,aAuthorLastName);
       
       lastError = null;//Will hold any errors from method
       int result = 0;
       int TempAuthorIdHolder = 0;//Hold authorId of book author
       boolean newAuthor = true; //determine if new author or existing
       boolean newBook = true;//determine if book already exists or if is new
       // set parameters, then execute insertNewPerson
       try{
           if (results.contains(tempBookCheck)) {
               /**
                * If the author/book combo already exist then throw an exception
                */
               throw new Exception("This Author and Book are already added.");
           }
           for (int i = 0; i< results.size(); i++){

               if (results.get(i).getISBN().equalsIgnoreCase(aISBN)){
                   /**
                    * If the book already exist don't add it again
                    */
                   newBook = false;
               }else if(newBook){
                   /**
                    * Else, if the book is not found then add it.
                    */
                   newBook = true;
               }else {
                   /**
                    * Do Nothing. I needed this here so that the two if
                    * statements, one to check if the book is new and one to
                    * check if the author is new, would both we checked
                    * separately. If they weren't it wouldn't work correctly.
                    */
               }
               /**
                * I went past the 80 character mark on this one, but I think
                * its more readable this way.
                */
               if(results.get(i).getAuthorLastName().equalsIgnoreCase(aAuthorLastName) &&
                  results.get(i).getAuthorFirstName().equalsIgnoreCase(aAuthorFirstName)){
                   /**
                    * If the first name and last name already exist then don't
                    * add this author again, just get the authorId so it can
                    * be used to add it along w/ this book to the
                    * authorsISBN table.
                    */
                   TempAuthorIdHolder = results.get(i).getAuthorID();
                   newAuthor = false;
               }else if (newAuthor){
                   /**
                    * Else, either the first name, last name, or both didn't
                    * match so add this as a new author.
                    */
                   newAuthor = true;
               }
           }


           /**
            * The setString method will insert the given variable into the sql
            * statement that was defined in the constructor.
            */
           if (newAuthor){
               /**
                * Insert a new author into the authors table.
                */
               insertNewAuthor.setString(1, aAuthorFirstName);
               insertNewAuthor.setString(2, aAuthorLastName);
               result = insertNewAuthor.executeUpdate();
               /**
                * Below the .getGeneratedKey method is used to get the unique
                * Id assigned to the new author so that it can be added to the
                * authorsISBN table below.
                */
               tempResultSet = insertNewAuthor.getGeneratedKeys();
               tempResultSet.next();
               TempAuthorIdHolder = tempResultSet.getInt(1);
           }

           if (newBook){
               /**
                * If a new book then add it here
                */
               insertNewBook.setString(1, aISBN);
               insertNewBook.setString(2, aTitle);
               insertNewBook.setInt(3, aEditionNumber);
               insertNewBook.setString(4, aCopyright);
               result += insertNewBook.executeUpdate();
           }

           /**
            * Finally, regardless of whether just a author, just a book, or both
            * were added, a new entry with the authorId/ISBN combination
            * must be added to this table.
            */
           insertAuthorISBN.setInt(1, TempAuthorIdHolder);
           insertAuthorISBN.setString(2, aISBN);
           result += insertAuthorISBN.executeUpdate();


         
       }catch ( SQLException sqlException ){
           result = 0;
           lastError += sqlException.toString();
       }catch ( Exception exception){
           result = 0;
           lastError += exception.toString();
       }finally{
           return result;
       }
      
   }



   /** Updates a book/author in the database
   * @TheCs Cohesion -  Updates a book/author in the database.
   * Completeness - Completely updates a book/author in the database.
   * Convenience - Simply updates a book/author in the database.
   * Clarity - It is simple to understand that this updates a book/author in the
   *           database.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @exception SQLException for sql string being executed
   * @exception Exception general exception capture
   * @param aISBN ISBN of book to be updated
   * @param aTitle Title of book to be updated
   * @param aEditionNumber Edition Number of book to be updated
   * @param aCopyright copyright year of book to be updated
   * @param aFirstName first name of book author to be updated
   * @param aLastName last name of book author to be updated
   * @param aAuthorId unique author id needed for the update
   * @param aOldISBN the original ISBN needed for the update
   * @return result returns number of rows affected by sql statement
   */
   public int updateBook( String aISBN, String aTitle, int aEditionNumber,
           String aCopyright, String aFirstName, String aLastName,
           int aAuthorId, String aOldISBN  ){

       lastError = null; //Will hold any errors from method
       int result = 0;
       ResultSet tempResultSet = null;

       try{
           /**
            * This is another temp resultset that gets all of the entries from
            * authorISBN table. This is similar to the resultset seen in the
            * addBook method. I originally wanted to create a common method
            * since these are similar, however given the vast differences in
            * sql statements, values passed, values returned, I decided it would
            * actually be less coding to keep individual ones in each method.
            */
           tempResultSet = selectAllAuthorISBN.executeQuery();
           while ( tempResultSet.next() ){
               if (tempResultSet.getString("ISBN").equalsIgnoreCase(aOldISBN)){
                   /**
                    * This gets a listing of all the authors associated w/ the
                    * old ISBN for the purpose of updating them to be associated
                    * with the new ISBN. Each loop adds a new sql to the batch
                    * w/ the new author and new isbn. The batchs are added here
                    * but are not executed until the end of the method since
                    * the authors and titles tables must first be updated before
                    * the authorsISBN table is.
                    */
                   insertAuthorISBN.setInt(1, tempResultSet.getInt("authorID"));
                   insertAuthorISBN.setString(2, aISBN);
                   insertAuthorISBN.addBatch();
               }
           }

           /**
            * First delete all records from the authorISBN table for the book
            * in question
            */
           updateByDeleteAuthorISBN.setString(1,aOldISBN);
           result += updateByDeleteAuthorISBN.executeUpdate();

           /**
            * The only actual update. update the authors information
            */
           updateAuthor.setString(1, aFirstName);
           updateAuthor.setString(2, aLastName);
           updateAuthor.setInt(3, aAuthorId);
           result = updateAuthor.executeUpdate();

           /**
            * Delete the book from the titles table
            */
           deleteBook.setString( 1, aOldISBN );
           result += deleteBook.executeUpdate();

           /**
            * Add the new book to the titles table
            */
           insertNewBook.setString(1, aISBN);
           insertNewBook.setString(2, aTitle);
           insertNewBook.setInt(3, aEditionNumber);
           insertNewBook.setString(4, aCopyright);
           result += insertNewBook.executeUpdate();

           /**
            * This statement executes all of the sql statements added to the
            * batch before to insert author/isbn to the authorsISBN table.
            * The return value is an array of ints so I just use the length of
            * the array to indicate how many records were updated.
            */
           result += insertAuthorISBN.executeBatch().length;


        }catch ( SQLException sqlException ){
            result = 0;
            lastError += sqlException.toString();
        }catch ( Exception exception){
            result = 0;
            lastError += exception.toString();
      }finally{
          return result;
      }
   } 


   /** Deletes a book from the database
   * @TheCs Cohesion -  Deletes a book from the database.
   * Completeness - Completely deletes a book from the database.
   * Convenience - Simply deletes a book from the database.
   * Clarity - It is simple to understand that this deletes a book from the
   *           database.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @exception SQLException for sql string being executed
   * @exception Exception general exception capture
   * @param aAuthorId unique id of author of book to delete
   * @param aISBN ISBN of book to delete.
   * @return result returns number of rows affected by sql statement
   */
   public int deleteBook(int aAuthorId, String aISBN ){
       lastError = null; //Will hold any errors from method
       int result = 0;
       /**
        * These two booleans are used below to determine when deleting if the
        * book should be deleted completely if there are no more authors and
        * also if the author should be deleted if there are no more books for
        * the author.
        */
       boolean noMoreBooksForThisAuthor = true;
       boolean noMoreAuthorsForThisBook = true;
       ResultSet tempResultSet = null;


       try{
           /**
            * The setString method will insert the name variable into the sql
            * statement that was defined in the constructor.
            *
            * First the authorId/ISBN combo are removed from the authorISBN
            * table since this should happen no matter what the circumstance.
            */
           deleteAuthorISBN.setInt(1, aAuthorId);
           deleteAuthorISBN.setString(2, aISBN);
           result = deleteAuthorISBN.executeUpdate();

           /**
            * Again I needed to build a temp resultset on the authorISBN table
            * to determine if this book has any more authors and also if the 
            * author has any more books. If so then do not delete the book and/
            * or author, otherwise delete the author and/or book.
            */

           tempResultSet = selectAllAuthorISBN.executeQuery();


           while ( tempResultSet.next() ){
               if (tempResultSet.getString("ISBN").equalsIgnoreCase(aISBN)){
                   /**
                    * One or more authors still exist for this book, so don't
                    * delete it from the titles table.
                    */
                   noMoreAuthorsForThisBook = false;
               }else if (noMoreAuthorsForThisBook){
                   /**
                    * Else no more authors exists so delete the book from the
                    * titles table.
                    */
                   noMoreAuthorsForThisBook = true;
               }else{

               }
               if (tempResultSet.getInt("AuthorID") == aAuthorId){
                   /**
                    * One of more books still exist for this author so don't
                    * delete the author from the authors table.
                    */
                   noMoreBooksForThisAuthor = false;
               }else if (noMoreBooksForThisAuthor){
                   /**
                    * Else no more books exist for this author so delete the
                    * author from the authors table.
                    */
                   noMoreBooksForThisAuthor = true;
               }
           }



           if (noMoreBooksForThisAuthor){
               /**
                * Delete author from authors table
                */
               deleteAuthor.setInt(1, aAuthorId);
               result += deleteAuthor.executeUpdate();
           }
           if (noMoreAuthorsForThisBook){
               /**
                * Delete book from titles table
                */
               deleteBook.setString( 1, aISBN );
               result += deleteBook.executeUpdate();
           }

        }catch ( SQLException sqlException ){
            result = 0;
            lastError += sqlException.toString();
        }catch ( Exception exception){
            result = 0;
            lastError += exception.toString();
        }finally{
            return result;
        }
   }
   
   /**Closes the database connection
   * @TheCs Cohesion - Closes the database connection.
   * Completeness - Completely closes the database connection.
   * Convenience - Simply closes the database connection.
   * Clarity - It is simple to understand that this closes the database
   *           connection.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   *
   * @exception SQLException for sql string being executed
   * @exception Exception general exception capture
   */
   public void close(){
       lastError = null;//Will hold any errors from method
       try{
           connection.close();
       }catch ( SQLException sqlException ){
           lastError += sqlException.toString();
       }catch ( Exception exception){
           lastError += exception.toString();
       }
   }


/** Everything below, with the exception of the getLastError, is used for the
 *  TableModel needed by the JTable.
 */


   /** Returns data type for each column in JTable
   * @TheCs Cohesion - Returns data type for each column in JTable.
   * Completeness - Completely returns data type for each column in JTable.
   * Convenience - Simply returns data type for each column in JTable.
   * Clarity - It is simple to understand that this returns data type for each
   *           column in JTable.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @param column number of column to determine type
   * @throws IllegalStateException if not connected to database
   * @exception Exception general exception capture
   * @return the type used in the column
   */
    @Override
   public Class getColumnClass( int column ) throws IllegalStateException{
        lastError = null;//Will hold any errors from method
        // ensure database connection is available
        if ( !connectedToDatabase ){
            lastError += "Not Connected to Database";
            throw new IllegalStateException( "Not Connected to Database" );
        }
        // determine Java class of column
        try{
            String className = metaData.getColumnClassName( column + 1 );
            // return Class object that represents className
            return Class.forName( className );
        }catch ( Exception exception){
            lastError += exception.toString();
            return Object.class; // if problems occur above, assume type Object
        }
   }


   /** Returns number of columns for JTable
   * @TheCs Cohesion - Returns number of columns for JTable.
   * Completeness - Completely returns number of columns for JTable.
   * Convenience - Simply returns number of columns for JTable.
   * Clarity - It is simple to understand that this returns number of columns
   *           for JTable.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @param column number of column to determine type
   * @throws IllegalStateException if not connected to database
   * @exception SQLException for acquiring metaData
   * @return the number of columns used
   */
   public int getColumnCount() throws IllegalStateException{
       lastError = null; //Will hold any errors from method
       // ensure database connection is available
       if ( !connectedToDatabase ){
           lastError += "Not Connected to Database";
           throw new IllegalStateException( "Not Connected to Database" );
       }
      // determine number of columns
       try{
           return metaData.getColumnCount();
       }catch ( SQLException sqlException ){
           lastError += sqlException.toString();
           return 0; // if problems occur above, return 0 for number of columns
       }
   }

   /** Returns name of columns for JTable
   * @TheCs Cohesion - Returns name of columns for JTable.
   * Completeness - Completely returns name of columns for JTable.
   * Convenience - Simply returns name of columns for JTable.
   * Clarity - It is simple to understand that this returns name of columns
   *           for JTable.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @param column number of column to determine name
   * @throws IllegalStateException if not connected to database
   * @exception SQLException for acquiring metaData
   * @return the name of columns used
   */
    @Override
   public String getColumnName( int column ) throws IllegalStateException{
        lastError = null;//Will hold any errors from method
        // ensure database connection is available
        if ( !connectedToDatabase ){
            lastError += "Not Connected to Database";
            throw new IllegalStateException( "Not Connected to Database" );
        }
        // determine column name
        try{
            return metaData.getColumnName( column + 1 );
        }catch ( SQLException sqlException ){
            lastError += sqlException.toString();
            return ""; // if problems, return empty string for column name
        }
   }

   /** Returns number of rows for JTable
   * @TheCs Cohesion - Returns number of rows for JTable.
   * Completeness - Completely returns number of rows for JTable.
   * Convenience - Simply returns number of rows for JTable.
   * Clarity - It is simple to understand that this number of rows
   *           for JTable.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @throws IllegalStateException if not connected to database
   * @return the number of rows used
   */
   public int getRowCount() throws IllegalStateException{
       lastError = null;//Will hold any errors from method
       // ensure database connection is available
       if ( !connectedToDatabase ){
           lastError += "Not Connected to Database";
           throw new IllegalStateException( "Not Connected to Database" );
       }
       return numberOfRows;
   }

   /** Gets the value of a particular row and column
   * @TheCs Cohesion - Gets the value of a particular row and column.
   * Completeness - Completely gets the value of a particular row and column.
   * Convenience - Simply gets the value of a particular row and column.
   * Clarity - It is simple to understand that this gets the value of a
   *           particular row and column.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @param row the row of the value needed
   * @param column the column of the value needed
   * @throws  IllegalStateException if not connected to database
   * @exception SQLException for acquiring data from resultSet
   * @return value from row and column specified
   */
   public Object getValueAt( int row, int column ) throws IllegalStateException{
       lastError = null;//Will hold any errors from method
       // ensure database connection is available
       if ( !connectedToDatabase ){
           lastError += "Not Connected to Database";
           throw new IllegalStateException( "Not Connected to Database" );
       }
       // obtain a value at specified ResultSet row and column
       try{
           resultSet.absolute( row + 1 );
           return resultSet.getObject( column + 1 );
       } catch ( SQLException sqlException ){
           lastError += sqlException.toString();
           return ""; // if problems, return empty string object
       }
   }

   /** Runs a new query on the database
   * @TheCs Cohesion - Runs a new query on the database.
   * Completeness - Completely runs a new query on the database.
   * Convenience - Simply runs a new query on the database.
   * Clarity - It is simple to understand that this runs a new query on the
   *           database.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @param sql string to execute
   * @throws  IllegalStateException if not connected to database
   */
   private void setQuery( String query )
           throws SQLException, IllegalStateException{
       lastError = null;//Will hold any errors from method
       // ensure database connection is available
       if ( !connectedToDatabase ){
           lastError += "Not Connected to Database";
           throw new IllegalStateException( "Not Connected to Database" );
       }
       // specify query and execute it
       resultSet = statement.executeQuery( query );
       // obtain meta data for ResultSet
       metaData = resultSet.getMetaData();
       // determine number of rows in ResultSet
       resultSet.last();                   // move to last row
       numberOfRows = resultSet.getRow();  // get row number
       // notify JTable that model has changed
       fireTableStructureChanged();
   }

   /** Closes resultSet, statement, and connection
   * @TheCs Cohesion - Closes resultSet, statement, and connection.
   * Completeness - Completely closes resultSet, statement, and connection.
   * Convenience - Simply closes resultSet, statement, and connection.
   * Clarity - It is simple to understand that this closes resultSet,
   *           statement, and connection.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @exception SQLException for closing resultSet, statement, and connection
   */
   public void disconnectFromDatabase(){
       lastError = null;//Will hold any errors from method
       if ( connectedToDatabase ){
           // close Statement and Connection
           try{
               resultSet.close();
               statement.close();
               connection.close();
           }catch ( SQLException sqlException ){
               lastError += sqlException.toString();
           }finally{
               connectedToDatabase = false;// update database connection status
           }
       }
   }

   /** Returns Error messages from BooksQuery methods
   * @TheCs Cohesion -  Returns Error messages from BooksQuery methods.
   * Completeness - Completely returns Error messages from BooksQuery
   *                methods.
   * Convenience - Simply returns Error messages from BooksQuery methods.
   * Clarity - It is simple to understand that this returns Error messages
   *           from BooksQuery methods.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @return lastError holds error messages from methods of BooksQuery
   */
   public String getLastError(){
       return lastError;
   }
}




 