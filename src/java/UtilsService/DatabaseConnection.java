package UtilsService;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 * Connection to the database using Singleton implementation
 */
public class DatabaseConnection {

    private static class DBConnectionHolder {

        private static final DatabaseConnection INSTANCE = new DatabaseConnection();
    }

    private Connection conn;

    private DatabaseConnection() {
        initConnectionToDb();
    }

    /**
     * Method that returns the singleton instance of the class.
     */
    public static DatabaseConnection getInstance() {

        //the return statement will return the insce of the class
        return DBConnectionHolder.INSTANCE;
    }

    /**
     * Method that access the credentials stored in configuration file and uses
     * them to connect to the database.
     */
    private void initConnectionToDb() {
        ConfigurationFileProperties config = ConfigurationFileProperties.getInstance();
        String url = config.getPropertyValue("dbUrl");
        String dbName = config.getPropertyValue("dbName");
        String userName = config.getPropertyValue("dbUserName");
        String password = config.getPropertyValue("dbPassword");
        String driver=config.getPropertyValue("dbDriver");

        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, userName, password);
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, "Error connecting to database", ex);
        }
    }

    /**
     * The SQL statements will be executed as PreparedStatements with specific
     * parameters. 
     * Parameter 'sqlToExecute' is used to execute the statement.
     * Parameter 'params' is used as the parameters of the statement.
     */
    public boolean executeSQL(String sqlToExecute, Object... params) {
        PreparedStatement ps = createPreparedStatement(sqlToExecute, params);
        try {
            return executeSQL(ps);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, "Error closing PreparedStatement", ex);
        }
        return false;
    }

    private boolean executeSQL(PreparedStatement ps) throws SQLException {
        try {
            if (conn == null) {
                initConnectionToDb();
            }

            if (ps != null) {
                ps.execute();
                return true;
            }

            //The return statement is used to confirm wheather the statement is executed successfully. 
            return false;
        } catch (SQLException ex) {

            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, "Error executing sql statement", ex);
            return false;
        } finally {
            if (ps != null) {
                ps.close();
            }

        }
    }

    /**
     * The SQL statements will be executed as PreparedStatements with specific
     * parameters. 
     * Parameter 'sqlToExecute' is used to execute the statement.
     * Parameter 'props' is used as the parameters returned out from the
     * statement. 
     * Parameter 'params' is used as the parameters of the statement.
     * The method returns the properties requested in List format along with the values from the database.

     */
    public List<Map<String, Object>> queryDB(String sqlQuery, List<String> props, Object... params) throws SQLException {
        PreparedStatement ps = null;
        List<Map<String, Object>> returnedProps = new ArrayList<>();

        try {
            ps = createPreparedStatement(sqlQuery, params);
            ResultSet res = queryDB(ps);

            while (res.next()) {
                Map<String, Object> result = new HashMap<>();

                for (String prop : props) {
                    result.put(prop, res.getObject(prop));
                }

                returnedProps.add(result);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, "Error manipulating ResultSet", ex);
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        
        return returnedProps;
    }

    private ResultSet queryDB(PreparedStatement ps) {
        ResultSet res = null;
        try {
            if (conn == null) {
                initConnectionToDb();
            }

            if (ps != null) {
                res = ps.executeQuery();

            }
         
        // the method throws SQLException
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, "Error executing sql query statement", ex);
        }

        return res;
    }
    
    
    /**
     * The SQL statements will be executed as PreparedStatements with specific
     * parameters.
     * Parameter 'params' is used as the parameters of the statement.
     * Parameter 'sqlUpdate' is used to update the statement.
     */
        public void updateDB(String sqlUpdate, Object... params) {
        try {
            PreparedStatement ps = createPreparedStatement(sqlUpdate, params);
            updateDB(ps);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, "Error closing PreparedStatement", ex);
        }
    }

    private void updateDB(PreparedStatement ps) throws SQLException {
        try {
            if (conn == null) {
                initConnectionToDb();
            }
            if (ps != null) {
                ps.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, "Error executing sql update statement", ex);
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    /**
     * Creation of PreparedStatements for the SQL string statement. 
     * Parameter 'sqlString' is used as SQL statement to create PreparedStatements 
     * Parameter 'params' is used as the parameters of the statement.
     */
    private PreparedStatement createPreparedStatement(String sqlString, Object... params) {
        PreparedStatement ps = null;

        try {
            if (conn == null) {
                initConnectionToDb();
            }

            ps = conn.prepareStatement(sqlString);

            if (ps != null) {
                for (int i = 0; i < params.length; ++i) {
                    Object param = params[i];
                    if (param != null) {

                        Class paramClass = param.getClass();

                        if (paramClass == String.class) {
                            ps.setString(i + 1, (String) param);
                        } else if (paramClass == Integer.class) {
                            ps.setInt(i + 1, (int) param);
                        } else if (paramClass == Float.class) {
                            ps.setFloat(i + 1, (float) param);
                        } else {
                            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, "PreparedStatement set method not mapped");
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, "Error creating or manipulating new preparedStatement", ex);
        }

        //The return statement returns the prepared statemenr of SQL statement. 
        return ps;
    }
}

