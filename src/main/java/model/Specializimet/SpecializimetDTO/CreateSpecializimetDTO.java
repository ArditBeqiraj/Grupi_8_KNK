package model.Specializimet.SpecializimetDTO;

public class CreateSpecializimetDTO {
    private String emri_specializimit;
    public CreateSpecializimetDTO(String emri_specializimit){
        this.emri_specializimit = emri_specializimit;
    }

    public String getEmri_specializimit() {
        return emri_specializimit;
    }

    public void setEmri_specializimit(String emri_specializimit) {
        this.emri_specializimit = emri_specializimit;
    }
}
