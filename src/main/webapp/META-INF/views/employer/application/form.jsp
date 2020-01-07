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
	<acme:form-select code="employer.application.form.label.status" path="status">
		<acme:form-option code= "employer.application.form.option.pending" value="PENDING"/>
		<acme:form-option code= "employer.application.form.option.rejected" value="REJECTED"/>
		<acme:form-option code= "employer.application.form.option.accepted" value="ACCEPTED"/>
	</acme:form-select>
	<acme:form-textarea code="employer.application.form.label.justification" path="justification" />
	
	<jstl:if test="${not empty answer}">
		<acme:form-panel code="employer.application.form.panel.answer">
			<acme:form-textarea code="employer.application.form.answer" path="answer" readonly="true"/>
			<jstl:if test="${not empty optionalAnswer}">
				<acme:form-textbox code="employer.application.form.optionalAnswer" path="optionalAnswer" readonly="true"/>
				<jstl:if test="${not empty password}">
					<acme:form-textbox code="employer.application.form.password" path="password" readonly="true"/>
					<!-- if the employer needs the pass for something set it as textbox -->
				</jstl:if>
			</jstl:if>
		</acme:form-panel>
	</jstl:if>

	<acme:form-submit test="${command == 'show'}" code="employer.application.form.button.update" action="/employer/application/update" />
	<acme:form-submit test="${command == 'update'}" code="employer.application.form.button.update"
		action="/employer/application/update" />

	<acme:form-return code="employer.application.form.button.return" />

</acme:form>
