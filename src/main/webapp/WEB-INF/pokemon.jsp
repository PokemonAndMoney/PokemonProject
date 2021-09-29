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
        <h1>Your Pokemon is <c:out value="${name}!" /></h1>
    	<div>
        <img src="${sprite}" alt="Pokemon" width="250" height="250">
        <h3><c:out value="${hp}:${hpVal}" /></h3>
        <h3><c:out value="${atk}:${atkVal}" /></h3>
        <h3><c:out value="${def}:${defVal}" /></h3>
        <h3><c:out value="${spAtk}:${spAtkVal}" /></h3>
        <h3><c:out value="${spDef}:${spDefVal}" /></h3>
        <h3><c:out value="${spd}:${spdVal}" /></h3>
        </div>
        <div>
        List of moves:
        <c:forEach items="${moves}" var="move">
			<ul>
                <li>
                <c:out value="${move}"/>
                </li>
            </ul>
		</c:forEach>
        </div>
    </div>
</body>
</html>