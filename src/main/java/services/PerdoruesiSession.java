package services;

import model.Perdoruesi.Perdoruesi;

public class PerdoruesiSession {
    private static Perdoruesi perdoruesi;

    public static void setPerdoruesiSession(Perdoruesi newPerdoruesi) { perdoruesi = newPerdoruesi; }

    public static Perdoruesi getPerdoruesi() { return perdoruesi; }

    public static void clearPerdoruesiSession() { perdoruesi = null; }
}
