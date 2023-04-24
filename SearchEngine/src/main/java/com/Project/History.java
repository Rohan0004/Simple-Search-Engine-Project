package com.Project;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/*
    this class acts as history servlet by extending http-servlet;
    this performs following operation:
        get history from history table present in database, store it in arraylist and forward the
            request to History.jsp which shows the history in tabular form;
*/

@WebServlet("/History")
public class History extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        try {
            //make database connection and get result from history table;
            Connection connection = ConnectionDatabase.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM history;");

            //store result, historyResultPair is class for storing keyword(query from user) and link together in arraylist;
            ArrayList<historyResultPair> results=new ArrayList<>();
            while (resultSet.next()){
                historyResultPair pair=new historyResultPair();
                pair.setKeyWord(resultSet.getString("keyWord"));
                pair.setLink(resultSet.getString("Link"));
                results.add(pair);
            }

            //set attribute History = results arraylist;
            request.setAttribute("History",results);
            //forward the request to History.jsp(frontend) which shows history result in web page;
            request.getRequestDispatcher("History.jsp").forward(request,response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
