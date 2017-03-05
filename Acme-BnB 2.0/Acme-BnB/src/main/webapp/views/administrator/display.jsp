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
				<img src="${administrator.picture}" width="100" height="100" >
				
			</td>
		</tr>
		<tr>
			<th><spring:message code="administrator.name"/></th>
			<th><spring:message code="administrator.surname"/></th>
			
		</tr>
		<tr>
			<td><jstl:out value="${administrator.name}"/></td>
			<td><jstl:out value="${administrator.surname}"/></td>
		</tr>
		<tr>
			<th><spring:message code="administrator.email"/></th>
			<th><spring:message code="administrator.phone"/></th>
		</tr>
		<tr>
			<td><jstl:out value="${administrator.email}"/></td>
			<td><jstl:out value="${administrator.phone}"/></td>
		</tr>
	</tbody>
</table>
	
<display:table name="socialIdentities"
	id="row"
	class="displaytag"
	pagesize="10" >
	
	<security:authorize access="isAuthenticated()">
						
	<spring:message code="socialIdentity.nick" var="nickHeader" />
	<display:column property="nick" title="${nickHeader}" sortable="true"/>
	
	<spring:message code="socialIdentity.socialNetwork" var="socialNetworkHeader" />
	<display:column property="socialNetwork" title="${socialNetworkHeader}" sortable="true"/>
	
	<spring:message code="socialIdentity.profileURL" var="profileURLHeader" />
	<display:column property="profileURL" title="${profileURLHeader}" sortable="false"/>		
		
	</security:authorize>

</display:table>