package model.FaturimiDTO;

import java.util.Date;

public class CreateFaturaDTO {
    Date date = new Date();
    private int pacient_id;
    private int sherbimi_id;
    private double shuma;
    private String statusi;
    private java.sql.Date krijuar = new java.sql.Date(date.getTime());

    public CreateFaturaDTO(int pacient_id, int sherbimi_id, double shuma, String statusi, java.sql.Date krijuar) {
        this.pacient_id = pacient_id;
        this.sherbimi_id = sherbimi_id;
        this.shuma = shuma;
        this.statusi = statusi;
        this.krijuar = krijuar;
    }

    public int getPacient_id() {
        return pacient_id;
    }

    public void setPacient_id(int pacient_id) {
        this.pacient_id = pacient_id;
    }

    public int getSherbimi_id() {
        return sherbimi_id;
    }

    public void setSherbimi_id(int sherbimi_id) {
        this.sherbimi_id = sherbimi_id;
    }

    public double getShuma() {
        return shuma;
    }

    public void setShuma(double shuma) {
        this.shuma = shuma;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }

    public java.sql.Date getKrijuar() {
        return krijuar;
    }

    public void setKrijuar(java.sql.Date krijuar) {
        this.krijuar = krijuar;
    }

}
