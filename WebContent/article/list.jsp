<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<c:if test="${cookie.userId.value ne null}">
    <c:set var="idck" value=" checked" />
    <c:set var="saveid" value="${cookie.userId.value}" />
</c:if>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>LTW</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="./assets/favicon.ico" />
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet"
          type="text/css" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css" />
    <link href="${root}/assets/css/style.css" rel="stylesheet" />
</head>

<body id="page-top">
<!-- Navigation-->
<nav class="shadow navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand fs-4" href="${root }">
            <!-- <img src="assets/img/navbar-logo.svg" alt="..." /> -->
            Let's Travel Well
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            Menu
            <i class="fas fa-bars ms-1"></i>
        </button>
        <jsp:include page="/fragment/nav.jsp">
            <jsp:param value="ok" name="flag" />
        </jsp:include>
    </div>
</nav>
<!-- Masthead-->
<header class="masthead">
    <div class="container">
        <div class="masthead-subheading">Wherever You Go</div>
    </div>
</header>
<!-- login section -->
<section>
    <div class="container" id="article-list-section">
        <div class="row align-self-center mb-2">
            <div class="col-md-2 text-start">
                <button type="button" id="btn-mv-register" class="btn btn-outline-primary btn-sm">
                    글쓰기
                </button>
            </div>
            <div class="col-md-7 offset-3">
                <form class="d-flex" id="form-search" action="">
                    <input type="hidden" name="action" value="list"/>
                    <input type="hidden" name="pgno" value="1"/>
                    <select
                            name="key"
                            id="key"
                            class="form-select form-select-sm ms-5 me-1 w-50"
                            aria-label="검색조건"
                    >
                        <option value="">검색조건</option>
                        <option value="id">글번호</option>
                        <option value="subject">제목</option>
                        <option value="content">내용</option>
                    </select>
                    <div class="input-group input-group-sm">
                        <input type="text" name="word" id="word" class="form-control" value="${word}" placeholder="검색어..." />
                        <button id="btn-search" class="btn btn-dark" type="button">검색</button>
                    </div>
                </form>
            </div>
        </div>
        <table class="table table-hover">
            <thead>
            <tr class="text-center">
                <th scope="col">글번호</th>
                <th scope="col">제목</th>
                <th scope="col">작성자</th>
                <th scope="col">조회수</th>
                <th scope="col">작성일</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="article" items="${articles}">
                <tr class="text-center">
                    <th scope="row">${article.id}</th>
                    <td class="text-start">
                        <a
                                href="#"
                                class="article-title link-dark"
                                data-no="${article.id}"
                                style="text-decoration: none"
                        >
                                ${article.subject}
                        </a>
                    </td>
                    <td>${article.memberName}</td>
                    <td>${article.hit}</td>
                    <td>${article.createdDate}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="row">
            ${navigation.navigator}
        </div>
    </div>
    <form id="form-param" method="get" action="">
        <input type="hidden" id="p-action" name="action" value="">
        <input type="hidden" id="p-pgno" name="pgno" value="">
        <input type="hidden" id="p-key" name="key" value="">
        <input type="hidden" id="p-word" name="word" value="">
    </form>
</section>
<!-- Footer-->
<footer class="footer py-4">
    <div class="container">
        <div class="row align-items-center">
            <div class="col-lg-4 text-lg-start">
                Copyright &copy; Your Website 2022
            </div>
            <div class="col-lg-4 my-3 my-lg-0">
                <a class="btn btn-dark btn-social mx-2" href="#!" aria-label="Twitter"><i class="fab fa-twitter"></i></a>
                <a class="btn btn-dark btn-social mx-2" href="#!" aria-label="Facebook"><i
                        class="fab fa-facebook-f"></i></a>
                <a class="btn btn-dark btn-social mx-2" href="#!" aria-label="LinkedIn"><i
                        class="fab fa-linkedin-in"></i></a>
            </div>
            <div class="col-lg-4 text-lg-end">
                <a class="link-dark text-decoration-none me-3" href="#!">Privacy Policy</a>
                <a class="link-dark text-decoration-none" href="#!">Terms of Use</a>
            </div>
        </div>
    </div>
</footer>

<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="${root}/assets/js/script.js"></script>
<script src='${root}/assets/js/login.js'></script>
<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
<!-- * *                               SB Forms JS                               * *-->
<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    let titles = document.querySelectorAll(".article-title");
    titles.forEach(function (title) {
        title.addEventListener("click", function () {
            console.log(this.getAttribute("data-no"));
            location.href = "${root}/article?action=view&articleId=" + this.getAttribute("data-no");
        });
    });

    document.querySelector("#btn-mv-register").addEventListener("click", function () {
        location.href = "${root}/article?action=mvwrite";
    });

    let pages = document.querySelectorAll(".page-link");
    pages.forEach(function (page) {
        page.addEventListener("click", function () {
            console.log(this.parentNode.getAttribute("data-pg"));
            document.querySelector("#p-action").value = "list";
            document.querySelector("#p-pgno").value = this.parentNode.getAttribute("data-pg");
            document.querySelector("#p-key").value = "${param.key}";
            document.querySelector("#p-word").value = "${param.word}";
            document.querySelector("#form-param").submit();
        });
    });

    document.querySelector("#btn-search").addEventListener("click", function () {
        let form = document.querySelector("#form-search");
        form.setAttribute("action", "${root}/article");
        form.submit();
    });

    // select 요소 가져오기
    var selectElement = document.getElementById("key");

    // option 요소 중 value가 "subject"인 요소의 인덱스를 가져오기
    var optionIndex = Array.from(selectElement.options).findIndex(option => option.value === "${key}");

    // 해당 인덱스를 selectedIndex 속성으로 설정하여 해당 option을 선택하도록 함
    selectElement.selectedIndex = optionIndex;
</script>
</body>

</html>