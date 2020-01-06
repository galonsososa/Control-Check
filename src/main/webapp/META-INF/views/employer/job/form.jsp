<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form >

	<jstl:if test="${finalMode == 'true' }">
		<acme:form-textbox code="employer.job.form.label.reference" path="reference" readonly="true"/>
		<acme:form-textbox code="employer.job.form.label.title" path="title" readonly="true"/>
		<acme:form-moment code="employer.job.form.label.deadline" path="deadline" readonly="true"/>
		<acme:form-money code="employer.job.form.label.salary" path="salary" readonly="true"/>
		<acme:form-url code="employer.job.form.label.moreInfo" path="moreInfo" readonly="true"/>
		<acme:form-textarea code="employer.job.form.label.description" path="description" readonly="true"/>
		<jstl:if test="${not empty challengeDescription}">
		<acme:form-panel code="worker.job.form.panel.challenge">
			<acme:form-textarea code="worker.job.form.label.challengeDescription" path="challengeDescription" readonly="true" />
			<acme:form-url code="worker.job.form.label.challengeMoreInfo" path="challengeMoreInfo" readonly="true" />	
		</acme:form-panel>
	</jstl:if>
		<jstl:if test="${command != 'create'}">
			<acme:form-return code="employer.job.form.button.duties" action="/employer/duty/list-by-job?id=${id}"/>
		</jstl:if>
	</jstl:if>
	<jstl:if test="${finalMode != 'true'}">
		<acme:form-textbox code="employer.job.form.label.reference" path="reference" placeholder="EMP1-JOB1"/>
		<acme:form-textbox code="employer.job.form.label.title" path="title" />
		<acme:form-moment code="employer.job.form.label.deadline" path="deadline" />
		<acme:form-money code="employer.job.form.label.salary" path="salary" />
		<acme:form-url code="employer.job.form.label.moreInfo" path="moreInfo" />
		<acme:form-textarea code="employer.job.form.label.description" path="description" />
		
		
		<acme:form-panel code="worker.job.form.panel.challenge">
			<acme:form-textarea code="worker.job.form.label.challengeDescription" path="challengeDescription" />
			<acme:form-url code="worker.job.form.label.challengeMoreInfo" path="challengeMoreInfo"/>	
		</acme:form-panel>
		
		<jstl:if test="${command == 'show' || command == 'update' }">
			<acme:form-checkbox code="employer.job.form.label.finalMode" path="finalMode"/>
			<acme:form-return code="employer.job.form.button.duties" action="/employer/duty/list-by-job?id=${id}"/>
		</jstl:if>
		
		<acme:form-submit method="get" test="${command == 'show'}"
			code = "employer.job.form.button.createDuty"
			action = "/employer/duty/create?jobId=${id}"/>
	</jstl:if>
	<br><br>
	<acme:form-submit test="${command == 'show' && finalMode=='false'}"
		code = "employer.job.form.button.update"
		action = "/employer/job/update"/>
	<acme:form-submit test="${command == 'update' && finalMode=='false'}" 
		code="employer.job.form.button.update" 
		action="/employer/job/update"/>
	<acme:form-submit test="${command == 'show' }" code="employer.job.form.button.delete"
		action="/employer/job/delete" />
	<acme:form-submit test="${command == 'delete' }" code="employer.job.form.button.delete"
		action="/employer/job/delete" />
	<acme:form-submit test="${command == 'create' }" code="employer.job.form.button.create"
		action="/employer/job/create" />
		
	<acme:form-return code="employer.job.form.button.return" />
	
</acme:form>

