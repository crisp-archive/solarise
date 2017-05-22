<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.solarise.server.QueryRecord" %>
<%@ page import="com.solarise.entity.RecordEntity" %>
<style type="text/css">
body{
	margin:0;
}
</style>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
<%
	boolean isLogged=false;
      String email="";
      Cookie[] c=request.getCookies();
      if(c!=null){
        for(int i=0;i<c.length;i++){
          if(c[i].getName().equals("email")){
            email=c[i].getValue();
            isLogged=true;
            break;
          }
        }
      }

	QueryRecord q=new QueryRecord(email);
	out.print(q.getJSON());
%>
recordData=eval("("+records+")");
rNum=parseInt(recordData.number);
projectId=<%= request.getParameter("projectId") %>;
google.load('visualization', '1.0', {'packages':['corechart']});
google.setOnLoadCallback(drawChart);
function drawChart() {
	var options = {
		'title':'Eletricity Generated Tracker',
		'width':600,
		'height':300,
		vAxis:{title:"kw*h"},
	};
	
  	var data = new google.visualization.DataTable();
  // Add columns
  	data.addColumn('string', 'Month after deployment');
  	data.addColumn('number', 'Eletricity');
        
	count=0;
	for(i=0;i<rNum;i++){	
		if(projectId==recordData.record[i].projectId){
			count++;
			data.addRow(['#'+count,parseFloat(recordData.record[i].data) ] );
		}
		else
			continue;
	}
	var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
	chart.draw(data, options);

	var options2 = {
		'title':'Money Saved Tracker',
		'width':600,
		'height':300,
		vAxis:{title:"$"},
	};
	var data2 = new google.visualization.DataTable();
  // Add columns
  	data2.addColumn('string', 'Month after deployment');
  	data2.addColumn('number', 'Money Saved');
        
	count=0;
	for(i=0;i<rNum;i++){	
		if(projectId==recordData.record[i].projectId){
			count++;
			data2.addRow(['#'+count,parseFloat(recordData.record[i].data)*0.08 ] );
		}
		else
			continue;
	}
	var chart2 = new google.visualization.ColumnChart(document.getElementById('chart_div2'));
	chart2.draw(data2, options2);
}
</script>
<div id="chart_div">
</div>
<div id="chart_div2">
</div>