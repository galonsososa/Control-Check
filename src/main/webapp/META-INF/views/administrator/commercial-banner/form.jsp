<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-url code="administrator.commercial-banner.form.label.picture" path="picture" />
	<acme:form-textbox code="administrator.commercial-banner.form.label.slogan" path="slogan" />
	<acme:form-url code="administrator.commercial-banner.form.label.targetUrl" path="targetUrl" />
	<acme:form-textbox code="administrator.commercial-banner.form.label.creditCardNumber" path="creditCardNumber"
		placeholder="1111222233334444" />
	<acme:form-textbox code="administrator.commercial-banner.form.label.holder" path="holder" />
	<acme:form-textbox code="administrator.commercial-banner.form.label.cvv" path="cvv" placeholder="123" />
	<acme:form-textbox code="administrator.commercial-banner.form.label.brand" path="brand"
		placeholder="VISA / MASTERCARD / AMERICAN EXPRESS..." />
	<acme:form-moment code="administrator.commercial-banner.form.label.expirationDate" path="expirationDate" />

	<acme:form-submit test="${command == 'show'}" code="administrator.commercial-banner.form.button.update"
		action="/administrator/commercial-banner/update" />
	<acme:form-submit test="${command == 'show'}" code="administrator.commercial-banner.form.button.delete"
		action="/administrator/commercial-banner/delete" />
	<acme:form-submit test="${command == 'create'}" code="administrator.commercial-banner.form.button.create"
		action="/administrator/commercial-banner/create" />
	<acme:form-submit test="${command == 'update'}" code="administrator.commercial-banner.form.button.update"
		action="/administrator/commercial-banner/update" />
	<acme:form-submit test="${command == 'delete'}" code="administrator.commercial-banner.form.button.delete"
		action="/administrator/commercial-banner/delete" />

	<acme:form-return code="administrator.commercial-banner.form.button.return" />
</acme:form>