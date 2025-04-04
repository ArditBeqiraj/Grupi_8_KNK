package model.Sherbimet.SherbimetDTO;

public class CreateSherbimDTO {
    private String emri_sherbimit;
    private String pershkrimi;
    private double cmimi;

    public CreateSherbimDTO(String emri_sherbimit, String pershkrimi, double cmimi) {
        this.emri_sherbimit = emri_sherbimit;
        this.pershkrimi = pershkrimi;
        this.cmimi = cmimi;
    }

    public String getEmri_sherbimit() {
        return emri_sherbimit;
    }

    public void setEmri_sherbimit(String emri_sherbimit) {
        this.emri_sherbimit = emri_sherbimit;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
    }

    public double getCmimi() {
        return cmimi;
    }

    public void setCmimi(double cmimi) {
        this.cmimi = cmimi;
    }

}
