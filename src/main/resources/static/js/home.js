$(document).ready(function() {
    $('.book_title').each(function() {
      const text = $(this).text().trim();
      const textLength = text.length;
  
      if (textLength > 8) {
        // 글자 수가 8을 넘어가면 말줄임표를 추가
        const truncatedText = text.slice(0, 8) + '...';
        $(this).text(truncatedText);
      }
  
      if (textLength > 5) {
        const parentDiv = $(this).closest('.book_item');
        if (parentDiv.length > 0) {
          const randomPercentage = Math.floor(Math.random() * 41) + 60;
          parentDiv.css('height', randomPercentage + '%');
        }
      }
    });
});
  