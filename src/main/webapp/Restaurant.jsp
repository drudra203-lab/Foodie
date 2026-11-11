
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page
	import="java.util.List, java.util.ArrayList, com.foodie.model.Restaurant"%>

<%!// Escape database values before displaying them in HTML.
	public String escapeHtml(String value) {
		if (value == null)
			return "";

		return value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;")
				.replace("'", "&#39;");
	}%>

<%
List<Restaurant> restaurants = (List<Restaurant>) request.getAttribute("allRestaurants");

if (restaurants == null) {
	restaurants = new ArrayList<Restaurant>();
}

String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<meta name="description"
	content="Discover restaurants and food across Bengaluru.">

<title>Foodie | Discover Bengaluru</title>

<link rel="preconnect" href="https://images.unsplash.com">

<link
	href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;600;700;800&family=Outfit:wght@500;600;700;800&display=swap"
	rel="stylesheet">

<link rel="stylesheet" href="<%=contextPath%>/css/Restaurant.css">
</head>

<body>

	<!-- Navigation -->
	<header class="site-header">
		<nav class="navbar">

			<a class="brand" href="<%=contextPath%>/RestaurantServlet">
				foodie<span>.</span>
			</a>

			<div class="nav-links">
				<a class="active" href="<%=contextPath%>/RestaurantServlet">
					Discover </a>
				<%
				if (session.getAttribute("user") != null) {
				%>
				<a href="<%=request.getContextPath()%>/LogoutServlet"> Logout </a>
				<%
				}
				%>

				<a href="#restaurants">Restaurants</a> <a href="#offers">Offers</a>
			</div>

			<a class="login-link" href="<%=contextPath%>/Login.jsp"> Log in </a>

			<a class="cart-button" href="<%=contextPath%>/Cart.jsp"> 🛍 Cart
			</a>

		</nav>
	</header>

	<main>

		<!-- Hero Section -->
		<section class="hero">

			<div class="hero-copy">

				<p class="kicker">BENGALURU'S FOOD SCENE</p>

				<h1>
					Good food.<br> <span>Good mood.</span>
				</h1>

				<p class="hero-description">Discover memorable dining spaces and
					restaurants across the city.</p>

				<div class="search-wrap">

					<span class="search-icon">⌕</span> <input id="searchInput"
						type="search"
						placeholder="Search restaurants, cuisines or areas..."
						aria-label="Search restaurants"> <a href="#restaurants"
						class="search-button"> Explore </a>

				</div>

				<p class="delivery-note">● Discover popular places across
					Bengaluru</p>

			</div>

			<div class="hero-art">

				<img
					src="https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?auto=format&fit=crop&w=1000&q=85"
					alt="Restaurant dining ambience">

				<div class="hero-tag">
					<strong><%=restaurants.size()%>+</strong> <span>places to
						explore</span>
				</div>

			</div>

		</section>

		<!-- Highlights -->
		<section class="offers-strip" id="offers">

			<div>
				<span class="offer-icon">⚡</span>
				<p>
					<strong>Quick picks</strong> <small>Well-known dining
						places</small>
				</p>
			</div>

			<div>
				<span class="offer-icon">✦</span>
				<p>
					<strong>Top rated</strong> <small>Customer favourites</small>
				</p>
			</div>

			<div>
				<span class="offer-icon">♡</span>
				<p>
					<strong>Something new</strong> <small>Find your next dining
						spot</small>
				</p>
			</div>

		</section>

		<!-- Restaurant Listing -->
		<section class="restaurants-section" id="restaurants">

			<div class="section-heading">

				<div>
					<p class="kicker">HANDPICKED FOR YOU</p>

					<h2>Explore restaurants</h2>

					<p class="section-subtitle">Explore restaurants and dining
						spaces across Bengaluru.</p>
				</div>

				<span class="result-count" id="resultCount"> <%=restaurants.size()%>
					restaurants
				</span>

			</div>

			<!-- Cuisine Filters -->
			<div class="filter-row">

				<button class="filter-chip active" data-filter="all" type="button">
					All restaurants</button>

				<button class="filter-chip" data-filter="south indian" type="button">
					South Indian</button>

				<button class="filter-chip" data-filter="andhra" type="button">
					Andhra</button>

				<button class="filter-chip" data-filter="asian" type="button">
					Asian</button>

				<button class="filter-chip" data-filter="coastal" type="button">
					Coastal</button>

				<button class="filter-chip" data-filter="continental" type="button">
					Continental</button>

				<button class="filter-chip" data-filter="fine dining" type="button">
					Fine dining</button>

			</div>

			<!-- Dynamic Restaurant Cards -->
			<div class="restaurant-grid" id="restaurantGrid">

				<%
				if (restaurants.isEmpty()) {
				%>

				<p class="empty-state">No restaurants available right now.
					Please try again later.</p>

				<%
				} else {

				for (Restaurant restaurant : restaurants) {

					String name = escapeHtml(restaurant.getName());
					String category = escapeHtml(restaurant.getCategory());
					String location = escapeHtml(restaurant.getLocation());
					String offer = escapeHtml(restaurant.getOffer());
					String imageUrl = escapeHtml(restaurant.getImageUrl());

					String deliveryTime = escapeHtml(restaurant.getDeliveryTime());

					String searchData = (name + " " + category + " " + location).toLowerCase();
				%>

				<a class="restaurant-card"
					href="<%=contextPath%>/menuItemServlet?restaurantID=<%=restaurant.getId()%>"
					data-search="<%=searchData%>"
					data-category="<%=category.toLowerCase()%>">

					<div class="image-wrap">

						<%
						if (imageUrl != null && !imageUrl.trim().isEmpty()) {
						%>

						<img src="<%=imageUrl%>" alt="<%=name%>" loading="lazy"
							onerror="this.style.display='none'; this.nextElementSibling.style.display='flex';">

						<div class="image-placeholder" style="display: none;">Image
							unavailable</div>

						<%
						} else {
						%>

						<div class="image-placeholder">Image unavailable</div>

						<%
						}
						%>

					</div>

					<div class="card-body">

						<div class="card-title-row">

							<h3><%=name%></h3>

							<span class="rating"> ★ <%=restaurant.getRating()%>
							</span>

						</div>

						<p class="cuisine">
							<%=category%>
						</p>

						<%
						if (offer != null && !offer.trim().isEmpty()) {
						%>

						<p class="restaurant-offer">
							<%=offer%>
						</p>

						<%
						}
						%>

						<div class="card-meta">

							<span> ◷ <%=deliveryTime%> min
							</span> <span> ₹<%=restaurant.getPriceForTwo()%> for two
							</span>

						</div>

						<p class="location">
							📍
							<%=location%>
						</p>

					</div>

				</a>

				<%
				}
				}
				%>

				<p class="empty-state" id="emptyState" style="display: none;">
					No restaurants found. Try another search or category.</p>

			</div>

		</section>

	</main>

	<!-- Footer -->
	<footer class="footer">

		<a class="brand footer-brand"
			href="<%=contextPath%>/RestaurantServlet"> foodie<span>.</span>
		</a>

		<p>Discover great food across Bengaluru.</p>

		<small> © 2026 Foodie. Demo website. </small>

	</footer>

	<!-- Search and Category Filtering -->
	<script>

        const searchInput = document.getElementById("searchInput");

        const filterButtons = document.querySelectorAll(".filter-chip");

        const restaurantCards = document.querySelectorAll(".restaurant-card");

        const emptyState = document.getElementById("emptyState");

        const resultCount = document.getElementById("resultCount");

        let activeFilter = "all";

        function filterRestaurants() {

            const searchText = searchInput.value.toLowerCase().trim();

            let visibleCount = 0;

            restaurantCards.forEach(card => {

                const searchData = card.dataset.search || "";

                const category = card.dataset.category || "";

                const matchesSearch = searchData.includes(searchText);

                const matchesCategory =
                    activeFilter === "all" ||
                    category.includes(activeFilter);

                const isVisible = matchesSearch && matchesCategory;

                card.style.display = isVisible ? "" : "none";

                if (isVisible) {
                    visibleCount++;
                }

            });

            resultCount.textContent =
                visibleCount + (visibleCount === 1
                    ? " restaurant"
                    : " restaurants");

            emptyState.style.display =
                visibleCount === 0 ? "block" : "none";
        }

        searchInput.addEventListener("input", filterRestaurants);

        filterButtons.forEach(button => {

            button.addEventListener("click", function() {

                filterButtons.forEach(btn =>
                    btn.classList.remove("active")
                );

                this.classList.add("active");

                activeFilter = this.dataset.filter;

                filterRestaurants();

            });

        });

    </script>

</body>
</html>
