// This example is from _Java Examples in a Nutshell_. (http://www.oreilly.com)
// Copyright (c) 1997 by David Flanagan
// This example is provided WITHOUT ANY WARRANTY either expressed or implied.
// You may study, use, modify, and distribute it for non-commercial purposes.
// For any commercial use, see http://www.davidflanagan.com/javaexamples

import java.sql.*;
import java.io.*;

/**
 * A general-purpose SQL interpreter program.
 **/
public class ExecuteSQL {
  public static void main(String[] args) {
    Connection conn = null;  // Our JDBC connection to the database server
    try {
      String driver = null, url = null, user = "", password = "";

      // Parse all the command-line arguments
      for(int n = 0; n < args.length; n++) {
        if (args[n].equals("-d")) driver = args[++n];
        else if (args[n].equals("-u")) user = args[++n];
        else if (args[n].equals("-p")) password = args[++n];
        else if (url == null) url = args[n];
        else throw new IllegalArgumentException("Unknown argument.");
      }

      // The only required argument is the database URL.
      if (url == null) 
        throw new IllegalArgumentException("No database specified");

      // If the user specified the classname for the DB driver, load
      // that class dynamically.  This gives the driver the opportunity
      // to register itself with the DriverManager.
      if (driver != null) Class.forName(driver);

      // Now open a connection the specified database, using the user-specified
      // username and password, if any.  The driver manager will try all of
      // the DB drivers it knows about to try to parse the URL and connect to
      // the DB server.
      conn = DriverManager.getConnection(url, user, password);

      // Now create the statement object we'll use to talk to the DB
      Statement s = conn.createStatement();

      // Get a stream to read from the console
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

      // Loop forever, reading the user's queries and executing them
      while(true) {
        System.out.print("sql> ");   // prompt the user
        System.out.flush();          // make the prompt appear immediately
        String sql = in.readLine();  // get a line of input from the user

        // Quit when the user types "quit".
        if ((sql == null) || sql.equals("quit")) break;

        // Ignore blank lines
        if (sql.length() == 0) continue;
        
        // Now, attempt to execute the user's line of SQL and display results.
        try {
          // We don't know if this is a query or some kind of update, so we
          // use execute() instead of executeQuery() or executeUpdate()
          // If the return value is true, it was a query, else an update
          boolean status = s.execute(sql);
        
          // Loop until there are no more results to return
          do {
            if (status) { // statement was a query that returns a ResultSet
              // Get the set of results and display them
              ResultSet rs = s.getResultSet();
              printResultsTable(rs, System.out);
            }
            else {
              // If the SQL command that was executed was some kind of update
              // rather than a query, then it doesn't return a ResultSet.
              // Instead, we just print the number of rows that were affected.
              int numUpdates = s.getUpdateCount();
              System.out.println("Ok. " + numUpdates + " rows affected.");
            }

            // Now go see if there are even more results, and
            // continue the results display loop if there are.
            status = s.getMoreResults();
          // With some buggy JDBC drivers, this condition causes an infinite
          // loop with SQL updates. If that happens, change to: while(status); 
          } while(status || s.getUpdateCount() != -1);
        }
        // If a SQLException is thrown, display an error message.  Note that
        // SQLExceptions can have a general message and a DB-specific message
        // returned by getSQLState()
        catch (SQLException e) {
          System.err.println("SQLException: " + e.getMessage() + ":" +
                             e.getSQLState());
        }
        // Each time through this loop, check to see if any warnings were
        // issued.  Note that there can be a whole chain of warnings.
        finally { // print out any warnings that occurred
          for(SQLWarning w=conn.getWarnings(); w != null; w=w.getNextWarning())
            System.err.println("WARNING: " + w.getMessage() + 
                               ":" + w.getSQLState());
        }
      }
    }
    // Handle exceptions that occur during argument parsing, database
    // connection setup, etc.  For SQLExceptions, print the details.
    catch (Exception e) {
      System.err.println(e);
      if (e instanceof SQLException)
        System.err.println("SQL State: " + ((SQLException)e).getSQLState());
      System.err.println("Usage: java ExecuteSQL [-d <driver>] [-u <user>] " +
                         "[-p <password>] <database URL>");
    }
    // Be sure to always close the database connection when we exit, whether
    // we exit because the user types 'quit' or because of an exception thrown
    // while setting things up.  Closing this connection also implicitly
    // closes any open statements and result sets associated with it.
    finally {
      try { conn.close(); } catch (Exception e) {}
    }
  }

