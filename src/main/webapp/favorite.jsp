<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Video yêu thích nhất</title>
<link rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>

<body>

    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">

            <a class="navbar-brand" href="${pageContext.request.contextPath}/home">
                Blog Tổng Hợp
            </a>

            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">

                    <li class="nav-item"><a class="nav-link"
                        href="${pageContext.request.contextPath}/home">Trang Chủ</a></li>

                    <li class="nav-item"><a class="nav-link"
                        href="${pageContext.request.contextPath}/about.jsp">Giới Thiệu</a></li>

                    <li class="nav-item">
                        <a class="nav-link active"
                           href="${pageContext.request.contextPath}/favorite.jsp">
                           Video yêu thích
                        </a>
                    </li>

                    <li class="nav-item"><a class="nav-link"
                        href="${pageContext.request.contextPath}/manager">Quản lý Video</a></li>

                    <li class="nav-item"><a class="nav-link"
                        href="${pageContext.request.contextPath}/login">Đăng nhập</a></li>

                </ul>
            </div>
        </div>
    </nav>

    <!-- Nội dung -->
    <div class="container mt-4">

        <h2 class="mb-4 text-center">Video được yêu thích nhất</h2>

        <c:if test="${empty favorites}">
            <div class="alert alert-info text-center">Chưa có video yêu thích nào.</div>
        </c:if>

        <div class="row">
            <c:forEach var="video" items="${favorites}">
                <div class="col-md-4 mb-4">

                    <div class="card shadow-sm">

                        <!-- Lấy ID video YouTube -->
                        <c:set var="videoId" value="${fn:substringAfter(video.link, 'v=')}" />

                        <iframe width="100%" height="200"
                            src="https://www.youtube.com/embed/${videoId}"
                            title="${video.title}"
                            frameborder="0"
                            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                            allowfullscreen>
                        </iframe>

                        <div class="card-body">
                            <h5 class="card-title">${video.title}</h5>

                          
                            <p class="text-muted">
                                👁️ Lượt xem: <strong>${video.views}</strong>
                            </p>

                            
                        </div>
                    </div>

                </div>
            </c:forEach>
        </div>

    </div>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
