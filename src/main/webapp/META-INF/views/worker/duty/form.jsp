<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="jobId"/>
	<acme:form-hidden path="jobFinalMode"/>

	<acme:form-textbox code="worker.duty.form.label.title" path="title" />
	<acme:form-textarea code="worker.duty.form.label.description" path="description" />
	<acme:form-integer code="worker.duty.form.label.percentage" path="percentage" placeholder="25"/>

	<acme:form-return code="worker.duty.form.button.return" />
	
</acme:form>
