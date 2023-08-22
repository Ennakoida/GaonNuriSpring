<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
				<section id="reserve-title">
		            <h1>행사 예매 내역</h1>
		            <div id="colorBox"></div>
	            </section>
	            <section id="reserve">
					<table>
						<tr>
							<td>행사 장소</td>
							<td>${ reserve.reservePlace }</td>
							<td>행사 날짜</td>
							<td>${ reserve.reserveDate }</td>
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
							<td>${ reserve.makeReservationDate }</td>
						</tr>
						<tr>
							<td>이메일</td>
							<td>${ reserve.reserveEmail }</td>
							<td>전화번호</td>
							<td>${ reserve.reservePhone }</td>
						</tr>
						<tr>
							<c:if test="${ reserve.reservePlace eq '경복궁' }">
								<td colspan="4" id="map">
									 <iframe
		                                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d7520.7011343417!2d126.9719724535835!3d37.57707972127264!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357ca2c74aeddea1%3A0x8b3046532cc715f6!2z6rK967O16raB!5e0!3m2!1sko!2skr!4v1686107280969!5m2!1sko!2skr"
		                                width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy"
		                                referrerpolicy="no-referrer-when-downgrade">
		                            </iframe>
								</td>
							</c:if>
							<c:if test="${ reserve.reservePlace eq '덕수궁' }">
								<td colspan="4" id="map">
									<iframe
		                                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3162.5438642980466!2d126.97257117645422!3d37.56580912416875!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357ca28d3199c531%3A0x7a2d35df0efd2d8!2z642V7IiY6raB!5e0!3m2!1sko!2skr!4v1686151846558!5m2!1sko!2skr"
		                                width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy"
		                                referrerpolicy="no-referrer-when-downgrade">
		                            </iframe>
								</td>
							</c:if>
							<c:if test="${ reserve.reservePlace eq '창경궁' }">
								<td colspan="4" id="map">
									 <iframe
		                                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d12648.007615590745!2d126.98885107113242!3d37.57857285822762!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357ca2d63cdfd837%3A0x4ae06b4de851f3cf!2z7LC96rK96raB!5e0!3m2!1sko!2skr!4v1686151908915!5m2!1sko!2skr"
		                                width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy"
		                                referrerpolicy="no-referrer-when-downgrade">
		                            </iframe>
								</td>
							</c:if>
							<c:if test="${ reserve.reservePlace eq '창덕궁' }">
								<td colspan="4" id="map">
									<iframe
		                                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3161.881779069215!2d126.99028832645487!3d37.5814014232746!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357ca25bce7408e3%3A0xff296b97da565068!2z7LC9642V6raB!5e0!3m2!1sko!2skr!4v1686151958650!5m2!1sko!2skr"
		                                width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy"
		                                referrerpolicy="no-referrer-when-downgrade">
		                            </iframe>
								</td>
							</c:if>
							<c:if test="${ reserve.reservePlace eq '종묘' }">
								<td colspan="4" id="map">
									 <iframe
		                                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d12647.523586041023!2d126.98256352109549!3d37.581422204113984!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357ca2d85a1cbacf%3A0x24dfda0247e6e153!2z7KKF66yY!5e0!3m2!1sko!2skr!4v1686151989410!5m2!1sko!2skr"
		                                width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy"
		                                referrerpolicy="no-referrer-when-downgrade">
		                            </iframe>
								</td>
							</c:if>
						</tr>
					</table>
	            </section>
			</main>
            <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
		</div>
	</body>
</html>