<%@ include file="head.jsp" %>
<%@ page import="com.solarise.server.QueryPlan" %>
<%@ page import="com.solarise.entity.PlanEntity" %>
<%@ page import="com.solarise.server.QueryPanel" %>
<%@ page import="com.solarise.entity.PanelEntity" %>
<%
    if(!isLogged){
        response.sendRedirect("login.jsp?redir=tracker.jsp");
    }
    QueryPanel qpanel=new QueryPanel();
    QueryPlan qplan=new QueryPlan(email);

    Integer projectId=0;
    Integer withProjectId=0;
    if(request.getParameter("projectId")!=null){
    	projectId=Integer.parseInt(request.getParameter("projectId"));
	}
	if(request.getParameter("withProjectId")!=null){
    	withProjectId=Integer.parseInt(request.getParameter("withProjectId"));
	}
%>
    <article class="content">
    <table id="compare">
        <tr>
            <td><b>Project Information</b></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>Coordinates (Latitutde, Longitude)</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>Roof Area</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>Energy Usage</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td><b>Panel Information</b></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>Number</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
        <td>Manufacturer</td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <td>Model</td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <td>Technology</td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <td>Length</td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <td>Width</td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <td>Height</td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <td>Weight</td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <td>Power Output</td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <td>Conversion Efficiency</td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <td>Peak Output</td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <td>Peak Voltage</td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <td>Peak Current</td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <td>Open Circuit Voltage</td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <td>Short Circuit Current</td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <td>Max System Voltage</td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <td>Max Series Fuse Rating</td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <td>Power Tolerance</td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <td>Efficiency Loss</td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <td>Inverter Lifespan</td>
        <td></td>
        <td></td>
      </tr>
        <tr>
            <td><b>Project Outputs</b></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>Duration of Sunshine</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>Daily Electricity Generated</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>Annual Electricity Generated</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>Daily Electricity Sales</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>Annual Electricity Generated</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>Daily Equivalent Electricity Fee</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td><b>Surplus Outputs</b></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>Daily Surplus Electricity</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>Daily Equivalent Surplus Electricity Fee</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>Annual Equivalent Surplus Electricity Fee</td>
            <td></td>
            <td></td>
        </tr>
    </table>
    <script type="text/javascript">
	<%= qpanel.getJSON() %>
	<%= qplan.getJSON() %>
	var projectId=<%= projectId %>;
	var withProjectId=<%= withProjectId %>;
	$(document).ready(function(){
		panelData=eval("("+panels+")");
    	planData=eval("("+plans+")");
    	$("tr:eq(0)>td:eq(1)").html("#"+projectId);
		$("tr:eq(0)>td:eq(2)").html("#"+withProjectId);
		$("tr:eq(1)>td:eq(1)").html("("+parseFloat(planData.plan[projectId].latitude).toFixed(2)+", "+parseFloat(planData.plan[projectId].longitude).toFixed(2)+")");
		$("tr:eq(1)>td:eq(2)").html("("+parseFloat(planData.plan[withProjectId].latitude).toFixed(2)+", "+parseFloat(planData.plan[projectId].longitude).toFixed(2)+")");
		$("tr:eq(2)>td:eq(1)").html(planData.plan[projectId].roofArea);
		$("tr:eq(2)>td:eq(2)").html(planData.plan[withProjectId].roofArea);
		$("tr:eq(3)>td:eq(1)").html(planData.plan[projectId].energyUse);
		$("tr:eq(3)>td:eq(2)").html(planData.plan[withProjectId].energyUse);

		$("tr:eq(5)>td:eq(1)").html(planData.plan[projectId].number);
		$("tr:eq(5)>td:eq(2)").html(planData.plan[withProjectId].number);
		a=planData.plan[projectId].panelId;
		b=planData.plan[withProjectId].panelId;
		$("tr:eq(6)>td:eq(1)").html(panelData.panel[a].manufacturer);
		$("tr:eq(6)>td:eq(2)").html(panelData.panel[b].manufacturer);
		$("tr:eq(7)>td:eq(1)").html(panelData.panel[a].model);
		$("tr:eq(7)>td:eq(2)").html(panelData.panel[b].model);
		$("tr:eq(8)>td:eq(1)").html(panelData.panel[a].technology);
		$("tr:eq(8)>td:eq(2)").html(panelData.panel[b].technology);
		$("tr:eq(9)>td:eq(1)").html(panelData.panel[a].length+" mm");
		$("tr:eq(9)>td:eq(2)").html(panelData.panel[b].length+" mm");
		$("tr:eq(10)>td:eq(1)").html(panelData.panel[a].width+" mm");
		$("tr:eq(10)>td:eq(2)").html(panelData.panel[b].width+" mm");
		$("tr:eq(11)>td:eq(1)").html(panelData.panel[a].height+" mm");
		$("tr:eq(11)>td:eq(2)").html(panelData.panel[b].height+" mm");
		$("tr:eq(12)>td:eq(1)").html(panelData.panel[a].weight+" kg");
		$("tr:eq(12)>td:eq(2)").html(panelData.panel[b].weight+" kg");
		$("tr:eq(13)>td:eq(1)").html(panelData.panel[a].powerOutput+" W");
		$("tr:eq(13)>td:eq(2)").html(panelData.panel[b].powerOutput+" W");
    	$("tr:eq(14)>td:eq(1)").html(panelData.panel[a].modelConversionEfficiency+"%");
    	$("tr:eq(14)>td:eq(2)").html(panelData.panel[b].modelConversionEfficiency+"%");
    	$("tr:eq(15)>td:eq(1)").html(panelData.panel[a].peakOutput+" W");
    	$("tr:eq(15)>td:eq(2)").html(panelData.panel[b].peakOutput+" W");
    	$("tr:eq(16)>td:eq(1)").html(panelData.panel[a].peakVoltage+" V");
    	$("tr:eq(16)>td:eq(2)").html(panelData.panel[b].peakVoltage+" V");
    	$("tr:eq(17)>td:eq(1)").html(panelData.panel[a].peakCurrent+" A");
    	$("tr:eq(17)>td:eq(2)").html(panelData.panel[b].peakCurrent+" A");
    	$("tr:eq(18)>td:eq(1)").html(panelData.panel[a].openCircuitVoltage+" V");
    	$("tr:eq(18)>td:eq(2)").html(panelData.panel[b].openCircuitVoltage+" V");
    	$("tr:eq(19)>td:eq(1)").html(panelData.panel[a].shortCircuitCurrent+" A");
    	$("tr:eq(19)>td:eq(2)").html(panelData.panel[b].shortCircuitCurrent+" A");
    	$("tr:eq(20)>td:eq(1)").html(panelData.panel[a].maxSystemVoltage+" V");
    	$("tr:eq(20)>td:eq(2)").html(panelData.panel[b].maxSystemVoltage+" V");
    	$("tr:eq(21)>td:eq(1)").html(panelData.panel[a].maxSeriesFuseRating+" A");
    	$("tr:eq(21)>td:eq(2)").html(panelData.panel[b].maxSeriesFuseRating+" A");
    	$("tr:eq(22)>td:eq(1)").html(panelData.panel[a].powerTolerance);
    	$("tr:eq(22)>td:eq(2)").html(panelData.panel[b].powerTolerance);
    	$("tr:eq(23)>td:eq(1)").html("<2% / Year");
    	$("tr:eq(23)>td:eq(2)").html("<2% / Year");
    	$("tr:eq(24)>td:eq(1)").html("10 Years");
		$("tr:eq(24)>td:eq(2)").html("10 Years"); 
		durationOfSunshine=10.0;
		number=planData.plan[projectId].number;
		usage=planData.plan[projectId].energyUse;
		$("tr:eq(26)>td:eq(1)").html(durationOfSunshine+" Hrs");
    	$("tr:eq(27)>td:eq(1)").html(getDailyGen(panelData.panel[a].powerOutput,durationOfSunshine,number).toFixed(2)+" kW*h");
    	$("tr:eq(28)>td:eq(1)").html(getAnnualGen(panelData.panel[a].powerOutput,durationOfSunshine,number).toFixed(2)+" kW*h");
    	$("tr:eq(29)>td:eq(1)").html(getDailySales(panelData.panel[a].powerOutput,durationOfSunshine,number).toFixed(2)+" $");
    	$("tr:eq(30)>td:eq(1)").html(getAnnualSales(panelData.panel[a].powerOutput,durationOfSunshine,number).toFixed(2)+" $");
    	$("tr:eq(31)>td:eq(1)").html(getDailyFee(panelData.panel[a].powerOutput,durationOfSunshine,number).toFixed(2)+" $");

    	$("tr:eq(33)>td:eq(1)").html(getDailySurplus(panelData.panel[a].powerOutput,durationOfSunshine,number,usage).toFixed(2)+" kW*h");
    	$("tr:eq(34)>td:eq(1)").html(getDailySurplusFee(panelData.panel[a].powerOutput,durationOfSunshine,number,usage).toFixed(2)+" $");
    	$("tr:eq(35)>td:eq(1)").html(getAnnualSurplusFee(panelData.panel[a].powerOutput,durationOfSunshine,number,usage).toFixed(2)+" $");
    	durationOfSunshine=9.0;
		number=planData.plan[withProjectId].number;
		usage=planData.plan[withProjectId].energyUse;
		$("tr:eq(26)>td:eq(2)").html(durationOfSunshine+" Hrs");
    	$("tr:eq(27)>td:eq(2)").html(getDailyGen(panelData.panel[a].powerOutput,durationOfSunshine,number).toFixed(2)+" kW*h");
    	$("tr:eq(28)>td:eq(2)").html(getAnnualGen(panelData.panel[a].powerOutput,durationOfSunshine,number).toFixed(2)+" kW*h");
    	$("tr:eq(29)>td:eq(2)").html(getDailySales(panelData.panel[a].powerOutput,durationOfSunshine,number).toFixed(2)+" $");
    	$("tr:eq(30)>td:eq(2)").html(getAnnualSales(panelData.panel[a].powerOutput,durationOfSunshine,number).toFixed(2)+" $");
    	$("tr:eq(31)>td:eq(2)").html(getDailyFee(panelData.panel[a].powerOutput,durationOfSunshine,number).toFixed(2)+" $");

    	$("tr:eq(33)>td:eq(2)").html(getDailySurplus(panelData.panel[a].powerOutput,durationOfSunshine,number,usage).toFixed(2)+" kW*h");
    	$("tr:eq(34)>td:eq(2)").html(getDailySurplusFee(panelData.panel[a].powerOutput,durationOfSunshine,number,usage).toFixed(2)+" $");
    	$("tr:eq(35)>td:eq(2)").html(getAnnualSurplusFee(panelData.panel[a].powerOutput,durationOfSunshine,number,usage).toFixed(2)+" $");
	});

