<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.auditor.form.label.firm" path="firm" readonly="true"/>
	<acme:form-textbox code="administrator.auditor.form.label.statement" path="statement" readonly="true"/>
	
	<acme:form-submit  test="${command == 'create'}" code="administrator.auditor.form.button.create" action="/administrator/auditor/create"/>
	<acme:form-submit  test="${command == 'update'}" code="administrator.auditor.form.button.update" action="/administrator/auditor/update"/>
	<acme:form-return code="administrator.auditor.form.button.return" />

</acme:form>
