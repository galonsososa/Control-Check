<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="${command == 'show'}">

	<jstl:if test="${command == 'show'}">
		<acme:form-textbox code="authenticated.message.form.label.threadTitle" path="thread.title" readonly="true" />
	</jstl:if>
	
	<acme:form-textbox code="authenticated.message.form.label.title" path="title" />
	
	<jstl:if test="${command == 'show'}">
		<acme:form-moment code="authenticated.message.form.label.creationMoment" path="creationMoment" readonly="true"/>
	</jstl:if>
	
	<acme:form-textarea code="authenticated.message.form.label.body" path="body"/>
	<acme:form-textbox code="authenticated.message.form.label.tags" path="tags"/>
	
	<jstl:if test="${command == 'create'}">
		<acme:form-checkbox code="authenticated.message.form.label.confirm" path="confirmation"/>
	</jstl:if>
	
	<acme:form-submit test="${command == 'create'}" code="authenticated.message.form.button.create" action="/authenticated/message/create?threadId=${threadId}"/>
	<acme:form-return code="authenticated.thread.form.button.return"/>
</acme:form>

