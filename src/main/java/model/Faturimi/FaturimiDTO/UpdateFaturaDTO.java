package model.Faturimi.FaturimiDTO;

public class UpdateFaturaDTO {
    private int fatura_id;
    private String statusi;

    public UpdateFaturaDTO(int fatura_id, String statusi) {
        this.fatura_id = fatura_id;
        this.statusi = statusi;
    }

    public int getFatura_id() {
        return fatura_id;
    }

    public void setFatura_id(int fatura_id) {
        this.fatura_id = fatura_id;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }
}