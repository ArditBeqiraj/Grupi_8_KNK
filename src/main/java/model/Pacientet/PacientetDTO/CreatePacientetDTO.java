package model.Pacientet.PacientetDTO;

import java.sql.Date;
import java.time.LocalDate;

public class CreatePacientetDTO {
    private int user_id;
    private LocalDate datelindja;
    private String gjinia;
    private String adresa;
    private String kontakti_emergjent;
    private Integer mjeku_familjar_id;


    public CreatePacientetDTO(int user_id, LocalDate datelindja, String gjinia, String adresa, String kontakti_emergjent, Integer mjeku_familjar_id) {
        this.user_id = user_id;
        this.datelindja = datelindja;
        this.gjinia = gjinia;
        this.adresa = adresa;
        this.kontakti_emergjent = kontakti_emergjent;
        this.mjeku_familjar_id = mjeku_familjar_id;
    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getDatelindja() {
        if (this.datelindja == null) return null;
        return Date.valueOf(this.datelindja);
    }


    public void setDatelindja(LocalDate datelindja) {
        this.datelindja = datelindja;
    }

    public String getGjinia() {
        return gjinia;
    }

    public void setGjinia(String gjinia) {
        this.gjinia = gjinia;
    }

    public String getAdresa() {
        if (this.adresa == null) return null;
        return this.adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getKontakti_emergjent() {
        if (this.kontakti_emergjent == null) return null;
        return this.kontakti_emergjent;
    }

    public void setKontakti_emergjent(String kontakti_emergjent) {
        this.kontakti_emergjent = kontakti_emergjent;
    }

    public Integer getMjeku_familjar_id() {
        if (this.mjeku_familjar_id == null) return null;
        return this.mjeku_familjar_id;
    }

    public void setMjeku_familjar_id(int mjeku_familjar_id) {
        this.mjeku_familjar_id = mjeku_familjar_id;
    }
}
