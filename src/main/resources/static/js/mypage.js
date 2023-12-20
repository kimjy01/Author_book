let author_info;

$(document).ready(function () {
	
	//2️⃣책 추가하기 모달창 코드
	let addModalBtn = document.getElementById('popAddModal');
	let addModal = document.getElementById('addModal');
	let addModalClose = document.getElementById('addModalClose');
	
	addModalBtn.onclick = function() {
	    addModal.style.display = 'block';
	    const addMo = event.currentTarget;
	    
	    id = addMo.dataset.id;
	    
	    var newAction = "info/add?id=" + id; 
    	$("#addForm").attr("action", newAction);
    	
	}
	  addModalClose.onclick = function() {
	    addModal.style.display = 'none';
	}
	
	//5 책 정보 수정 모달창
	let upModalbtn = document.querySelector('.update');
	let upmodal = document.getElementById('upModal');
	let upmodalClose = document.getElementById('upModalClose');
	
	upModalbtn.onclick = function() {
		
		const upMo = event.currentTarget;
	    
	    id = upMo.dataset.id;
	    
	    $("#author_info_up").val(upMo.dataset.info);
	    
	    var upAction = "info/add?id=" + id; 
    	$("#upForm").attr("action", upAction);

	    upmodal.style.display = 'block';
	    
	}
	  upmodalClose.onclick = function() {
	    upmodal.style.display = 'none';
	}
	//-------------------------------------------------------------------------
	
});

function openNewWindow(authorId) {
    localStorage.setItem('authorId', authorId);
    var newWindow = window.open('authorSearch', 'Popup', 'width=800, height=500');
    
    if (!newWindow) {
        alert("팝업 창이 차단되었습니다. 팝업 차단을 해제하고 다시 시도하세요.");
    }
}