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

    
    let closeButton = document.getElementById('closePopup');
    let openModalBtn = document.querySelectorAll('.profile_image')
    let modal = document.getElementById('modal');

    openModalBtn.forEach(function(popModal) {
		
	    popModal.onclick = function() {
			let authorId = $(this).data('id');
			let author_name = $(this).data('name');
			let author_info = $(this).data('info');
			
			$('.modal_author').text(author_name + '작가님');
		    $('.author_intro').text(author_info);
		    
		    $.ajax({
				url:"author/profile?id="+authorId,
				type : "post",
				success : function(data) {
					var container = $("#authorImageContainer");
					container.empty();
			
			        var imageUrls = Object.values(data);
				
		            for (var i = 0; i < imageUrls.length; i++) {
		              	var imageElement = $('<div class="author_image"><img src="' + imageUrls[i] + '" alt=""></div>');
            			container.append(imageElement);
		            }
			        
			        modal.style.display = 'block';
				},
				error : function() {
					alert("error");
				}
			});
			
	    };
	});
	  closeButton.onclick = function() {
	    modal.style.display = 'none';
	}

});

function subscribeToAuthor(userId, authorId) {
    // AJAX request using jQuery
    $.ajax({
        type: "POST",
        url: "subscribe",
        data: { userId: userId, authorId: authorId },
        success: function () {
            location.reload(true);
        },
        error: function () {
            alert("Subscription failed!");
        }
    });
}

function unsubscribeFromAuthor(userId, authorId) {
    // AJAX request using jQuery
    $.ajax({
        type: "POST",
        url: "unsubscribe",
        data: { userId: userId, authorId: authorId },
        success: function () {
            location.reload(true);
        },
        error: function () {
            alert("Unsubscription failed!");
        }
    });
}