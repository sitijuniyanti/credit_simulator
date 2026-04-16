# File Input Setup Guide

## 📋 Overview

The credit simulator now supports file input for automated loan calculation. You can pass a file path as a command-line argument instead of manually entering data.

---

## 🚀 How to Use

### Step 1: Build the Project

```bash
# Using build script
./build.sh

# Or using Maven directly
mvn clean package
```

### Step 2: Prepare Input File

Create a text file (e.g., `file_inputs.txt`) with **exactly 6 lines** in this order:

```
mobil
NEW
2023
10000000
5
4000000
```

**Format explanation:**
| Line | Parameter | Example | Description |
|------|-----------|---------|-------------|
| 1 | Type | `mobil` or `motor` | Vehicle type (case-insensitive) |
| 2 | Condition | `NEW` or `USED` | Vehicle condition |
| 3 | Year | `2023` | Year of vehicle (4-digit number) |
| 4 | Amount | `10000000` | Loan amount in Rupiah |
| 5 | Tenor | `5` | Loan duration in years (1-6) |
| 6 | DP | `4000000` | Down payment in Rupiah |

### Step 3: Run with File Input

#### On Linux/Mac (using bash):
```bash
java -jar target/credit_simulator-1.0-SNAPSHOT.jar file_inputs.txt
```

#### On Windows (using Command Prompt/PowerShell):
```cmd
java -jar target/credit_simulator-1.0-SNAPSHOT.jar file_inputs.txt
```

---

## ✅ Validation Rules

The application will automatically validate your input file:

- ✓ File must exist
- ✓ File must contain exactly 6 lines
- ✓ Vehicle type must be `mobil` or `motor`
- ✓ Condition must be `NEW` or `USED`
- ✓ Year must be 4-digit number within valid range:
  - NEW: currentYear - 1 to currentYear
  - USED: currentYear - 5 to currentYear
- ✓ Amount must be between 1 to 1,000,000,000 Rupiah
- ✓ Tenor must be between 1-6 years
- ✓ DP minimum:
  - NEW: 35% of loan amount
  - USED: 25% of loan amount

---

## 📊 Example Input Files

### Example 1: New Car
```
mobil
NEW
2025
100000000
3
35000000
```

### Example 2: Used Motorcycle
```
motor
USED
2022
15000000
4
4000000
```

### Example 3: New Motorcycle
```
motor
NEW
2024
25000000
2
7500000
```

---

## 🔄 Fallback: Interactive Mode

If you **don't** provide a file path, the application runs in interactive mode:

```bash
java -jar target/credit_simulator-1.0-SNAPSHOT.jar
```

This allows you to:
- Manually input data via CLI
- Save data to sheets
- Load saved sheets
- View calculations for multiple years

---

## ⚠️ Error Examples

### Missing File
```
Error: File tidak ditemukan: input.txt
```

### Invalid File Format
```
Error: File harus berisi 6 baris (type, condition, year, amount, tenor, dp)
```

### Invalid Data
```
Error: Validasi gagal - DP mobil minimal 35% dari jumlah pinjaman
```

---

## 💡 Quick Test

Use the provided `file_inputs.txt`:

```bash
./build.sh
java -jar target/credit_simulator-1.0-SNAPSHOT.jar file_inputs.txt
```

Or use the test scripts:
- **Windows**: `test_file_input.bat`
- **Linux/Mac**: `test-file-input.sh`

---

## 📝 Available Commands (Interactive Mode Only)

When running without file input:
- `show` - Display available commands
- `input` - Input loan data manually
- `save` - Save current data as sheet
- `load` - Load saved sheet
- `list` - List all saved sheets
- `exit` - Exit program

---

## ✨ Features

✅ File-based input processing
✅ Automatic validation
✅ Detailed error messages
✅ Monthly payment calculation
✅ Multi-year interest progression
✅ Formatted currency output (Rupiah)
✅ Backward compatible with interactive mode
