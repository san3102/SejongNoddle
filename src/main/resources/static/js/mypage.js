    // 삭제 버튼을 클릭했을 때 호출되는 함수
    $('.delete_btn').on('click', function () {
        var postId = $(this).find('.delete_post').data('post-id'); // 해당 게시물의 ID 가져오기

        var csrfToken = $("meta[name='_csrf']").attr("content"); // 삭제를 위한 csrf 토큰 받아오기
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");

        // 게시물 삭제 전에 사용자에게 확인을 받는다.
        var confirmDelete = confirm('게시물을 삭제하시겠습니까?');
        if (!confirmDelete) {
            return; // 사용자가 취소하면 삭제 동작을 수행하지 않음.
        }

        // 게시물 삭제 요청을 보내는 API 호출
        $.ajax({
            type: 'DELETE',
            url: '/review/delete/' + postId, // 게시물 삭제 API 엔드포인트로 변경해야 함
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function (data) {
                // 삭제 성공 시, 원하는 동작 수행 (예: 해당 게시물을 화면에서 제거하는 등)
                alert("게시물 삭제 성공");
                location.reload();
                console.log('게시물 삭제 성공');
            },
            error: function (error) {
                // 삭제 실패 시, 원하는 동작 수행
                alert("게시물 삭제 실패");
                location.reload();
                console.error('게시물 삭제 실패');
            }
        });
    });
