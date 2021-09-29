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
        <h1>Your Pokemon is <c:out value="${name}!"/><c:out value=" ID#:${id}"/></h1>
        <a href="/view/${id-1}"><button>Previous Pokemon</button></a>
        <a href="/view/${id+1}"><button>Next Pokemon</button></a>
    	<div>
        <img src="${sprite}" alt="Pokemon" width="250" height="250">
        <h3>Type(s):</h3>
                <c:forEach items="${types}" var="type">
			      <h2 style="display: flex">
            
                <c:out value="${type}"/>
                </h2>            
		  </c:forEach>
        <h3><c:out value="${hp}:${hpVal}" /></h3>
        <h3><c:out value="${atk}:${atkVal}" /></h3>
        <h3><c:out value="${def}:${defVal}" /></h3>
        <h3><c:out value="${spAtk}:${spAtkVal}" /></h3>
        <h3><c:out value="${spDef}:${spDefVal}" /></h3>
        <h3><c:out value="${spd}:${spdVal}" /></h3>

        </div>
        <div>
        Move 1:
        <select>                
        <c:forEach items="${moves}" var="move">
			      <option>
                <c:out value="${move}"/>
                </option>            
		  </c:forEach>
        </select>
      </div>
              <div>
        Move 2:
        <select>                
        <c:forEach items="${moves}" var="move">
			      <option>
                <c:out value="${move}"/>
                </option>            
		  </c:forEach>
        </select>
      </div>
              <div>
        Move 3:
        <select>                
        <c:forEach items="${moves}" var="move">
			      <option>
                <c:out value="${move}"/>
                </option>            
		  </c:forEach>
        </select>
      </div>
              <div>
        Move 4:
        <select>                
        <c:forEach items="${moves}" var="move">
			      <option>
                <c:out value="${move}"/>
                </option>            
		  </c:forEach>
        </select>
      </div>
      <div>
        Ability:
        <select>                
        <c:forEach items="${abilities}" var="ability">
			      <option>
                <c:out value="${ability}"/>
                </option>            
		  </c:forEach>
        </select>
      </div>
    </div>
</body>
</html>