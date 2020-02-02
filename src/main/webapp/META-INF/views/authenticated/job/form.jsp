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
	
	<jstl:if test="${not empty pust}">
		<acme:form-panel code="authenticated.job.form.panel.challenge">
			<acme:form-textarea code="authenticated.job.form.label.pust" path="pust" />
			<jstl:if test="${not empty bow}">
				<acme:form-url code="authenticated.job.form.label.bow" path="bow" />	
			</jstl:if>
		</acme:form-panel>
	</jstl:if>
	
	<acme:form-return code="authenticated.job.form.button.duties" action="/authenticated/duty/list-by-job?id=${id}"/>
	
	
	<acme:form-return code="authenticated.job.form.button.return" />
	
</acme:form>