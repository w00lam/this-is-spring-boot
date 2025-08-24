<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<body>
<h2>Spring MVC Demo 회원 목록</h2>
<table>
    <c:forEach var="member" items="${members}">
        <tr>
            <td>${member.id}</td>
            <td>${member.name}</td>
            <td>${member.email}</td>
            <td>${member.age}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>