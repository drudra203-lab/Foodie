
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page
	import="com.foodie.model.Restaurant, com.foodie.model.MenuItem, java.util.List, java.util.Collections"%>

<%!private String escapeHtml(String value) {
		if (value == null) {
			return "";
		}

		return value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;")
				.replace("'", "&#x27;");
	}%>

<%
Restaurant restaurant = (Restaurant) request.getAttribute("restaurant");

List<MenuItem> menuItems = (List<MenuItem>) request.getAttribute("MenuItems");

String menuError = (String) request.getAttribute("menuError");

if (menuItems == null) {
	menuItems = Collections.emptyList();
}
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title><%=restaurant != null ? escapeHtml(restaurant.getName()) + " | Restaurant Menu" : "Restaurant Menu | Foodie"%></title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/MenuItem.css">
</head>

<body>

	<!-- Navbar -->
	<nav class="navbar">

		<div class="logo">
			Foodie<span>.</span>
		</div>

		<div class="nav-links">
			<a href="<%=request.getContextPath()%>/RestaurantServlet"> Home
			</a> <a href="<%=request.getContextPath()%>/Cart.jsp"> My Orders </a> <a
				href="<%=request.getContextPath()%>/Login.jsp"> Login </a>
			<%
			if (session.getAttribute("user") != null) {
			%>
			<a href="<%=request.getContextPath()%>/LogoutServlet"> Logout </a>
			<%
			}
			%>
		</div>

	</nav>

	<main class="container">

		<%
		if (menuError != null) {
		%>

		<div class="menu-error">
			<p><%=escapeHtml(menuError)%></p>

			<a href="<%=request.getContextPath()%>/RestaurantServlet"> Back
				to Restaurants </a>
		</div>

		<%
		} else if (restaurant == null) {
		%>

		<div class="menu-error">
			<p>Restaurant information is unavailable.</p>

			<a href="<%=request.getContextPath()%>/RestaurantServlet"> Back
				to Restaurants </a>
		</div>

		<%
		} else {
		%>

		<!-- Restaurant Information -->
		<section class="restaurant-header">

			<div>

				<p class="breadcrumb">Home / Restaurants / Menu</p>

				<h1>
					<%=escapeHtml(restaurant.getName())%>
				</h1>

				<p class="restaurant-description">
					<%=escapeHtml(restaurant.getCategory())%>
				</p>

				<div class="restaurant-details">

					<span> ⭐ <%=restaurant.getRating()%> Rating
					</span> <span> 🕒 <%=escapeHtml(restaurant.getDeliveryTime())%>
						mins
					</span> <span> 📍 <%=escapeHtml(restaurant.getLocation())%>
					</span>

				</div>

			</div>

			<div class="restaurant-badge">
				<span>🍽️</span>
				<p>Restaurant Menu</p>
			</div>

		</section>

		<!-- Menu Heading -->
		<section class="menu-heading">

			<div>
				<h2>Explore Our Menu</h2>
				<p>Choose your favourite food</p>
			</div>

			<input type="search" id="searchInput"
				placeholder="Search food items..." aria-label="Search food items">

		</section>

		<!-- Category Filters -->
		<section class="categories">

			<button type="button" class="active" data-category="all">
				All Items</button>

			<button type="button" data-category="Starter">Starters</button>

			<button type="button" data-category="Main">Main Course</button>

			<button type="button" data-category="Dessert">Desserts</button>

			<button type="button" data-category="Drinks">Drinks</button>

		</section>

		<!-- Menu Items -->
		<section class="menu-grid" id="menuGrid">

			<%
			if (!menuItems.isEmpty()) {
			%>

			<%
			for (MenuItem menu : menuItems) {
			%>

			<div class="food-card"
				data-category="<%=escapeHtml(menu.getCategory())%>"
				data-name="<%=escapeHtml(menu.getItemName())%>">

				<div class="food-image">

					<%
					if (menu.getImageUrl() != null && !menu.getImageUrl().trim().isEmpty()) {
					%>

					<img src="<%=escapeHtml(menu.getImageUrl())%>"
						alt="<%=escapeHtml(menu.getItemName())%>" loading="lazy">

					<%
					} else {
					%>

					<div class="image-placeholder">Image unavailable</div>

					<%
					}
					%>

					<div class="food-type <%=menu.isVeg() ? "veg" : "nonveg"%>"
						title="<%=menu.isVeg() ? "Veg" : "Non-Veg"%>">

						<span class="veg-dot"></span>

					</div>

				</div>

				<div class="food-info">

					<h3>
						<%=escapeHtml(menu.getItemName())%>
					</h3>

					<p>
						<%=escapeHtml(menu.getDescription())%>
					</p>

					<div class="food-bottom">

						<span class="price"> ₹<%=String.format("%.2f", menu.getPrice())%>
						</span>

						<form action="<%=request.getContextPath()%>/CartServlet"
							method="post">

							<input type="hidden" name="menuId" value="<%=menu.getId()%>">

							<input type="hidden" name="quantity" value="1"> <input
								type="hidden" name="restaurantID"
								value="<%=menu.getRestaurantId()%>">

							<!-- Preserved existing parameter name -->
							<input type="hidden" name="acction" value="add">

							<button type="submit" class="add-btn">ADD TO CART</button>

						</form>

					</div>

				</div>

			</div>

			<%
			}
			%>

			<%
			}
			%>

			<p class="empty-state" id="emptyState"
				<%=menuItems.isEmpty() ? "" : "hidden"%>>

				<%=menuItems.isEmpty()
		? "No menu items are available for this restaurant right now."
		: "No matching food items found. Try another search or category."%>

			</p>

		</section>

		<%
		}
		%>

	</main>

	<!-- Cart Bar -->
	<div class="cart-bar">

		<div>
			<h3>Your Cart</h3>
			<p>Start adding delicious food!</p>
		</div>

		<a href="<%=request.getContextPath()%>/Cart.jsp"
			class="view-cart-btn"> View Cart → </a>

	</div>

	<!-- Search and Category Filtering -->
	<script>
		const searchInput = document.getElementById("searchInput");
		const categoryButtons = document.querySelectorAll(".categories button");
		const foodCards = document.querySelectorAll(".food-card");
		const emptyState = document.getElementById("emptyState");

		let selectedCategory = "all";

		function filterMenu() {

			const searchText = searchInput.value.toLowerCase().trim();
			let visibleCount = 0;

			foodCards.forEach(function(card) {

				const itemName = (card.dataset.name || "").toLowerCase();

				const itemCategory = card.dataset.category || "";

				const matchesSearch = itemName.includes(searchText);

				const matchesCategory = selectedCategory === "all"
						|| itemCategory === selectedCategory;

				if (matchesSearch && matchesCategory) {
					card.style.display = "";
					visibleCount++;
				} else {
					card.style.display = "none";
				}

			});

			if (emptyState) {
				emptyState.hidden = visibleCount !== 0;
				emptyState.textContent = "No matching food items found. Try another search or category.";
			}
		}

		if (searchInput) {
			searchInput.addEventListener("input", filterMenu);
		}

		categoryButtons.forEach(function(button) {

			button.addEventListener("click", function() {

				categoryButtons.forEach(function(btn) {
					btn.classList.remove("active");
				});

				button.classList.add("active");

				selectedCategory = button.dataset.category;

				filterMenu();

			});

		});
	</script>

</body>
</html>
