<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	<acme:form-hidden path="jobId"/>
	<acme:form-hidden path="isJobFinalMode"/>

	<acme:form-textarea code="employer.XXXX.form.label.description" path="description" />
	<acme:form-url code="employer.XXXX.form.label.moreInfo" path="moreInfo" placeholder="http://acme.com"/>
		
	<acme:form-submit test="${command == 'create' }" code="employer.XXXX.form.button.create"
		action="/employer/xxxx/create" />
		
	<jstl:if test="${isJobFinalMode != 'true' }">
		<acme:form-submit test="${command == 'show'}" 
			code="employer.XXXX.form.button.delete"
			action="/employer/xxxx/delete" />
		<acme:form-submit test="${command == 'delete' }" code="employer.XXXX.form.button.delete"
			action="/employer/xxxx/delete" />
		<acme:form-submit test="${command == 'show' }"
			code = "employer.XXXX.form.button.update"
			action = "/employer/xxxx/update"/>	
		<acme:form-submit test="${command == 'update'}" 
			code="employer.XXXX.form.button.update" 
			action="/employer/xxxx/update"/>
	</jstl:if>
		
		
	<acme:form-return code="employer.XXXX.form.button.return" />
	
</acme:form>