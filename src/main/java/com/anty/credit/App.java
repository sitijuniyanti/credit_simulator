package com.anty.credit;

import java.util.Scanner;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.anty.credit.model.Vehicle;
import com.anty.credit.model.Loan;
import com.anty.credit.service.ValidatorService;
import com.anty.credit.service.CreditCalculatorService;
import com.anty.credit.service.FileInputService;
import com.anty.credit.service.APIService;
import com.anty.credit.factory.InterestFactory;
import com.anty.credit.util.JsonHelper;

public class App {
    static java.util.Map<String, java.util.List<String>> sheets = new java.util.HashMap<>();
    static java.util.List<String> currentData = new java.util.ArrayList<>();

    public static void main(String[] args) {

        String type;
        String condition;
        int year;
        double amount;
        int tenor;
        double dp;

        try {
            Scanner sc = new Scanner(System.in);

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

                        sheets.put(name, new java.util.ArrayList<>(currentData));

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

                            java.util.List<String> data = sheets.get(name);

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

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
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

        System.out.print("Jenis kendaraan: ");
        String type = sc.nextLine();

        System.out.print("Kondisi: ");
        String condition = sc.nextLine();

        System.out.print("Tahun: ");
        int year = Integer.parseInt(sc.nextLine());

        System.out.print("Amount: ");
        double amount = Double.parseDouble(sc.nextLine());

        System.out.print("Tenor: ");
        int tenor = Integer.parseInt(sc.nextLine());

        System.out.print("DP: ");
        double dp = Double.parseDouble(sc.nextLine());

        runCalculation(type, condition, year, amount, tenor, dp);
        currentData.clear();
        currentData.add(type);
        currentData.add(condition);
        currentData.add(String.valueOf(year));
        currentData.add(String.valueOf(amount));
        currentData.add(String.valueOf(tenor));
        currentData.add(String.valueOf(dp));
    }

    public static void runCalculation(String type, String condition, int year,
            double amount, int tenor, double dp) {

        ValidatorService.validate(type, condition, year, amount, tenor, dp);

        double rate = InterestFactory.getRate(type);

        Loan loan = new Loan(amount, tenor, dp);
        CreditCalculatorService service = new CreditCalculatorService();

        double monthly = service.calculate(loan, rate);

        System.out.println("Cicilan per bulan: " + monthly);
    }
}