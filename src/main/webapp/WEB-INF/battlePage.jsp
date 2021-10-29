<!-- attack method (should b attack method in controller)
	should take in the move as a parameter, the pokemon being attacked
	current pokemon being attacked,
	updates the attacked pokemons health,
	send to attack method in controller,
	if pokemon is dead send to filter method in controller
	 update the current pokemon
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

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

.leftside{
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

      <div class="flex">
      <div class="leftside">
        <h3>You</h3>
        
        <h3><c:out value="${userPokemon.getName()}"/> </h3>
        <img src="${userPokemon.getImg()}" alt="Pokemon" width="250" height="250">
        <h3>Hp: <c:out value="${userPokemonHp}"/> </h3>
                <h4>
	<c:out value="${str1}"/>
	<c:out value="${str2}"/>
	</h4>
         <h4>What move will you use?</h4>      
         <form action="/attack" method="POST">
    				<input type="hidden" name="userMove" value="${userPokemon.getMove1()}">
    				<button type="submit" value="submit move1"><c:out value="${userPokemon.getMove1()}"/></button>
    	 </form>
    	 <form action="/attack" method="POST">
    				<input type="hidden" name="userMove" value="${userPokemon.getMove2()}">
    				<button type="submit" value="submit move2"><c:out value="${userPokemon.getMove2()}"/></button>
    	 </form>
    	 <form action="/attack" method="POST">
    				<input type="hidden" name="userMove" value="${userPokemon.getMove3()}">
    				<button type="submit" value="submit move3"><c:out value="${userPokemon.getMove3()}"/></button>
    	 </form>
    	 <form action="/attack" method="POST">
    				<input type="hidden" name="userMove" value="${userPokemon.getMove4()}">
    				<button type="submit" value="submit move4"><c:out value="${userPokemon.getMove4()}"/></button>
    	 </form>
      </div>

        <div class="rightside">
          <h3><c:out value="${oppUsername}"/></h3>
          <h3><c:out value="${oppPokemon.getName()}"/> </h3>
          <img src="${oppPokemon.getImg()}" alt="Pokemon" width="250" height="250">
          <h3>Hp: <c:out value="${oppPokemonHp}"/> </h3>
      </div>

    </div>


</body>
</html>
