<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.job.form.label.reference" path="reference" />
	<acme:form-textbox code="authenticated.job.form.label.title" path="title" />
	<acme:form-moment code="authenticated.job.form.label.deadline" path="deadline" />
	<acme:form-money code="authenticated.job.form.label.salary" path="salary" />
	<acme:form-url code="authenticated.job.form.label.moreInfo" path="moreInfo" />
	<acme:form-textarea code="authenticated.job.form.label.description" path="description" />
	
	<acme:form-return code="authenticated.job.form.button.duties" action="/authenticated/duty/list-by-job?id=${id}"/>
	
	
	<acme:form-return code="authenticated.job.form.button.return" />
	
</acme:form>