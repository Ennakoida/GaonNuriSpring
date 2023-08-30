<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!--Q&A-->
<!DOCTYPE html>
<html lang="ko">
    <head>
		<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
        <link rel="stylesheet" href="/resources/css/qna/qnaDetail.css">
        <title>Q&A</title>
    </head>
    <body>
        <div id="container">
			<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
            <main>
                <section id="detail-title">
                    <h1>Q & A</h1>
                    <div id="colorBox"></div>
                </section>
                <!-- Q&A -->
                <section id="detail">
                
                	<!-- 작성자(loginUser)가 아닐 경우 Q&A 수정 불가능 -->
                	<!-- 관리자(admin), 작성자(loginUser)가 아닐 경우 Q&A 삭제 불가능 -->
                    <c:if test="${ sessionScope.userId eq 'admin' || sessionScope.userId eq qna.qnaWriter }">
	                  	<div id="detail-button">
		                    <button onclick="location.href='/qna/modify.do?qnaNo=${ qna.qnaNo }'" id="modify-detail-btn">수정하기</button>
		                    <button onclick="deleteCheck();" id="delete-detail-btn">삭제하기</button>
                		</div>
                    </c:if>
					<table>
						<tr>
							<td class="detail-top">${ qna.qnaNo }</td>
							<td class="detail-top">${ qna.qnaSubject }</td>
							<td class="detail-top">${ qna.qnaWriter }</td>
							<td class="detail-top">${ qna.qCreateDate }</td>
							<td class="detail-top">${ qna.qViewCount }</td>
						</tr>
						<tr>
							<td colspan="5" id="detail-content" style="white-space:pre;">${ qna.qnaContent }</td>
						</tr>
						<c:if test="${ qna.qnaFileName ne null }">
							<tr>
								<td colspan="5" id="downloadFile"><span style="color:black;">첨부파일 :</span> <a href="../resources/GN_NoticeFiles/${ qna.qnaFileRename }" download style="color:#979797;">${ qna.qnaFileName }</a></td>
							</tr>						
						</c:if>
					</table>
					
					<!-- 댓글 목록 -->
					<c:if test="${ !empty rList }">
						<table id=reply-list>
							<c:forEach var="reply" items="${ rList }">
								<tr>
									<td>${ reply.replyWriter }</td>
									<td>
										<fmt:formatDate value="${ reply.rCreateDate }" pattern="yyyy-MM-dd" var="datePart" />
									    <fmt:formatDate value="${ reply.rCreateDate }" pattern="HH:mm:ss" var="timePart" />
									    ${ datePart } &nbsp;&nbsp; ${ timePart }
									</td>
									<c:url var="delUrl" value="/reply/delete.do">
										<c:param name="replyNo" value="${ reply.replyNo }"></c:param>
										<c:param name="replyWriter" value="${ reply.replyWriter }"></c:param>
										<c:param name="refQnaNo" value="${ reply.refQnaNo }"></c:param>
									</c:url>
									<c:if test="${ sessionScope.userId eq 'admin' || sessionScope.userId eq reply.replyWriter }">
										<td class="reply-edit-btn">
											<!-- 작성자만 댓글 수정 가능 -->
											<c:if test="${ sessionScope.userId eq reply.replyWriter }">	
												<a href="javascript:void(0)" onclick="showModifyForm(this);">수정</a>&nbsp;&nbsp;
											</c:if>
											<!-- 관리자와 작성자만 댓글 삭제 가능 -->
											<a href="javascript:void(0)" onclick="deleteReply('${ delUrl }');">삭제</a>
										</td>
									</c:if>
								</tr>
								<tr>
									<td colspan="3"  style="white-space:pre; vertical-align: top;">${ reply.replyContent }</td>
								</tr>
								<tr style="display: none;">
									<form action="/reply/update.do" method="post">
										<input type="hidden" name="replyNo" value="${ reply.replyNo }">
										<input type="hidden" name="refQnaNo" value="${ reply.refQnaNo }">
										<td colspan="2" class="modify-replyContent"><textarea rows="3" cols="55" name="replyContent">${ reply.replyContent }</textarea>
										<td class="modify-reply-btn"><input type="submit" value="수정 완료"></td>
									</form>
								</tr>
							</c:forEach>
						</table>
					</c:if>
					
					<!-- 댓글 작성 -->
					<form action="/reply/add.do" method="post">
						<input type="hidden" name="refQnaNo" value="${ qna.qnaNo }">
						<table id=insert-reply>
							<tr>
								<td>
									<textarea rows="3" cols="55" name="replyContent"></textarea>
								</td>
								<td>
									<input type="submit" value="등록">
								</td>
							</tr>
						</table>
					</form>
			
					<div>
						<button onclick="location.href='/qna/list.do'">목록으로</button>
<!-- 						<button onclick="goBack();">목록으로</button> -->
                	</div>
                </section>
            </main>
            <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
            <jsp:include page="/WEB-INF/views/include/asideMovePageBtn.jsp"></jsp:include>
        </div>
        <script>
        	const getSearchNotice = () => {
        		searchForm.submit();
        	}
        	
        	const deleteCheck = () => {
				const qnaNo = '${ qna.qnaNo }';
				if(confirm("삭제하시겠습니까?")){
					location.href = "/qna/delete.do?qnaNo=" + qnaNo;
				}
			}
        </script>
        <script>
			function showModifyForm(obj) {
				var obj = obj.parentElement.parentElement.nextElementSibling.nextElementSibling;
				if(obj.style.display == "none") obj.style.display = "";
				else obj.style.display = "none";
			}
			
			function deleteReply(url) {
				if(confirm("댓글을 삭제하시겠습니까?")){
					location.href = url;
				}
			}
		</script>
		<script>
			function goBack() {
			    history.back(); // 이전 페이지로 돌아가는 함수
			}
		</script>
    </body>
</html>