$('.nav-bar__item').click(function(){
    $(this).addClass('active').siblings().removeClass('active');
})

$('.challenge').click(function(){
    $.ajax({
        url : '10.ajax1-update.html',
        dataType: 'html',
        method: 'get',
        data: {}
    }).done(function(data){
        $('.col-8').html(data);
    });
});

$('.book').click(function(){
    $.ajax({
        url : '10.ajax1-update.html',
        dataType: 'html',
        method: 'get',
        data: {}
    }).done(function(data){
        $('.col-8').html(data);
    });
});

$('.home').click(function(){
    $.ajax({
        url : '../templates/main.html',
        dataType: 'html',
        method: 'get',
        data: {}
    }).done(function(data){
        $('.col-8').html(data);
    });
});

$('.chat').click(function(){
    $.ajax({
        url : '10.ajax1-update.html',
        dataType: 'html',
        method: 'get',
        data: {}
    }).done(function(data){
        $('.col-8').html(data);
    });
});

$('.mypage').click(function(){
    $.ajax({
        url : '',
        dataType: 'html',
        method: 'get',
        data: {}
    }).done(function(data){
        $('.col-8').html(data);
    });
});