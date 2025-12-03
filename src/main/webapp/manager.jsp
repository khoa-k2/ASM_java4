<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý Video</title>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">

</head>
<body class="bg-light">

    <!-- Navbar -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/home">Blog Tổng Hợp</a>

			<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
				data-bs-target="#navbarNav" aria-controls="navbarNav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav ms-auto">
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/home">Trang Chủ</a></li>

					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/manager">Quản lý Video</a></li>

					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/logout">Đăng xuất</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container mt-4">

		<h2 class="mb-3">Quản lý Video</h2>

		<div class="row">
			<div class="col-md-4">

				<!-- Form thêm Video -->
				<div class="card shadow">
					<div class="card-header bg-primary text-white">
						Thêm Video Mới
					</div>

					<div class="card-body">
						<form action="${pageContext.request.contextPath}/manager" method="post">

							<div class="mb-3">
								<label class="form-label">ID Video:</label>
								<input type="text" name="id" class="form-control" required>
							</div>

							<div class="mb-3">
								<label class="form-label">Title:</label>
								<input type="text" name="title" class="form-control" required>
							</div>

							<div class="mb-3">
								<label class="form-label">Link YouTube:</label>
								<input type="text" name="link" class="form-control"
									placeholder="https://www.youtube.com/watch?v=xxxx" required>
							</div>

							<div class="mb-3">
								<label class="form-label">Poster (URL hình ảnh):</label>
								<input type="text" name="poster" class="form-control">
							</div>

							<div class="mb-3">
								<label class="form-label">Mô tả:</label>
								<textarea name="description" class="form-control" rows="3"></textarea>
							</div>

							<button type="submit" class="btn btn-success w-100">Thêm Video</button>

						</form>

						<c:if test="${not empty message}">
							<div class="alert alert-info mt-3">${message}</div>
						</c:if>
					</div>
				</div>
			</div>

			<!-- Danh sách video -->
			<div class="col-md-8">
				<div class="card shadow">
					<div class="card-header bg-secondary text-white">
						Danh sách Video
					</div>

					<div class="card-body">

						<table class="table table-bordered table-hover">
							<thead class="table-dark">
								<tr>
									<th>ID</th>
									<th>Title</th>
									<th>Poster</th>
									<th>Hành động</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="v" items="${videos}">
									<tr>
										<td>${v.id}</td>
										<td>${v.title}</td>
										<td>
											<img src="${v.poster}" width="90" class="rounded">
										</td>
										<td>
											<a href="edit-video?id=${v.id}" class="btn btn-warning btn-sm">Sửa</a>
											<a href="delete-video?id=${v.id}" class="btn btn-danger btn-sm"
												onclick="return confirm('Xóa video này?')">Xóa</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

					</div>
				</div>
			</div>
		</div>

	</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>