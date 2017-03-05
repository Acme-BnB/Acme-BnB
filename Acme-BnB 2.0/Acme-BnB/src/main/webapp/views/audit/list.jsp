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
<security:authorize access="hasRole('AUDITOR')">
<display:table pagesize="5" class="displaytag" keepStatus="true" name="audits" requestURI="${requestURI}" id="row">
	
	<!-- Action links -->
	
	<!-- Attributes -->
	
	<spring:message code="audit.text" var="text"/>
	<display:column property="text" title="${text}" sortable="false" />

	<spring:message code="audit.writtenMoment" var="writtenMomentHeader" />
	<display:column property="writtenMoment" title="${writtenMomentHeader}" sortable="true" />
	
	<spring:message code="audit.property" var="property" />
	<display:column property="property.name" title="${property}" sortable="true" />
	
	<spring:message code="audit.draft" var="draft" />
	<display:column property="draft" title="${draft}" sortable="true" />
	
	
		<display:column>
				<jstl:if test="${row.draft == true}">
					<input type="button" name="final" value="<spring:message code="audit.final" />"
			onclick="javascript: window.location.replace('auditor/audit/final.do?auditId=${row.id}')" />
				</jstl:if>
		
		</display:column>
		
	<display:column>
		<input type="button" name="view" value="<spring:message code="audit.view" />"
			onclick="javascript: window.location.replace('audit/display.do?auditId=${row.id}')" />
	</display:column>
	
		
	</display:table>
</security:authorize>