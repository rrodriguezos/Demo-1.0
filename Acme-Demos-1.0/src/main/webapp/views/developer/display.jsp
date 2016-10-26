<%--
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:jstlOut code="demo.title" value="${demo.title }" />
<acme:jstlOut code="demo.momentReleased" value="${demo.description }" />

<h2>
	<spring:message code="demo.comments" />
</h2>

<display:table name="descriptions" id="row" pagesize="5"
	requestURI="${requestUri}" class="displaytag">

	<spring:message code="description.text" var="text" />
	<display:column title="${text}">

	</display:column>
	<spring:message code="isoCode.text" var="isoCode" />
	<display:column title="${isoCode}">

	</display:column>

	<spring:message code="description.display" var="display" />
	<display:column title="${display}">
		<input type="button"
			value="<spring:message code="description.display" />"
			onclick="javascript: window.location.assign('description/display.do?descriptionId=${row.id}')" />
	</display:column>

</display:table>

<security:authorize access="hasRole('DEVELOPER')">
	<jstl:if test="${mydemo == true}">

		<input type="button" value="<spring:message code="demo.edit" />"
			onclick="javascript: window.location.assign('demo/developer/edit.do?demoId=${demo.id}')" />

	</jstl:if>
</security:authorize>
<br>
<h2>
	<spring:message code="demo.comments" />
</h2>

<display:table name="comments" id="row" pagesize="5"
	requestURI="${requestUri}" class="displaytag">

	<spring:message code="comment.text" var="textHeader" />
	<display:column property="text" title="${textHeader}" />

	<spring:message code="comment.stars" var="starsHeader" />
	<display:column property="stars" title="${starsHeader}" />

	<spring:message code="comment.moment" var="momentHeader" />
	<display:column property="moment" title="${momentHeader}"
		format="{0,date,dd/MM/yyyy HH:mm}" sortable="true" />

	<spring:message code="comment.author" var="author" />
	<display:column title="${actor}">
		<jstl:out value="${row.getActor().getName()}" />
		<jstl:out value="${row.getActor().getSurname() }" />
	</display:column>

	<spring:message code="comment.display" var="display" />
	<display:column title="${display}">
		<input type="button" value="<spring:message code="comment.display" />"
			onclick="javascript: window.location.assign('comment/display.do?commentId=${row.id}')" />
	</display:column>

</display:table>
<br>

<input type="button" value="<spring:message code="comment.create" />"
	onclick="javascript: window.location.assign('comment/actor/edit.do?commentableId=${demo.id}')" />



<input type="button" name="cancel"
	value="<spring:message code="demo.cancel"/>"
	onclick="javascript: window.history.back()" />
