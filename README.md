# Sale Management System

## Project Information
This project is a comprehensive Sale Management System developed using Java and JavaFX. It provides functionalities for managing products, customers, sales transactions, and generating sales reports. The system is designed to help businesses streamline their sales operations and maintain accurate records of transactions.

## Resources

- **Product Management:** Admins can add, update, and remove products, track inventory levels, and provide detailed product information.
- **Customer Management:** Admins can add and manage customer information, including customer profiles.
- **Sales Management:** Record, track, and modify sales transactions. Each sale is tied to a customer, with details about purchased products and total amounts.
- **Reports:** Generate sales reports for business analysis and decision-making.

## ER Diagram
The ER diagram outlines the relationships between the main entities in the Sale Management System:

![Screenshot 2024-01-26 154657](https://github.com/user-attachments/assets/ac23aa19-5825-4f51-8d33-610fd7707ce9)


## Building, Packaging, and Running the Application

### Building and Packaging

1. **Clone the repository:**
    ```bash
    git clone https://github.com/SharNall/Sale-Management-System.git
    cd Sale-Management-System
    ```

2. **Set up the MySQL database:**
    - Create a MySQL database.
    - Execute the SQL script located in the `/db` folder to create necessary tables.
    - Update the database connection details in the `dbConnection` class.

3. **Compile the project using your IDE (IntelliJ, Eclipse):**
    - Make sure JavaFX libraries are properly configured in your IDE.

### Running the Application

1. **Run the MySQL server and ensure the database is set up correctly.**

2. **Run the Java application through your IDE or via the command line using:**
    ```bash
    java -jar SaleManagementSystem.jar
    ```

## What We Have Learned
Through this project, we have gained valuable experience and knowledge in the following areas:

- **Java & JavaFX:** Developing user-friendly desktop applications.
- **MySQL:** Managing relational data and ensuring data integrity.
- **CSS for JavaFX:** Styling desktop applications to provide a sleek, modern interface.
- **Database Connectivity:** Establishing and managing connections between the Java application and a MySQL database.

