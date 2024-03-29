# Project 1
A Java 8 backend web API and ES6+ HTML/JS web interface with a PostgreSQL database.

# Project Description
### User stories
- An Employee...
    - [x] can login
    - [x] can view the Employee Homepage
    - [x] can logout
    - [x] can submit a reimbursement request
    - [] can upload an image of his/her receipt as part of the reimbursement request
    - [x] can view their pending reimbursement requests
    - [x] can view their resolved reimbursement requests
    - [x] can view their information
    - [x] can update their information

- A Manager...
    - [x] can login
    - [x] can view the Manager Homepage
    - [x] can logout
    - [x] can approve/deny pending reimbursement requests
    - [x] can view all pending requests from all employees
    - [] can view images of the receipts from reimbursement requests
    - [x] can view all requests from all employees
        - [x]see which manager resolved it
    - [x] can view all Employees
    - [x] can view their information
    - [x] can update their information

# Tools & APIs
- [x] User Stories
- [x] Java SE 8
- [x] Gradle
- [x] JDBC
- [x] PostgreSQL
- [x] JavaEE Servlet
- [x] HTML/JS/CSS
- [x] AJAX/Fetch
- [x] JUnit
- [x] log4j or similar
- [x] Jest or similar JS testing framework
- [x] Docker, Docker-Compose
- [x] Mockito

### Architecture
- [x] Anemic/DDD OR n-tier package & class structure
- [x] Design Patterns:
  - [] Dependency Injection
  - [x] Data Access Object
  - [] Business Delegate
  - [x] Model-View-Controller
  - [] Front Controller
- [x] SQL Normalization (3rd form)
- [x] PL/pgSQL
- [] Optional:
  - [] Single Page Application

### Functionality
- [x] CRUD - Create, Read, Update, Delete
- [x] Web App dashboard interface
- [] Asynchronous interface updates
- [x] Login - Authentication & Authorization
- [x] Database persistance
- [x] Session management

# Getting Started
- install Docker and Gradle

### Docker
To Run:
```bash
docker-compose --file docker/compose.yaml up --build -d
```
To Stop:
```bash
docker-compose --file docker/compose.yaml down
```
Add "winpty" to front of command if on Windows

### To enter DB
```bash
docker exec -it db psql -U hiworld
\c postgres
```
