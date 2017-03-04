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

			
<display:table name="${property.values}" id="row" class="displaytag" pagesize="5" requestURI="${requestURI}" >
	
	<spring:message code="property.attribute" var="attributeHeader" />
	<display:column property="attribute.name" title="${attributeHeader}" sortable="false"/>
	
	<spring:message code="property.text" var="textHeader" />
	<display:column property="text" title="${textHeader}" sortable="false"/>

	<security:authorize access="hasRole('LESSOR')">
		<jstl:if test="${property.lessor.userAccount.username == pageContext.request.remoteUser}">
			<display:column>
				<input type="button" name="editAttribute" value="<spring:message code="property.delete" />"
					onclick="javascript: window.location.replace('lessor/value/delete.do?valueId=${row.id}')" />
			</display:column>
		</jstl:if>
	</security:authorize>

</display:table>
	
<security:authorize access="hasRole('LESSOR')">
	<jstl:if test="${property.lessor.userAccount.username == pageContext.request.remoteUser}">

		<input type="button" name="addAttribute" value="<spring:message code="property.addAttribute" />"
				onclick="javascript: window.location.replace('lessor/value/create.do?propertyId=${property.id}')" />
	</jstl:if>
</security:authorize>
	