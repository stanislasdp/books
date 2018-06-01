<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>Add book</title>
</head>
<body>

 <spring:url value="/updateBook" var="url"/>
<form:form action="${url}" method="POST" modelAttribute="book">
 <form:hidden path="id" value="${book.id}"/>

 <label for="booktitle">Title</>
 <form:input type="text" path="title" id="booktitle" value="${book.title}"/>
 <form:errors path = "title"/>

 <label for="bookauthor">Author</>
 <form:input type="text" path="author" id="bookauthor" readonly="true"/>
 <form:errors path = "author"/>

 <label for="bookisbn">ISBN</>
 <form:input type="text" path="isbn" id="bookisbn"/>
 <form:errors path = "isbn"/>

  <label for="bookPrintedDate">Printed date</>
  <form:input type="date" path="printedDate" id="bookPrintedDate"/>
  <form:errors path = "printedDate"/>

 <form><input type="submit"></form>
</form:form>

</body>
</html>