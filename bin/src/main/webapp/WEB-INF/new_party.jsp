<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Party Page</title>
</head>
<body>
    <h1>Party!</h1>
    
    <p><form:errors path="party.*"/></p>
    
    <form:form method="POST" action="/party/new" modelAttribute="party">
        <p>
            <form:label path="name">Name of your party:</form:label>
            <form:input path="name"/>
            <form:input type="hidden" path="user" value="${userId}"/>
        </p>
        <input type="submit" value="Create my party!"/>
    </form:form>
    
</body>
</html>
