
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.foodie.model.Cart"%>
<%@ page import="com.foodie.model.CartItem"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>My Cart | Foodie</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Cart.css">
</head>

<body>

	<!-- Navbar -->
	<nav class="navbar">

		<div class="logo">
			Foodie<span>.</span>
		</div>

		<div class="nav-links">
			<a href="<%=request.getContextPath()%>/RestaurantServlet"> Home </a>
			<a href="<%=request.getContextPath()%>/Login.jsp"> Login </a>
			<%
			if (session.getAttribute("user") != null) {
			%>
			<a href="<%=request.getContextPath()%>/LogoutServlet"> Logout </a>
			<%
			}
			%>
		</div>

	</nav>


	<!-- Main Cart Section -->
	<main class="cart-container">

		<h1>My Cart</h1>

		<p class="subtitle">Review your items before checkout.</p>

		<%
		Cart cart = (Cart) session.getAttribute("cart");

		boolean isCartEmpty = (cart == null || cart.getItems().isEmpty());

		int restaurantID = (cart != null) ? cart.getRestaurantId() : 0;

		String cartMessage = (String) session.getAttribute("cartMessage");

		session.removeAttribute("cartMessage");
		%>

		<%
		if (cartMessage != null) {
		%>

		<p class="cart-message">
			<%=cartMessage%>
		</p>

		<%
		}
		%>


		<div class="cart-layout <%=isCartEmpty ? "empty" : ""%>">

			<!-- Cart Items -->
			<section class="cart-items">

				<%
				if (!isCartEmpty) {
				%>

				<%
				for (CartItem item : cart.getItems().values()) {
				%>

				<div class="cart-item">

					<!-- Item Information -->
					<div class="item-info">

						<h3>
							<%=item.getItemName()%>
						</h3>

						<p>
							₹<%=item.getPrice()%>
							per item
						</p>

					</div>


					<!-- Quantity Controls -->
					<div class="quantity-control">

						<!-- Decrease Quantity -->
						<form action="CartServlet" method="post">

							<input type="hidden" name="menuId" value="<%=item.getId()%>">

							<input type="hidden" name="acction" value="update"> <input
								type="hidden" name="quantity"
								value="<%=item.getQuantity() - 1%>">

							<button type="submit" class="quantity-btn">−</button>

						</form>


						<span> <%=item.getQuantity()%>
						</span>


						<!-- Increase Quantity -->
						<form action="CartServlet" method="post">

							<input type="hidden" name="menuId" value="<%=item.getId()%>">

							<input type="hidden" name="acction" value="update"> <input
								type="hidden" name="quantity"
								value="<%=item.getQuantity() + 1%>">

							<button type="submit" class="quantity-btn">+</button>

						</form>

					</div>


					<!-- Item Total -->
					<div class="item-total">

						₹<%=item.getTotalPrice()%>

					</div>


					<!-- Remove Item -->
					<form action="CartServlet" method="post">

						<input type="hidden" name="menuId" value="<%=item.getId()%>">

						<input type="hidden" name="acction" value="delete">

						<button type="submit" class="remove-btn">Remove</button>

					</form>

				</div>

				<%
				}
				%>


				<%
				} else {
				%>

				<!-- Empty Cart Message -->
				<div class="empty-cart">

					<div class="empty-cart-icon">&#128722;</div>

					<h2>Your Cart is Empty!</h2>

					<p>You don't have any items in your cart. Please add some
						delicious food to continue.</p>

					<a href="<%=request.getContextPath()%>/RestaurantServlet"
						class="add-items-btn"> + Add Items </a>

				</div>

				<%
				}
				%>

			</section>


			<%
			if (!isCartEmpty) {
			%>

			<!-- Bill Summary -->
			<aside class="cart-summary">

				<h2>Bill Details</h2>


				<div class="summary-row">

					<span>Item Total</span> <span> ₹<%=cart.getTotalPrice()%>
					</span>

				</div>


				<div class="summary-row">

					<span>Delivery Fee</span> <span>₹40</span>

				</div>


				<div class="summary-row">

					<span>Platform Fee</span> <span>₹5</span>

				</div>


				<hr>


				<div class="summary-row grand-total">

					<span>Grand Total</span> <span> ₹<%=cart.getTotalPrice() + 45%>
					</span>

				</div>


				<!-- Add More Items -->
				<a
					href="<%=request.getContextPath()%>/menuItemServlet?restaurantID=<%=restaurantID%>"
					class="add-more-btn"> + Add More Items </a>


				<!-- Checkout -->
				<a href="<%=request.getContextPath()%>/CheckOut.jsp"
					class="checkout-btn"> Proceed to Checkout </a>

			</aside>

			<%
			}
			%>

		</div>

	</main>


	<!-- Footer -->
	<footer>

		<p>© 2026 Foodie. All rights reserved.</p>

	</footer>

</body>
</html>
