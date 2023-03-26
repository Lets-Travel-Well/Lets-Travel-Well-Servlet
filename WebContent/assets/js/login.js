var root = "/lets_travel_well_servlet";
document.getElementById("login-button").addEventListener("click", () => {
    let loginId = document.getElementById("loginId");
    let loginPw = document.getElementById("loginPw");

    if (!loginId.value) {
        alert("아이디를 입력해주세요.");
        loginId.focus();
        return;
    }

    if (!loginPw.value) {
        alert("비밀번호를 입력해주세요.");
        loginPw.focus();
        return;
    }

    let form = document.getElementById("login-form");
    let url = root + "/member";
    form.setAttribute("action",url);
    form.submit();
})