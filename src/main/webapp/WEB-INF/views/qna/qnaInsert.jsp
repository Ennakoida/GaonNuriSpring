<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
    <head>
		<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
        <link rel="stylesheet" href="/resources/css/qna/qnaInsert.css">
        <title>Q&A</title>
    </head>
    <body>
        <div id="container">
			<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
            <main>
                <section id="qna-insert-title">
                    <h1>Q&A 작성</h1>
                    <div id="colorBox"></div>
                </section>
                <section id="qna-insert">
					<form action="/qna/insert.do" method="post" enctype="multipart/form-data">                
	                	<div id="qna-subject">
	                		<input type="text" name="qna-subject" placeholder="제목을 입력해주세요.">
	                	</div>
	                	<div id="qna-content">
	                		<textarea cols="100" rows="30" name="qna-content" placeholder="내용을 입력해주세요..."></textarea>
	                	</div>
	                	<div>
		                	<input type="file" name="uploadFile" id="uploadFile">
		                	<div>
								<input type="button" onclick="location.href='/qna/list.do'" value="목록으로">
								<input type="submit" value="작성하기">
		                	</div>
	                	</div>
                	</form>
                </section>
            </main>
            <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
            <jsp:include page="/WEB-INF/views/include/asideMovePageBtn.jsp"></jsp:include>
        </div>
    </body>
</html>