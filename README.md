# Spring Security Medium Demo (Spring Boot 3)

## Description
This project is a **Spring Boot 3** demo showcasing how to implement **basic security with role-based access control** and **method-level security** using modern **Spring Security** practices. It provides simple authentication with two roles: **USER** and **ADMIN**.

---

## How to Run the Project

1. **Clone the project or copy the code into your local environment.**

2. **Build and run the project:**

   - If you're using **Maven**, run the following command:
     ```bash
     mvn spring-boot:run
     ```
   - Alternatively, execute the `SecurityDemoApplication.java` class from your IDE (e.g., IntelliJ).

3. **Access the endpoints** in your browser at `http://localhost:8080`.

---

## Available Endpoints

| Endpoint     | Description                      | Authentication Required | Role Required |
|--------------|----------------------------------|-------------------------|---------------|
| `/`          | Public page                      | No                      | None          |
| `/user`      | User-only page                   | Yes                     | USER          |
| `/admin`     | Admin-only page                  | Yes                     | ADMIN         |
| `/delete`    | Admin-only method-level security | Yes                     | ADMIN         |

---

## Users and Credentials

The application has two pre-configured users for testing:

| Username | Password   | Role  |
|----------|------------|-------|
| `user`   | `password` | USER  |
| `admin`  | `adminpass` | ADMIN |

---

## Testing Instructions

1. **Access the public endpoint (`/`)**:
   - No authentication is required.

2. **Log in as a User:**
   - Visit the `/user` endpoint.
   - Use the following credentials:
     ```
     Username: user
     Password: password
     ```
   - You should see the message: `"Welcome, user!"`.

3. **Log in as an Admin:**
   - Visit the `/admin` endpoint.
   - Use the following credentials:
     ```
     Username: admin
     Password: adminpass
     ```
   - You should see the message: `"Welcome, admin!"`.

4. **Test Admin-Only Method-Level Security (`/delete`)**:
   - This endpoint is secured using method-level security with `@PreAuthorize("hasRole('ADMIN')")`.
   - Only the admin can access this endpoint.

---

## ⚠️ Important Notes

- **Role-Based Access Testing:**  
  If you log in with one role (e.g., `user`), your session will remain active for all endpoints.  
  To test another role (e.g., `admin`), **use an incognito/private browsing window** or log out before logging in with different credentials.

- **Password Encryption:**  
  The application uses **BCrypt** for password hashing, ensuring secure password storage.

---

## Security Configuration Overview

The security configuration is implemented using **`SecurityFilterChain`** and an in-memory user store:

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/", "/public").permitAll()
            .requestMatchers("/admin").hasRole("ADMIN")
            .anyRequest().authenticated()
        )
        .httpBasic(withDefaults());
    return http.build();
}
