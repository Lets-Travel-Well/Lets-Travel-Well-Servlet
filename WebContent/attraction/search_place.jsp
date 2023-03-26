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
      <link rel="icon" type="image/x-icon" href="./assets/favicon.ico" />
      <!-- service Key -->
      <!-- <script src="./js/key.js"></script> -->
      <!-- bootstrap -->
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous" />
      <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css" />
      <!-- Font Awesome icons (free version)-->
      <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
      <!-- Google fonts-->
      <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
      <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet"
        type="text/css" />
      <!-- Core theme CSS (includes Bootstrap)-->
      <link href="${root}/assets/css/style.css" rel="stylesheet" />
    </head>

    <body id="page-top">
      <!-- Navigation   헤더 -->
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
      <!-- Navigation   헤더 end -->

      <header class="masthead">
        <div class="masthead-subheading">Search Wherever You Go</div>
      </header>

      <center>
        <section class="page-section p-3 mt-3" id="search-place">
          <div class="text-center fw-bold w-75" role="alert">
            <h2 class="section-heading text-uppercase">Travel Information</h2>
            <h3 class="section-subheading text-muted">
              원하는 여행지를 검색해보세요.
            </h3>

            <!-- 관광지 검색 start -->
            <form class="mb-3 d-flex justify-content-center" onsubmit="return false;" role="search">
              <div class="col-3 m-1 ms-5">
                <select id="search-area" class="form-select me-2 shadow">
                  <option value="0" selected>검색 할 시도</option>
                  <c:forEach items="${sidos}" var="sido">
                    <option value="${sido.sidoCode}">${sido.sidoName}</option>
                  </c:forEach>
                </select>
              </div>
              <div class="col-3 m-1">
                <select id="search-sigun" class="form-select me-2 shadow">
                  <option value="0" selected>검색 할 구군</option>
                </select>
              </div>
              <div class="col-3 mt-1 ms-1">
                <select id="search-content-id" class="form-select me-2 shadow bg-gradient"
                  style="background-color: #fefaea">
                  <option value="0" selected>컨텐츠 선택</option>
                  <option value="12">관광지</option>
                  <option value="14">문화시설</option>
                  <option value="15">축제공연행사</option>
                  <option value="25">여행코스</option>
                  <option value="28">레포츠</option>
                  <option value="32">숙박</option>
                  <option value="38">쇼핑</option>
                  <option value="39">음식점</option>
                </select>
                <!-- <input
                id="search-keyword"
                class="form-control me-2 shadow"
                type="search"
                placeholder="검색어"
                aria-label="검색어"
              /> -->
              </div>
              <div class="col-2 mt-1">
                <button id="btn-search" class="btn btn-outline-success shadow" type="button">
                  Search
                </button>
              </div>
            </form>

            <!-- kakao map start -->
            <div id="map" class="mt-3 shadow" style="width: 100%; height: 400px"></div>
            <!-- kakao map end -->
            <div class="row mt-3">
              <table class="table table-striped" style="display: none">
                <thead>
                  <tr>
                    <th>대표이미지</th>
                    <th>관광지명</th>
                    <th>주소</th>
                    <th>위도</th>
                    <th>경도</th>
                  </tr>
                </thead>
                <tbody id="trip-list"></tbody>
              </table>
            </div>
            <!-- 관광지 검색 end -->
          </div>
        </section>
      </center>
      <!-- Footer start-->
      <footer class="footer py-4 mt-5">
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
      <!-- Footer end -->

      <!-- Bootstrap core JS-->
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
      <!-- Core theme JS-->
      <script src="${root}/assets/js/script.js"></script>
      <script src="${root}/assets/js/search_place.js"></script>

      <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
      <!-- kakao api JS -->
      <script type="text/javascript"
        src="//dapi.kakao.com/v2/maps/sdk.js?appkey=65e66ed3a412cb73e32b300c2f3a1803&libraries=services,clusterer,drawing"></script>
    </body>

    </html>