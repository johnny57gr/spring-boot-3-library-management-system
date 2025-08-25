# Library Management System (Spring Boot 3)

A simple **CRUD web application** built with Spring Boot 3.  
This project focuses on basic Create, Read, Update, and Delete (CRUD) operations for books, members, and loans in a library.

 **Note:** This project does **not** include authentication, user roles, or admin features.  
It is designed as a learning exercise to practice Spring Boot, JPA, and web development basics.

---

## Live Demo

🔗 [View the app on Render](https://spring-boot-3-library-management-system.onrender.com/)

---

## Features (CRUD)

- **Books** → add, update, delete, view
- **Members** → register, edit, remove, view
- **Loans** → issue and return books
- View data via a simple **Thymeleaf web interface**
-  Layered backend architecture (Controller → Service → Repository)

---

## Tech Stack

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA (Hibernate)**
- **Thymeleaf**
- **PostgreSQL** (production) / **H2** (local development)
- **Maven**
- **HTML / CSS**

---

## Installation (local)

1. Clone the repository:
   ```bash
   git clone https://github.com/johnny57gr/librarymanagement.git
   cd librarymanagement

2. Configure your database (PostgreSQL or H2).
   Update application.properties or set environment variables:
    ```bash
    SPRING_DATASOURCE_URL=jdbc:postgresql://<host>:5432/<dbname>?sslmode=require
    SPRING_DATASOURCE_USERNAME=<username>
    SPRING_DATASOURCE_PASSWORD=<password>

3. Run the project:
    ```bash
    ./mvnw spring-boot:run


4. Then open:   
    http://localhost:8080

## Project Structure

<pre>
📦 src
 ┣ 📂 main
 ┃ ┣ 📂 java
 ┃ ┃ └── 📂 com
 ┃ ┃     └── 📂 ypassas
 ┃ ┃         └── 📂 springboot
 ┃ ┃             └── 📂 librarymanagement
 ┃ ┃                 ├── 📂 controller
 ┃ ┃                 ├── 📂 model   
 ┃ ┃                 ├── 📂 repository   
 ┃ ┃                 ├── 📂 service        
 ┃ ┃                 └── 📄 LibrarymanagementApplication.java
 ┃ ┣ 📂 resources
 ┃ ┃ ├── 📂 static      # CSS, JS, images 
 ┃ ┃ ├── 📂 templates   # Thymeleaf templates (HTML pages)
 ┃ ┃ └── 📄 application.properties
 README.md
</pre>

## Author
- **Created by Ioannis Passas**
- **<a href="mailto:yiannispas86@gmail.com">yiannispas86@gmail.com</a>**
- **<a href="https://www.linkedin.com/in/yiannis-passas-dev/" target="_blank">Linkedin</a>**

## Notes
This project was created as a learning exercise to practice:

- **Spring Boot fundamentals**
- **CRUD operations with JPA/Hibernate**
- **MVC architecture (Controllers, Services, Repositories)**
- **Server-side rendering with Thymeleaf**
