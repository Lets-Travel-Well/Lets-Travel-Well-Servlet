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
                integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
                crossorigin="anonymous" />
            <link rel="stylesheet"
                href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css" />
            <link href="${root}/assets/css/style.css" rel="stylesheet" />
            <style>
                #bgimg {
                    background-image: url('${root}/assets/img/user/5501718.jpg');
                    border-radius: 25px;
                    background-size: cover;
                    opacity: 0.85;
                }
            </style>
        </head>

        <body id="page-top">
            <!-- Navigation-->
            <nav class="shadow navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
                <div class="container">
                    <a class="navbar-brand fs-4" href="${root }">
                        <!-- <img src="assets/img/navbar-logo.svg" alt="..." /> -->
                        Let's Travel Well
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"
                        aria-label="Toggle navigation">
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


            <section>
                <div class="container" id="mypage-section">
                    <div class="row d-flex justify-content-center align-items-center h-100">
                        <div class="col-lg-12 col-xl-11">
                            <div class="card text-black shadow border border-primary" style="border-radius: 25px">
                                <div class="card-body p-md-5 sh" id="bgimg">
                                    <div class="row justify-content-center">
                                        <div class="container" id="article-modify-section">
                                            <div class="row justify-content-center">
                                                <div class="col-lg-8 col-md-10 col-sm-12">
                                                    <h2
                                                        class="border rounded border-warning my-3 py-3 shadow-sm  text-center">
                                                        수정
                                                    </h2>
                                                </div>
                                                <div class="col-lg-8 col-md-10 col-sm-12">
                                                    <form id="form-register" method="POST" action="">
                                                        <input type="hidden" name="action" value="modify">
                                                        <input type="hidden" name="articleId" value="${article.id}">
                                                        <input type="hidden" name="memberId"
                                                            value="${article.memberId}">
                                                        <div class="mb-3">
                                                            <label for="subject" class="form-label fw-bolder">제목 :
                                                            </label>
                                                            <input type="text" class="form-control" id="subject"
                                                                name="subject" value="${article.subject}" />
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="content" class="form-label fw-bolder">내용 :
                                                            </label>
                                                            <textarea class="form-control" id="content" name="content"
                                                                rows="7">${article.content}</textarea>
                                                        </div>
                                                        <div class="col-auto text-center">
                                                            <button type="button" id="btn-modify"
                                                                class="btn btn-primary  shadow mb-3">
                                                                수정하기
                                                            </button>
                                                            <button type="button" id="btn-delete"
                                                                class="btn btn-danger  shadow mb-3">
                                                                글삭제
                                                            </button>
                                                            <button type="reset" id="btn-view"
                                                                class="btn btn-danger shadow mb-3">뒤로가기</button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- Footer-->
            <footer class="footer py-4">
                <div class="container">
                    <div class="row align-items-center">
                        <div class="col-lg-4 text-lg-start">
                            Copyright &copy; Your Website 2022
                        </div>
                        <div class="col-lg-4 my-3 my-lg-0">
                            <a class="btn btn-dark btn-social mx-2" href="#!" aria-label="Twitter"><i
                                    class="fab fa-twitter"></i></a>
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
                document.querySelector("#btn-modify").addEventListener("click", function () {
                    location.href = "${root}/article?action=list"
                    if (!document.querySelector("#subject").value) {
                        alert("제목 입력!!");
                        return;
                    } else if (!document.querySelector("#content").value) {
                        alert("내용 입력!!");
                        return;
                    } else {
                        let form = document.querySelector("#form-register");
                        console.log(form);
                        form.setAttribute("action", "/ltw/article");
                        form.submit();
                    }
                });
                document.querySelector("#btn-view").addEventListener("click", function () {
                    location.href = "${root}/article?action=view&articleId=" + ${ article.id };
                });
                document.querySelector("#btn-delete").addEventListener("click", function () {
                    location.href = "${root}/article?action=delete&articleId=" + ${ article.id };
                });

            </script>
        </body>

        </html>