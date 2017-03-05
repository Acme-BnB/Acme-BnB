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

<display:table name="lessor" id="row" class="displaytag"  pagesize="5" requestURI="${requestURI}" >
	
	<security:authorize access="hasRole('ADMIN')">
						
	<spring:message code="administrator.lessor.username" var="nameHeader" />
	<display:column property="userAccount.username" title="${nameHeader}" sortable="true"/>
	
	<display:column>
		<a href="administrator/dashboardLessor.do?lessorId=${row.id}"><spring:message code="administrator.dasboard.lessor" /></a>
	</display:column>			
		
	</security:authorize>

</display:table>