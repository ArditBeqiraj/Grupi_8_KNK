package model.Sherbimet.SherbimetDTO;

public class UpdateSherbimDTO {
    private int id;
    private double cmimi;

    public UpdateSherbimDTO(int id, double cmimi) {
        this.id = id;
        this.cmimi = cmimi;
    }

    public int getSherbimi_id() {
        return id;
    }

    public void setSherbimi_id(int id) {
        this.id = id;
    }

    public double getCmimi() {
        return cmimi;
    }

    public void setCmimi(double cmimi) {
        this.cmimi = cmimi;
    }
}