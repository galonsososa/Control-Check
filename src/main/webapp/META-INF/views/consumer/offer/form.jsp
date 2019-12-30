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
  	<acme:form-textbox code="consumer.offer.form.label.title" path="title" />
  	<acme:form-textarea code="consumer.offer.form.label.description" path="description"/>
  	<jstl:if test="${command != 'create'}">
  		<acme:form-moment code="consumer.offer.form.label.creationMoment" path="creationMoment" readonly="true"/>

  	</jstl:if> 	

	<acme:form-moment code="consumer.offer.form.label.deadline" path="deadline" />				
	<acme:form-money code="consumer.offer.form.label.minMoney" path="minMoney"/>	
	<acme:form-money code="consumer.offer.form.label.maxMoney" path="maxMoney"/>
	<acme:form-textbox code="consumer.offer.form.label.ticker" path="ticker" placeholder ="OAAAA-12345"/>
	<acme:form-url code="consumer.offer.form.label.moreInfo" path="moreInfo"/>		
				<acme:form-checkbox code="consumer.offer.form.label.accept" path="accept" />
			
	<acme:form-submit test="${command == 'create' }" code="consumer.offer.form.button.create"
		action="/consumer/offer/create"/>
  	<acme:form-return code="consumer.offer.form.button.return"/>
  	
</acme:form>
