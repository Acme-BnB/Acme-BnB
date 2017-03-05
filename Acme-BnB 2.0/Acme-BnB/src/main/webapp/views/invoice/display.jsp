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


<table id="row" class="table">
	<tbody>
		<tr>
			<th>
				<spring:message code="invoice.date" />
			</th>
			<td>
				<jstl:out value="${invoice.issuedMoment }" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code="invoice.detail" />
			</th>
			<td>
				<jstl:out value="${invoice.detail }" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code="invoice.vat" />
			</th>
			<td>
				<jstl:out value="${invoice.vatNumber }" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code="invoice.tenant" />
			</th>
			<td>
				<jstl:out value="${invoice.information }" />
			</td>
		</tr>
		<tr>
			<th>
				<spring:message code="invoice.ammount" />
			</th>
			<td>
				<jstl:out value="${invoice.amountDue }" />
			</td>
		</tr>
	</tbody>
</table>