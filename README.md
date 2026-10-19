# ChatApp – Formative 2 Part 2

## Overview

ChatApp is a Java-based messaging application developed in NetBeans for the PROG5121 Programming 1A module. This project builds on the first part by adding messaging functionality, message validation, and options to send, discard, and store messages.

The application demonstrates Java programming concepts, object-oriented programming, input validation, file handling, and unit testing.

## Features

### 1. User Registration and Login
- Register a user using the required details.
- Validate usernames and passwords according to the implemented rules.
- Check login details and provide appropriate feedback.

### 2. Messaging Functionality
- Create and validate messages.
- Generate message IDs and message hashes.
- Enter recipient details and message content.
- Send messages, discard messages, or store messages for later use.

### 3. Message Storage
- Save stored messages in JSON format.
- Keep message information available for later retrieval.
- Use file handling to manage stored message data.

### 4. Unit Testing
- Use JUnit to test application functionality.
- Test login and messaging-related methods.
- Check expected results and identify errors.

## Technologies Used

- **Programming language:** Java
- **IDE:** Apache NetBeans
- **Build tool:** Apache Maven
- **Testing framework:** JUnit 5
- **Data storage:** JSON
- **Version control:** Git and GitHub

## Project Structure

```text
ChatApp - formative 2 part 2/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/chatapp/
│   │           ├── Login.java
│   │           ├── Message.java
│   │           ├── MessageJsonStore.java
│   │           └── MainApp.java
│   └── test/
│       └── java/
│           └── com/chatapp/
│               ├── LoginTest.java
│               └── MessageTest.java
├── pom.xml
├── README.md
```

*Note: The structure above describes the intended project layout. Keep only the files and folders that actually exist in your repository.*

## Requirements

Before running the project, ensure that you have:

- Java Development Kit (JDK) compatible with the project's `pom.xml`.
- Apache NetBeans IDE.
- Apache Maven, or NetBeans Maven support.
- An internet connection when Maven needs to download project dependencies.

## How to Run the Application

1. Download or clone this repository from GitHub.
2. Open Apache NetBeans.
3. Select **File → Open Project**.
4. Locate and open the `ChatApp - formative 2 part 2` project folder.
5. Allow Maven to load the project's dependencies.
6. Locate `MainApp.java` under Source Packages.
7. Right-click `MainApp.java` and select **Run File**, or right-click the project and select **Run**.
8. Follow the instructions displayed in the console.

## How to Run the Unit Tests

1. Open the project in NetBeans.
2. Expand **Test Packages**.
3. Check that `LoginTest.java` and `MessageTest.java` are present.
4. Right-click the project and select **Test**.
5. Review the test results and resolve any failures.

Alternatively, if Maven is installed, open a terminal in the project directory and run:

```bash
mvn test
```

## Data Storage

The application uses JSON file storage for messages where implemented. Stored messages can be saved and retrieved through the relevant storage functionality.

Make sure the application has permission to create or update its data files.

## Learning Outcomes

This project provides practical experience in:

- Java classes and objects.
- Methods and validation.
- User input and application control flow.
- Message processing and file handling.
- Unit testing with JUnit.
- Version control using Git and GitHub.

## Author

**Student:** Leonard Petersen - ST10504745  
**Module:** PROG5121 – Programming 1A  
**Project:** ChatApp – Formative 2 Part 2
