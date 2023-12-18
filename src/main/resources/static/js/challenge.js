
//1️⃣달력 코드------------------------------------------------------------------------- 
let nowMonth = new Date();  // 현재 달을 페이지를 로드한 날의 달로 초기화
let today = new Date();     // 페이지를 로드한 날짜를 저장
today.setHours(0, 0, 0, 0);    // 비교 편의를 위해 today의 시간을 초기화

// 달력 생성 : 해당 달에 맞춰 테이블을 만들고, 날짜를 채워 넣는다.
function buildCalendar() {

    let firstDate = new Date(nowMonth.getFullYear(), nowMonth.getMonth(), 1);     // 이번달 1일
    let lastDate = new Date(nowMonth.getFullYear(), nowMonth.getMonth() + 1, 0);  // 이번달 마지막날

    let tbody_Calendar = document.querySelector(".Calendar > tbody");
    document.getElementById("calYear").innerText = nowMonth.getFullYear();             // 연도 숫자 갱신           
    document.getElementById("calMonth").innerText = leftPad(nowMonth.getMonth() + 1);  // 월 숫자 갱신
    document.getElementById("change").innerText = leftPad(nowMonth.getMonth() + 1)+"월 "+nowMonth.getFullYear(); //몇월 몇년 타이틀 부분 추가

    while (tbody_Calendar.rows.length > 0) {                        // 이전 출력결과가 남아있는 경우 초기화
        tbody_Calendar.deleteRow(tbody_Calendar.rows.length - 1);
    }

    let nowRow = tbody_Calendar.insertRow();        // 첫번째 행 추가           

    for (let j = 0; j < firstDate.getDay(); j++) {  // 이번달 1일의 요일만큼
        let nowColumn = nowRow.insertCell();        // 열 추가
    }

    for (let nowDay = firstDate; nowDay <= lastDate; nowDay.setDate(nowDay.getDate() + 1)) {   // day는 날짜를 저장하는 변수, 이번달 마지막날까지 증가시키며 반복  

        let nowColumn = nowRow.insertCell();        // 새 열을 추가하고


        let newDIV = document.createElement("p");
        newDIV.innerHTML = leftPad(nowDay.getDate());        // 추가한 열에 날짜 입력
        nowColumn.appendChild(newDIV);

        if (nowDay.getDay() == 6) {                 // 토요일인 경우
            nowRow = tbody_Calendar.insertRow();    // 새로운 행 추가
        }

        if (nowDay < today) {                       // 지난날인 경우
            // newDIV.className = "pastDay";
            newDIV.className = "futureDay";
            newDIV.onclick = function () { choiceDate(this); }
        }
        else if (nowDay.getFullYear() == today.getFullYear() && nowDay.getMonth() == today.getMonth() && nowDay.getDate() == today.getDate()) { // 오늘인 경우           
            newDIV.className = "today";
            newDIV.onclick = function () { choiceDate(this); }
        }
        else {                                      // 미래인 경우
            newDIV.className = "futureDay";
            newDIV.onclick = function () { choiceDate(this); }
        }
    }
}

// 이전달 버튼 클릭
function prevCalendar() {
    nowMonth = new Date(nowMonth.getFullYear(), nowMonth.getMonth() - 1, nowMonth.getDate());   // 현재 달을 1 감소
    buildCalendar();    // 달력 다시 생성
}
// 다음달 버튼 클릭
function nextCalendar() {
    nowMonth = new Date(nowMonth.getFullYear(), nowMonth.getMonth() + 1, nowMonth.getDate());   // 현재 달을 1 증가
    buildCalendar();    // 달력 다시 생성
}

// 날짜 선택
function choiceDate(newDIV) {
    if (document.getElementsByClassName("choiceDay")[0]) {                              // 기존에 선택한 날짜가 있으면
        document.getElementsByClassName("choiceDay")[0].classList.remove("choiceDay");  // 해당 날짜의 "choiceDay" class 제거
    }
    newDIV.classList.add("choiceDay");           // 선택된 날짜에 "choiceDay" class 추가
}

