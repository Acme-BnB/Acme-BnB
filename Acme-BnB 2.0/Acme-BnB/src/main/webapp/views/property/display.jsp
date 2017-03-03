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

<table id="row" class="table">
	<tbody>
		<tr>
			<th><spring:message code="property.name"/></th>
			<th><spring:message code="property.rate"/></th>
			
		</tr>
		<tr>
			<td><jstl:out value="${property.name}"/></td>
			<td><jstl:out value="${property.rate}"/></td>
		</tr>
		<tr>
			<th><spring:message code="property.description"/></th>
			<th><spring:message code="property.address"/></th>
		</tr>
		<tr>
			<td><jstl:out value="${property.description}"/></td>
			<td><jstl:out value="${property.address}"/></td>
		</tr>
	</tbody>
</table>

	<input type="button" name="viewLessor" value="<spring:message code="property.view.lessor" />"
			onclick="javascript: window.location.replace('lessor/display.do?propertyId=${property.id}')" />