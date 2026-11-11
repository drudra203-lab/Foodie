🍕 FoodApp — Full Stack Food Delivery Application
A Swiggy/Zomato-style food delivery web application built with Java EE.

Tech Stack
Backend — Java Servlets, JDBC, JSP
Frontend — HTML, CSS
Database — MySQL
Server — Apache Tomcat
Security — BCrypt password hashing
Features
🏠 Restaurant listing page
🍽 Dynamic menu per restaurant
🛒 Session-based cart system
👤 User login & signup with BCrypt
📦 Order placement & history
🛡 Admin role support
How to Run
Import project into Eclipse
Setup MySQL database — run SQL scripts
Add MySQL & jBCrypt JARs to WEB-INF/lib
Deploy on Apache Tomcat
Visit http://localhost:8080/FoodApp/

Foodie/
│
├── src/main/java/
│   ├── com/foodie/dao/          # DAO Interfaces
│   ├── com/foodie/daoImple/     # DAO Implementation classes (JDBC)
│   ├── com/foodie/dbconnection/ # Database Connection utility
│   ├── com/foodie/model/        # Entity/Model classes (User, Restaurant, MenuItem, Cart, Order, etc.)
│   └── com/foodie/servlet/      # Controllers (Servlets for Login, Cart, Checkout, etc.)
│
├── src/main/webapp/
│   ├── WEB-INF/
│   │   ├── lib/                 # External JARs (MySQL Connector, JBCrypt)
│   │   └── web.xml              # Deployment Descriptor
│   ├── css/                     # Stylesheets for pages
│   ├── images/                  # Restaurant and Menu assets
│   ├── Cart.jsp                 # Cart management view
│   ├── CheckOut.jsp             # Checkout view
│   ├── Login.jsp                # User login view
│   ├── MenuItem.jsp             # Restaurant menu view
│   ├── Restaurant.jsp           # Restaurant listing view
│   └── Signup.jsp               # User registration view
│
├── .gitignore
└── README.md
