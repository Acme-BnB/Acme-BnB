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

<form:form action="${requestURI}" modelAttribute="tenantForm">

	<form:hidden path="id"/>

	<jstl:choose>
		<jstl:when test="${tipe eq 'personal' }">
			<fieldset>
				<legend align="left"><spring:message code="tenant.personal.info"/></legend>
					<br/>
					<acme:textbox code="tenant.name" path="name" />
					<br/>
					<acme:textbox code="tenant.surname" path="surname"/>
					<br/>
					<acme:textbox code="tenant.email" path="email"/>
					<br/>
					<acme:textbox code="tenant.phone" path="phone"/>
					<br/>
					<acme:textbox code="tenant.picture" path="picture"/>
			</fieldset>
		</jstl:when>
		<jstl:otherwise>
			<fieldset>
				<legend align="left"><spring:message code="tenant.account.info"/></legend>
					<acme:textbox code="tenant.username" path="username" />
						<br/>
						<acme:password code="tenant.password" path="password"/>
						<br/>
						
						<acme:password code="tenant.password2" path="password2"/>
						<br/>
						<form:checkbox path="agreed"/>
						<form:label path="agreed">
							<spring:message code="tenant.register.agree" />
							<a href="misc/lopd.do"><spring:message code="tenant.register.agree.2"/></a>
						</form:label>
						<form:errors path="agreed" cssClass="error" />
						<br/>
			</fieldset>
			<fieldset>
				<legend align="left"><spring:message code="tenant.personal.info"/></legend>
					<br/>
					<acme:textbox code="tenant.name" path="name" />
					<br/>
					<acme:textbox code="tenant.surname" path="surname"/>
					<br/>
					<acme:textbox code="tenant.email" path="email"/>
					<br/>
					<acme:textbox code="tenant.phone" path="phone"/>
					<br/>
					<acme:textbox code="tenant.picture" path="picture"/>
			</fieldset>
		</jstl:otherwise>
	</jstl:choose>
	
	
	<br/>
	<acme:submit name="save" code="tenant.save"/>
	<acme:cancel url="welcome/index.do" code="tenant.cancel"/>

</form:form>