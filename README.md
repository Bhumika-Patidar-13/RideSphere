🚗 RideSphere – Vehicle Booking and Driver Management System

RideSphere is a microservices-based vehicle booking and driver management application developed using Java and Spring Boot. It consists of independent services for managing customers, drivers, bookings, payments, trips, vehicles, feedback, and administration.

🚀 Key Features

- Customer Management
- Driver Management
- Vehicle Management
- Booking Management
- Payment Management
- Trip Management
- Feedback Management
- Admin Management
- Service Discovery using Netflix Eureka
- Inter-service Communication using OpenFeign

🛠️ Technology Stack

Category| Technologies
Programming Language| Java
Backend Framework| Spring Boot
Architecture| Microservices
Service Discovery| Netflix Eureka
Inter-service Communication| OpenFeign
API Development| REST APIs
Frontend| RideSphere Frontend

🏗️ Microservices Architecture

The application follows a microservices architecture in which different business functionalities are separated into independent services.

Services Overview

Service| Responsibility
Eureka Server| Service registration and discovery
Admin Service| Administration management
Customer Service| Customer-related operations
Driver Service| Driver-related operations
Booking Service| Vehicle booking management
Payment Service| Payment-related operations
Trip Service| Trip management
Vehicle Service| Vehicle management
Feedback Service| Customer feedback management

🔗 Service Communication

- Netflix Eureka Server: Used for service registration and discovery.
- OpenFeign Client: Used for communication between microservices.
- REST APIs: Used to expose backend functionalities.

📁 Project Structure

RideSphere/
│
├── Admin-Service/
├── Payment-service/
├── booking-service/
├── customer-service/
├── driver-service/
├── eureka-server/
├── feedback-service/
├── trip-service/
├── vehicle-service/
│
├── RideSphere-Frontend/
│
└── README.md

⚙️ How to Run the Project

1. Clone the repository.

git clone https://github.com/Bhumika-Patidar-13/RideSphere.git

2. Open the project in your preferred IDE.

3. Configure the required database settings in each service's "application.properties" file.

4. Start the Eureka Server.

5. Start the individual microservices.

6. Run the frontend application.

7. Access the application using its configured frontend URL.

🎯 Learning Outcomes

- Understanding Microservices Architecture.
- Implementing service discovery using Netflix Eureka.
- Understanding inter-service communication using OpenFeign.
- Developing RESTful APIs using Spring Boot.
- Managing independent backend services.

👩‍💻 Developer

Bhumika Patidar

Java Backend Developer | Spring Boot | Microservices

GitHub: https://github.com/Bhumika-Patidar-13

---

⭐ If you find this project interesting, feel free to explore the repository!
