document.addEventListener('DOMContentLoaded', function () {
    if (document.querySelectorAll('#map').length > 0)
    {
        if (document.querySelector('html').lang)
            lang = document.querySelector('html').lang;
        else
            lang = 'en';

        var js_file = document.createElement('script');
        js_file.type = 'text/javascript';
        js_file.src = 'https://maps.googleapis.com/maps/api/js?&signed_in=true&language=' + lang;
        document.getElementsByTagName('head')[0].appendChild(js_file);
    }
});
var app = (function () {
    var map;
    var markers;
    var bounds;
    var _deaths;
    var _confirmed;
    var _recovered;
    function _number(provinces) {
        var total = provinces.reduce(function (total, value) {
            return total + value.deaths;
        }, 0);
        _deaths = total;

        var total = provinces.reduce(function (total, value) {
            return total + value.confirmed;
        }, 0);
        _confirmed = total;

        var total = provinces.reduce(function (total, value) {
            return total + value.recovered;
        }, 0);
        _recovered = total;
    };

    var _genTableByCountry = function(info){
        $("#table > tbody").empty();
        info.map(function(province){
            $("#table > tbody").append(
                "<tr> <td>" +
                province.province  +
                "</td>" +
                "<td>" +
                province.deaths +
                "</td>" +
                "<td>" +
                province.confirmed +
                "</td>" +
                "<td>" +
                province.recovered +
                "</td></tr>"
            );
        });
    };

    var _genTableAll = function(info){
        $("#tableAll > tbody").empty();
        info.map(function(country){
            $("#tableAll > tbody").append(
                "<tr> <td>" +
                country.name +
                "</td>" +
                "<td>" +
                country.deaths +
                "</td>" +
                "<td>" +
                country.confirmed +
                "</td>" +
                "<td>" +
                country.recovered +
                "</td></tr>"
            );
        });
    };

    var _genTableCount = function(info){
        _number(info);
        $("#tableTotal > tbody").empty();
        $("#tableTotal > tbody").append(
            "<tr> <td> Num Deaths</td>" +
            "<td>" +
            _deaths +
            "</td></tr>"
        );
        $("#tableTotal > tbody").append(
            "<tr> <td> Num Infected</td>" +
            "<td>" +
            _confirmed +
            "</td></tr>"
        );
        $("#tableTotal > tbody").append(
            "<tr> <td> Num Cured</td>" +
            "<td>" +
            _recovered +
            "</td></tr>"
        );
    };

    function plotMarkers(m) {
        _genTableByCountry(m);
        _genTableCount(m);
        markers = [];
        bounds = new google.maps.LatLngBounds();

        m.map(function (marker) {
            var position = new google.maps.LatLng(marker.location.latitude, marker.location.longitude);

            markers.push(
                new google.maps.Marker({
                    position: position,
                    map: map,
                    animation: google.maps.Animation.DROP
                })
            );

            bounds.extend(position);
        });

        map.fitBounds(bounds);
    }

    function clearMap(){
        if (markers){
            markers.forEach(function (marker) {
                marker.setMap(null);
            });
        }
    }

    function initMap(){
        getAllCovid19();
        map = new google.maps.Map(document.getElementById('map'), {
            center: {lat: -34.397, lng: 150.644},
            zoom: 100
        });

    };

    function getCovid19ByCountry(name) {
        apiClient.getCovid19ByCountry(name,plotMarkers);

    }

    function getAllCovid19() {
        apiClient.getAllCovid19(_genTableAll);
    }

    
    return {
        getAllCovid19:getAllCovid19,
        getCovid19ByCountry:getCovid19ByCountry,
        _genTableByCountry:_genTableByCountry,
        _genTableAll:_genTableAll,
        _genTableCount:_genTableCount,
        initMap:initMap,
        plotMarkers:plotMarkers,
    }
})();