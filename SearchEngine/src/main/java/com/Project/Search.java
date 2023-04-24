package com.Project;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/*
    this acts as search servlet by extending http-servlet;
    this performs two operations:
        1.save the current query in history table in database;
        2.get the top 30 result from database using ranking algorithm, store it in arraylist and forward the
            request to Search.jsp which shows the result in tabular form;
*/

@WebServlet("/Search")
public class Search extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        //gets the query(keyWord) entered by user;
        String keyWord=request.getParameter("keyWord").toLowerCase();
        try {
            //makes connection with database;
            Connection connection=ConnectionDatabase.getConnection();

            /* store query in history table; */
            //prepare sql statement for insert data;
            PreparedStatement preparedStatement= connection.prepareStatement("INSERT INTO history VALUES (?,?);");
            //add keyWord and link to database;
            preparedStatement.setString(1,keyWord);
            preparedStatement.setString(2,"http://localhost:8080/SearchEngine/Search?keyWord="+keyWord);
            // execute sql statement;
            preparedStatement.executeUpdate();

            /* result from database*/
            //run sql query to get top 30 result from DB according to ranking algorithm(sets the priority according to
            // count of occurrence of keyword in page text);
            ResultSet resultSet= connection.createStatement().executeQuery("SELECT DISTINCT pageTitle , pageLink,\n" +
                    " (LENGTH(LOWER(pageText))-LENGTH(REPLACE(LOWER(pageText),'"+keyWord+"','')))/LENGTH('"+keyWord+"')\n" +
                    " AS countOfkeyWordinPageText\n" +
                    "FROM pages\n" +
                    "ORDER BY countOfkeyWordinPageText DESC limit 30;");
            //store result, ResultPair is class for storing page title and link together in arraylist;
            ArrayList<ResultPair> result=new ArrayList<>();
            while (resultSet.next()){
                ResultPair pair=new ResultPair();
                pair.setPageTitle(resultSet.getString("pageTitle")); //title
                pair.setPageLink(resultSet.getString("pageLink"));   //link
                result.add(pair);
            }


//            response.setContentType("text/html");
//            PrintWriter printWriter=response.getWriter();
            for (ResultPair pair:result) {
                // printWriter.println("<h1>"+pair.getPageTitle()+"</h1><h2><a href="+pair.getPageLink()+">"+pair.getPageLink()+"</a></h2><br>"); //printing on web page
                System.out.println(pair.getPageTitle()+"\n"+pair.getPageLink()+"\n");  //printing on console
            }

            //set attribute search = result arraylist;
            request.setAttribute("Search",result);
            //forward the request to search.jsp(frontend) which shows result in web page;
            request.getRequestDispatcher("Search.jsp").forward(request,response);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
