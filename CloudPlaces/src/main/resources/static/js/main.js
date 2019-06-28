'use strict';

(function ($) {
    
    /*------------------
        Slick Nav
    --------------------*/
    $(".main-menu").slicknav({
        prependTo: '#mobile-menu-wrap',
        allowParentLinks: true
    });

    /*------------------
        Preloader
    --------------------*/
    $(window).on('load', function () {
        $(".loader").fadeOut();
        $("#preloder").delay(400).fadeOut("slow");
    });

    /*------------------
        Background Set
    --------------------*/
    $('.set-bg').each(function () {
        var bg = $(this).data('setbg');
        $(this).css('background-image', 'url(' + bg + ')');
    });

    /*------------------
        Nice Select
    --------------------*/
    $(document).ready(function () {
        $('.filter-location').niceSelect();
    });

    $(document).ready(function () {
        $('.filter-property').niceSelect();
    });

    $(document).ready(function () {
        $('.date-select').niceSelect();
    });

    /*------------------
        Carousel Slider
    --------------------*/
    $('.slider-active').owlCarousel({
        items: 1,
        dots: false,
        nav: true,
        loop: true,
        navText: ["<i class='fa fa-angle-left'></i>", "<i class='fa fa-angle-right'></i>"]
    });

    /*--------------------------------
        Property Image Carousel Slider
    -----------------------------------*/
    $('.property-img').owlCarousel({
        items: 1,
        dots: false,
        nav: true,
        loop: true,
        navText: ["<i class='fa fa-angle-left'></i>", "<i class='fa fa-angle-right'></i>"]
    });

    /*--------------------------------
        Price Slider
    -----------------------------------*/
    $("#slider-range").slider({
        range: true,
        min: 100,
        max: 500,
        step: 50,
        value: 50,
        values: [100, 500],
        slide: function (event, ui) {
            $('#slider-range .slider-left').text(ui.values[0] + '$');
            $('#slider-range .slider-right').text(ui.values[1] + '$');
        }
    });

    /*--------------------------------
        Price Slider
    -----------------------------------*/

    $("#slider-range-bedrooms").slider({
        range: true,
        min: 1,
        max: 6,
        step: 1,
        value: 50,
        values: [1, 6],
        slide: function (event, ui) {
            $('#slider-range-bedrooms .slider-left').text(" " + ui.values[0] + " ");
            $('#slider-range-bedrooms .slider-right').text(" " + ui.values[1] + " ");
        }
    }); 

})(jQuery);