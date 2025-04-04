package model.Pacientet.PacientetDTO;

import java.sql.Date;
import java.time.LocalDate;

public class CreatePacientetDTO {
    private int user_id;
    private LocalDate datelindja;
    private String gjinia;
    private String adresa;
    private String kontakti_emergjent;


    public CreatePacientetDTO(int user_id, LocalDate datelindja, String gjinia, String adresa, String kontakti_emergjent) {
        this.user_id = user_id;
        this.datelindja = datelindja;
        this.gjinia = gjinia;
        this.adresa = adresa;
        this.kontakti_emergjent = kontakti_emergjent;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getDatelindja() {
        return java.sql.Date.valueOf(datelindja);
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
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getKontakti_emergjent() {
        return kontakti_emergjent;
    }

    public void setKontakti_emergjent(String kontakti_emergjent) {
        this.kontakti_emergjent = kontakti_emergjent;
    }

}
