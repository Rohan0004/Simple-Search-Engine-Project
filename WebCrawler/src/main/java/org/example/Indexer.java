package org.example;

import org.jsoup.nodes.Document;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//insert desired data(page title,page link and page text) in database(table name = pages);
public class Indexer {
    static Connection connection=null;

    Indexer(Document document, String link) {
        //get title and text of webPage
        String title=document.title();
        String text=document.text();
        try {
            //make connection with database
            connection=ConnectionDatabase.getConnection();

            //run insert query to add data in table;
            //prepare sql query with 3 parameters to store Page title,link and text and execute it;
            PreparedStatement preparedStatement = connection.prepareStatement("Insert into pages values(?,?,?);");
            preparedStatement.setString(1,title);
            preparedStatement.setString(2,link);
            preparedStatement.setString(3,text);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
