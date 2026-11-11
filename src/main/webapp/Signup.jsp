
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Foodie | Create Account</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/Signup.css">

</head>

<body>

	<!-- Main Registration Container -->
	<div class="register-container">

		<div class="register-card">

			<!-- Brand Section -->
			<div class="brand-section">

				<h1>
					Foodie<span>.</span>
				</h1>

				<p>Create your account and start exploring delicious food.</p>

			</div>

			<!-- Success and Error Messages -->
			<%
			String error = request.getParameter("error");

			if ("empty".equals(error)) {
			%>
			<p class="error">Please fill in all the required fields.</p>
			<%
			} else if ("username".equals(error)) {
			%>
			<p class="error">Username already exists. Please choose another.
			</p>
			<%
			} else if ("email".equals(error)) {
			%>
			<p class="error">Email already registered. Please use another
				email.</p>
			<%
			} else if ("duplicate".equals(error)) {
			%>
			<p class="error">Username or email already exists.</p>
			<%
			} else if ("password".equals(error)) {
			%>
			<p class="error">Password must contain at least 8 characters and
				cannot exceed 72 UTF-8 bytes.</p>
			<%
			} else if ("server".equals(error)) {
			%>
			<p class="error">Server error. Please try again later.</p>
			<%
			} else if ("failed".equals(error)) {
			%>
			<p class="error">Registration failed. Please try again.</p>
			<%
			}
			%>

			<!-- Registration Form -->
			<form action="${pageContext.request.contextPath}/SignupServlet"
				method="post">

				<!-- Username -->
				<div class="form-group">

					<label for="username">Username</label> <input type="text"
						id="username" name="username" placeholder="Enter your username"
						autocomplete="username" maxlength="60" required>

				</div>

				<!-- Email -->
				<div class="form-group">

					<label for="email">Email Address</label> <input type="email"
						id="email" name="email" placeholder="Enter your email"
						autocomplete="email" required>

				</div>

				<!-- Password -->
				<div class="form-group">

					<label for="password">Password</label> <input type="password"
						id="password" name="password" placeholder="Create a password"
						autocomplete="new-password" minlength="8" required>

				</div>

				<!-- Delivery Address -->
				<div class="form-group">

					<label for="address">Delivery Address</label> <input type="text"
						id="address" name="address"
						placeholder="Enter your delivery address"
						autocomplete="street-address" required>

				</div>

				<!-- Register Button -->
				<button type="submit" class="register-btn">Create Account</button>

			</form>

			<!-- Login Link -->
			<div class="login-section">

				<p>
					Already have an account? <a
						href="${pageContext.request.contextPath}/Login.jsp"> Login </a>
				</p>

			</div>

		</div>

	</div>

</body>
</html>
