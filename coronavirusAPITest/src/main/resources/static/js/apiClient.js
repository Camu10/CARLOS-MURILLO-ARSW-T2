var apiClient = (function () {
    var getCovid19ByCountry = (function (name,callback) {

        axios({
            method: 'GET',
            url: '/v1/stats/'+name,

        })
            .then(response => callback(response.data))
            .catch(error => console.log(error));
    });

    var getAllCovid19 = (function (callback) {

        axios({
            method: 'GET',
            url: '/v1/stats/',

        })
            .then(response => callback(response.data))
            .catch(error => console.log(error));
    });

    return{
        getCovid19ByCountry:getCovid19ByCountry,
        getAllCovid19:getAllCovid19,
    }
})();