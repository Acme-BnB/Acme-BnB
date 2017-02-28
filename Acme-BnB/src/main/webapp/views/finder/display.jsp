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

<display:table name="finder"
	id="row"
	class="displaytag"
	pagesize="5">
	
	<security:authorize access="hasRole('TENANT')">
	
	<display:column>
		<a href="tenant/finder/edit.do?finderId=${row.id}"><spring:message code="finder.edit" /></a>
	</display:column>			
	
					
	<spring:message code="finder.destinationCity" var="destinationCityHeader" />
	<display:column property="destinationCity" title="${destinationCityHeader}" sortable="true"/>
	
	<spring:message code="finder.minPrice" var="minPriceHeader" />
	<display:column property="minPrice" title="${minPriceHeader}" sortable="true"/>
	
	<spring:message code="finder.maxPrice" var="maxPriceHeader" />
	<display:column property="maxPrice" title="${maxPriceHeader}" sortable="true"/>
	
	<spring:message code="finder.keyword" var="keywordHeader"/>
	<display:column property="keyword" title="${keywordHeader}" sortable="false"/>
	
	</security:authorize>

</display:table>