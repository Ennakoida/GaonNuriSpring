<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
		<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
        <link rel="stylesheet" href="/resources/css/user/my-info.css">
        <title>마이페이지</title>
    </head>
    <body>
        <div id="container">
			<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
			<main>
				<section id="myinfo-title">
	                    <h1>마이페이지</h1>
	                    <div id="colorBox"></div>
	            </section>
	            <section id="my">
	            	<section id="myinfo">
		                <div id="myinfo-input">
		                    <form action="/user/modify.do?userId=${ user.userId }" method="post">
		                        <label for="user-id">아이디</label><br>
		                        <input type="text" style="background-color:#ccc;" name="user-id" id="user-id" value="${ user.userId }" readonly>
		                        <br>
		                        <label for="user-pw">비밀번호</label><br>
		                        <input type="password" name="user-pw" id="user-pw" value="${ user.userPw }" placeholder="6 ~ 12자 영문, 숫자, 특수문자" required  minlength="6" maxlength="12" pattern="^[a-zA-Z0-9!@#$%^&*()_+{}\[\]:;<>,.?~\\/\-=|\\]+$" oninput="setCustomValidity('')" oninvalid="this.setCustomValidity('6 ~ 12자 영문, 숫자, 특수문자')">
		                        <br>
		                        <label for="user-pw-check">비밀번호 확인</label><br>
		                        <input type="password" id="user-pw-check" placeholder="수정한 비밀번호를 다시 입력해 주세요.">
		                        <br>
		                        <label for="user-name">이름</label><br>
		                        <input type="text" style="background-color:#ccc;" name="user-name" id="user-name" value="${ user.userName }" readonly>
		                        <br>
		                        <label for="user-phone">전화번호</label><br>
		                        <input type="tel" name="user-phone" id="user-phone" value="${ user.userPhone }" placeholder="하이픈(-)은 빼고 입력해 주세요." required maxlength="11" pattern="^01[0-9]{8,9}$" oninput="setCustomValidity('')" oninvalid="this.setCustomValidity('01로 시작하는 전화번호 11자리')">
		                        <br>
		                        <label for="user-email">이메일 (선택)</label><br>
		                        <input type="email" name="user-email" id="user-email" value="${ user.userEmail }" pattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$">
		                        <br>
		                        <c:if test="${ user.userEventYn eq 'Y' }">
			                        <label for="user-event-yn">
			                        <input type="checkbox" name="user-event-yn" id="user-event-yn" value="Y" checked> SMS, 이메일로 상품 및 이벤트 정보를 받겠습니다. (선택)</label>
		                        </c:if>
		                        <c:if test="${ user.userEventYn eq 'N' }">
		   	                        <label for="user-event-yn">
		   	                        <input type="checkbox" name="user-event-yn" id="user-event-yn" value="N"> SMS, 이메일로 상품 및 이벤트 정보를 받겠습니다. (선택)</label>
		                        </c:if>
		                        <br>
		                        <div id="myinfo-btn">
		                            <button type="submit" id="modify-btn">수정하기</button>
		<!--                             <button onclick="location.href='javascript:void(0); checkDelete();" id="delete-btn">탈퇴하기</button> -->
		                            <a href="javascript:void(0)"><input type="button" onclick="checkDelete();" id="delete-btn" value="탈퇴하기"></a>
		                        </div>
		                    </form>
		                </div>
		            </section>
		            <section id="my-activity">
						<h4>행사 예매 내역</h4>
						<section id="my-reserve">
							<table>
								<tr class="table-title">
									<th>행사 장소</th>
									<th>날짜</th>
									<th>시간</th>
								</tr>
								<c:forEach var="reserve" items="${ rList }">
									<tr onclick="openReservePopup(${ reserve.reserveNo });">
										<td>${ reserve.reservePlace }</td>
										<td>${ reserve.reserveDate }</td>
										<td>${ reserve.reserveTime }</td>
									</tr>
								</c:forEach>
							</table>
							
							<div id="modalWrap">
							    <div id="modalBody">
									<span id="closeBtn">&times;</span>
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
									</table>
							    </div>
							</div>
						</section>            
						<h4>한복 대여 내역</h4>
						<section id="my-rental">
							<table>
								<tr class="table-title">
									<th>OOO 지점</th>
									<th>대여 일자</th>
									<th>한복 이름</th>
								</tr>
								<c:forEach var="rental" items="${ hList }">
									<tr onclick="openRentalPopup(${ rental.rentalNo });">
										<td>${ rental.rentalPlace }</td>
										<td>${ rental.rentalDate }</td>
										<td>${ rental.rentalHanbok }</td>
									</tr>
								</c:forEach>
							</table>
						</section>            
						<h4>나의 질문</h4>
						<section id="my-q">
							<table>
								<tr class="table-title">
									<th>제목</th>
									<th>작성일</th>
								</tr>
								<c:forEach var="qna" items="${ qList }">
									<tr onclick="#">
										<td>${ qna.qnaSubject }</td>
										<td>${ qna.qCreateDate }</td>
									</tr>
								</c:forEach>
							</table>
						</section>            
		            </section>
	            </section>
			</main>
            <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
            <jsp:include page="/WEB-INF/views/include/asideMovePageBtn.jsp"></jsp:include>
		</div>

        <script>
            var userPw = document.getElementById("user-pw");
            var userPwCheck = document.getElementById("user-pw-check");

            function checkPassword() {
                if(userPw.value != userPwCheck.value){
                    userPwCheck.setCustomValidity("비밀번호가 일치하지 않습니다."); 
                } else {
                    userPwCheck.setCustomValidity(''); 
                }
            }

            userPw.onchange = checkPassword;
            userPwCheck.onkeyup = checkPassword;
        </script>
        
        <script>
			function checkDelete() {
				const userId = '${ sessionScope.userId }';
				if(confirm("탈퇴하시겠습니까?")){
					location.href = "/user/delete.do?userId=" + userId;
				} 				
			}
		</script>

	    <script>
			const modal = document.getElementById('modalWrap');
			const closeBtn = document.getElementById('closeBtn');
			
		    function openReservePopup(reserveNo) {
// 		    	var options = "width=550,height=700,resizable=no,scrollbars=no";
// 		    	window.open("/user/popReserve.do?reserveNo=" + reserveNo, "행사 예매 내역", options);
				modal.style.display = 'block';
	        }
		    
		    function openRentalPopup(rentalNo) {
		    	var options = "width=550,height=700,resizable=no,scrollbars=no";
		    	window.open("/user/popRental.do?rentalNo=" + rentalNo, "한복 대여 내역", options);
	        }
		    
		    closeBtn.onclick = function() {
				modal.style.display = 'none';
		    }
    	</script>
	</body>
</html>