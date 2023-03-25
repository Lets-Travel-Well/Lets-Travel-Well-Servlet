document
  .getElementById("register-button")
  .addEventListener("click", function () {
    let name = document.getElementById("memberName").value;
    let email = document.getElementById("memberEmail").value;
    let password = document.getElementById("memberPw").value;
    let passwordConfirm = document.getElementById("memberPwConfirm").value;
    let user;
    console.log("ee");
    if (!name || !email || !password || !passwordConfirm) {
      alert("빈칸이 없도록 입력해주세요.");
      return;
    } else {
      // input data로 user 만들기
      user = {
        name: name,
        email: email,
        password: password,
      };
    }
    localStorage.setItem("user", JSON.stringify(user));
    alert("사용자 등록 성공!");
    location.replace("login.html");
  });
