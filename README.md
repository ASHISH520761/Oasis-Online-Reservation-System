# Online Reservation System (Java Swing + MySQL)

The **Online Reservation System** is a Java Swing–based desktop application designed to manage ticket reservations efficiently. It provides secure user login, ticket booking with automatic PNR generation, ticket viewing, and cancellation with confirmation. All data is stored and managed using a centralized MySQL database through JDBC.

---

## 🚀 Features

- Secure user login system  
- Ticket reservation with auto-generated PNR  
- View ticket details using PNR  
- Ticket cancellation with confirmation  
- User-friendly graphical interface (Java Swing)  
- Centralized MySQL database  
- Reliable JDBC-based database connectivity  

---

## 🛠️ Technologies Used

- **Java (JDK 8+)**
- **Java Swing (GUI)**
- **MySQL**
- **JDBC (MySQL Connector/J)**
- **CMD / Terminal**

---

## 📁 Project Structure
online-reservation-system
│
├── lib
│ └── mysql-connector-j.jar
│
├── bin
│ └── (compiled .class files)
│
└── src
└── reservation
├── DBConnection.java
├── LoginFrame.java
├── MenuFrame.java
├── ReservationFrame.java
├── ViewTicketFrame.java
├── CancellationFrame.java
└── MainGUI.java


---

## 🗄️ Database Setup

Create database and tables in MySQL:

```sql
CREATE DATABASE reservation_system;
USE reservation_system;

CREATE TABLE users (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50)
);

INSERT INTO users VALUES ('admin','admin123');

CREATE TABLE reservation (
    pnr INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    age INT,
    train_no VARCHAR(10),
    train_name VARCHAR(50),
    class_type VARCHAR(10),
    journey_date DATE,
    source VARCHAR(30),
    destination VARCHAR(30)
);


▶️ How to Run the Project

1️⃣ Compile the project
javac -d bin -cp lib\mysql-connector-j.jar src\reservation\*.java

2️⃣ Run the application
java -cp "lib\mysql-connector-j.jar;bin" reservation.MainGUI
