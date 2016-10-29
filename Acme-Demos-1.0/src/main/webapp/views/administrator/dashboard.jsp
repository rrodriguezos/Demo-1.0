<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<!-- Average number of comments per demo -->
<p>
	<h4><spring:message code="administrator.dashboard.averageCommentsPerDemo" /></h4>
</p>
<jstl:out value="${avgNumberCommentsPerDemo}" />
<br>

<!-- Demos that have got at least 25% more comments than the average -->
<p>
	<h4><spring:message code="administrator.dashboard.demos25PercentageMoreCommentsThanAvg" /></h4>
</p>
<display:table name="demos25PercentageMoreCommentsThanAvg" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag" >
	
	<display:column property="title" titleKey="demo.title" />
	
	<display:column property="momentReleased" titleKey="demo.momentReleased" format="{0,date,dd/MM/yyyy HH:mm}" sortable="true" />
	
	<display:column titleKey="demo.display" >
			<input type="button" value="<spring:message code="demo.display" />" 
					onclick="javascript: window.location.assign('demo/display.do?demoId=${row.id}')" />
	</display:column>
	
	<display:column titleKey="demo.descriptions" >
			<input type="button" value="<spring:message code="demo.descriptions" />" 
					onclick="javascript: window.location.assign('description/list.do?demoId=${row.id}')" />
	</display:column>
	
	<display:column titleKey="demo.resources" >
			<input type="button" value="<spring:message code="demo.resources" />" 
					onclick="javascript: window.location.assign('resource/list.do?demoId=${row.id}')" />
	</display:column>
	
	<display:column titleKey="demo.comments" >
			<input type="button" value="<spring:message code="demo.comments" />" 
					onclick="javascript: window.location.assign('comment/list.do?demoId=${row.id}')" />
	</display:column>
</display:table>
<br>


<!-- Developers whose demos have got more comments than the average -->
<p>
	<h4><spring:message code="administrator.dashboard.developersMoreCommentsThanAvg" /></h4>
</p>
<display:table name="developersMoreCommentsThanAvg" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag" >
	
	<display:column property="name" titleKey="developer.name" />
	
	<display:column property="surname" titleKey="developer.surname" />
	
	<display:column property="phone" titleKey="developer.phone" />
	
	<display:column property="emailAddress" titleKey="developer.email" />
	
</display:table>



