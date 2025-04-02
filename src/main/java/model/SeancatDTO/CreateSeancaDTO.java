package model.SeancatDTO;

import java.sql.Timestamp;

public class CreateSeancaDTO {
    private int pacient_id;
    private int doktor_id;
    private Timestamp data_seances;
    private String statusi;
    private String shenime;

    public CreateSeancaDTO(int pacient_id, int doktor_id, Timestamp data_seances, String statusi, String shenime) {
        this.pacient_id = pacient_id;
        this.doktor_id = doktor_id;
        this.data_seances = data_seances;
        this.statusi = statusi;
        this.shenime = shenime;
    }

    public int getPacient_id() {
        return pacient_id;
    }

    public void setPacient_id(int pacient_id) {
        this.pacient_id = pacient_id;
    }

    public int getDoktor_id() {
        return doktor_id;
    }

    public void setDoktor_id(int doktor_id) {
        this.doktor_id = doktor_id;
    }

    public Timestamp getData_seances() {
        return data_seances;
    }

    public void setData_seances(Timestamp data_seances) {
        this.data_seances = data_seances;
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
