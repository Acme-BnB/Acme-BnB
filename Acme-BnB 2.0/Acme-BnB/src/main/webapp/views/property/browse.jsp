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

<display:table pagesize="5" class="displaytag" keepStatus="true" name="properties" requestURI="${requestURI}" id="row">
	
	<!-- Action links -->
	
	<!-- Attributes -->
	
	<spring:message code="property.name" var="nameHeader"/>
	<display:column property="name" title="${nameHeader}" sortable="false" />

	<spring:message code="property.rate" var="rateHeader" />
	<display:column property="rate" title="${rateHeader}" sortable="true" />

	<spring:message code="property.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="false" />

	<spring:message code="property.address" var="addressHeader" />
	<display:column property="address" title="${addressHeader}" sortable="false"  />

	<spring:message code="property.lessor" var="lessor"/>
	<display:column property="lessor.name" title="${lessor}" sortable="true"/>
	
	<display:column>
		<a href="lessor/display.do?propertyId=${row.id}"><spring:message code="property.view.lessor" /></a>
	</display:column>
	
	<display:column>
		<a href="property/display.do?propertyId=${row.id}"><spring:message code="property.view" /></a>
	</display:column>
<security:authorize access="isAuthenticated()">
	<display:column>
		<a href="audit/browse.do?propertyId=${row.id}"><spring:message code="property.view.audits" /></a>
	</display:column>
</security:authorize>

<security:authorize access="hasRole('AUDITOR')">
	<display:column>
		<a href="auditor/audit/create.do?propertyId=${row.id}"><spring:message code="property.add.audit" /></a>
	</display:column>
</security:authorize>	

</display:table>
	<input type="button" name="orderByRequest" value="<spring:message code="property.order" />"
			onclick="javascript: window.location.replace('property/browseByReq.do')" />