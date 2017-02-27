<%--
 * register.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="${requestURI}" modelAttribute="administratorForm">

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	<acme:textbox code="lesor.username" path="username" />
	<br/>
	<acme:password code="lesor.password" path="password"/>
	<br/>
	<acme:password code="lesor.password2" path="password2"/>
	<br/>
	<acme:textbox code="lesor.name" path="name" />
	<br/>
	<acme:textbox code="lesor.surname" path="surname"/>
	<br/>
	<acme:textbox code="lesor.email" path="email"/>
	<br/>
	<acme:textbox code="lesor.phone" path="phone"/>
	<br/>
	<acme:textbox code="lesor.picture" path="picture"/>
	<br/>
	<div>
		<acme:textbox code="lesor.creditCard.holderName" path="creditCard.holderName"/>
					
		<acme:textbox code="lesor.creditCard.brandName" path="creditCard.brandName"/>
					
		<acme:textbox code="lesor.creditCard.number" path="creditCard.number"/>
					
		<acme:textbox code="lesor.creditCard.expirationMonth" path="creditCard.expirationMonth"/>
					
		<acme:textbox code="lesor.creditCard.expirationYear" path="creditCard.expirationYear"/>
			
		<acme:textbox code="lesor.creditCard.cvvCode" path="creditCard.cvvCode"/>
	</div>
	<form:checkbox path="agreed"/>
	<form:label path="agreed">
		<spring:message code="lesor.register.agree" />
		<a href="misc/lopd.do"><spring:message code="lesor.register.agree.2"/></a>
	</form:label>
	<form:errors path="agreed" cssClass="error" />
	<br/>
	<acme:submit name="save" code="lesor.save"/>
	<acme:cancel url="welcome/index.do" code="lesor.cancel"/>

</form:form>

