<%--
 * action-1.jsp
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
	
	<acme:textbox code="administrator.username" path="username" />
	<br/>
	<acme:password code="administrator.password" path="password"/>
	<br/>
	<acme:password code="administrator.password2" path="password2"/>
	<br/>
	<acme:textbox code="administrator.name" path="name" />
	<br/>
	<acme:textbox code="administrator.surname" path="surname"/>
	<br/>
	<acme:textbox code="administrator.email" path="email"/>
	<br/>
	<acme:textbox code="administrator.phone" path="phone"/>
	<br/>
	<acme:textbox code="administrator.picture" path="picture"/>
	<br/>
	<form:checkbox path="agreed"/>
	<form:label path="agreed">
		<spring:message code="administrator.register.agree" />
		<a href="misc/lopd.do"><spring:message code="administrator.register.agree.2"/></a>
	</form:label>
	<form:errors path="agreed" cssClass="error" />
	<br/>
	<acme:submit name="save" code="administrator.save"/>
	<acme:cancel url="welcome/index.do" code="administrator.cancel"/>

</form:form>
