<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page
	import="com.foodie.model.User,
                 com.foodie.model.Cart,
                 com.foodie.model.CartItem"%>

<%
// Check whether the customer is logged in.
User user = (User) session.getAttribute("user");

if (user == null) {
	response.sendRedirect(request.getContextPath() + "/Login.jsp");
	return;
}

// Retrieve cart from session.
Cart cart = (Cart) session.getAttribute("cart");

if (cart == null || cart.getItems() == null || cart.getItems().isEmpty()) {
	response.sendRedirect(request.getContextPath() + "/Cart.jsp");
	return;
}

// Calculate item total dynamically.
double itemTotal = 0;

for (CartItem item : cart.getItems().values()) {
	itemTotal += item.getPrice() * item.getQuantity();
}

// Fixed charges.
double deliveryFee = 40;
double platformFee = 5;

// Final amount.
double grandTotal = itemTotal + deliveryFee + platformFee;
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Checkout | Foodie</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/Checkout.css">
</head>

<body>
	<div class="page">

		<!-- Header -->
		<header class="topbar">

			<a href="<%=request.getContextPath()%>/Cart.jsp" class="back-link">
				&larr; Back to Cart </a> 
				<a href="<%=request.getContextPath()%>/RestaurantServlet" class="logo">
				Foodie<span>.</span>
			</a>

			<div class="secure-text">&#128274; Secure Checkout</div>

		</header>

		<!-- Heading -->
		<section class="intro">

			<p class="small-heading">ALMOST THERE</p>

			<h1>Checkout</h1>

			<p>Confirm your delivery details and choose your payment method.</p>

		</section>

		<!-- Checkout Form -->
		<form action="<%=request.getContextPath()%>/CheckoutServlet"
			method="post" class="checkout-grid">

			<div class="left-column">

				<!-- Delivery Address -->
				<section class="panel">

					<div class="panel-heading">

						<div class="step">01</div>

						<div>
							<h2>Delivery Address</h2>
							<p>Where should we deliver your order?</p>
						</div>

					</div>

					<label for="address">Full Delivery Address</label>

					<textarea id="address" name="address"
						placeholder="Enter house number, street, area, landmark, city and PIN code"
						minlength="5" maxlength="500" required></textarea>

				</section>

				<!-- Payment Method -->
				<section class="panel">

					<div class="panel-heading">

						<div class="step">02</div>

						<div>
							<h2>Payment Method</h2>
							<p>Select how you would like to pay.</p>
						</div>

					</div>

					<div class="payment-list">

						<label class="payment-card"> <input type="radio"
							name="paymentMode" value="Cash on Delivery" required> <span
							class="payment-icon">&#128181;</span> <span class="payment-info">
								<strong>Cash on Delivery</strong> <small>Pay when your
									order arrives</small>
						</span> <span class="custom-radio"></span>

						</label> <label class="payment-card"> <input type="radio"
							name="paymentMode" value="UPI"> <span
							class="payment-icon">&#128241;</span> <span class="payment-info">
								<strong>UPI</strong> <small>Pay using your UPI
									application</small>
						</span> <span class="custom-radio"></span>

						</label> <label class="payment-card"> <input type="radio"
							name="paymentMode" value="Card"> <span
							class="payment-icon">&#128179;</span> <span class="payment-info">
								<strong>Credit / Debit Card</strong> <small>Pay using
									your bank card</small>
						</span> <span class="custom-radio"></span>

						</label>

					</div>

					<p class="note">Select your preferred payment method to
						continue.</p>

				</section>

			</div>

			<!-- Dynamic Order Summary -->
			<aside class="panel summary-panel">

				<div class="summary-heading">

					<h2>Order Summary</h2>

					<span>&#128722;</span>

				</div>

				<!-- Actual cart items -->
				<div class="cart-items">

					<%
					for (CartItem item : cart.getItems().values()) {
					%>

					<div class="checkout-item">

						<div class="item-details">

							<strong> <%=item.getItemName()%>
							</strong> <small> ₹<%=String.format("%.2f", item.getPrice())%> ×
								<%=item.getQuantity()%>
							</small>

						</div>

						<strong> ₹<%=String.format("%.2f", item.getTotalPrice())%>
						</strong>

					</div>

					<%
					}
					%>

				</div>

				<!-- Dynamic price calculation -->
				<div class="price-lines">

					<div class="price-line">
						<span>Item Total</span> <span> ₹<%=String.format("%.2f", itemTotal)%>
						</span>
					</div>

					<div class="price-line">
						<span>Delivery Fee</span> <span> ₹<%=String.format("%.2f", deliveryFee)%>
						</span>
					</div>

					<div class="price-line">
						<span>Platform Fee</span> <span> ₹<%=String.format("%.2f", platformFee)%>
						</span>
					</div>

				</div>

				<!-- Grand Total -->
				<div class="total-line">

					<strong>To Pay</strong> <strong> ₹<%=String.format("%.2f", grandTotal)%>
					</strong>

				</div>

				<button type="submit" class="place-order-btn">
					Place Order <span>&rarr;</span>
				</button>

				<p class="terms">By placing your order, you agree to Foodie's
					terms and conditions.</p>

			</aside>

		</form>

		<!-- Footer -->
		<footer class="footer"> &copy; 2026 Foodie. Made with care
			for food lovers. </footer>

	</div>

</body>
</html>
