package model.Seancat.SeancatDTO;

import java.sql.Timestamp;

public class UpdateSeancaDTO {
    private int seanca_id;
    private Timestamp data_seances;
    private String statusi;

    public UpdateSeancaDTO(int seanca_id, Timestamp data_seances) {
        this.seanca_id = seanca_id;
        this.data_seances = data_seances;
    }

    public UpdateSeancaDTO(int seanca_id, String statusi) {
        this.seanca_id = seanca_id;
        this.statusi = statusi;
    }

    public int getSeanca_id() {
        return seanca_id;
    }

    public void setSeanca_id(int seanca_id) {
        this.seanca_id = seanca_id;
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