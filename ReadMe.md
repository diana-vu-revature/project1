# Project 1
A Java 8 backend web API and ES6+ HTML/JS web interface with a PostgreSQL database.

### Docker
To Run:
```bash
docker-compose --file docker/compose.yaml up --build -d
```
To Stop:
```bash
docker-compose --file docker/compose.yaml down
```

### Tools & APIs
- [x] User Stories
- [x] Java SE 8
- [x] Gradle
- [x] JDBC
- [x] PostgreSQL
- [x] JavaEE Servlet
- [x] HTML/JS/CSS
- [] AJAX/Fetch
- [x] JUnit
- [x] log4j or similar
- [x] Jest or similar JS testing framework
- [] Optional:
    - [x] Docker, Docker-Compose
    - [] Jenkins CI automation
    - [] Mockito

### Architecture
- [] Anemic/DDD OR n-tier package & class structure
- [] Design Patterns:
  - [] Dependency Injection
  - [] Data Access Object
  - [] Business Delegate
  - [] Model-View-Controller
  - [] Front Controller
- [] SQL Normalization (3rd form)
- [] PL/pgSQL
- [] Optional:
  - [] Single Page Application

### Functionality
- [x] CRUD - Create, Read, Update, Delete
- [x] Web App dashboard interface
- [] Asynchronous interface updates
- [x] Login - Authentication & Authorization
- [x] Database persistance
- [x] Session management

### User stories
- An Employee...
    - [] can login
    - [] can view the Employee Homepage
    - [] can logout
    - [] can submit a reimbursement request
    - [] can upload an image of his/her receipt as part of the reimbursement request
    - [] can view their pending reimbursement requests
    - [] can view their resolved reimbursement requests
    - [] can view their information
    - [] can update their information
    - [] receives an email when one of their reimbursement requests is resolved (optional)

- A Manager...
    - [] can login
    - [] can view the Manager Homepage
    - [] can logout
    - [] can approve/deny pending reimbursement requests
    - [] can view all pending requests from all employees
    - [] can view images of the receipts from reimbursement requests
    - [] can view all resolved requests from all employees and see which manager resolved it
    - [] can view all Employees
    - [] can view reimbursement requests from a single Employee 