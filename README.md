# credit_simulator
jar sample for credit_simulator



Requirement : 
java 1.8 or above
use command : 
$ java -jar credit_simulator.jar


#WebServices EndPoint Url Update MockyIO Updates : 
http://www.mocky.io/v2/5d11a58d310000b23508cd62

# 🚗 Credit Simulator

A console-based Java application to simulate vehicle loan calculations (car & motorcycle) with validation, dynamic CLI commands, and multi-input support.

---

## 📌 Overview

This project is built as part of a backend technical assessment.
It demonstrates clean code structure, Object-Oriented Programming (OOP), validation logic, and basic system design.

---

## ✨ Features

* ✅ Vehicle loan simulation (Car & Motorcycle)
* ✅ Input validation (type, condition, year, DP, tenor)
* ✅ Dynamic interest rate per year
* ✅ Monthly installment calculation
* ✅ Console-based interactive CLI
* ✅ Multi-sheet save & load system
* ✅ Input from:

  * CLI (manual input)
  * File
  * API (if implemented)
* ✅ Clean OOP structure (model, service, factory, util)
* ✅ Unit testing with JUnit
* ✅ CI pipeline using GitHub Actions

---

## 🧠 Business Rules

### Vehicle Type

* Only `mobil` or `motor`
* Case-insensitive
* Alphabet only

### Condition

* `NEW` or `USED`

### Year

* Must be 4-digit number
* Range: `1900 - current year`
* For `NEW`:
  `year ≥ currentYear - 1`

### Tenor

* Must be between **1–6 years**

### Down Payment (DP)

* `NEW` → minimum **35%**
* `USED` → minimum **25%**

### Interest Rate

* Car: **8%**
* Motorcycle: **9%**
* Increase **0.1% per year**

---

## 🏗️ Project Structure

```text
src/
 ├── main/
 │    ├── java/
 │    │    └── com/anty/credit/
 │    │
 │    │    ├── App.java
 │    │
 │    │    ├── model/
 │    │    │    ├── Loan.java
 │    │    │    ├── LoanCalculation.java
 │    │    │    ├── CreditProduct.java
 │    │    │    ├── MobilKredit.java
 │    │    │    ├── MotorKredit.java
 │    │    │    └── Vehicle.java
 │    │
 │    │    ├── service/
 │    │    │    ├── CreditCalculatorService.java
 │    │    │    ├── ValidatorService.java
 │    │    │    ├── APIService.java
 │    │    │    └── FileInputService.java
 │    │
 │    │    ├── factory/
 │    │    │    └── InterestFactory.java
 │    │
 │    │    └── util/
 │    │         ├── FormatHelper.java
 │    │         └── JsonHelper.java
 │
 │    └── test/
 │         └── com/anty/credit/
 │              └── AppTest.java
```

---

## 🧩 Architecture Overview

* **model** → representasi data (Loan, Product, Result)
* **service** → business logic & validation
* **factory** → menentukan interest berdasarkan type
* **util** → helper (formatting, JSON, dll)
* **App** → entry point & CLI handler

---

## 💡 Design Pattern Used

* **Factory Pattern** → untuk menentukan jenis kredit (mobil/motor)
* **Separation of Concerns** → logic dipisah per layer
* **Validation Layer** → centralized validation di service

```

---

## ⚙️ Requirements

* Java 8+
* Maven

---

## 🚀 Build & Run

### 🔹 Build

```bash
./build.sh
```

---

### 🔹 Run (Manual CLI)

```bash
./credit_simulator
```

---

### 🔹 Run with File Input

```bash
./credit_simulator file_inputs.txt
```

The file must contain exactly 6 lines in this format:
```
mobil
NEW
2023
10000000
5
4000000
```

For detailed file input guide, see [FILE_INPUT_GUIDE.md](FILE_INPUT_GUIDE.md)

---

### 🔹 Run with API - example 

```bash
./credit_simulator https://mocki.io/v1/672e5b6a-2594-4065-a482-dd12fec5f8bb


## 💻 CLI Commands

```
show   → display available commands
input  → input loan data
save   → save current data as sheet
load   → load saved sheet
list   → list all sheets
exit   → exit program
```

---

## 🧪 Example Input

```
mobil
NEW
2025
10000000
3
4000000
```

---

## 📊 Example Output

```
tahun 1 : Rp. 206,667/bln, Suku Bunga : 8.0%
tahun 2 : Rp. 207,167/bln, Suku Bunga : 8.1%
tahun 3 : Rp. 207,667/bln, Suku Bunga : 8.2%
```

---

## 🧪 Testing

Run unit tests:

```bash
mvn test
```

---

## 🔁 CI/CD

This project uses **GitHub Actions** for:

* Automated build
* Automated test execution

Pipeline runs on every push.

---

## 👩‍💻 Author

**Siti Juniyanti**

---

## 📎 Notes

This project focuses on:

* Clean architecture
* Validation logic
* Maintainable and readable code
* Real-world backend development practices
