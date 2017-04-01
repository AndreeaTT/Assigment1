;(function($) {
    "use strict";
    $(document).ready(function() {
        $('#access').on('touchstart click', '.skip-link', function(event) {
            $(this).toggleClass('focus');
            $($(this).attr('href')).toggleClass('target');
            event.preventDefault();
        }).find('.skip-link').append('<span>'+$('#menu .active').text()+'</span>');
    });
})(jQuery);/**
 * Created by Andreea ADM on 3/31/2017.
 */
