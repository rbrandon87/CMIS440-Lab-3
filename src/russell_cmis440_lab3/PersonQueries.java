package russell_cmis440_lab3;

// Fig. 28.31: PersonQueries.java
// PreparedStatements used by the Address Book application
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

public class PersonQueries extends AbstractTableModel
{
   private static final String URL = "jdbc:derby:AddressBook";
   private static final String USERNAME = "deitel";
   private static final String PASSWORD = "deitel";

   private Connection connection = null; // manages connection
   private PreparedStatement selectAllPeople = null; 
   private PreparedStatement selectPeopleByLastName = null; 
   private PreparedStatement insertNewPerson = null;
   private PreparedStatement deletePerson = null;
   private PreparedStatement updatePerson = null;
   private int numberOfRows;
   private Statement statement;
   private ResultSetMetaData metaData;
   private ResultSet resultSet;

   // keep track of database connection status
   private boolean connectedToDatabase = false;
   
   // constructor
   public PersonQueries()
   {
      try 
      {
         connection = 
            DriverManager.getConnection( URL, USERNAME, PASSWORD );

         // create query that selects all entries in the AddressBook
         selectAllPeople = 
            connection.prepareStatement( "SELECT * FROM Addresses" );
         
         // create query that selects entries with a specific last name
         selectPeopleByLastName = connection.prepareStatement( 
            "SELECT * FROM Addresses WHERE LastName = ?" );
         
         // create insert that adds a new entry into the database
         insertNewPerson = connection.prepareStatement( 
            "INSERT INTO Addresses " + 
            "( FirstName, LastName, Email, PhoneNumber ) " + 
            "VALUES ( ?, ?, ?, ? )" );

         // create insert that adds a new entry into the database
         updatePerson = connection.prepareStatement(
            "UPDATE Addresses SET FirstName=?, LastName=?, Email=?,"
            + " PhoneNumber=? WHERE AddressID=?");

         deletePerson = connection.prepareStatement(
            "DELETE FROM Addresses WHERE AddressID = ?");

      // create Statement to query database
      statement = connection.createStatement(
         ResultSet.TYPE_SCROLL_INSENSITIVE,
         ResultSet.CONCUR_READ_ONLY );

      // update database connection status
      connectedToDatabase = true;

      // set query and execute it
      setQuery( "SELECT * FROM Addresses" );
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
         System.exit( 1 );
      } // end catch
   } // end PersonQueries constructor
   
   // select all of the addresses in the database
   public List< Person > getAllPeople()
   {
      List< Person > results = null;
      ResultSet myResultSet = null;
      
      try 
      {
         // executeQuery returns ResultSet containing matching entries
         setQuery( "SELECT * FROM Addresses" );
         myResultSet = selectAllPeople.executeQuery();
         results = new ArrayList< Person >();
         
         while ( myResultSet.next() )
         {
            results.add( new Person(
               myResultSet.getInt( "addressID" ),
               myResultSet.getString( "firstName" ),
               myResultSet.getString( "lastName" ),
               myResultSet.getString( "email" ),
               myResultSet.getString( "phoneNumber" ) ) );
         } // end while
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();         
      } // end catch
      finally
      {
         try 
         {
            myResultSet.close();
         } // end try
         catch ( SQLException sqlException )
         {
            sqlException.printStackTrace();         
            close();
         } // end catch
      } // end finally
      
      return results;
   } // end method getAllPeople

   
   // select person by last name   
   public List< Person > getPeopleByLastName( String name )
   {
      List< Person > results = null;
      ResultSet resultSet = null;

      try 
      {
         selectPeopleByLastName.setString( 1, name ); // specify last name
         setQuery("SELECT * FROM Addresses WHERE LastName = '" + name + "'");
         // executeQuery returns ResultSet containing matching entries
         resultSet = selectPeopleByLastName.executeQuery(); 

         results = new ArrayList< Person >();

         while ( resultSet.next() )
         {
            results.add( new Person( resultSet.getInt( "addressID"),
               resultSet.getString( "firstName" ),
               resultSet.getString( "lastName" ),
               resultSet.getString( "email" ),
               resultSet.getString( "phoneNumber" ) ) );
         } // end while
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
      } // end catch
      finally
      {
         try 
         {
            resultSet.close();
         } // end try
         catch ( SQLException sqlException )
         {
            sqlException.printStackTrace();         
            close();
         } // end catch
      } // end finally
      
      return results;
   } // end method getPeopleByName
   
