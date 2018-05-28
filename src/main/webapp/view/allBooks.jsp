<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <c:forEach items="${books}" var="book">
        <tr>
            <td>
                <p>{book.id}</p>
            </td>
            <td>
                <p>${book.title}</p>
            </td>
            <td>
                <p>${book.author}</p>
            </td>
            <td>
                <p>${book.isbn}</p>
            </td>
            <td>
                <p>${book.bookPrintedDate}</p>
            </td>
            <td>
                <p>${book.readAlready}</p>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
