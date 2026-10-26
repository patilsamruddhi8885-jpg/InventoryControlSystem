# Inventory Control System

## About the Project
The Inventory Control System is a console-based application developed using Core Java. It helps manage product information, monitor stock levels, and maintain inventory transaction records.

## Features
- Add new products with duplicate ID prevention
- View all products
- Search for products by ID
- Update product details
- Delete products
- Add stock and remove stock
- Identify low-stock products
- Generate an inventory value report
- View transaction history
- Record initial stock, stock additions, and stock removals
- Save and load product data using file handling
- Preserve transaction history between program runs
- Validate numeric inputs and handle invalid entries

## Technologies Used
- Java
- Object-Oriented Programming (OOP)
- ArrayList and collections
- Exception handling
- File handling
- LocalDateTime
- VS Code

## Project Structure
```text
Inventory Control System java project/
├── src/
│   ├── Main.java
│   ├── Product.java
│   ├── Inventory.java
│   ├── Transaction.java
│   ├── FileManager.java
│   └── InputValidator.java
├── data/
│   ├── products.txt
│   └── transactions.txt
├── screenshots/
├── .gitignore
└── README.md
```

## How to Run

1. Install the Java Development Kit (JDK).
2. Open the project folder in VS Code.
3. Make sure the Java files are in the `src` folder.
4. Compile the source files from the project root:

```bash
javac -d out src/*.java
```

5. Run the application:

```bash
java -cp out Main
```

The program creates its data files in the `data` directory if they do not already exist.

## Learning Outcomes
- Practiced object-oriented programming in Java.
- Used collections to manage product records.
- Implemented file-based data persistence.
- Applied input validation and exception handling.
- Designed inventory and transaction management functionality.

## Future Improvements
- Add a graphical user interface.
- Integrate a database such as MySQL.
- Add user authentication and role-based access.
- Export inventory reports to CSV.

## Author
Samruddhi Patil
