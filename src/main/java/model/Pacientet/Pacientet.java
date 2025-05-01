package model.Pacientet;

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
        java.sql.Date datelindja = resultSet.getDate("datelindja");
        String gjinia = resultSet.getString("gjinia");
        String adresa = resultSet.getString("adresa");
        String kontakti_emergjent = resultSet.getString("kontakti_emergjent");

        return new Pacientet(id, user_id, datelindja.toLocalDate(), gjinia, adresa, kontakti_emergjent);
    }

    public int getPacient_id() {
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
}
