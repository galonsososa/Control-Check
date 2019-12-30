<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.message.list.label.title" path="title" width="70%"/>
	<acme:list-column code="authenticated.message.list.label.creationMoment" path="creationMoment" width="30%"/>
</acme:list>

<acme:form>
	<acme:form-return code="authenticated.message.list.button.return"/>
</acme:form>