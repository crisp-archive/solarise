<html>
<head>
<script type="text/javascript" src="jquery-1.8.1.min.js"></script>
<style type="text/css">
body{
margin:0;
}
</style>
</head>
<body>
<script type="text/javascript" src="https://maps.google.com/maps/api/js?key=AIzaSyCeTCnjWP2I9g7duvziGpPy_Duc6uqoCqg&sensor=false"></script>
<script>
var geocoder;
var map;
var lat=-27.45178;
var lng=153.01673000000005;
var address;
function initialize() {
    geocoder = new google.maps.Geocoder();
    var latlng = new google.maps.LatLng( lat, lng);
    var mapOptions = {
	    zoom: 8,
        center: latlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);
}

function codeAddress() {
    address = document.getElementById('address').value;
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
    var result = getResult(address,lat,lng);
}
</script>
<div id="map_canvas" style="width:830px;height:450px;"></div>
<div>
	<input id="address" type="text" value="Queensland University of Technology">
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
<script>
$(document).ready(function(){
	initialize();
	codeAddress();
});
</script>
</body></html>