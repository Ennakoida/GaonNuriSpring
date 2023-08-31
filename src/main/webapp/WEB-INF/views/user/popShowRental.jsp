<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
        <link rel="stylesheet" href="/resources/css/user/pop-show.css">
        <title>한복 대여 내역</title>
	</head>
	<body>
		<div id="container">
			<header></header>
			<main style="height: 529px">
				<section id="title">
		            <h1>한복 예매 내역</h1>
		            <div id="colorBox"></div>
	            </section>
	            <section id="content">
					<table>
						<tr>
							<td>대여 장소</td>
							<td>${ hanbok.rentalPlace } ○○○ 지점</td>
							<td>대여 날짜</td>
							<td>${ hanbok.rentalDate }</td>
						</tr>
						<tr>
							<td style="width: 20%;">대여 제품 이름</td>
							<td colspan="3">${ hanbok.rentalHanbok }</td>
						</tr>
						<tr>
							<td>상의 색상</td>
							<td>${ hanbok.rentalTopColor }</td>
							<td>상의 사이즈</td>
							<td>${ hanbok.rentalTopSize }</td>
						</tr>
						<tr>
							<td>하의 색상</td>
							<td>${ hanbok.rentalPantsColor }</td>
							<td>하의 사이즈</td>
							<td>${ hanbok.rentalPantsSize }</td>
						</tr>
						<tr>
							<td>장신구</td>
							<td>${ hanbok.rentalAccessories }</td>
							<td>가격</td>
							<td><fmt:formatNumber pattern="##,###,###" value="${ hanbok.rentalPrice }"/>원</td>
						</tr>
					</table>
	            </section>
			</main>
		</div>
	</body>
</html>