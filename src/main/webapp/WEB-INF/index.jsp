<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!DOCTYPE html>

<html lang="en">
<head></head>
	<style>	
	.error_message{
		color:red;
	}
	.message_message{
		color:limegreen;
	}
	</style>
<body>
    <div class="fluid-content">
    	<nav>
		    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" href="/">Home</a>
        <c:if test="${userId == null}">
        <li class="nav-item">
          <a class="nav-link" href="/home/login">Login</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/home/register">Register</a>
        </li>
        </c:if>
        <c:if test="${userId != null}">
        <li class="nav-item">
          <a class="nav-link" href="/home/logout">Logout</a>
        </li>
        </c:if>
      </ul>
    </div>
		</nav>
    	<div>
       <% for(int i = 1; i < 808; i+=1) { %>
        <tr>      
            <td><a href="/view/<%=i%>"><img src="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-viii/icons/<%=i%>.png" alt="Pokemon" width="100" height="100"></a>
            </td>
        </tr>
    <% } %>
      </div>
    </div>
</body>
</html>