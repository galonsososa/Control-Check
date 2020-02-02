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
	<acme:form-hidden path="jobId"/>
	<acme:form-hidden path="jobHasPust"/>

	<acme:form-textbox code="worker.application.form.label.reference" path="reference" placeholder="EEEE-JJJJ:WWWW" />
	<jstl:if test="${command != 'create' }">
		<acme:form-moment code="worker.application.form.label.moment" path="moment" readonly="true" />
		<acme:form-textbox code="worker.application.form.label.status" path="status" readonly="true"/>
		<jstl:if test="${status != 'PENDING' }">
			<acme:form-textarea code="worker.application.form.label.justification" path="justification" readonly="true"/>
		</jstl:if>	
	</jstl:if> 
	<acme:form-textarea code="worker.application.form.label.statement" path="statement" />
	
	<jstl:if test="${not empty answer && command=='show'}">
		<acme:form-panel code="worker.application.form.panel.answer">
			<acme:form-textarea code="worker.application.form.answer" path="answer" readonly="true" />
			<jstl:if test="${not empty bow}">
				<acme:form-textbox code="worker.application.form.bow" path="bow" readonly="true"/>
				<jstl:if test="${not empty password}">
					<acme:form-textbox code="worker.application.form.password" path="password" readonly="true"/>
					<!-- if the employer needs the pass for something set it as textbox -->
				</jstl:if>
			</jstl:if>
		</acme:form-panel>
	</jstl:if>
	
	<jstl:if test="${jobHasPust=='true' && command=='create'}">
		<acme:form-panel code="worker.application.form.panel.answer">
			<acme:form-textarea code="worker.application.form.answer" path="answer"/>
			<acme:form-textbox code="worker.application.form.bow" path="bow"/>
			<acme:form-password code="worker.application.form.password" path="password"/>
			<!-- if the employer needs the pass for something set it as textbox -->
		</acme:form-panel>
	</jstl:if>

	<acme:form-submit test="${command == 'create' }" code="worker.application.form.button.create" action="/worker/application/create" />
	
	<acme:form-return code="worker.application.form.button.return" />

</acme:form>
