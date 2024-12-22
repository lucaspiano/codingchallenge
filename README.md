# codingchallenge
Blog Post API for the Prosiglieri coding challenge

# Blog API

This is a simple Blog API built with Spring Boot and H2 in-memory database. The API allows you to create blog posts, add comments to them, and retrieve posts and their associated comments.

---

## Prerequisites

- Java 17 or higher
- Maven 3.8 or higher
- A tool like Postman or cURL for testing the API

---

## How to Run the Application

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd <repository-folder>
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```
   Or execute the JAR file directly if it was built:
   ```bash
   java -jar target/blog-api-1.0.0.jar
   ```

4. **Access the application**
   - The application will run on `http://localhost:8080` by default.

5. **Access the H2 Console (Optional)**
   - URL: `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:testdb`
   - User: `sa`
   - Password: (leave blank)

---

## API Endpoints

### 1. **Create a Blog Post**
- **Endpoint:** `/api/posts`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "title": "My First Post",
    "content": "This is the content of my first post."
  }
  ```
- **Response Example (201 Created):**
  ```json
  {
    "id": 1,
    "title": "My First Post",
    "content": "This is the content of my first post.",
    "comments": null
  }
  ```

### 2. **Add a Comment to a Blog Post**
- **Endpoint:** `/api/posts/{postId}/comments`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "content": "Nice post!",
    "author": "User123"
  }
  ```
- **Response Example (201 Created):**
  ```json
  {
    "id": 1,
    "content": "Nice post!",
    "author": "User123",
    "blogPostId": 1
  }
  ```

### 3. **List All Blog Posts**
- **Endpoint:** `/api/posts`
- **Method:** `GET`
- **Response Example (200 OK):**
  ```json
  [
    {
      "id": 1,
      "title": "My First Post",
      "content": null,
      "comments": null
    },
    {
      "id": 2,
      "title": "Another Post",
      "content": null,
      "comments": null
    }
  ]
  ```

### 4. **Get a Blog Post by ID**
- **Endpoint:** `/api/posts/{id}`
- **Method:** `GET`
- **Response Example (200 OK):**
  ```json
  {
    "id": 1,
    "title": "My First Post",
    "content": "This is the content of my first post.",
    "comments": null
  }
  ```

---

## Database Schema

### BlogPost Table
- **Columns:**
  - `id` (Primary Key)
  - `title` (String)
  - `content` (Text)

### Comment Table
- **Columns:**
  - `id` (Primary Key)
  - `content` (Text)
  - `author` (String)
  - `blog_post_id` (Foreign Key to BlogPost)

---

## Initial Data

The application will populate the H2 database with the following initial data:

### Blog Posts
| ID  | Title           | Content               |
|-----|-----------------|-----------------------|
| 1   | First Post      | Content of first post|
| 2   | Second Post     | Content of second post|

### Comments
| ID  | Content        | Author   | Blog Post ID |
|-----|----------------|----------|--------------|
| 1   | Great post!    | Alice    | 1            |
| 2   | Thanks for sharing! | Bob | 2            |

---

## Testing with Postman

1. Import the following endpoints into Postman:
   - `POST /api/posts`
   - `POST /api/posts/{postId}/comments`
   - `GET /api/posts`
   - `GET /api/posts/{id}`

2. Use the example requests and responses provided above to interact with the API.

---

## Troubleshooting

- **Port Conflicts:** If the default port 8080 is in use, modify the `application.properties` file to set a new port:
  ```properties
  server.port=9090
  ```
- **Error Connecting to H2 Console:** Ensure the application is running and the JDBC URL, username, and password are correct.

---

Enjoy exploring the Blog API!

