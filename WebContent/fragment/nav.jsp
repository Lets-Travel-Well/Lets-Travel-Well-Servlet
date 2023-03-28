<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
          <li class="nav-item">
            <a class="nav-link" href="${root}/attraction?action=mvsearch#search-place">Search Place</a>
          </li>

          <c:if test="${empty userinfo}">
              <li class="nav-item">
                <a class="nav-link" href="${root}/member/login.jsp#login-section">login</a>
              </li>	
              <li class="nav-item">
                <a class="nav-link" href="${root}/member/sign_up.jsp#signup-section">Sign Up</a>
              </li>
		  </c:if>
		  <c:if test="${not empty userinfo}">
          	<li class="nav-item">
          	  <a class="nav-link" href="./my_travel.html#my-travel">My Travel</a>
          	</li>
            <li class="nav-item">
                <a class="nav-link" href="${root}/article?action=list#article-list-section">Community</a>
            </li>
          	  <li class="nav-item">
            	<a class="nav-link" href="${root}/member?action=mypage#mypage-section">My Page</a>
        	  </li>
        	  <li class="nav-item">
                <a class="nav-link" href="${root}/member?action=logout">logout</a>
              </li>
          </c:if>
        </ul>
      </div>
