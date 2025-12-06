<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${video.title}</title>
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

<div class="container mt-4">

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <c:if test="${not empty video}">

        <h2>${video.title}</h2>

        <c:set var="vid" value="${fn:substringAfter(video.link, 'v=')}" />

        <iframe width="100%" height="450"
            src="https://www.youtube.com/embed/${vid}"
            frameborder="0" allowfullscreen>
        </iframe>

        <p class="mt-3">
            <strong>Lượt xem:</strong> ${video.views}<br>
           <%--  /<strong>Lượt thích:</strong> ${video.likes}<br>
            <strong>Lượt chia sẻ:</strong> ${video.shares} --%>
        </p>

        <button class="btn btn-primary" onclick="likeVideo(${video.id})">Like</button>
        <button class="btn btn-success" onclick="shareVideo(${video.id})">Share</button>

        <p class="mt-4">${video.description}</p>

    </c:if>

</div>

<script>
function likeVideo(id){
    alert("Like video " + id);
    // TODO: Gọi servlet Like
}

function shareVideo(id){
    let email = prompt("Nhập email chia sẻ:");
    if(email){
        alert("Đã chia sẻ video " + id + " đến " + email);
        // TODO: Gọi servlet Share
    }
}
</script>

</body>
</html>
