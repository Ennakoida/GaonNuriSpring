<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 회원가입 -->
<!DOCTYPE html>
<html lang="ko">
    <head>
		<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
        <link rel="stylesheet" href="/resources/css/user/enroll.css">
        <title>회원가입</title>
    </head>
    <body>
        <div id="container">
			<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
            <main>
                <section id="enroll-title">
                    <h1>회원가입</h1>
                    <p>회원가입 하시면 홈페이지 내에 다양한 서비스를 이용하실 수 있습니다.</p>
                    <div id="colorBox"></div>
                </section>
                <section id="enroll">
                    <div id="enroll-input">
                        <form action="/user/enroll.do" method="post">
                            <label for="user-id">아이디&nbsp;&nbsp;&nbsp;
<!--                             <button onclick="checkDuplicateId();">중복 확인</button> -->
                            </label><br>
                            <input type="text" name="user-id" id="user-id" placeholder="6 ~ 20자 영문, 숫자" required minlength="6" maxlength="20" pattern="^[a-zA-Z0-9]{6,20}$" oninput="setCustomValidity('')" oninvalid="this.setCustomValidity('6 ~ 20자 영문, 숫자')">
                            <br>
                            <label for="user-pw">비밀번호</label><br>
                            <input type="password" name="user-pw" id="user-pw" placeholder="6 ~ 12자 영문, 숫자, 특수문자" required  minlength="6" maxlength="12" pattern="^[a-zA-Z0-9!@#$%^&*()_+{}\[\]:;<>,.?~\\/\-=|\\]+$" oninput="setCustomValidity('')" oninvalid="this.setCustomValidity('6 ~ 12자 영문, 숫자, 특수문자')">
                            <br>
                            <label for="user-pw-check">비밀번호 확인</label><br>
                            <input type="password" id="user-pw-check" placeholder="비밀번호를 다시 입력해 주세요." required>
                            <br>
                            <label for="user-name">이름</label><br>
                            <input type="text" name="user-name" id="user-name" placeholder="이름은 한글, 영어로 입력해주세요." required pattern="^[가-힣a-zA-Z]+$" oninput="setCustomValidity('')" oninvalid="this.setCustomValidity('한글, 영어')">
                            <br>
                            <label for="user-phone">전화번호</label><br>
                            <input type="tel" name="user-phone" id="user-phone" placeholder="하이픈(-)은 빼고 입력해 주세요." required maxlength="11" pattern="^01[0-9]{8,9}$" oninput="setCustomValidity('')" oninvalid="this.setCustomValidity('01로 시작하는 전화번호 11자리')">
                            <br>
                            <label for="user-email">이메일 (선택)</label><br>
                            <input type="email" name="user-email" id="user-email" pattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$">
                            <br>
                            <label for="user-event-yn">
                            <input type="checkbox" name="user-event-yn" id="user-event-yn" value="Y"> SMS, 이메일로 상품 및 이벤트 정보를 받겠습니다. (선택)</label>
                            <br>
                            <input type="submit" value="가입하기" id="enroll-btn">
<!--                             <input type="button" value="가입하기" id="enroll-btn" onclick="checkForm();"> -->
                        </form>
                    </div>
                    <div id="sns-enroll">
                        <!-- 각 기업에서 제공하는 표준 logo resource 사용 -->
                        <button id="naver">
                            <div>
                                <img src="/resources/img/user/sns-logo/naver.png" alt="네이버로 회원가입" style="width: 40px;">
                                <div>네이버로 시작하기</div>
                            </div>
                        </button>
                        <button id="kakao">
                            <div>
                                <img src="/resources/img/user/sns-logo/kakao.png" alt="카카오로 회원가입" style="width: 34px;">
                                <div>카카오로 시작하기</div>
                            </div>
                        </button>
                        <button id="google">
                            <div>
                                <img src="/resources/img/user/sns-logo/google.png" alt="구글로 회원가입" style="width: 20px;">
                                <div>Google 계정으로 시작하기</div>
                            </div>
                        </button>
                    </div>
                </section>
            </section>
            </main>
            <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
            <jsp:include page="/WEB-INF/views/include/asideMovePageBtn.jsp"></jsp:include>
        </div>

        <script>
	        document.querySelectorAll("input").forEach(input => {
	            input.addeventListener("invalid", () => {
	                document.forms[0].classList.add("was-validated")
	            })
	        })
        </script>
        <script>
        	// 비밀번호 재확인
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
            
//             // 아이디 중복 검사
//             function checkDuplicateId() {
// 				var userId = document.getElementById("user-id");
// //             	location.href = '/user/checkDuplicateId.do?userId=' + userId;
// 				const user = '${ uList }';
// 				for (var i = 0; i < user.length; i++) {
// 				       if (user[i].userId === userId) {
// 				           alert("사용할 수 없는 아이디입니다.");
// 				           return false; // 폼 제출을 막기 위해 false를 반환
// 				       }
//             }
        </script>
    </body>
</html>