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
<security:authorize access="hasRole('ADMIN')">

<!-- RECIPE PART -->
<h1><spring:message code="administrator.requestPart"/></h1>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.adrL" /></legend>
		<table id="adrL" class="table">
			<tr>
				<th><spring:message code="administrator.dashboard.accepted"/></th>
				<jstl:if test="${not empty adrL }">
					<td><jstl:out value="${adrL.get(0) }" /></td>
				</jstl:if>
			</tr>
			<tr>
				<th><spring:message code="administrator.dashboard.denied"/></th>
				<jstl:if test="${not empty adrL }">
					<td><jstl:out value="${adrL.get(1) }" /></td>
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.adrT" /></legend>
		<table id="adrT" class="table">
			<tr>
				<th><spring:message code="administrator.dashboard.accepted"/></th>
				<jstl:if test="${not empty adrT }">
					<td><jstl:out value="${adrT.get(0) }" /></td>
				</jstl:if>
			</tr>
			<tr>
				<th><spring:message code="administrator.dashboard.denied"/></th>
				<jstl:if test="${not empty adrT }">
					<td><jstl:out value="${adrT.get(1) }" /></td>
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<display:table name="lamR" id="row" class="displaytag" pagesize="5">
		<spring:message code="administrator.lamR" var="name"/>
		<display:column title="${name}" property="userAccount.username"/>
	</display:table>
</div>

<div>
	<display:table name="ldmR" id="row" class="displaytag" pagesize="5">
		<spring:message code="administrator.ldmR" var="name"/>
		<display:column title="${name}" property="userAccount.username"/>
	</display:table>
</div>

<div>
	<display:table name="lpmR" id="row" class="displaytag" pagesize="5">
		<spring:message code="administrator.lpmR" var="name"/>
		<display:column title="${name}" property="userAccount.username"/>
	</display:table>
</div>

<div>
	<display:table name="tamR" id="row" class="displaytag" pagesize="5">
		<spring:message code="administrator.tamR" var="name"/>
		<display:column title="${name}" property="userAccount.username"/>
	</display:table>
</div>

<div>
	<display:table name="tdmR" id="row" class="displaytag" pagesize="5">
		<spring:message code="administrator.tdmR" var="name"/>
		<display:column title="${name}" property="userAccount.username"/>
	</display:table>
</div>

