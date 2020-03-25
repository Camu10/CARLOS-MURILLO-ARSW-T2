var app = (function () {
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

    function getCovid19ByCountry(name) {
        apiClient.getCovid19ByCountry(name,_genTableByCountry);

    }

    function getAllCovid19() {
        apiClient.getAllCovid19(_genTableAll);
    }
    
    return {
        getAllCovid19:getAllCovid19,
        getCovid19ByCountry:getCovid19ByCountry,
        _genTableByCountry:_genTableByCountry,
        _genTableAll:_genTableAll,
    }
})();