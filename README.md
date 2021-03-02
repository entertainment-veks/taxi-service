# **Taxi Service**
Demo-app for taxi service on Java

### **Project Structure**
* Controllers
* Daos (and implementations)
* Services (and implementations)
* Models
* Injector
* Security
* Custom exceptions
* Other minor

### **Technologies used**
* Java 11
* Maven 3.1.1
* Maven Checkstyle Plugin
* Javax Servlet 2.5
* JSTL 1.2
* Apache Tomcat
* MySQL

### **To start the project you need:**
1. Download and install the JDK
2. Download and install servlet container (for example Apache Tomcat)
3. Download and install MySQL Server
4. Setup new connection in ConnectionUtil.java with
   dbProperties.put("user", [user]);
   dbProperties.put("password", [password]);
   String url = "jdbc:mysql://[host]:[port]/[db_name]?serverTimezone=UTC";
5. Run

### **End-point list:**
* /drivers/add
* /drivers/all
* /drivers/login
* /drivers/cars
* /cars/add
* /cars/drivers/add
* /manufacturers/add
