<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
        <link rel="stylesheet" href="/resources/css/user/pop-show-reserve.css">
        <title>행사 예매 내역</title>
	</head>
	<body>
		<div id="container">
			<header>
				<div></div>
			</header>
			<main>
				<section id="reserve-title">
		            <h1>행사 예매 내역</h1>
		            <div id="colorBox"></div>
	            </section>
	            <section id="reserve">
					<table>
						<tr>
							<td>행사 장소</td>
							<td>행사 날짜</td>
							<td>행사 시간</td>
							<td>인원 수</td>
						</tr>
						<tr>
							<td>예매자</td>
							<td>전화번호</td>
							<td>이메일</td>
							<td>예매 날짜</td>
						</tr>
						<tr>
							<td colspan="4">지도, 찾아오는 길</td>
						</tr>
					</table>
	            </section>
			</main>
            <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
		</div>
	</body>
</html>