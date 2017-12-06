package com.hariinc.jdbctest;

import java.sql.*;
import java.sql.DatabaseMetaData;

/**
 * Test program to connect to a database and run query.
 */
public class DBTest {

    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/sakila";  // TODO
        String user = "root";
        String password="gogobob5";
        
        Connection conn = getConnection(jdbcURL, user, password);
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("SELECT first_name, last_name FROM sakila.actor");  // TODO
            ResultSet rs = ps.executeQuery();
            printColumnInfo(rs);
            // Print results
            while (rs.next()) {
                System.out.printf("%s, %s\n", rs.getString(1), rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close prepared statement.
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // Close connection.
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static Connection getConnection(String url, String user, String password) {
        Connection conn = null;
        try {
            System.out.println("Connecting to database");
            // For MySQL
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila?user=root&password=gogobob5");
            
        	
            System.out.println("Connected to database");
            
            
            DatabaseMetaData md = conn.getMetaData();
            String productName = md.getDatabaseProductName();
            System.out.println("Product name: " + productName + 
                    ", Product major version: " + md.getDatabaseMajorVersion() +
                    ", Product minor version: " + md.getDatabaseMinorVersion() + 
                    ", Product version: " + md.getDatabaseProductVersion() +
                    ", Driver Major version: " + md.getDriverMajorVersion() + 
                    ", Driver Minor version: " + md.getDriverMinorVersion() +
                    ", Driver name: " + md.getDriverName() +
                    ", Driver version: " + md.getDriverVersion());
            
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }
    
    public static void printColumnInfo(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        for (int ix=1; ix <= rsmd.getColumnCount(); ix++) {
            System.out.printf("Column: %1d Name: '%2s' Type: %3s", ix, rsmd.getColumnName(ix), rsmd.getColumnTypeName(ix));
            System.out.println();
        }
    }
}