   // add an entry
   public int addPerson( 
      String fname, String lname, String email, String num )
   {
      int result = 0;
      
      // set parameters, then execute insertNewPerson
      try 
      {
         insertNewPerson.setString( 1, fname );
         insertNewPerson.setString( 2, lname );
         insertNewPerson.setString( 3, email );
         insertNewPerson.setString( 4, num );

         // insert the new entry; returns # of rows updated
         result = insertNewPerson.executeUpdate();
         
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
         close();
      } // end catch
      
      return result;
   } // end method addPerson



   // add an entry
   public int updatePerson(
      String addressID, String fname, String lname, String email, String num )
   {
      int result = 0;

      // set parameters, then execute insertNewPerson
      try
      {
         
         updatePerson.setString( 1, fname );
         updatePerson.setString( 2, lname );
         updatePerson.setString( 3, email );
         updatePerson.setString( 4, num );
         updatePerson.setString( 5, addressID);

         // insert the new entry; returns # of rows updated
         result = updatePerson.executeUpdate();

      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
         close();
      } // end catch

      return result;
   } // end method addPerson



   public int deletePerson(String addressID )
   {
      int result = 0;

      // set parameters, then execute insertNewPerson
      try
      {
         deletePerson.setString( 1, addressID );

         // insert the new entry; returns # of rows updated
         result = deletePerson.executeUpdate();
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
         close();
      } // end catch

      return result;
   } // end method addPerson
   
   // close the database connection
   public void close()
   {
      try 
      {
         connection.close();
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
      } // end catch
   } // end method close


//*****************************************************************************

   // get class that represents column type
    @Override
   public Class getColumnClass( int column ) throws IllegalStateException
   {
      // ensure database connection is available
      if ( !connectedToDatabase )
         throw new IllegalStateException( "Not Connected to Database" );

      // determine Java class of column
      try
      {
         String className = metaData.getColumnClassName( column + 1 );

         // return Class object that represents className
         return Class.forName( className );
      } // end try
      catch ( Exception exception )
      {
         exception.printStackTrace();
      } // end catch

      return Object.class; // if problems occur above, assume type Object
   } // end method getColumnClass

   // get number of columns in ResultSet
   public int getColumnCount() throws IllegalStateException
   {
      // ensure database connection is available
      if ( !connectedToDatabase )
         throw new IllegalStateException( "Not Connected to Database" );

      // determine number of columns
      try
      {
         return metaData.getColumnCount();
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
      } // end catch

      return 0; // if problems occur above, return 0 for number of columns
   } // end method getColumnCount

   // get name of a particular column in ResultSet
    @Override
   public String getColumnName( int column ) throws IllegalStateException
   {
      // ensure database connection is available
      if ( !connectedToDatabase )
         throw new IllegalStateException( "Not Connected to Database" );

      // determine column name
      try
      {
         return metaData.getColumnName( column + 1 );
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
      } // end catch

      return ""; // if problems, return empty string for column name
   } // end method getColumnName

   // return number of rows in ResultSet
   public int getRowCount() throws IllegalStateException
   {
      // ensure database connection is available
      if ( !connectedToDatabase )
         throw new IllegalStateException( "Not Connected to Database" );

      return numberOfRows;
   } // end method getRowCount

   // obtain value in particular row and column
   public Object getValueAt( int row, int column )
      throws IllegalStateException
   {
      // ensure database connection is available
      if ( !connectedToDatabase )
         throw new IllegalStateException( "Not Connected to Database" );

      // obtain a value at specified ResultSet row and column
      try
      {
         resultSet.absolute( row + 1 );
         return resultSet.getObject( column + 1 );
      } // end try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
      } // end catch

      return ""; // if problems, return empty string object
   } // end method getValueAt

   // set new database query string
   public void setQuery( String query )
      throws SQLException, IllegalStateException
   {
      // ensure database connection is available
      if ( !connectedToDatabase )
         throw new IllegalStateException( "Not Connected to Database" );

      // specify query and execute it
      resultSet = statement.executeQuery( query );

      // obtain meta data for ResultSet
      metaData = resultSet.getMetaData();

      // determine number of rows in ResultSet
      resultSet.last();                   // move to last row
      numberOfRows = resultSet.getRow();  // get row number

      // notify JTable that model has changed
      fireTableStructureChanged();

   } // end method setQuery

   // close Statement and Connection
   public void disconnectFromDatabase()
   {
      if ( connectedToDatabase )
      {
         // close Statement and Connection
         try
         {
            resultSet.close();
            statement.close();
            connection.close();
         } // end try
         catch ( SQLException sqlException )
         {
            sqlException.printStackTrace();
         } // end catch
         finally  // update database connection status
         {
            connectedToDatabase = false;
         } // end finally
      } // end if
   } // end method disconnectFromDatabase

   public void getLastError(){
       
   }

} // end class PersonQueries




 