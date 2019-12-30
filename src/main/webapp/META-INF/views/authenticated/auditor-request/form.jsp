<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	<acme:form-textbox code="authenticated.auditor-request.form.label.firm" path="firm"/>
	<acme:form-textarea code="authenticated.auditor-request.form.label.statement" path="statement"/>
	
	<acme:form-submit code="authenticated.auditor-request.form.button.create" 
		action="/authenticated/auditor-request/create"/>
	
	<acme:form-return code="authenticated.auditor-request.form.button.return" />

</acme:form>
