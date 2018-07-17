<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Google Maps APIs</title>
	<style type="text/css">
    div#map{
    	width:100%;
    	height:350px;
    }
    </style>
</head>

<body>
	<div id="map"></div>

	<script>


	function initMap() {

      var locations = eval('('+'${locations}'+')');
      var center = {lat: locations[0][2], lng: locations[0][3]};


    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 12,
        center: center
      });

    var infowindow =  new google.maps.InfoWindow({});

    var marker, count;

    for (count = 0; count < locations.length; count++) {
        marker = new google.maps.Marker({
          position: new google.maps.LatLng(locations[count][2], locations[count][3]),
          map: map,
          title: locations[count][1]
        });
    google.maps.event.addListener(marker, 'click', (function (marker, count) {
          return function () {
            infowindow.setContent(locations[count][1]);
            infowindow.open(map, marker);
          }
        })(marker, count));
      }
    }

	</script>
	<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDr8ch1qj5vSquEnjcKskq-9zCnK0Hqqhk&callback=initMap"></script>
</body>

</html>