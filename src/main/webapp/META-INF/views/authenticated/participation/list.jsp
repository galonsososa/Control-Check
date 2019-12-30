<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.participation.list.label.username" path="authenticated.userAccount.username" width="20%" />
	<acme:list-column code="authenticated.participation.list.label.name" path="authenticated.userAccount.identity.name" width="40%"/>
	<acme:list-column code="authenticated.participation.list.label.surname" path="authenticated.userAccount.identity.surname" width="40%"/>	
</acme:list>

<acme:form>
	<acme:form-return code="authenticated.participation.list.button.return"/>
</acme:form>


