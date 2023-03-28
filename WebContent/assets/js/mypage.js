var root = "/ltw"
document.getElementById("modify-button").addEventListener("click", function () {
    // 버튼 클릭
    let form = document.getElementById("form-modify");
    let url = root + "/member";
    form.setAttribute("action", url);
    form.submit();
})