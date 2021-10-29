<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!DOCTYPE html>

<html lang="en">
<head>
  </head>
	<style>	
	.error_message{
		color:red;
	}
	.message_message{
		color:limegreen;
	}
  .leftside, .rightside {
height: 100vh;
width: 50%;
}

.leftside,h1{
    background: turquoise;
}

.rightside {
    background: red;
}
.flex{
  display: flex;
}
.fluid-content {
  background:turquoise;
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
        <li class="nav-item">
        <a class="nav-link" href="/battle">Battle</a>
        </li>
      </c:if>
      </ul>
    </div>
		</nav>
        <!--battle logic and views go in here-->
    </div>

<h1>
WINNER!
</h1>


</body>
</html>

