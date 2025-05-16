package model.Pacientet;

import model.Perdoruesi.Perdoruesi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Pacientet {
    private int id;
    private int user_id;
    private LocalDate datelindja;
    private String gjinia;
    private String adresa;
    private String kontakti_emergjent;
    private Perdoruesi perdoruesi;

    private Pacientet(int id, int user_id, LocalDate datelindja, String gjinia, String adresa, String kontakti_emergjent) {
        this.id = id;
        this.user_id = user_id;
        this.datelindja = datelindja;
        this.gjinia = gjinia;
        this.adresa = adresa;
        this.kontakti_emergjent = kontakti_emergjent;
    }

    public static Pacientet getInstance(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int user_id = resultSet.getInt("user_id");
        String gjinia = resultSet.getString("gjinia");
        java.sql.Date datelindjaSql = resultSet.getDate("datelindja");
        LocalDate datelindja = (datelindjaSql != null) ? datelindjaSql.toLocalDate() : null;

        String adresa = resultSet.getString("adresa");
        if (resultSet.wasNull()) adresa = null;

        String kontakti_emergjent = resultSet.getString("kontakti_emergjent");
        if (resultSet.wasNull()) kontakti_emergjent = null;

        return new Pacientet(id, user_id, datelindja, gjinia, adresa, kontakti_emergjent);
    }


    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public LocalDate getDatelindja() {
        return datelindja;
    }

    public String getGjinia() {
        return gjinia;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getKontakti_emergjent() {
        return kontakti_emergjent;
    }

    public Perdoruesi getPerdoruesi() {
        return perdoruesi;
    }

    public void setPerdoruesi(Perdoruesi perdoruesi) {
        this.perdoruesi = perdoruesi;
    }

    public String getEmri() {
        return perdoruesi != null ? perdoruesi.getEmri() : "";
    }

    public String getMbiemri() {
        return perdoruesi != null ? perdoruesi.getMbiemri() : "";
    }

    public String getEmail() {
        return perdoruesi != null ? perdoruesi.getEmail() : "";
    }

    public String getTelefoni() {
        return perdoruesi != null ? perdoruesi.getTelefoni() : "";
    }
}
