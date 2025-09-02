# OrangeHRM Test Automation Framework

This project is an automated testing framework for [OrangeHRM](https://www.orangehrm.com/) built using **Java**, **Selenium WebDriver**, and **TestNG**.  
It is designed to simplify test execution, debugging, and reporting while ensuring both frontend and backend validation.

---

## 🚀 Features

- **Logger Information for Easy Debugging**  
  Centralized logging makes it simple to trace test execution and identify issues quickly.

- **Auto-Open TestNG Report**  
  After test execution, the HTML test report is automatically opened for immediate review.

- **Screenshot Capture on Failed Tests**  
  When a test fails, the framework automatically captures a screenshot and attaches it to the report.

- **Database Checking for Backend Tests**  
  Enables validation of data integrity by directly querying the backend database.

- **Data-Driven Testing via XLSX**  
  Test data is read from Excel files, supporting multiple input combinations without modifying the test scripts.

---

## 🛠 Tech Stack

- **Language**: Java  
- **Automation Tool**: Selenium WebDriver  
- **Test Framework**: TestNG  
- **Build Tool**: Maven  
- **Database**: JDBC (MySQL or other supported DBs)  
- **Reporting**: ExtentReports / TestNG default reports  
- **Data Source**: Apache POI for Excel (XLSX) reading

---

## 📂 Project Structure

```
OrangeHRM/
 ├── PageObjects/         # Page Object Model classes
 ├── TestCases/           # Test scripts
 ├── Utilities/           # Helpers (logging, reporting, database, data provider)
 ├── testng.xml           # Test suite configuration
 ├── pom.xml              # Maven dependencies and build configuration
 └── README.md            # Project documentation
```

---

## ▶️ How to Run

1. **Clone the repository**
   ```bash
   git clone https://github.com/<your-username>/OrangeHRM.git
   cd OrangeHRM
   ```

2. **Configure test data and properties**  
   - Update `config.properties` for URL, username, password, etc.  
   - Ensure your test data Excel (`.xlsx`) is placed in the correct path.

3. **Run tests using Maven**
   ```bash
   mvn clean test
   ```

4. **View reports**  
   The TestNG/ExtentReports HTML report will auto-open in your browser after execution.

---

## 📜 License

This project is for learning and demonstration purposes. Modify and use as needed.

---

### ✨ Author
Developed by **Pika Prasetya**
