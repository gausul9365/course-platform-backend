# Course Platform Backend API

## Overview
This project is a backend API for a simple learning platform, built as part of a backend intern assignment.

The platform allows users to:
- Browse courses and learning content (public access)
- Search courses and content using free-text queries
- Register and log in using JWT-based authentication
- Enroll in courses
- Track learning progress by marking subtopics as completed

The focus of this project is clean backend design, correct business logic, and making all features testable through Swagger. There is no frontend UI included.


## Tech Stack
- Java 17+
- Spring Boot
- Spring Data JPA (Hibernate)
- PostgreSQL
- Spring Security with JWT
- Swagger / OpenAPI


## How to Run Locally

### Prerequisites
Make sure the following are installed on your system:
- Java 17 or higher
- PostgreSQL
- Git

### Database Setup
1. Create a PostgreSQL database (any name is fine).
2. Note down:
    - database name
    - username
    - password

### Application Configuration
Update the database configuration in `src/main/resources/application.properties`:

#### properties
<pre>
    spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
    spring.datasource.username=your_db_username
    spring.datasource.password=your_db_password
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true

</pre>

### Running the Application

1. Clone the repository:
   ```bash
   git clone <your-repo-url>


2. Open the project in IntelliJ IDEA.

3. Make sure Maven dependencies are downloaded.

4. Run the main class:
   CoursePlatformApplication

5. The application will start on:
http://localhost:8080

## API & Swagger Usage

All APIs can be tested using Swagger UI.

Swagger URL:
http://localhost:8080/swagger-ui/index.html



### Public APIs (No Authentication Required)

These endpoints work without login and can be tested immediately:

- **GET /api/courses**  
  List all available courses.

- **GET /api/courses/{courseId}**  
  View a single course with topics and subtopics.

- **GET /api/search?q={query}**  
  Search courses and content using free-text queries.

---

### Authentication APIs

- **POST /api/auth/register**  
  Register a new user.

  Example request body:
  ```json
  {
    "email": "student@example.com",
    "password": "securePassword123"
  }

- **POST /api/auth/login**  
  Login and receive a JWT token.

  Example request body:
  ```json
  {
    "email": "student@example.com",
    "password": "securePassword123"
  }

After login, copy the JWT token and use the Authorize button in Swagger:
Bearer <your-jwt-token>


### Authenticated APIs (JWT Required)
These endpoints require a valid JWT token.

- **POST /api/courses/{courseId}/enroll**  
  Enroll the authenticated user in a course.
- **POST /api/subtopics/{subtopicId}/complete**
  Mark a subtopic as completed.
  This operation is idempotent.
- **GET /api/enrollments/{enrollmentId}/progress**
  View learning progress for a course enrollment, including:
    total subtopics
    completed subtopics
    completion percentage
    completed items with timestamps

### Authentication Flow

1. A user registers using the `/api/auth/register` endpoint.
2. The user logs in using `/api/auth/login` and receives a JWT token.
3. The JWT token must be provided in the `Authorization` header for all authenticated APIs.

Example header:
Authorization: Bearer <jwt-token>

- Public APIs such as course browsing and search do not require authentication.  
- Endpoints related to enrollment and progress tracking require a valid JWT token.


## Design Decisions

- Course, topic, and subtopic content is loaded from seed JSON data and treated as read-only, as required by the assignment.
- A simple PostgreSQL-based search using case-insensitive and partial matching was implemented to keep the solution clear and easy to understand.
- JWT-based authentication is used only for user-specific actions, while public content remains accessible without login.
- Business rules such as preventing duplicate enrollments and enforcing enrollment before progress tracking are handled at the service layer.
- Swagger is used as the primary way to test and review all APIs.

## How did I implement search?
I implemented a PostgreSQL-based free-text search using case-insensitive and partial matching
across course, topic, and subtopic content, focusing on clarity and correctness over optimization for this assignment.

## What I Would Improve With More Time

- Improve search relevance by using PostgreSQL full-text search and ranking results based on where the match occurs (title vs content).
- Add fuzzy matching to tolerate small spelling mistakes in search queries.
- Optimize progress and search queries to reduce in-memory filtering as data size grows.
- Add pagination for course listings and search results.
- Improve error handling by mapping all exceptions to clear and consistent HTTP responses.


Thank you for taking the time to review this project.

**Gausul Wara ** 
