<%--
 * browse.jsp
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


<!-- Listing grid -->
<security:authorize access="isAuthenticated()">
<display:table pagesize="5" class="displaytag" keepStatus="true" name="audits" requestURI="${requestURI}" id="row">
	
	<!-- Action links -->
	
	<!-- Attributes -->
	
	<spring:message code="audit.auditor" var="auditor"/>
	<display:column property="auditor.name" title="${auditor}" sortable="false" />

	<spring:message code="audit.writtenMoment" var="writtenMomentHeader" />
	<display:column property="writtenMoment" title="${writtenMomentHeader}" sortable="true" />
	
	<display:column>
		<a href="audit/display.do?auditId=${row.id}"><spring:message code="audit.view" /></a>
	</display:column>
	
</display:table>
</security:authorize>