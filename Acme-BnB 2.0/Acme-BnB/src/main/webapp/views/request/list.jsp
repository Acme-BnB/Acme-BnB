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

<display:table pagesize="5" class="displaytag" name="requests" id="row">

	<spring:message code="request.checkIn" var="checkInHeader" />
	<display:column title="${checkInHeader}"	sortable="true"><fmt:formatDate value="${row.checkIn }" pattern="dd/MM/yyyy HH:mm" /></display:column>
	
	<spring:message code="request.checkOut" var="checkOutHeader" />
	<display:column title="${checkOutHeader}"	sortable="true"><fmt:formatDate value="${row.checkOut }" pattern="dd/MM/yyyy HH:mm" /></display:column>
	
	<spring:message code="request.smoker" var="smokereader" />
	<display:column property="smoker" title="${smokerHeader}" sortable="true" />

	<spring:message code="request.creditCard.number" var="creditCard.numberHeader" />
	<display:column property="creditCard.number" title="${creditCard.numberHeader}" sortable="false" />

	<spring:message code="request.status" var="statusHeader" />
	<display:column property="status" title="${statusHeader}" sortable="false"  />
	
	<display:column>
		<jstl:if test="${lessor.userAccount.username == pageContext.request.remoteUser}">
			<jstl:if test="${row.status == 'PENDING'}">
				<input type="button" name="addComment" value="Boton"/>
				<input type="button" name="addComment" value="Boton"/>
			</jstl:if>
		</jstl:if>	
	</display:column>

</display:table>