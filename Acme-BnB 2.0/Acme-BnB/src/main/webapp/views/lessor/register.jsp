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

<form:form action="${requestURI}" modelAttribute="lessorForm">

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	<acme:textbox code="lessor.username" path="username" />
	<br/>
	<acme:password code="lessor.password" path="password"/>
	<br/>
	<acme:password code="lessor.password2" path="password2"/>
	<form:checkbox path="agreed"/>
	<form:label path="agreed">
		<spring:message code="lessor.register.agree" />
		<a href="misc/lopd.do"><spring:message code="lessor.register.agree.2"/></a>
	</form:label>
	<form:errors path="agreed" cssClass="error" />
	
	<br/>
	<acme:textbox code="lessor.name" path="name" />
	<br/>
	<acme:textbox code="lessor.surname" path="surname"/>
	<br/>
	<acme:textbox code="lessor.email" path="email"/>
	<br/>
	<acme:textbox code="lessor.phone" path="phone"/>
	<br/>
	<acme:textbox code="lessor.picture" path="picture"/>
	<br/>
	<fieldset>
		<legend align="right"><spring:message code="lessor.creditCard.info"/></legend>
		<acme:textbox code="lessor.creditCard.holderName" path="creditCard.holderName"/>
		<br/>			
		<acme:textbox code="lessor.creditCard.brandName" path="creditCard.brandName"/>
		<br/>		
		<acme:textbox code="lessor.creditCard.number" path="creditCard.number"/>
		<br/>			
		<acme:textbox code="lessor.creditCard.expirationMonth" path="creditCard.expirationMonth"/>
		<br/>			
		<acme:textbox code="lessor.creditCard.expirationYear" path="creditCard.expirationYear"/>
		<br/>	
		<acme:textbox code="lessor.creditCard.cvv" path="creditCard.cvv"/>
	</fieldset>
	<br/>
	<acme:submit name="save" code="lessor.save"/>
	<acme:cancel url="/welcome/index.do" code="lessor.cancel"/>

</form:form>

