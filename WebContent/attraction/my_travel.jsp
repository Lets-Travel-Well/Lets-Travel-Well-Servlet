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
      <link
	  	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
	  	rel="stylesheet"
	  />
      <!-- Google fonts-->
      <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" rel="stylesheet"/>
      <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
      <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet"
        type="text/css" />
      <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.css" rel="stylesheet"/>
      <!-- Core theme CSS (includes Bootstrap)-->
      <link href="${root}/assets/css/style.css" rel="stylesheet" />
      <link href="${root}/assets/css/map.css" rel="stylesheet" />
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
        <div class="masthead-subheading">Like Wherever You Go</div>
      </header>

      <center>
        <section class="page-section p-3 mt-3" id="my-travel">
          <div class="text-center fw-bold w-75" role="alert">
            <h2 class="section-heading text-uppercase">My Travel</h2>
            <h3 class="section-subheading text-muted">
              나의 여행지 정보를 조회하세요.
            </h3>
            <div class="row">
	            <!-- kakao map start -->
	            <div class="col">
					<div class = "h3"> Shortest Path </div>
	            	<div id="map" class="mt-3 shadow" style="width: 100%; height: 600px"></div>
	            </div>
				<!-- kakao map end -->
            	
            	<!-- TODO: 내가 스크랩한 위치들 게시판의 형태로 보여주기 -->
            	<div class = "col "> 
            		<div class = "h3"> LIKE Place </div>
            		<p class="text-secondary">최단 경로를 구할 여행지를 선택해주세요.</p>
            		<div  class="overflow-auto" style="width: 100%; height: 600px">  
            		
            		<!--  사용자가 선택한 정보 리스트 조회 : ajax => data 조회  -->
            			<div class="card m-3">
            				<div class="card-body row"> 
            					<div class="col-2"> Image </div>
            					<div class="col-2"> Name </div>
            					<div class="col-4"> Address </div>
            					<div class="col-3"> Zipcode </div>
            					<input type="checkbox" class="mt-1 col-1" id="choose-button" style="width: 20px; height: 20px;"></input>	
            				</div> 
            			</div>   
              				
            		 </div> 
            		
            	</div>
            	
            </div>

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
      
      <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
      <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.js"></script>
      <!-- kakao api JS -->
      <script type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=65e66ed3a412cb73e32b300c2f3a1803&libraries=services,clusterer,drawing"></script>
      <script src="${root}/assets/js/my_travel.js"></script>
      
    </body>

    </html>