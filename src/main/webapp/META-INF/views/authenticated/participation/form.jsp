<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="${command != 'add_member'}">

	<jstl:if test="${command == 'add_member'}">
		<acme:form-textbox code="authenticated.participation.form.label.username" path="authenticated.userAccount.username"/>
	</jstl:if>
	
	
	<jstl:if test="${command != 'add_member'}">
		<acme:form-textbox code="authenticated.participation.form.label.username" path="authenticated.userAccount.username"/>
		<acme:form-textbox code="authenticated.participation.form.label.name" path="authenticated.userAccount.identity.name"/>
		<acme:form-textbox code="authenticated.participation.form.label.surname" path="authenticated.userAccount.identity.surname"/>
		<acme:form-textbox code="authenticated.participation.form.label.email" path="authenticated.userAccount.identity.email"/>
	</jstl:if>	
	
	<acme:form-submit test="${command == 'show' && isOwner}" code="authenticated.participation.form.button.remove" action="/authenticated/participation/remove_member" />
	<acme:form-submit test="${command == 'add_member'}" code="authenticated.participation.form.button.add" action="/authenticated/participation/add_member?threadId=${threadId}" />
  	<acme:form-return code="authenticated.participation.form.button.return"/>
</acme:form>
