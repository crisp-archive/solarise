<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css">
body{
	margin:0;
}
</style>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
google.load('visualization', '1.0', {'packages':['corechart']});
google.setOnLoadCallback(drawChart);
function drawChart() {
	var options = {
		'title':'Power Output in Future 10 Years with Panel Efficiency Loss',
		'width':600,
		'height':300,
		hAxis:{title:"Year"},
		vAxis:{title:"kw*h"},
	};
	
  	var data = google.visualization.arrayToDataTable([
        ['Year', 'Eletricity'],
        <%
        Double output=Double.parseDouble(request.getParameter("output"));
        Double loss=Double.parseDouble(request.getParameter("loss"));
        for(int i=0;i<10;i++){
        	//out.print("['"+(i+1)+"',"+(output*Math.pow(1-loss,i)*10.0*3600/3.6/1000000)+","+(output*Math.pow(1-loss,i)*10.0*3600/3.6/1000000*0.08)+"],\n");
        	out.print("['"+(i+1)+"',"+(output*Math.pow(1-loss,i)*10.0*3600/3.6/1000000)+"],\n");
        }
        %>
     ]);

	var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
	chart.draw(data, options);
}
</script>
<div id="chart_div">
</div>