package model.Inventari.InventariDTO;

public class CreateInventariDTO {
    private String emri_produktit;
    private int sasia;
    private String furnizuesi;

    public CreateInventariDTO(String emri_produktit, int sasia, String furnizuesi) {
        this.emri_produktit = emri_produktit;
        this.sasia = sasia;
        this.furnizuesi = furnizuesi;
    }

    public String getEmri_produktit() {
        return emri_produktit;
    }

    public void setEmri_produktit(String emri_produktit) {
        this.emri_produktit = emri_produktit;
    }

    public int getSasia() {
        return sasia;
    }

    public void setSasia(int sasia) {
        this.sasia = sasia;
    }

    public String getFurnizuesi() {
        return furnizuesi;
    }

    public void setFurnizuesi(String furnizuesi) {
        this.furnizuesi = furnizuesi;
    }

}
