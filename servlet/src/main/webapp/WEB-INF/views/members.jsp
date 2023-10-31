<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Member List</title>
</head>
<body>
<a href="/index.html">메인</a>
<table>
    <thead>
    <th>id</th>
    <th>username</th>
    <th>age</th>
    </thead>

    <tbody>
    <!-- 뷰 템플릿 사용으로 for문 사용하지 않음! -->
    <c:forEach var="item" items="${members}">
    <tr> <td>${item.id}</td>
    <td>${item.username}</td>
    <td>${item.age}</td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>