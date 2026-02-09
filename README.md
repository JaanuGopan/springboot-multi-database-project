```
# 🎓 Spring Boot Multi-Database Project  
### Student Portal Backend (PostgreSQL + MongoDB)

This project is a learning-focused backend system built with Spring Boot to demonstrate how to use **multiple databases in a single application**. It combines a relational database (PostgreSQL) for structured data and MongoDB for activity logging and audit tracking.

The system models a simple **Student–Module Enrollment Portal** where students can be created, modules can be managed, and students can enroll in modules while all important actions are logged.

---

## 🚀 Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Spring Data MongoDB
- Lombok
- REST APIs
- Maven

---

## 🧠 Project Purpose

This project was created to practice and understand:

- Using **PostgreSQL** for relational data
- Using **MongoDB** for document-based logging
- Implementing **JPA relationships**
- Managing **cascade deletes**
- Designing clean **REST APIs**
- Applying **polyglot persistence** (SQL + NoSQL together)

---

## 🗂️ Architecture Overview

### PostgreSQL (Relational Data)
Stores core system data:

- Students
- Modules
- Module Enrollments

### MongoDB (NoSQL Logging)
Stores activity logs:

- Student creation/update/delete
- Module creation/update/delete
- Enrollment actions

---

## 📦 Entities

### Student
- id
- studentName
- studentEmail

### Module
- id
- moduleName
- moduleCode

### ModuleEnrollment
- id
- student (ManyToOne)
- module (ManyToOne)

A student can enroll in multiple modules.

---

## 🔗 Relationships

- One Student → Many Enrollments
- One Module → Many Enrollments
- Enrollment → Linked to Student & Module

Cascade rules:
- Deleting a Student removes related enrollments
- Deleting a Module removes related enrollments

---

## 📊 Activity Logging (MongoDB)

Every major action is logged:
- CREATE_STUDENT
- UPDATE_STUDENT
- DELETE_STUDENT
- CREATE_MODULE
- UPDATE_MODULE
- DELETE_MODULE
- CREATE_MODULE_ENROLLMENT

This demonstrates using MongoDB for:
- Audit trails
- Event tracking
- Non-relational data

---

## 🌐 REST API Endpoints

### Student APIs
```

POST   /students
GET    /students/{id}
PUT    /students/{id}
DELETE /students/{id}

```

### Module APIs
```

POST   /modules
GET    /modules/{id}
PUT    /modules/{id}
DELETE /modules/{id}

```

### Enrollment API
```

POST /enrollments/students/{studentId}/modules/{moduleId}

```

---

## ⚙️ Database Configuration

### PostgreSQL
- Database: `student_portal_db`
- Stores relational entities

### MongoDB
- Database: `student_portal_logs`
- Stores activity logs

---

## ▶️ Running the Project

### 1️⃣ Start PostgreSQL
Create database:
```

student_portal_db

```

### 2️⃣ Start MongoDB
Default port:
```

27017

```

### 3️⃣ Configure application.yml
```

spring:
datasource:
url: jdbc:postgresql://localhost:5432/student_portal_db
username: postgres
password: postgres

data:
mongodb:
uri: mongodb://localhost:27017/student_portal_logs

```

### 4️⃣ Run Spring Boot
```

mvn spring-boot:run

```

---

## 📚 Learning Highlights

This project demonstrates:

- Spring Data JPA relationships
- MongoDB document storage
- Repository pattern
- Service layer architecture
- Clean REST controller design
- Cascade delete handling
- Multi-database integration

---

## 🧪 Future Improvements

- Add authentication (Keycloak / JWT)
- Add DTO layer
- Add pagination
- Add validation annotations
- Add Swagger documentation
- Convert to microservices

---

## 👨‍💻 Author

**Janu Gopan**  
Computer Engineering Undergraduate  
University of Ruhuna

GitHub:  
https://github.com/JaanuGopan

---

## ⭐ Project Goal

This repository is part of my backend learning journey to strengthen knowledge in:

- Spring Boot development
- SQL vs NoSQL design
- Real-world system modeling
- Clean architecture practices
```

---
