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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<jstl:if test="${lessor.userAccount.username == pageContext.request.remoteUser}">
	<display:table pagesize="5" class="displaytag" name="requests" id="row" requestURI="${requestURI}">
	
		<spring:message code="request.property.name" var="nameHeader" />
		<display:column property="property.name" title="${nameHeader}" sortable="false" />
		
		<spring:message code="request.property.address" var="addressHeader" />
		<display:column property="property.address" title="${addressHeader}" sortable="false" />
		
		<spring:message code="request.checkIn" var="checkInHeader" />
		<display:column title="${checkInHeader}"	sortable="true"><fmt:formatDate value="${row.checkIn }" pattern="dd/MM/yyyy" /></display:column>
		
		<spring:message code="request.checkOut" var="checkOutHeader" />
		<display:column title="${checkOutHeader}"	sortable="true"><fmt:formatDate value="${row.checkOut }" pattern="dd/MM/yyyy" /></display:column>
		
		<spring:message code="request.smoker" var="smokerHeader" />
		<display:column title="${smokerHeader}">
			<jstl:if test="${row.smoker==true}">
				<spring:message code="request.smoker.yes" var="yesH" />
				<jstl:out value="${yesH}"/>
			</jstl:if>
			<jstl:if test="${row.smoker==false}">
				<spring:message code="request.smoker.no" var="noH" />
				<jstl:out value="${noH}"/>
			</jstl:if>
		</display:column>
		<spring:message code="request.creditCard.number" var="creditCardnumberHeader" />
		<display:column property="creditCard.number" title="${creditCardnumberHeader}" sortable="false" />
	
		<spring:message code="request.status" var="statusHeader" />
		<display:column property="status" title="${statusHeader}" sortable="false"  />
		
		<display:column>
				<jstl:if test="${row.status == 'PENDING'}">
					<input type="button" name="accept" value="<spring:message code="request.accept" />"
			onclick="javascript: window.location.replace('request/accept.do?requestId=${row.id}')" />
					<input type="button" name="deny" value="<spring:message code="request.deny" />"
			onclick="javascript: window.location.replace('request/deny.do?requestId=${row.id}')" />
				</jstl:if>
		
		</display:column>
		<spring:message code="request.tenant" var="tenantHeader"/>
		<display:column title="${tenantHeader}">
			<a href="tenant/display.do?tenantId=${row.tenant.id}"><jstl:out value="${row.tenant.name}" /></a>
		</display:column>
	</display:table>
</jstl:if>	