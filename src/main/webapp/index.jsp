<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ Video</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body>
	<!-- Navbar -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/home"> Blog Tổng Hợp</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav ms-auto">
				
				<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/home.jsp">Trang Chủ</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/about.jsp">Giới thiệu</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/login.jsp">Video yêu
							thích</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/login.jsp">Đăng
							nhập</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container mt-4">
		<h2>Video nổi bật</h2>
		<div class="row">
			<c:forEach var="video" items="${videos}">
				<div class="col-md-4 mb-4">
					<div class="card">

						<%-- <img src="${video.poster}" class="card-img-top" style="cursor:pointer"
                         onclick="location.href='videoDetail?id=${video.id}'">  --%>
						<c:set var="videoId" value="${fn:substringAfter(video.link,'v=')}" />
						<iframe width="100%" height="200"
							src="https://www.youtube.com/embed/${videoId}"
							title="${video.title}" frameborder="0"
							allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
							allowfullscreen></iframe>

						<div class="card-body">
							<h5 class="card-title">${video.title}</h5>
							<button class="btn btn-primary" onclick="likeVideo(${video.id})">Like</button>
							<button class="btn btn-success" onclick="shareVideo(${video.id})">Share</button>
						</div>
					</div>
				</div>
			</c:forEach>


		</div>

		<!-- Phân trang -->
		<nav>
			<ul class="pagination justify-content-center">
				<li class="page-item ${currentPage==1 ? 'disabled' : ''}"><a
					class="page-link" href="?page=1">&laquo; Đầu</a></li>
				<li class="page-item ${currentPage==1 ? 'disabled' : ''}"><a
					class="page-link" href="?page=${currentPage-1}">&lt; Trước</a></li>
				<c:forEach var="i" begin="1" end="${totalPages}">
					<li class="page-item ${i==currentPage ? 'active' : ''}"><a
						class="page-link" href="?page=${i}">${i}</a></li>
				</c:forEach>
				<li class="page-item ${currentPage==totalPages ? 'disabled' : ''}">
					<a class="page-link" href="?page=${currentPage+1}">Sau &gt;</a>
				</li>
				<li class="page-item ${currentPage==totalPages ? 'disabled' : ''}">
					<a class="page-link" href="?page=${totalPages}">Cuối &raquo;</a>
				</li>
			</ul>
		</nav>
	</div>

	<script>
function likeVideo(videoId){
    alert("Like video: " + videoId);
    // Gọi AJAX gửi request lên Servlet xử lý Like
}

function shareVideo(videoId){
    let email = prompt("Nhập email bạn muốn chia sẻ:");
    if(email){
        alert("Đã gửi link video " + videoId + " đến " + email);
        // Gọi AJAX gửi request lên Servlet xử lý Share
    }
}
</script>
</body>
</html>