function getDailyGen(powerOutput,durationOfSunshine,number){
    return powerOutput*durationOfSunshine*number*3600/3.6/1000000;
}

function getAnnualGen(powerOutput,durationOfSunshine,number){
    return getDailyGen(powerOutput,durationOfSunshine,number)*365;
}

function getDailySales(powerOutput,durationOfSunshine,number){
    return getDailyGen(powerOutput,durationOfSunshine,number)*0.08;
}

function getAnnualSales(powerOutput,durationOfSunshine,number){
    return getDailySales(powerOutput,durationOfSunshine,number)*365;
}

function getDailyFee(powerOutput,durationOfSunshine,number){
    return getDailyGen(powerOutput,durationOfSunshine,number)*0.12;
}

function getDailySurplus(powerOutput,durationOfSunshine,number,usage){
    return getDailyGen(powerOutput,durationOfSunshine,number,usage)-usage;
}

function getDailySurplusFee(powerOutput,durationOfSunshine,number,usage){
    return getDailySurplus(powerOutput,durationOfSunshine,number,usage)*0.12;
}

function getAnnualSurplusFee(powerOutput,durationOfSunshine,number,usage){
    return getDailySurplusFee(powerOutput,durationOfSunshine,number,usage)*365;
}
	</script>
</article><!-- end .content -->
<%@ include file="foot.jsp" %>
