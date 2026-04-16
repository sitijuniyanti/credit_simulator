package com.anty.credit.util;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatHelper {
    
    public static String formatRupiah(double amount) {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("id", "ID"));
        return "Rp " + formatter.format(amount);
    }

    public static String formatPercent(double rate) {
        return String.format("%.1f%%", rate * 100);
    }
}
