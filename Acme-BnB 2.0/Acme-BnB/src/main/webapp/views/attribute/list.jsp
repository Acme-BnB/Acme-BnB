<%--
 * list.jsp
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

<display:table name="attributes"
	id="row"
	class="displaytag"
	pagesize="5"
	requestURI="${requestURI}">
	
	<security:authorize access="hasRole('ADMIN')">
	
	<display:column>
		<a href="administrator/attribute/edit.do?attributeId=${row.id}"><spring:message code="attribute.edit" /></a>
	</display:column>			
	
	<spring:message code="attribute.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true"/>
	
	</security:authorize>

</display:table>