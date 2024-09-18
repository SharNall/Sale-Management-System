<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sale Management System</title>
</head>
<body>
    <h1>Sale Management System</h1>

    <h2>Overview</h2>
    <p>The <strong>Sale Management System</strong> is a desktop-based application designed to streamline sales operations. Built with Java and JavaFX, it provides an intuitive interface for managing products, customers, and sales transactions. The system integrates with MySQL for data storage and uses CSS for styling to enhance the user experience.</p>

    <h2>Features</h2>
    <ul>
        <li><strong>Product Management</strong>: Easily add, update, and delete products from the inventory.</li>
        <li><strong>Customer Management</strong>: Store and manage customer information with ease.</li>
        <li><strong>Sales Transactions</strong>: Record, view, and track sales transactions efficiently.</li>
        <li><strong>Reports</strong>: Generate sales reports and view key metrics to understand business performance.</li>
    </ul>

    <h2>Tech Stack</h2>
    <ul>
        <li><strong>Java</strong>: Core programming language for application logic.</li>
        <li><strong>JavaFX</strong>: Used for building the user interface.</li>
        <li><strong>MySQL</strong>: Database system for storing product, customer, and sales data.</li>
        <li><strong>CSS</strong>: Custom styling for JavaFX components to provide a sleek and modern look.</li>
    </ul>

    <h2>Installation</h2>
    <ol>
        <li>Clone the repository:
            <pre><code>git clone https://github.com/SharNall/Sale-Management-System.git</code></pre>
        </li>
        <li>Ensure you have the following installed:
            <ul>
                <li>Java JDK (version 8 or higher)</li>
                <li>JavaFX SDK</li>
                <li>MySQL Server</li>
                <li>Any IDE (such as IntelliJ or Eclipse) for running the project</li>
            </ul>
        </li>
        <li>Set up the MySQL database:
            <ul>
                <li>Create a MySQL database.</li>
                <li>Execute the provided SQL script (in the <code>/db</code> folder) to create the necessary tables.</li>
                <li>Update the database connection details in the <code>dbConnection</code> class.</li>
            </ul>
        </li>
        <li>Run the project using your IDE, ensuring that the JavaFX libraries are properly configured.</li>
    </ol>

    <h2>Usage</h2>
    <ul>
        <li><strong>Products</strong>: Add new products, update existing ones, or delete them.</li>
        <li><strong>Customers</strong>: Manage customer records, including personal and purchase information.</li>
        <li><strong>Sales</strong>: Record sales transactions and generate reports.</li>
    </ul>

    <h2>Screenshots</h2>
    <p>Include screenshots of key screens such as the product list, sales form, and dashboard.</p>

    <h2>Contributions</h2>
    <p>Feel free to fork this repository, submit issues, or contribute by opening pull requests.</p>
</body>
</html>
