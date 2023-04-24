<html>
<head>
    <%-- importing stylesheet for styling this page --%>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<%-- title --%>
<h1>SIMPLE SEARCH ENGINE</h1>

<%-- search area to enter query with submit button, when submit button is clicked search servlet(Search.java) executes --%>
<form action="Search" class="searchForm">
    <input type="text" name="keyWord"></input>
    <button type="submit">Search</button>
</form>
<br>

<%-- to get history, when this button is clicked history servlet(History.java) executes--%>
<form action="History" >
    <button type="submit">History</button>
</form>
</body>
</html>
