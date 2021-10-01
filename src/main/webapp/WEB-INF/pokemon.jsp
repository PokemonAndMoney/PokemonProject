<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>

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
					</li>
					
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
		
		<form:form action="/add/pokemon/toParty" method="post" modelAttribute="addPokemon">
			
			<form:input path="name" value="${name}" type="hidden"/>
	        
	        <h1>Your Pokemon is <c:out value="${name}!"/><c:out value=" ID#:${id}"/></h1>
	        
	        <a href="/view/${id-1}"><button>Previous Pokemon</button></a>
	        <a href="/view/${id+1}"><button>Next Pokemon</button></a>
	    	
	    	<div>
	    		<form:input path="img" value="${sprite.getFront_default()}" type="hidden"/>
		        <img src="${sprite.getFront_default()}" alt="Pokemon" width="250" height="250">
		        
		        <h3>Type(s):</h3>
		        		
	              <c:forEach items="${types}" var="type">
		                <c:set var = "counter" value = "1"/>
					    <h2 style="display: flex">
			                <c:out value="${type.getType().getName()}"/>
			                <form:input path="type${counter}" value="${type.getType().getName()}" type="hidden"/>
			                <c:set var = "counter" value = "${counter + 1}"/>
		                </h2>            
				  </c:forEach>
				  <h3>Stats:</h3>
		     		<c:forEach items="${stats}" var="stat">
						<h3 style="display: flex">
						<c:choose>
							<c:when test="${stat.getStat().getName() == 'hp'}">
								<form:input path="hp" value="${stat.getBase_stat()}" type="hidden"/>		        
							</c:when>
							
							<c:when test="${stat.getStat().getName() == 'attack'}">
								<form:input path="atk" value="${stat.getBase_stat()}" type="hidden"/>		        
							</c:when>
							
							<c:when test="${stat.getStat().getName() == 'defense'}">
								<form:input path="def" value="${stat.getBase_stat()}" type="hidden"/>		        
							</c:when>
							
							<c:when test="${stat.getStat().getName() == 'special-attack'}">
								<form:input path="spAtk" value="${stat.getBase_stat()}" type="hidden"/>		        
							</c:when>
							<c:when test="${stat.getStat().getName() == 'special-defense'}">
								<form:input path="spDef" value="${stat.getBase_stat()}" type="hidden"/>		        
							</c:when>
							
							<c:otherwise>
								<form:input path="spd" value="${stat.getBase_stat()}" type="hidden"/>		        
							</c:otherwise>	
						</c:choose>	
						
		                <c:out value="${stat.getStat().getName()}"/>: <c:out value="${stat.getBase_stat()}"/>
		                </h3>            
				  </c:forEach>
	        </div>
	        
	        <div>
		        Move 1:
		        <form:select path="move1">
					<c:forEach items="${moves}" var="move">
						<form:option value="${move.getMove().getName()}">
							<c:out value="${move.getMove().getName()}"/>
						</form:option>
					</c:forEach>
				</form:select>
	      	</div>
	      	
	        <div>
	        Move 2:
	        <form:select path="move2">
				<c:forEach items="${moves}" var="move">
					<form:option value="${move.getMove().getName()}">
						<c:out value="${move.getMove().getName()}"/>
					</form:option>
				</c:forEach>
			</form:select>
	      	</div>
	      	
	        <div>
	        Move 3:
	        <form:select path="move3">
				<c:forEach items="${moves}" var="move">
					<form:option value="${move.getMove().getName()}">
						<c:out value="${move.getMove().getName()}"/>
					</form:option>
				</c:forEach>
			</form:select>
	      	</div>
	      	
	        <div>
	        Move 4:
	       <form:select path="move4">
				<c:forEach items="${moves}" var="move">
					<form:option value="${move.getMove().getName()}">
						<c:out value="${move.getMove().getName()}"/>
					</form:option>
				</c:forEach>
			</form:select>
	      	</div>
	      	
	      	<div>
				Ability:
				<form:select path="ability">
					<c:forEach items="${abilities}" var="ability">
						<form:option value="${ability.getAbility().getName()}">
							<c:out value="${ability.getAbility().getName()}"/>
						</form:option>
					</c:forEach>
				</form:select>
			</div>
			
			<div>	
				<c:if test="${userId != null}">
				<form:select path="party">
					<c:forEach items="${allParties}" var="party">
						<form:option value="${party.getId()}">
							<c:out value="${party.getName()}"/>
						</form:option>
					</c:forEach>
				</form:select>
					<input type="submit" value="Add to My Party!"/>
				</c:if>
	    	</div>
        </form:form> 
     </div> 
</body>
</html>