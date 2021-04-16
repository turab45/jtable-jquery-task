package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
private static Connection conn = null;
    
    public static Connection getConnection(){
    
        if(conn == null){
        
            try {
                
                Class.forName("com.mysql.jdbc.Driver");
                conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jquery-task-db", "root", "root");
            } catch (Exception e) {
                System.out.println("ERROR: "+e.getMessage());
                e.printStackTrace();
            }
            
        }
        return conn;
    }

}
