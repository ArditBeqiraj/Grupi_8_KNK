package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Pacientet {
    private int pacient_id;
    private int user_id;
    private LocalDate datelindja;
    private String gjinia;
    private String adresa;
    private String kontakti_emergjent;

    private Pacientet(int pacient_id, int user_id, LocalDate datelindja, String gjinia, String adresa, String kontakti_emergjent) {
        this.pacient_id = pacient_id;
        this.user_id = user_id;
        this.datelindja = datelindja;
        this.gjinia = gjinia;
        this.adresa = adresa;
        this.kontakti_emergjent = kontakti_emergjent;
    }

    public static Pacientet getInstance(ResultSet resultSet) throws SQLException {
        int pacient_id = resultSet.getInt("pacient_id");
        int user_id = resultSet.getInt("user_id");
        java.sql.Date datelindja = resultSet.getDate("datelindja");
        String gjinia = resultSet.getString("gjinia");
        String adresa = resultSet.getString("adresa");
        String kontakti_emergjent = resultSet.getString("kontakti_emergjent");

        return new Pacientet(pacient_id, user_id, datelindja.toLocalDate(), gjinia, adresa, kontakti_emergjent);
    }

    public int getPacient_id() {
        return pacient_id;
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
}
