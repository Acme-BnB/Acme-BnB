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

<form:form action="${requestURI}" modelAttribute="auditorForm">

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	<acme:textbox code="auditor.username" path="username" />
	<br/>
	<acme:password code="auditor.password" path="password"/>
	<br/>
	<acme:password code="auditor.password2" path="password2"/>
	<br/>
	<acme:textbox code="auditor.name" path="name" />
	<br/>
	<acme:textbox code="auditor.surname" path="surname"/>
	<br/>
	<acme:textbox code="auditor.email" path="email"/>
	<br/>
	<acme:textbox code="auditor.phone" path="phone"/>
	<br/>
	<acme:textbox code="auditor.picture" path="picture"/>
	<br/>
	<acme:textbox code="auditor.companyName" path="companyName"/>
	<br/>
	<form:checkbox path="agreed"/>
	<form:label path="agreed">
		<spring:message code="auditor.register.agree" />
		<a href="misc/lopd.do"><spring:message code="auditor.register.agree.2"/></a>
	</form:label>
	<form:errors path="agreed" cssClass="error" />
	<br/>
	<acme:submit name="save" code="auditor.save"/>
	<acme:cancel url="welcome/index.do" code="auditor.cancel"/>

</form:form>