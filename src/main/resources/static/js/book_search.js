$('input[name=query]').keydown(function(evt){
    if(evt.keyCode == 13){ // enter click
        fetchKakaoBooks($(this).val());
    }
});    

function truncatePublisher(isbn) {
    const maxLength = 13;

    if (isbn.length > maxLength) {
        // 만약 13글자 이상이면 뒤에서 13글자를 가져옴
        return isbn.substring(isbn.length - maxLength);
    } else {
        // 13글자 이하이면 앞에서부터 그대로 가져옴
        return isbn;
    }
}

function selectBook(title, authors, isbn, thumbnail) {
    // 부모 창으로 선택한 도서 정보를 전달
    window.opener.document.getElementById('book_name').value = title;
    window.opener.document.getElementById('author_name').value = authors;
    window.opener.document.getElementById('thumbnail').value = thumbnail;
    
    
    const isbn_13 = truncatePublisher(isbn);
    window.opener.document.getElementById('isbn').value = isbn_13;

    window.close();
}

function fetchKakaoBooks(query){
    $.ajax({
        url: 'https://dapi.kakao.com/v3/search/book?target=title',
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
                <div class="row g-0" onclick="selectBook('${v.title}', '${v.authors}', '${v.isbn}', '${thumbnail}')">
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