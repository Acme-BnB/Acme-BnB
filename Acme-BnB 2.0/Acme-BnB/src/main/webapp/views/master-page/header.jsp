<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="Acme BnB Co., Inc." />
</div>

<div>
	<ul id="jMenu">
		<li><a href="property/browse.do"><spring:message code="master.page.browseProperty" /></a></li>
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
<%-- 					<li><a href="auditor/register.do"><spring:message code="master.page.administrator.register" /></a></li>				 --%>
						<li><a href="administrator/attribute/list.do"><spring:message code="master.page.administrator.attribute.list" /></a></li>
						<li><a href="administrator/attribute/create.do"><spring:message code="master.page.administrator.attribute.create" /></a></li>			
				
				</ul>
				<li><a href="administrator/dashboard.do"><spring:message code="master.page.administrator.dashboard" /></a></li>	
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv"><spring:message	code="master.page.customer" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="customer/action-1.do"><spring:message code="master.page.customer.action.1" /></a></li>
					<li><a href="customer/action-2.do"><spring:message code="master.page.customer.action.2" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('LESSOR')">
			<li><a class="fNiv"><spring:message	code="master.page.lessor" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="lessor/property/list.do"><spring:message code="master.page.lessor.property.list" /></a></li>
					<li><a href="lessor/property/create.do"><spring:message code="master.page.lessor.property.create" /></a></li>	
					<li><a href="request/list.do"><spring:message code="master.page.lessor.request.list" /></a></li>		
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('TENANT')">
			<li><a class="fNiv"><spring:message	code="master.page.tenant" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="tenant/finder/display.do"><spring:message code="master.page.tenant.finder.display" /></a></li>			
				</ul>
			</li>
		</security:authorize>
		
		
		
		<security:authorize access="isAnonymous()">
			
			
			<li><a class="fNiv" href="lessor/register.do"><spring:message code="master.page.lessor.register"/></a></li>
			<li><a class="fNiv" href="tenant/register.do"><spring:message code="master.page.tenant.register"/></a></li>
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="profile/action-1.do"><spring:message code="master.page.profile.action.1" /></a></li>
					<li><a href="profile/action-2.do"><spring:message code="master.page.profile.action.2" /></a></li>
					<li><a href="profile/action-3.do"><spring:message code="master.page.profile.action.3" /></a></li>					
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