<div>
	<display:table name="tpmR" id="row" class="displaytag" pagesize="5">
		<spring:message code="administrator.tpmR" var="name"/>
		<display:column title="${name}" property="userAccount.username"/>
	</display:table>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.mmrL" /></legend>
		<div>
			<display:table name="marL" id="row" class="displaytag" pagesize="5">
				<spring:message code="administrator.marL" var="name"/>
				<display:column title="${name}" >
					<jstl:out value="${row.name}(${row.userAccount.username})"></jstl:out>
				</display:column>
			</display:table>
		</div>
		<div>
			<display:table name="mirL" id="row" class="displaytag" pagesize="5">
				<spring:message code="administrator.mirL" var="name"/>
				<display:column title="${name}" >
					<jstl:out value="${row.name}(${row.userAccount.username})"></jstl:out>
				</display:column>
			</display:table>
		</div>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.mmrT" /></legend>
		<div>
			<display:table name="marT" id="row" class="displaytag" pagesize="5">
				<spring:message code="administrator.marT" var="name"/>
				<display:column title="${name}" >
					<jstl:out value="${row.name}(${row.userAccount.username})"></jstl:out>
				</display:column>
			</display:table>
		</div>
		<div>
			<display:table name="mirT" id="row" class="displaytag" pagesize="5">
				<spring:message code="administrator.mirT" var="name"/>
				<display:column title="${name}" >
					<jstl:out value="${row.name}(${row.userAccount.username})"></jstl:out>
				</display:column>
			</display:table>
		</div>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.ammrF" /></legend>
		<table id="ammrF" class="table">
			<tr>
				<th><spring:message code="administrator.dashboard.avg"/></th>
				<jstl:if test="${not empty ammrF }">
					<td><jstl:out value="${ammrF.get(0) }" /></td>
				</jstl:if>
			</tr>
			<tr>
				<th><spring:message code="administrator.dashboard.min"/></th>
				<jstl:if test="${not empty ammrF }">
					<td><jstl:out value="${ammrF.get(1) }" /></td>
				</jstl:if>
			</tr>
			<tr>
				<th><spring:message code="administrator.dashboard.max"/></th>
				<jstl:if test="${not empty ammrF }">
					<td><jstl:out value="${ammrF.get(2) }" /></td>
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.mamAP" /></legend>
		<table id="mamAP" class="table">
			<tr>
				<th><spring:message code="administrator.dashboard.min"/></th>
				<jstl:if test="${not empty mamAP }">
					<td><jstl:out value="${mamAP.get(0) }" /></td>
				</jstl:if>
			</tr>
			<tr>
				<th><spring:message code="administrator.dashboard.avg"/></th>
				<jstl:if test="${not empty mamAP }">
					<td><jstl:out value="${mamAP.get(1) }" /></td>
				</jstl:if>
			</tr>
			<tr>
				<th><spring:message code="administrator.dashboard.max"/></th>
				<jstl:if test="${not empty mamAP }">
					<td><jstl:out value="${mamAP.get(2) }" /></td>
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<display:table name="asd" id="row" class="displaytag" pagesize="5">
		<spring:message code="administrator.asd" var="name"/>
		<display:column title="${name}" property="name"/>
	</display:table>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.mamSi" /></legend>
		<table id="mamSi" class="table">
			<tr>
				<th><spring:message code="administrator.dashboard.min"/></th>
				<jstl:if test="${not empty mamSi }">
					<td><jstl:out value="${mamSi.get(0) }" /></td>
				</jstl:if>
			</tr>
			<tr>
				<th><spring:message code="administrator.dashboard.avg"/></th>
				<jstl:if test="${not empty mamSi }">
					<td><jstl:out value="${mamSi.get(1) }" /></td>
				</jstl:if>
			</tr>
			<tr>
				<th><spring:message code="administrator.dashboard.max"/></th>
				<jstl:if test="${not empty mamSi }">
					<td><jstl:out value="${mamSi.get(2) }" /></td>
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.mamIi" /></legend>
		<table id="mamIi" class="table">
			<tr>
				<th><spring:message code="administrator.dashboard.min"/></th>
				<jstl:if test="${not empty mamIi }">
					<td><jstl:out value="${mamIi.get(0) }" /></td>
				</jstl:if>
			</tr>
			<tr>
				<th><spring:message code="administrator.dashboard.avg"/></th>
				<jstl:if test="${not empty mamIi }">
					<td><jstl:out value="${mamIi.get(1) }" /></td>
				</jstl:if>
			</tr>
			<tr>
				<th><spring:message code="administrator.dashboard.max"/></th>
				<jstl:if test="${not empty mamIi }">
					<td><jstl:out value="${mamIi.get(2) }" /></td>
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.aMi" /></legend>
		<table id="aMi" class="table">
			<tr>
				<th><spring:message code="administrator.dashboard.aMi"/></th>
				<jstl:if test="${not empty aMi }">
					<td><jstl:out value="${aMi }" /></td>
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>

<div>
	<fieldset><legend class="dashLegend"><spring:message code="administrator.oMVSzL" /></legend>
		<table id="oMVSzL" class="table">
			<tr>
				<th><spring:message code="administrator.dashboard.oM"/></th>
				<jstl:if test="${not empty oMVSzL }">
					<td><jstl:out value="${oMVSzL.get(0) }" /></td>
				</jstl:if>
			</tr>
			<tr>
				<th><spring:message code="administrator.dashboard.zL"/></th>
				<jstl:if test="${not empty oMVSzL }">
					<td><jstl:out value="${oMVSzL.get(1) }" /></td>
				</jstl:if>
			</tr>
		</table>
	</fieldset>
</div>
</security:authorize>