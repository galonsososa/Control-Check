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

<acme:form>


	<acme:form-textbox code="employer.application.form.label.reference" path="reference" readonly="true" />
	<acme:form-moment code="employer.application.form.label.moment" path="moment" readonly="true" />
	<acme:form-textbox code="employer.application.form.label.statement" path="statement" readonly="true" />
	<acme:form-textarea code="employer.application.form.label.skills" path="skills" readonly="true" />
	<acme:form-textarea code="employer.application.form.label.qualifications" path="qualifications" readonly="true" />
	<acme:form-select code="administrator.auditor-request.form.label.status" path="status">
		<option value="PENDING">PENDING</option>
		<option value="ACCEPTED">ACCEPTED</option>
		<option value="REJECTED">REJECTED</option>
	</acme:form-select>
	<acme:form-textarea code="employer.application.form.label.justification" path="justification" />

	<acme:form-submit test="${command == 'show'}" code="employer.application.form.button.update" action="/employer/application/update" />
	<acme:form-submit test="${command == 'update'}" code="employer.application.form.button.update"
		action="/employer/application/update" />

	<acme:form-return code="employer.application.form.button.return" />

</acme:form>
