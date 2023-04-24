<%@page import = "java.util.ArrayList" %>
<%@page import = "com.Project.ResultPair" %>

<html>
<head>
    <%-- importing stylesheet for styling this page --%>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <%-- back button on search page, when this is clicked, it goes to home page i.e. index.jsp --%>
    <button onclick="window.location.href='http://localhost:8080/SearchEngine';">Back</button>
    <br>
    <h3>Results</h3>
    <%-- result of query in table format with page Title and Link; --%>
    <table border=2 class="table">
        <tr>
            <th>Title</th>
            <th>Link</th>
        </tr>
        <%-- Accessing the result arraylist and printing row by row--%>
        <%
            ArrayList<ResultPair> result=(ArrayList<ResultPair>) request.getAttribute("Search");
            for(ResultPair resPair:result){
        %>
        <tr>
            <td><% out.println(resPair.getPageTitle()); %></td>
            <td><a href="<% out.println(resPair.getPageLink()); %>"><% out.println(resPair.getPageLink()); %></a></td>
        </tr>
        <% } %>
    </table>
</body>
</html>