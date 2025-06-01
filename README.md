# 🌟 Meter Reading System - Spring Boot Application

This is a simple web application for managing meter readings using **Spring Boot** and **Thymeleaf**. It allows meter readers to record meter readings for customers and calculate their bills.

## 🚀 Features

✅ Add new meter readings for customers  
✅ Display success or error messages after form submission  
✅ Save readings to the database using Spring Data JPA  
✅ Follows the MVC (Model-View-Controller) architecture

---

## 🛠️ Technologies Used

- **Spring Boot** - Web framework
- **Spring Data JPA** - Database persistence
- **Thymeleaf** - HTML rendering engine
- **H2** (or any RDBMS) - Sample database
- **Bootstrap** - UI styling

---

## 🏗️ How to Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/meter-reading-system.git
   cd meter-reading-system
   ```

2. **Configure the database:**
   ```bash
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update

   ```

3. **Run the application:**

   ```bash
   ./mvnw spring-boot:run
   ```

3. **Access the web app:**

   ```bash
   [./mvnw spring-boot:run](http://localhost:8080/reader/meter/addReading)
   ```

   ```bash
   [./mvnw spring-boot:run](http://localhost:8080/customer/meter/bill)

   ```

   ### Screenshot:

   ### 🏠 Customer Page

 ![Customer Page](/images/customer.png) <!-- Replace with your actual image path -->

---

   ### 📄 Meter Reader Page

 ![Meter Reader Page](/images/meterreader.png)
