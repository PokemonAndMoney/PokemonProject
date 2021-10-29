<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${users}" var="user">
<h3><a href="/battle/setup/${user.getId()}"><c:out value="${user.getEmail()}"/></a></h3>          
</c:forEach>