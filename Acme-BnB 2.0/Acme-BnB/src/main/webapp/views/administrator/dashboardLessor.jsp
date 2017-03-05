<%--
 * dashboard.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%> 

<!-- RECIPE PART -->
<h1><spring:message code="administrator.requestPart2"/></h1>

<security:authorize access="hasRole('ADMIN')">
	<div>
		<fieldset><legend class="dashLegend"><spring:message code="administrator.psA" /></legend>
			<display:table name="psA" id="row" class="displaytag" pagesize="5" requestURI="${requestURI}" >							
				<spring:message code="property.name" var="nameHeader" />
				<display:column property="name" title="${nameHeader}" sortable="true"/>
				
				<spring:message code="property.rate" var="rateHeader" />
				<display:column property="rate" title="${rateHeader}" sortable="true"/>
				
				<spring:message code="property.description" var="descriptionHeader" />
				<display:column property="description" title="${descriptionHeader}" sortable="false"/>
				
				<spring:message code="property.address" var="addressHeader"/>
				<display:column property="address" title="${addressHeader}" sortable="false"/>
			</display:table>
		</fieldset>
	</div>
	
	<div>
		<fieldset><legend class="dashLegend"><spring:message code="administrator.psR" /></legend>
			<display:table name="psR" id="row" class="displaytag" pagesize="5" requestURI="${requestURI}" >							
				<spring:message code="property.name" var="nameHeader" />
				<display:column property="name" title="${nameHeader}" sortable="true"/>
				
				<spring:message code="property.rate" var="rateHeader" />
				<display:column property="rate" title="${rateHeader}" sortable="true"/>
				
				<spring:message code="property.description" var="descriptionHeader" />
				<display:column property="description" title="${descriptionHeader}" sortable="false"/>
				
				<spring:message code="property.address" var="addressHeader"/>
				<display:column property="address" title="${addressHeader}" sortable="false"/>
			</display:table>
		</fieldset>
	</div>
	
	<div>
		<fieldset><legend class="dashLegend"><spring:message code="administrator.psAp" /></legend>
			<display:table name="psAp" id="row" class="displaytag" pagesize="5" requestURI="${requestURI}" >							
				<spring:message code="property.name" var="nameHeader" />
				<display:column property="name" title="${nameHeader}" sortable="true"/>
				
				<spring:message code="property.rate" var="rateHeader" />
				<display:column property="rate" title="${rateHeader}" sortable="true"/>
				
				<spring:message code="property.description" var="descriptionHeader" />
				<display:column property="description" title="${descriptionHeader}" sortable="false"/>
				
				<spring:message code="property.address" var="addressHeader"/>
				<display:column property="address" title="${addressHeader}" sortable="false"/>
			</display:table>
		</fieldset>
	</div>
	
	<div>
		<fieldset><legend class="dashLegend"><spring:message code="administrator.psDn" /></legend>
			<display:table name="psDn" id="row" class="displaytag" pagesize="5" requestURI="${requestURI}" >							
				<spring:message code="property.name" var="nameHeader" />
				<display:column property="name" title="${nameHeader}" sortable="true"/>
				
				<spring:message code="property.rate" var="rateHeader" />
				<display:column property="rate" title="${rateHeader}" sortable="true"/>
				
				<spring:message code="property.description" var="descriptionHeader" />
				<display:column property="description" title="${descriptionHeader}" sortable="false"/>
				
				<spring:message code="property.address" var="addressHeader"/>
				<display:column property="address" title="${addressHeader}" sortable="false"/>
			</display:table>
		</fieldset>
	</div>
	
	<div>
		<fieldset><legend class="dashLegend"><spring:message code="administrator.psPn" /></legend>
			<display:table name="psPn" id="row" class="displaytag" pagesize="5" requestURI="${requestURI}" >							
				<spring:message code="property.name" var="nameHeader" />
				<display:column property="name" title="${nameHeader}" sortable="true"/>
				
				<spring:message code="property.rate" var="rateHeader" />
				<display:column property="rate" title="${rateHeader}" sortable="true"/>
				
				<spring:message code="property.description" var="descriptionHeader" />
				<display:column property="description" title="${descriptionHeader}" sortable="false"/>
				
				<spring:message code="property.address" var="addressHeader"/>
				<display:column property="address" title="${addressHeader}" sortable="false"/>
			</display:table>
		</fieldset>
	</div>
</security:authorize>