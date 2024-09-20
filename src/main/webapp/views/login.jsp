<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<h2>Login</h2>
	<form action="/LTW/login" method="post">
		<c:if test="${alert != null}">
			<h3 class="alert alert-danger">${alert}</h3>
		</c:if>
		<label for="uname">Username:</label>
		<input type="text" id="uname" name="uname" required> <br>
		<label for="psw">Password:</label>
		<input type="password" id="psw" name="psw" required> <br>
		<button type="submit">Login</button>
	</form>
	<c:if test="${not empty errorMessage}">
		<p style="color: red">${errorMessage}</p>
	</c:if>
	<a href="register">Register</a>
	<a href="forgot-password">Forgot Password</a>
</body>
</html>
