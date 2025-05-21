package app;

import model.Mjeket.Mjeket;
import model.Pacientet.Pacientet;
import model.Perdoruesi.Perdoruesi;

import java.util.Locale;

public class SessionManager {
    private static Perdoruesi perdoruesi;
    private static Mjeket mjeku;
    private static Locale currentLocale = Locale.getDefault();

    public static void setPerdoruesi(Perdoruesi newPerdoruesi) {
        perdoruesi = newPerdoruesi;
    }

    public static void setMjeku(Mjeket newmjeku) {mjeku = newmjeku;}

    public static Mjeket getMjeku() {return mjeku;}

    public static Perdoruesi getPerdoruesi() {
        return perdoruesi;
    }

    public static void logout() {
        perdoruesi = null;
    }

    public static void setLocale(Locale newLocale) {
        currentLocale = newLocale;
        Locale.setDefault(newLocale);
    }

    public static Locale getLocale() {
        return currentLocale;
    }

    public static Locale getNewLocale() {
        if (getLocale().getLanguage().equals("en")) {
            return new Locale("sq");
        }

        return new Locale("en", "US");
    }
}