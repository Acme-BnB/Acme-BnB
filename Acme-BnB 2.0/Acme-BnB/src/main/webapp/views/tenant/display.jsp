<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<table id="row" class="table">
	<tbody>
		<tr>
			<td>
				<img src="${tenant.picture}" width="100" height="100" >
				
			</td>
		</tr>
		<tr>
			<th><spring:message code="tenant.name"/></th>
			<th><spring:message code="tenant.surname"/></th>
			
		</tr>
		<tr>
			<td><jstl:out value="${tenant.name}"/></td>
			<td><jstl:out value="${tenant.surname}"/></td>
		</tr>
		<tr>
			<th><spring:message code="tenant.email"/></th>
			<th><spring:message code="tenant.phone"/></th>
		</tr>
		<tr>
			<td><jstl:out value="${tenant.email}"/></td>
			<td><jstl:out value="${tenant.phone}"/></td>
		</tr>
	</tbody>
</table>
			
<display:table pagesize="10" class="displaytag" keepStatus="true" name="comments" id="row">	
	<spring:message code="tenant.comment.title" var="titleHeader"/>
	<display:column title="${titleHeader }" property="title"/>
	
	<spring:message code="tenant.comment.postedMoment" var="postedMomentHeader"/>
	<display:column title="${postedMomentHeader}" sortable="true"><fmt:formatDate value="${row.postedMoment }" pattern="dd/MM/yyyy HH:mm" /></display:column>
	
	<spring:message code="tenant.comment.text" var="textHeader"/>
	<display:column title="${textHeader }" property="text"/>
	
	<spring:message code="tenant.comment.stars" var="starsHeader"/>
	<display:column title="${starsHeader }" property="stars"/>
</display:table>

<security:authorize access="hasAnyRole('LESSOR','TENANT')">
	<input type="button" name="addComment"
			value="Boton"/>
<br/>
</security:authorize>