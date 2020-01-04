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
	<acme:form-hidden path="XXXXCreated"/>

	<acme:form-textbox code="worker.job.form.label.reference" path="reference" readonly="true"/>
	<acme:form-textbox code="worker.job.form.label.title" path="title" readonly="true"/>
	<acme:form-moment code="worker.job.form.label.deadline" path="deadline" readonly="true"/>
	<acme:form-money code="worker.job.form.label.salary" path="salary" readonly="true"/>
	<acme:form-url code="worker.job.form.label.moreInfo" path="moreInfo" readonly="true"/>
	<acme:form-textarea code="worker.job.form.label.description" path="description" readonly="true"/>
	<acme:form-return code="worker.job.form.button.duties" action="/worker/duty/list-by-job?id=${id}"/>
	<jstl:if test="${XXXXCreated == 'true' }">
		<acme:form-return code="worker.job.form.button.XXXX" action="/worker/xxxx/show?id=${id}"/>
	</jstl:if>
	<br><br>
	<acme:form-submit method = "get"
		code="worker.job.form.button.update" 
		action="/worker/application/create?jobId=${id}"/>
		
	<acme:form-return code="worker.job.form.button.return" />
	
</acme:form>

