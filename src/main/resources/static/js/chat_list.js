$(document).ready(function () {
    const chatWrapDivs = document.querySelectorAll('.chat_wrap');

    // 각 "chat_wrap" div에 대한 반복문을 실행합니다.
    chatWrapDivs.forEach((chatWrapDiv) => {
    // 현재 "chat_wrap" div 내의 모든 "author" 클래스를 가진 li 요소를 선택합니다.
    const authorLiElements = chatWrapDiv.querySelectorAll('.author');

    // 마지막 "author" li 요소의 hr 요소를 찾아 제거합니다.
    if (authorLiElements.length > 0) {
        const lastAuthorLi = authorLiElements[authorLiElements.length - 1];
        const hrElement = lastAuthorLi.querySelector('hr');
        if (hrElement) {
        lastAuthorLi.removeChild(hrElement);
        }
    }
    });

    
    const closeButton = document.getElementById('closePopup');
    const openModalBtn = document.getElementById('open-modal');
    const modal = document.getElementById('modal');

    if (closeButton && openModalBtn && modal) {
        closeButton.addEventListener('click', () => {
            modal.style.display = 'none';
        });

        openModalBtn.addEventListener('click', function (event) {
            event.preventDefault();
            modal.style.display = 'block';
        });

        window.addEventListener('click', function (event) {
            if (event.target === modal) {
            modal.style.display = 'none';
            }
        });
    }

});