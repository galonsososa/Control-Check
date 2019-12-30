<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.thread.list.label.title" path="title" width="70%"/>
	<acme:list-column code="authenticated.thread.list.label.creationMoment" path="creationMoment" width="30%"/>
</acme:list>