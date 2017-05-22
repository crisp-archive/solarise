<%@ include file="head.jsp" %>
<%@ page import="com.solarise.server.QueryPanel" %>
<%@ page import="com.solarise.entity.PlanEntity" %>
<%
PlanEntity pe=new PlanEntity();
if(request.getParameter("panelId")!=null && request.getParameter("panelId")!=""){
    pe.panelId=Long.parseLong(request.getParameter("panelId"));
}
else{
    pe.panelId=(long)0;
}
if(request.getParameter("number")!=null && request.getParameter("number")!=""){
    pe.number=Long.parseLong(request.getParameter("number"));
}
else{
    pe.number=(long)0;
}
if(request.getParameter("address")!=null){
    pe.address=request.getParameter("address");
}
if(request.getParameter("latitude")!=null && request.getParameter("latitude")!=""){
    pe.latitude=Double.parseDouble(request.getParameter("latitude"));
}
else{
    pe.latitude=(double)0;
}
if(request.getParameter("longitude")!=null && request.getParameter("longitude")!=""){
    pe.longitude=Double.parseDouble(request.getParameter("longitude"));
}
else{
    pe.longitude=(double)0;
}
if(request.getParameter("energyUse")!=null && request.getParameter("energyUse")!=""){
    pe.energyUse=Double.parseDouble(request.getParameter("energyUse"));
}
else{
    pe.energyUse=(double)0;
}
if(request.getParameter("roofArea")!=null && request.getParameter("roofArea")!=""){
    pe.roofArea=Double.parseDouble(request.getParameter("roofArea"));
}
else{
    pe.roofArea=(double)0;
}
%>
<div id="wizard">
    <span>Getting Started</span> >
    <span>Solar Panels</span> >
    <span>Geolocation</span> >
    <span>Estimation</span>
</div>
<article class="content">
    <table>
        <tr>
            <td><b>Plan Information</b></td>
            <td></td>
        </tr>
        <tr>
            <td>Panel Model</td>
            <td></td>
        </tr>
        <tr>
            <td>Number</td>
            <td><%= pe.number %></td>
        </tr>
        <tr>
            <td>Address</td>
            <td><%= pe.address %></td>
        </tr>
        <tr>
            <td>Coordinates (Latitutde, Longitude)</td>
            <td><%= String.format("%.2f",pe.latitude) %>, <%= String.format("%.2f",pe.longitude) %></td>
        </tr>
        <tr>
            <td><b>Estimation</b></td>
            <td></td>
        </tr>
        <tr>
            <td>Power Output</td>
            <td></td>
        </tr>
        <tr>
            <td>Duration of Sunshine</td>
            <td></td>
        </tr>
        <tr>
            <td>Daily Electricity Generated</td>
            <td></td>
        </tr>
        <tr>
            <td>Annual Electricity Generated</td>
            <td></td>
        </tr>
        <tr>
            <td>Daily Equivalent Electricity Fee</td>
            <td></td>
        </tr>
        <tr>
            <td><b>Surplus Power</b></td>
            <td></td>
        </tr>
        <tr>
            <td>Daily Electricity Usage</td>
            <td><%= pe.energyUse %> kW*h</td>
        </tr>
        <tr>
            <td>Daily Surplus Electricity</td>
            <td></td>
        </tr>
        <tr>
            <td>Annual Surplus Electricity</td>
            <td></td>
        </tr>
    </table>
</article><!-- end .content -->
<div id="wizardBtn"><input type="button" value="Back"><input type="button" value="Save" <% if(!isLogged){out.print("readonly");} %>></div>
<script type="text/javascript">
<%
QueryPanel q=new QueryPanel();
out.print(q.getJSON());
%>
$(document).ready(function(){
    panelData=eval("("+panels+")");
    num=parseInt(panelData.number);
    $("tr:eq(1)>td:eq(1)").html(panelData.panel[0].model);
    $("tr:eq(6)>td:eq(1)").html(panelData.panel[0].powerOutput+" W");
    //
    var durationOfSunshine=10.0;
    var number=<%= pe.number %>;
    var usage=<%= pe.energyUse %>;
    //
    $("tr:eq(7)>td:eq(1)").html(durationOfSunshine+" Hrs");
    $("tr:eq(8)>td:eq(1)").html(getDailyGen(panelData.panel[0].powerOutput,durationOfSunshine,number).toFixed(2)+" kW*h | "+getDailySales(panelData.panel[0].powerOutput,durationOfSunshine,number).toFixed(2)+" $");
    $("tr:eq(9)>td:eq(1)").html(getAnnualGen(panelData.panel[0].powerOutput,durationOfSunshine,number).toFixed(2)+" kW*h | "+getAnnualSales(panelData.panel[0].powerOutput,durationOfSunshine,number).toFixed(2)+" $");
    $("tr:eq(10)>td:eq(1)").html(getDailyFee(panelData.panel[0].powerOutput,durationOfSunshine,number).toFixed(2)+" $");
    $("tr:eq(12)>td:eq(1)").html(usage+" kW*h");
    $("tr:eq(13)>td:eq(1)").html(getDailySurplus(panelData.panel[0].powerOutput,durationOfSunshine,number,usage).toFixed(2)+" kw*h | "+getDailySurplusFee(panelData.panel[0].powerOutput,durationOfSunshine,number,usage).toFixed(2)+" $");
    $("tr:eq(14)>td:eq(1)").html(getAnnualSurplus(panelData.panel[0].powerOutput,durationOfSunshine,number,usage).toFixed(2)+" kw*h | "+getAnnualSurplusFee(panelData.panel[0].powerOutput,durationOfSunshine,number,usage).toFixed(2)+" $");
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

function getAnnualSurplus(powerOutput,durationOfSunshine,number,usage){
    return getDailySurplus(powerOutput,durationOfSunshine,number,usage)*365;
}

function getDailySurplusFee(powerOutput,durationOfSunshine,number,usage){
    return getDailySurplus(powerOutput,durationOfSunshine,number,usage)*0.12;
}

function getAnnualSurplusFee(powerOutput,durationOfSunshine,number,usage){
    return getDailySurplusFee(powerOutput,durationOfSunshine,number,usage)*365;
}
</script>
<script>
    $("#wizard>span:eq(3)").css("font-weight","bold");
    $("#wizardBtn>input:eq(0)").click(function(){
        window.location.href="geolocation.jsp?panelId=<%= pe.panelId %>&number=<%= pe.number %>&address=<%= pe.address %>&latitude=<%= pe.latitude %>&longitude=<%= pe.longitude %>&energyUse=<%= pe.energyUse %>&roofArea=<%= pe.roofArea %>";
    });
    $("#wizardBtn>input:eq(1)").click(function(){
        window.location.href="savePlan?panelId=<%= pe.panelId %>&number=<%= pe.number %>&address=<%= pe.address %>&latitude=<%= pe.latitude %>&longitude=<%= pe.longitude %>&energyUse=<%= pe.energyUse %>&email=<%= email %>&roofArea=<%= pe.roofArea %>";
    });
</script>
<%@ include file="foot.jsp" %>
