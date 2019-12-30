<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.auditor-request.list.label.userAccountName" path="userAccountName" width="40%"/>
	<acme:list-column code="administrator.auditor-request.list.label.moment" path="moment" width="10%"/>
	<acme:list-column code="administrator.auditor-request.list.label.status" path="status" width="10%"/>
</acme:list>