<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
  	<acme:form-textbox code="administrator.company-record.form.label.company-name" path="companyName"/>
  	<acme:form-textbox code="administrator.company-record.form.label.sector" path="sector" />
  	<acme:form-textbox code="administrator.company-record.form.label.ceo-name" path="ceoName"/>
	<acme:form-textarea code="administrator.company-record.form.label.description" path="description" />
	<acme:form-url code="administrator.company-record.form.label.website" path="webSite" />
  	<acme:form-textbox code="administrator.company-record.form.label.phone" path="phone" placeholder ="+99 (999) 999 999"/>
	<acme:form-textbox code="administrator.company-record.form.label.email" path="email" placeholder = "acme@gmail.com"/>
	<acme:form-checkbox code="administrator.company-record.form.label.incorporated" path="incorporated"/>
	<acme:form-integer code="administrator.company-record.form.label.stars" path="stars" placeholder ="1-5"/>

	<acme:form-submit test="${command == 'show'}"
		code = "administrator.company-record.form.button.update"
		action = "/administrator/company-record/update"/>
	<acme:form-submit test="${command == 'show'}"
		code = "administrator.company-record.form.button.delete"
		action = "/administrator/company-record/delete"/>
	<acme:form-submit test="${command == 'create'}"
		code = "administrator.company-record.form.button.create"
		action = "/administrator/company-record/create"/>
	<acme:form-submit test="${command == 'update'}"
		code = "administrator.company-record.form.button.update"
		action = "/administrator/company-record/update"/>
	<acme:form-submit test="${command == 'delete'}"
		code = "administrator.company-record.form.button.delete"
		action = "/administrator/company-record/delete"/>
  	<acme:form-return 
  		code="administrator.company-record.form.button.return"/>
</acme:form>