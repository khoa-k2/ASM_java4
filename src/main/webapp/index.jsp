
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
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
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
						href="${pageContext.request.contextPath}/home">Trang Chủ</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/about.jsp">Giới thiệu</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/favorite">Video yêu
							thích</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/manager">Quản lý
							Video</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/login">Đăng nhập</a></li>
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
							<a href="video/view?id=${video.id} "><h5 class="card-title">${video.title}</h5></a>

							<p class="text-muted mb-2">
								❤️ Views: <strong>${video.views}</strong>
							</p>
							
							
							<c:if test="${not empty sessionScope.user}">
								<c:set var="videoId" value="${video.id}" />
								<button class="btn btn-primary"
									onclick="shareVideo('${video.id}')">Share</button>
							</c:if>
							<c:if test="${empty sessionScope.user}">
								<a href="login.jsp"><button class="btn btn-secondary">Login
										để share</button></a>
							</c:if>
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
	<!-- <!-- Share Modal -->
	<script>
function shareVideo(videoId) {
    fetch(`video/share?id=${videoId}`)
        .then(res => res.text()) // đọc raw text trước
        .then(text => {
            if(!text) throw new Error("Empty response");
            const data = JSON.parse(text); // parse JSON an toàn
            if(data.status === 'success'){
                const shareUrl = window.location.origin + "/ASM_JAVA4/videoDetail?id=" + videoId;
                const message = `
Chia sẻ video qua:
1. Facebook
2. Twitter
3. Copy link
                `;
                const choice = prompt(message + "\nNhập 1, 2 hoặc 3:");

                if(choice === '1'){
                    window.open(
                        "https://www.facebook.com/sharer/sharer.php?u=" + encodeURIComponent(shareUrl),
                        "Share","width=600,height=400");
                } else if(choice === '2'){
                    window.open(
                        "https://twitter.com/intent/tweet?url=" + encodeURIComponent(shareUrl),
                        "Share","width=600,height=400");
                } else if(choice === '3'){
                    navigator.clipboard.writeText(shareUrl)
                        .then(() => alert("Link đã được copy!"))
                        .catch(err => alert("Copy thất bại: " + err));
                }
            } else {
                alert(data.message); // thông báo lỗi (chưa login hoặc video không tồn tại)
            }
        })
        .catch(err => console.error("Lỗi fetch shareVideo:", err));
}
</script>
</body>
</html>