  /**
   * This method attempts to output the contents of a ResultSet in a 
   * textual table.  It relies on the ResultSetMetaData class, but a fair
   * bit of the code is simple string manipulation.
   **/
  static void printResultsTable(ResultSet rs, OutputStream output) 
       throws SQLException {
    // Set up the output stream
    PrintWriter out = new PrintWriter(new OutputStreamWriter(output));

    // Get some "meta data" (column names, etc.) about the results
    ResultSetMetaData metadata = rs.getMetaData();
    
    // Variables to hold important data about the table to be displayed
    int numcols = metadata.getColumnCount();// how many columns
    String[] labels = new String[numcols];  // the column labels
    int[] colwidths = new int[numcols];     // the width of each
    int[] colpos = new int[numcols];        // start position of each
    int linewidth;                          // total width of table
    
    // Figure out how wide the columns are, where each one begins, 
    // how wide each row of the table will be, etc.
    linewidth = 1; // for the initial '|'.
    for(int i = 0; i < numcols; i++) {          // for each column
      colpos[i] = linewidth;                    // save its position
      labels[i] = metadata.getColumnLabel(i+1); // get its label 
      // Get the column width.  If the db doesn't report one, guess
      // 30 characters.  Then check the length of the label, and use
      // it if it is larger than the column width
      int size =  metadata.getColumnDisplaySize(i+1);
      if (size == -1) size = 30;                // some drivers return -1...
      int labelsize = labels[i].length();
      if (labelsize > size) size = labelsize;   
      colwidths[i] = size + 1;                  // save the column the size  
      linewidth += colwidths[i] + 2;            // increment total size
    }
    
    // Create a horizontal divider line we use in the table.
    // Also create a blank line that is the initial value of each 
    // line of the table
    StringBuffer divider = new StringBuffer(linewidth);
    StringBuffer blankline = new StringBuffer(linewidth);
    for(int i = 0; i < linewidth; i++) { 
      divider.insert(i, '-');
      blankline.insert(i, " ");
    }
    // Put special marks in the divider line at the column positions
    for(int i=0; i<numcols; i++) divider.setCharAt(colpos[i]-1,'+');
    divider.setCharAt(linewidth-1, '+');
    
    // Begin the table output with a divider line
    out.println(divider);
    
    // The next line of the table contains the column labels.
    // Begin with a blank line, and put the column names and column
    // divider characters "|" into it.  overwrite() is defined below.
    StringBuffer line = new StringBuffer(blankline.toString());
    line.setCharAt(0, '|');
    for(int i = 0; i < numcols; i++) {
      int pos = colpos[i] + 1 + (colwidths[i]-labels[i].length())/2;
      overwrite(line, pos, labels[i]);
      overwrite(line, colpos[i] + colwidths[i], " |");
    }
    
    // Then output the line of column labels and another divider
    out.println(line);
    out.println(divider);
    
    // Now, output the table data.  Loop through the ResultSet, using
    // the next() method to get the rows one at a time. Obtain the 
    // value of each column with getObject(), and output it, much as 
    // we did for the column labels above.
    while(rs.next()) {
      line = new StringBuffer(blankline.toString());
      line.setCharAt(0, '|');
      for(int i = 0; i < numcols; i++) {
        Object value = rs.getObject(i+1);
        overwrite(line, colpos[i] + 1, value.toString().trim());
        overwrite(line, colpos[i] + colwidths[i], " |");
      }
      out.println(line);
    }
    
    // Finally, end the table with one last divider line.
    out.println(divider);
    out.flush();
  }

  /** This utility method is used when printing the table of results */
  static void overwrite(StringBuffer b, int pos, String s) {
    int len = s.length();
    for(int i = 0; i < len; i++) b.setCharAt(pos+i, s.charAt(i));
  }
}
