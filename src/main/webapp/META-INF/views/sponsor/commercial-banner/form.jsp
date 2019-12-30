<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	<acme:form-url code="sponsor.commercial-banner.form.label.picture" path="picture" />
	<acme:form-textbox code="sponsor.commercial-banner.form.label.slogan" path="slogan" />
	<acme:form-url code="sponsor.commercial-banner.form.label.targetUrl" path="targetUrl" />
	
	<jstl:if test="${command == 'show'}">
		<acme:form-url code="sponsor.commercial-banner.form.select.creditCard" path="creditCard.creditCardNumber" readonly="${command == 'show'}" />
	</jstl:if>
	
	<jstl:if test="${command == 'create'}">
		<acme:form-select code="sponsor.commercial-banner.form.select.creditCard" path="creditCard.id">
			<jstl:forEach items="${creditCards}" var="item"> 
				<acme:form-option code="${item.creditCardNumber}" value="${item.id}"/>
			</jstl:forEach>
		</acme:form-select>
	</jstl:if>
  	
  
  <acme:form-submit test="${command == 'show'}" code="sponsor.commercial-banner.form.button.update"
		action="/sponsor/commercial-banner/update" />
	<acme:form-submit test="${command == 'show'}" code="sponsor.commercial-banner.form.button.delete"
		action="/sponsor/commercial-banner/delete" />
	<acme:form-submit test="${command == 'create'}" code="sponsor.commercial-banner.form.button.create"
		action="/sponsor/commercial-banner/create" />
	<acme:form-submit test="${command == 'update'}" code="sponsor.commercial-banner.form.button.update"
		action="/sponsor/commercial-banner/update" />
	<acme:form-submit test="${command == 'delete'}" code="sponsor.commercial-banner.form.button.delete"
		action="/sponsor/commercial-banner/delete" />
	
		
	<acme:form-return code="sponsor.commercial-banner.form.button.return" />

</acme:form>
