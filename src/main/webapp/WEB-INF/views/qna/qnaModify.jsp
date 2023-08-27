<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
    <head>
		<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
        <link rel="stylesheet" href="/resources/css/qna/qnaModify.css">
        <title>Q&A</title>
    </head>
    <body>
        <div id="container">
			<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
            <main>
                <section id="qna-modify-title">
                    <h1>Q&A 수정</h1>
                    <div id="colorBox"></div>
                </section>
                <section id="qna-modify">
					<form action="/qna/modify.do" method="post" enctype="multipart/form-data">       
	         			<input type="hidden" name="qnaNo" value="${ qna.qnaNo }">
	         			<input type="hidden" name="qnaFileName" value="${ qna.qnaFileName }">
						<input type="hidden" name="qnaFileRename" value="${ qna.qnaFileRename }">
						<input type="hidden" name="qnaFilePath" value="${ qna.qnaFilePath }">
						<input type="hidden" name="qnaFileLength" value="${ qna.qnaFileLength }">
	                	<div id="qna-subject">
	                		<input type="text" name="qna-subject" placeholder="제목을 입력해주세요." value="${ qna.qnaSubject }">
	                	</div>
	                	<div id="qna-content">
	                		<textarea cols="100" rows="30" name="qna-content" placeholder="내용을 입력해주세요...">${ qna.qnaContent }</textarea>
	                	</div>
	                	<div>
		                	<input type="file" name="uploadFile" id="uploadFile">
							<input type="submit" value="수정하기">
	                	</div>
                	</form>
                </section>
            </main>
            <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
            <jsp:include page="/WEB-INF/views/include/asideMovePageBtn.jsp"></jsp:include>
        </div>
    </body>
</html>