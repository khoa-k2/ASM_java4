<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" 
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">

</head>
<body class="bg-light">

<c:if test="${not empty warning}">
    <div class="alert alert-warning mt-3 p-2 text-center">
        ${warning} <br>
        <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger mt-2">
            Đăng xuất
        </a>
    </div>
</c:if>
<div class="container d-flex justify-content-center align-items-center" style="height:100vh;">
    <div class="card shadow p-4" style="width: 350px;">
    <c:if test="${empty warning}">
        <h3 class="text-center mb-4">Đăng nhập hệ thống</h3>

        <form action="${pageContext.request.contextPath}/login" method="post">
            
            <div class="mb-3">
                <label class="form-label">Tên đăng nhập:</label>
                <input type="text" name="Id" class="form-control" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Mật khẩu:</label>
                <input type="password" name="Password" class="form-control" required>
            </div>

            <button type="submit" class="btn btn-primary w-100">Đăng nhập</button>
        </form>
        <c:if test="${not empty success}">
				<div class="alert alert-success text-center">${success}</div>
			</c:if>
			
        <c:if test="${not empty error}">
            <div class="alert alert-danger mt-3 p-2 text-center">${error}</div>
        </c:if>
</c:if>
    </div>
</div>

</body>

</html>