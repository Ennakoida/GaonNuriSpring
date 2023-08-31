<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
        <link rel="stylesheet" href="/resources/css/user/pop-show.css">
        <title>나의 질문</title>
	</head>
	<body>
		<div id="container">
			<header></header>
			<main>
				<section id="title">
		            <h1>나의 질문</h1>
		            <div id="colorBox"></div>
	            </section>
	            <section id="content" style="padding-bottom: 50px;">
					<table>
						<tr>
							<td>제목</td>
							<td>${ qna.qnaSubject }</td>
							<td>작성일</td>
							<td>${ qna.qCreateDate }</td>
						</tr>
						<tr>
							<td>내용</td>
							<td colspan="3" style="white-space:pre; text-align: left">${ qna.qnaContent }</td>
						</tr>
					</table>
	            </section>
			</main>
		</div>
	</body>
</html>