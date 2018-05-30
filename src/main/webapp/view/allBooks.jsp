<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>List of Bookssss</title>
</head>
<body>
<table border="1">

    <spring:url value="/addBook" var="createUrl"/>
  <input type="button" onclick="location.href='${createUrl}'" value="Create">
    <table>
        <th>
            Table name is List of books</td>
        </th>
        <c:forEach items="${pageHolder.getPageList()}" var="item">
            <tr>
                <td>
                    <p>"${item.title}"</p>
                </td>
                <td>
                  <p>"${item.author}"</p>
                </td>
                <td>
                  <p>"${item.isbn}"</p>
                </td>
                <td>
                <p>"${item.printedDate}"</p>
                </td>
                <td>
                 <p>"${item.readAlready}"</p>
                </td>
            </tr>
        </c:forEach>
    </table>
    <c:forEach begin="1" end="${pageHolder.getPageCount()}" varStatus="page">
         <spring:url value="/listBooks/${page.index}" var="pageUrl"/>
         <a href="${pageUrl}">${page.index}</a>
    </c:forEach>
</form>
</body>
</html>