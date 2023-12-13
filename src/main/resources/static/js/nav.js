$('.nav-bar__item').click(function(){
    $(this).addClass('active').siblings().removeClass('active');
})

$('.challenge').click(function(){
    $.ajax({
        url : 'challenge',
        dataType: 'html',
        method: 'get',
        data: {}
    }).done(function(data){
        $('.contents_wrap').html(data);
    });
});

$('.book').click(function(){
    $.ajax({
        url : 'book',
        dataType: 'html',
        method: 'get',
        data: {}
    }).done(function(data){
        $('.contents_wrap').html(data);
    });
});

$('.home').click(function(){
    $.ajax({
        url : 'home',
        dataType: 'html',
        method: 'get',
        data: {}
    }).done(function(data){
        $('.contents_wrap').html(data);
    });
});

$('.chat').click(function(){
    $.ajax({
        url : 'chatList',
        dataType: 'html',
        method: 'get',
        data: {}
    }).done(function(data){
        $('.contents_wrap').html(data);
    });
});

$('.mypage').click(function(){
    $.ajax({
        url : 'mypage',
        dataType: 'html',
        method: 'get',
        data: {}
    }).done(function(data){
        $('.contents_wrap').html(data);
    });
});