package model.Terminet.TerminetDTO;

import java.sql.Date;

public class CreateTerminetDTO {
    private int id_pacient;
    private int id_mjek;
    private int id_salle;
    private Date data;
    private String statusi;
    private String shenime;

    public CreateTerminetDTO(int id_pacient, int id_mjek, int id_salle, Date data, String statusi, String shenime) {
        this.id_pacient = id_pacient;
        this.id_mjek = id_mjek;
        this.id_salle = id_salle;
        this.data = data;
        this.statusi = statusi;
        this.shenime = shenime;
    }

    public int getId_pacient() {
        return id_pacient;
    }

    public void setId_pacient(int id_pacient) {
        this.id_pacient = id_pacient;
    }

    public int getId_mjek() {
        return id_mjek;
    }

    public void setId_mjek(int id_mjek) {
        this.id_mjek = id_mjek;
    }

    public int getId_salle() {
        return id_salle;
    }

    public void setId_salle(int id_salle) {
        this.id_salle = id_salle;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }

    public String getShenime() {
        return shenime;
    }

    public void setShenime(String shenime) {
        this.shenime = shenime;
    }
}
