# Easy Catalog

This is a monorepo project containing a backend (Spring Boot) and a frontend (Angular) application for managing a product catalog called Easy Catalog.

## Backend (easy-catalog-backend)

### Overview

The backend application is built using Spring Boot and provides REST APIs for managing products and categories. It uses an H2 in-memory database for development and testing.

### Technologies Used

*   Spring Boot 3.x
*   Spring Data JPA
*   Spring Security
*   Spring Web
*   Lombok
*   H2 Database
*   JWT (JSON Web Token)
*   Jakarta XML Binding (JAXB)

### Controllers

*   **ProductController:**
    *   `GET /api/products`: Retrieves all products.
    *   `GET /api/products/{id}`: Retrieves a product by ID.
    *   `POST /api/products`: Creates a new product.
    *   `PUT /api/products/{id}`: Updates a product.
    *   `DELETE /api/products/{id}`: Deletes a product.

*   **CategoryController:**
    *   `GET /api/categories`: Retrieves all categories.

### Running the Backend

1.  Navigate to the `easy-catalog-backend` directory.
2.  Run the application using Maven: `mvn spring-boot:run`

### Security

The backend uses JWT for authentication.  You'll need to implement the login functionality to generate and manage JWTs.  The `validateToken` method in your `AuthService` (as discussed in our previous exchanges) handles the token validation.

## Frontend (easy-catalog-front)

### Overview

The frontend application is built using Angular and provides a user interface for browsing and filtering products.

### Technologies Used

*   Angular 18.x
*   RxJS

### Functionalities

*   **Product CRUD:** Create, update and delete products.
*   **Filtering:** Allows filtering products by:
    *   Description
    *   Category
    *   Availability (presumably based on a field in your product data)

### Running the Frontend

1.  Navigate to the `easy-catalog-front` directory.
2.  Install dependencies: `npm install`
3.  Run the application: `ng serve`

---

Anderson Farias.