package com.anty.credit;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import com.anty.credit.model.Loan;
import com.anty.credit.model.CreditProduct;
import com.anty.credit.model.MobilKredit;
import com.anty.credit.model.MotorKredit;
import com.anty.credit.model.LoanCalculation;
import com.anty.credit.service.CreditCalculatorService;
import com.anty.credit.service.ValidatorService;
import com.anty.credit.service.FileInputService;
import com.anty.credit.factory.InterestFactory;
import java.util.List;
import java.io.File;

public class App {
    static Map<String, List<String>> sheets = new HashMap<>();
    static List<String> currentData = new ArrayList<>();

    public static void main(String[] args) {
        try {
            // Check if file path was provided as argument
            if (args.length > 0) {
                String filePath = args[0];
                handleFileInput(filePath);
                return;
            }

            // Otherwise, run interactive mode
            try (Scanner sc = new Scanner(System.in)) {

                while (true) {
                    System.out.print("\nCommand: ");
                    String command = sc.nextLine();

                    if (command.equalsIgnoreCase("exit")) {
                        System.out.println("Bye!");
                        break;
                    }

                    if (command.equalsIgnoreCase("show")) {
                        showMenu();
                    } else if (command.equalsIgnoreCase("input")) {
                        runSimulation(sc);
                    } else if (command.equalsIgnoreCase("save")) {
                        if (currentData.isEmpty()) {
                            System.out.println("Tidak ada data untuk disimpan");
                        } else {
                            System.out.print("Nama sheet: ");
                            String name = sc.nextLine();

                            sheets.put(name, new ArrayList<>(currentData));

                            System.out.println("Data disimpan sebagai: " + name);
                        }
                    } else if (command.equalsIgnoreCase("load")) {
                        if (sheets.isEmpty()) {
                            System.out.println("Belum ada sheet tersimpan");
                        } else {
                            System.out.print("Nama sheet: ");
                            String name = sc.nextLine();

                            if (!sheets.containsKey(name)) {
                                System.out.println("Sheet tidak ditemukan");
                            } else {
                                System.out.println("Load sheet: " + name);

                                List<String> data = sheets.get(name);

                                runCalculation(
                                        data.get(0),
                                        data.get(1),
                                        Integer.parseInt(data.get(2)),
                                        Double.parseDouble(data.get(3)),
                                        Integer.parseInt(data.get(4)),
                                        Double.parseDouble(data.get(5)));
                            }
                        }
                    } else if (command.equalsIgnoreCase("list")) {
                        if (sheets.isEmpty()) {
                            System.out.println("Belum ada sheet");
                        } else {
                            System.out.println("Daftar sheet:");
                            for (String key : sheets.keySet()) {
                                System.out.println("- " + key);
                            }
                        }
                    } else {
                        System.out.println("Command tidak dikenali");
                    }

                }

            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void handleFileInput(String filePath) {
        try {
            // Validate file exists
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("Error: File tidak ditemukan: " + filePath);
                return;
            }

            // Read file
            List<String> lines = FileInputService.readFile(filePath);

            // Validate we have exactly 6 lines
            if (lines.size() != 6) {
                System.out.println("Error: File harus berisi 6 baris (type, condition, year, amount, tenor, dp)");
                return;
            }

            // Parse data
            String type = lines.get(0).trim();
            String condition = lines.get(1).trim();
            int year = Integer.parseInt(lines.get(2).trim());
            double amount = Double.parseDouble(lines.get(3).trim());
            int tenor = Integer.parseInt(lines.get(4).trim());
            double dp = Double.parseDouble(lines.get(5).trim());

            // Validate data
            ValidatorService.validate(type, condition, year, amount, tenor, dp);

            // Run calculation
            System.out.println("Data dari file: " + filePath);
            System.out.println("Type: " + type + ", Condition: " + condition + ", Year: " + year);
            System.out.println(
                    "Amount: " + formatRupiah(amount) + ", Tenor: " + tenor + " tahun, DP: " + formatRupiah(dp));
            System.out.println("\n=== Hasil Perhitungan ===\n");

            runCalculation(type, condition, year, amount, tenor, dp);

        } catch (NumberFormatException e) {
            System.out.println("Error: Format data tidak valid. Pastikan year, amount, tenor, dan dp adalah angka: "
                    + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Validasi gagal - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void showMenu() {
        System.out.println("Available commands:");
        System.out.println("show  - tampilkan command");
        System.out.println("input - input data kredit");
        System.out.println("save  - simpan data");
        System.out.println("load  - load data tersimpan");
        System.out.println("exit  - keluar");
        System.out.println("list  - lihat semua sheet");
    }

    public static void runSimulation(Scanner sc) {
        String type = askType(sc);
        String condition = askCondition(sc);
        int year = askYear(sc, condition);
        double amount = askAmount(sc);
        int tenor = askTenor(sc);
        double dp = askDP(sc, amount, condition);

        currentData.clear();
        currentData.add(type);
        currentData.add(condition);
        currentData.add(String.valueOf(year));
        currentData.add(String.valueOf(amount));
        currentData.add(String.valueOf(tenor));
        currentData.add(String.valueOf(dp));

        runCalculation(type, condition, year, amount, tenor, dp);
    }

    public static void runCalculation(String type, String condition, int year,
            double amount, int tenor, double dp) {

        CreditProduct product = createCreditProduct(type);

        for (int i = 1; i <= tenor; i++) {

            Loan loan = new Loan(amount, tenor, dp);
            CreditCalculatorService service = new CreditCalculatorService();

            LoanCalculation result = service.calculate(loan, product);
            double monthly = result.getMonthlyPayment();

            System.out.println(
                    "tahun " + i +
                            " : " + formatRupiah(monthly) +
                            "/bln, Suku Bunga : " + formatPercent(product.getInterestRate()));
        }
    }

    private static CreditProduct createCreditProduct(String type) {
        if (type.equalsIgnoreCase("mobil")) {
            return new MobilKredit();
        } else if (type.equalsIgnoreCase("motor")) {
            return new MotorKredit();
        } else {
            throw new IllegalArgumentException("Jenis kendaraan tidak valid");
        }
    }

    private static String askType(Scanner sc) {
        while (true) {
            try {
                System.out.print("Jenis kendaraan (mobil/motor): ");
                String input = sc.nextLine().trim();
                ValidatorService.validateType(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("WARN!!!!!!!!" + e.getMessage() + "!!!!!!!!");
            } catch (Exception e) {
                System.out.println("WARN!!!!!!!Input error!!!!!!!!");
            }
        }
    }

    private static String askCondition(Scanner sc) {
        while (true) {
            try {
                System.out.print("Kondisi kendaraan (NEW/USED): ");
                String input = sc.nextLine().trim();
                ValidatorService.validateCondition(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("WARN!!!!!!!!" + e.getMessage() + "!!!!!!!!");
            } catch (Exception e) {
                System.out.println("WARN!!!!!!!Input error!!!!!!!!");
            }
        }
    }

    private static int askYear(Scanner sc, String condition) {
        while (true) {
            try {
                System.out.print("Tahun kendaraan: ");
                int year = Integer.parseInt(sc.nextLine());
                ValidatorService.isValidYear(year, condition);
                return year;
            } catch (IllegalArgumentException e) {
                System.out.println("WARN!!!!!!!!!" + e.getMessage() + "!!!!!!");
            } catch (Exception e) {
                System.out.println("WARN!!!!!!!Input harus angka!!!!!!!!");
            }
        }
    }

    private static double askAmount(Scanner sc) {
        while (true) {
            try {
                System.out.print("Jumlah pinjaman: ");
                double amount = Double.parseDouble(sc.nextLine().trim());
                ValidatorService.validateAmount(amount);
                return amount;
            } catch (NumberFormatException ex) {
                System.out.println("WARN!!!!!!!Input harus angka numeric!!!!!!!!");
            } catch (IllegalArgumentException ex) {
                System.out.println("WARN!!!!!!!!" + ex.getMessage() + "!!!!!!!!");
            }
        }
    }

    private static int askTenor(Scanner sc) {
        while (true) {
            try {
                System.out.print("Tenor (tahun): ");
                int tenor = Integer.parseInt(sc.nextLine());
                ValidatorService.validateTenor(tenor);
                return tenor;
            } catch (NumberFormatException ex) {
                System.out.println("WARN!!!!!!!Input harus angka numeric!!!!!!!!");
            } catch (IllegalArgumentException ex) {
                System.out.println("WARN!!!!!!!!" + ex.getMessage() + "!!!!!!!!");
            }
        }
    }

    private static double askDP(Scanner sc, double amount, String condition) {
        while (true) {
            try {
                System.out.print("Down payment: ");
                double dp = Double.parseDouble(sc.nextLine().trim());
                ValidatorService.validateDP(dp, amount, condition);
                return dp;
            } catch (NumberFormatException ex) {
                System.out.println("WARN!!!!!!!Input harus angka numeric!!!!!!!!");
            } catch (IllegalArgumentException ex) {
                System.out.println("WARN!!!!!!!!" + ex.getMessage() + "!!!!!!!!");
            }
        }
    }

    public static String formatRupiah(double amount) {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("id", "ID"));
        return "Rp " + formatter.format(amount);
    }

    public static String formatPercent(double rate) {
        return String.format("%.1f%%", rate * 100);
    }
}
