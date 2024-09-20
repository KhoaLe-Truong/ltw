<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quên Mật Khẩu</title>
</head>
<body>
    <h2>Quên Mật Khẩu</h2>
    <form action="/LTW/forgot-password" method="post">

        <label for="username">Tên đăng nhập:</label>
        <input type="text" id="username" name="username" required><br>
       
        <label for="newPassword">Mật khẩu mới:</label>
        <input type="password" id="newPassword" name="newPassword" required><br>

        <button type="submit">Cập nhật mật khẩu</button>
    </form>

    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
    <c:if test="${not empty message}">
        <p style="color: green;">${message}</p>
    </c:if>
</body>
</html>
