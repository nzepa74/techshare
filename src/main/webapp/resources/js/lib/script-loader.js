$(document).ready(function () {

    if (document.URL.search("companyRegistration") > 1)
        scriptLoader("/resources/js/techshare/companyRegistration.js");

});

var scriptLoader = function (url) {
    $.ajax(
        {
            type: "GET",
            url: url,
            dataType: "script",
            cache: false
        });
};

