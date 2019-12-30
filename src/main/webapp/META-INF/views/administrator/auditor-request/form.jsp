<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>


	<acme:form-textbox code="administrator.auditor-request.form.label.moment" path="moment" readonly="true" />
	<acme:form-textbox code="administrator.auditor-request.form.label.userAccountName" path="userAccountName" readonly="true" />
	<acme:form-textbox code="administrator.auditor-request.form.label.firm" path="firm" readonly="true"/>
	<acme:form-textarea code="administrator.auditor-request.form.label.statement" path="statement" readonly="true" />
	
			
		<acme:form-submit method="post" test="${command == 'show'}"
			code = "administrator.auditor-request.form.button.accept"
			action = "/administrator/auditor/create"/>
		<acme:form-submit method="post" test="${command == 'update'}" 
			code="administrator.auditor-request.form.button.accept" 
			action="/administrator/auditor/create"/>
			
		<acme:form-submit method="post" test="${command == 'show' }"
			code = "administrator.auditor-request.form.button.delete"
			action = "/administrator/auditor-request/delete"/>
		<acme:form-submit method="post" test="${command == 'update'}" 
			code="administrator.auditor-request.form.button.delete" 
			action="/administrator/auditor-request/delete"/>

	<acme:form-return code="administrator.auditor-request.form.button.return" />

</acme:form>
