# 📝 Blog REST API

A RESTful Blog API built from scratch with **Spring Boot**, where users can create posts and posts can have comments. Built as a hands-on project to master Spring Boot fundamentals — layered architecture, JPA relationships, DTO design, validation, and global exception handling — without following step-by-step tutorials.

> **Status:** ✅ Core API complete (Pass 1) · 🔐 Spring Security + JWT coming next (Pass 2)

---

## 🚀 Features

- **Full CRUD** for Users, Posts, and Comments
- **Layered architecture** — Controller → Service → Repository
- **Entity relationships** — `User 1—∞ Post 1—∞ Comment` (bidirectional `@OneToMany` / `@ManyToOne`)
- **DTO pattern** — request and response DTOs keep entities out of the API (no infinite recursion, no data leaks)
- **Input validation** — `@Valid` with `@NotBlank`, `@Email`, `@Size`
- **Global exception handling** — `@RestControllerAdvice` returns clean `404` / `400` responses, never raw stack traces
- **Proper HTTP status codes** — `201 Created`, `200 OK`, `204 No Content`, `400`, `404`

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Java 17+ |
| Framework | Spring Boot 3 |
| Data | Spring Data JPA (Hibernate) |
| Database | PostgreSQL |
| Mapping | ModelMapper |
| Boilerplate | Lombok |
| Build | Maven |

---

## 🏗️ Architecture

```
Client (Postman)
      │  HTTP request + JSON
      ▼
┌─────────────────────┐
│   Controller        │  @RestController · maps URLs · @Valid · ResponseEntity
└─────────┬───────────┘
          ▼
┌─────────────────────┐
│   Service           │  business logic · Entity ⇄ DTO · @Transactional
└─────────┬───────────┘
          ▼
┌─────────────────────┐
│   Repository        │  JpaRepository · save/findById/findAll/delete
└─────────┬───────────┘
          ▼
┌─────────────────────┐
│   PostgreSQL        │  users · posts · comments
└─────────────────────┘

Cross-cutting:  DTOs · Validation (@Valid) · @RestControllerAdvice
```

---

## 📁 Project Structure

```
src/main/java/com/practice/Blog/API
├── config/                # ModelMapper bean
├── controller/            # REST controllers (User, Post, Comment)
├── DTO/                   # Response DTOs + CreateANew/ request DTOs
├── entity/                # JPA entities (User, Post, Comment)
├── exceptions/            # ResourceNotFoundException + GlobalExceptionHandler
├── repository/            # Spring Data JPA repositories
└── services/             # Service interfaces + implementation/
```

---

## ⚙️ Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.8+
- PostgreSQL running locally

### 1. Clone the repository

```bash
git clone https://github.com/<your-username>/blog-api.git
cd blog-api
```

### 2. Create the database

```sql
CREATE DATABASE "BlogDB";
```

### 3. Configure `src/main/resources/application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/BlogDB
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

server.servlet.context-path=/api

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> 💡 Tip: keep real credentials out of git — use environment variables or a local `application-dev.properties` that is `.gitignore`d.

### 4. Run the application

```bash
./mvnw spring-boot:run
```

The API starts at `http://localhost:8080/api`

---

## 📚 API Endpoints

> Base path: `/api`

### 👤 Users

| Method | Endpoint | Description | Status |
|--------|----------|-------------|--------|
| `POST` | `/users` | Create a user | `201` |
| `GET` | `/users` | List all users | `200` |
| `GET` | `/users/{id}` | Get one user | `200` |
| `PUT` | `/users/{id}` | Update a user | `200` |
| `DELETE` | `/users/{id}` | Delete a user | `204` |

### 📄 Posts

| Method | Endpoint | Description | Status |
|--------|----------|-------------|--------|
| `POST` | `/users/{userId}/posts` | Create a post for a user | `201` |
| `GET` | `/posts` | List all posts | `200` |
| `GET` | `/posts/{id}` | Get a post (with its comments) | `200` |
| `GET` | `/users/{id}/posts` | All posts by a user | `200` |
| `PUT` | `/posts/{id}` | Update a post | `200` |
| `DELETE` | `/posts/{id}` | Delete a post | `204` |

### 💬 Comments

| Method | Endpoint | Description | Status |
|--------|----------|-------------|--------|
| `POST` | `/posts/{postId}/comments` | Add a comment to a post | `201` |
| `GET` | `/posts/{postId}/comments` | List comments on a post | `200` |
| `DELETE` | `/comments/{id}` | Delete a comment | `204` |

---

## 🧪 Example Requests

**Create a user**

```http
POST /api/users
Content-Type: application/json

{
  "name": "Ada Lovelace",
  "email": "ada@example.com",
  "bio": "First programmer"
}
```

**Create a post for that user**

```http
POST /api/users/1/posts
Content-Type: application/json

{
  "title": "My first post",
  "content": "Hello world from Spring Boot!"
}
```

**Validation error response (`400 Bad Request`)**

```json
{
  "title": "must not be blank"
}
```

**Not found response (`404 Not Found`)**

```text
Post not found with id:99
```

---

## 🎯 What I Learned

- **DTO design** — why request and response DTOs differ, and why entities should never leak into the API
- **Bidirectional relationships** — solving the infinite JSON recursion by making DTOs one-directional
- **Validation** — `@NotBlank` vs `@Size`/`@Email` (the latter pass on `null`!)
- **Exception handling** — centralizing error responses with `@RestControllerAdvice`
- **Input hygiene** — request DTOs should only contain what the client is allowed to send

---

## 🗺️ Roadmap

- [x] CRUD for Users, Posts, Comments
- [x] Entity relationships + DTO mapping
- [x] Validation + global exception handling
- [ ] 🔐 Spring Security + JWT authentication
- [ ] Role-based authorization ("only the author can edit their post")
- [ ] Pagination, sorting & search
- [ ] Unit & integration tests (JUnit + Mockito)
- [ ] Dockerize + deploy

---

## 🤝 Contributing

This is a personal learning project, but feedback and suggestions are very welcome — feel free to open an issue.

---

## 📄 License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.

---

> Built with ☕ and curiosity while learning Spring Boot. *Break it, fix it, repeat.*
