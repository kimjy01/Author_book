$('input[name=query]').keydown(function(evt){
    if(evt.keyCode == 13){ // enter click
        fetchKakaoBooks($(this).val());
    }
});    

function selectBook(title, thumbnail) {
    var authorId = localStorage.getItem('authorId');
    
    console.log(authorId);
    console.log(title);
    console.log(thumbnail);
    
    // AJAX를 사용하여 데이터를 부모 창의 서버로 전송
    $.ajax({
        type: "POST",
        url: "author/book/add",  // 실제로는 데이터를 전송할 서버의 엔드포인트로 변경
        data: {
            book_title: title,
            book_image: thumbnail,
            authorId: authorId
        },
        success: function(response) {
            console.log("Data successfully sent to the server");
            // 원하는 작업 수행
        },
        error: function(error) {
            console.error("Error sending data to the server");
            // 오류 처리
        }
    });
    
    window.close();
}

function fetchKakaoBooks(query){
    $.ajax({
        url: 'https://dapi.kakao.com/v3/search/book?target=person',
        headers: {
            Authorization: 'KakaoAK e3d1c9ffbae324e2c0e1cc31c0ff93c3'
        },
        dataType: 'JSON',
        data: {
            query: query
        }
    }).done(function(data){
        let rs = []; 
        $.each(data.documents, function(k,v){
            let thumbnail = (v.thumbnail == '') ? 'https://dummyimage.com/120x174/333/fff': v.thumbnail;

            rs.push(`
            <div class="col">
            <div class="card mb-3">
                <div class="row g-0" onclick="selectBook('${v.title}', '${thumbnail}')">
                    <div class="col-md-4">
                    	<img src="${thumbnail}" class="img-fluid rounded-start" alt="...">
                    </div>
                    <div class="col-md-8" style="display:flex; align-items:center;">
                        <div class="card-body">
                            <h6 class="card-title">${v.title}</h6>
                            <p class="card-text">${v.authors}</p>
                            <p class="card-text">${v.publisher}</p>
                        </div>
                    </div>
                </div>
            </div>
            </div>
            `);
        });
        $('#pocket').html(rs.join(''));
    });
}