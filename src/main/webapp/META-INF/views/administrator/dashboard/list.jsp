<%--
- list.jsp
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

<acme:form readonly="true">
<acme:form-integer code="administrator.dashboard.legend.NTA" path="totalNumberAnnouncements"/>
<acme:form-integer code="administrator.dashboard.legend.NTRC" path="totalNumberCompanyRecords"/>
<acme:form-integer code="administrator.dashboard.legend.NTRI" path="totalNumberInvestorRecords"/>
<acme:form-integer code="administrator.dashboard.legend.RMINP" path="minRequestReward"/>
<acme:form-integer code="administrator.dashboard.legend.RMAXP" path="maxRequestReward"/>
<acme:form-integer code="administrator.dashboard.legend.RMEDP" path="avgRequestReward"/>
<acme:form-integer code="administrator.dashboard.legend.RDEVP" path="stdRequestReward"/>
<acme:form-integer code="administrator.dashboard.legend.RMINO" path="minOfferReward"/>
<acme:form-integer code="administrator.dashboard.legend.RMAXO" path="maxOfferReward"/>
<acme:form-integer code="administrator.dashboard.legend.RMEDO" path="avgOfferReward"/>
<acme:form-integer code="administrator.dashboard.legend.RDEVO" path="stdOfferReward"/>
</acme:form>


<div style="height:25%; width:25%; display:inline-block">
<acme:message code="administrator.dashboard.chart.Company"/> 
<canvas id = "numberOfCompaniesBySector" > </canvas>
</div>


<div style="height:25%; width:25%;display:inline-block">
<acme:message code="administrator.dashboard.chart.Investor"/> 
<canvas id = "numberOfInvestorsBySector" > </canvas>
</div> </br>


<script type = "text/javascript">
$(document).ready(function(){
var CanvasCompany = document.getElementById("numberOfCompaniesBySector");
Chart.defaults.global.defaultFontFamily = "Lato";
Chart.defaults.global.defaultFontSize = 18;

var DataCompany = {
	    labels: [
				<jstl:forEach items = "${numberOfCompaniesGroupedBySector}" var = "item">
				"<jstl:out value = "${item[0]}" />" ,
				</jstl:forEach>
	    ],
	    datasets: [
	        {
	            data: [
	            	<jstl:forEach items = "${numberOfCompaniesGroupedBySector}" var = "item">
					"<jstl:out value = "${item[1]}" />" ,
					</jstl:forEach>
	            ], 
	            backgroundColor :["black", "yellow", "pink", "orange"]
	        }]
	};
var pieChartCompany = new Chart(CanvasCompany, {
	  type: 'pie',
	  data: DataCompany
	});
});
</script>



<script type = "text/javascript">
$(document).ready(function(){
var CanvasInvestor = document.getElementById("numberOfInvestorsBySector");
Chart.defaults.global.defaultFontFamily = "Lato";
Chart.defaults.global.defaultFontSize = 18;

var DataInvestor = {
	    labels: [
				<jstl:forEach items = "${numberOfInvestorsGroupedBySector}" var = "item">
				"<jstl:out value = "${item[0]}" />" ,
				</jstl:forEach>
	    ],
	    datasets: [
	        {
	            data: [
	            	<jstl:forEach items = "${numberOfInvestorsGroupedBySector}" var = "item">
					"<jstl:out value = "${item[1]}" />" ,
					</jstl:forEach>
	            ],
	            backgroundColor :["black", "yellow", "pink", "orange"]
	        }]
	};
var pieChartInvestor = new Chart(CanvasInvestor, {
	  type: 'pie',
	  data: DataInvestor
	});
});
</script>


	    	
	 
