# Spring Security OAuth2 Authorization Server Configuration

This repository contains a complete programmatic configuration for a standalone **Authorization Server** built using Spring Boot, Spring Security 6.x, and the Spring Authorization Server framework. It implements OAuth2 and OpenID Connect (OIDC) protocols to securely issue tokens and manage clients.

---

## 🛠️ Technology Stack
* **Java 17+**
* **Spring Security 6.x**
* **Spring Authorization Server**
* **OAuth2 & OpenID Connect (OIDC)**
* **JSON Web Tokens (JWT) / JWK**

---

## 🔑 Key Components Explained

### 1. Security Filter Chains
The configuration defines two filter chains ordered by priority (`@Order`):
* **`authorizationServerSecurityFilterChain` (Order 1):** Dedicated to handling OAuth2/OIDC protocol endpoints (e.g., token issuance, authorization, user info). It enables OIDC support by default and redirects unauthenticated clients to the `/login` page.
* **`appSecurityFilterChain` (Order 2):** Secures all remaining application endpoints. It requires full authentication for any other request and enables the default Spring Security form-based login interface.

### 2. User Authentication (UserDetailsService)
A sample user is registered in-memory for testing and local development:
* **Username:** `user`
* **Password:** `password` (Secured using **BCrypt** password hashing)
* **Authorities/Roles:** `read`

### 3. Client Registration Repository
An OAuth2 Client App is configured in-memory to test token exchange flows:
* **Client ID:** `client`
* **Client Secret:** `secret` (Stored securely using BCrypt)
* **Scopes:** `openid`, `profile`
* **Authentication Method:** `CLIENT_SECRET_BASIC`
* **Authorization Grant Types:** `AUTHORIZATION_CODE` and `REFRESH_TOKEN`
* **Redirect URI:** `http://springone.io/authorized`

### 4. Cryptographic Key Management (JWK Source)
The `jwkSource` bean automatically generates an **RSA key pair (2048-bit)** dynamically at application startup. These cryptographic keys are packed into a JSON Web Key (JWK) set and are strictly used to sign the outgoing JWT Access and ID tokens to prevent tampering.

---

## 🚀 Standard Authorization Code Flow

1. **Authorization Request:** The client application redirects the user to the Authorization Server's authorization endpoint.
2. **User Login:** The user is prompted with a login form and enters credentials (`user` / `password`).
3. **Authorization Code:** Upon successful authentication, the server redirects back to the registered Redirect URI with an authorization `code`.
4. **Token Exchange:** The client application exchanges this `code` alongside its `Client Secret` to safely obtain the **Access Token**, **Refresh Token**, and **ID Token (JWT)**.

---

## ⚠️ Important Production Considerations

* **In-Memory Storage:** Both users (`InMemoryUserDetailsManager`) and clients (`InMemoryRegisteredClientRepository`) are saved in volatile memory. For a production environment, you must switch to persistent storage such as database-backed repositories (JDBC).
* **Ephemeral Cryptographic Keys:** Keys are generated dynamically on startup. If the server restarts, a new key pair will be generated, which immediately invalidates all previously issued tokens. For production, keys must be loaded from a permanent, secure external `KeyStore`.