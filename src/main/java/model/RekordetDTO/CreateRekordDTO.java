package model.RekordetDTO;

import java.util.Date;

public class CreateRekordDTO {
    Date date = new Date();
    private int pacient_id;
    private int doktor_id;
    private String diagnoza;
    private String udhezimi;
    private java.sql.Date data_vizites;

    public CreateRekordDTO(int pacient_id, int doktor_id, String diagnoza, String udhezimi, java.sql.Date data_vizites) {
        this.pacient_id = pacient_id;
        this.doktor_id = doktor_id;
        this.diagnoza = diagnoza;
        this.udhezimi = udhezimi;
        this.data_vizites = data_vizites;
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

    public String getDiagnoza() {
        return diagnoza;
    }

    public void setDiagnoza(String diagnoza) {
        this.diagnoza = diagnoza;
    }

    public String getUdhezimi() {
        return udhezimi;
    }

    public void setUdhezimi(String udhezimi) {
        this.udhezimi = udhezimi;
    }

    public java.sql.Date getData_vizites() {
        return data_vizites;
    }

    public void setData_vizites(java.sql.Date data_vizites) {
        this.data_vizites = data_vizites;
    }

}
