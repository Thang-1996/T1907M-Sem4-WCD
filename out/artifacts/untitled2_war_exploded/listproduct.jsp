<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: thang
  Date: 3/15/21
  Time: 10:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<jsp:useBean id="finder" class="model.ProductFinderBean"/>
<table>
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Description</td>
    </tr>
    <c:forEach var="product" items="${finder.products}">
        <tr>
            <td><c:out value="${product.id}"/></td>
            <td><c:out value="${product.name}"/></td>
            <td><c:out value="${product.desc}"/></td>
            <td><a href="ProductController?proId=${product.id}" >Xoa</a></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
