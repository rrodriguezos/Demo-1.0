<%--

 * register.jsp

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
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>



<form:form requestURI="${requestUri}" modelAttribute="developerForm">
	<fieldset>
		<legend align="left">
			<spring:message code="developer.userAccount" />
		</legend>
		<acme:textbox code="developer.userAccount.username" path="username" />
		<acme:password code="developer.userAccount.password" path="password" />
		<acme:password code="developer.userAccount.repeatPassword"
			path="confirmPassword" />
	</fieldset>

	<fieldset>
		<legend align="left">
			<spring:message code="developer.personalInfo" />
		</legend>
		<acme:textbox code="developer.name" path="name" />
		<acme:textbox code="developer.surname" path="surname" />
		<acme:textbox code="developer.email" path="emailAddress" />
		<acme:textbox code="developer.phone" path="phone" />

	</fieldset>

	<p>
		<acme:checkbox code="developer.acceptConditions" path="accept" />

		<a href="legalTerms/legalTerms.do"> <spring:message
				code="developer.registration" />
		</a> <br> <br>

		<acme:submit name="save" code="developer.edit.save" />

		<input type="button" name="cancel"
			value="<spring:message code="return"/>"
			onclick="javascript: window.location.replace('')" />
</form:form>

