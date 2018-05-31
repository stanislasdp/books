<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>Add book</title>
</head>
<body>

 <spring:url value="/addNewBook" var="createUrl"/>
<form:form action="${createUrl}" method="POST" modelAttribute="book">

 <label for="booktitle">Title</label>
 <form:input type="text" path="title" id="booktitle"/>

 <label for="bookauthor">Author</label>
 <form:input type="text" path="author" id="bookauthor"/>

 <label for="bookisbn">ISBN<</label>
 <form:input type="text" path="isbn" id="bookisbn"/>

 <label for="bookPrintedDate">Printed date</label>
  <form:input type="date" path="printedDate" id="bookPrintedDate"/>

 <label for="bookChecked">Read?</label>
 <form:checkbox path="readAlready" id="bookChecked"/>

 <form><input type="submit"></form>
</form:form>

</body>
</html>