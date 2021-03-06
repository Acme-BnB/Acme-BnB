<%--
 * edit.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<security:authorize
	access="hasRole('TENANT')">

	<form:form	action="tenant/request/edit.do"	modelAttribute="requestForm"> 

		<form:hidden path="propertyId"/>
		
		<acme:textbox code="request.checkIn" path="checkIn"/>
		<br/>
		<acme:textbox code="request.checkOut" path="checkOut"/>
		<br/>
		<acme:checkbox code="request.smoker" path="smoker"/>
		<br/>
		<fieldset>
		<legend align="left"><spring:message code="request.creditCard.info"/></legend>
		<acme:textbox code="request.creditCard.holderName" path="creditCard.holderName"/>
		<br/>			
		<acme:textbox code="request.creditCard.brandName" path="creditCard.brandName"/>
		<br/>		
		<acme:textbox code="request.creditCard.number" path="creditCard.number"/>
		<br/>			
		<acme:textbox code="request.creditCard.expirationMonth" path="creditCard.expirationMonth"/>
		<br/>			
		<acme:textbox code="request.creditCard.expirationYear" path="creditCard.expirationYear"/>
		<br/>	
		<acme:textbox code="request.creditCard.cvv" path="creditCard.cvv"/>
	</fieldset>
	<br/>
	<br/>
	<acme:submit name="save" code="request.save"/>
	<acme:cancel code="request.cancel" url="tenant/finder/display.do" />
		
		
	</form:form>

</security:authorize>


