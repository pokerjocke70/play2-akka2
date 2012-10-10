var appTemplates = (function () {
    "use strict";

    var templateNames = new Array('city', 'towns');

    $(document).ready(function () {
        //Load the mustache template
        loadTemplates();
    });

    $('#b1').live('click', function () {
        loadCities();
    });

    $('#b2').live('click', function () {
        doGet('/profile.json');
    });

    function loadTemplates() {

        $.each(templateNames, function(i, name){
            $.get('/assets/html/' + name + '.html', function (data) {
                ich.addTemplate(name, data)
            });
        });

    }


    function loadCities() {
        $.getJSON('/cities.json', function (cities) {
            $('#body').html(ich.city(cities));
        });
    }

    function doGet(url){
        $.getJSON(url, function (data) {

        });
    }

}());