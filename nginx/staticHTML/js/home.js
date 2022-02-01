$(document).ready(function() {
    $('button').button();
    
    $('.container').shapeshift();
    /*$('.container').masonry({
        columnWidth: 300
    });*/
    
    $("img").unveil(200, function() {
        $('.container').trigger("ss-rearrange");
    });
    
    LazyLoad.css('http://fonts.googleapis.com/css?family=Playfair+Display:700|Raleway:300', function() {
        setTimeout(function() {
            $('.container').trigger("ss-rearrange");
        }, 1000);
    })
    
    $('.addCmtInput').click(function() {
        $(this).siblings('.pinCmtBtn').slideDown('fast', function() {
            $('.container').trigger("ss-rearrange");
            //$container.data('masonry').layout();
        });
    });
    
    $('.cancelCmt').click(function() {
        $(this).parent().slideUp('fast', function() {
            $('.container').trigger("ss-rearrange");
            //$('.container').data('masonry').layout();
        });
    });
});
