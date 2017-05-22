<%@ include file="head.jsp" %>
<%@ page import="com.solarise.server.QueryPlan" %>
<%@ page import="com.solarise.entity.PlanEntity" %>
<%@ page import="com.solarise.server.QueryPanel" %>
<%@ page import="com.solarise.entity.PanelEntity" %>
<%
    if(!isLogged){
        response.sendRedirect("login.jsp?redir=projects.jsp");
    }
    QueryPanel qpanel=new QueryPanel();
    QueryPlan qplan=new QueryPlan(email);
%>
<article class="content">
    <table>
        <tr>
            <td><b>Saved Project</b></td>
            <td><select></select></td>
        </tr>
        <tr>
            <td>Panel Name</td>
            <td></td>
        </tr>
        <tr>
            <td>Number</td>
            <td></td>
        </tr>
        <tr>
            <td>Address</td>
            <td></td>
        </tr>
        <tr>
            <td>Coordinates (Latitutde, Longitude)</td>
            <td></td>
        </tr>
        <tr>
            <td>Roof Area</td>
            <td></td>
        </tr>
        <tr>
            <td>Energy Usage</td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <span class="prj_btn">
                    <form method="post" action="addTracker.jsp">
                        <input type="submit" value="Add Tracker">
                        <input type="hidden" name="projectId" value="0">
                    </form>
                </span>
                <span class="prj_btn">
                    <form method="post" action="deletePlan" onsubmit="return confirm_del()">
                        <input type="submit" value="Delete Project">
                        <input type="hidden" name="projectId" value="0">
                        <input type="hidden" name="email" value="<%= email %>">
                    </form>
                </span>
                <span class="prj_btn">
                    <form method="post" action="compare.jsp">
                        <input type="submit" value="Compare with...">
                        <select name="withProjectId"></select>
                        <input type="hidden" name="projectId" value="0">
                    </form>
                </span>
            </td>
        </tr>
    </table>
</article><!-- end .content -->
<script type="text/javascript">
<%= qpanel.getJSON() %>
<%= qplan.getJSON() %>
$(document).ready(function(){
    panelData=eval("("+panels+")");
    planData=eval("("+plans+")");
    
    planNum=parseInt(planData.number);
    if(planNum==0){
        $("select").append("<option>No projects saved.</option>");
    }
    else{
        for(i=0;i<planNum;i++){
            $("select").append("<option value=\""+i+"\">#"+i+"</option>");
        }
        showPlan(0);
    }
});

$("select:eq(0)").change(function(){
    idx=$("option:selected").index();
    showPlan(idx);
});

function showPlan(i){
    $("tr:eq(1)>td:eq(1)").html(panelData.panel[planData.plan[i].panelId].manufacturer+" "+panelData.panel[planData.plan[i].panelId].model);
    $("tr:eq(2)>td:eq(1)").html(planData.plan[i].number);
    $("tr:eq(3)>td:eq(1)").html(planData.plan[i].address);
    $("tr:eq(4)>td:eq(1)").html(parseFloat(planData.plan[i].latitude).toFixed(2)+", "+parseFloat(planData.plan[i].longitude).toFixed(2));
    $("tr:eq(5)>td:eq(1)").html(planData.plan[i].roofArea+" square meters");
    $("tr:eq(6)>td:eq(1)").html(planData.plan[i].energyUse+" kw*h");
    $("input:eq(1)").val(idx);
    $("input:eq(3)").val(idx);
    $("input:eq(6)").val(idx);
}

function confirm_del(){
    var isDel=confirm("Are you sure to delete this project?\n\nWarning: If you delete this project, all the trackers of it will be deleted at the same time.");
    return isDel;
}
</script>
<%@ include file="foot.jsp" %>
