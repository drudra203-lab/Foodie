
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Foodie - Login</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/Login.css">
</head>

<body>

	<!-- Main Login Container -->
	<div class="login-container">

		<div class="logo">
			<h1>
				Foodie<span>.</span>
			</h1>
			<p>Welcome back! Login to continue</p>
		</div>

		<!-- Success and Error Messages -->
		<%
		String error = request.getParameter("error");
		String signup = request.getParameter("signup");

		if ("success".equals(signup)) {
		%>
		<p class="success">Account created successfully! Please login.</p>
		<%
		} else if ("empty".equals(error)) {
		%>
		<p class="error">Please enter your username and password.</p>
		<%
		} else if ("invalid".equals(error)) {
		%>
		<p class="error">Invalid username or password. Please try again.</p>
		<%
		} else if ("server".equals(error)) {
		%>
		<p class="error">Something went wrong. Please try again later.</p>
		<%
		}
		%>

		<!-- Login Form -->
		<form action="${pageContext.request.contextPath}/LoginServlet"
			method="post">

			<!-- Username -->
			<div class="input-group">

				<label for="username">Username</label> <input type="text"
					id="username" name="username" placeholder="Enter your username"
					autocomplete="username" required>

			</div>

			<!-- Password -->
			<div class="input-group">

				<label for="password">Password</label> <input type="password"
					id="password" name="password" placeholder="Enter your password"
					autocomplete="current-password" required>

			</div>

			<!-- Forgot Password -->
			<div class="forgot-password">
				<a href="#">Forgot Password?</a>
			</div>

			<!-- Submit Button -->
			<button type="submit">Login</button>

		</form>

		<!-- Signup Link -->
		<div class="register-link">

			<p>
				Don't have an account? <a
					href="${pageContext.request.contextPath}/Signup.jsp"> Create
					Account </a>
			</p>

		</div>

	</div>

</body>
</html>
