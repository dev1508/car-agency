# Spring Boot REST API Project with MongoDB

Welcome! This project is a RESTful API built using the **Spring Boot** framework and **MongoDB** as the database. It showcases the implementation of various backend functionalities, including a robust error-handling mechanism not covered in the original tutorial video I followed.

This project was developed as part of learning and improving my backend development skills. You can find the tutorial I used as a base [here](https://www.youtube.com/watch?v=5PdEmeopJVQ), but I have expanded on it by adding additional features such as custom error handling.

## Technologies Used

- **Spring Boot** (Backend Framework)
- **MongoDB** (NoSQL Database)
- **Git** (Version Control)

## Tools Required

To run and test the project, you will need the following tools:

- **IntelliJ IDEA** (Integrated Development Environment)
- **MongoDB Compass** (Database GUI for managing MongoDB collections)
- **Postman** (For API testing)

## Getting Started

### Prerequisites

Ensure you have the following installed on your local system:

- [Java JDK 8 or later](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [MongoDB](https://www.mongodb.com/try/download/community)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)
- [Postman](https://www.postman.com/downloads/)

### Installation Steps

1. **Clone the repository** to your local machine:
   ```bash
   git clone https://github.com/dev1508/car-agency.git
   cd repo-name
   ```

2. Create a file named `.env` in the `src/main/resources` directory with the following structure:
   ```bash
   MONGO_DATABASE=your-database-name
   MONGO_USER=your-username
   MONGO_PASSWORD=your-password
   MONGO_CLUSTER=your-cluster-id
   ```

3. **Run the application**:
    - Navigate to `src/main/java/com/example/agency/AgencyApplication.java` and run the `AgencyApplication` class.
    - Alternatively, use the terminal:
      ```bash
      ./mvnw spring-boot:run
      ```

4. If port `8080` is in use, modify the port by adding the following line in the `application.properties` file:
   ```bash
   server.port=your-port-number
   ```

5. Use **Postman** to test the available APIs while the application is running.

### MongoDB Setup

To connect to your MongoDB database, you will need to create a new database and cluster. Once set up, MongoDB will provide a connection string that looks like:

```bash
mongodb+srv://username:password@cluster-id.mongodb.net/database-name
```

Ensure this information is correctly entered into your `.env` file as shown above.

## API Testing

Use **Postman** to test the various endpoints provided by this API. For more detailed information on available endpoints, check the `src/main/java/com/example/agency/controller` package.

## Additional Features

- **Error Handling**: This project includes custom error-handling mechanisms not covered in the original tutorial.
- **.env Configuration**: Easily manage environment variables such as database credentials and cluster ID.

## Connect with Me

If you have any questions or would like to discuss this project, feel free to connect with me on [LinkedIn](https://www.linkedin.com/in/dev-vrat-pathak-aa6570176/ "Let's Connect").

---

Thank you for taking the time to check out this project. Your feedback is greatly appreciated!