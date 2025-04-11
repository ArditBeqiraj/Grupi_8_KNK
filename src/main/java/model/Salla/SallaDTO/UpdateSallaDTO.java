package model.Salla.SallaDTO;

public class UpdateSallaDTO {
    private int id;
    private String emri_salles;
    private String tipi;
    private Integer kapaciteti;
    private String statusi;

    public UpdateSallaDTO(int id, String emri_salles, String tipi, Integer kapaciteti, String statusi) {
        this.id = id;
        this.emri_salles = emri_salles;
        this.tipi = tipi;
        this.kapaciteti = kapaciteti;
        this.statusi = statusi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmri_salles() {
        return emri_salles;
    }

    public void setEmri_salles(String emri_salles) {
        this.emri_salles = emri_salles;
    }

    public String getTipi() {
        return tipi;
    }

    public void setTipi(String tipi) {
        this.tipi = tipi;
    }

    public Integer getKapaciteti() {
        return kapaciteti;
    }

    public void setKapaciteti(Integer kapaciteti) {
        this.kapaciteti = kapaciteti;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }
}
