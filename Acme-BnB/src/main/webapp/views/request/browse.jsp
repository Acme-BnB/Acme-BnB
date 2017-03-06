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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('TENANT')">
	<jstl:if test="${tenantUsername == pageContext.request.remoteUser}">


<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" keepStatus="true" name="requests" requestURI="${requestURI}" id="row">
	
	<!-- Action links -->
	
	<!-- Attributes -->

	<spring:message code="request.checkIn" var="checkInHeader"/>
	<display:column title="${checkInHeader}" sortable="true"><fmt:formatDate value="${row.checkIn }" pattern="dd/MM/yyyy" /></display:column>

	<spring:message code="request.checkOut" var="checkOutHeader"/>
	<display:column title="${checkOutHeader}" sortable="true"><fmt:formatDate value="${row.checkOut }" pattern="dd/MM/yyyy" /></display:column>

	<spring:message code="request.smoker" var="smokerHeader" />
	<display:column property="smoker" title="${smokerHeader}" sortable="false" />

	<spring:message code="request.creditCard.number" var="creditCardnumberHeader" />
	<display:column property="creditCard.number" title="${creditCardnumberHeader}" sortable="false" />

	<spring:message code="request.status" var="status"/>
	<display:column property="status" title="${status}" sortable="true"/>
	
	<spring:message code="request.property" var="property"/>
	<display:column property="property.name" title="${property}" sortable="true"/>
	
	<spring:message code="request.property.lessor" var="property"/>
	<display:column property="property.lessor.name" title="${property}" sortable="true"/>
	
	<display:column>
		<a href="lessor/displayByReq.do?requestId=${row.id}"><spring:message code="request.view.lessor" /></a>
	</display:column>
	
	<display:column>
		<a href="property/displayByRequest.do?requestId=${row.id}"><spring:message code="request.view.property" /></a>
	</display:column>
	
	<display:column>
	   <jstl:if test="${ row.status == 'ACCEPTED'}">
	    	<a href="invoice/display.do?requestId=${row.id }"><spring:message code="request.invoice" /></a>
	   </jstl:if>
  	</display:column>
	
</display:table>
</jstl:if>
</security:authorize>