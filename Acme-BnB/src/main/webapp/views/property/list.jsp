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

<display:table name="properties"
	id="row"
	class="displaytag"
	pagesize="5">
	
	<security:authorize access="hasRole('LESSOR')">
	
	<display:column>
		<a href="lessor/property/edit.do?propertyId=${row.id}"><spring:message code="property.edit" /></a>
	</display:column>			
	
					
	<spring:message code="property.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true"/>
	
	<spring:message code="property.rate" var="rateHeader" />
	<display:column property="rate" title="${rateHeader}" sortable="true"/>
	
	<spring:message code="property.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="false"/>
	
	<spring:message code="property.address" var="addressHeader"/>
	<display:column property="address" title="${addressHeader}" sortable="false"/>
	
	</security:authorize>

</display:table>
