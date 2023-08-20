<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--공지사항-->
<!DOCTYPE html>
<html lang="ko">
    <head>
		<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
        <link rel="stylesheet" href="/resources/css/notice/notice.css">
        <title>공지사항</title>
    </head>
    <body>
        <div id="container">
			<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
            <main>
                <section id="notice-title">
                    <h1>공지사항</h1>
                    <div id="colorBox"></div>
                </section>
                <!-- 공지사항 -->
                <section id="notice">
                
                	<div id="notice-menu">
	                    <!-- 작성하기 버튼 -->
						<!-- 관리자 계정(admin)만 작성하기 버튼 보임  -->
						<c:if test="${ sessionScope.userId eq 'admin' }">
		                    <div id="write-notice">
			                    <button onclick="location.href='/notice/writeNotice.do'">작성하기</button>
		                    </div>
	                    </c:if>
	                    <c:if test="${ sessionScope.userId ne 'admin' }">
		                    <div id="write-notice" style="visibility: hidden;">
			                    <button onclick="location.href='/notice/writeNotice.do'">작성하기</button>
		                    </div>
	                    </c:if>
	                    
   						<!-- 검색창 -->
						<div id="search-notice">
		                    <form name="searchForm" action="/notice/search.do" method="get" id="search-notice-input">
	                    		<!-- 검색 분류 -->
	                    		<select name="searchCondition">
									<option value="all" <c:if test="${ searchCondition eq 'all' }">selected</c:if>>전체</option>
									<option value="title" <c:if test="${ searchCondition eq 'title' }">selected</c:if>>제목</option>
									<option value="content" <c:if test="${ searchCondition eq 'content' }">selected</c:if>>내용</option>
								</select>
		                    	<input type="search" name="searchKeyword" placeholder="검색어 입력" value=${ searchKeyword }>
		                    	<div id="search-notice-icon">
			                    	<a href="javascript:void(0)" onclick="getSearchNotice();"><img alt="검색" src="/resources/img/search-icon.png"></a>
		                    	</div>
		                    </form>
						</div>
                	</div>
                    
                    <table frame=void>
                        <tr>
                            <th>공지번호</th>
                            <th>공지사항</th>
                            <th>작성일</th>
                        </tr>
                        <c:forEach var="search" items="${ sList }">
	                        <tr>
	                        	<td>${ search.noticeNo }</td>
	                        	<td onclick="location.href='/notice/detail.do?noticeNo=${ search.noticeNo }'">${ search.noticeSubject }</td>
	                        	<td>${ search.noticeDate }</td>
	                        </tr>
                        </c:forEach>
                    </table>
                    
                    <!-- 페이지 전환 버튼  -->
                    <ul id="page">
                    	<!-- 이전 -->
						<c:url var="prevPageUrl" value="/notice/search.do">
							<c:param name="searchCondition" value="${ searchCondition }"></c:param>
							<c:param name="searchKeyword" value="${ searchKeyword }"></c:param>
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
							<c:url var="pageUrl" value="/notice/search.do">
								<c:param name="searchCondition" value="${ searchCondition }"></c:param>
								<c:param name="searchKeyword" value="${ searchKeyword }"></c:param>
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
						<c:url var="nextPageUrl" value="/notice/search.do">
							<c:param name="searchCondition" value="${ searchCondition }"></c:param>
							<c:param name="searchKeyword" value="${ searchKeyword }"></c:param>
							<c:param name="page" value="${ pInfo.endNavi + 1 }"></c:param>
						</c:url>
						<c:if test="${ pInfo.endNavi ne pInfo.naviTotalCount }">
							<li style="cursor: pointer;" onclick="location.href='${ nextPageUrl }'">></li>
						</c:if>
						<c:if test="${ pInfo.endNavi eq pInfo.naviTotalCount }">
							<li>></li>
						</c:if>
                    </ul>
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