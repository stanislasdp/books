<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>List of Books</title>
</head>
<body>

    <spring:url value="/addBook" var="createUrl"/>
    <input type="button" onclick="location.href='${createUrl}'" value="Add book">
    <spring:url value="/listBooks/1" var="listUrl"/>
    <br/>
    <form:form action="${listUrl}" method="GET" modelAttribute="searchAttr">
    <label for="searchByTitle">Search by Title</label>
        <form:input type="text" id="searchByTitle" path="searchParameters['title']"/>
    <label for="searchByAuthor">Search by author</label>
        <form:input type="text" id="searchByAuthor" path="searchParameters['author']"/>
    <label for="searchByIsbn">Search by ISBN</label>
        <form:input type="text" id="searchByIsbn" path="searchParameters['isbn']"/>
    <br>
    <form><input type="submit" value="Apply search params"></form>
    </form:form>

    <c:if test = "${pageHolder.getNrOfElements() > 0}">
    <table>
            <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>ISBN</th>
            <th>printed Date</th>
            <th>Already read?</th>
            </tr>
            <c:forEach items="${pageHolder.getPageList()}" var="item">
                <tr>
                    <td>
                        <p>${item.id}</p>
                    </td>
                    <td>
                        <p>${item.title}</p>
                    </td>
                    <td>
                        <p>${item.author}</p>
                    </td>
                    <td>
                        <p>${item.isbn}</p>
                    </td>
                    <td>
                        <p>${item.printedDate}</p>
                    </td>
                    <td>
                        <p>${item.readAlready}</p>
                    </td>
                    <td>
                        <spring:url value="/getBookForUpdate" var="getUrl"/>
                        <input type="button" onclick="location.href='${getUrl}/${item.id}'" value="Update">
                    </td>
                    <td>
                        <spring:url value="/readBook" var="readUrl"/>
                        <form:form action="${readUrl}/${item.id}" method="POST">
                            <input type="submit" value="Read Book">
                        </form:form>
                    </td>
                    <td>
                        <spring:url value="/deleteBook" var="delUrl"/>
                        <form:form action="${delUrl}/${item.id}" method="POST">
                            <input type="hidden" name="_method" value="DELETE"/>
                            <input type="submit" value="Delete Book">
                        </form:form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test = "${pageHolder.getPageCount() > 1}">
     <c:forEach begin="1" end="${pageHolder.getPageCount()}" varStatus="page">
            <spring:url value="/listBooks/${page.index}" var="pageUrl"/>
        <a href="${pageUrl}">${page.index}</a>
        </c:forEach>
    </c:if>
</body>
</html>