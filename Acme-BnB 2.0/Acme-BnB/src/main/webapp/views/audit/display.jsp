<%--
 * display.jsp
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

<security:authorize access="isAuthenticated()">
	
<table id="row" class="table">
	<tbody>
		<tr>
			<th><spring:message code="audit.auditor"/></th>
			<th><spring:message code="audit.writtenMoment"/></th>
		</tr>
		<tr>
			<td><jstl:out value="${audit.auditor.name}"/></td>
			<td><jstl:out value="${audit.writtenMoment}"/></td>
		</tr>
		<tr>
			<th><spring:message code="audit.text"/></th>
		</tr>
		<tr>
			<td><jstl:out value="${audit.text}"/></td>
		</tr>
	</tbody>
</table>


</security:authorize>
