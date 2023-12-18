let book_name;
let author_name;
let isbn;
let user_id;
let thumbnail;
let review_content;

$(document).ready(function () {
	
	//1️⃣책의 선반이 자동으로 생기게 js 코드
	let bookStoreElement  = document.querySelector('.bookStoreElement');
	let lines = document.querySelector('.lines');
	let books = bookStoreElement.querySelectorAll('.books');
	
	// bookStores 내 div 요소의 개수.
	let numberOfDivs = books.length;
	
	// lines에 추가할 div 개수를 계산합니다.
	let numberOfLines = Math.ceil(numberOfDivs / 3); // 줄 개수를 계산
	
	// lines에 div를 추가
	for (let i = 0; i < numberOfLines; i++) {
	    let lineDiv = document.createElement('div');
	    lineDiv.classList.add('line');
	    lines.appendChild(lineDiv);
	}
	//-------------------------------------------------------------------------
	
	//2️⃣책 추가하기 모달창 코드
	let addModalBtn = document.getElementById('popAddModal');
	let addModal = document.getElementById('addModal');
	let addModalClose = document.getElementById('addModalClose');
	
	addModalBtn.onclick = function() {
	    addModal.style.display = 'block';
	    const addMo = event.currentTarget;
	    
	    id = addMo.dataset.id;
	    
	    document.querySelector('#userId').value = id;
	}
	  addModalClose.onclick = function() {
	    addModal.style.display = 'none';
	}
	// ✔ 여기 이 코드는 있으면 배경 선택 시 모달 팝업이 사라짐. 없으면 무조건 닫기 버튼으로만 모달창을 내림
	//   window.onclick = function(event) {
	//     if (event.target == addModal) {
	//     addModal.style.display = "none";
	//     }
	//   }
	//-------------------------------------------------------------------------
	
	//3️⃣textarea 늘리기 코드
	let textareas = document.querySelectorAll('.writeReview');
	
	textareas.forEach(function(textarea) {
	    textarea.addEventListener('input', function() {
	        // 스크롤 높이를 임시로 0으로 설정
	        this.style.height = '0';
	        // 스크롤 높이를 요소의 스크롤 높이에 맞게 늘리기
	        this.style.height = `${this.scrollHeight / 1.5}px`; // px 단위 추가
	        console.log(this.scrollHeight);
	    });
	});
	
	
	//4️⃣책 정보 보기 모달창
	let popModals = document.querySelectorAll('#popModal');
	let modal = document.getElementById('modal');
	let modalClose = document.getElementById('modalClose');
	let del_book = document.querySelector('.delete');
	
	popModals.forEach(function(popModal) {
		
	    popModal.onclick = function() {
	        
	        book_name = $(this).data("title");
	        author_name = $(this).data("author");
	        thumbnail = $(this).data("thumbnail");
	        isbn = $(this).data("isbn");
	        review_content = $(this).data("review");
	        user_id = $(this).data("userid");
	        
	        id = $(this).data("id");
	        
	        $(".modal_author").text(book_name);
	        $(".bookAuthorName").text(author_name);
	        $(".bookReview").text(review_content);

	        modal.style.display = 'block';
			
			del_book.onclick = function() {
				location.href='/book/delete?id=' + id;
			}
	        
	    };
	});
	  modalClose.onclick = function() {
	    modal.style.display = 'none';
	}
	
	//5 책 정보 수정 모달창
	let upModalbtn = document.querySelector('.update');
	let upmodal = document.getElementById('upModal');
	let upmodalClose = document.getElementById('upModalClose');
	
	upModalbtn.onclick = function() {
		
		let info = document.querySelectorAll('.info');
		
		info[0].value = book_name;
		info[1].value = author_name;
		info[2].value = thumbnail;
		info[3].value = isbn;
		info[4].value = review_content;
		info[5].value = user_id;
		
		let book = document.querySelector('.bookId');
		book.value = id;
		
		$('#upForm').attr('action', 'book/update?id='+id);
		
	    upmodal.style.display = 'block';
	    
	}
	  upmodalClose.onclick = function() {
	    upmodal.style.display = 'none';
	}
	//-------------------------------------------------------------------------
	
});

function openNewWindow() {
    var newWindow = window.open('bookSearch', 'Popup', 'width=800, height=500');
    
    // 팝업이 차단되었을 경우에 대한 처리
    if (!newWindow || newWindow.closed || typeof newWindow.closed == 'undefined') {
        alert('팝업이 차단되었습니다. 팝업 차단을 해제해주세요.');
    }
}