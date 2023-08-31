<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
        <link rel="stylesheet" href="/resources/css/user/pop-show.css">
        <title>행사 예매 내역</title>
        <style>
        	tr, td, table {
        		border: 1px solid black;
        	}
        </style>
	</head>
	<body>
		<div id="container">
			<header></header>
			<main>
				<section id="title">
		            <h1>행사 예매 내역</h1>
		            <div id="colorBox"></div>
	            </section>
	            <section id="content">
					<table>
						<tr>
							<td>행사 장소</td>
							<td>${ reserve.reservePlace }</td>
							<td>행사 날짜</td>
							<td>
								<fmt:formatDate value="${ reserve.reserveDate }" pattern="yyyy년 MM월 dd일"/>
							</td>
						</tr>
						<tr>
							<td>행사 시간</td>
							<td>${ reserve.reserveTime }</td>
							<td>인원 수</td>
							<td>${ reserve.reservePeople }명</td>
						</tr>
						<tr>
							<td>예매자</td>
							<td>${ reserve.reserveName }</td>
							<td>예매 날짜</td>
							<td>
								<fmt:formatDate value="${ reserve.makeReservationDate }" pattern="yyyy년 MM월 dd일" var="datePart" />
							    <fmt:formatDate value="${ reserve.makeReservationDate }" pattern="HH시 mm분" var="timePart" />
							    ${ datePart }<br> ${ timePart }
						    </td>
						</tr>
						<tr>
							<td>이메일</td>
							<td>${ reserve.reserveEmail }</td>
							<td>전화번호</td>
							<c:set var="rawPhoneNumber" value="${ reserve.reservePhone }" />
							<c:set var="formattedPhoneNumber" value="${ rawPhoneNumber.substring(0, 3) }-${ rawPhoneNumber.substring(3, 7) }-${ rawPhoneNumber.substring(7) }" />
							<td>${ formattedPhoneNumber }</td>
						</tr>
					</table>
	            </section>
			</main>
		</div>
	</body>
</html>