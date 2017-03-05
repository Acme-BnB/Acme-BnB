<%--
 * edit.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
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
	access="hasRole('LESSOR')">

	<form:form	action="lessor/value/edit.do" modelAttribute="valueForm"> 
		
		<form:hidden path="propertyId"/>
		
		<acme:select items="${attributes}" itemLabel="name" code="value.name" path="attribute"/>
		
		<acme:textbox code="value.text" path="text"/>
		
		<acme:submit name="save" code="attribute.save"/>
		<acme:cancel code="attribute.cancel" url="property/display.do?propertyId=${valueForm.propertyId}"/>
		
		
	</form:form>

</security:authorize>

