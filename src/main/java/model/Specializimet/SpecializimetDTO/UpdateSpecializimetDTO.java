package model.Specializimet.SpecializimetDTO;

public class UpdateSpecializimetDTO {
    private int id;
    private String emri_specializimit;

    public UpdateSpecializimetDTO(int id, String emri_specializimit) {
        this.id = id;
        this.emri_specializimit = emri_specializimit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmri_specializimit() {
        return emri_specializimit;
    }

    public void setEmri_specializimit(String emri_specializimit) {
        this.emri_specializimit = emri_specializimit;
    }
}
