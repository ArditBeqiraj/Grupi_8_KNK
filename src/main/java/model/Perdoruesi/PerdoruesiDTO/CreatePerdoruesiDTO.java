package model.Perdoruesi.PerdoruesiDTO;

import java.sql.Timestamp;
import java.util.Date;

public class CreatePerdoruesiDTO {
    Date date = new Date();
    private String emri;
    private String mbiemri;
    private String email;
    private String telefoni;
    private String roli;
    private String salt;
    private String hashedpassword;
    private Timestamp krijuar = new Timestamp(date.getTime());

    public CreatePerdoruesiDTO(String emri, String mbiemri, String email, String telefoni, String roli, String salt, String hashedpassword, Timestamp krijuar) {
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.email = email;
        this.telefoni = telefoni;
        this.roli = roli;
        this.salt = salt;
        this.hashedpassword = hashedpassword;
        this.krijuar = krijuar;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public void setMbiemri(String mbiemri) {
        this.mbiemri = mbiemri;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefoni() {
        return telefoni;
    }

    public void setTelefoni(String telefoni) {
        this.telefoni = telefoni;
    }

    public String getRoli() {
        return roli;
    }

    public void setRoli(String roli) {
        this.roli = roli;
    }


    public String getHashedpassword() {
        return hashedpassword;
    }

    public void setPassword(String hashedpassword) {
        this.hashedpassword = hashedpassword;
    }

    public Timestamp getKrijuar() {
        return krijuar;
    }

    public void setKrijuar(Timestamp krijuar) {
        this.krijuar = krijuar;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
