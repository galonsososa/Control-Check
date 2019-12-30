<%--
- menu.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.entities.roles.Provider,acme.entities.roles.Consumer"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.favourite-link" action="http://www.example.com/" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.anonymous.announcements-link" action="/anonymous/announcement/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.anonymous.investor" action="/anonymous/investor/list" />
			<acme:menu-suboption code="master.menu.anonymous.top-investor" action="/anonymous/investor/topList" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.anonymous.company-record" action="/anonymous/company-record/list" />
			<acme:menu-suboption code="master.menu.anonymous.top-company-record" action="/anonymous/company-record/topList" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.authenticated" access="hasRole('Authenticated')">
			<acme:menu-suboption code="master.menu.authenticated.announcements-link" action="/authenticated/announcement/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.authenticated.request-link" action="/authenticated/request/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.authenticated.challenge-link" action="/authenticated/challenge/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.authenticated.investor" action="/authenticated/investor/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.authenticated.company-record" action="/authenticated/company-record/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.authenticated.offer" action="/authenticated/offer/list" />
			<acme:menu-suboption code="master.menu.authenticated.job.list" action="/authenticated/job/list" />

			<acme:menu-separator />		
			<acme:menu-suboption code="master.menu.authenticated.thread.create" action="/authenticated/thread/create" />
			<acme:menu-suboption code="master.menu.authenticated.thread.list_mine" action="/authenticated/thread/list_mine" />

			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.authenticated.auditor-request.create" action="/authenticated/auditor-request/create" />

		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.announcement" action="/administrator/announcement/list" />
			<acme:menu-suboption code="master.menu.administrator.announcement.create" action="/administrator/announcement/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.investor.create" action="/administrator/investor/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.challenge-link" action="/administrator/challenge/list" />
			<acme:menu-suboption code="master.menu.administrator.challenge.create" action="/administrator/challenge/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.dashboard" action="/administrator/dashboard/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.customParams" action="/administrator/customparams/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.company-record" action="/administrator/company-record/list" />
			<acme:menu-suboption code="master.menu.administrator.company-record.create" action="/administrator/company-record/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.commercial-banner-link" action="/administrator/commercial-banner/list" />

			<acme:menu-separator />

			<acme:menu-suboption code="master.menu.administrator.commercial-banner.create" action="/administrator/commercial-banner/create" />
			<acme:menu-suboption code="master.menu.administrator.non-commercial-banner-link"
				action="/administrator/non-commercial-banner/list" />
			<acme:menu-suboption code="master.menu.administrator.non-commercial-banner.create"
				action="/administrator/non-commercial-banner/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.auditor-request.list" action="/administrator/auditor-request/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.provider" access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.provider.favourite-link" action="http://www.example.com/" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.provider.request.create" action="/provider/request/create" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.consumer" access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.favourite-link" action="https://www.google.es/" />
			<acme:menu-suboption code="master.menu.consumer.offer.create" action="/consumer/offer/create" />

		</acme:menu-option>

		<acme:menu-option code="master.menu.employer" access="hasRole('Employer')">
			<acme:menu-suboption code="master.menu.employer.job.list-mine" action="/employer/job/list-mine" />
			<acme:menu-suboption code="master.menu.employer.job.create" action="/employer/job/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.employer.application.list" action="/employer/application/list-mine" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.auditor" access="hasRole('Auditor')">
			<acme:menu-suboption code="master.menu.auditor.job.list" action="/auditor/job/list-mine" />
			<acme:menu-suboption code="master.menu.auditor.job.list.not-mine" action="/auditor/job/list-not-mine" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.worker" access="hasRole('Worker')">
			<acme:menu-suboption code="master.menu.worker.application.list" action="/worker/application/list-mine" />
			<acme:menu-suboption code="master.menu.worker.job.list" action="/worker/job/list-mine" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.sponsor" access="hasRole('Sponsor')">
			<acme:menu-suboption code="master.menu.sponsor.commercial-banner.list-mine" action="/sponsor/commercial-banner/list-mine" />

			<acme:menu-suboption code="master.menu.sponsor.commercial-banner.create" action="/sponsor/commercial-banner/create" />
			<acme:menu-suboption code="master.menu.sponsor.non-commercial-banner.list-mine" action="/sponsor/non-commercial-banner/list-mine" />
			<acme:menu-suboption code="master.menu.sponsor.non-commercial-banner.create" action="/sponsor/non-commercial-banner/create" />
			<acme:menu-suboption code="master.menu.sponsor.credit-card.list" action="/sponsor/credit-card/list" />
			<acme:menu-suboption code="master.menu.sponsor.credit-card.create" action="/sponsor/credit-card/create" />
			

		</acme:menu-option>

	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()" />
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()" />

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update" />
			<acme:menu-suboption code="master.menu.user-account.become-provider" action="/authenticated/provider/create"
				access="!hasRole('Provider')" />
			<acme:menu-suboption code="master.menu.user-account.provider" action="/authenticated/provider/update"
				access="hasRole('Provider')" />
			<acme:menu-suboption code="master.menu.user-account.become-consumer" action="/authenticated/consumer/create"
				access="!hasRole('Consumer')" />
			<acme:menu-suboption code="master.menu.user-account.consumer" action="/authenticated/consumer/update"
				access="hasRole('Consumer')" />
			<acme:menu-suboption code="master.menu.user-account.become-worker" action="/authenticated/worker/create"
				access="!hasRole('Worker')" />
			<acme:menu-suboption code="master.menu.user-account.worker" action="/authenticated/worker/update"
			 access="hasRole('Worker')" />
			<acme:menu-suboption code="master.menu.user-account.become-employer" action="/authenticated/employer/create"
				access="!hasRole('Employer')" />
			<acme:menu-suboption code="master.menu.user-account.consumer" action="/authenticated/consumer/update"
				access="hasRole('Consumer')" />
     		<acme:menu-suboption code="master.menu.user-account.sponsor" action="/authenticated/sponsor/update"
				access="hasRole('Sponsor')" />
			<acme:menu-suboption code="master.menu.user-account.become-sponsor" action="/authenticated/sponsor/create"
				access="!hasRole('Sponsor')" />
			<acme:menu-suboption code="master.menu.user-account.employer" action="/authenticated/employer/update"
				access="hasRole('Employer')" />

		</acme:menu-option>
		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()" />
	</acme:menu-right>
</acme:menu-bar>
