<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${party.getPokemon()}" var="pokemon">
	<img src="${pokemon.getImg()}" alt="Pokemon" width="150" height="150">
	<h5>
		<ul>
			<li>
				<c:out value="${pokemon.getName()}"/>
			</li>
			
			<li>
				Ability: <c:out value="${pokemon.getAbility()}"/>
			</li>
			<h4>Type(s)</h4>
			<li>
				<c:out value="${pokemon.getType1()}"/>
			</li>
		
			<h4>Move(s)</h4>
			<li>
				<c:out value="${pokemon.getMove1()}"/>
			</li>
			<li>
				<c:out value="${pokemon.getMove2()}"/>
			</li>
			<li>
				<c:out value="${pokemon.getMove3()}"/>
			</li>
			<li>
				<c:out value="${pokemon.getMove4()}"/>
			</li>
			<h4>Stats</h4>
			<li>
				hp: <c:out value="${pokemon.getHp()}"/>
			</li>
			<li>
				atk: <c:out value="${pokemon.getAtk()}"/>
			</li>
			<li>
				def: <c:out value="${pokemon.getDef()}"/>
			</li>
			<li>
				spAtk: <c:out value="${pokemon.getSpAtk()}"/>
			</li>
			<li>
				spDef: <c:out value="${pokemon.getSpDef()}"/>
			</li>
			<li>
				speed: <c:out value="${pokemon.getSpd()}"/>
			</li>
		</ul>
	</h5>         
</c:forEach>