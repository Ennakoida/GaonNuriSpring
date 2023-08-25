<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--Q & A-->
<!DOCTYPE html>
<html lang="ko">
    <head>
		<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
        <link rel="stylesheet" href="/resources/css/qna/list.css">
        <title>Q & A</title>
    </head>
    <body>
        <div id="container">
			<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
            <main>
                <section id="qna-title">
                    <h1>Q & A</h1>
                    <div id="colorBox"></div>
                </section>
                <!-- Q & A -->
                <section id="qna">
                	<div id="qna-menu">
	                    <!-- 작성하기 버튼 -->
						<!-- 관리자 계정(admin)만 작성하기 버튼 보임  -->
						<c:if test="${ sessionScope.userId eq 'admin' }">
		                    <div id="write-qna">
			                    <button onclick="location.href='/qna/insert.do'">작성하기</button>
		                    </div>
	                    </c:if>
	                    <c:if test="${ sessionScope.userId ne 'admin' }">
		                    <div id="write-qna" style="visibility: hidden;">
			                    <button onclick="location.href='/qna/insert.do'">작성하기</button>
		                    </div>
	                    </c:if>
	                    
						<!-- 검색창 -->
						<div id="search-qna">
		                    <form name="searchForm" action="/qna/search.do" method="get" id="search-qna-input">
	                    		<!-- 검색 분류 -->
	                    		<select name="searchCondition">
									<option value="all">전체</option>
									<option value="title">제목</option>
									<option value="content">내용</option>
								</select>
		                    	<input type="search" name="searchKeyword" placeholder="검색어 입력">
		                    	<div id="search-qna-icon">
			                    	<a href="javascript:void(0)" onclick="getSearchNotice();"><img alt="검색" src="/resources/img/search-icon.png"></a>
		                    	</div>
		                    </form>
						</div>
                	</div>
                    
                    <table frame=void>
                        <tr>
                            <th>공지번호</th>
                            <th>Q & A</th>
                            <th>작성일</th>
                            <th>조회수</th>
                        </tr>
                        <!-- Q & A 목록이 존재하지 않을 때 -->
                        <c:if test="${ empty qList }">
                        	<tr>
                        		<td colspan="4" id="qna-none">목록이 존재하지 않습니다.</td>
                       		</tr>
                        </c:if>
                        <!-- Q & A 목록이 존재할 때 -->
                        <c:if test="${ !empty qList }">
                        	<!-- Q & A 목록 -->
	                        <c:forEach var="qna" items="${ qList }">
		                        <tr>
		                        	<td>${ qna.qnaNo }</td>
		                        	<td onclick="location.href='/qna/detail.do?qnaNo=${ qna.qnaNo }'">${ qna.qnaSubject }
		                        		<c:if test="${ qna.qnaFileName ne null }">
		                        			&nbsp;<img src="/resources/img/file-icon.png" alt="첨부파일" id="file-exist">
		                        		</c:if>
		                        	</td>
		                        	<td>${ qna.qnaDate }</td>
		                        	<td>${ qna.viewCount }</td>
		                        </tr>
	                        </c:forEach>  
                        </c:if>
                    </table>
	                        
                     <!-- 페이지 전환 버튼  -->
                     <c:if test="${ !empty qList }">
	                    <ul id="page">
	                    	<!-- 이전 -->
							<c:url var="prevPageUrl" value="/qna/list.do">
								<c:param name="page" value="${ pInfo.startNavi - 1 }"></c:param>
							</c:url>
							<c:if test="${ pInfo.startNavi ne 1 }">
								<li style="cursor: pointer;" onclick="location.href='${ prevPageUrl }'"><</li>
							</c:if>
							<c:if test="${ pInfo.startNavi eq 1 }">
								<li><</li>
							</c:if>
							
							<!-- 페이징 -->
							<c:forEach begin="${ pInfo.startNavi }" end="${ pInfo.endNavi }" var="p">
								<c:url var="pageUrl" value="/qna/list.do">
									<c:param name="page" value="${ p }"></c:param>
								</c:url>
								
								<c:if test="${ pInfo.currentPage ne p }">
									<li onclick="location.href='${ pageUrl }'">${ p }</li>
								</c:if>
								<c:if test="${ pInfo.currentPage eq p }">
									<li style="color: #EA5455; font-weight: bold;" onclick="location.href='${ pageUrl }'">${ p }</li>
								</c:if>
							</c:forEach>
							
							<!-- 다음 -->
							<c:url var="nextPageUrl" value="/qna/list.do">
								<c:param name="page" value="${ pInfo.endNavi + 1 }"></c:param>
							</c:url>
							<c:if test="${ pInfo.endNavi ne pInfo.naviTotalCount }">
								<li style="cursor: pointer;" onclick="location.href='${ nextPageUrl }'">></li>
							</c:if>
							<c:if test="${ pInfo.endNavi eq pInfo.naviTotalCount }">
								<li>></li>
							</c:if>
	                    </ul>   
	            	</c:if>                   	
                </section>
            </main>
            <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
            <jsp:include page="/WEB-INF/views/include/asideMovePageBtn.jsp"></jsp:include>
        </div>
        <script>
        	const getSearchNotice = () => {
        		searchForm.submit();
        	}
        </script>
    </body>
</html>