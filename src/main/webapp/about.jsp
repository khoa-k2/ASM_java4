<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Giới Thiệu</title>
<link rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body>
<body>

    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/home">Blog Tổng Hợp</a>

            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">

                    <li class="nav-item"><a class="nav-link"
                        href="${pageContext.request.contextPath}/home">Trang Chủ</a></li>

                    <li class="nav-item">
                        <a class="nav-link active" href="${pageContext.request.contextPath}/about.jsp">Giới Thiệu</a>
                    </li>

                    <li class="nav-item"><a class="nav-link"
                        href="${pageContext.request.contextPath}/favorite.jsp">Video yêu thích</a></li>

                    <li class="nav-item"><a class="nav-link"
                        href="${pageContext.request.contextPath}/manager">Quản lý Video</a></li>

                    <li class="nav-item"><a class="nav-link"
                        href="${pageContext.request.contextPath}/login">Đăng nhập</a></li>

                </ul>
            </div>
        </div>
    </nav>

    <!-- Nội dung giới thiệu -->
    <div class="container mt-5">

        <h2 class="text-center mb-4">Giới thiệu về Blog Tổng Hợp</h2>

        <div class="card shadow-sm p-4">

            <p>
                <strong>Blog Tổng Hợp</strong> là nền tảng chia sẻ video, bài viết và kiến thức tổng hợp
                trong nhiều lĩnh vực như giải trí, giáo dục, công nghệ và đời sống.
            </p>

            <p>
                Mục tiêu của chúng tôi là mang đến cho người dùng những nội dung chất lượng, hữu ích và 
                được chọn lọc mỗi ngày. Các video trên trang đều được tổng hợp từ những nguồn uy tín,
                giúp bạn cập nhật xu hướng và kiến thức nhanh chóng.
            </p>

            <h4 class="mt-4">🎯 Sứ mệnh</h4>
            <ul>
                <li>Cung cấp nội dung hữu ích và đáng tin cậy.</li>
                <li>Tạo không gian xem và chia sẻ video dễ dàng.</li>
                <li>Hỗ trợ người dùng quản lý video yêu thích.</li>
            </ul>

            <h4 class="mt-4">💡 Tính năng nổi bật</h4>
            <ul>
                <li>Xem video trực tiếp từ Youtube.</li>
                <li>Like và chia sẻ video đến bạn bè.</li>
                <li>Lưu video yêu thích của bạn.</li>
                <li>Quản lý danh sách video dành cho Admin.</li>
            </ul>

            <p class="mt-4">
                Chúng tôi luôn cố gắng hoàn thiện để mang đến trải nghiệm tốt nhất cho người dùng.
                Mọi góp ý của bạn đều giúp Blog trở nên hoàn thiện hơn.
            </p>

            <p class="text-end"><strong>— Đội ngũ phát triển Blog</strong></p>
        </div>

    </div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</body>
</html>