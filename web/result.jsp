<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: thang
  Date: 3/12/21
  Time: 9:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List product</title>
</head>
<body>
<h2>List Product</h2>
<%
    String keyword = request.getParameter("name");
    if(keyword == null || keyword.trim().isEmpty()){
        //URL rewriting : thay doi thong tin url
        response.sendRedirect("search.jsp?msg=Enter your keyword");
        return;
    }
%>
<c:if test="${requestScope.finder==null}">
    <jsp:forward page="search.jsp"/>
</c:if>
<jsp:useBean id="finder" class="model.ProductFinderBean"/>
<jsp:setProperty name="finder" property="keyword" value="<%=keyword%>"/>
<table>
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Description</td>
    </tr>
    <c:forEach var="product" items="${finder.allProduct}">
        <tr>
            <td><c:out value="${product.id}"/></td>
            <td><c:out value="${product.name}"/></td>
            <td><c:out value="${product.desc}"/></td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
