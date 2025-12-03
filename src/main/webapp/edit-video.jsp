<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chỉnh sửa Video</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>

<body class="bg-light">

<div class="container mt-4">
    <h2 class="mb-3">Chỉnh sửa Video</h2>

    <div class="card shadow p-4">
        <form action="${pageContext.request.contextPath}/update-video" method="post">

            <div class="mb-3">
                <label>ID Video:</label>
                <input type="text" name="id" value="${video.id}" class="form-control" readonly>
            </div>

            <div class="mb-3">
                <label>Title:</label>
                <input type="text" name="title" value="${video.title}" class="form-control">
            </div>

            <div class="mb-3">
                <label>Link YouTube:</label>
                <input type="text" name="link" value="${video.link}" class="form-control">
            </div>

            <div class="mb-3">
                <label>Poster URL:</label>
                <input type="text" name="poster" value="${video.poster}" class="form-control">
            </div>

            <div class="mb-3">
                <label>Mô tả:</label>
                <textarea name="description" class="form-control" rows="3">${video.description}</textarea>
            </div>

            <button class="btn btn-primary w-100">Cập nhật</button>

        </form>

        <c:if test="${not empty message}">
            <div class="alert alert-info mt-3">${message}</div>
        </c:if>

    </div>
</div>

</body>
</html>