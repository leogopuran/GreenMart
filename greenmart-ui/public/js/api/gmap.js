var myLat;
var myLng;
var currentLatlng;

function initMap() {

    var map;
    var marker;
    currentLatlng = getCurrentLocation();
    var geocoder = new google.maps.Geocoder();
    var infowindow = new google.maps.InfoWindow();



    function getCurrentLocation() {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position){

            myLat = position.coords.latitude;
            myLng = position.coords.longitude;
        });

//        currentLatlng = new google.maps.LatLng(parseFloat(myLat), parseFloat(myLng));
        currentLatlng = new google.maps.LatLng(20.268455824834792, 85.84099235520011);
        return currentLatlng;

      } else {
        alert("Geolocation is not supported by this browser.");
      }
    }


    var mapOptions = {
        zoom: 18,
        center: currentLatlng
    };

    map = new google.maps.Map(document.getElementById("map"), mapOptions);

    const input = document.getElementById("desiredLocation");
    console.log("input : " + input);
    const autocomplete = new google.maps.places.Autocomplete(input);
    autocomplete.bindTo("bounds", map);
    autocomplete.setFields(["place_id", "geometry", "name", "formatted_address"]);

    marker = new google.maps.Marker({
        map: map,
        position: new google.maps.LatLng(myLat, myLng),
        draggable: true
    });

    geocoder.geocode({
        'latLng': currentLatlng
    }, function(results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
            if (results[0]) {
                $('#latitude,#longitude').show();
                $('#address').val(results[0].formatted_address);
                //                $('#latitude').val(marker.getPosition().lat());
                //                $('#longitude').val(marker.getPosition().lng());
                infowindow.setContent(results[0].formatted_address);
                infowindow.open(map, marker);
            }
        }
    });

    marker.addListener('dragend', function() {
        console.log("listner invoked");
        geocoder.geocode({
            'latLng': marker.getPosition()
        }, function(results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                if (results[0]) {
                    $('#address').val(results[0].formatted_address);
                    $('#latitude').val(marker.getPosition().lat());
                    $('#longitude').val(marker.getPosition().lng());
                    myLat = marker.getPosition().lat();
                    myLng = marker.getPosition().lng();

                    console.log("Lat : " + myLat + " | Lng : " + myLng);
                    console.log("Address : " + results[0].formatted_address);

                    $("#desiredLocation").val(results[0].formatted_address);
                }
            }
        });
    });

    autocomplete.addListener("place_changed", () => {
        infowindow.close();
        const place = autocomplete.getPlace();

        if (!place.place_id) {
            return;
        }
        geocoder.geocode({
            placeId: place.place_id
        }, (results, status) => {
            if (status !== "OK") {
                window.alert("Geocoder failed due to: " + status);
                return;
            }
            map.setZoom(11);
            map.setCenter(results[0].geometry.location);

            myLat = results[0].geometry.location.lat();
            myLng = results[0].geometry.location.lng();

            console.log("Lat : " + myLat + " | Lng : " + myLng);


            marker.setMap(map);
            marker.setDraggable(true);
            marker.setPosition(new google.maps.LatLng(myLat, myLng));

        });
    });

}

function getProductLocationInfo(){

    var productLocationInfo = {
            "productLocationLat":myLat,
            "productLocationLng":myLng,
            "productLocationAddress":$("#desiredLocation").val()
        };

    return productLocationInfo;

}

function getCustomerLocationInfo(){

    var customerLocationInfo = {
            "customerLocationLat":myLat,
            "customerLocationLng":myLng,
            "customerLocationAddress":$("#desiredLocation").val()
        };

    return customerLocationInfo;

}