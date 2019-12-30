<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.employer.form.label.company" path="company" />
	<acme:form-textbox code="authenticated.employer.form.label.sector" path="sector" />
	
	<acme:form-submit  test="${command == 'create'}" code="authenticated.employer.form.button.create" action="/authenticated/employer/create"/>
	<acme:form-submit  test="${command == 'update'}" code="authenticated.employer.form.button.update" action="/authenticated/employer/update"/>
	<acme:form-return code="authenticated.employer.form.button.return" />

</acme:form>
