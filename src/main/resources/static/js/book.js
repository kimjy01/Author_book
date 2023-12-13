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
	let textarea = document.getElementById('writeReview');
	
	textarea.addEventListener('input', function() {
	    //스크롤 높이를 임시로 0으로 설정
	    this.style.height = '0';
	    // 스크롤 높이를 요소의 스크롤 높이에 맞게 늘리기
	    this.style.height = `${this.scrollHeight/1.5}%`;
	    console.log(this.scrollHeight);
	});
	
	
	//4️⃣책 정보 보기 모달창
	let popModal = document.getElementById('popModal');
	let modal = document.getElementById('modal');
	let modalClose = document.getElementById('modalClose');
	
	popModal.onclick = function() {
	    modal.style.display = 'block';
	}
	  modalClose.onclick = function() {
	    modal.style.display = 'none';
	}
	//-------------------------------------------------------------------------
});