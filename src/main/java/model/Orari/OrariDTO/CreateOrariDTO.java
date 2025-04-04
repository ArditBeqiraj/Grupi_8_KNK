package model.Orari.OrariDTO;

import java.sql.Timestamp;

public class CreateOrariDTO {
    private int perdoruesi_id;
    private Timestamp fillimi_ndrrimit;
    private Timestamp perfundimi_ndrrimit;
    private String roli;

    public CreateOrariDTO(int perdoruesi_id, Timestamp fillimi_ndrrimit, Timestamp perfundimi_ndrrimit, String roli) {
        this.perdoruesi_id = perdoruesi_id;
        this.fillimi_ndrrimit = fillimi_ndrrimit;
        this.perfundimi_ndrrimit = perfundimi_ndrrimit;
        this.roli = roli;
    }

    public int getPerdoruesi_id() {
        return perdoruesi_id;
    }

    public void setPerdoruesi_id(int perdoruesi_id) {
        this.perdoruesi_id = perdoruesi_id;
    }

    public Timestamp getFillimi_ndrrimit() {
        return fillimi_ndrrimit;
    }

    public void setFillimi_ndrrimit(Timestamp fillimi_ndrrimit) {
        this.fillimi_ndrrimit = fillimi_ndrrimit;
    }

    public Timestamp getPerfundimi_ndrrimit() {
        return perfundimi_ndrrimit;
    }

    public void setPerfundimi_ndrrimit(Timestamp perfundimi_ndrrimit) {
        this.perfundimi_ndrrimit = perfundimi_ndrrimit;
    }

    public String getRoli() {
        return roli;
    }

    public void setRoli(String roli) {
        this.roli = roli;
    }

}