// input값이 한자리 숫자인 경우 앞에 '0' 붙혀주는 함수
function leftPad(value) {
    if (value < 10) {
        value = "0" + value;
        return value;
    }
    return value;
}

buildCalendar();

//달력 코드 끝-------------------------------------------------------------------------
$(document).ready(function () {	
	//3️⃣챌린지 추가하기 모달창-------------------------------------------------------------------------
	let popChallengeModal = document.getElementById('popChallengeModal');
	let challengeModal = document.getElementById('challengeModal');
	let challengeModalClose = document.getElementById('challengeModalClose');
	
	popChallengeModal.onclick = function() {
		
		const id = $(this).data("id");
	    
	    document.querySelector('#userId').value = id;
		
	    challengeModal.style.display = 'block';
	}
	challengeModalClose.onclick = function() {
	    challengeModal.style.display = 'none';
	}
	//챌린지 추가하기 모달창 끝-------------------------------------------------------------------------
	
	
	//4️⃣어서북 챌린지에서 책 이름 길면 잘리도록 함... 왜냐면 css 처리가 좀 이상해져서...-------------------------------------------------------------------------
	let otherbookNameElements = document.querySelectorAll('.otherbookName b');
	
	otherbookNameElements.forEach(element => {
	    let textContent = element.textContent;
	
	    if (textContent.length >9) {
	        let truncatedText = textContent.substring(0, 9) + '····';
	        element.textContent = truncatedText;
	    }
	});
	//끝-------------------------------------------------------------------------
	
	
	//5️⃣마찬가지로 어서북 챌린지에서 '~와 함께하는' 부분 길면 잘리도록 함... => 이거 ~와 함께하는 말고 작가 이름으로 하는 건....?
	let otherBookTitle = document.querySelectorAll('.otherBookTitle');
	
	otherBookTitle.forEach(element => {
	    let textContent = element.textContent;
	
	    if (textContent.length >9) {
	        let truncatedText = textContent.substring(0, 9) + '····';
	        element.textContent = truncatedText;
	    }
	});
	//끝-------------------------------------------------------------------------
	
	
	//6️⃣완독하기 누르면 목표페이지 막기-------------------------------------------------------------------------
	let bookAllCheckbox = document.getElementById('bookAll');
	let textInput = document.getElementById('textInput');
	
	// 체크박스 변경 이벤트 리스너 추가
	bookAllCheckbox.addEventListener('change', function() {
	    if (bookAllCheckbox.checked) {
	        // 체크된 경우 input을 읽기 전용으로 설정하고 값을 초기화
	        textInput.readOnly = true;
	        textInput.value = '완독'; // 읽기 전용 시 값 초기화
	        textInput.style.color = 'gray';
	    } else {
	        // 체크가 해제된 경우 input을 읽기/쓰기 가능하게 설정
	        textInput.readOnly = false;
	        textInput.style.color = 'black';
	    }
	});
	//완독하기 누르면 목표페이지 막기 끝-------------------------------------------------------------------------

});

//2️⃣오늘의 챌린지 체크박스 클릭시 이미지 바꾸기 바뀌기-------------------------------------------------------------------------
function changeCheck(img, todoId) {
    let src = img.src;
    let name = src.substring(src.lastIndexOf('/')+1, src.length + 1);
    
    // AJAX 요청
    $.ajax({
        type: 'POST',
        url: `todo/${todoId}`, // API 엔드포인트 경로
        data: { id: todoId },
        success: function(response) {
            // 서버에서 성공적으로 응답을 받은 경우
            if (name === "uncheck.png") {
                img.setAttribute("src", "../../../static/image/check.png");
                img.nextElementSibling.classList.add('textLine');
            } else if (name === "check.png") {
                img.setAttribute("src", "../../../static/image/uncheck.png");
                img.nextElementSibling.classList.remove('textLine');
            }
        },
        error: function(error) {
            console.error('Error toggling check:', error);
        }
    });
 
}

function deleteTodo() {
    var todoId = event.target.getAttribute('data-todo-id');
    location.href='/todo/delete?id=' + todoId;
}