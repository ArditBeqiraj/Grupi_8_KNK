package model.Faturimi.FaturimiDTO;

public class UpdateFaturaDTO {
    private int id;
    private String statusi;

    public UpdateFaturaDTO(int id, String statusi) {
        this.id = id;
        this.statusi = statusi;
    }

    public int getFatura_id() {
        return id;
    }

    public void setFatura_id(int id) {
        this.id = id;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }
}