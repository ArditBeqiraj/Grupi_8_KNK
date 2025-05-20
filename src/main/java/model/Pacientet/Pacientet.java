package model.Pacientet;

import model.Perdoruesi.Perdoruesi;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;

public class Pacientet {
    private int id;
    private int userId;
    private LocalDate datelindja;
    private String gjinia;
    private String adresa;
    private String kontakti_emergjent;
    private Integer mjeku_familjar_id;
    private Perdoruesi perdoruesi;

    private Pacientet(int id, int userId, LocalDate datelindja, String gjinia, String adresa, String kontakti_emergjent, Integer mjeku_familjar_id) {
        this.id = id;
        this.userId = userId;
        this.datelindja = datelindja;
        this.gjinia = gjinia;
        this.adresa = adresa;
        this.kontakti_emergjent = kontakti_emergjent;
        this.mjeku_familjar_id = mjeku_familjar_id;
    }

    public static Pacientet getInstance(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int userId = resultSet.getInt("user_id");

        java.sql.Date datelindjaSql = resultSet.getDate("datelindja");
        LocalDate datelindja = (datelindjaSql != null) ? datelindjaSql.toLocalDate() : null;

        String gjinia = resultSet.getString("gjinia");

        String adresa = resultSet.getString("adresa");
        if (resultSet.wasNull()) adresa = null;

        String kontakti_emergjent = resultSet.getString("kontakti_emergjent");
        if (resultSet.wasNull()) kontakti_emergjent = null;

        Integer mjeku_familjar_id = resultSet.getObject("mjeku_familjar_id", Integer.class);
        if (resultSet.wasNull()) mjeku_familjar_id = null;

        return new Pacientet(id, userId, datelindja, gjinia, adresa, kontakti_emergjent, mjeku_familjar_id);
    }

    // GETTERS
    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
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

    public Integer getMjeku_familjar_id() {
        return mjeku_familjar_id;
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
