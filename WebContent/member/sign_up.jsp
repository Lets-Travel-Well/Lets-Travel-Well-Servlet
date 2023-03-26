<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="root" value="${pageContext.request.contextPath}" />
    <!DOCTYPE html>
    <html lang="en">

    <head>
      <meta charset="utf-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
      <meta name="description" content="" />
      <meta name="author" content="" />
      <title>LTW</title>
      <!-- Favicon-->
      <link rel="icon" type="image/x-icon" href="${root}/assets/favicon.ico" />
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
			<jsp:param value="ok" name="flag"/>
			</jsp:include>
        </div>
      </nav>
      <!-- Masthead-->
      <header class="masthead">
        <div class="container">
          <div class="masthead-subheading">Wherever You Go</div>
        </div>
      </header>
      <!-- signup section -->
      <section>
        <div class="container" id="signup-section">
          <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-lg-12 col-xl-11">
              <div class="card text-black shadow" style="border-radius: 25px">
                <div class="card-body p-md-5 sh">
                  <div class="row justify-content-center">
                    <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">
                      <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">
                        Sign up
                      </p>

                      <form class="mx-1 mx-md-4" id="form-join" method="POST" action="">
                        <input type="hidden" name="action" value="join">
                        <div class="d-flex flex-row mb-4">
                          <i class="mt-2 fas fa-file-signature fa-lg me-3 fa-fw"></i>
                          <div class="form-outline flex-fill mb-0 me-3">
                            <input type="text" id="loginId" name="loginId" class="form-control" />
                            <label class="form-label" for="loginId">Your Id</label>
                          </div>
                          <!-- 아이디 중복 버튼  -->
                          <div class="col-2">
                            <button id="idCheck" class="btn btn-outline-success shadow" type="button">
                              check
                            </button>
                          </div>
                          <div>

                          </div>
                        </div>

                        <div class="d-flex flex-row mb-4">
                          <i class=" mt-2 fas fa-lock fa-lg me-3 fa-fw"></i>
                          <div class="form-outline flex-fill mb-0">
                            <input type="password" id="loginPw" name="loginPw" class="form-control" />
                            <label class="form-label" for="loginPw">Password</label>
                          </div>
                        </div>

                        <div class="d-flex flex-row  mb-4">
                          <i class="mt-2 fas fa-key fa-lg me-3 fa-fw"></i>
                          <div class="form-outline flex-fill mb-0">
                            <input type="password" id="loginPwConfirm" class="form-control" />
                            <label class="form-label" for="loginPwConfirm">Repeat your password</label> </br>
                            <span id="passwordConfirmSpan"> </span>
                          </div>
                        </div>


                        <div class="d-flex flex-row mb-4">
                          <i class="mt-2 fas fa-user fa-lg me-3 fa-fw"></i>
                          <div class="form-outline flex-fill mb-0">
                            <input type="text" id="memberName" class="form-control" />
                            <label class="form-label" for="memberName">Your Name</label>
                          </div>
                        </div>

                        <div class="d-flex flex-row mb-4">
                          <i class="mt-2 fas fa-envelope fa-lg me-3 fa-fw"></i>
                          <div class="form-outline flex-fill mb-0">
                            <input type="email" id="email" name="email" class="form-control" />
                            <label class="form-label" for="email">Your Email</label>
                          </div>
                        </div>

                        <div class="d-flex flex-row mb-4">
                          <i class="mt-2 fas fa-phone  fa-lg me-3 fa-fw"></i>
                          <div class="form-outline flex-fill mb-0">
                            <input type="tel" id="phone" name="phone" class="form-control"
                              pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}" required>
                            <label class="form-label" for="phone">Your Phone</label> </br>
                            <small>Format: 010-1234-7890</small>
                          </div>
                        </div>



                        <div class="form-check d-flex justify-content-center mb-5">
                          <input class="form-check-input me-2" type="checkbox" value="" id="form2Example3c" />
                          <label class="form-check-label" for="form2Example3">
                            I agree all statements in
                            <a href="#!">Terms of service</a>
                          </label>
                        </div>

                        <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                          <button type="button" class="btn btn-primary btn-lg shadow" id="register-button">
                            Register
                          </button>
                        </div>
                      </form>
                    </div>
                    <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">
                      <img src="${root}/assets/img/user/5501718.jpg" class="img-fluid" alt="Sample image" />
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



      <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
      <!-- * *                               SB Forms JS                               * *-->
      <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
      <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
      <script>

      console.log("${root}")
      </script>
            <script src="${root}/assets/js/script.js"></script>
      <script src='${root}/assets/js/signup.js'></script>
      <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>

    </html>