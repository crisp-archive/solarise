<%@ include file="head.jsp" %>
<%@ page import="com.solarise.server.QueryRecord" %>
<%@ page import="com.solarise.entity.RecordEntity" %>
<%
    if(!isLogged){
        response.sendRedirect("login.jsp?redir=tracker.jsp");
    }
    QueryRecord q=new QueryRecord(email);
%>
<article class="content">
    <table>
        <tr>
            <td><b>Saved Records</b></td>
            <td><select></select></td>
        </tr>
        <tr>
            <td>Project</td>
            <td></td>
        </tr>
        <tr>
            <td>Data</td>
            <td></td>
        </tr>
        <tr>
            <td>Comment</td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="button" id="showChart" value="Show Graphics"></td>
        </tr>
    </table>
    <iframe id="e_chart" src="" style="border:0;width:600px;height:300px;">
    </iframe>
    <iframe id="m_chart" src="" style="border:0;width:600px;height:300px;">
    </iframe>
</article><!-- end .content -->
<script type="text/javascript">
<%= q.getJSON() %>
$(document).ready(function(){
    recordData=eval("("+records+")");
    
    rNum=parseInt(recordData.number);
    if(rNum==0){
        $("select").append("<option>No records saved.</option>");
    }
    else{
        for(i=0;i<rNum;i++){
            $("select").append("<option value=\""+i+"\">#"+i+"</option>");
        }
        showRecord(0);
    }
});

$("select:eq(0)").change(function(){
    idx=$("option:selected").index();
    showRecord(idx);
});

function showRecord(i){
    $("tr:eq(1)>td:eq(1)").html("#"+recordData.record[i].projectId);
    $("tr:eq(2)>td:eq(1)").html(recordData.record[i].data);
    $("tr:eq(3)>td:eq(1)").html(recordData.record[i].comment);
}

$("input#showChart").click(function(){
	var i=$("option:selected").index();
	window.open("recordChart.jsp?projectId="+recordData.record[i].projectId);
});
</script>
<%@ include file="foot.jsp" %>
