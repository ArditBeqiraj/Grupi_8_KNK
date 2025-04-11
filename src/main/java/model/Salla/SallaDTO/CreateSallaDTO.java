package model.Salla.SallaDTO;

public class CreateSallaDTO {
    private String emri_salles;
    private String tipi;
    private int kapaciteti;
    private String statusi;

    public CreateSallaDTO(String emri_salles, String tipi, int kapaciteti, String statusi) {
        this.emri_salles = emri_salles;
        this.tipi = tipi;
        this.kapaciteti = kapaciteti;
        this.statusi = statusi;
    }

    public String getEmri_salles() {
        return emri_salles;
    }

    public String getTipi() {
        return tipi;
    }

    public int getKapaciteti() {
        return kapaciteti;
    }

    public String getStatusi() {
        return statusi;
    }
}
