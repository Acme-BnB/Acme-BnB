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
	access="hasRole('TENANT')">

	<form:form	action="tenant/finder/edit.do"	modelAttribute="finder"> 
		
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		<form:hidden path="lastTimeSearched"/>
		
		<acme:textbox code="finder.destinationCity" path="destinationCity"/>
		<acme:textbox code="finder.minPrice" path="minPrice"/>
		<acme:textbox code="finder.maxPrice" path="maxPrice"/>
		<acme:textbox code="finder.keyword" path="keyword"/>
		
		<acme:submit name="save" code="finder.save"/>
		<jstl:if test="${finder.id != 0}">
			<acme:submit name="delete" code="finder.delete"/>
		</jstl:if>
		<acme:cancel code="finder.cancel" url="tenant/finder/display.do"/>
		
		
	</form:form>

</security:authorize>

