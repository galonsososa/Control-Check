<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.thread.form.label.title" path="title"/>
	
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="authenticated.thread.form.label.creationMoment" path="creationMoment"/>
	</jstl:if>
	
	<jstl:if test="${isMine == true}">
		<acme:form-submit method="get" test="${command == 'show'}" code="authenticated.thread.form.button.add" action="/authenticated/participation/add_member?threadId=${id}"/>
	</jstl:if>
	<acme:form-submit method="get" test="${command == 'show'}" code="authenticated.thread.form.button.authenticateds" action="/authenticated/participation/list_thread?threadId=${id}"/>
	<acme:form-submit method="get" test="${command == 'show'}" code="authenticated.thread.form.button.new-message" action="/authenticated/message/create?threadId=${id}"/>
	<acme:form-submit method="get" test="${command == 'show'}" code="authenticated.thread.form.button.messages" action="/authenticated/message/list_thread?threadId=${id}" />
	
	<acme:form-submit test="${command == 'create'}" code="authenticated.thread.form.button.create" action="/authenticated/thread/create" />
	
	<acme:form-return code="authenticated.thread.form.button.return"/>
</acme:form>
