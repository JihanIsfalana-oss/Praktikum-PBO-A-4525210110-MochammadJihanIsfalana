package id.ac.univpancasila.perpustakaan.util;

import java.text.NumberFormat;
import java.util.Locale;

public final class Format {

    private Format() {
    }

    public static String rupiah(long nominal) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("id-ID"));
        formatter.setMaximumFractionDigits(0);
        formatter.setMinimumFractionDigits(0);
        return formatter.format(nominal);
    }
}
