package model.Seancat.SeancatDTO;

import java.sql.Timestamp;

public class UpdateSeancaDTO {
    private int id;
    private Timestamp data_seances;
    private String statusi;

    public UpdateSeancaDTO(int id, Timestamp data_seances) {
        this.id = id;
        this.data_seances = data_seances;
    }

    public UpdateSeancaDTO(int id, String statusi) {
        this.id = id;
        this.statusi = statusi;
    }

    public int getSeanca_id() {
        return id;
    }

    public void setSeanca_id(int id) {
        this.id = id;
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
}