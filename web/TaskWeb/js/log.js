$(function () {
    $(".dropdown-menu li a").click(function () {
        $("#dropdown-button").text($(this).text());
        $("#dropdown-button").val($(this).text());
    });
});

