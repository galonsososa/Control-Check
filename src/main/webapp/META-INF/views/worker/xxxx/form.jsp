<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	<acme:form-textarea code="worker.XXXX.form.label.description" path="description" />
	<acme:form-url code="worker.XXXX.form.label.moreInfo" path="moreInfo" placeholder="http://acme.com"/>
	
	<!-- insert button answer -->		
	<acme:form-return code="worker.XXXX.form.button.return" />
	
</acme:form>