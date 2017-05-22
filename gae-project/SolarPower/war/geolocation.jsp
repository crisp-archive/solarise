<%@ include file="head.jsp" %>
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
<script type="text/javascript" src="https://maps.google.com/maps/api/js?key=AIzaSyCeTCnjWP2I9g7duvziGpPy_Duc6uqoCqg&sensor=false">
</script>
<script>
var geocoder;
var map;
var lat=<%= pe.latitude %>;
var lng=<%= pe.longitude %>;
function initialize() {
    geocoder = new google.maps.Geocoder();
    var latlng = new google.maps.LatLng(lat, lng);
    var mapOptions = {
	    zoom: 8,
        center: latlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);
}

function codeAddress() {
    var address = document.getElementById('address').value;
    geocoder.geocode( { 'address': address}, function(results, status) {
    	if (status == google.maps.GeocoderStatus.OK) {
	        map.setCenter(results[0].geometry.location);
        	var marker = new google.maps.Marker({
	            map: map,
            	position: results[0].geometry.location
        	});
        	map.setZoom(16);
            lat=map.getCenter().lat();
            lng=map.getCenter().lng();
        	document.getElementById("showLat").innerHTML=lat.toFixed(2);
        	document.getElementById("showLng").innerHTML=lng.toFixed(2);
    	} else {
        	alert('Geocode was not successful for the following reason: ' + status);
    	}
	});
}
</script>

  <div id="wizard">
    <span>Getting Started</span> >
    <span>Solar Panels</span> >
    <span>Geolocation</span> >
    <span>Estimation</span>
  </div>
<article class="content">
<div id="map_canvas"></div>
<div>
	<input id="address" type="text" value="<%= pe.address %>" style="width:400px;">
	<input type="button" value="Find Place" onclick="codeAddress()">
</div>
<table id="latlng">
	<tr>
		<td>Latitude</td>
		<td id="showLat"></td>
	</tr>
	<tr>
		<td>Longitude</td>
		<td id="showLng"></td>
	</tr>
</table>
</article><!-- end .content -->
<div id="wizardBtn"><input type="button" value="Back"><input type="button" value="Next"></div>
<script>
	$(document).ready(function(){
		initialize();
		codeAddress();
	});
    $("#wizard>span:eq(2)").css("font-weight","bold");
    $("#wizardBtn>input:eq(0)").click(function(){
    	var address=$("#address").val();
        window.location.href="panels.jsp?panelId=<%= pe.panelId %>&number=<%= pe.number %>&address="+address+"&latitude="+lat+"&longitude="+lng+"&energyUse=<%= pe.energyUse %>&roofArea=<%= pe.roofArea %>";
    });
    $("#wizardBtn>input:eq(1)").click(function(){
    	var address=$("#address").val();
        window.location.href="estimation.jsp?panelId=<%= pe.panelId %>&number=<%= pe.number %>&address="+address+"&latitude="+lat+"&longitude="+lng+"&energyUse=<%= pe.energyUse %>&roofArea=<%= pe.roofArea %>";
    });
</script>
<%@ include file="foot.jsp" %>
