package model.Pacientet;

import model.Perdoruesi.Perdoruesi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Pacientet {
    private int id;
    private int userId;
    private LocalDate datelindja;
    private String gjinia;
    private String adresa;
    private String kontaktiEmergjent;
    private Integer mjekuFamiljarId;
    private Perdoruesi perdoruesi;

    private Pacientet(int id, int userId, LocalDate datelindja, String gjinia, String adresa, String kontaktiEmergjent, Integer mjekuFamiljarId) {
        this.id = id;
        this.userId = userId;
        this.datelindja = datelindja;
        this.gjinia = gjinia;
        this.adresa = adresa;
        this.kontaktiEmergjent = kontaktiEmergjent;
        this.mjekuFamiljarId = mjekuFamiljarId;
    }

    public static Pacientet getInstance(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int userId = resultSet.getInt("user_id");

        java.sql.Date datelindjaSql = resultSet.getDate("datelindja");
        LocalDate datelindja = (datelindjaSql != null) ? datelindjaSql.toLocalDate() : null;

        String gjinia = resultSet.getString("gjinia");

        String adresa = resultSet.getString("adresa");
        if (resultSet.wasNull()) adresa = null;

        String kontaktiEmergjent = resultSet.getString("kontakti_emergjent");
        if (resultSet.wasNull()) kontaktiEmergjent = null;

        Integer mjekuFamiljarId = resultSet.getObject("mjeku_familjar_id", Integer.class);

        return new Pacientet(id, userId, datelindja, gjinia, adresa, kontaktiEmergjent, mjekuFamiljarId);
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

    public String getKontaktiEmergjent() {
        return kontaktiEmergjent;
    }

    public Integer getMjekuFamiljarId() {
        return mjekuFamiljarId;
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
