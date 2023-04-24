<%@page import = "java.util.ArrayList" %>
<%@page import = "com.Project.historyResultPair" %>

<html>
<head>
    <%-- importing stylesheet for styling this page --%>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <%-- back button on search page, when this is clicked, it goes to home page i.e. index.jsp --%>
    <button onclick="window.location.href='http://localhost:8080/SearchEngine';">Back</button>
    <br>
    <h3>History</h3>
    <table border=2 class="table">
        <tr>
            <th>keyWord</th>
            <th>Link</th>
        </tr>
        <%-- Accessing the result arraylist and printing row by row--%>
        <%
            ArrayList<historyResultPair> result=(ArrayList<historyResultPair>) request.getAttribute("History");
            for(historyResultPair resPair:result){
        %>
        <tr>
            <td><% out.println(resPair.getKeyWord()); %></td>
            <td><a href="<% out.println(resPair.getLink()); %>"><% out.println(resPair.getLink()); %></a></td>
        </tr>
        <% } %>
    </table>
</body>
</html>