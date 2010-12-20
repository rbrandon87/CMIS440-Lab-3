package russell_cmis440_lab3;

/**
* Program Name: CMIS440 Lab 3 JavaDB Address Book Program
* @author Brandon R Russell
* @Course CMIS440
* Date: Dec 15,2010
*/

/** This class is used to perform various queries on the AddressBook database
* Also, this class extends the AbstractTableModel in order for a JTable to be
* modeled after this class so that it can display its data.
*|----------------------------------------------------------------------------|
*|                           CRC: BooksQuery                                  |
*|----------------------------------------------------------------------------|
*|Used to query database to insert/update/delete entries   AddressBookDisplay |
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
   private PreparedStatement selectAllBooks = null;
   private PreparedStatement selectAllAuthors = null;
   private PreparedStatement selectBooksByAuthorLastName = null;

   private PreparedStatement insertNewBook = null;
   private PreparedStatement insertNewAuthor = null;
   private PreparedStatement insertAuthorISBN = null;

   private PreparedStatement deleteBook = null;
   private PreparedStatement deleteAuthor = null;
   private PreparedStatement deleteAuthorISBN = null;
  
   private PreparedStatement updateBook = null;
   private PreparedStatement updateAuthor = null;
   private PreparedStatement updateAuthorISBN = null;

   private String selectAllAuthorsSQL = "";
   private String selectAllBooksSQL = "";
   private String selectBooksByAuthorLastNameSQL = "";

   private String insertNewAuthorSQL = "";
   private String insertIntoAuthorISBNSQL = "";
   private String insertNewBookSQL = "";

   private String deleteBookSQL = "";
   private String deleteAuthorSQL = "";
   private String deleteFromAuthorISBNSQL = "";

   private String updateBookSQL = "";
   private String updateAuthorSQL = "";
   private String updateAuthorISBNSQL = "";
   private int numberOfRows;
   private Statement statement;
   private ResultSetMetaData metaData;
   private ResultSet resultSet;

   // keep track of database connection status
   private boolean connectedToDatabase = false;
   private String lastError = null;
   
   /** Constructs a PersonQueries object. Sets up SQl statements.
   * @TheCs Cohesion - Constructs a PersonQueries object. Sets up SQl
   *                   statements.
   * Completeness - Completely constructs a PersonQueries object. Sets up SQl
   *                statements.
   * Convenience - Simply constructs a PersonQueries object. Sets up SQl
   *               statements.
   * Clarity - It is simple to understand that this constructs a PersonQueries
   *           object. Sets up SQl statements.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @exception SQLException if any problems w/ SQL statement preparation
   * @exception Exception  general exception capture
   */
   public BooksQuery(){
       try{
           /**
            * SELECT * FROM authors
            * INNER JOIN authorISBN on authors.authorID=authorISBN.authorID
            * INNER JOIN Titles on AuthorISBN.isbn=Titles.isbn
            */
           lastError = null; //Will hold any errors from method
           //*****Begin SQL Definitions******
           selectAllAuthorsSQL = "select * from authors";
           selectAllBooksSQL = "SELECT * FROM deitel.authors "
            + "LEFT OUTER JOIN authorISBN on authors.authorID=authorISBN.authorID "
            + "RIGHT OUTER JOIN Titles on AuthorISBN.isbn=Titles.isbn";
           //selectAllBooksSQL = "SELECT * FROM authors";
           selectBooksByAuthorLastNameSQL = "SELECT * FROM authors "
            + "INNER JOIN authorISBN on authors.authorID=authorISBN.authorID "
            + "INNER JOIN Titles on AuthorISBN.isbn=Titles.isbn "
            + "WHERE authors.LastName = ?";

           insertNewBookSQL = "INSERT INTO Titles ( ISBN, Title, EditionNumber, Copyright) VALUES ( ?, ?, ?, ? )";
           insertIntoAuthorISBNSQL = "INSERT INTO authorISBN ( AuthorID, ISBN ) VALUES ( ?, ? )";
           insertNewAuthorSQL = "INSERT INTO authors ( FirstName, LastName) VALUES ( ?, ? )";

           deleteBookSQL = "DELETE FROM Titles WHERE ISBN = ?";
           deleteFromAuthorISBNSQL = "DELETE FROM authorISBN WHERE AuthorID = ? AND ISBN = ?";
           deleteAuthorSQL = "DELETE FROM authors WHERE AuthorID = ?";
           
           updateBookSQL = "UPDATE Titles SET ISBN=?, Title=?, EditionNumber=?, Copyright=? WHERE ISBN=?";
           updateAuthorISBNSQL = "UPDATE authorISBN SET AuthorID=?, ISBN=? WHERE AuthorID=? AND ISBN=?";
           updateAuthorSQL = "UPDATE authors SET FirstName=?, LastName=? WHERE AuthorID=?";
           //*****End SQL Definitions******
           connection = DriverManager.getConnection( URL, USERNAME, PASSWORD );
           selectAllAuthors = connection.prepareStatement(selectAllAuthorsSQL);
           // create query that selects all entries in the AddressBook
           selectAllBooks = connection.prepareStatement( selectAllBooksSQL );
           // create query that selects entries with a specific last name
           selectBooksByAuthorLastName = connection.prepareStatement(
                   selectBooksByAuthorLastNameSQL );
           // create insert that adds a new entry into the database
           insertNewBook = connection.prepareStatement(insertNewBookSQL );
           insertAuthorISBN = connection.prepareStatement(insertIntoAuthorISBNSQL );
           insertNewAuthor = connection.prepareStatement(insertNewAuthorSQL );
           // create update that updates a entry in the database
           updateBook = connection.prepareStatement(updateBookSQL);
           updateAuthorISBN = connection.prepareStatement(updateAuthorISBNSQL);
           updateAuthor = connection.prepareStatement(updateAuthorSQL);
           // create delete that deletes an entry from the database
           deleteBook = connection.prepareStatement(deleteBookSQL);
           deleteAuthorISBN = connection.prepareStatement(deleteFromAuthorISBNSQL);
           deleteAuthor = connection.prepareStatement(deleteAuthorSQL);
           // create Statement to query database
           statement = connection.createStatement(
                   ResultSet.TYPE_SCROLL_INSENSITIVE,
                   ResultSet.CONCUR_READ_ONLY );

           // update database connection status
           connectedToDatabase = true;

           // set query and execute it
           setQuery( selectAllBooksSQL );
       }
       catch ( SQLException sqlException ){
           lastError += sqlException.toString();
           sqlException.printStackTrace();
       }catch ( Exception exception){
          lastError += exception.toString();
      }
   } 
   
   /** Returns a list of persons currently in database
   * @TheCs Cohesion - Returns a list of persons currently in database
   * Completeness - Completely returns a list of persons currently in database.
   * Convenience - Simply returns a list of persons currently in database.
   * Clarity - It is simple to understand that this returns a list of persons
   *           currently in database.
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
            * JTable data up-to-date. Next the selectAllPeople SQL is executed
            * and places into myResultSet all persons from the database.
            */
           setQuery( selectAllBooksSQL );
           myResultSet = selectAllBooks.executeQuery();
           results = new ArrayList< Book >();
           /**
            * Loops through the Resultset and creates a new Person object for
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

   
   /** Return entries with given past name
   * @TheCs Cohesion - Return entries with given past name.
   * Completeness - Completely returns entries with given past name.
   * Convenience - Simply returns entries with given past name.
   * Clarity - It is simple to understand that this returns entries with given
   *           past name.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @exception SQLException if problems with executing SQL statements
   * @exception Exception general exception capture
   * @return results List of persons in database w/ given last name
   * @param name last name to find in entries
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
           selectBooksByAuthorLastName.setString( 1, name ); // specify last name
           /**
            * Interestingly enough you cannot get a string from a
            * PreparedStatement that represents the SQL statement so I had to
            * use my string definition from before and replace the ? w/ name
            * manually below.
            */
           setQuery(selectBooksByAuthorLastNameSQL.replace("?", "'" +name + "'"));
           // executeQuery returns ResultSet containing matching entries
           myResultSet = selectBooksByAuthorLastName.executeQuery();

           results = new ArrayList< Book >();
           /**
            * Loops through the Resultset and creates a new Person object for
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
   
   /** Adds a person to the database
   * @TheCs Cohesion -  Adds a person to the database.
   * Completeness - Completely  adds a person to the database.
   * Convenience - Simply adds a person to the database.
   * Clarity - It is simple to understand that this adds a person to the
   *           database.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @exception SQLException for sql string being executed
   * @exception Exception general exception capture
   * @param fName first name of person to add
   * @param lName last name of person to add
   * @param email email address of person to add
   * @param num  Phone number of person to add
   * @return result returns number of rows affected by sql statement
   */
   public int addBook(String aISBN, String aTitle, String aEditionNumber, String aCopyright, String aAuthorFirstName, String aAuthorLastName, boolean aNewAuthor ){
       ResultSet tempResultSet = null;
       lastError = null;//Will hold any errors from method
       List< Book > results = null;
       int result = 0;
       int TempAuthorIdHolder = 0;
       int i = 0;
       boolean checkInsert = false;
       // set parameters, then execute insertNewPerson

       try{
           /**
            * The setString method will insert the given variable into the sql
            * statement that was defined in the constructor.
            */     
           //insertNewBookSQL = "INSERT INTO Titles ( ISBN, Title, EditionNumber, Copyright) VALUES ( ?, ?, ?, ? )";
           //insertIntoAuthorISBNSQL = "INSERT INTO authorISBN ( AuthorID, ISBN ) VALUES ( ?, ? )";
           //insertNewAuthorSQL = "INSERT INTO authors ( FirstName, LastName) VALUES ( ?, ? )";
           if (aNewAuthor){
               insertNewAuthor.setString(1, aAuthorFirstName);
               insertNewAuthor.setString(2, aAuthorLastName);
               result = insertNewAuthor.executeUpdate();
               setQuery(selectAllAuthorsSQL);
               tempResultSet = selectAllAuthors.executeQuery();
           }
           while ( tempResultSet.next() ){
               if (tempResultSet.getString("firstName").equalsIgnoreCase(aAuthorFirstName) && tempResultSet.getString("lastName").equalsIgnoreCase(aAuthorLastName)){
                   TempAuthorIdHolder = tempResultSet.getInt("authorID");
                   break;
               }
               i++;
           }


           insertNewBook.setString(1, aISBN);
           insertNewBook.setString(2, aTitle);
           insertNewBook.setString(3, aEditionNumber);
           insertNewBook.setString(4, aCopyright);
           result += insertNewBook.executeUpdate();

           insertAuthorISBN.setInt(1, TempAuthorIdHolder);
           insertAuthorISBN.setString(2, aISBN);
           result += insertAuthorISBN.executeUpdate();

         
       }catch ( SQLException sqlException ){
           lastError += sqlException.toString();
       }catch ( Exception exception){
           lastError += exception.toString();
       }finally{
           return result;
       }
      
   }



   /** Updates a person in the database
   * @TheCs Cohesion -  Updates a person in the database.
   * Completeness - Completely updates a person in the database.
   * Convenience - Simply updates a person in the database.
   * Clarity - It is simple to understand that this updates a person in the
   *           database.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @exception SQLException for sql string being executed
   * @exception Exception general exception capture
   * @param addressID unique ID of person to update
   * @param fName first name of person to update
   * @param lName last name of person to update
   * @param email email address of person to update
   * @param num  Phone number of person to update
   * @return result returns number of rows affected by sql statement
   */
   public int updateBook( String aISBN, String aTitle, String aEditionNumber, String aCopyright, String aFirstName, String aLastName, int aAuthorId  ){
       lastError = null; //Will hold any errors from method
        int result = 0;
        String TempOldISBNHolder = "";
        List< Book > results = null;
        // set parameters, then execute insertNewPerson
        try{
           results = getAllBooks();
           for (int i = 0; i < results.size(); i++){
               if (results.get(i).getAuthorID() == aAuthorId){
                   TempOldISBNHolder = results.get(i).getISBN();
                   break;
               }
           }
            /**
             * The setString method will insert the name variable into the sql
             * statement that was defined in the constructor.
             */
           //updateBookSQL = "UPDATE Titles SET ISBN=?, Title=?, EditionNumber=?, Copyright=? WHERE ISBN=?";
           //updateAuthorISBNSQL = "UPDATE authorISBN SET AuthorID=?, ISBN=? WHERE AuthorID=? AND ISBN=?";
           //updateAuthorSQL = "UPDATE authors SET FirstName=?, LastName=? WHERE AuthorID=?";

            updateAuthor.setString(1, aFirstName);
            updateAuthor.setString(2, aLastName);
            updateAuthor.setInt(3, aAuthorId);
            result = updateAuthor.executeUpdate();

            updateBook.setString(1, aISBN);
            updateBook.setString(2, aTitle);
            updateBook.setString(3, aEditionNumber);
            updateBook.setString(4, aCopyright);
            updateBook.setString(5, TempOldISBNHolder);
            result += updateBook.executeUpdate();

            updateAuthorISBN.setInt(1, aAuthorId);
            updateAuthorISBN.setString(2, aISBN);
            updateAuthorISBN.setInt(3, aAuthorId);
            updateAuthorISBN.setString(4, TempOldISBNHolder);
            result += updateAuthorISBN.executeUpdate();
            
        }catch ( SQLException sqlException ){
            lastError += sqlException.toString();
        }catch ( Exception exception){
            lastError += exception.toString();
      }finally{
          return result;
      }
   } 


   /** Deletes a person from the database
   * @TheCs Cohesion -  Deletes a person from the database.
   * Completeness - Completely deletes a person from the database.
   * Convenience - Simply deletes a person from the database.
   * Clarity - It is simple to understand that this deletes a person from the
   *           database.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @exception SQLException for sql string being executed
   * @exception Exception general exception capture
   * @param addressID unique ID of person to delete
   * @return result returns number of rows affected by sql statement
   */
   public int deleteBook(int aAuthorId, String aISBN ){
       lastError = null; //Will hold any errors from method
        int result = 0;
        List< Book > results = null;
        boolean noMoreBooksForThisAuthor = false;
        // set parameters, then execute insertNewPerson
        try{
            /**
             * The setString method will insert the name variable into the sql
             * statement that was defined in the constructor.
             */
           //deleteBookSQL = "DELETE FROM Titles WHERE ISBN = ?";
           //deleteFromAuthorISBNSQL = "DELETE FROM authorISBN WHERE AuthorID = ? AND ISBN = ?";
           //deleteAuthorSQL = "DELETE FROM authors WHERE AuthorID = ?";

            deleteBook.setString( 1, aISBN );
            // delete the entry; returns # of rows updated
            result = deleteBook.executeUpdate();

            deleteAuthorISBN.setInt(1, aAuthorId);
            deleteAuthorISBN.setString(2, aISBN);
            result += deleteAuthorISBN.executeUpdate();

           results = getAllBooks();
           for (int i = 0; i < results.size(); i++){
               if (results.get(i).getAuthorID() == aAuthorId){
                   if(results.get(i).getISBN() == null || results.get(i).getISBN().equals("")){
                       noMoreBooksForThisAuthor = true;
                   }else{
                       noMoreBooksForThisAuthor = false;
                   }

               }
           }

           if (noMoreBooksForThisAuthor){
               deleteAuthor.setInt(1, aAuthorId);
               result += deleteAuthor.executeUpdate();
           }

        }catch ( SQLException sqlException ){
            lastError += sqlException.toString();
        }catch ( Exception exception){
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

   /** Returns Error messages from PersonQueries methods
   * @TheCs Cohesion -  Returns Error messages from PersonQueries methods.
   * Completeness - Completely returns Error messages from PersonQueries
   *                methods.
   * Convenience - Simply returns Error messages from PersonQueries methods.
   * Clarity - It is simple to understand that this returns Error messages
   *           from PersonQueries methods.
   * Consistency - It uses the same syntax rules as the rest of the class and
   *               continues to use proper casing and indentation.
   * @return lastError holds error messages from methods of personQueries
   */
   public String getLastError(){
       return lastError;
   }
}




 