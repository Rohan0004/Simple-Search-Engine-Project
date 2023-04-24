package org.example;
import java.sql.Connection;
import java.sql.DriverManager;

//database connection;
public class ConnectionDatabase {
    static Connection connection = null;
    public static Connection getConnection(){
        //if already connected return that connection else make new connection;
        if(connection!=null) return connection;
        String usr,pass,db;
        usr="root";
        pass="2004";
        db="searchEngineData";
        return getConnection(usr,pass,db);
    }
    private static Connection getConnection(String usr, String pass, String db){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + db + "?user=" + usr + "&password=" + pass);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}
