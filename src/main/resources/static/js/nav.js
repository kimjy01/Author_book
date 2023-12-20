$(document).ready(function() {
    // 페이지 로드 시 세션 스토리지에서 저장된 탭을 가져와서 활성화
    var activeTab = sessionStorage.getItem('activeTab');
   if (!activeTab) {
        // 세션 스토리지에 저장된 값이 없는 경우 기본값으로 'home'을 설정
        activeTab = 'home';
        sessionStorage.setItem('activeTab', activeTab);
    }

    // 활성 탭 설정
    $('.nav-bar__item').removeClass('active');
    $('.nav-bar__item.' + activeTab).addClass('active');

    // 해당 탭의 콘텐츠 로드
    loadContent(activeTab);

    // 탭 클릭 시 처리
    $('.nav-bar__item').click(function() {
        var tabClass = $(this).attr('class').split(' ')[1]; // 탭의 클래스 이름을 가져옴
        $(this).addClass('active').siblings().removeClass('active');
        
        // 클릭한 탭을 세션 스토리지에 저장
        sessionStorage.setItem('activeTab', tabClass);

        loadContent(tabClass); // 해당 탭의 콘텐츠를 로드하는 함수 호출
    });

    // 각 탭에 대한 콘텐츠를 로드하는 함수
    function loadContent(tab) {
        $.ajax({
            url: tab,
            dataType: 'html',
            method: 'get',
            data: {}
        }).done(function(data) {
            $('.contents_wrap').html(data);
        });
    }
});