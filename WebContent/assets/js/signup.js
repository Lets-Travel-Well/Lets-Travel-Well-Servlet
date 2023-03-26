        // 아이디 중복 버튼 클릭시 => controller 호출 => /member?action=idCheck&userId=?
        var userid = document.getElementById("loginId");
        var idCheck = false;
        var root = "/lets_travel_well_servlet"
        
        document.getElementById("idCheck").addEventListener("click", () => {
        	var url = root + "/member?action=idCheck&userid=" + userid.value;
        	console.log(url);
          fetch(url)
            .then(response => response.text())
            .then(data => {
              console.log(data);
              if (data != 0) {
                // 아이디 중복
                alert(userid.value + "는 사용할 수 없습니다.");
                userid.value = "";
                userid.focus();
                idCheck = false;

              } else {
                alert(userid.value + "는 사용할 수 있습니다.")
                userid.readOnly = true;
                idCheck = true;
              }
            });
        })

        // TODO: 조인 버튼이 눌리는 경우 => idCheck가 false 이면 아이디 중복 검사하라는 알림 띄우기
        // TODO: 비밀번호 일치하지 않는 경우 span으로 비번 일치하지 않는다. 띄우기 
        let password = document.getElementById("loginPw");
        let passwordConfirm = document.getElementById("loginPwConfirm");
        let passwordConfirmSpan = document.getElementById("passwordConfirmSpan");
        let passwordConfirmCheck = false;
        passwordConfirm.addEventListener("keyup", () => {
          if (password.value == passwordConfirm.value) {
            passwordConfirmSpan.innerText = "비밀번호가 일치합니다.";
            passwordConfirmSpan.className = "text-primary";
            passwordConfirmCheck = true;
          } else {
            passwordConfirmSpan.innerText = "비밀번호가 일치하지 않습니다.";
            passwordConfirmSpan.className = "text-danger";
            passwordConfirmCheck = false;
          }
        })
        document.getElementById("register-button").addEventListener("click", () => {
          if (!idCheck) {
            // 아이디 중복 체크 하지 않음
            alert("아이디 중복 확인을 해주세요.");
            userid.focus();
            return;
          }

          if (!passwordConfirmCheck) {
            // 비밀번호가 일치하 지 않음 
            alert("비밀번호가 일치하지 않습니다.");
            passwordConfirm.focus();
            return;
          }

          let name = document.getElementById("memberName").value;
          let loginId = document.getElementById("loginId").value;
          let loginPw = document.getElementById("loginPw").value;
          let email = document.getElementById("email").value;
          let phone = document.getElementById("phone").value;
          if (!name || !loginId || !loginPw || !email || !phone) {
            alert("모든 정보를 입력해주세요.");
            return;
          }
          
          let form = document.querySelector("#form-join");
          let url = root + "/member";
          console.log(url);
          form.setAttribute("action",url);
          form.submit();
        })



