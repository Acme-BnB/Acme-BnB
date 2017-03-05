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
	access="hasRole('AUDITOR')">

	<form:form	action="auditor/audit/edit.do"	modelAttribute="auditForm"> 

		<form:hidden path="propertyId"/>
		<form:hidden path="id"/>
		<acme:textarea code="audit.text" path="text"/>
		<br/>
		
		
	<br/>
	<br/>
	<acme:submit name="save" code="audit.save"/>
	<jstl:if test="${id!=0}">
			<acme:submit name="delete" code="audit.delete"/>
		</jstl:if>
	
	<acme:cancel code="audit.cancel" url="auditor/audit/display.do" />
		
		
	</form:form>

</security:authorize>


