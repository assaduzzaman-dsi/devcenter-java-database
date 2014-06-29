package com.heroku.example;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

public class Main {
    
    public static void main(String[] args) throws Exception {
        
        Connection connection = getConnection();
        
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS ticks");
        stmt.executeUpdate("CREATE TABLE ticks (tick timestamp)");
        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
        while (rs.next()) {
            System.out.println("Read from DB: " + rs.getTimestamp("tick"));
        }
    }
    
    private static Connection getConnection() throws URISyntaxException, SQLException {
    	
    	String url = "postgres://webmxepepayoov:IVWp0871dGYbbMyqrOVUv0eNeb@ec2-54-197-250-40.compute-1.amazonaws.com:5432/d22j8uu3jhe3qq";
        URI dbUri = new URI(url);

//        System.get
        
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath() + "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

        return DriverManager.getConnection(dbUrl, username, password);
    }

}