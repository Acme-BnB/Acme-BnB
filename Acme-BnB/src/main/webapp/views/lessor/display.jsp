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
				<img src="${lessor.picture}" width="100" height="100" >
				
			</td>
		</tr>
		<tr>
			<th><spring:message code="lessor.name"/></th>
			<th><spring:message code="lessor.surname"/></th>
			
		</tr>
		<tr>
			<td><jstl:out value="${lessor.name}"/></td>
			<td><jstl:out value="${lessor.surname}"/></td>
		</tr>
		<tr>
			<th><spring:message code="lessor.email"/></th>
			<th><spring:message code="lessor.phone"/></th>
		</tr>
		<tr>
			<td><jstl:out value="${lessor.email}"/></td>
			<td><jstl:out value="${lessor.phone}"/></td>
		</tr>
	</tbody>
</table>
			
	<jstl:if test="${lessor.userAccount.username == pageContext.request.remoteUser}">
	
		<table id="row" class="table">
			<tbody>
				<tr>
					<th><spring:message code="lessor.creditCard.brandName"/></th>
					<td><jstl:out value="${lessor.creditCard.brandName}"/></td>
				</tr>
				<tr>
					<th><spring:message code="lessor.creditCard.number"/></th>
					<td><jstl:out value="${lessor.creditCard.number}"/></td>
				</tr>
				<tr>
					<th><spring:message code="lessor.creditCard.expirationMonth"/></th>
					<td><jstl:out value="${lessor.creditCard.expirationMonth}"/></td>
				</tr>
				<tr>
					<th><spring:message code="lessor.creditCard.expirationYear"/></th>
					<td><jstl:out value="${lessor.creditCard.expirationYear}"/></td>
				</tr>
				<tr>
					<th><spring:message code="lessor.creditCard.cvv"/></th>
					<td><jstl:out value="${lessor.creditCard.cvv}"/></td>
				</tr>
				<tr>
					<th><spring:message code="lessor.feeAmount"/></th>
					<td><jstl:out value="${lessor.feeAmount}"/></td>
				</tr>	
		</table>
	</jstl:if>	
	
	<display:table name="socialIdentities"
		 id="row"
		 class="displaytag"
		 pagesize="10" 
		 requestURI="${requestURI}">
		 
	<security:authorize access="isAuthenticated()">
		      
		 <spring:message code="socialIdentity.nick" var="nickHeader" />
		 <display:column property="nick" title="${nickHeader}" sortable="true"/>
		 
		 <spring:message code="socialIdentity.socialNetwork" var="socialNetworkHeader" />
		 <display:column property="socialNetwork" title="${socialNetworkHeader}" sortable="true"/>
		 
		 <spring:message code="socialIdentity.profileURL" var="profileURLHeader" />
		 <display:column property="profileURL" title="${profileURLHeader}" sortable="false"/>  
		  
	</security:authorize>

</display:table>
	
	
<display:table pagesize="10" class="displaytag" keepStatus="true" name="comments" id="row" requestURI="${requestURI}">	
	<spring:message code="lessor.comment.title" var="titleHeader"/>
	<display:column title="${titleHeader }" property="title"/>
	
	<spring:message code="lessor.comment.postedMoment" var="postedMomentHeader"/>
	<display:column title="${postedMomentHeader}" sortable="true"><fmt:formatDate value="${row.postedMoment }" pattern="dd/MM/yyyy HH:mm" /></display:column>
	
	<spring:message code="lessor.comment.text" var="textHeader"/>
	<display:column title="${textHeader }" property="text"/>
	
	<spring:message code="lessor.comment.stars" var="starsHeader"/>
	<display:column title="${starsHeader }" property="stars"/>
</display:table>

<security:authorize access="hasAnyRole('LESSOR','TENANT')">
	<input type="button" name="comment" value="<spring:message code="lessor.comment" />"
			onclick="javascript: window.location.replace('commentator/comment/create.do?commentableId=${lessor.id}')" />
<br/>
</security:authorize>