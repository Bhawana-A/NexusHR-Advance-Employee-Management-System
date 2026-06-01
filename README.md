<div align="center">

# 🚀 NexusHR - Advance Employee Management System

### Enterprise Level HR Management Backend using Spring Boot

<br>

<a href="https://github.com/Bhawana-A/NexusHR-Advance-Employee-Management-System">
<img src="https://img.shields.io/badge/GitHub-Repository-black?style=for-the-badge&logo=github">
</a>

<a href="https://nexushr-advance-employee-management-1u4v.onrender.com/swagger-ui/index.html">
<img src="https://img.shields.io/badge/Swagger-API_Documentation-85EA2D?style=for-the-badge&logo=swagger&logoColor=black">
</a>

<a href="https://nexushr-advance-employee-management-1u4v.onrender.com">
<img src="https://img.shields.io/badge/Render-Live_Deployment-46E3B7?style=for-the-badge&logo=render&logoColor=black">
</a>

</div>

---

# 🛠 Technologies Used

<p align="center">

<img src="https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk">
<img src="https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=springboot">
<img src="https://img.shields.io/badge/MySQL-Database-4479A1?style=for-the-badge&logo=mysql">
<img src="https://img.shields.io/badge/Hibernate-ORM-59666C?style=for-the-badge&logo=hibernate">
<img src="https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven">
<img src="https://img.shields.io/badge/JPA-Persistence-59666C?style=for-the-badge">
<img src="https://img.shields.io/badge/Lombok-Annotation-red?style=for-the-badge">
<img src="https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?style=for-the-badge&logo=swagger">
<img src="https://img.shields.io/badge/Render-Deployment-46E3B7?style=for-the-badge&logo=render">

</p>

---

# 📌 Project Overview

NexusHR is an advanced Employee Management System developed using Spring Boot and MySQL.

This system provides a complete HR management solution for organizations.

### Key Modules

 Employee Management

 Department Management

 Project Management

 Employee Profiles

 Leave Management

 Analytics & Reporting

 Soft Delete Implementation

 Pagination & Sorting

 Validation Handling

 Global Exception Handling

---

# 👩‍💻 Author

### Bhawana Ahirwar

Backend Java Developer

---

# 🔗 Project Links

## GitHub Repository

https://github.com/Bhawana-A/NexusHR-Advance-Employee-Management-System

## Swagger API Documentation

https://nexushr-advance-employee-management-1u4v.onrender.com/swagger-ui/index.html

## Live Deployment

https://nexushr-advance-employee-management-1u4v.onrender.com

---

# 🏗️ System Architecture

## Entity Relationships

* Department → Employees
* Employee → Profile
* Employee → Leave Requests
* Employee ↔ Projects (Many-To-Many)

---

# 📂 Database ER Diagram

![ER Diagram](images/er-diagram.png)

---

# 📸 Project Screenshots

## Swagger Dashboard

![Swagger Dashboard](images/swagger-dashboard.png)

---

## Employee APIs

![Employee APIs](images/employee-api.png)

---

## Department APIs

![Department APIs](images/department-api.png)

---

## Project APIs

![Project APIs](images/project-api.png)

---

## Leave APIs

![Leave APIs](images/leave-api.png)

---

# 🚀 API Endpoints

## Employee APIs

| Method | Endpoint                        |
| ------ | ------------------------------- |
| POST   | /api/v1/employees               |
| GET    | /api/v1/employees               |
| GET    | /api/v1/employees/{id}          |
| PUT    | /api/v1/employees/{id}          |
| DELETE | /api/v1/employees/{id}          |
| PATCH  | /api/v1/employees/{id}/transfer |
| PATCH  | /api/v1/employees/{id}/promote  |

---

## Department APIs

| Method | Endpoint                       |
| ------ | ------------------------------ |
| POST   | /api/v1/departments            |
| GET    | /api/v1/departments            |
| GET    | /api/v1/departments/{id}       |
| PUT    | /api/v1/departments/{id}       |
| DELETE | /api/v1/departments/{id}       |
| PUT    | /api/v1/departments/{id}/raise |
| GET    | /api/v1/departments/{id}/stats |

---

## Employee Profile APIs

| Method | Endpoint                                |
| ------ | --------------------------------------- |
| POST   | /api/v1/employee-profiles               |
| GET    | /api/v1/employee-profiles/employee/{id} |
| PUT    | /api/v1/employee-profiles/employee/{id} |
| DELETE | /api/v1/employee-profiles/employee/{id} |

---

## Leave APIs

| Method | Endpoint                                     |
| ------ | -------------------------------------------- |
| POST   | /api/v1/leaves/request                       |
| PUT    | /api/v1/leaves/{id}/status                   |
| GET    | /api/v1/leaves/employee/{employeeId}         |
| GET    | /api/v1/leaves/employee/{employeeId}/balance |

---

## Project APIs

| Method | Endpoint                                            |
| ------ | --------------------------------------------------- |
| POST   | /api/v1/projects                                    |
| GET    | /api/v1/projects                                    |
| GET    | /api/v1/projects/{id}                               |
| PUT    | /api/v1/projects/{id}                               |
| PATCH  | /api/v1/projects/{id}/status                        |
| POST   | /api/v1/projects/{projectId}/assign                 |
| DELETE | /api/v1/projects/{projectId}/employees/{employeeId} |

---

# ✨ Features

### Employee Management

* Employee Onboarding
* Employee Promotion
* Employee Transfer
* Soft Delete

### Department Management

* Department Creation
* Budget Management
* Salary Raise
* Department Analytics

### Leave Management

* Leave Requests
* Leave Approval
* Leave History
* Leave Balance Calculation

### Project Management

* Project Creation
* Team Assignment
* Project Timeline
* Status Tracking

---

# 📁 Project Structure

src

├── controller

├── dto

├── entity

├── enums

├── exception

├── mapper

├── repository

├── service

├── serviceImpl

└── config

---

# 📈 Future Enhancements

* JWT Authentication
* Spring Security
* Role Based Access Control
* Attendance Module
* Payroll System
* Email Notifications
* Dashboard Analytics

---

# ⭐ Support

If you like this project, don't forget to give it a Star ⭐ on GitHub.

---

<div align="center">

### 🚀 © 2026 Bhawana Ahirwar | NexusHR Employee Management System

</div>
